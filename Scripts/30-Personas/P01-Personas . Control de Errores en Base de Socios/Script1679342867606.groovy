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
//CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Ingreso al menu ?25

WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?25')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('Object Repository/02-Dashboard/spanPersonas2'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/29-Personas/lnkControl de Errores en Base de Socios'))

WebUI.switchToWindowTitle('BCCL.PER.CTRL.ERROR')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/31-Personas/txtTransactionId'), '1000000001.0035')

WebUI.click(findTestObject('Object Repository/31-Personas/imgModificarRegistro'))

WebUI.setText(findTestObject('Object Repository/31-Personas/txtCertCalidad'), '02')

WebUI.setText(findTestObject('Object Repository/31-Personas/txtPendConsegDato'), '02')

WebUI.click(findTestObject('Object Repository/31-Personas/imgModificarRegistro'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/31-Personas/Page_BCCL.PER.CTRL.ERROR/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/31-Personas/Page_BCCL.PER.CTRL.ERROR/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/31-Personas/Page_BCCL.PER.CTRL.ERROR/lblTxnCompleta'))
assert element.contains('Txn Completa:')
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



