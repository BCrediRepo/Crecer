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

//Generacion de posteo a traves de caso PST04
WebUI.callTestCase(findTestCase('36-Posteo/PST04-Generar Posteos para liquidar - Pago en efectivo'), [:], FailureHandling.STOP_ON_FAILURE)

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 45), findTestData('MainData/Users').getValue(
        2, 45))

WebUI.maximizeWindow()

//apertura de app y limpieza de filtro
WebUI.click(findTestObject('02-Dashboard/35-Posteos/lnkReversos'))

WebUI.click(findTestObject('02-Dashboard/35-Posteos/Reversos/lnkReversodeOperaciones'))

WebUI.switchToWindowTitle('BCCL.E.EB.CONS.REVE')

CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

WebUI.switchToWindowIndex(0)

WebUI.click(findTestObject('02-Dashboard/35-Posteos/Reversos/lnkReversodeOperaciones'))

//Seteo de datos y busqueda de posteo a reversar
WebUI.switchToWindowTitle('BCCL.E.EB.CONS.REVE')

WebUI.setText(findTestObject('37-Posteo/BCCL.E.EB.CONS.REVE/txtUsuario'), 'B.0419')

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Reverso de posteo y verificacion de TXN completa
WebUI.click(findTestObject('37-Posteo/BCCL.E.EB.CONS.REVE/lnkReversar'))

WebUI.switchToWindowTitle('TELLER')

WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/btnReversar'))

//Seleccionar "Aceptar Alertas
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lnkAceptarAlertas'))

WebUI.verifyElementVisible(findTestObject('37-Posteo/Movimiento de Fondos/lblTxnCompleta'))

label = WebUI.getText(findTestObject('37-Posteo/Movimiento de Fondos/lblTxnCompleta'))

assert label.contains('Txn Completa:') == true


//---------------------------------------------------------------------
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

