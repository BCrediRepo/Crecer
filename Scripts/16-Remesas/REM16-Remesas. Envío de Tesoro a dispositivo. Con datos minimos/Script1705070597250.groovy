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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 31), findTestData('MainData/Users').getValue(2, 31))
WebUI.maximizeWindow()

//Ingresar "TELLER,ENVIO.PN099.DESDE.TESORO I F3" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'TELLER,ENVIO.PN099.DESDE.TESORO I F3')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "TELLER"
WebUI.switchToWindowTitle('TELLER')

//Esperar "ARS"
WebUI.waitForElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/lblARS'), 3)
	
//Verificar que la moneda sea ARS
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/lblARS'))

//Validar "ARS"
def element = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblARS'))
assert element.contains('ARS')

//Esperar "Monto MN"
WebUI.waitForElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoMN'), 3)

//Setear "Monto MN"
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoMN'), '1000')

//Seleccionar "txtComentarios"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'))

//Setear "Comentarios"
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'), 'PRUEBAS CRECER')

//Seleccionar "boton Dropdown Origen en PN099"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnOrigenPN'))

//Seleccionar "TF"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTF'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Denominaciones CR"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lblDenominaciones'))

//Setear 1 en la posicion de CantidadMil
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtCantidadMil'),'1')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnAceptarRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Validar "Txn Completa"
def element2 = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))
assert element2.contains('Txn Completa')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}