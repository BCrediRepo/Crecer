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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,17), findTestData('MainData/Users').getValue(2,17))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en comisiones
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkComisiones'))

//Click en Cobro de Comisiones Manuales EN EFECTIVO
WebUI.click(findTestObject('Object Repository/02-Dashboard/04-Comisiones/lnkCobro de Comisiones Manuales EN EFECTIVO'))

//Switch a la ventana Account Charge Request
WebUI.switchToWindowTitle('Account Charge Request')

//Maximizamos
WebUI.maximizeWindow()

//Selecionamos la moneda
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btndropdown'))
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblPesos Argentinos'))

//Selecionamos si es o no socio
WebUI.selectOptionByIndex(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/cbxEsSocio'), 2)

//Ingresamos el ID ordenante
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtId Ordenante'), '1000027871')

//Seleciono Codigo Concepto
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'), '18301CMI')

//Agregamos comentarios de observaciones
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'), 'PRUEBA')

//Click en aceptar registro
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnAceptar Registro'))

//Click en Aceptar Alertas
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lnkAceptar Alertas'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))

def element = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))

assert element.contains('Txn Completa')

//Switch a la ventana Account Charge Request
WebUI.switchToWindowTitle('Account Charge Request')

// Imprimir el numero de operacion en consola
println("El ID de la txt es: " + element)
 
//Dividir la oración en palabras individuales utilizando el espacio como separador
String[] palabras = element.split(" ");
 
// Obtener la tercera palabra (índice 2 ya que los índices comienzan en 0 en arrays)
String terceraPalabra = palabras[2];
 
// Imprimir la tercera palabra seleccionada
println("La tercera palabra es: " + terceraPalabra);
 
//Ingresa el numero de operacion obtenido
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtComisiones Manuales-Caja'), terceraPalabra)

//Click en ver un registro
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnVerRegistro'))


def check = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/span217,80'))

// Verifica el valor de check y reporta el resultado
if (check == "217,80") {
	check ==  ("Checkpoint estado: Coincide")
} else {
	check == ("Checkpoint estado: No coincide")
}


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


