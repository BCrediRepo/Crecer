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


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 49), findTestData('MainData/Users').getValue(2, 49))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se accede al menu ?327
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?327')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

//Maximizamos
WebUI.maximizeWindow()

//Click en Dispositivos
WebUI.click(findTestObject('Object Repository/21-Fallas/05-Temenos T24/spanDispositivos'))

//Click en Registro de Fallas
WebUI.click(findTestObject('Object Repository/21-Fallas/05-Temenos T24/spanRegistro de Fallas en Dispositivos'))

//Click en Alta de faltantes ATM/CD/TAS
WebUI.click(findTestObject('Object Repository/21-Fallas/05-Temenos T24/lnkAlta de Faltantes de ATMCDTAS'))

//Switch a la ventana TELLER
WebUI.switchToWindowTitle('TELLER')

//Maximizamos
WebUI.maximizeWindow()

//Ingresa monto
WebUI.setText(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/txtMonto'), '100')

//Validar
WebUI.click(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/btnValidarRegistro'))

//Selecciona rb Deposito
WebUI.waitForElementVisible(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/rbDeposito'),6)
WebUI.click(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/rbDeposito'))

//Validar
WebUI.click(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/btnValidarRegistro'))

//Selecciona dispositivo
WebUI.waitForElementVisible(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/btnDropdownDispositivo'),6)
WebUI.click(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/btnDropdownDispositivo'))
WebUI.click(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/btnPrimerDispositivo'))

//Sacamos captura
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Validar
WebUI.click(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/btnValidarRegistro'))

//Click pestaña CR
WebUI.click(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/btnDenominacionesCR'))

//Completa denominación
WebUI.setText(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/txtCantidad100'), '1')

//Sacamos captura
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Validar 
WebUI.click(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/btnValidarRegistro'))

//Aceptar
WebUI.click(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/btnAceptarRegistro'))

//Aceptar Alertas
WebUI.click(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/btnAceptarAlertas'))

//Maximizamos
WebUI.maximizeWindow()

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/lblTxnCompleta'))

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

//Ingresa el numero de operacion obtenido
WebUI.setText(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/txtFaltantesEnDispositivosTransactionId'), terceraPalabra)

//Sacamos captura
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ver un registro
WebUI.click(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/btnVerRegistro'))

//Validar un un dato de la tabla
WebUI.waitForElementVisible(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/lblIdDispositivo'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/lblIdDispositivo'))
def element2 = WebUI.getText(findTestObject('Object Repository/21-Fallas/Alta de Faltantes de ATM-CD-TAS/lblIdDispositivo'))

assert element2.contains('Id Dispositivo')

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



