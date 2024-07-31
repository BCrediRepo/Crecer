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

WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/Baja Logica PF - Temenos T24/lnkModificacion y Baja de Firmantes'))

// Cambia a la ventana Firmas
WebUI.switchToWindowTitle('Firmas')

//Maximizar
WebUI.maximizeWindow()

//Seteo de datos "Nro. Cuenta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro. Cuenta', '13220017001') //10430033951
//CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro. Cuenta', '21190118359')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar ejecutar
WebUI.click(findTestObject('Object Repository/39-Cuentas/Firmas - Baja log firmante/lnkEjecutar'))

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/39-Cuentas/Firmas - Baja log firmante/lnkBaja de Firmas'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.switchToWindowTitle('CUENTAS')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.setText(findTestObject('Object Repository/39-Cuentas/CUENTAS - Baja Logica Firmante/txtFecha Baja.2'), '20230901') //20230828

WebUI.setText(findTestObject('Object Repository/39-Cuentas/CUENTAS - Baja Logica Firmante/txtForma de Operar.1'), '01')

WebUI.setText(findTestObject('Object Repository/39-Cuentas/CUENTAS - Baja Logica Firmante/txtMotivo de Baja.2'), '1')

WebUI.setText(findTestObject('Object Repository/39-Cuentas/CUENTAS - Baja Logica Firmante/txtForma de Operar'), '01')

WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS - Baja Logica Firmante/btnValidarRegistro'))

WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS - Baja Logica Firmante/btnAceptarRegistro'))

WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/CUENTAS - Baja Logica Firmante/lblTRANSACCION FINALIZADA'), 6)

WebUI.closeBrowser()

//Volvemos a logear para la verificar la baja

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,8), findTestData('MainData/Users').getValue(2,8))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verificacion
//WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ACCOUNT S [21190118359]')
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ACCOUNT S [13220017001]') //10430033951
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.switchToWindowTitle('CUENTAS')

// Maximizamos
WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mantenimiento/txtACCOUNT'), '13220017001') //10430033951

WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mantenimiento/btnModificarRegistro'))

WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/CUENTAS - Verf. Baj Log Firm/lblL Ac Fe Baja.2'), 6)

WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/CUENTAS - Verf. Baj Log Firm/lblL Co Cas Baja.2'), 6)

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


