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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 62), findTestData('MainData/Users').getValue(2, 62))
WebUI.maximizeWindow()

//Ingresar "ENQ BCCL.E.MONEX.OPER.IN" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.MONEX.OPER.IN')

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Ingreso de parametros generales de la operacion de Moneda Extranjera"
WebUI.switchToWindowIndex(1)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Pantalla
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Moneda Extranjera', 'USD')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Tipo Operacion', 'Socio Compra')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Tipo Cotizacion', 'V')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Ingreso Fondos', 'CUENTA')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Egreso Fondos', 'CUENTA')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Seleccionar "Ingresar operacion"
WebUI.click(findTestObject('Object Repository/15-MONEX/Ingreso de parametros generales de la operacion de Moneda Extranjera/lnkIngresaroperacion'))

//Seleccionar "boton Concepto"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/btnConcepto'))

//Seleccionar "ConceptoA09 - Billetes - Cheques Viajero en poder de residentes"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/lblA09ViajeropoderResidentes'))

//Setear "Importe en Mon. Ext."
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtImporteenMon.Ext'), '1')

//Seleccionar "ID Ordenante"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDOrdenante'))

//Setear "ID Ordenante"
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDOrdenante'), '1000559765')

//Setear "ID Cuenta Debito"
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDCuentaDebito'), '10010083574')

//Seleccionar "ID Cuenta Credito"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDCuentaCredito'))

//Setear "ID Cuenta Credito"
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDCuentaCredito'), '20010083574')

//Seleccionar "Observaciones"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtObservaciones'))

//Setear "Observaciones"
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtObservaciones'), 'PRUEBAS CRECER')

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/lnkAceptarAlertas'))

//Cambiar ventana "Verificacion de Firmas - Fil.119 Monserrat"
WebUI.switchToWindowIndex(2)

//Seleccionar "FORZAR" en el combo box
WebUI.selectOptionByIndex(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/cbxAccion'), 2)

//Maximizar Pantalla
WebUI.maximizeWindow()

//Seleccionar "Aceptar el registro"
WebUI.click(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/btnAceptar'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lnkAceptarAlertas'))

//Verificar "FINALIZADA"
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblFinalizada'))

//Validar "Finalizada"
finalizada = WebUI.getText(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblFinalizada'))
assert finalizada == "FINALIZADA"

//Verificar "ALERTAS PENDIENTE DE AUTORIZACION"
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblAlertasPendientesdeAutorizacion'))

//Validar "ALERTAS PENDIENTE DE AUTORIZACION"
alertas = WebUI.getText(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblAlertasPendientesdeAutorizacion'))
assert alertas == "ALERTAS PENDIENTE DE AUTORIZACION"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}