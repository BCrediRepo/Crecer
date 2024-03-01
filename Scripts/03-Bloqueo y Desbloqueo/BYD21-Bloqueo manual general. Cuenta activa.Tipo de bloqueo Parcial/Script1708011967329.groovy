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
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 56), findTestData('MainData/Users').getValue(
        2, 56))

WebUI.click(findTestObject('02-Dashboard/lnkCuentas'))

WebUI.click(findTestObject('02-Dashboard/37-Cuentas/lnkModificaciondDeCuenta'))

WebUI.click(findTestObject('02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/lnkBloqueoyDesbloqueo'))

WebUI.click(findTestObject('02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/lnkBloqueo'))

WebUI.click(findTestObject('02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/01-Bloqueo/lnkSeleccionandoCuenta'))

WebUI.switchToWindowTitle('BCCL.E.AC.BLO.POR.CTA')

CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

WebUI.switchToWindowIndex(0)

WebUI.click(findTestObject('02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/01-Bloqueo/lnkSeleccionandoCuenta'))

WebUI.switchToWindowTitle('BCCL.E.AC.BLO.POR.CTA')

WebUI.setText(findTestObject('04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.CTA/txtCuentaID'), '10200022000')

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.CTA/lnkBloqueoParcial'))

WebUI.setText(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/txtMotivo'), 'AF')

WebUI.setText(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/txtFechaDesde'), '20230828')

WebUI.setText(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/txtMonto'), '100')

WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/btnAceptarRegistro'))

WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/lnkAceptarAlertas'))

WebUI.verifyElementVisible(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/lblTxnCompleta'))

txn = WebUI.getText(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/lblTxnCompleta'))

assert txn.contains("Txn Completa: ") == true

//Control fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}


