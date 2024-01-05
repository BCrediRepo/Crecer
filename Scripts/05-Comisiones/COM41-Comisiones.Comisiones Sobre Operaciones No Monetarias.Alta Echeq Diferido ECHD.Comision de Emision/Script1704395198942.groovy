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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(2, 1))
WebUI.maximizeWindow()

//Ingresar "BCCL.AS.ACCOUNT,CTA.ECHQS.ALTA" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.AS.ACCOUNT,CTA.ECHQS.ALTA')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "BCCL.AS.ACCOUNT"
WebUI.switchToWindowTitle('BCCL.AS.ACCOUNT')

//Setear Valor "1234567890"
WebUI.setText(findTestObject('Object Repository/06-Comisiones/BCCL.AS.ACCOUNT/txtBCCL.AS.ACCOUNT,CTA.ECHQS.ALTA'), '1234567890')

//Seleccionar "boton Modificar Registro"
WebUI.click(findTestObject('Object Repository/06-Comisiones/BCCL.AS.ACCOUNT/btnModificarRegistro'))

//Setear "id Cuenta"
WebUI.setText(findTestObject('Object Repository/06-Comisiones/BCCL.AS.ACCOUNT/txtidCuenta'), '00780012787')

//Seleccionar "boton Dropdown Lista de registros"
WebUI.click(findTestObject('Object Repository/06-Comisiones/BCCL.AS.ACCOUNT/btnDropdownListadeRegistros'))

//Seleccionar "ECHD"
WebUI.click(findTestObject('Object Repository/06-Comisiones/BCCL.AS.ACCOUNT/lblECHD'))

//Seleccionar "boton Aceptar el registro"
WebUI.click(findTestObject('Object Repository/06-Comisiones/BCCL.AS.ACCOUNT/btnAceptarRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/06-Comisiones/BCCL.AS.ACCOUNT/lblTxnCompleta'))

//Validar "Txn Completa"
def element = WebUI.getText(findTestObject('Object Repository/06-Comisiones/BCCL.AS.ACCOUNT/lblTxnCompleta'))
assert element.contains('Txn Completa')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}