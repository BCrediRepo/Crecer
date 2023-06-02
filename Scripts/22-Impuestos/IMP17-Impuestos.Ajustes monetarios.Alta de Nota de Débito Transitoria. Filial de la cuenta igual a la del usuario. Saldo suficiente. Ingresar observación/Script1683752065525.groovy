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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter
//Necesario para transformacion de string a double
import java.text.DecimalFormat as DecimalFormat

//TEST NAME: Impuestos.Ajustes monetarios. Alta de Nota de Débito Ajuste. Cuenta en pesos. Persistencia
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(
        2, 3))

WebUI.maximizeWindow()

WebUI.click(findTestObject('02-Dashboard/lnkCuentas'))

WebUI.click(findTestObject('02-Dashboard/37-Cuentas/lnkConsultasdeCuentas'))

WebUI.click(findTestObject('02-Dashboard/37-Cuentas/lnkConsultadeSaldoalDia'))

WebUI.switchToWindowTitle('Saldos de Cuenta')

WebUI.setText(findTestObject('02-Dashboard/37-Cuentas/04-Consulta de cuentas/01-Consulta de saldos al dia/txtCuenta'), '00730029258')

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

def operacion

//transformacion de un sting a un double
saldoActual = WebUI.getText(findTestObject('02-Dashboard/37-Cuentas/04-Consulta de cuentas/01-Consulta de saldos al dia/lblSaldoActual'))

def formato = new DecimalFormat('#,##0.00', new java.text.DecimalFormatSymbols(Locale.US))

def saldo = formato.parse(saldoActual).doubleValue()

if (saldo <= 0) {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

WebUI.closeWindowTitle('Saldos de Cuenta')

WebUI.switchToWindowIndex(0)

WebUI.click(findTestObject('02-Dashboard/lnkAjustesMonetarios'))

WebUI.click(findTestObject('02-Dashboard/21-Impuestos/05-Ajustes Monetarios/lnkNotadeDebitoTransitoria'))

WebUI.switchToWindowTitle('Movimiento de Fondos')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('23-Impuestos/11-Nota Debito Transitoria/Movimiento de Fondos/txtNroCuenta'), '00730029258')

WebUI.click(findTestObject('23-Impuestos/11-Nota Debito Transitoria/Movimiento de Fondos/txtImporte'))

WebUI.waitForElementVisible(findTestObject('23-Impuestos/11-Nota Debito Transitoria/Movimiento de Fondos/txtImporte'), 3)

WebUI.setText(findTestObject('23-Impuestos/11-Nota Debito Transitoria/Movimiento de Fondos/txtImporte'), '100')

WebUI.setText(findTestObject('23-Impuestos/11-Nota Debito Transitoria/Movimiento de Fondos/txtConcepto'), '18299NTI')

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('23-Impuestos/11-Nota Debito Transitoria/Movimiento de Fondos/btnValidarRegistro'))

WebUI.click(findTestObject('23-Impuestos/11-Nota Debito Transitoria/Movimiento de Fondos/btnAceptarRegistro'))

WebUI.waitForElementVisible(findTestObject('23-Impuestos/11-Nota Debito Transitoria/Movimiento de Fondos/lnkAceptarAlertas'),
	3)

WebUI.click(findTestObject('23-Impuestos/11-Nota Debito Transitoria/Movimiento de Fondos/lnkAceptarAlertas'))

label = WebUI.getText(findTestObject('23-Impuestos/11-Nota Debito Transitoria/Movimiento de Fondos/lblTXNcompleta'))

assert label.contains("Txn Completa: FT")


//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}
