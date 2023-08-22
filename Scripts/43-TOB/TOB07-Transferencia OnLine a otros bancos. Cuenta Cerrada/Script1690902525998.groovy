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

//Apertura app
WebUI.click(findTestObject('02-Dashboard/43-TOB/lnkTransferenciasACuentasdeOtrosBancosSNP'))

WebUI.click(findTestObject('02-Dashboard/43-TOB/Transferencias a Cuentas de Otros Bancos SNP/lnkTransferenciasOtrosBancosDiferida'))

WebUI.switchToWindowTitle('Movimiento de Fondos')

//Seteo de datos
WebUI.setText(findTestObject('44-TOB/Movimiento de Fondos/txtConcepto'), '3')

WebUI.setText(findTestObject('44-TOB/Movimiento de Fondos/txtIDOrdenante'), '1000873562')

WebUI.setText(findTestObject('44-TOB/Movimiento de Fondos/txtCuentaDebito'), '15170000212')

WebUI.click(findTestObject('44-TOB/Movimiento de Fondos/txtImporte'))

//WebUI.click(findTestObject('44-TOB/Movimiento de Fondos/btnDrillDownCuentaDebito'))
//WebUI.click(findTestObject('44-TOB/Movimiento de Fondos/lblCuentaARS'))
WebUI.verifyElementVisible(findTestObject('44-TOB/Movimiento de Fondos/lblMonedaUSD'))

WebUI.setText(findTestObject('44-TOB/Movimiento de Fondos/txtImporte'), '150')

WebUI.setText(findTestObject('44-TOB/Movimiento de Fondos/txtCBUaAcreditar'), '0720120820000000332936')

WebUI.click(findTestObject('44-TOB/Movimiento de Fondos/btnDrillDownCUI'))

WebUI.click(findTestObject('44-TOB/Movimiento de Fondos/lblCUIT'))

WebUI.setText(findTestObject('44-TOB/Movimiento de Fondos/txtNroCUIBenef'), '30537620893')

WebUI.click(findTestObject('44-TOB/Movimiento de Fondos/txtNombreBenef'))

WebUI.setText(findTestObject('44-TOB/Movimiento de Fondos/txtNombreBenef'), 'EVASIO MARMETTO S A')

WebUI.click(findTestObject('44-TOB/Movimiento de Fondos/btnAceptarRegistroRecarga'))

//WebUI.click(findTestObject('44-TOB/Movimiento de Fondos/lnkAceptarAlertaRecarga'))

WebUI.verifyElementVisible(findTestObject('44-TOB/Movimiento de Fondos/lblCuentaDebitoE'))

debito = WebUI.getText(findTestObject('44-TOB/Movimiento de Fondos/lblCuentaDebitoE'))

assert debito.contains('Nro. de Cuenta Debito')

WebUI.verifyElementVisible(findTestObject('44-TOB/Movimiento de Fondos/lblNoSeEncuentraACCOUNT'))

cuentant = WebUI.getText(findTestObject('44-TOB/Movimiento de Fondos/lblNoSeEncuentraACCOUNT'))

assert cuentant.contains('No se encuentra archivo ACCOUNT')

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

