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
//TEST NAME: Impuestos.Ajustes monetarios. Alta de Nota de Débito Ajuste. Cuenta en pesos. Persistencia

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Accedo al menu de Ajustes Monetarios - Alta de Nota de Debito por Ajuste
def menuDesplegable = ["Ajustes Monetarios"]
def link = "Nota de Debito por Ajustes"

//Si el menu que busco está en dashboard uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Switch a la ventana de Nota de Debito por Ajustes
WebUI.switchToWindowTitle('Movimiento de Fondos')
WebUI.maximizeWindow()
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/09-Nota de Debito por Ajustes/txtNroCuenta'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/09-Nota de Debito por Ajustes/txtNroCuenta'), '00730029258')
WebUI.click(findTestObject('Object Repository/23-Impuestos/09-Nota de Debito por Ajustes/txtImporte'))
WebUI.setText(findTestObject('Object Repository/23-Impuestos/09-Nota de Debito por Ajustes/txtImporte'), '100')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/09-Nota de Debito por Ajustes/txtConcepto'), '18201AMI')
WebUI.click(findTestObject('Object Repository/23-Impuestos/09-Nota de Debito por Ajustes/txtImporte'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Aceptar registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Acepto las Alertas y completo la transaccion
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'), 6)
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'), 6)
def txn = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert txn.contains('Txn Completa:')

// Imprimir el numero de operacion en consola
println("El ID de la txt es: " + txn)
 
//Dividir la oración en palabras individuales utilizando el espacio como separador
String[] palabras = txn.split(" ");
 
// Obtener la tercera palabra (índice 2 ya que los índices comienzan en 0 en arrays)
String terceraPalabra = palabras[2];
 
// Imprimir la tercera palabra seleccionada
println("La tercera palabra es: " + terceraPalabra);

//Ejecuta en la linea de comando ENQ BCCL.E.EB.CONS.REVE
WebUI.switchToWindowIndex(0)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.EB.CONS.REVE')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

///Abre la pestaña BCCL.E.EB.CONS.REVE
WebUI.switchToWindowTitle('BCCL.E.EB.CONS.REVE')
WebUI.maximizeWindow()

//Seteo de datos "Usuario", "Nro. Contrato"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Usuario', 'B.0043')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro. Contrato', terceraPalabra)

//Selecciona botón Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Espera y verifica que se muestre al menos 1 dato de la tx buscada
WebUI.waitForElementVisible(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/lblIdOperacion'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/lblIdOperacion'))
def element4 = WebUI.getText(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/lblIdOperacion'))
assert element4.contains('Id Operacion')

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Selecciona boton Reversar
WebUI.click(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/btnReversar'))

//Reversa el registro
WebUI.click(findTestObject('Object Repository/55-Reversos/TELLER/lnkReversarRegistro'))

//Acepto alertas
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'),6)
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Espera y recibe mensaje de tx completa reversada
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def element5 = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert element5.contains('Txn Completa:')

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}