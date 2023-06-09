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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(
        2, 1))

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

//Selecciona CONSULTA OPERATORIA DE FILIALES
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkCONSULTASOPERATORIASDEFILIALES'))

//Selecciona DETALLE DE OPERACIONES
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkDETALLEDEOPERACIONES'))

//Ir a AJUSTES DE DENOMINACIONES
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkAJSUTESDEDENOMINACION'))

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.switchToWindowTitle('TELLER')

//Verifica titulo de ajustes monetarios
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/lbltituloCambioDeDenominacion')) 

//Ingresa un monto en ARS
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/inputMonto'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/inputMonto'),'1000')

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Valida el registro
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/btnValidarRegistro'))

//Ingresa una DENOMINACIONES DB 1
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/btnDenominacionesDB'))
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/inputCantidad.1DB'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/inputCantidad.1DB'),'1')

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresa una DENOMINACIONES CR 1
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/btnDenominacionesCR'))
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/input_Cantidad.1CR'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/input_Cantidad.1CR'),'1')

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Acepta el registro
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/btnAceptarRegistro'))

//Aceptar alertas.
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/btnAceptarAlertas'),6)
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/btnAceptarAlertas'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/lblTxnCompleta'))
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

