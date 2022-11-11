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

//Config
def vWindowTitle = 'ENQ '+ findTestData('Modulos/Modulos').getValue(4,2)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(GlobalVariable.vTest10_IP, GlobalVariable.vTest10Name, GlobalVariable.vUser, GlobalVariable.vPass)

WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),vWindowTitle)

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana de Cierre de Cuenta
WebUI.switchToWindowTitle (findTestData('Modulos/Modulos').getValue(4,2))

WebUI.click(findTestObject('Object Repository/04-Cuentas/01-Cierre de Cuentas/txtSucursal'))

WebUI.setText(findTestObject('Object Repository/04-Cuentas/01-Cierre de Cuentas/txtSucursal'),findTestData('MainData/Users').getValue(3,1))

WebUI.click(findTestObject('Object Repository/04-Cuentas/01-Cierre de Cuentas/btnEjecutar'))

WebUI.verifyElementVisible(findTestObject('Object Repository/04-Cuentas/01-Cierre de Cuentas/lblCuentasCandidatasCerradas'))


//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'('Screenshot/Fails/CDC01Error.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}