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
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en el menu Cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))

//Click en Consulta de Cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkConsultasdeCuentas'))

//Click en Consulta de cuentas por cuenta
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/04-Consulta de cuentas/lnkConsultaDeCuentasPorCuenta'))

//Swicht a la ventana
WebUI.switchToWindowTitle('Consulta de Cuentas por Cuenta')

//Maximizamos
WebUI.maximizeWindow()

//Click en nueva seleccion
WebUI.click(findTestObject('Object Repository/43-Verificacion de Firmas/01-Consulta de Cuentas por Cuenta/lnkNueva Seleccion'))

//Seteamos el numero de cuenta a consultar
WebUI.setText(findTestObject('Object Repository/43-Verificacion de Firmas/01-Consulta de Cuentas por Cuenta/txtNroCuenta'), '00890010861')

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/43-Verificacion de Firmas/01-Consulta de Cuentas por Cuenta/btnEjecutar'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/43-Verificacion de Firmas/02-BCCL.E.VER.CUENTA.AMBI/lblNO SE ENCONTRARON REGISTROS'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/43-Verificacion de Firmas/02-BCCL.E.VER.CUENTA.AMBI/lblNO SE ENCONTRARON REGISTROS'))

def element = WebUI.getText(findTestObject('Object Repository/43-Verificacion de Firmas/02-BCCL.E.VER.CUENTA.AMBI/lblNO SE ENCONTRARON REGISTROS'))

assert element.contains('NO SE ENCONTRARON REGISTROS')


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





