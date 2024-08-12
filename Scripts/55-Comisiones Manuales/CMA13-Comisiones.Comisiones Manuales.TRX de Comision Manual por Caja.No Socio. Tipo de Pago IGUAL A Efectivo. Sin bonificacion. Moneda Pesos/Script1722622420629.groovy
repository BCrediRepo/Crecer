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
import java.awt.Robot
import java.awt.event.KeyEvent

def idOrdenante = '1003699096'
def concepto = '18301CMI'
def observaciones = 'PRUEBA'
def IVA = 'Z'
def cuit = '30708114584'
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,17), findTestData('MainData/Users').getValue(2,17))


//desde el menu principal, accedemos a la aplicacion de cobro de comisiones manuales en efectivo
def menuDesplegable0 = ["Comisiones"]
def link0 = "Cobro de Comisiones Manuales EN EFECTIVO"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable0, link0)
WebUI.switchToWindowTitle('Account Charge Request')
WebUI.maximizeWindow()

//cargamos los datos requeridos para el caso - pago en pesos, en efectivo y sin bonificaciones
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btndropdown'))
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblPesos Argentinos'))
WebUI.selectOptionByIndex(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/cbxEsSocio'), 1)
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtId Ordenante'), idOrdenante)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCUIT'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCUIT'), cuit)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtIVA'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtIVA'), IVA)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'), concepto)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'), observaciones)
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//validamos que la operacion haya finalizado con exito y guardamos el numero de la misma
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def TxnInicial = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))
assert TxnInicial.contains('Txn Completa')
def parts = TxnInicial.tokenize(' ')
def transaccion = parts[2]

//Buscamos la transaccion y validamos que este en efectivo, sin bonificacion y NO SOCIO
WebUI.switchToWindowTitle('Account Charge Request')
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtComisiones Manuales-Caja'), transaccion)
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTipoDePago'))
TipoPago = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTipoDePago'))
assert TipoPago == "EFECTIVO"
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblBonificacion'))
Bonificacion = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblBonificacion'))
assert Bonificacion == "0,00"
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblEsSocio'))
esSocio = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblEsSocio'))
assert esSocio == "NO"


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
