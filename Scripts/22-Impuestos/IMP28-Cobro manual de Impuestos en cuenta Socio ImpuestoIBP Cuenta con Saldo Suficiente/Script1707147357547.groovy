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
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seteamos en el commandline
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ACCOUNT')
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Switch a la ventana CUENTAS
WebUI.switchToWindowTitle('CUENTAS')

//Maximizamos
WebUI.maximizeWindow()

//Ingresamos la cuenta
WebUI.setText(findTestObject('Object Repository/23-Impuestos/14-CUENTAS/txtACCOUNT'), '00895279312')

//Click en ver registro
WebUI.click(findTestObject('Object Repository/23-Impuestos/14-CUENTAS/btnVerRegistro'))

//Switch a la ventana principal
WebUI.switchToWindowIndex(0)

//seteamos en el commandline
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.PERSONA')
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Switch a la ventana ARCHIVOS PERSONAS
WebUI.switchToWindowTitle('ARCHIVOS PERSONAS')

//Maximizamos
WebUI.maximizeWindow()

//Ingresamos los datos
WebUI.setText(findTestObject('Object Repository/23-Impuestos/15-ARCHIVOS PERSONAS/txtBCCL.PERSONA'), '1000740898')

//Click en ver registro
WebUI.click(findTestObject('Object Repository/23-Impuestos/14-CUENTAS/btnVerRegistro'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/15-ARCHIVOS PERSONAS/lblPer Co Cprov'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/23-Impuestos/15-ARCHIVOS PERSONAS/lblPer Co Cprov'))

def element = WebUI.getText(findTestObject('Object Repository/23-Impuestos/15-ARCHIVOS PERSONAS/lblPer Co Cprov'))

assert element.contains('Per Co Cprov')


//Switch a la ventana Principal
WebUI.switchToWindowIndex(0)

//seteamos en el commandline
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.ALICUOTAS')
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Switch a la ventana BCCL.ALICUOTA
WebUI.switchToWindowTitle('BCCL.ALICUOTAS')

//Maximizamos
WebUI.maximizeWindow()

//Ingresamos los datos
WebUI.setText(findTestObject('Object Repository/23-Impuestos/16-BCCL.ALICUOTAS/txtBCCL.ALICUOTAS'), 'BP01A.20150706')

//Click en ver registro
WebUI.click(findTestObject('Object Repository/23-Impuestos/14-CUENTAS/btnVerRegistro'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/16-BCCL.ALICUOTAS/spanImporte'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/23-Impuestos/16-BCCL.ALICUOTAS/spanImporte'))

def element2 = WebUI.getText(findTestObject('Object Repository/23-Impuestos/16-BCCL.ALICUOTAS/spanImporte'))

assert element2.contains('Importe')


//Switch a la ventana Principal
WebUI.switchToWindowIndex(0)

//Click de cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))

//Click en consultas de cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkConsultasdeCuentas'))

//Click en consulta de saldo al dia
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkConsultadeSaldoalDia'))

//Switch a la ventana Saldos de Cuenta
WebUI.switchToWindowTitle('Saldos de Cuenta')

//Maximizamos
WebUI.maximizeWindow()

//Ingresamos la cuenta a consultar
WebUI.setText(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/txtCuenta'), '00895279312')

//Click en ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblNro.deCuenta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblNro.deCuenta'))

def element3 = WebUI.getText(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblNro.deCuenta'))

assert element3.contains('Nro. de Cuenta')

WebUI.closeBrowser()


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en impuestos
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkImpuestos'))

//Click en cobro de impuestos
WebUI.click(findTestObject('Object Repository/02-Dashboard/21-Impuestos/lnkCobrodeImpuestos'))

//Click en cobro de impuestos en cuenta socio
WebUI.click(findTestObject('Object Repository/02-Dashboard/21-Impuestos/02-Cobro de Impuestos/lnkCobroImpuestosenCuenta(Socio)'))

//Switch a la ventana Movimiento de Fondos
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Maximizamos
WebUI.maximizeWindow()

//Ingresamos ID Persona Socio
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txt ID PersonaSocio'), '1000740898')

//Ingresamos cuenta debito
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtCuentaDebito'), '00895279312')

//Ingresamos Prov juridiccion
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtProv Jurisdiccion'), '00')

//Selecionamos el MVTO
WebUI.click(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/cbxMvtoGravadoOriginal'))
WebUI.selectOptionByIndex(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/cbxMvtoGravadoOriginal'), 1)

//Ingresamos el tipo de impuesto
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtTipo de impuesto'), 'BP')

//Ingresamos monto a cobrar
WebUI.click(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtMonto a Cobrar'))
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtMonto a Cobrar'), '150')

//Ingresamos la base imponible
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtBase Imponible'), '5000')

//Ingresamos ID alicuota
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtID Alicuota'), 'BP01A.20150706')

//Seleccionamos la opcion correcta del cbx
WebUI.click(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/cbxOperatoriaOrigen'))
WebUI.selectOptionByIndex(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/cbxOperatoriaOrigen'), 29)

//Click en validar registro
WebUI.click(findTestObject('Object Repository/23-Impuestos/02-Cobro Impuestos en Cuenta (Socio)/btnValidarRegistro'))

//Click en aceptar registro
WebUI.click(findTestObject('Object Repository/23-Impuestos/02-Cobro Impuestos en Cuenta (Socio)/btnAceptarRegistro'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))

def element4 = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))

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
WebUI.click(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/btnVerRegistro'))

//Switch a la ventana Principal
WebUI.switchToWindowIndex(0)

//Click de cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))

//Click en consultas de cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkConsultasdeCuentas'))

//Click en Consulta de Mov por Fecha Proceso
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkConsulta de Mov. por Fecha Proceso'))

//Switch a la ventana Movimientos por Fecha de Cuentas
WebUI.switchToWindowTitle('Movimientos por Fecha de Cuentas')

//Maximizamos
WebUI.maximizeWindow()

//Ingresamos la cuenta
WebUI.setText(findTestObject('Object Repository/23-Impuestos/18-Movimientos por Fecha de Cuentas/txtNroDeCuenta'), '00895279312')

//Click en ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Click en btn ver detalle completo Txn
WebUI.click(findTestObject('Object Repository/23-Impuestos/18-Movimientos por Fecha de Cuentas/btnVerDetalleCompletoTxn'))

//Validacion del Tipo de Impuesto
def tipoImpuesto = WebUI.getText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/lblTipoImp'))
WebUI.println(tipoImpuesto)
WebUI.verifyElementVisible(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/lblTipoImp'))
assert tipoImpuesto.contains('BP')


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


