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

//Ingresar "ENQ BCCL.E.MONEX.OPER.IN" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.MONEX.OPER.IN')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Ingreso de parametros generales de la operacion de Moneda Extranjera"
WebUI.switchToWindowTitle('Ingreso de parametros generales de la operacion de Moneda Extranjera')

//Setear Moneda Extranjera
WebUI.setText(findTestObject('Object Repository/15-MONEX/Ingreso de parametros generales de la operacion de Moneda Extranjera/txtMonedaExtranjera'), 'USD')

//Maximizar Ventana
WebUI.maximizeWindow()

//Setear Tipo Operacion
WebUI.setText(findTestObject('Object Repository/15-MONEX/Ingreso de parametros generales de la operacion de Moneda Extranjera/txtTipoOperacion'), 'Socio Vende')

//Setear Tipo Cotizacion
WebUI.setText(findTestObject('Object Repository/15-MONEX/Ingreso de parametros generales de la operacion de Moneda Extranjera/txtTipoCotizacion'), 'V')

//Setear Ingreso Fondos
WebUI.setText(findTestObject('Object Repository/15-MONEX/Ingreso de parametros generales de la operacion de Moneda Extranjera/txtIngresoFondos'), 'EFECTIVO')

//Setear Egreso Fondos
WebUI.setText(findTestObject('Object Repository/15-MONEX/Ingreso de parametros generales de la operacion de Moneda Extranjera/txtEgresoFondos'), 'EFECTIVO')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ingresar operacion"
WebUI.click(findTestObject('Object Repository/15-MONEX/Ingreso de parametros generales de la operacion de Moneda Extranjera/lnkIngresaroperacion'))

//Cambiar ventana "Compra-Venta"
WebUI.switchToWindowTitle('Compra-Venta')

//Setear Importe en Mon. Ext.
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtImporteenMon.Ext'), '100')

//Seleccionar ID Ordenante
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDOrdenante'))

//Setear ID Ordenante
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDOrdenante'), '1000873562')

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

//Verificar "Ingreso Fondos"
WebUI.verifyElementVisible(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/lblIngresoFondos'))

//Validar "Ingreso Fondos"
def element = WebUI.getText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/lblIngresoFondos'))
assert element.contains('EFECTIVO')

//Verificar "Egreso Fondos"
WebUI.verifyElementVisible(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/lblEgresoFondos'))

//Validar "Egreso Fondos"
def element2 = WebUI.getText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/lblEgresoFondos'))
assert element2.contains('EFECTIVO')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}