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
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 14), findTestData('MainData/Users').getValue(
		2, 14))

WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ejecuta en la linea de comando menu ?1
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?1')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Abre la pestaña del menú ?01
WebUI.switchToWindowTitle('Temenos T24')

//Maximiza la pantalla
WebUI.maximizeWindow()

//Ir a Sucursal piloto
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkSucursalPiloto'))

//Selecciona D2 AUTOMATIZACION DE SUCURSALES
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkD2AutomatizaciondeSucursales'))
//WebUI.delay(3)

//Selecciona POSTEO PLANTA CAJA
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/lnkPOSTEOPLANTACAJA'))
//WebUI.delay(3)

//Selecciona POSTEO
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/lnkPOSTEO'))
//WebUI.delay(3)

//Ir a Pago en Efectivo
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/lnkPAGOENEFECTIVO'))

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se mueve a la ventana Movimiento de Fondos
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Maximiza la pantalla
WebUI.maximizeWindow()

//Verifica titulo de Detalle de operaciones sin efectivo
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Pago en Efectivo/lblTituloPosteoPagoenEfectivo'))

//Ingresa CONCEPTO 18950PME (Posteo Manual Generico s/ide)
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Pago en Efectivo/txtConcepto'),6)
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Pago en Efectivo/btnConceptoDropdown'))
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Pago en Efectivo/txtConcepto'),'18950PME')
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Pago en Efectivo/btnConceptoDropdown'))

//Ingreso nombre posteo
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Pago en Efectivo/txtNombrePosteo'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Pago en Efectivo/txtNombrePosteo'),'PAGO EN EFECTIVO')

//Ingresa monto
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Pago en Efectivo/txtImporte'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Pago en Efectivo/txtImporte'),'10')

//Validar la operación
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Pago en Efectivo/btnValidarRegistro'))

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click Aceptar
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Pago en Efectivo/btnAceptarRegistro'))
//WebUI.delay(10)

//Click Aceptar Alertas
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Pago en Efectivo/btnAceptarAlertas'))
//WebUI.delay(10)

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Pago en Efectivo/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Pago en Efectivo/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Pago en Efectivo/lblTxnCompleta'))
assert element.contains('Txn Completa:') 

//Definir Objeto
Transaccion1 = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion1.split('\\s+')
def trx1 = partes[2]
GlobalVariable.vTxn = trx1
assert Transaccion1.contains('Txn Completa:')

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se mueve a la ventana del comprobante
//WebUI.switchToWindowTitle('e-forms')

//Maximiza la pantalla
WebUI.maximizeWindow()

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