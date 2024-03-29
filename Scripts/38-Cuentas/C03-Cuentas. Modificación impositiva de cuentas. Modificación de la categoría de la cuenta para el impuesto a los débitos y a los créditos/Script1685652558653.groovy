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

// Ingreso al menu ?6
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?6')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Swicht a Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

//Maximizamos
WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/03- Modificacion Impositiva - Temenos T24/spanCuentas'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/03- Modificacion Impositiva - Temenos T24/lnkModificacion Impositiva de Cuentas'))

//Switch a la ventana CUENTAS
WebUI.switchToWindowTitle('CUENTAS')

//Maximizamos
WebUI.maximizeWindow()

//Ingresamos los datos para la modificacion
WebUI.setText(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mod Imp/txtModificacion de Datos Impositivo'), '00545293967')

WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mod Imp/btnModificarRegistro'))

WebUI.setText(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mod Imp/txtCategoriaCondicion'), '1')

WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mod Imp/btnValidarRegistro'))

WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mod Imp/btnAceptarRregistro'))

//Verificamos que se haya completado la Txn
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/CUENTAS - Mod Imp/lblTxn Completa'), 6)

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





