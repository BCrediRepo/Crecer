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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 8), findTestData('MainData/Users').getValue(2, 8))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresamos el menu ?302 en el command line
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?302')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

//Maximizamos
WebUI.maximizeWindow()

//Click en cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/01-Temenos T24/spanCuentas'))

//Click en modificacion de cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/01-Temenos T24/spanModificacion de Cuenta'))

//Click en modificacion de subproducto
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/01-Temenos T24/lnkModificacion de Subproducto'))

//Switch a la ventana Modificacion de Subproducto
WebUI.switchToWindowTitle('Modificacion de Subproducto')

//Maximizamos
WebUI.maximizeWindow()

//Ingresamos la cuenta
WebUI.setText(findTestObject('Object Repository/39-Cuentas/Modificacion de Subproducto/txtNroCuenta'), '00890093805')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/39-Cuentas/Modificacion de Subproducto/lnkEjecutar'))

//Click en modificar subproducto
WebUI.click(findTestObject('Object Repository/39-Cuentas/Modificacion de Subproducto/lnkModificar Subproducto'))

//Switch a la venta CUENTAS
WebUI.switchToWindowTitle('CUENTAS')

//Ingresamos los datos para la modificacion
WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mod Subproducto/btnSubProducto'))

//Ingresamos el subproducto
WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mod Subproducto/spanCuentaCorriente Entidades C Social'))

//Ingresamos Uso cuenta
WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mod Subproducto/btnUsoCuenta'))

WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mod Subproducto/spanCOMERCIAL'))

//Ingresamos Tarifario
WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mod Subproducto/btnTarifario'))

WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mod Subproducto/spanTarifa General'))

//Validamos el registro
WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mod Subproducto/btnValidarRegistro'))

//Aceptamos el registro
WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mod Subproducto/btnAceptarRegistro'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mod Subproducto/lblTRANSACCION FINALIZADA'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mod Subproducto/lblTRANSACCION FINALIZADA'))

def element = WebUI.getText(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mod Subproducto/lblTRANSACCION FINALIZADA'))

assert element.contains('TRANSACCION FINALIZADA')

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


