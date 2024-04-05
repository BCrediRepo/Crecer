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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,2), findTestData('MainData/Users').getValue(2,2))
WebUI.maximizeWindow()

//Ir a Pases
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkPases'))

//Ir a Solicitud de Pase Entre Cajas 
WebUI.click(findTestObject('Object Repository/02-Dashboard/55-Pases Entre Cajas/lnkSolicituddePaseEntreCajas'))

//Abre la pestaña TELLER
WebUI.switchToWindowTitle('TELLER')

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verifica titulo Pase Entre Cajas - Cajero
WebUI.verifyElementVisible(findTestObject('Object Repository/57-Pases Entre Cajas/lblTituloPaseEntre Cajas-Cajero'))

//Ingresa Numero de Caja
WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/txtDeLaCaja'), '1542')

//Ingresa Monto
WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/txtMontoARS'), '1000')

//Valida un registro
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/btnValidarRegistro'))

//Ingresa comentarios
WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/txtComentarios'), 'Prueba crecer')

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Acepta un registro
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/btnAceptarRegistro'))

//Aceptar Alertas
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/btnAceptarAlertas'))

//Espera y recibe mensaje de tx completa
//WebUI.waitForElementVisible(findTestObject('Object Repository/57-Pases Entre Cajas/lblTxnCompleta'),6)
//WebUI.verifyElementVisible(findTestObject('Object Repository/57-Pases Entre Cajas/lblTxnCompleta'))
//def element = WebUI.getText(findTestObject('Object Repository/57-Pases Entre Cajas/lblTxnCompleta'))
//assert element.contains('Txn Completa:')

//Definir Objeto
Transaccion1 = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion1.split('\\s+')
def trx1 = partes[2]
GlobalVariable.vTxn = trx1
assert Transaccion1.contains('Txn Completa:')

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
