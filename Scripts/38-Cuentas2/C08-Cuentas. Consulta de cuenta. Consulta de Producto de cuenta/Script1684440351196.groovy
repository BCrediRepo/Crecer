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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,2), findTestData('MainData/Users').getValue(2,2))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Ingreso al menu ?27
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?27')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

// Cambia a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

// Maximizamos
WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/06- Consulta Producto - Temenos T24/spanCuentas'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/06- Consulta Producto - Temenos T24/spanConsultas de Cuenta'))

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/06- Consulta Producto - Temenos T24/lnkConsultaDeProductos'))

WebUI.switchToWindowTitle('Consulta de Productos de Cuenta')

// Maximizamos
WebUI.maximizeWindow()

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

//Cambia a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/06- Consulta Producto - Temenos T24/lnkConsultaDeProductos'))

//Swicht a la venta Consulta de Productos de Cuenta
WebUI.switchToWindowTitle('Consulta de Productos de Cuenta')

// Maximizamos
WebUI.maximizeWindow()

//Ingresamos los datos para la consulta
WebUI.click(findTestObject('Object Repository/39-Cuentas2/Consulta de Productos de Cuenta/lnkNueva Seleccion'))

WebUI.click(findTestObject('Object Repository/39-Cuentas2/Consulta de Productos de Cuenta/btndropdownTarifario'))

WebUI.click(findTestObject('Object Repository/39-Cuentas2/Consulta de Productos de Cuenta/lbl000'))

WebUI.click(findTestObject('Object Repository/39-Cuentas2/Consulta de Productos de Cuenta/btndropdownMoneda'))

WebUI.click(findTestObject('Object Repository/39-Cuentas2/Consulta de Productos de Cuenta/lblARS'))

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/39-Cuentas2/Consulta de Productos de Cuenta/lnkEjecutar'))

//Validamos que aparezca el txt ProdSubprod
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas2/Consulta de Productos de Cuenta/lblProdSubprod'), 6)

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

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










