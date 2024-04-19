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

//Selecciona Deposito en Fondo de Cese laboral 
WebUI.click(findTestObject('Object Repository/02-Dashboard/49-Depositos/lnkDepositoenFondodeCese laboral'))

//Abre la pestaña TELLER
WebUI.switchToWindowTitle('Teller Financial Services')

//Verifica titulo TELLER.FINANCIAL.SERVICES,CESE.LABORAL
WebUI.waitForElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/lblTituloTELLER.FINANCIAL.SERVICES,CESE.LABORAL'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/lblTituloTELLER.FINANCIAL.SERVICES,CESE.LABORAL'))

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresa cuenta 
WebUI.waitForElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/txtNroDeCuenta'), 6)
WebUI.setText(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/txtNroDeCuenta'),'11138515109')

//Ingresa monto
WebUI.waitForElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/txtImporte'), 6)
WebUI.setText(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/txtImporte'),'100')

//Click en boton validar
WebUI.waitForElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/btnValidarRegistro'),6)
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/btnValidarRegistro'))

//Click boton aceptar
WebUI.waitForElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/btnAceptarRegistro'),6)
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/btnAceptarRegistro'))

//Acepto alertas
WebUI.waitForElementVisible(findTestObject('Object Repository/55-Reversos/TELLER/btnAceptarAlertas'),6)
WebUI.click(findTestObject('Object Repository/55-Reversos/TELLER/btnAceptarAlertas'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito en Fondo de Cese Laboral/Teller Financial Services/lblTxnCompleta'))
assert element.contains('Txn Completa:')

// Imprimir el numero de operacion en consola
println("El ID de la txt es: " + element)
 
//Dividir la oración en palabras individuales utilizando el espacio como separador
String[] palabras = element.split(" ");
 
// Obtener la tercera palabra (índice 2 ya que los índices comienzan en 0 en arrays)
String terceraPalabra = palabras[2];
 
// Imprimir la tercera palabra seleccionada
println("La tercera palabra es: " + terceraPalabra);

//Cierra Deposito
WebUI.switchToWindowIndex(0)
WebUI.click(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/Fil.089 M.del Plata Ctr/btnDepositos'))

//Ir a Reversos
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkReversos'))

//Selecciona Reverso de operaciones
WebUI.click(findTestObject('Object Repository/02-Dashboard/54-Reversos/lnkReversodeOperaciones'))

//Abre la pestaña BCCL.E.EB.CONS.REVE
WebUI.switchToWindowTitle('BCCL.E.EB.CONS.REVE')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

//Selecciona Reverso de operaciones
WebUI.switchToWindowIndex(0)
WebUI.click(findTestObject('Object Repository/02-Dashboard/54-Reversos/lnkReversodeOperaciones'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Abre la pestaña BCCL.E.EB.CONS.REVE
WebUI.switchToWindowTitle('BCCL.E.EB.CONS.REVE')
WebUI.maximizeWindow()

//Ingresa Usuario
WebUI.setText(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/txtUsuario'),'B.2547')

//Ingresa el numero de operacion obtenido
WebUI.setText(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/txtNroContrato'),terceraPalabra)

//Selecciona botón Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Espera y verifica que se muestre al menos 1 dato de la tx buscada
WebUI.waitForElementVisible(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/lblIdOperacion'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/lblIdOperacion'))
def element2 = WebUI.getText(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/lblIdOperacion'))
assert element2.contains('Id Operacion')

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Selecciona boton Reversar
WebUI.click(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/btnReversar'))

//Reversa el registro
WebUI.click(findTestObject('Object Repository/55-Reversos/TELLER/btnAceptarRegistro'))

//Acepto alertas
//WebUI.waitForElementVisible(findTestObject('Object Repository/55-Reversos/TELLER/btnAceptarAlertas'),6)
//WebUI.click(findTestObject('Object Repository/55-Reversos/TELLER/btnAceptarAlertas'))


//Espera y recibe mensaje de tx completa reversada
WebUI.waitForElementVisible(findTestObject('Object Repository/55-Reversos/TELLER/lblTxnCompletaReversada'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/55-Reversos/TELLER/lblTxnCompletaReversada'))
def element3 = WebUI.getText(findTestObject('Object Repository/55-Reversos/TELLER/lblTxnCompletaReversada'))
assert element3.contains('Txn Completa:')

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
