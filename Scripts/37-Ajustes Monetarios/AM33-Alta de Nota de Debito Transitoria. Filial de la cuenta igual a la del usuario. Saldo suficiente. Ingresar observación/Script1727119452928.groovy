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
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter
//Necesario para transformacion de string a double
import java.text.DecimalFormat as DecimalFormat

//TEST NAME:Alta de Nota de Débito Ajuste. Cuenta en pesos. Persistencia
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(2, 3))
WebUI.maximizeWindow()

//Navegar por el menu de Cuentas
def menuDesplegable = ["Cuentas", "Consultas de Cuentas"]
def link = "Consulta de Saldo al Dia"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowIndex(1)

//Seteo de Datos 
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cuenta', '00730029258')
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//transformacion de un sting a un double
def operacion
saldoActual = WebUI.getText(findTestObject('02-Dashboard/37-Cuentas/04-Consulta de cuentas/01-Consulta de saldos al dia/lblSaldoActual'))
def formato = new DecimalFormat('#,##0.00', new java.text.DecimalFormatSymbols(Locale.US))
def saldo = formato.parse(saldoActual).doubleValue()

if (saldo <= 0) {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}
WebUI.switchToWindowIndex(0)

//Navegar por el menu de Ajustes Monetarios
def menuDesplegable2 = ["Ajustes Monetarios"]
def link2 = "Nota de Debito Transitoria"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable2, link2)
WebUI.switchToWindowIndex(2)
WebUI.maximizeWindow()

WebUI.setText(findTestObject('23-Impuestos/11-Nota Debito Transitoria/txtNroCuenta'), '00730029258')
WebUI.click(findTestObject('23-Impuestos/11-Nota Debito Transitoria/txtImporte'))
WebUI.waitForElementVisible(findTestObject('23-Impuestos/11-Nota Debito Transitoria/txtImporte'), 3)
WebUI.setText(findTestObject('23-Impuestos/11-Nota Debito Transitoria/txtImporte'), '100')
WebUI.setText(findTestObject('23-Impuestos/11-Nota Debito Transitoria/txtConcepto'), '18299NTI')
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Acepto las Alertas y completo la transaccion
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'), 6)
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'), 6)
def element = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
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
