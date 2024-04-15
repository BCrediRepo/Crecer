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

//Ingresar "BCCL.OP.CAMBIO,FILIAL.CTA.INT" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.OP.CAMBIO,FILIAL.CTA.INT')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Compra-Venta"
WebUI.switchToWindowTitle('Compra-Venta')

//Seleccionar "boton Nuevo Registro"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/btnNuevoRegistro'))

//Setear Importe en Mon. Ext.
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtImporteenMon.Ext'), '5')

//Seleccionar ID Ordenante
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDOrdenante'))

//Setear ID Ordenante
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDOrdenante'), '1000873562')

//Setear Cuenta de Debito
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDCuentaDebito'), '11190118359')

//Seleccionar el campo para setear Observaciones
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtObservaciones'))

//Setear Observaciones
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtObservaciones'), 'PRUEBAS CRECER')

//Seleccionar "CABAL"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/rbEmisoraTarjetaCABAL'))

//Seleccionar "Nro. Cuenta Tarjeta"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtNroCuentaTarjeta'))

//Setear "Nro. Cuenta Tarjeta"
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtNroCuentaTarjeta'), '123456789')
	
//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/lnkAceptarAlertas'))

//Cambiar ventana "Verificacion de Firmas - Fil.221 Flores Norte"
WebUI.switchToWindowTitle('Verificacion de Firmas - Fil.221 Flores Norte')

//Seleccionar "FORZAR" en el combo box
WebUI.selectOptionByIndex(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/cbxAccion'), 2)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

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

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}