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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 22), findTestData('MainData/Users').getValue(
        2, 22))

//Ir a transferencias internas, Alta Transferencias Interna Origen Cuenta
def menuDesplegable = ["Transferencias Internas"]
def link = "Alta Transf. Interna Origen Cuenta"

CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Ingresa datos (sucursal, tipo de moneda, id ordenante, importe, cta debito, beneficiario)
WebUI.setText(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtSucursalDestino'), '089')
WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/btnDrillDownMoneda'))
WebUI.setText(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtMoneda'), 'USD')
WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtIDOrdenante'))
WebUI.setText(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtIDOrdenante'), '1000873562')
WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtImporte'))
WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/btnDrillDownCtaDebito'))
WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/lblCtaDebito'))
WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/btnDrillDownMotivo'))
WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/lblVarios'))
WebUI.setText(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtIDBeneficiario'), '1004568475')
WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtImporte'))
WebUI.setText(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtImporte'), '15')

//Acepta registros y alertas
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Captura de Pantalla PDF
WebUI.switchToWindowTitle('e-forms')
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Forzado y verificacion de firma
WebUI.switchToWindowTitle('Verificacion de Firmas - Fil.119 Monserrat')
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

WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
Completada = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))

assert Completada.contains('Txn Completa:')

//----------------------------------------------Control de fin de script----------------------------------------------//

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}