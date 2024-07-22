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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(
        2, 1))

//Carga comision inicial
WebUI.click(findTestObject('02-Dashboard/lnkComisionesyBonificaciones'))

WebUI.click(findTestObject('02-Dashboard/04-Comisiones/1-Comisiones y Bonificaciones/lnkComisiones'))

WebUI.click(findTestObject('02-Dashboard/04-Comisiones/lnkCobroComisiones Manuales - Planta'))

WebUI.switchToWindowIndex(1)

WebUI.selectOptionByIndex(findTestObject('56-Comisiones Manuales/Account Charge Request/cbxTipoPago'), 1)

WebUI.click(findTestObject('56-Comisiones Manuales/Account Charge Request/btnValidar Registro'))

WebUI.setText(findTestObject('56-Comisiones Manuales/Account Charge Request/txtCuentaDebito'), '10700010299')

WebUI.click(findTestObject('56-Comisiones Manuales/Account Charge Request/btnValidar Registro'))

WebUI.setText(findTestObject('56-Comisiones Manuales/Account Charge Request/txtFecha'), GlobalVariable.vFechaCOBAmbTES10)

WebUI.click(findTestObject('56-Comisiones Manuales/Account Charge Request/btnValidar Registro'))

WebUI.setText(findTestObject('56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'), '18301CMI')

WebUI.click(findTestObject('56-Comisiones Manuales/Account Charge Request/btnValidar Registro'))

WebUI.setText(findTestObject('56-Comisiones Manuales/Account Charge Request/txtObservaciones'), 'PRUEBASCRECER')

WebUI.click(findTestObject('56-Comisiones Manuales/Account Charge Request/btnAceptar Registro'))

//Se guarda el codigo de operacion, separandolo por medio del metodo "tokenize",
//una vez finalizado, cerramos ambas ventanas y volvemos al index
TxnInicial = WebUI.getText(findTestObject('56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))

def parts = TxnInicial.tokenize(' ')

def transaccion = parts[2]

WebUI.closeWindowIndex(2)

WebUI.closeWindowIndex(1)

WebUI.switchToWindowIndex(0)

//buscamos en la aplicacion de reversos el codigo de operacion almacenado anteriormente
WebUI.click(findTestObject('02-Dashboard/lnkReversos'))

WebUI.click(findTestObject('02-Dashboard/54-Reversos/lnkReversodeOperaciones'))

WebUI.switchToWindowIndex(1)

//Seteo de datos "Usuario" "Nro. Contrato"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro. Contrato', transaccion)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Usuario', 'B.2055')

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
WebUI.waitForElementVisible(findTestObject('55-Reversos/BCCL.E.EB.CONS.REVE/btnReversar'), 10)

WebUI.click(findTestObject('55-Reversos/BCCL.E.EB.CONS.REVE/btnReversar'))

WebUI.click(findTestObject('56-Comisiones Manuales/Account Charge Request/btnReversarRegistro'))

TxnCompleta = WebUI.getText(findTestObject('56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))

assert TxnCompleta.contains('Txn Completa:') 


//validamos que la reversa esté realizada correctameente viendo el estado en los detalles del registro
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtComisiones Manuales-Caja'), transaccion)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnVerRegistro'))
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/spanAudit'))
def Estado = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblRecordStatusPos1'))
assert Estado.contains('REVE')



//Control fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

