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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 2), findTestData('MainData/Users').getValue(2, 2))
WebUI.maximizeWindow()

//Ingresar "?1" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?1')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')

//Seleccionar "Sucursal Piloto"
WebUI.click(findTestObject('Object Repository/17-Remesas/Temenos T24/lnkSucursalPiloto'))

//Maximizar pantalla
WebUI.maximizeWindow()

//Seleccionar "D2 - Automatizacion de Sucursales"
WebUI.click(findTestObject('Object Repository/17-Remesas/Temenos T24/Sucursal Piloto/D2-AutomatizaciondeSucursales'))

//Seleccionar "TESORERO GENERAL"
WebUI.click(findTestObject('Object Repository/17-Remesas/Temenos T24/Sucursal Piloto/D2 - Automatizacion de Sucursales/lnkTesoreroGeneral'))

//Seleccionar "ADMINISTRACION DE TESORERO GENERAL"
WebUI.click(findTestObject('Object Repository/17-Remesas/Temenos T24/Sucursal Piloto/D2 - Automatizacion de Sucursales/TESORERO GENERAL/lnkAdministraciondeTesoreroGeneral'))

//Seleccionar "ENVIOS"
WebUI.click(findTestObject('Object Repository/17-Remesas/Temenos T24/Sucursal Piloto/D2 - Automatizacion de Sucursales/TESORERO GENERAL/ADMINISTRACION DE TESORERO GENERAL/lnkEnvios'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "RETIROS EN CD A CONFORMAR"
WebUI.click(findTestObject('Object Repository/17-Remesas/Temenos T24/Sucursal Piloto/D2 - Automatizacion de Sucursales/TESORERO GENERAL/ADMINISTRACION DE TESORERO GENERAL/ENVIOS/lnkRetirosenCDaConformar'))

//Cambiar ventana "TELLER"
WebUI.switchToWindowTitle('TELLER')

//Seleccionar Monto MN
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoMN'))

//Setear Monto MN
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoMN'), '2000')

//Seleccionar Comentarios
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'))

//Setear Comentarios
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'), 'PRUEBAS CRECER')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar Denominaciones DB
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lblDenominaciones'))

//Setear Cantidad de 2000 ARS
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtCantidadDosMilRetiroCD'), '1')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnAceptarRegistro'))

//Definir Objeto
Transaccion1 = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion1.split('\\s+')
def trx1 = partes[2]
assert Transaccion1.contains('Txn Completa:')

//Setear en "Retiro en CD"
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtTELLER'), trx1)

//Seleccionar "boton Ver Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnVerRegistro'))

//Verificar Registro en Retiro en CD
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/lblRetiroenCD'))

//Validar Registro en Retiro en CD
def element = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblRetiroenCD'))
assert element.contains('Retiro en CD')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}