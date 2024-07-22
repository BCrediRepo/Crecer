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

def cuenta = '11190118359'
def concepto = '18301CMI'
String fecha = GlobalVariable.vFechaCOBAmbTES10


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,17), findTestData('MainData/Users').getValue(2,17))

//desde el menu principal, accedemos a la aplicacion de cobro de comisiones manuales con debito
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkComisiones'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/04-Comisiones/lnkCobro Comisiones Manuales CON DEB EN CTA'))
WebUI.switchToWindowTitle('Account Charge Request')
WebUI.maximizeWindow()

//Cargamos los valores requeridos por el caso - cuenta debito, sin bonificacion
WebUI.selectOptionByIndex(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/cbxTipoPago'), 1)
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCuentaDebito'), cuenta)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'), concepto)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtFecha'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtFecha'), fecha)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'), 'PRUEBA')
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnAceptar Registro'))


//validamos que la operacion haya finalizado con exito y guardamos el numero de la misma 
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))
def TxnInicial = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))
assert TxnInicial.contains('Txn Completa')
def parts = TxnInicial.tokenize(' ')
def transaccion = parts[2]

//validamos que la comision una vez realizada, esté en DEBITO y sin bonificacion
WebUI.switchToWindowTitle('Account Charge Request')
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtComisiones Manuales-Caja'), transaccion)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnVerRegistro'))
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTipoDePago'))
TipoPago = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTipoDePago'))
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblBonificacion'))
Bonificacion = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblBonificacion'))
assert TipoPago == "DEBITO"
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

