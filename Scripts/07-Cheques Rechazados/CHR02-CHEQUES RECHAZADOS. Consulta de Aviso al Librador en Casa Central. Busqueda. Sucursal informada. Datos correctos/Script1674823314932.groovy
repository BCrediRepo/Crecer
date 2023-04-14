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


WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?302')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('02-Dashboard/lnkRechazoDeCheques'))

WebUI.click(findTestObject('02-Dashboard/06-Cheques rechazados/lnkConsultas'))

WebUI.click(findTestObject('02-Dashboard/06-Cheques rechazados/1-Consultas - Temenos T24/lnkConsultaDeAvisosAlLibrador'))

WebUI.switchToWindowTitle('BCCL.E.CQ.CHRECH.AVI.LIBRADOR')

WebUI.setText(findTestObject('08-Cheques Rechazados/BCCL.E.CQ.CHRECH.AVI.LIBRADOR/txtSucursal-value111'), '001')

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.E.CQ.CHRECH.AVI.LIBRADOR/lnkEjecutar'))

WebUI.delay(60)

WebUI.verifyTextPresent('Sucursal', true)

NumSuc = WebUI.getText(findTestObject('08-Cheques Rechazados/BCCL.E.CQ.CHRECH.AVI.LIBRADOR/lblNumSucursal'))

assert NumSuc == '001'

WebUI.maximizeWindow()

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

