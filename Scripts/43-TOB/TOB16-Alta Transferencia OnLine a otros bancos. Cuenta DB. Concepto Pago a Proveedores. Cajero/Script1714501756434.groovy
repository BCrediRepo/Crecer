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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 65), findTestData('MainData/Users').getValue(2, 65))
WebUI.maximizeWindow()

//Seleccionar "Transferencia a Cuentas de Otros Bancos SNP"
WebUI.click(findTestObject('02-Dashboard/43-TOB/lnkTransferenciasACuentasdeOtrosBancosSNP'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Transf. a Ctas Otros Bancos Diferida
WebUI.click(findTestObject('02-Dashboard/43-TOB/Transferencias a Cuentas de Otros Bancos SNP/lnkTransferenciasOtrosBancosDiferida'))

//Cambiar ventana "Movimiento de Fondos"
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Setear "Concepto"
WebUI.setText(findTestObject('44-TOB/Movimiento de Fondos/txtConcepto'), '1')

//Maximizar Ventana
WebUI.maximizeWindow()

//Setear "ID Ordenante"
WebUI.setText(findTestObject('44-TOB/Movimiento de Fondos/txtIDOrdenante'), '1000873562')

//Seleccionar "boton Drill down Numero de cuenta de debito"
WebUI.click(findTestObject('44-TOB/Movimiento de Fondos/btnDrillDownCuentaDebito'))

//Seleccionar "Nro. de cuenta de debito"
WebUI.click(findTestObject('44-TOB/Movimiento de Fondos/lblCuentaARS'))

//Setear "Importe"
WebUI.setText(findTestObject('44-TOB/Movimiento de Fondos/txtImporte'), '200')

//Setear "Observaciones"
WebUI.setText(findTestObject('Object Repository/44-TOB/Movimiento de Fondos/txtObservaciones'), 'PRUEBAS CRECER')

//Setear "CBU Cuenta a acreditar"
WebUI.setText(findTestObject('44-TOB/Movimiento de Fondos/txtCBUaAcreditar'), '0720120820000000332936')

//Setear "Tipo de CUI"
WebUI.setText(findTestObject('Object Repository/44-TOB/Movimiento de Fondos/txtTipoCUI'), 'T')

//Setear "Numero de CUI"
WebUI.setText(findTestObject('44-TOB/Movimiento de Fondos/txtNroCUIBenef'), '30537620893')

//Seleccionar "txt Nombre Beneficiario"
WebUI.click(findTestObject('44-TOB/Movimiento de Fondos/txtNombreBenef'))

//Setear "Nombre del Beneficiario"
WebUI.setText(findTestObject('44-TOB/Movimiento de Fondos/txtNombreBenef'), 'EVASIO MARMETTO S A')

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('44-TOB/Movimiento de Fondos/btnAceptarRegistroRecarga'))

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('44-TOB/Movimiento de Fondos/lnkAceptarAlertaRecarga'))

//Cambiar Ventana "Verificacion de Firmas - Fil.007 Liniers"
WebUI.switchToWindowTitle('Verificacion de Firmas - Fil.007 Liniers')

//Seleccionar opcion "Forzar" en el Combo Box
WebUI.selectOptionByIndex(findTestObject('44-TOB/Verificacion de Firmas/cbxAccion'), 2)

//Seleccionar "boton Aceptar Registro" 
WebUI.click(findTestObject('44-TOB/Verificacion de Firmas/btnAceptar'))

//Verificar Finalizada
WebUI.verifyElementVisible(findTestObject('44-TOB/Verificacion de Firmas/lblFinalizada'))

//Obtener texto de Finalizada
Finalizada = WebUI.getText(findTestObject('44-TOB/Verificacion de Firmas/lblFinalizada'))

//Verificar Autorizada
WebUI.verifyElementVisible(findTestObject('44-TOB/Verificacion de Firmas/lblAutorizada'))

//Obtener Texto Autorizada
Autorizada = WebUI.getText(findTestObject('44-TOB/Verificacion de Firmas/lblAutorizada'))

//Validar Finalizada
assert Finalizada == 'FINALIZADA'

//Validar Autorizada
assert Autorizada == 'AUTORIZADA'

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Cambiar Ventana "Movimiento de Fondos"
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Verificar Txn Completa
WebUI.verifyElementVisible(findTestObject('44-TOB/Movimiento de Fondos/lblTxnCompleta'))

//Obtener texto Txn Completa
Completada = WebUI.getText(findTestObject('44-TOB/Movimiento de Fondos/lblTxnCompleta'))

//Validar Txn Completa
assert Completada.contains('Txn Completa:')

//Control Fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}