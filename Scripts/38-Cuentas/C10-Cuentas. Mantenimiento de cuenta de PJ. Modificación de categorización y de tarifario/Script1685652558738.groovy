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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,8), findTestData('MainData/Users').getValue(2,8))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Ingreso al menu ?302
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?302')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Cambia a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

// Maximizamos
WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/Baja Logica PF - Temenos T24/span_Cuentas'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/Baja Logica PF - Temenos T24/spanModificacion de Cuenta'))

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/07- Mantenimiento PF - Temenos T24/lnkMantenimiento Cuenta Persona Fisica'))

WebUI.switchToWindowTitle('Mantenimiento - Fil.102 Gcia Operativa')

// Maximizamos
WebUI.maximizeWindow()

WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Mantenimiento/lnkBusqueda Persona Fisica'), 6)

WebUI.click(findTestObject('Object Repository/39-Cuentas/Mantenimiento/lnkBusqueda Persona Fisica'))

WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Mantenimiento/lnkNueva Seleccion'), 6)

WebUI.click(findTestObject('Object Repository/39-Cuentas/Mantenimiento/lnkNueva Seleccion'))

WebUI.setText(findTestObject('Object Repository/39-Cuentas/Mantenimiento/txtIDPersona'), '1003731364')

WebUI.click(findTestObject('Object Repository/39-Cuentas/Mantenimiento/lnkEjecutar'))

WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Mantenimiento/lnkExhibir Cuenta'), 6)

WebUI.click(findTestObject('Object Repository/39-Cuentas/Mantenimiento/lnkExhibir Cuenta'))

WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Mantenimiento/lnkModificacionDatosCuenta'), 6)

WebUI.click(findTestObject('Object Repository/39-Cuentas/Mantenimiento/lnkModificacionDatosCuenta'))

WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Mantenimiento/btndropdown'), 6)

WebUI.click(findTestObject('Object Repository/39-Cuentas/Mantenimiento/btndropdown'))

//WebUI.click(findTestObject('Object Repository/39-Cuentas/Mantenimiento/lbl499'))

WebUI.click(findTestObject('Object Repository/39-Cuentas/Mantenimiento/lbl406'))

WebUI.click(findTestObject('Object Repository/39-Cuentas/Mantenimiento/btnValidarRegistro'))

WebUI.click(findTestObject('Object Repository/39-Cuentas/Mantenimiento/btnAceptarRegistro'))

WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Mantenimiento/lblTRANSACCION FINALIZADA'), 6)

WebUI.closeBrowser()

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,8), findTestData('MainData/Users').getValue(2,8))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verificacion
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ACCOUNT S [11150267547]')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.switchToWindowTitle('CUENTAS')

// Maximizamos
WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mantenimiento/txtACCOUNT'), '11150267547')

WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mantenimiento/btnModificarRegistro'))

WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mantenimiento/lbl L Ac Tarifa'), 6)

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
