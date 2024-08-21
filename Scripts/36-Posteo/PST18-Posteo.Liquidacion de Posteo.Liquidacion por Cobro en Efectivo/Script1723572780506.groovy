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
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 48), findTestData('MainData/Users').getValue(
		2, 48))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("?1", 1)

def menuDesplegable = ["Sucursal Piloto", "D2 - Posteo", "POSTEO"]
def link = "COBRO EN EFECTIVO"
//si el menú que busco está en Temenos T24, uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//se cargan datos de posteo
WebUI.switchToWindowTitle('Movimiento de Fondos')
WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/btnDesplegarConcepto'))
WebUI.click(findTestObject('Object Repository/37-Posteo/Movimiento de Fondos/lblConcepto3'))
WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/txtNombrePosteo'))
WebUI.setText(findTestObject('37-Posteo/Movimiento de Fondos/txtNombrePosteo'), 'PRUEBAS CRECER')
WebUI.setText(findTestObject('Object Repository/37-Posteo/Movimiento de Fondos/txtImporte'), '50')
WebUI.setText(findTestObject('37-Posteo/Movimiento de Fondos/txtReferPosteo'), 'PRUEBAS CRECER')
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

Transaccion1 = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))

// Dividir la cadena por espacios en blanco y tomar el segundo elemento
def partes = Transaccion1.split('\\s+')

def trx1 = partes[2]

assert Transaccion1.contains('Txn Completa:')

//Screen pdf
WebUI.switchToWindowIndex(3)
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.switchToWindowIndex(0)

WebUI.click(findTestObject('02-Dashboard/btnLogout'))

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 49), findTestData('MainData/Users').getValue(
		2, 49))

def menuDesplegable2 = ["Posteo"]
def link2 = "Transacciones Pendientes de Liquidacion"

//Si el menu que busco está en dashboard uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable2, link2)

WebUI.switchToWindowTitle('BCCL.E.EB.POSTEO.INAU')

// Obtén el elemento de la tabla
WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
 
// Obtén todas las filas dentro de la tabla
List<WebElement> rows = table.findElements(By.tagName("tr"))
 
// Valor específico que estás buscando
String targetValue = trx1
 
// Variable para rastrear si se encontró el valor específico
boolean foundTargetValue = false
 
// Itera a través de las filas
for (WebElement row : rows) {
	// Obtiene el tercer valor de la fila (índice 2, ya que las listas son base cero)
	WebElement cell = row.findElements(By.tagName("td"))[0]
 
	// Obtiene el texto de la celda
	String cellText = cell.getText()
 
	// Compara el valor de la celda con el valor específico
	if (cellText.equals(targetValue)) {
		foundTargetValue = true
			
		// Obtiene la lista de elementos td
		List<WebElement> tdList = row.findElements(By.tagName("td"))
		
		// Accede al elemento td en la posición 8
		WebElement tdElement = tdList[8]
 
		// Intenta encontrar el elemento 'a' dentro del elemento td
		WebElement liquidar = tdElement.findElement(By.tagName("a"))
 
		// Haz clic en el enlace
		liquidar.click()
		
		break
	}
}

WebUI.switchToWindowTitle('Movimiento de Fondos')

//Valida que haya sido un posteo de cobro en efectivo con el concepto
conceptoAssert = WebUI.getText(findTestObject('Object Repository/37-Posteo/Movimiento de Fondos/lblConceptoCobroEfect'))
assert conceptoAssert.contains('18030PMI')

WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAutorizaRegistro'))

WebUI.switchToWindowIndex(2)

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.switchToWindowTitle('Movimiento de Fondos')

Transaccion2 = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))

assert Transaccion2.contains('Txn Completa:') == true

//-------------------------------------------------------------------------------------------------------
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}