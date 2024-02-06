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


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 43), findTestData('MainData/Users').getValue(2, 43))

WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Consultamos el Saldo Previo de la cuenta

//Click en Cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))

//Click en Consulta de Cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkConsultasdeCuentas'))

//Click en Consulta de Saldo al dia
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkConsultadeSaldoalDia'))

//Switch a la ventana Saldos de Cuenta
WebUI.switchToWindowTitle('Saldos de Cuenta')

//Maximizamos
WebUI.maximizeWindow()

//Completamos los datos para la consulta
WebUI.setText(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/txtCuenta'), '01020311343')

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblNro.deCuenta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblNro.deCuenta'))

def element = WebUI.getText(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblNro.deCuenta'))

assert element.contains('Nro. de Cuenta')

WebUI.closeBrowser()


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 8), findTestData('MainData/Users').getValue(2, 8))

WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en Transferencia de Fondos - MAP
WebUI.click(findTestObject('Object Repository/02-Dashboard/spanTransferencia de Fondos - MAP'))

//Click en el link de Transferencia de Fondos - MAP
WebUI.click(findTestObject('Object Repository/51-MAP/Transferencia de Fondos - MAP/lnkTransferencia de Fondos - MAP'))

//Switch a la ventana Movimientos de Fondos
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Maximizamos
WebUI.maximizeWindow()

//Click en el dropdown
WebUI.click(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/btnDropdown'))

//Click en la opcion correspondiente
WebUI.click(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/lblMAPMEPEXE-Transferencia MEP Exenta'))

//Completamos el numero de cuenta debito
WebUI.setText(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/txtNro. de Cuenta Debito'), '01020311343')

//Completamos el numero de cuenta credito
WebUI.click(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/txtNro. de Cuenta Credito'))
WebUI.setText(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/txtNro. de Cuenta Credito'), '13490019758')

//Completamos el importe
WebUI.setText(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/txtImporte'), '10')

//Completamos las observaciones
WebUI.setText(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/txtObservaciones'), 'Pruebas123')

//Click en Validar Registro
WebUI.click(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/btnValidarRegistro'))

//Click en Aceptar Registro
WebUI.click(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/btnAceptarRegistro'))

//Click en Aceptar Alertas
WebUI.click(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/lnkAceptar Alertas'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/lblTxn Completa'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/lblTxn Completa'))

//def element = WebUI.getText(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/lblTxn Completa'))

//assert element.contains('Txn Completa')

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

