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


def moneda = 'ARS'
def iva = 'F'
def cuit = '27032150220'
def concepto = '18301CMI'

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,17), findTestData('MainData/Users').getValue(2,17))
WebUI.maximizeWindow()

//Desde el menu principal (dashboard) accedemos al menu de Cobro de Comisiones manuales en efectivo
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkComisiones'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/04-Comisiones/lnkCobro de Comisiones Manuales EN EFECTIVO'))
WebUI.switchToWindowTitle('Account Charge Request')
WebUI.maximizeWindow()

//Cargamos los datos informados - No es socio, en pesos, en efectivo y sin bonificacion
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtMoneda'), moneda)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/cbxEsSocio'))
WebUI.selectOptionByIndex(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/cbxEsSocio'), 1) 
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtIVA'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtIVA'), iva)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCUIT'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCUIT'), cuit)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'), concepto)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'), 'TEST')
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnValidar Registro'))
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnAceptar Registro'))
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lnkAceptar Alertas'))

//validamos que la comision haya finalizado con exito y guardamos el numero de operacion
WebUI.waitForElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))
def txn = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))
assert txn.contains('Txn Completa')
String[] palabras = txn.split(" ")
String transaccion = palabras[2]
 
//Validaciones de comision en pesos, en efectivo y sin bonificacion
WebUI.switchToWindowTitle('Account Charge Request')
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtComisiones Manuales-Caja'), transaccion)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnVerRegistro'))
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblMoneda'))
tipoMoneda = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblMoneda'))
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTipoDePago'))
TipoPago = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTipoDePago'))
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblBonificacion'))
Bonificacion = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblBonificacion'))

assert tipoMoneda == moneda
assert TipoPago == "EFECTIVO"
assert Bonificacion == "0,00"


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

