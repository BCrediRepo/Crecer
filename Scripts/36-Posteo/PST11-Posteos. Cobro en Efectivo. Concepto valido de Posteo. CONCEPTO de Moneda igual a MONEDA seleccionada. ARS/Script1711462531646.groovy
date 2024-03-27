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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 14), findTestData('MainData/Users').getValue(2, 14))

//Setear "?1" en el buscador del Dashboard
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?1')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')

//Seleccionar "Sucursal Piloto"
WebUI.click(findTestObject('02-Dashboard/lnkSucursalPiloto'))

//Seleccionar "D2-Posteo"
WebUI.click(findTestObject('02-Dashboard/05-SucursalPiloto/lnkD2-Posteo'))

//Seleccionar "Posteo"
WebUI.click(findTestObject('02-Dashboard/05-SucursalPiloto/D2 - Posteos/lnkPosteo'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Cobro en efectivo"
WebUI.click(findTestObject('02-Dashboard/05-SucursalPiloto/D2 - Posteos/Posteo/lnkCobroEnEfectivo'))

//Cambiar a la ventana "Movimiento de Fondos"
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Esperar elemento "boton Desplegar Concepto"
WebUI.waitForElementVisible(findTestObject('37-Posteo/Movimiento de Fondos/btnDesplegarConcepto'), 3)

//Seleccionar "boton Desplegar Concepto"
WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/btnDesplegarConcepto'))

//Seleccionar Concepto
WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/lblConcepto2'))

//Setear Comentarios
WebUI.setText(findTestObject('37-Posteo/Movimiento de Fondos/txtNombrePosteo'), 'PRUEBAS CRECER')

//Setear Importe
WebUI.setText(findTestObject('37-Posteo/Movimiento de Fondos/txtImporte'), '5')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/lnkAceptarAlertas'))

//Definir objeto
Transaccion1 = WebUI.getText(findTestObject('37-Posteo/Movimiento de Fondos/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar el segundo elemento
def partes = Transaccion1.split('\\s+')
def trx1 = partes[2]

//Guardar objeto como variable global
GlobalVariable.vTxn = trx1

assert Transaccion1.contains('Txn Completa:')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}