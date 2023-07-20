import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
        2, 9))

WebUI.maximizeWindow()

WebUI.click(findTestObject('02-Dashboard/41-Ingresos Egresos Varios/lnkPosteo'))

WebUI.click(findTestObject('02-Dashboard/41-Ingresos Egresos Varios/Posteo/lnkEgresosVariosDeCaja'))

WebUI.switchToWindowIndex(1)

//WebUI.click(findTestObject('42-Ingresos Egresos Varios/Movimiento de Fondos/btnDropDownMoneda'))

//WebUI.click(findTestObject('42-Ingresos Egresos Varios/Movimiento de Fondos/lblPeso'))

//WebUI.click(findTestObject('42-Ingresos Egresos Varios/Movimiento de Fondos/txtImporte'))

WebUI.setText(findTestObject('42-Ingresos Egresos Varios/Movimiento de Fondos/txtImporte'), '100')

WebUI.setText(findTestObject('42-Ingresos Egresos Varios/Movimiento de Fondos/txtNombrePosteo'), 'CRECER')

WebUI.click(findTestObject('42-Ingresos Egresos Varios/Movimiento de Fondos/btnDropDownConcepto'))

WebUI.click(findTestObject('42-Ingresos Egresos Varios/Movimiento de Fondos/lblEgresoVariosCaja'))

WebUI.click(findTestObject('42-Ingresos Egresos Varios/Movimiento de Fondos/btnAceptarRegistro'))

WebUI.click(findTestObject('42-Ingresos Egresos Varios/Movimiento de Fondos/lnkAceptarAlertas'))

WebUI.verifyElementVisible(findTestObject('42-Ingresos Egresos Varios/Movimiento de Fondos/lblTxnCompleta'))

def label = WebUI.getText(findTestObject('42-Ingresos Egresos Varios/Movimiento de Fondos/lblTxnCompleta'))

def stringCompleto = label

def partes = stringCompleto.split(' ')

def token = partes[2]

WebUI.closeBrowser()

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 7), findTestData('MainData/Users').getValue(
        2, 7))

WebUI.maximizeWindow()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?1')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('02-Dashboard/lnkSucursalPiloto'))

WebUI.click(findTestObject('02-Dashboard/05-SucursalPiloto/lnkD2-IngresosEgresos'))

WebUI.click(findTestObject('02-Dashboard/05-SucursalPiloto/Ingresos Egresos/lnkLiquidarPosteoEnCaja'))

WebUI.switchToWindowIndex(2)

WebUI.verifyElementVisible(findTestObject('42-Ingresos Egresos Varios/BCCL.E.EB.POSTEO.INAU/lblFTLiquidada'))

TxnALiquidar = WebUI.getText(findTestObject('42-Ingresos Egresos Varios/BCCL.E.EB.POSTEO.INAU/lblFTLiquidada'))

//assert TxnALiquidar == token

WebUI.click(findTestObject('42-Ingresos Egresos Varios/BCCL.E.EB.POSTEO.INAU/lnkLiquidar'))

WebUI.delay(3)

WebUI.switchToWindowTitle('Movimiento de Fondos')

WebUI.verifyElementVisible(findTestObject('42-Ingresos Egresos Varios/Movimiento de Fondos/btnAutorizarRegistro'))

WebUI.click(findTestObject('42-Ingresos Egresos Varios/Movimiento de Fondos/btnAutorizarRegistro'))

WebUI.switchToWindowIndex(3)

WebUI.verifyElementVisible(findTestObject('42-Ingresos Egresos Varios/TicketReporte/formTicket'))

WebUI.switchToWindowTitle('Movimiento de Fondos')

WebUI.verifyElementVisible(findTestObject('42-Ingresos Egresos Varios/Movimiento de Fondos/lblTxnCompletaLiquidacion'))

TxnLiquidacion = WebUI.getText(findTestObject('42-Ingresos Egresos Varios/Movimiento de Fondos/lblTxnCompletaLiquidacion'))

assert TxnLiquidacion.contains('Txn Completa:') == true //Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

