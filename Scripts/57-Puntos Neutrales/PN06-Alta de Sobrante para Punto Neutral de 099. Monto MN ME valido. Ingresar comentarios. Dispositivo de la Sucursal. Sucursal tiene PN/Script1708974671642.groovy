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
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.support.ui.Select

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 6), findTestData('MainData/Users').getValue(2, 6))
WebUI.maximizeWindow()

//Ingresar "?327" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?327')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')

//Maximizar pantalla
WebUI.maximizeWindow()

//Seleccionar "Dispositivos"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/02-Temenos T24/lnkDispositivos'))

//Seleccionar "Registro de Fallas en Dispositivos "
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/02-Temenos T24/Dispositivos/lnkRegistrodeFallasenDispositivos'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Alta de Sobrantes Puntos Neutrales"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/02-Temenos T24/Dispositivos/Registro de Fallas en Dispositivos/lnkAltadeSobrantesPuntosNeutrales'))

//Cambiar ventana "TELLER"
WebUI.switchToWindowTitle('TELLER')

//Esperar elemento "Monto MN"
WebUI.waitForElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoMN'), 3)

//Setear "Monto MN"
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoMN'), '100')

//Seleccionar "Comentarios"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'))

//Setear "Comentarios"
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'), 'PRUEBAS CRECER')

//Seleccionar "DEPOSITO"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/rbtnDeposito'))

//Seleccionar "boton Drop down ID Dispositivo"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/btnDropdownIdDispositivo'))

//Seleccionar "70151"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/lblidDispositivo70151'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Denominaciones DB"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lblDenominaciones'))

//Setear 1 en la posicion de CantidadCien
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtCantidadCien'), '1')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lnkAceptarAlertas'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Validar "Txn Completa"
def element = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))
assert element.contains('Txn Completa')

//Definir Objeto
Transaccion1 = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion1.split('\\s+')
def trx1 = partes[2]
GlobalVariable.vTxn = trx1
assert Transaccion1.contains('Txn Completa:')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}