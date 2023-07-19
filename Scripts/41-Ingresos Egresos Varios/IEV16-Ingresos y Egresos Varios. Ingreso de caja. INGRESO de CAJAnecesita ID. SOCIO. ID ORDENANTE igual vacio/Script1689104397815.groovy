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

//Ir a Sucursal piloto
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkSucursalPiloto'))

//Selecciona D2 AUTOMATIZACION DE SUCURSALES
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkD2AutomatizaciondeSucursales'))

//Selecciona INGRESOS Y EGRESOS
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkINGRESOSYEGRESOS'))

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ir a INGRESOS De caja
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkINGRESOSDECAJA'))
WebUI.switchToWindowTitle('TELLER')

//Maximiza la pantalla
WebUI.maximizeWindow()

//Verifica titulo de Ingresos Varios de Caja
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresosDeCaja/lblTituloIngresosVariosdeCaja'))

//Ingresa Moneda USD
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresosDeCaja/rdMonedaDropdown'),6)
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresosDeCaja/rdMonedaDropdown'))
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresosDeCaja/rdDolarEstadounidense'))

//Ingresa importe 10
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresosDeCaja/txtImporteUSD-EUR'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresosDeCaja/txtImporteUSD-EUR'),'10')

//Ingresa concepto 18155IEI (Cheque Cancelatorio - Emision)
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresosDeCaja/txtConcepto'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresosDeCaja/txtConcepto'),'18155IEI')

//Click en Validar
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresosDeCaja/btnValidarRegistro'))
WebUI.delay(3)

//Selecciona que SI es socio
WebUI.waitForElementPresent(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresosDeCaja/cbNOSI'), 6)
WebUI.selectOptionByIndex(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresosDeCaja/cbNOSI'), 2)

//Ingresa persona juridica id ordenante 0303456
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresosDeCaja/txtOrdenante'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresosDeCaja/txtOrdenante'),'')

//Click en Validar
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresosDeCaja/btnValidarRegistro'))
WebUI.delay(3)

//Maximiza la pantalla
WebUI.maximizeWindow()

//Espera y recibe mensaje de que el id persona esta vacio
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresosDeCaja/lblOrdenanteVacio'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresosDeCaja/lblOrdenanteVacio'))
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresosDeCaja/lblOrdenanteVacio'))
assert element.contains('PARA ESTA TRANSACCION DEBE CAPTURAR NO. DE DOCUMENTO')

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
