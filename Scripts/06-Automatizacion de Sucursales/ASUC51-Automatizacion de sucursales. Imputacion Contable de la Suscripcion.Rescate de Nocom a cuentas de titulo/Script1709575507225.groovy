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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 5), findTestData('MainData/Users').getValue(2, 5))
WebUI.maximizeWindow()

//Ejecutar en la linea de comando "FUNDS.TRANSFER,NOCOM.FILIAL.SUSCRIPCION"
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("FUNDS.TRANSFER,NOCOM.FILIAL.SUSCRIPCION", 1)

//Seleccionar "Nuevo Registro"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/btnNuevoRegistro'))

//Seleccionar "boton Sucursal Destino"
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Movimiento de Fondos/btnDropdownSucursalDestino'), 3)
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Movimiento de Fondos/btnDropdownSucursalDestino'))

//Seleccionar "073 Jujuy"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Movimiento de Fondos/lblJujuy'))

//Setear "Monto"
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Movimiento de Fondos/txtMonto'), 3)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Movimiento de Fondos/txtMonto'), '5')
WebUI.maximizeWindow()

//Setear "Comentarios"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Movimiento de Fondos/txtComentarios'))
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Movimiento de Fondos/txtComentarios'), 'PRUEBAS CRECER')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Definir Objeto
Transaccion1 = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion1.split('\\s+')
def trx1 = partes[2]
assert Transaccion1.contains('Txn Completa:')

//Setear Transaccion
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Movimiento de Fondos/txtImputaciondeSuscripNOCOMSuc-PR'), trx1)

//Seleccionar "boton VerRegistro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

//Validar Sucursal Destino
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Movimiento de Fondos/lblSucursalDestino'))
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Movimiento de Fondos/lblSucursalDestino'))
assert element.contains('115')

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