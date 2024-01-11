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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,39), findTestData('MainData/Users').getValue(2,39))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Accedo al menu de Legales BCCL.COBRANZAS.LEGALES,INPUT F3
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'BCCL.COBRANZAS.LEGALES,INPUT F3')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana de Legales BCCL.COBRANZAS.LEGALES,INPUT F3 y se completan los campos mandatorios
WebUI.switchToWindowTitle('BCCL.COBRANZAS.LEGALES')
WebUI.waitForElementVisible(findTestObject('Object Repository/28-Cobranza de Legales/01-BCCL.COBRANZAS.LEGALES,INPUT F3/txtCarpeta'), 6)
WebUI.setText(findTestObject('Object Repository/28-Cobranza de Legales/01-BCCL.COBRANZAS.LEGALES,INPUT F3/txtCarpeta'), '93911')
WebUI.setText(findTestObject('Object Repository/28-Cobranza de Legales/01-BCCL.COBRANZAS.LEGALES,INPUT F3/txtAbogado'), '2036')
WebUI.setText(findTestObject('Object Repository/28-Cobranza de Legales/01-BCCL.COBRANZAS.LEGALES,INPUT F3/txtFiador'),'SALAME CORREA ANDREA LORENA')
WebUI.setText(findTestObject('Object Repository/28-Cobranza de Legales/01-BCCL.COBRANZAS.LEGALES,INPUT F3/txtMoneda'), 'ARS')
WebUI.selectOptionByIndex(findTestObject('Object Repository/28-Cobranza de Legales/01-BCCL.COBRANZAS.LEGALES,INPUT F3/selectCONCEPTO'), 4)
WebUI.setText(findTestObject('Object Repository/28-Cobranza de Legales/01-BCCL.COBRANZAS.LEGALES,INPUT F3/txtImporte'), '100')
WebUI.click(findTestObject('Object Repository/28-Cobranza de Legales/01-BCCL.COBRANZAS.LEGALES,INPUT F3/rdbPAGADOPORFiador'))
WebUI.click(findTestObject('Object Repository/28-Cobranza de Legales/01-BCCL.COBRANZAS.LEGALES,INPUT F3/btnValidarRegistro'))
WebUI.click(findTestObject('Object Repository/28-Cobranza de Legales/01-BCCL.COBRANZAS.LEGALES,INPUT F3/btnAceptarRegistro'))

//Verifico la txn Completa
WebUI.maximizeWindow()
WebUI.waitForElementVisible(findTestObject('Object Repository/28-Cobranza de Legales/01-BCCL.COBRANZAS.LEGALES,INPUT F3/lblTxnCompleta'), 6)
def element = WebUI.getText(findTestObject('Object Repository/28-Cobranza de Legales/01-BCCL.COBRANZAS.LEGALES,INPUT F3/lblTxnCompleta'))
assert element.contains('Txn Completa:')


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
