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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 14), findTestData('MainData/Users').getValue(
		2, 14))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en Ajustes Monetarios
WebUI.click(findTestObject('02-Dashboard/lnkAjustesMonetarios'))

//Click en Nota Debito Ajustes"
WebUI.click(findTestObject('02-Dashboard/36-Ajustes Monetarios/lnkNotaDebitoAjustes'))

//Cambiamos a la ventana "Movimiento de Fondos"
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Esperamos a que el txt "Nro de Cuenta" sea visible
WebUI.waitForElementVisible(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/txtNroCuenta'), 6)

//Seteamos el texto "02180086531"
WebUI.setText(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/txtNroCuenta'), '02180086531')

//Clickeamos en el fondo de la aplicacion para que se apliquen los cambios
WebUI.click(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/divFondo'))

//Seteamos 100 en el imput de "Importe"
WebUI.setText(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/txtImporte'), '100')

//Volvemos a clickear en el fondo de la aplicacion para que se apliquen los cambios.
WebUI.click(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/divFondo'))

//Click en "Lista de Registros"
WebUI.click(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/btnListaRegistros'))

//Selecciono concepto "NotaDebAjs"
WebUI.click(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblNotaDebAjs'))

//SCRENSHOT
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en "Aceptar Registro"
WebUI.click(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/btnAceptarRegistro'))

//Realizamos el Assert.
WebUI.waitForElementVisible(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblTransaccionRequiereAutorizacion'),
	3)

WebUI.verifyElementText(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblTransaccionRequiereAutorizacion'), 'Transaccion requiere autorizacion por monto. Nivel NIV1')

//Acepto alertas
WebUI.click(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lnkAceptarAlertas'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblTxnCompleta'))
assert element.contains('Txn Completa:')

//---------------------------------------------------------------------------------------------------------------------
//Codigo para sacar el numero de operacion para busqueda posterior

// Imprimir el numero de operacion en consola
println("El ID de la txt es: " + element)
 
//Dividir la oración en palabras individuales utilizando el espacio como separador
String[] palabras = element.split(" ");
 
// Obtener la tercera palabra (índice 2 ya que los índices comienzan en 0 en arrays)
String terceraPalabra = palabras[2];
 
// Imprimir la tercera palabra seleccionada
println("La tercera palabra es: " + terceraPalabra);

//---------------------------------------------------------------------------------------------------------------------

//Ingresa el numero de operacion obtenido
WebUI.switchToWindowIndex(1)
WebUI.setText(findTestObject('Object Repository/38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/txtNotaDeDebitoxAjusteTransactionId'),terceraPalabra)

//Ver registro
WebUI.click(findTestObject('Object Repository/38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/btnVerRegistro'))

//Valido que se muestre al menos 1 dato del registro
WebUI.waitForElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblNroCuenta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblNroCuenta'))
def element2 = WebUI.getText(findTestObject('Object Repository/38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblNroCuenta'))
assert element2.contains('Nro Cuenta')

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