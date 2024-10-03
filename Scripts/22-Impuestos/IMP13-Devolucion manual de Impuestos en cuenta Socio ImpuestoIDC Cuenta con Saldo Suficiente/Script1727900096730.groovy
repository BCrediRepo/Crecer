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

//Ejecuta en la linea de comando
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("BCCL.PERSONA", 1)
WebUI.maximizeWindow()

//Ingresamos los datos y validamos "'Per Co Cprov'"
WebUI.setText(findTestObject('Object Repository/23-Impuestos/15-ARCHIVOS PERSONAS/txtBCCL.PERSONA'), '1000740898')
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/15-ARCHIVOS PERSONAS/lblPer Co Cprov'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/23-Impuestos/15-ARCHIVOS PERSONAS/lblPer Co Cprov'))
def element = WebUI.getText(findTestObject('Object Repository/23-Impuestos/15-ARCHIVOS PERSONAS/lblPer Co Cprov'))
assert element.contains('Per Co Cprov')

//Switch a la ventana Principal
WebUI.switchToWindowIndex(0)

//Ejecuta en la linea de comando
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("BCCL.ALICUOTAS", 2)
WebUI.maximizeWindow()

//Ingresamos los datos y validamos "Importe"
WebUI.setText(findTestObject('Object Repository/23-Impuestos/16-BCCL.ALICUOTAS/txtBCCL.ALICUOTAS'), 'DC00P0.20150706')
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/16-BCCL.ALICUOTAS/spanImporte'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/23-Impuestos/16-BCCL.ALICUOTAS/spanImporte'))
def element2 = WebUI.getText(findTestObject('Object Repository/23-Impuestos/16-BCCL.ALICUOTAS/spanImporte'))
assert element2.contains('Importe')

//Switch a la ventana Principal
WebUI.switchToWindowIndex(0)

def menuDesplegable = ["Cuentas", "Consultas de Cuentas"]
def link = "Consulta de Saldo al Dia"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowIndex(3)
WebUI.maximizeWindow()

//Ingresamos la cuenta a consultar y validamos
WebUI.setText(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/txtCuenta'), '00895279312')
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblNro.deCuenta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblNro.deCuenta'))
def element3 = WebUI.getText(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblNro.deCuenta'))
assert element3.contains('Nro. de Cuenta')

//Switch a la ventana Principal
WebUI.switchToWindowIndex(0)

def menuDesplegable2 = ["Impuestos", "Devolucion de Impuestos"]
def link2 = "Devolucion Impuestos en Cuenta (Socio)"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable2, link2)
WebUI.switchToWindowIndex(4)
WebUI.maximizeWindow()

//Seteo de Datos y validacion
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txt ID PersonaSocio'), '1000740898')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtCuentaCredito'), '00895279312')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtProv Jurisdiccion'), '01')
WebUI.click(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/cbxMvtoGravadoOriginal'))
WebUI.selectOptionByIndex(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/cbxMvtoGravadoOriginal'), 1)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtTipo de impuesto'), 'DC')
WebUI.click(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtMonto a Cobrar'))
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtMonto a Cobrar'), '30')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtBase Imponible'), '5000')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtID Alicuota'), 'DC00P0.20150706')

WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Validamos Txn Completa
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def element4 = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert element4.contains('Txn Completa')

// Imprimir el numero de operacion en consola
println("El ID de la txt es: " + element4)
 
//Dividir la oración en palabras individuales utilizando el espacio como separador
String[] palabras = element4.split(" ");
 
// Obtener la tercera palabra (índice 2 ya que los índices comienzan en 0 en arrays)
String terceraPalabra = palabras[2];
 
// Imprimir la tercera palabra seleccionada
println("La tercera palabra es: " + terceraPalabra);

//Ingresa el numero de operacion obtenido
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtFUNDS.TRANSFER,BCCL.IMPTOS.COB.CTA'), terceraPalabra)

//Click en ver un registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

//Switch a la ventana Principal
WebUI.switchToWindowIndex(0)

def link3 = "Consulta de Mov. por Fecha Proceso"
CustomKeywords.'pkgModules.kywBusquedaMenu.clickearEnLinkDashboard'(link3)
WebUI.switchToWindowIndex(5)
WebUI.maximizeWindow()

//Ingresamos la cuenta
WebUI.setText(findTestObject('Object Repository/23-Impuestos/18-Movimientos por Fecha de Cuentas/txtNroDeCuenta'), '00895279312')
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Validacion de Producto
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/18-Movimientos por Fecha de Cuentas/lblProducto'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/23-Impuestos/18-Movimientos por Fecha de Cuentas/lblProducto'))
def element5 = WebUI.getText(findTestObject('Object Repository/23-Impuestos/18-Movimientos por Fecha de Cuentas/lblProducto'))
assert element5.contains('Producto')

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
