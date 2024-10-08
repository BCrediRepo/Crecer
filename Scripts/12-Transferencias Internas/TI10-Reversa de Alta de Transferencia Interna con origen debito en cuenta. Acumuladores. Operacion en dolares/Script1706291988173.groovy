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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
@Grab(module = 'jsoup', version = '1.14.3', group = 'org.jsoup')
import org.jsoup.Jsoup as Jsoup
import org.jsoup.nodes.Document as Document

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(
		2, 3))

//Ir a transferencias internas, alta transferencia interna origen cuenta
def menuDesplegable = ["Transferencias Internas"]
def link = "Alta Transf. Interna Origen Cuenta"

CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowTitle('Movimiento de Fondos')
WebUI.maximizeWindow()

//Ingresa datos (sucursal, id ordenante, importe,cta debito, motivo, id beneficiario)
WebUI.setText(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtSucursalDestino'), '089')
WebUI.setText(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtIDOrdenante'), '1000506210')
WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/Movimiento de Fondos/txtMoneda'))
WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/Movimiento de Fondos/btnDrillDownMoneda'))
WebUI.setText(findTestObject('Object Repository/12-Transferencias Internas/Movimiento de Fondos/txtMoneda'), 'USD')
WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtImporte'))
WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/btnDrillDownCtaDebito'))
WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/lblCtaDebito'))
WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/btnDrillDownMotivo'))
WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/lblVarios'))
WebUI.setText(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtIDBeneficiario'), '1004568475')
WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtImporte'))
WebUI.setText(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtImporte'), '1')

//Acepto registro y alertas
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Forzado y verificacion de firma
WebUI.switchToWindowTitle('Verificacion de Firmas - Fil.089 M.del Plata Ct')
WebUI.maximizeWindow()
WebUI.selectOptionByIndex(findTestObject('44-TOB/Verificacion de Firmas/cbxAccion'), 2)
WebUI.click(findTestObject('44-TOB/Verificacion de Firmas/btnAceptar'))
WebUI.verifyElementVisible(findTestObject('44-TOB/Verificacion de Firmas/lblFinalizada'))

Finalizada = WebUI.getText(findTestObject('44-TOB/Verificacion de Firmas/lblFinalizada'))
WebUI.verifyElementVisible(findTestObject('44-TOB/Verificacion de Firmas/lblAutorizada'))
Autorizada = WebUI.getText(findTestObject('44-TOB/Verificacion de Firmas/lblAutorizada'))
assert Finalizada == 'FINALIZADA'
assert Autorizada == 'AUTORIZADA'
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verificacion de txn finalizada
WebUI.switchToWindowTitle('Movimiento de Fondos')
WebUI.maximizeWindow()
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
Completada = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert Completada.contains('Txn Completa:')

//Forzado y verificacion de firma
WebUI.switchToWindowTitle('Movimiento de Fondos')
WebUI.maximizeWindow()
//VALIDO que la transaccion se haya completado y guardo el FT
Transaccion = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))

// Dividir la cadena por espacios en blanco y tomar el segundo elemento
def partes = Transaccion.split('\\s+')
def trx1 = partes[2]
assert Transaccion.contains('Txn Completa:')

//REVERSAMOS
//Ejecuta en la linea de comando ENQ BCCL.E.EB.CONS.REVE
WebUI.switchToWindowIndex(0)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.EB.CONS.REVE')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

///Abre la pestaña BCCL.E.EB.CONS.REVE
WebUI.switchToWindowTitle('BCCL.E.EB.CONS.REVE')

//Seteo de Datos "Usuario", "Nro. Contrato"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Usuario', 'B.0289')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro. Contrato', trx1)

//Selecciona botón Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Espera y verifica que se muestre al menos 1 dato de la tx buscada
WebUI.waitForElementVisible(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/lblIdOperacion'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/lblIdOperacion'))
def element = WebUI.getText(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/lblIdOperacion'))
assert element.contains('Id Operacion')

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Selecciona boton Reversar
WebUI.click(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/btnReversar'))

//Reversa el registro
WebUI.click(findTestObject('Object Repository/55-Reversos/TELLER/lnkReversarRegistro'))

//Acepto alertas
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'),6)
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Verifica tx completa reversada
assert Completada.contains('Txn Completa:')

//Toma screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingreso numero de trx en "Nota de Debito por Ajuste"
WebUI.setText(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/inputNotadeDebitoporAjuste'), trx1)

//Ver registro 
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

//Toma screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se clickea en btn "Audit"
WebUI.click(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/btnAudit'))

//Toma screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se realiza un assert del estado del registro "REVE"
WebUI.waitForElementVisible(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/lbEstadoRegistro'), 3)
WebUI.verifyElementVisible(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/lbEstadoRegistro'))
def reversa = WebUI.getText(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/lbEstadoRegistro'))
assert reversa.contains('REVE')

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}