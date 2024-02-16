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

//Ingresar "BCCL.OP.CAMBIO,CC.OFICIOS" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.OP.CAMBIO,CC.OFICIOS')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Compra-Venta"
WebUI.switchToWindowTitle('Compra-Venta')

//Seleccionar "boton Nuevo Registro"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/btnNuevoRegistro'))

//Seleccionar V - Vigente
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/rbVigente'))

//Seleccionar Socio Vende
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/rbSocioVende'))

//Setear Importe en Mon. Ext.
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtImporteenMon.Ext'), '5')

//Seleccionar ID Ordenante
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDOrdenante'))

//Setear ID Ordenante
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDOrdenante'), '1000873562')

//Seleccionar el campo para setear Observaciones
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtObservaciones'))

//Setear Observaciones
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtObservaciones'), 'PRUEBAS CRECER')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/lnkAceptarAlertas'))

//Definir Objeto
Transaccion = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion.split('\\s+')
def trx1 = partes[2]
assert Transaccion.contains('Txn Completa:')

//Setear Compra-Venta de Moneda Extranjera
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtCompra-VentadeMonedaExtranjera'), trx1)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Ver Registro"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/btnVerRegistro'))

//Verificar "Moneda Extranjera"
WebUI.verifyElementVisible(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/lblMonedaExtranjera'))

//Validar "Moneda Extranjera"
def element = WebUI.getText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/lblMonedaExtranjera'))
assert element.contains('USD')

//Verificar "Tipo Operacion"
WebUI.verifyElementVisible(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/lblTipoOperacion'))

//Validar "Tipo Operacion"
def element2 = WebUI.getText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/lblTipoOperacion'))
assert element2.contains('Socio Vende')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}