import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//librerias necesarias

import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory


//ESTA Funcion setea la cantidad de denominaciones pasadas por parametro en 'tab2' con la denominacion 'CIEN PESOS'
def SetTabla(String tabla, String variable, int posVariable, int posInput, String cant) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id(tabla))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (int i = 0; i < rows.size(); i++) {
		WebElement row = rows[i]
		WebElement cell = row.findElements(By.tagName("td"))[posVariable]
		String cellText = cell.getText()
		if (cellText.equals(variable)) {
			// Incrementamos la fila (i + 1)
			if (i + 1 < rows.size()) {
				WebElement nextRow = rows[i + 1]
				List<WebElement> tdList = nextRow.findElements(By.tagName("td"))
				WebElement tdElement = tdList[posInput]
				WebElement lnkElement = tdElement.findElement(By.tagName("input"))
				lnkElement.sendKeys(cant)
				return true
			} else {
				println("No hay una fila siguiente.")
				return false
			}
		}
	}
	return false
}

//Funcion que setea la cantidad de denominaciones pasadas por parametro en 'tab2' con la denominacion 'CIEN PESOS'
def encontrado = false
while (!encontrado) {
	encontrado = SetTabla('tab2','CIEN PESOS', 4, 3, '1')
}

//LOS PARAMETROS INDICAN: (De izq a der) 
//--> 'tab2' es la tabla que contiene los elementos con los que vamos a interactuar
//--> 'CIEN PESOS' es la denominacion que vamos a buscar en la columna 4
//--> '4' es la columna donde localizamos las denominaciones a buscar
//--> '3' es la columna donde vamos a escribir la cantidad de denominaciones antes buscada
//--> '1' es la cantidad de denominaciones que queremos setear en la columna 3.
//--> saluditos