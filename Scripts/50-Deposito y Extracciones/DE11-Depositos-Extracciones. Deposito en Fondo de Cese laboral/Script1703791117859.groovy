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
import java.text.SimpleDateFormat
import java.util.Date


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,17), findTestData('MainData/Users').getValue(2,17))
WebUI.maximizeWindow()

//Selecciona Deposito
WebUI.click(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/Fil.089 M.del Plata Ctr/btnDepositos'))

//Selecciona Deposito en Fondo de Cese laboral 
WebUI.click(findTestObject('Object Repository/02-Dashboard/49-Depositos/lnkDepositoenFondodeCese laboral'))

//Abre la pestaña TELLER
WebUI.switchToWindowTitle('Teller Financial Services')

//Verifica titulo TELLER.FINANCIAL.SERVICES,CESE.LABORAL
WebUI.waitForElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/lblTituloTELLER.FINANCIAL.SERVICES,CESE.LABORAL'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/lblTituloTELLER.FINANCIAL.SERVICES,CESE.LABORAL'))

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresa cuenta 
WebUI.waitForElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/txtNroDeCuenta'), 6)
WebUI.setText(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/txtNroDeCuenta'),'11138515109')

//Ingresa monto
WebUI.waitForElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/txtImporte'), 6)
WebUI.setText(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/txtImporte'),'100')

//Click en boton validar
WebUI.waitForElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/btnValidarRegistro'),6)
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/btnValidarRegistro'))

//Click boton aceptar
WebUI.waitForElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/btnAceptarRegistro'),6)
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/btnAceptarRegistro'))

//Aceptar alertas
WebUI.waitForElementVisible(findTestObject('Object Repository/55-Reversos/TELLER/btnAceptarAlertas'),6)
WebUI.click(findTestObject('Object Repository/55-Reversos/TELLER/btnAceptarAlertas'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/lblTxnCompleta'))
assert element.contains('Txn Completa:')

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

