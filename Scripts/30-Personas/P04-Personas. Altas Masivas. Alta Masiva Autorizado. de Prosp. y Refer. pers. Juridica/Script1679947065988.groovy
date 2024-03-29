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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,10), findTestData('MainData/Users').getValue(2,10))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Ingreso al menu ?5

WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?5')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/02-Dashboard/spanPersonas2'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/29-Personas/spanAltasMasivas'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/29-Personas/Altas Masivas/lnkAltas Masivas-Lotes Pendientes'))

WebUI.switchToWindowTitle('BCCL.E.PER.ALTM.LOTES.NOF.AA')

WebUI.maximizeWindow()

WebUI.waitForElementPresent(findTestObject('Object Repository/31-Personas/BCCL.E.PER.ALTM.LOTES.NOF.AA/cbxAutorizarLote'), 6)

WebUI.selectOptionByIndex(findTestObject('Object Repository/31-Personas/BCCL.E.PER.ALTM.LOTES.NOF.AA/cbxAutorizarLote'), 2)

WebUI.click(findTestObject('Object Repository/31-Personas/BCCL.E.PER.ALTM.LOTES.NOF.AA/imgDrillDrown'))

//WebUI.click(findTestObject('Object Repository/31-Personas/ADMINISTRA ALTAS MASIVAS/imgValidarRegistro'))

WebUI.click(findTestObject('Object Repository/31-Personas/ADMINISTRA ALTAS MASIVAS/btnAceptarR'))


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









