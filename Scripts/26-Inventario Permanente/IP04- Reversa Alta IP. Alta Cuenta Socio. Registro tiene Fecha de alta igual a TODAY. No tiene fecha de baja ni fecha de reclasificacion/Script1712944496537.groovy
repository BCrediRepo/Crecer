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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(2, 3))

WebUI.maximizeWindow()

WebUI.click(findTestObject('02-Dashboard/lnkInventarioPermanente'))

WebUI.click(findTestObject('02-Dashboard/25-Inventario Permanente/spanAltaDePartidas'))

WebUI.click(findTestObject('02-Dashboard/25-Inventario Permanente/Alta de Partidas/lnkContraCuentaDelSocio'))

//WebUI.waitForPageLoad(6)

WebUI.switchToWindowTitle('BCCL.IP.PARTIDAS')
//WebUI.switchToWindowIndex(2)

//Maximizamos
WebUI.maximizeWindow()

WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtCodigoIP'), '0144')

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtPersonaID'))

WebUI.waitForElementVisible(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtPersonaID'), 3)

WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtPersonaID'), '1001825391')

//Seleccionar Cuenta Socio
WebUI.click(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtCuentaSocio'))
	
WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtCuentaSocio'), '11270517014')

//Seleccionar Cuenta
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.E.IP.RECLA.PARTIDAS/txtCuenta'))

//Esperar para poder setear en Cuenta
WebUI.waitForElementVisible(findTestObject('Object Repository/27-Inventario Permanente/BCCL.E.IP.RECLA.PARTIDAS/txtCuenta'), 3)

//Setear Cuenta
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.E.IP.RECLA.PARTIDAS/txtCuenta'), '11270517014')

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtFecha'))

WebUI.waitForElementVisible(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtFecha'), 3)

WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtFecha'), GlobalVariable.vFechaCOB)

//Seleccionar Monto
WebUI.click(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtMonto'))

WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtMonto'), '1500')

//Seleccionar Observaciones
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.E.IP.RECLA.PARTIDAS/txtObservaciones'))

//Setear Observaciones
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.E.IP.RECLA.PARTIDAS/txtObservaciones'), 'PRUEBAS CRECER')

//Seleccionar "Datos Adicionales"
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.E.IP.RECLA.PARTIDAS/lblDATOSADICIONALES'))

//Seleccionar "Tarjeta" en el Combo Box
WebUI.selectOptionByIndex(findTestObject('Object Repository/27-Inventario Permanente/BCCL.E.IP.RECLA.PARTIDAS/cbTipoOperatoria'), 3)

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/btnValidarRegistro'))

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/btnAceptarRegistro'))

//Verifica si el elemento está presente
if (WebUI.verifyElementPresent(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'), 5 , FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
}

txn = WebUI.verifyElementVisible(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/lblTxnCompletada'))

assert txn == true

WebUI.closeWindowTitle('BCCL.IP.PARTIDAS')

WebUI.switchToWindowIndex(0)

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.IP.PARTIDAS.REVE')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('BCCL.E.IP.PARTIDAS.REVE')

//Seteo de datos "Codigo IP"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Codigo IP', '0144')

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.E.IP.PARTIDAS.REVE/lnkEjecutar'))

WebUI.maximizeWindow(FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.E.IP.PARTIDAS.REVE/lnkReversarPartida'))

WebUI.switchToWindowTitle('BCCL.IP.PARTIDAS')

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/btnReversar'))

//WebUI.click(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/lnkAceptarAlertas'))

txnReversa = WebUI.verifyElementVisible(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/lblTxnCompletadaReversa'))

assert txnReversa == true //Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}