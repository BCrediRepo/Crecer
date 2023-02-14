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

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.AC.COM.POR.DIS.SUC')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'), FailureHandling.STOP_ON_FAILURE)

WebUI.switchToWindowTitle('Consulta de Comisiones Cobradas')

WebUI.setText(findTestObject('06-Comisiones/Consulta de Comisiones Cobradas/txtvalue111-FechaProceso'), '20220722')

WebUI.click(findTestObject('06-Comisiones/Consulta de Comisiones Cobradas/lnkEjecutar'))

WebUI.delay(60)

SucOrigenH = WebUI.getText(findTestObject('06-Comisiones/Consulta de Comisiones Cobradas/lblSucOrigenH'))

SucOrigenT = WebUI.getText(findTestObject('06-Comisiones/Consulta de Comisiones Cobradas/lblSucOrigenT'))

SucDestinoH = WebUI.getText(findTestObject('06-Comisiones/Consulta de Comisiones Cobradas/lblSucDestinoH'))

SucDestinoT = WebUI.getText(findTestObject('06-Comisiones/Consulta de Comisiones Cobradas/lblSucDestinoT'))

assert SucOrigenH != null

assert SucOrigenT != null

assert SucDestinoH != null

assert SucDestinoT != null

WebUI.maximizeWindow()

WebUI.takeScreenshot('Screenshot/Comisiones/COM04-COMISIONES. Consulta Distribución porcentaje comisiones Telecoop. Completitud.png')

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'('Screenshot/Fails/Comisiones/Error-COM04-COMISIONES. Consulta Distribución porcentaje comisiones Telecoop. Completitud.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}


