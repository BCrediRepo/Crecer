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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Ingreso al menu ?302
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?302')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

// Cambia a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

// Maximizamos
WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/02-Temeno T24/spanCuentas'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/02-Temeno T24/spanModificacion de Cuenta'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/02-Temeno T24/lnkModificacion de Subproducto'))

// Cambia a la ventana Modificacion de Subproducto
WebUI.switchToWindowTitle('Modificacion de Subproducto')

// Maximizamos
WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/39-Cuentas2/Modificacion de Subproducto/lnkNueva Seleccion'))

WebUI.setText(findTestObject('Object Repository/39-Cuentas2/Modificacion de Subproducto/txtNroCuenta'), '00430014075')

WebUI.click(findTestObject('Object Repository/39-Cuentas2/Modificacion de Subproducto/lnkEjecutar'))

WebUI.click(findTestObject('Object Repository/39-Cuentas2/Modificacion de Subproducto/lnkModificar Subproducto'))

// Cambia a la ventana CUENTAS
WebUI.switchToWindowTitle('CUENTAS')

// Maximizamos
WebUI.maximizeWindow()

//Ingresamos los datos de la modificacion
WebUI.click(findTestObject('Object Repository/39-Cuentas2/CUENTAS - Mod domicilio/btnListaRegistro'))

WebUI.click(findTestObject('Object Repository/39-Cuentas2/CUENTAS - Mod domicilio/cbx1034'))

WebUI.setText(findTestObject('Object Repository/39-Cuentas2/CUENTAS - Mod domicilio/txtUsoCuenta'), 'COMERCIAL')

WebUI.setText(findTestObject('Object Repository/39-Cuentas2/CUENTAS - Mod domicilio/txtTarifario'), '000')

WebUI.click(findTestObject('Object Repository/39-Cuentas2/CUENTAS - Mod domicilio/btnValidarRegistro'))

WebUI.click(findTestObject('Object Repository/39-Cuentas2/CUENTAS - Mod domicilio/btnAceptarRegistro'))

WebUI.click(findTestObject('Object Repository/39-Cuentas2/CUENTAS - Mod domicilio/lnkAceptar Alertas'))

//Verificamos que la txn se realizo
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas2/CUENTAS - Mod domicilio/lblTRANSACCION FINALIZADA'), 6)

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


