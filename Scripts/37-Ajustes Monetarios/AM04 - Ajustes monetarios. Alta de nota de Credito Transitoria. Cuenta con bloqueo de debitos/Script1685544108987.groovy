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

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 4), findTestData('MainData/Users').getValue(
		2, 4))
WebUI.maximizeWindow()

//Toma Screen
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en Ajustes Monetarios
WebUI.click(findTestObject('02-Dashboard/lnkAjustesMonetarios'))

//Click en "Nota de Credito Transitoria"
WebUI.click(findTestObject('02-Dashboard/36-Ajustes Monetarios/lnkNotaCreditoTransitoria'))

//Cambiamos a la ventana "Movimiento de Fondos"
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Esperamos a que el imputo "Nro de Cuenta" sea visible
WebUI.waitForElementVisible(findTestObject('38-Ajustes Monetarios/04 - Nota de Credito Transitoria/txtNroCuenta'), 6)

//Seteamos el texto "02180086531"
WebUI.setText(findTestObject('38-Ajustes Monetarios/04 - Nota de Credito Transitoria/txtNroCuenta'), '02180086531')

//Clickeamos en el fondo de la aplicacion para que se apliquen los cambios
WebUI.click(findTestObject('38-Ajustes Monetarios/04 - Nota de Credito Transitoria/divFondo'))

//Seteamos 100 en el imput de "Importe"
WebUI.setText(findTestObject('38-Ajustes Monetarios/04 - Nota de Credito Transitoria/txtImporte'), '100')

//Volvemos a clickear en el fondo de la aplicacion para que se apliquen los cambios.
WebUI.click(findTestObject('38-Ajustes Monetarios/04 - Nota de Credito Transitoria/divFondo'))

//Click en "Lista de Registros" (CONCEPTO)
WebUI.click(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/btnListaRegistros'))

//Click en "NotaDebAjs" (CONCEPTO)
WebUI.click(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblNotaDebAjs'))

//Click en "Aceptar Registro"
WebUI.click(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/btnAceptarRegistro'))

//Verfify
WebUI.verifyElementText(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblTransaccionRequiereAutorizacion'), 'Transaccion requiere autorizacion por monto. Nivel NIV1')

WebUI.click(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lnkAceptarAlertas'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblTxnCompleta'), 6)

WebUI.verifyElementVisible(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblTxnCompleta'))

def element = WebUI.getText(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblTxnCompleta'))

assert element.contains('Txn Completa:')
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


