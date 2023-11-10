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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
        2, 9))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//ingreso a comision de persona especial y extraccion de numero de cuenta del bolsin
//a utilizar en el caso
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.EB.COM.PER.ESP')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('COM.PER.ESP')

WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnDesplegar'))

WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/lblBolsinAElegir'))

WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnVerRegistro'))

NroCuenta = WebUI.getText(findTestObject('06-Comisiones/COM.PER.ESP/lblCuenta'))

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.closeWindowTitle('COM.PER.ESP')

WebUI.switchToWindowIndex(0)

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.COM.PER.ESP.BOLSIN')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//apertura app de alta, baja, modificacion de bolsin
//WebUI.click(findTestObject('02-Dashboard/lnkComisionesyBonificaciones'))
//WebUI.click(findTestObject('02-Dashboard/04-Comisiones/1-Comisiones y Bonificaciones/lnkComisiones'))
//WebUI.click(findTestObject('02-Dashboard/04-Comisiones/1-Comisiones y Bonificaciones/lnkBolsin'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//seteo de datos
WebUI.switchToWindowTitle('A/B/M Bolsin')

WebUI.clearText(findTestObject('06-Comisiones/ABM Bolsin/txtNumeroCuenta'))

WebUI.setText(findTestObject('06-Comisiones/ABM Bolsin/txtNumeroCuenta'), NroCuenta)

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebUI.verifyElementVisible(findTestObject('06-Comisiones/ABM Bolsin/lnkBaja'))

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

//-----------------------------
//Baja de bolsin
WebUI.click(findTestObject('06-Comisiones/ABM Bolsin/lnkBaja'))

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.switchToWindowTitle('COM.PER.ESP')

WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnConfirmarBajaBolsin'))

WebUI.delay(10)

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verificacion final
WebUI.verifyElementVisible(findTestObject('06-Comisiones/COM.PER.ESP/lblTrxCompleta'), FailureHandling.STOP_ON_FAILURE)

trx = WebUI.getText(findTestObject('06-Comisiones/COM.PER.ESP/lblTrxCompleta'))

assert trx.contains('Txn Completa:') == true //Control fin e script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

