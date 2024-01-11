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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 45), findTestData('MainData/Users').getValue(
		2, 45))

WebUI.maximizeWindow()

//Busqueda de  app
WebUI.click(findTestObject('02-Dashboard/lnkExtracciones'))

WebUI.click(findTestObject('02-Dashboard/47-Extracciones/lnkRetiroEnVentanilla'))

WebUI.switchToWindowTitle('TELLER')

//Seteo de datos
WebUI.setText(findTestObject('49-Extracciones/TELLER/txtNroCuenta'), '13490019758')

WebUI.click(findTestObject('49-Extracciones/TELLER/btnValidarRegistro'))

WebUI.setText(findTestObject('49-Extracciones/TELLER/txtMonto'), '1')

WebUI.click(findTestObject('49-Extracciones/TELLER/btnAceptarRegistro'))

WebUI.click(findTestObject('49-Extracciones/TELLER/lnkAceptarAlertas'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/49-Extracciones/TELLER/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/49-Extracciones/TELLER/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/49-Extracciones/TELLER/lblTxnCompleta'))
assert element.contains('Txn Completa:')

//Forzamos la firma de la tx realizada
WebUI.switchToWindowIndex(2)
WebUI.maximizeWindow()
WebUI.selectOptionByIndex(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/cbxAccion'),2)

//Acepta el registro de la firma
WebUI.click(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/btnAceptar'))

//Espera y recibe Estado FINALIZADA
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblFinalizada'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblFinalizada'))
def element2 = WebUI.getText(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblFinalizada'))
assert element2.contains('FINALIZADA')


//Espera y recibe Estado AUTORIZADA
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblAutorizada'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblAutorizada'))
def element3 = WebUI.getText(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblAutorizada'))
assert element3.contains('AUTORIZADA')

// Imprimir el numero de operacion en consola
println("El ID de la txt es: " + element)

//------------------------------//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

