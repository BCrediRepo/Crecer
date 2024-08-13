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
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(2, 1))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando menu ?1
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("?1", 1)

//Se accede al menu Automatizacion de Sucursales
menuDesplegable = ["Sucursal Piloto","D2 - Automatizacion de Sucursales","CONSULTAS OPERATORIAS DE FILIALES","DETALLE DE OPERACIONES"]
link = "AJSUTES DE DENOMINACION"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

WebUI.switchToWindowTitle('TELLER')

//Verifica titulo de ajustes monetarios
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/lbltituloCambioDeDenominacion')) 

//Ingresa un monto en ARS
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/inputMonto'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/inputMonto'),'1000')

//Valida el registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))

//Ingresa una DENOMINACIONES DB 1
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/btnDenominacionesDB'))
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/inputCantidad.1DB'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/inputCantidad.1DB'),'1')

//Ingresa una DENOMINACIONES CR 1
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/btnDenominacionesCR'))
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/input_Cantidad.1CR'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Ajustes De Denominacion/input_Cantidad.1CR'),'1')

//Acepta el registro y alertas
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'),6)
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
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
