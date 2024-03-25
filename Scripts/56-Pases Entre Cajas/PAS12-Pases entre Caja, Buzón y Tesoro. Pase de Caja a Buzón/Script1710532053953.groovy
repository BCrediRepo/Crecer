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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,60), findTestData('MainData/Users').getValue(2,60))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en pases
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkPases'))

//Click en pase de caja a buzon
WebUI.click(findTestObject('Object Repository/02-Dashboard/55-Pases Entre Cajas/lnkPasedeCajaaBuzon'))

//Switch a la ventana TELLER
WebUI.switchToWindowTitle('TELLER')

//Maximizamos
WebUI.maximizeWindow()

//Ingresamos Monto MN
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/txtMontoMN'), '10')

//Click en validar registro
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/003-TELLER/btnValidarRegistro'))

//Click en aceptar registro
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/003-TELLER/btnAceptarRegistro'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/57-Pases Entre Cajas/003-TELLER/lblTxn Completa'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/57-Pases Entre Cajas/003-TELLER/lblTxn Completa'))

def element = WebUI.getText(findTestObject('Object Repository/57-Pases Entre Cajas/003-TELLER/lblTxn Completa'))

assert element.contains('Txn Completa')


// Imprimir el numero de operacion en consola
println("El ID de la txt es: " + element)
 
//Dividir la oración en palabras individuales utilizando el espacio como separador
String[] palabras = element.split(" ");
 
// Obtener la tercera palabra (índice 2 ya que los índices comienzan en 0 en arrays)
String terceraPalabra = palabras[2];
 
// Imprimir la tercera palabra seleccionada
println("La tercera palabra es: " + terceraPalabra);


//Ingresa el numero de operacion obtenido
WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/003-TELLER/txtPaseCajaBuzon'), terceraPalabra)

//Click en ver un registro
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/003-TELLER/btnVerRegistro'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/57-Pases Entre Cajas/003-TELLER/lblARS'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/57-Pases Entre Cajas/003-TELLER/lblARS'))

def element2 = WebUI.getText(findTestObject('Object Repository/57-Pases Entre Cajas/003-TELLER/lblARS'))

assert element2.contains('ARS')

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




