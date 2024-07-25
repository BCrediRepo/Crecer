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

//Esta funcion es invocada cuando se pregunta si el elemento que se quiere encontrar fue localizado en la tabla.
//List<WebElement> ubica los valores de las rows (tag tr) en una lista ordenada
//el for recorre row por row y verifica en una columna indicada para que busque el dato proporcionado
//en este caso, si hay match con el dato que se busca, en esa misma fila busca el boton en la posicion td 16
//y le hace click

//en el bucle while invocamos la funcion y si el resultado que devuelve es true, cambia la bandera que ingreso como false
//al while y sale del mismo


//Reacondicionar en cada script segun sea necesario, este es solo un modelo para seguir

def variable = "valor"
def buscarElementoEnTabla(String variable) {		
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {		
		WebElement cell = row.findElements(By.tagName("td"))[1]		
		String cellText = cell.getText()
		if (cellText.equals(variable)) {			
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			WebElement tdElement = tdList[16]
			WebElement lnkElement = tdElement.findElement(By.tagName("a"))
			lnkElement.click()
			return true
		}
	}
	return false
}

def encontrado = false
while (!encontrado) {
	encontrado = buscarElementoEnTabla(variable)
	if (!encontrado) {
		WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnSiguiente'))
		WebUI.delay(2)
	}	
}
