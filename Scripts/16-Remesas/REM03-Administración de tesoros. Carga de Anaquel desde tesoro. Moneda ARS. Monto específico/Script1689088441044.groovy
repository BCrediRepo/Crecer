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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 21), findTestData('MainData/Users').getValue(
        2, 21))

WebUI.maximizeWindow()

//Busqueda de aplicacion en menu 323
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?323')
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))
WebUI.switchToWindowTitle('Temenos T24')
WebUI.click(findTestObject('17-Remesas/Temenos T24 - menu 323/lnkRemesas'))
WebUI.click(findTestObject('17-Remesas/Temenos T24 - menu 323/lnkAdministracinDeTesoros'))
WebUI.click(findTestObject('17-Remesas/Temenos T24 - menu 323/lnkCargadeAnaquel'))
WebUI.switchToWindowTitle('BCCL.ANAQUEL')

//Seteo y carga de monto
WebUI.verifyElementVisible(findTestObject('17-Remesas/BCCL.ANAQUEL/txtMonto'))
WebUI.setText(findTestObject('17-Remesas/BCCL.ANAQUEL/txtMonto'), '1500')
WebUI.click(findTestObject('17-Remesas/BCCL.ANAQUEL/btnAceptarRegistro'))

//Verificación final
WebUI.verifyElementVisible(findTestObject('17-Remesas/BCCL.ANAQUEL/lblTxnCompleta'))
label = WebUI.getText(findTestObject('17-Remesas/BCCL.ANAQUEL/lblTxnCompleta'))
assert label.contains("Txn Completa: 099.ANAQUEL") == true

//control fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}


