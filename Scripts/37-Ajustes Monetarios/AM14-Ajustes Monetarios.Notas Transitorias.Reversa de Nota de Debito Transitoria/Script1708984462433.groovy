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
 
//TEST NAME: Impuestos.Ajustes monetarios. Alta de Nota de Débito Ajuste. Cuenta en pesos. Persistencia
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
 
//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(
        2, 3))
 
WebUI.maximizeWindow()
 
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
 
WebUI.click(findTestObject('02-Dashboard/lnkAjustesMonetarios'))
 
WebUI.click(findTestObject('02-Dashboard/21-Impuestos/05-Ajustes Monetarios/lnkNotadeDebitoTransitoria'))
 
WebUI.switchToWindowTitle('Movimiento de Fondos')
 
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

element = WebUI.getText(findTestObject('23-Impuestos/11-Nota Debito Transitoria/Movimiento de Fondos/lblTXNcompleta'))

assert element.contains("Txn Completa: FT")
 
// Imprimir el numero de operacion en consola
println("El ID de la txt es: " + element)
 
//Dividir la oración en palabras individuales utilizando el espacio como separador
String[] palabras = element.split(" ");
 
// Obtener la tercera palabra (índice 2 ya que los índices comienzan en 0 en arrays)
String terceraPalabra = palabras[2];
 
// Imprimir la tercera palabra seleccionada
println("La tercera palabra es: " + terceraPalabra);

//Ingresa el numero de operacion obtenido
WebUI.setText(findTestObject('Object Repository/38-Ajustes Monetarios/Movimiento de Fondos/txtNotadeCreditoTransitoriaTransactionId'),terceraPalabra)
//
//Selecciona botón herramienta (reversar un registro segun estado
WebUI.click(findTestObject('Object Repository/38-Ajustes Monetarios/Movimiento de Fondos/btnHerramienta'))

//Reversa el registro
WebUI.click(findTestObject('Object Repository/38-Ajustes Monetarios/Movimiento de Fondos/btnReversarRegistro'))

//Acepta alertas
WebUI.click(findTestObject('Object Repository/38-Ajustes Monetarios/Movimiento de Fondos/btnAceptarAlertasReversa'))

//Espera y recibe mensaje de tx completa y reversada
WebUI.waitForElementVisible(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblTxnCompleta'))
def element2 = WebUI.getText(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblTxnCompleta'))
assert element2.contains('Txn Completa:')
 
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