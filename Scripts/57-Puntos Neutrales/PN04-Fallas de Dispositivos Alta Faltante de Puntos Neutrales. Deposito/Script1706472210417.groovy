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

//Ejecutar en la linea de comando "?327"
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("?327", 1)

//Navegar por el menu Temenos T24
def menuDesplegable = ["Dispositivos", "Registro de Fallas en Dispositivos"]
def link = "Alta de Faltantes Puntos Neutrales"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)
WebUI.switchToWindowIndex(2)

//Seteo de Datos y validacion
WebUI.waitForElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoMN'), 3)
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoMN'))
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoMN'), '100')
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'))
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'), 'PRUEBAS CRECER')
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/rbtnDeposito'))
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/btnDropdownIdDispositivo'))
//Seleccionar "70151"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/lblidDispositivo70151'))
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lblDenominaciones'))
WebUI.setText(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/txtCantidadCien'), '1')

WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

Transaccion1 = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def partes = Transaccion1.split('\\s+')
def trx1 = partes[2]
assert Transaccion1.contains('Txn Completa:')

//Setear en "Faltantes en Dispositivos Pn"
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), trx1)
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

//Validar "100,00"
WebUI.verifyElementVisible(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/lblMonto'))
def element = WebUI.getText(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/lblMonto'))
assert element.contains('100,00')

//Validar "DEPOSITO"
WebUI.verifyElementVisible(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/lblDEPOSITO'))
def element2 = WebUI.getText(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/lblDEPOSITO'))
assert element2.contains('DEPOSITO')

//----------------------------------------------------------------------------
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}
