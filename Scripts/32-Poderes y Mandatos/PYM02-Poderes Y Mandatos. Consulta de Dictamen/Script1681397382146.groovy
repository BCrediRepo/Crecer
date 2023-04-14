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

// Ingreso al menu ?29

WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?29')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/02-Dashboard/31-Poderes y Mandatos/spanDictamenes'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/31-Poderes y Mandatos/lnkConsulta de Dictamen'))

WebUI.switchToWindowTitle('BCCL.POD.MOD.TEMPLATE')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/33-Poderes y Mandatos/BCCL.POD.MOD.TEMPLATE/txtCodigo De Relacion'), '1004564423')

WebUI.click(findTestObject('Object Repository/33-Poderes y Mandatos/BCCL.POD.MOD.TEMPLATE/btnValidarRegistro'))

WebUI.waitForElementPresent(findTestObject('Object Repository/33-Poderes y Mandatos/BCCL.POD.MOD.TEMPLATE/btnImprimirPantalla'), 6)

WebUI.click(findTestObject('Object Repository/33-Poderes y Mandatos/BCCL.POD.MOD.TEMPLATE/btnImprimirPantalla'))

//WebUI.waitForElementPresent(findTestObject('Object Repository/33-Poderes y Mandatos/BCCL.POD.MOD.TEMPLATE/lblASOC SIND PROF S E ECHEVER'), 6)

WebUI.closeBrowser()

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

