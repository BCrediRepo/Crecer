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

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se accede a la consulta BCCL.E.NOFILE.DET.OPER.MONEX
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 3)

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.NOFILE.DET.OPER.MONEX')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Det. de Oper de Compra-Venta MONEX')

//WebUI.maximizeWindow()//
WebUI.waitForElementVisible(findTestObject('15-MONEX/01-BCCL.E.NOFILE.DET.OPER.MONEX/lblTitulo'), 3)

WebUI.click(findTestObject('15-MONEX/01-BCCL.E.NOFILE.DET.OPER.MONEX/lnkEjecutar'))

WebUI.waitForElementVisible(findTestObject('15-MONEX/01-BCCL.E.NOFILE.DET.OPER.MONEX/lblFecha'), 3)

Fecha = WebUI.verifyElementVisible(findTestObject('15-MONEX/01-BCCL.E.NOFILE.DET.OPER.MONEX/lblFecha'))

WebUI.maximizeWindow()

WebUI.click(findTestObject('15-MONEX/01-BCCL.E.NOFILE.DET.OPER.MONEX/btnLupa'))

WebUI.waitForElementVisible(findTestObject('15-MONEX/01-BCCL.E.NOFILE.DET.OPER.MONEX/lblFechaProceso'), 3)

WebUI.verifyElementVisible(findTestObject('15-MONEX/01-BCCL.E.NOFILE.DET.OPER.MONEX/lblFechaProceso'))

assert Fecha == true 

//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}
