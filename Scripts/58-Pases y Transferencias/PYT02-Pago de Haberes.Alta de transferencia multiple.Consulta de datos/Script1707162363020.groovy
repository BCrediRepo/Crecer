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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 22), findTestData('MainData/Users').getValue(2, 22))
WebUI.maximizeWindow()

//Ir "Pases y Transferencias entre Socios", "Pago de Haberes/Proveedores"
def menuDesplegable = ["Pases y Transferencias entre Socios"]
def link = "Pago de Haberes / Proveedores"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Esperar 50 segundos, la ventana se demora en iniciar, ya fue comentado a funcional y no lo reportaron.
WebUI.delay(50)

//Cambiar ventana "BCCL.AS.TRANSF.MULTIPLE"
WebUI.switchToWindowTitle('BCCL.AS.TRANSF.MULTIPLE')

//Seleccionar "boton Dropdown Tipo de Transaccion"
WebUI.click(findTestObject('Object Repository/59-Pases y Transferencias/BCCL.AS.TRANSF.MULTIPLE/btnDropdownTipodeTransaccion'))

//Seleccionar "Proveedores"
WebUI.click(findTestObject('Object Repository/59-Pases y Transferencias/BCCL.AS.TRANSF.MULTIPLE/lblProveedores'))

//Setear "Cuenta de Debito"
WebUI.setText(findTestObject('Object Repository/59-Pases y Transferencias/BCCL.AS.TRANSF.MULTIPLE/txtCuentadeDebito'), '11190118359')

//Setear "Importe Total"
WebUI.setText(findTestObject('Object Repository/59-Pases y Transferencias/BCCL.AS.TRANSF.MULTIPLE/txtImporteTotal'), '10')

//Setear "Cuenta de Credito"
WebUI.setText(findTestObject('Object Repository/59-Pases y Transferencias/BCCL.AS.TRANSF.MULTIPLE/txtCuentadeCredito'), '00630053337')

//Setear "Importe"
WebUI.setText(findTestObject('Object Repository/59-Pases y Transferencias/BCCL.AS.TRANSF.MULTIPLE/txtImporte'), '10')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click boton aceptar
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Definir Objeto
Transaccion1 = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion1.split('\\s+')
def trx1 = partes[2]
assert Transaccion1.contains('Txn Completa:')

//Para utilizar en el caso PYT05
GlobalVariable.vTxn = trx1

//Setear Transaccion
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), trx1)
	
//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Ver Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

//Definir Objeto
Transaccion2 = WebUI.getText(findTestObject('Object Repository/59-Pases y Transferencias/BCCL.AS.TRANSF.MULTIPLE/lblIdTransaccion'))

//Switch a la ventana del Dashboard
WebUI.switchToWindowIndex(0)

//Ingresar "TT S" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'FT S '+Transaccion2)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Movimiento de Fondos"
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Verificar "Transferencia por Pago de Haberes"
WebUI.verifyElementVisible(findTestObject('Object Repository/59-Pases y Transferencias/Movimiento de Fondos/lblTransferenciaporPagodeHaberes'))

//Validar "Transferencia por Pago de Haberes"
def element = WebUI.getText(findTestObject('Object Repository/59-Pases y Transferencias/Movimiento de Fondos/lblTransferenciaporPagodeHaberes'))
assert element.contains('Transferencia por Pago de Haberes')


//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}