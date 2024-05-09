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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,24), findTestData('MainData/Users').getValue(2, 24))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en transacciones especiales
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkTransacciones Especiales'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en ajustes de denominacion del tesoro
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkAjustesDe Denominacion del Tesoro'))

//Switch a la ventana TELLER
WebUI.switchToWindowTitle('TELLER')

//Maximizamos
WebUI.maximizeWindow()

//Ingresamos el monto
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/txtMontoMN'), '100')

//Ingresamos el comentario
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/txtComentarios'))
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/txtComentarios'), 'Prueba de cambio de denominacion')

//Click en Denominaciones DB
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/spanDenominaciones DB'))

//Ingresamos la cantidad en Denominaciones DB
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/txtDenominacionDB.8'), '1')

//Click en Denominaciones CR
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/spanDenominaciones CR'))

//Ingresamos la cantidad en Denominaciones CR
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/txtDenominacion.8'), '1')

//Click en validar registro
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/btnValidarRegistro'))

//Click en aceptar registro
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/btnAceptarRegistro'))

//Click en aceptar alertas
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/lnkAceptar Alertas'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/lblTxn Completa'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/lblTxn Completa'))

def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/lblTxn Completa'))

assert element.contains('Txn Completa')

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



