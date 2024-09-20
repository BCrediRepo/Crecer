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

import java.text.SimpleDateFormat
import java.util.Date

def cuenta ='03195011374'
def cod = '0099'

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(
        2, 3))

WebUI.maximizeWindow()

//Ir a Inventario permanente, alta de partidas, alta partidas-caja
def menuDesplegable = ["Inventario Permanente", "Alta de Partidas"]
def link = "Alta Partidas - Caja"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowTitle('BCCL.IP.PARTIDAS')

//Verifica lbl caja
WebUI.verifyElementVisible(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/lblCaja'))

//Ingresa codigo IP
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/txtCodigoIP'), cod)

//btn Validar
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))

//Ingresa id persona
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/txtPersona'), '2000514092')

//Ingresa cuenta socio
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/txtCuenta'), cuenta)

//btn Validar
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))

//Ingresa monto
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/txtMonto'), '100')

//acepta registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def txn = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert txn.contains('Txn Completa:')

WebUI.closeWindowTitle('BCCL.IP.PARTIDAS')
WebUI.switchToWindowIndex(0)

//Ejecuta en la linea de comando ENQ BCCL.E.IP.PARTIDAS.FIL.ALTAS
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("ENQ BCCL.E.IP.PARTIDAS.FIL.ALTAS", 1)
WebUI.maximizeWindow()

//Seteo de Datos "Id. Persona"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Codigo IP',cod)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro Cuenta',cuenta)

//Boton ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Boton ver partida
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/btnVerDetalleDePartida'))

WebUI.switchToWindowTitle('BCCL.IP.PARTIDAS')

CodigoIp = WebUI.verifyElementVisible(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/lblCodigoIP'))
assert CodigoIp == true

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}
@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}