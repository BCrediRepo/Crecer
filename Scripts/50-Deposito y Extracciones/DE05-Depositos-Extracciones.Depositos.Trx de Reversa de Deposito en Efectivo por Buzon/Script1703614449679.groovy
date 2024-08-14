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

//Ir a Deposito, Deposito en Buzon a toda hora
def menuDesplegable = ["Depositos"]
def link = "Deposito en Buzon a Toda Hora"

CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Abre la pestaña TELLER
WebUI.switchToWindowTitle('TELLER')

//Verifica si estamos en la ventana Deposito en Buzon a toda hora
WebUI.waitForElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito De Efectivo En Buzon A Toda Hora/TELLER/lblTituloDepositoDeEfectivoEnBuzonATodaHora'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito De Efectivo En Buzon A Toda Hora/TELLER/lblTituloDepositoDeEfectivoEnBuzonATodaHora'))

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresa cuenta en ARS
WebUI.waitForElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito De Efectivo En Buzon A Toda Hora/TELLER/txtNrodeCuenta'), 6)
WebUI.setText(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito De Efectivo En Buzon A Toda Hora/TELLER/txtNrodeCuenta'),'00890080461')

//Click en boton validar
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))

//Ingresa monto
WebUI.waitForElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito De Efectivo En Buzon A Toda Hora/TELLER/txtMontoARS'), 6)
WebUI.setText(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito De Efectivo En Buzon A Toda Hora/TELLER/txtMontoARS'),'100')

//Selecciona orden de tercero "NO"
WebUI.selectOptionByIndex(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito De Efectivo En Buzon A Toda Hora/TELLER/cbOrdenDeTercero'), 1)

//Click en boton validar
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))

//Click boton aceptar
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Acepto alertas
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'),6)
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def txn = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert txn.contains('Txn Completa:')

//Ejecuta en la linea de comando TELLER,DEPOSITO.EFECTIVO.BUZON
WebUI.switchToWindowIndex(0)
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("TELLER,DEPOSITO.EFECTIVO.BUZON", 3)

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Abre la pestaña TELLER
WebUI.switchToWindowTitle('TELLER')

// Imprimir el numero de operacion en consola
println("El ID de la txt es: " + txn)

//Dividir la oración en palabras individuales utilizando el espacio como separador
String[] palabras = txn.split(" ");

// Obtener la tercera palabra (índice 2 ya que los índices comienzan en 0 en arrays)
String terceraPalabra = palabras[2];

// Imprimir la tercera palabra seleccionada
println("La tercera palabra es: " + terceraPalabra);

//Ingresa el numero de operacion obtenido
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'),6)
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'),terceraPalabra)

//Selecciona botón herramienta (reversar un registro segun estado)
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnHerramienta'))

//Reversa el registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnReversar'))

//Acepta alertas
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Espera y recibe mensaje de tx completa y reversada
assert txn.contains('Txn Completa:')

//----------------------------------------------Control de fin de script----------------------------------------------//
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}
