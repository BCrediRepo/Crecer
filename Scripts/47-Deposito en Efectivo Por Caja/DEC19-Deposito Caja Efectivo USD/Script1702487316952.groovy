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
import java.text.SimpleDateFormat
import java.util.Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,17), findTestData('MainData/Users').getValue(2,17))
WebUI.maximizeWindow()

//Ir a Deposito, Deposito en Ventanilla
def menuDesplegable = ["Depositos"]
def link = "Deposito en Ventanilla"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Abre la pestaña TELLER
WebUI.switchToWindowTitle('TELLER')

//Verifica titulo Deposito De Efectivo En Ventanilla
WebUI.waitForElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/lblTituloDepositoDeEfectivoEnVentanilla'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/lblTituloDepositoDeEfectivoEnVentanilla'))

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresa cuenta en usd
WebUI.waitForElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtNrodeCuenta'), 6)
WebUI.setText(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtNrodeCuenta'),'22420030656')

//Click en boton validar
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))

//Ingresa monto
WebUI.waitForElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtImporteUSD'), 6)
WebUI.setText(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtImporteUSD'),'1')

//Selecciona orden de tercero "SI"
WebUI.selectOptionByIndex(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/cbNOSI'), 2)

//Ingresa datos del Depositante
//Ingresa Tipo documento
WebUI.setText(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtTipoDocumentoDepositante'),'01')

//Ingresa numero de documento
WebUI.setText(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtNroDocumentoDepositante'),'13823650')

//Ingresa denominación
WebUI.setText(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtDenominacionDepositante'),'prueba')

//Ingresa datos del Ordenante
//Ingresa Tipo documento
WebUI.setText(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtTipoDocumentoOrdenante'),'01')

//Click en boton validar
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))

//Ingresa numero de documento
WebUI.waitForElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtNroDocumentoOrdenante'),6)
WebUI.setText(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtNroDocumentoOrdenante'),'24735031')

//Ingresa denominación
WebUI.waitForElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtDenominacionOrdenante'),6)
WebUI.setText(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtDenominacionOrdenante'),'prueba')

//Click en boton validar
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))

//Click boton aceptar
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Acepto alertas
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'),6)
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def txn = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert txn.contains('Txn Completa:')

//Ingresa a movimiento de fechas por cuentas
WebUI.switchToWindowIndex(0)
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("ENQ BCCL.E.RES.CTA.MOV.FECHA.VALOR", 3)

//Abre la pestaña Movimiento por Fecha de Cuentas
WebUI.switchToWindowTitle('Movimientos de Ctas por Fecha Valor')

//Verifica titulo Deposito De Efectivo En Ventanilla
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Movimientos de Ctas por Fecha Valor/lblTituloMovimientosdeCtasporFechaValor'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/Movimientos de Ctas por Fecha Valor/lblTituloMovimientosdeCtasporFechaValor'))

//Maximiza la pantalla
WebUI.maximizeWindow()

//Seteo de datos "Nro de Cuenta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro de Cuenta', '22420030656')

//Selecciona boton EJECUTAR
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Valida que se muestre la primeran columna
WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/Movimientos de Ctas por Fecha Valor/lblIDTransaccion'))

//Selecciona ver detalle Txn
WebUI.click(findTestObject('Object Repository/39-Cuentas/Movimientos de Ctas por Fecha Valor/btnVerDetalleTxn'))

//Espera y muestra la descripcion de deposito en efectivo
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Consulta Mov. de una TXN/lblDeposito por caja en efectivo'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/Consulta Mov. de una TXN/lblDeposito por caja en efectivo'))
def element2 = WebUI.getText(findTestObject('Object Repository/39-Cuentas/Consulta Mov. de una TXN/lblDeposito por caja en efectivo'))
assert element2.contains('Deposito por caja en efectivo')

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

