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

WebUI.click(findTestObject('02-Dashboard/lnkInventarioPermanente'))

WebUI.click(findTestObject('02-Dashboard/25-Inventario Permanente/spanAltaDePartidas'))

//Click en alta de partidas - Caja
WebUI.click(findTestObject('Object Repository/02-Dashboard/25-Inventario Permanente/Alta de Partidas/lnkAltaPartidas-Caja'))

WebUI.switchToWindowTitle('BCCL.IP.PARTIDAS')

//Verifica lbl caja
WebUI.verifyElementVisible(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/lblCaja'))

//Ingresa codigo IP
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/txtCodigoIP'), cod)

//btn Validar
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/btnValidarRegistro'))

//Ingresa id persona
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/txtPersona'), '2000514092')

//Ingresa cuenta socio
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/txtCuenta'), cuenta)

//btn Validar
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/btnValidarRegistro'))

//Ingresa monto
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/txtMonto'), '100')

//acepta registro
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/btnAceptarRegistro'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/lblTxnCompleta'))
assert element.contains('Txn Completa:')

WebUI.closeWindowTitle('BCCL.IP.PARTIDAS')

WebUI.switchToWindowIndex(0)
WebUI.maximizeWindow()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.IP.PARTIDAS.FIL.ALTAS')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('BCCL.E.IP.PARTIDAS.FIL.ALTAS')

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

//-------------------------------------------------------------------------------------------------------------
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

