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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 2), findTestData('MainData/Users').getValue(2, 2))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se accede al menu ?327
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?327')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

//Maximizamos
WebUI.maximizeWindow()

def menuDesplegable = ["Registro de Fallas de Caja"]
def link = "Faltantes de Otro Usuario"

//si el menú que busco está en Temenos T24, uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Switch a la ventana TELLER
WebUI.switchToWindowTitle('TELLER')

//Maximizamos
WebUI.maximizeWindow()

//Ingresa monto
WebUI.setText(findTestObject('Object Repository/21-Fallas/Faltante de Caja de otro Usuario/txtMonto'), '10')

//Validar
WebUI.click(findTestObject('Object Repository/21-Fallas/Faltante de Caja de otro Usuario/btnValidarRegistro'))

//Ingresa comentario
WebUI.setText(findTestObject('Object Repository/21-Fallas/Faltante de Caja de otro Usuario/txtComentarios'), 'Prueba')

//Ingresa caja
WebUI.click(findTestObject('Object Repository/21-Fallas/Faltante de Caja de otro Usuario/btnCajaDropdown'))
WebUI.click(findTestObject('Object Repository/21-Fallas/Faltante de Caja de otro Usuario/btnPrimeraCaja'))

//Ingresa ID Persona
WebUI.setText(findTestObject('Object Repository/21-Fallas/Faltante de Caja de otro Usuario/txtIdPersona'), '1000873562')

//Sacamos captura
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Validar
WebUI.click(findTestObject('Object Repository/21-Fallas/Faltante de Caja de otro Usuario/btnValidarRegistro'))

//Aceptar
WebUI.click(findTestObject('Object Repository/21-Fallas/Faltante de Caja de otro Usuario/btnAceptarRegistro'))

//Aceptar Alertas
WebUI.click(findTestObject('Object Repository/21-Fallas/Faltante de Caja de otro Usuario/btnAceptarAlertas'))

//Maximizamos
WebUI.maximizeWindow()

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/21-Fallas/Faltante de Caja de otro Usuario/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/21-Fallas/Faltante de Caja de otro Usuario/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/21-Fallas/Faltante de Caja de otro Usuario/lblTxnCompleta'))

assert element.contains('Txn Completa:')

//Sacamos captura
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Imprimir el numero de operacion en consola
println("El ID de la txt es: " + element)
 
//Dividir la oración en palabras individuales utilizando el espacio como separador
String[] palabras = element.split(" ");
 
// Obtener la tercera palabra (índice 2 ya que los índices comienzan en 0 en arrays)
String terceraPalabra = palabras[2];
 
// Imprimir la tercera palabra seleccionada
println("La tercera palabra es: " + terceraPalabra);

//Abre la pestaña TELLER
WebUI.switchToWindowTitle('TELLER')

//Ingresa el numero de operacion obtenido
WebUI.setText(findTestObject('Object Repository/21-Fallas/Faltante de Caja de otro Usuario/txtFaltantedeCajadeOtroUsuarioTransactionId'), terceraPalabra)

//Sacamos captura
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ver un registro
WebUI.click(findTestObject('Object Repository/21-Fallas/Faltante de Caja de otro Usuario/btnVerRegistro'))

//Validar un un dato de la tabla
WebUI.waitForElementVisible(findTestObject('Object Repository/21-Fallas/Faltante de Caja de otro Usuario/lblFecha'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/21-Fallas/Faltante de Caja de otro Usuario/lblFecha'))
def element2 = WebUI.getText(findTestObject('Object Repository/21-Fallas/Faltante de Caja de otro Usuario/lblFecha'))

assert element2.contains('Fecha')

//---------------------------------------------------------------------------------------------------------------------

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}



