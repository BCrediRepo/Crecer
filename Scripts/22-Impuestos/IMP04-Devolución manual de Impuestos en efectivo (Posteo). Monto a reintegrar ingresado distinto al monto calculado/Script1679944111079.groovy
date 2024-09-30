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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//TEST CASE NAME: IMPUESTOS. Devolución manual de Impuestos en efectivo (Posteo). Monto a reintegrar ingresado distinto al monto calculado
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()

//Navegar por el menu Temenos T24
def menuDesplegable = ["Impuestos", "Devolucion de Impuestos"]
def link = "Devolucion Impuestos en Efectivo (Socio / No"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowIndex(1)
WebUI.maximizeWindow()

WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/04-Devolucion Impuestos en Efectivo/txtCUITCUIL'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/04-Devolucion Impuestos en Efectivo/txtCUITCUIL'), '30708114584')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/04-Devolucion Impuestos en Efectivo/txtNombrePersona'), 'TEST CRECER')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/04-Devolucion Impuestos en Efectivo/txtProv.Jurisdiccion'), '10')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/04-Devolucion Impuestos en Efectivo/txtTipodeImpuesto'), 'SE')
WebUI.click(findTestObject('Object Repository/23-Impuestos/04-Devolucion Impuestos en Efectivo/txtMontoaReintegrar'))
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/04-Devolucion Impuestos en Efectivo/txtMontoaReintegrar'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/04-Devolucion Impuestos en Efectivo/txtMontoaReintegrar'), '2,40')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/04-Devolucion Impuestos en Efectivo/txtBaseImponible'), '1,00')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/04-Devolucion Impuestos en Efectivo/txtId.Alicuota'), 'SE01AA.20150706')
WebUI.selectOptionByIndex(findTestObject('Object Repository/23-Impuestos/04-Devolucion Impuestos en Efectivo/select_OperatoriaOrigen'), 1)
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'), 6)
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
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
