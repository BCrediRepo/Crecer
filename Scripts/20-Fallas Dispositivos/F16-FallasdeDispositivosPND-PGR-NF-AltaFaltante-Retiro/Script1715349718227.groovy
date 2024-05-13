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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,33), findTestData('MainData/Users').getValue(2,33))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresar "FUNDS.TRANSFER,FALTANTE.DISPOSITIVO.PGR" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'FUNDS.TRANSFER,FALTANTE.DISPOSITIVO.PGR')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton de buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Movimiento de Fondos"
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Seleccionar "boton Nuevo Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnNuevoRegistro'))

//Seleccionar "boton Dropdown" de Moneda
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/02-Movimiento de Fondos/btnDropdownMoneda'))
	
//Seleccionar Primera Moneda
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/02-Movimiento de Fondos/lblPrimeraMoneda'))

//Seleccionar txt de Monto
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Movimiento de Fondos/txtMonto'))

//Setear Monto
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Movimiento de Fondos/txtMonto'), '100')
	
//Seleccionar txt de Comentarios
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Movimiento de Fondos/txtComentarios'))

//Setear Comentarios
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Movimiento de Fondos/txtComentarios'), 'TEST')

//Seleccionar txt de Sucursal
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/02-Movimiento de Fondos/txtSucursal'))

//Setear Sucursal
WebUI.setText(findTestObject('Object Repository/11-Fallas de Dispositivos/02-Movimiento de Fondos/txtSucursal'), '089')

//Seleccionar "boton Validar Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/04-TELLER ID/btnValidarRegistro'))

//Seleccionar "btnDropdown" de Dispositivo
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/02-Movimiento de Fondos/btnDropdownDispositivo'))

//Seleccionar Primer Dispositivo
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/02-Movimiento de Fondos/lblPrimerDispositivo'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/02-TELLER,REPOSICION.POR.MENOS.PN099/btnAceptarRegistro'))

//Definir Objeto
Transaccion = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion.split('\\s+')
def trx1 = partes[2]
assert Transaccion.contains('Txn Completa:')

//Setear trx1 en Faltantes en Dispositivos MAP 
WebUI.setText(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/txtFaltantesenDispositivosPn'), trx1)

//Seleccionar "boton Ver Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnVerRegistro'))

//Verificar "RETIRO"
WebUI.verifyElementVisible(findTestObject('Object Repository/11-Fallas de Dispositivos/02-Movimiento de Fondos/lblRETIRO'))

//Validar "RETIRO"
def element = WebUI.getText(findTestObject('Object Repository/11-Fallas de Dispositivos/02-Movimiento de Fondos/lblRETIRO'))
assert element.contains('RETIRO')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}