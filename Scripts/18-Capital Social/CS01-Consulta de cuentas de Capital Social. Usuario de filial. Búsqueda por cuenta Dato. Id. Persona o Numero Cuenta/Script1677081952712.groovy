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

WebUI.maximizeWindow()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?327')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('02-Dashboard/lnkCapitalSocial'))

WebUI.click(findTestObject('02-Dashboard/17-Capital Social/lnkConsultaDeCuentasDeCapitalSocial'))

WebUI.click(findTestObject('02-Dashboard/17-Capital Social/lnkConsultasDeCapitalSocial'))

WebUI.click(findTestObject('02-Dashboard/17-Capital Social/lnkCuentasCapitalSocialPorPersonaOCuenta'))

WebUI.switchToWindowTitle('Consulta Cuenta Capital Social')

WebUI.click(findTestObject('19-Capital Social/Consulta Cuenta Capital Social/lnkNuevaSeleccion'))

WebUI.click(findTestObject('19-Capital Social/Consulta Cuenta Capital Social/lnkEjecutar'))

WebUI.switchToWindowTitle('BCCL.E.CS.VER.CTA')

WebUI.click(findTestObject('19-Capital Social/BCCL.E.CS.VER.CTA/btnLupita'))

WebUI.switchToWindowTitle('Consulta Cuenta Capital Social')

WebUI.setText(findTestObject('19-Capital Social/Consulta Cuenta Capital Social/txtNroCuenta_value411'), '90890008453')

WebUI.click(findTestObject('19-Capital Social/Consulta Cuenta Capital Social/lnkEjecutar'))

WebUI.maximizeWindow()

WebUI.verifyElementVisible(findTestObject('19-Capital Social/Consulta Cuenta Capital Social/lblCuenta'), FailureHandling.STOP_ON_FAILURE)

cuenta = WebUI.getText(findTestObject('19-Capital Social/Consulta Cuenta Capital Social/lblCuenta'))

if (cuenta == '90890008453') {
    WebUI.takeScreenshot('Screenshot/Capital Social/CS01-Capital Social. Consulta de cuentas de Capital Social. Usuario de filial. Búsqueda por cuenta Dato. Id. Persona o Numero Cuenta.png')
} else {
    WebUI.takeScreenshot('Screenshot/Fails/Capital Social/Error - CS01-Capital Social. Consulta de cuentas de Capital Social. Usuario de filial. Búsqueda por cuenta Dato. Id. Persona o Numero Cuenta.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

