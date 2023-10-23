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
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 33), findTestData('MainData/Users').getValue(
        2, 33))

//Busqueda ENQ
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.PER.GEN.PJ.FIN')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Consulta General personas Juridicas')

//Seteo de datos
WebUI.setText(findTestObject('15-MONEX/Consulta General personas Juridicas/txtIDPersona'), '1002151621')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Busqueda de consulta
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebUI.verifyElementVisible(findTestObject('15-MONEX/Consulta General personas Juridicas/lblAsociado'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println(('Tiempo transcurrido: ' + elapsedTime) + ' milisegundos')

//---------------------------
//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))

TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))

println(TotalRegistros)

persona = WebUI.getText(findTestObject('15-MONEX/Consulta General personas Juridicas/lblAsociado'))

//Verificacion de que es la persona que filtramos
assert persona == '1002151621'

//Detalle de consulta por transacciones acumuladas MONEX (click en boton largavistas)
WebUI.click(findTestObject('15-MONEX/Consulta General personas Juridicas/btnAcumuladoPorAsociado'))

WebUI.switchToWindowTitle('BCCL.E.ACUM.OPER.MONEX')

// Obtén el elemento de la tabla
WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay")) 

// Obtén todas las filas dentro de la tabla
List<WebElement> rows = table.findElements(By.tagName("tr")) 

// Variable para rastrear si se encontró un valor diferente de "0,00"
boolean foundNonZeroValue = false 

// Itera a través de las filas (desde 1 hasta 10)
for (int i = 0; i < Math.min(9, rows.size()); i++) {
	WebElement row = rows[i]

	// Obtiene el tercer valor de la fila (índice 2, ya que las listas son base cero)
	WebElement cell = row.findElements(By.tagName("td"))[3]

	// Obtiene el texto de la celda
	String cellText = cell.getText()
	
	// Compara el valor de la celda con "0,00"
	if (!cellText.equals("0")) {
		foundNonZeroValue = true
		break
	}
}

assert foundNonZeroValue == false

//Control fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

