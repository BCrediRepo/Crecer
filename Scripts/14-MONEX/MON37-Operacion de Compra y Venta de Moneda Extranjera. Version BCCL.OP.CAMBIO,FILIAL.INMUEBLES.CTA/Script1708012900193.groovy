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

//Ingresar "QUERY BCCL.E.MONEX.OPER.INMUEBLES" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'QUERY BCCL.E.MONEX.OPER.INMUEBLES')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Vnta mon ext compra Inmueble"
WebUI.switchToWindowTitle('Vnta mon ext compra Inmueble')

//Seleccionar "CUENTA"
WebUI.click(findTestObject('Object Repository/15-MONEX/13-Vnta mon ext compra Inmueble/lnkCUENTA'))

//Maximizar Ventana
WebUI.maximizeWindow()

//Cambiar ventana "Compra-Venta"
WebUI.switchToWindowTitle('Compra-Venta')

//Seleccionar rb "V - Vigente"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/rbVigente'))

//Setear Importe en Mon. Ext.
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtImporteenMon.Ext'), '0,01')

//Seleccionar ID Ordenante
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDOrdenante'))

//Setear ID Ordenante
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDOrdenante'), '1000873562')

//Setear Cuenta de Debito
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDCuentaDebito'), '11190118359')

//Seleccionar el campo para setear Cuenta de Credito
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDCuentaCredito'))

//Setear Cuenta de Credito
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDCuentaCredito'), '21190118359')

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

//Cambiar ventana "Verificacion de Firmas - Fil.119 Monserrat"
WebUI.switchToWindowTitle('Verificacion de Firmas - Fil.119 Monserrat')

//Seleccionar "FORZAR" en el combo box
WebUI.selectOptionByIndex(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/cbxAccion'), 2)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar el registro"
WebUI.click(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/btnAceptar'))

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