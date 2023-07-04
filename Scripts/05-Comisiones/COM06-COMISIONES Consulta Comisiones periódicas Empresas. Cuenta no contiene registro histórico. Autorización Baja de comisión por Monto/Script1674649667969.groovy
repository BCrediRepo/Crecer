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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,9), findTestData('MainData/Users').getValue(2,9))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.EB.COM.PER.ESP')
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('COM.PER.ESP')
WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnDesplegar'))
WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/lblBolsinAElegir'))
WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnVerRegistro'))
NroCuenta = WebUI.getText(findTestObject('06-Comisiones/COM.PER.ESP/lblCuenta'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.closeWindowTitle('COM.PER.ESP')

WebUI.switchToWindowTitle('T24 - Fil.074 Caseros Centro')
WebUI.click(findTestObject('02-Dashboard/lnkComisionesyBonificaciones'))
WebUI.click(findTestObject('02-Dashboard/04-Comisiones/1-Comisiones y Bonificaciones/lnkComisiones'))
WebUI.click(findTestObject('02-Dashboard/04-Comisiones/1-Comisiones y Bonificaciones/lnkBolsin'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.switchToWindowTitle('A/B/M Bolsin')
WebUI.clearText(findTestObject('06-Comisiones/ABM Bolsin/txtNumeroCuenta'))
WebUI.setText(findTestObject('06-Comisiones/ABM Bolsin/txtNumeroCuenta'), NroCuenta)
WebUI.click(findTestObject('06-Comisiones/ABM Bolsin/lnkEjecutar'))
WebUI.click(findTestObject('06-Comisiones/ABM Bolsin/lnkBaja'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.switchToWindowTitle('COM.PER.ESP')
WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnConfirmarBajaBolsin'))
WebUI.delay(10)
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

trx = WebUI.verifyElementVisible(findTestObject('06-Comisiones/COM.PER.ESP/lblTrxCompleta'), FailureHandling.STOP_ON_FAILURE)

if (trx == true) {
    WebUI.takeScreenshot('Screenshot/Comisiones/COM06-COMISIONES. Comisiones. Consulta Comisiones periódicas Empresas. Cuenta no contiene registro histórico. Autorización Baja de comisión por Monto.png')
} else {
    WebUI.takeScreenshot('Screenshot/Fails/Comisiones/COM06-COMISIONES.Comisiones. Consulta Comisiones periódicas Empresas. Cuenta no contiene registro histórico. Autorización Baja de comisión por Monto.png')
}

//Desbloqueo de usuario CRECEREM
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 12), findTestData('MainData/Users').getValue(
		2, 12))

WebUI.maximizeWindow()

//Toma Screen
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresa el ENQ en el Buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'RECORD.LOCK')

//Clickea en el btn "Ejecutar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambia a ventana nueva
WebUI.switchToWindowTitle('Active Record Locks')

//Click en Lista de Registro
WebUI.click(findTestObject('06-Comisiones/DESBLOQUEO CUENTA/Page_Active Record Locks/btnListadeRegistros'))

//Click Cuenta Bloqueada
WebUI.click(findTestObject('06-Comisiones/DESBLOQUEO CUENTA/Page_Active Record Locks/lnkCuentaBloqueada'))

//Auto/Bor/Rev Registro
WebUI.click(findTestObject('06-Comisiones/DESBLOQUEO CUENTA/Page_Active Record Locks/btnAutoBorraRevRegistro'))

//Desbloquear
WebUI.click(findTestObject('06-Comisiones/DESBLOQUEO CUENTA/Page_Active Record Locks/btnDesbloquear')) //Control de fin de script

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
