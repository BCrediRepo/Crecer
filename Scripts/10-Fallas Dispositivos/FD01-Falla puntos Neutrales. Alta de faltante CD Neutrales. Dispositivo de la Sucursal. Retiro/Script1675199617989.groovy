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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()


//Ingreso al modulo Operatoria de Caja - Reemplazo

WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkOperatoriadeCaja-Reemplazo'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/09-OperatoriaDeCaja-Reemplazo/lnkDispositivos'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/09-OperatoriaDeCaja-Reemplazo/01-Dispositivos/lnkRegistroDeFallasEnDispositivos'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/09-OperatoriaDeCaja-Reemplazo/01-Dispositivos/01-Registro de Fallas en Dispositivos/lnkAltadeFaltantesdeATMCDTAS'))

WebUI.switchToWindowTitle('TELLER')

//Ingreso los datos correspondientes
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/btnRETIRO'), 6)
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/btnRETIRO'))
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/txtComentarios'), 6)
WebUI.setText(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/txtComentarios'), 'PRUEBAS CRECER')

WebUI.waitForElementVisible(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/txtMonto'), 6)

WebUI.setText(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/txtMonto'), '1000')
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/txtIdDispositivo'))
WebUI.setText(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/txtIdDispositivo'), '08911')

WebUI.waitForElementVisible(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/lblDenominacionesCR'), 6)
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/lblDenominacionesCR'))
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/lblDenominacionesCR'))

WebUI.waitForElementVisible(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/txtCantidadDNM1000'), 6)
WebUI.setText(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/txtCantidadDNM1000'), '1')

WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/lblFaltantesEnDipositivo'))
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/btnAceptarRegistro'), 6)
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/btnAceptarRegistro'))
//Acepto las alertas
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/btnAceptarAlertas'), 6)
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/btnAceptarAlertas'))

//Valido que la transaccion haya finalizado con exito
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/lblTxnCompleta'), 6)
def element = WebUI.getText(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/lblTxnCompleta'))
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