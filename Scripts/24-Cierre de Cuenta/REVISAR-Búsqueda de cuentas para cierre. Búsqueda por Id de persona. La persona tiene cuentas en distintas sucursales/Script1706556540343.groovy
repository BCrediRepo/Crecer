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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 6), findTestData('MainData/Users').getValue(2, 6))
WebUI.maximizeWindow()

//Ingresar "BCCL.AC.CTASACERRAR" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.AC.CTASACERRAR')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "BCCL.AC.CTASACERRAR"
WebUI.switchToWindowTitle('BCCL.AC.CTASACERRAR')

//Setear en "BCCL.AC.CTASACERRAR"
WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/04-BCCL.AC.CTASACERRAR/txtBCCL.AC.CTASACERRAR'), '01000027871')

//Seleccionar "boton Ver Registro"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/04-BCCL.AC.CTASACERRAR/btnVerRegistro'))

//Verificar "INA"
WebUI.verifyElementVisible(findTestObject('Object Repository/25-Cierre de Cuenta/04-BCCL.AC.CTASACERRAR/lblINA'))

//Validar "INA"
def element = WebUI.getText(findTestObject('Object Repository/25-Cierre de Cuenta/04-BCCL.AC.CTASACERRAR/lblINA'))
assert element.contains('INA')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}