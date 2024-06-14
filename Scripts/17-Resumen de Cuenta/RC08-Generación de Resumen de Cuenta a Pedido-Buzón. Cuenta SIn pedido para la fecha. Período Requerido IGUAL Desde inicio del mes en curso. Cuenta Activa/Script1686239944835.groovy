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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(2, 3))
WebUI.maximizeWindow()

//Click en cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))

//Click en pedido de resumen de cuenta
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/spanPedido de Resumen de Cuenta'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/02-Pedido de Resumen de Cuenta/lnkPedido de resumen de cuenta'))

//cambiamos a la ventana BCCL.RES.CTA.PEDIDO
WebUI.switchToWindowTitle('BCCL.RES.CTA.PEDIDO')
WebUI.maximizeWindow()

//Ingresamos los datos para el pedido de resumen de cuenta
WebUI.setText(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/txtNrode Cuenta'), '00430014075') //00545293967//10740044371 //10740044371

WebUI.click(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/cbxDesdeInicioDelMesEnCurso'))

WebUI.selectOptionByIndex(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/cbxDesdeInicioDelMesEnCurso'), 2)

WebUI.setText(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/txtCantidad de Copias'), '1')

WebUI.click(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/btnValidarRegistro'))

WebUI.click(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/btnAceptarRegistro'))

//Screenshot y aceptar alertas
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.click(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lnkAceptarAlertas'))

//Verifico que haya finalizado la transaccion y arroje su ID
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lblTxn Completa'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lblTxn Completa'))

def element = WebUI.getText(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lblTxn Completa'))

assert element.contains('Txn Completa:')

//INGRESO EL NUM DE TRX para realizar la reversa 
// Dividir la cadena por espacios en blanco y tomar el segundo elemento
def partes = element.split('\\s+')
def trx1 = partes[2]
WebUI.setText(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/txtSuperiorInputNumCuenta'), trx1)

//Boton Herramienta
WebUI.click(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/btnHerramienta'))
WebUI.waitForElementVisible(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/btnReversar'), 6)
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/btnReversar'))

//ASSERT PARA LA REVERSA DEL PEDIDO DE RESUMEN DE CUENTA
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lblTxn Completa'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lblTxn Completa'))
def element2 = WebUI.getText(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lblTxn Completa'))
assert element2.contains('Txn Completa:')

//VALIDO QUE SE HAYA REVERSADO y valido el campo REVE
// Dividir la cadena por espacios en blanco y tomar el segundo elemento
def partes2 = element2.split('\\s+')
def trx2 = partes[2]

WebUI.setText(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/txtSuperiorInputNumCuenta'), trx2)
WebUI.click(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/btnVerRegistro'))
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/btnAudit'), 6)
WebUI.click(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/btnAudit'))

WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lblREVE'))
def element3 = WebUI.getText(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lblREVE'))
assert element3.contains('REVE')


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