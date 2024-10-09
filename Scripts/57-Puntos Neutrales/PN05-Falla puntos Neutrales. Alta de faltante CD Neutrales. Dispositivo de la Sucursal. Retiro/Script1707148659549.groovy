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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()

//Navegar por el menu del Dashboard
def menuDesplegable = ["Operatoria de Caja- Reemplazo", "Dispositivos", "Registro de Fallas en Dispositivos"]
def link = "Alta de Faltantes de ATM/CD/TAS"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowIndex(1)

//Ingreso de datos y validacion
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/btnRETIRO'), 6)
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/btnRETIRO'))
WebUI.setText(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/txtComentarios'), 'PRUEBAS CRECER')
WebUI.setText(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/txtMonto'), '1000')
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/txtIdDispositivo'))
WebUI.setText(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/txtIdDispositivo'), '08911')
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/lblDenominacionesCR'))
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/lblDenominacionesCR'))
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/txtCantidadDNM1000'), 6)
WebUI.setText(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/txtCantidadDNM1000'), '1')
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/01-AltadeFaltantesDeATMCDTAS/lblFaltantesEnDipositivo'))

WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
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