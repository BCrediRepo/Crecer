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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
//TEST NAME: Impuestos.Ajustes monetarios.. Alta de Nota de Crédito Ajuste. Fecha valor permitida

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Accedo al menu de Ajustes Monetarios - Alta de Nota de Credito por Ajuste y completo el registro
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lnkAjustesMonetarios'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkAjustesMonetarios'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/21-Impuestos/05-Ajustes Monetarios/lnkNotadeCreditoporAjustes'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/21-Impuestos/05-Ajustes Monetarios/lnkNotadeCreditoporAjustes'))

//Switch a la ventana de Nota de Credito por Ajustes
WebUI.switchToWindowTitle('Movimiento de Fondos')
WebUI.maximizeWindow()

//Completo los campos necesarios para la nota de Credito
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/txtNroCuenta'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/txtNroCuenta'), '00730029258')
WebUI.click(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/txtImporte'))
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/txtImporte'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/txtImporte'), '100')
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/txtConcepto'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/txtConcepto'), '18602AME')
WebUI.click(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/txtImporte'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/btnAceptarRegistro'), 6)
WebUI.click(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/btnAceptarRegistro'))

//Acepto las Alertas y completo la transaccion
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/lnkAceptarAlertas'), 6)
WebUI.click(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/lnkAceptarAlertas'))

WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/lblTxnCompleta'), 6)
def element = WebUI.getText(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/lblTxnCompleta'))
assert element.contains('Txn Completa:')

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
