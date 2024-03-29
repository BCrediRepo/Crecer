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
WebUI.delay(3)

//Selecciona POSTEO PLANTA CAJA
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/lnkPOSTEOPLANTACAJA'))
WebUI.delay(3)

//Selecciona POSTEO
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/lnkPOSTEO'))
WebUI.delay(3)

//Ir a cobro en Efectivo
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/lnkCOBROENEFECTIVO'))

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se mueve a la ventana Movimiento de Fondos
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Maximiza la pantalla
WebUI.maximizeWindow()

//Verifica titulo de Detalle de operaciones sin efectivo
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/lblTituloPosteoCobroenEfectivo'))

//Ingresa moneda en USD
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/txtMoneda'),6)
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/btnMonedaDropdown'))
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/txtMoneda'),"USD")
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/btnMonedaDropdown'))

//Ingresa CONCEPTO 18030PMI (Legales - Cobranza en efectivo)
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/txtConcepto'),6)
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/btnConceptoDropdown'))
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/txtConcepto'),'18030PMI')
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/btnConceptoDropdown'))

//Ingreso nombre posteo
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/txtNombrePosteo'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/txtNombrePosteo'),'COBRO EN EFECTIVO')

//Ingresa monto
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/txtImporte'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/txtImporte'),'10')

//Validar la operación
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/btnValidar'))

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click Aceptar
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/btnAceptar'))
WebUI.delay(10)

//Click Aceptar Alertas
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/btnAceptarAlertas'))
WebUI.delay(10)

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/lblTxnCompleta'))
assert element.contains('Txn Completa:') 

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