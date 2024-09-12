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
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 17), findTestData('MainData/Users').getValue(
        2, 17))

WebUI.maximizeWindow()

//Ir a Deposito, Deposito en Fondo de Cese laboral 
def menuDesplegable = ["Depositos"]
def link = "Deposito en Fondo de Cese laboral"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowTitle('Teller Financial Services')

//Verifica titulo TELLER.FINANCIAL.SERVICES,CESE.LABORAL
WebUI.waitForElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/lblTituloTELLER.FINANCIAL.SERVICES,CESE.LABORAL'), 
    6)

WebUI.verifyElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/lblTituloTELLER.FINANCIAL.SERVICES,CESE.LABORAL'))

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresa cuenta 
WebUI.waitForElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/txtNroDeCuenta'), 
    6)

WebUI.setText(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/txtNroDeCuenta'), 
    '11138515109')

//Ingresa monto
WebUI.waitForElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/txtImporte'), 
    6)

WebUI.setText(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/txtImporte'), 
    '100')

//Click en boton validar
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))

//Click boton aceptar
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Acepto alertas
//WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'),6)
//WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def txn = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert txn.contains('Txn Completa:')

// Imprimir el numero de operacion en consola
println('El ID de la txt es: ' + txn)

//Dividir la oración en palabras individuales utilizando el espacio como separador
String[] palabras = txn.split(' ')

// Obtener la tercera palabra (índice 2 ya que los índices comienzan en 0 en arrays)
String terceraPalabra = palabras[2]

// Imprimir la tercera palabra seleccionada
println('La tercera palabra es: ' + terceraPalabra)

//Ir a Reversos de operaciones
WebUI.switchToWindowIndex(0)
def menuDesplegable2 = ["Reversos"]
def link2 = "Reverso de Operaciones"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable2, link2)
WebUI.switchToWindowTitle('BCCL.E.EB.CONS.REVE')
WebUI.maximizeWindow()

//Seteo de datos "Usuario", "Nro. Contrato"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Usuario', 'B.2547')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro. Contrato', terceraPalabra)

//Selecciona botón Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Espera y verifica que se muestre al menos 1 dato de la tx buscada
WebUI.waitForElementVisible(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/lblIdOperacion'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/lblIdOperacion'))
def dato = WebUI.getText(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/lblIdOperacion'))
assert dato.contains('Id Operacion')

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Selecciona boton Reversar
WebUI.click(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/btnReversar'))

//Reversa el registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.switchToWindowTitle('Teller Financial Services')

// Imprimir el numero de operacion en consola
println("El ID de la txt es: " + txn)

//Espera y recibe mensaje de tx completa reversada
assert txn.contains('Txn Completa:')

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}
@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}