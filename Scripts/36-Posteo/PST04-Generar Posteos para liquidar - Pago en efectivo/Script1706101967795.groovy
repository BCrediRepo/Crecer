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

//Accedemos a la enq a traves sucursal piloto en el menu ?1
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?1')
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))
WebUI.switchToWindowTitle('Temenos T24')
WebUI.click(findTestObject('02-Dashboard/lnkSucursalPiloto'))
WebUI.click(findTestObject('02-Dashboard/05-SucursalPiloto/lnkD2-Posteo'))
WebUI.click(findTestObject('02-Dashboard/05-SucursalPiloto/D2 - Posteos/lnkPosteo'))
WebUI.click(findTestObject('02-Dashboard/05-SucursalPiloto/D2 - Posteos/Posteo/lnkPagoEnEfectivo'))

//se cargan datos de posteo
WebUI.switchToWindowTitle('Movimiento de Fondos')
WebUI.waitForElementVisible(findTestObject('37-Posteo/Movimiento de Fondos/btnDesplegarConcepto'), 3)
WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/btnDesplegarConcepto'))
WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/lblConcepto1'))
WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/txtNombrePosteo'))
WebUI.setText(findTestObject('37-Posteo/Movimiento de Fondos/txtNombrePosteo'), 'PRUEBAS CRECER')
WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/txtImportePago'))
WebUI.setText(findTestObject('37-Posteo/Movimiento de Fondos/txtImportePago'), '1000')
WebUI.setText(findTestObject('37-Posteo/Movimiento de Fondos/txtReferPosteo'), 'PRUEBAS CRECER')
WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/btnAceptarRegistro'))
WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/lnkAceptarAlertas'))

//Definir Objeto
Transaccion1 = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion1.split('\\s+')
def trx1 = partes[2]
GlobalVariable.vTxn = trx1
assert Transaccion1.contains('Txn Completa:')

//Se verifica la txn y se saca captura del comprobante
Completa = WebUI.getText(findTestObject('37-Posteo/Movimiento de Fondos/lblTxnCompleta'))
assert Completa.contains('Txn Completa:') == true
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
//WebUI.switchToWindowTitle('e-forms')


//----------------------------------------------------------------------------
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}
