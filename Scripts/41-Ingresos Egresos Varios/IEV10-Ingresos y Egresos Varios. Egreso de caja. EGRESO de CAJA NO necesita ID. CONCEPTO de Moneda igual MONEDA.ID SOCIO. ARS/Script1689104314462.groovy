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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(
		2, 3))

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

//Ir a Sucursal piloto
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkSucursalPiloto'))

//Selecciona D2 AUTOMATIZACION DE SUCURSALES
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkD2AutomatizaciondeSucursales'))

//Selecciona INGRESOS Y EGRESOS
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkINGRESOSYEGRESOS'))

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ir a EGRESOS De caja
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkEGRESOSDECAJA'))
WebUI.switchToWindowTitle('TELLER')

//Maximiza la pantalla
WebUI.maximizeWindow()

//Verifica titulo de Egresos Varios de Caja
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/EgresosDeCaja/lblTituloEgresosVariosdeCaja'))

//Ingresa importe 10
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/EgresosDeCaja/txtImporteARS'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/EgresosDeCaja/txtImporteARS'),'10')

//Ingresa concepto 18805IEE (pago honorarios sin iva)
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/EgresosDeCaja/txtConcepto'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/EgresosDeCaja/txtConcepto'),'18805IEE')

//Click en Validar
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/EgresosDeCaja/btnValidar'))
WebUI.delay(3)

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en Aceptar
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/EgresosDeCaja/btnAceptarRegistro'))
WebUI.delay(3)

//Click en Aceptar Alertas
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/EgresosDeCaja/btnAceptarAlertas'))
WebUI.delay(10)

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/EgresosDeCaja/lblTxn Completa'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/EgresosDeCaja/lblTxn Completa'))
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/EgresosDeCaja/lblTxn Completa'))
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