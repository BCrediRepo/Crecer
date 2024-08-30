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
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 6), findTestData('MainData/Users').getValue(2, 6))
WebUI.maximizeWindow()

def menuDesplegable = ["Dispositivos", "Registro de Fallas en Dispositivos"]
def link = "Alta de Faltantes Puntos Neutrales"

//Ejecutar en la linea de comando "?327"
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("?327", 1)

//Cambiar ventana "Temenos T24"
WebUI.switchToWindowIndex(1)

//Maximizar pantalla
WebUI.maximizeWindow()

//Navegar por el menu Temenos T24
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Cambiar ventana "TELLER"
WebUI.switchToWindowIndex(2)

//Esperar elemento "txtMontoMN"
WebUI.waitForElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoMN'), 3)

//Seleccionar "Monto MN"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoMN'))

//Setear "Monto MN"
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoMN'), '100')

//Maximizar ventana
WebUI.maximizeWindow()

//Seleccionar "Comentarios"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'))

//Setear "Comentarios"
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'), 'PRUEBAS CRECER')

//Seleccionar "RETIRO"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/rbtnRETIRO'))

//Esperar "boton Drop down ID Dispositivo"
WebUI.waitForElementVisible(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/btnDropdownIdDispositivo'), 3)

//Seleccionar "boton Drop down ID Dispositivo"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/btnDropdownIdDispositivo'))

//Seleccionar "70151"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/lblidDispositivo70151'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Denominaciones CR"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lblDenominaciones'))

//Setear 1 en la posicion de CantidadCien
WebUI.setText(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/txtCantidadCien'), '1')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lnkAceptarAlertas'))

//Definir Objeto
Transaccion1 = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion1.split('\\s+')
def trx1 = partes[2]
assert Transaccion1.contains('Txn Completa:')

//Setear en "Faltantes en Dispositivos Pn"
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), trx1)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Ver un Registro"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/btnVerRegistro'))

//Verificar "100,00"
WebUI.verifyElementVisible(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/lblMonto'))

//Validar "100,00"
def element = WebUI.getText(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/lblMonto'))
assert element.contains('100,00')

//Verificar "RETIRO"
WebUI.verifyElementVisible(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/lblRETIRO'))

//Validar "RETIRO"
def element2 = WebUI.getText(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/lblRETIRO'))
assert element2.contains('RETIRO')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}