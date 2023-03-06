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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 4), findTestData('MainData/Users').getValue(
        2, 4))

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?327')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('02-Dashboard/lnkCapitalSocial'))

WebUI.click(findTestObject('02-Dashboard/17-Capital Social/lnkDepositoEnEfectivoCuentaCapSoc'))

WebUI.switchToWindowTitle('TELLER')

WebUI.setText(findTestObject('19-Capital Social/TELLER/txtPersonaID'), '1004568475')

WebUI.setText(findTestObject('19-Capital Social/TELLER/txtImporte'), '100')

WebUI.click(findTestObject('19-Capital Social/TELLER/btnValidarRegistro'))

WebUI.click(findTestObject('19-Capital Social/TELLER/btnAceptarRegistro'))

WebUI.verifyElementVisible(findTestObject('19-Capital Social/TELLER/lblTXcompleta'), FailureHandling.STOP_ON_FAILURE)

WebUI.switchToWindowIndex(3)

WebUI.takeScreenshot('Screenshot/Capital Social/CS02-Depósito. Aporte Voluntario a Capital Social - Pago por Ventanilla.png')

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

