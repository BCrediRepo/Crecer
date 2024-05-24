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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 11), findTestData('MainData/Users').getValue(
        2, 11))

//Carga comision inicial
WebUI.click(findTestObject('02-Dashboard/lnkComisionesyBonificaciones'))

WebUI.click(findTestObject('02-Dashboard/04-Comisiones/1-Comisiones y Bonificaciones/lnkComisiones'))

WebUI.click(findTestObject('02-Dashboard/04-Comisiones/lnkCobroComisiones Manuales - Planta'))

WebUI.switchToWindowIndex(1)

WebUI.selectOptionByIndex(findTestObject('56-Comisiones Manuales/Account Charge Request/cbxTipoPago'), 1)

WebUI.click(findTestObject('56-Comisiones Manuales/Account Charge Request/btnValidar Registro'))

WebUI.setText(findTestObject('56-Comisiones Manuales/Account Charge Request/txtCuentaDebito'), '00430300691')

WebUI.click(findTestObject('56-Comisiones Manuales/Account Charge Request/btnValidar Registro'))

WebUI.setText(findTestObject('56-Comisiones Manuales/Account Charge Request/txtFecha'), '30 AGO 2023')

WebUI.click(findTestObject('56-Comisiones Manuales/Account Charge Request/btnValidar Registro'))

WebUI.setText(findTestObject('56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'), '18306CMI')

WebUI.click(findTestObject('56-Comisiones Manuales/Account Charge Request/btnValidar Registro'))

WebUI.setText(findTestObject('56-Comisiones Manuales/Account Charge Request/txtObservaciones'), 'PRUEBASCRECER')

WebUI.click(findTestObject('56-Comisiones Manuales/Account Charge Request/btnAceptar Registro'))

//tarda una monstruosidad en dar el codigo de txn completa en TES11
//Si en TES10 anda piola, borrar o comentar este paso
WebUI.delay(60)

TxnInicial = WebUI.getText(findTestObject('56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))

def parts = TxnInicial.tokenize(' ')

def transaccion = parts[2]

WebUI.setText(findTestObject('56-Comisiones Manuales/Account Charge Request/txtBusquedaRegistro'), transaccion)

WebUI.click(findTestObject('56-Comisiones Manuales/Account Charge Request/btnVerRegistro'))

WebUI.switchToWindowIndex(2)

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.closeWindowIndex(2)

WebUI.switchToWindowIndex(1)

WebUI.verifyElementVisible(findTestObject('56-Comisiones Manuales/Account Charge Request/lblBonificacion'))

Bonif = WebUI.getText(findTestObject('56-Comisiones Manuales/Account Charge Request/lblBonificacion'))

assert Bonif == ""

//Control fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}
