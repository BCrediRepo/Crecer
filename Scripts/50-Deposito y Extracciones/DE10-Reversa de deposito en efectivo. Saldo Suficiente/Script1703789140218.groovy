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
import java.text.SimpleDateFormat
import java.util.Date


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,17), findTestData('MainData/Users').getValue(2,17))
WebUI.maximizeWindow()

//Selecciona Deposito
WebUI.click(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/Fil.089 M.del Plata Ctr/btnDepositos'))

//Selecciona Deposito en Ventanilla
WebUI.click(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/Fil.089 M.del Plata Ctr/btnDepositoenVentanilla'))

//Abre la pestaña TELLER
WebUI.switchToWindowTitle('TELLER')

//Verifica titulo Deposito De Efectivo En Ventanilla
WebUI.waitForElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/lblTituloDepositoDeEfectivoEnVentanilla'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/lblTituloDepositoDeEfectivoEnVentanilla'))

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresa cuenta en ARS
WebUI.waitForElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtNrodeCuenta'), 6)
WebUI.setText(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtNrodeCuenta'),'11190215166')

//Click en boton validar
WebUI.waitForElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/btnValidar'),6)
WebUI.click(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/btnValidar'))

//Ingresa monto
WebUI.waitForElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtImporteARS'), 6)
WebUI.setText(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtImporteARS'),'5')

//Selecciona orden de tercero "NO"
WebUI.selectOptionByIndex(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/cbNOSI'), 1)

//Click boton Validar
WebUI.click(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/btnValidar'))

//Click boton aceptar
WebUI.waitForElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/btnAceptarRegistro'),6)
WebUI.click(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/btnAceptarRegistro'))

//Acepto alertas
WebUI.waitForElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/btnAceptarAlertas'),6)
WebUI.click(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/btnAceptarAlertas'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/lblTxnCompleta'))
assert element.contains('Txn Completa:')


//Ejecuta en la linea de comando TELLER,DEPOSITO.EFECTIVO.VENTANILLA
WebUI.switchToWindowIndex(0)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'TELLER,DEPOSITO.EFECTIVO.VENTANILLA')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Abre la pestaña TELLER
WebUI.switchToWindowTitle('TELLER')

// Imprimir el numero de operacion en consola
println("El ID de la txt es: " + element)

//Dividir la oración en palabras individuales utilizando el espacio como separador
String[] palabras = element.split(" ");

// Obtener la tercera palabra (índice 2 ya que los índices comienzan en 0 en arrays)
String terceraPalabra = palabras[2];

// Imprimir la tercera palabra seleccionada
println("La tercera palabra es: " + terceraPalabra);

//Ingresa el numero de operacion obtenido
WebUI.setText(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtDepositoDeEfectivoEnVentanillaTransactionId'),terceraPalabra)

//Selecciona botón herramienta (reversar un registro segun estado
WebUI.click(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/btnHerramienta'))

//Reversa el registro
WebUI.click(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/btnReversarUnRegistro'))

//Acepta alertas
WebUI.click(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/btnAceptarAlertasReversadas'))

//Espera y recibe mensaje de tx completa y reversada
WebUI.waitForElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/lblTxnCompletaReversada'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/lblTxnCompletaReversada'))
def element2 = WebUI.getText(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/lblTxnCompletaReversada'))
assert element2.contains('Txn Completa:')
