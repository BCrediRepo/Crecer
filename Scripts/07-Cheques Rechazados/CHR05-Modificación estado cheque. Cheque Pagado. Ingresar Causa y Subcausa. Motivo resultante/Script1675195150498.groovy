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

/*ANTES DE CORRER ESTE SCRIPT SE DEBERÁ CAMBIAR EL ESTADO DEL CHEQUE A "PAGADO"*/


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,7), findTestData('MainData/Users').getValue(2,7))
WebUI.maximizeWindow()


WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.CQ.CHEQUES')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('BCCL.CQ.CHEQUES')

WebUI.setText(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/txtNumCheque'), '502004')

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/btnEditarRegistro'))

WebUI.selectOptionByIndex(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/cbEstadoCheque'), '9')

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/btnAceptarRegistro'))

WebUI.setText(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/txtNumCheque'), '502004')

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/btnAutorizarRegistro'))

WebUI.maximizeWindow()

WebUI.verifyElementVisible(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/lblUltimasModificaciones'))

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/btnAutorizarCambiosEnElRegistro'))

WebUI.verifyElementVisible(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/lblAutorizadorDistinto'))

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