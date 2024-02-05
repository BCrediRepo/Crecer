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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en comisiones y bonificaciones
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkComisionesyBonificaciones'))

//Click en comisiones
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkComisiones'))

//Click en Cobro Comisiones Manuales
WebUI.click(findTestObject('Object Repository/02-Dashboard/04-Comisiones/lnkCobroComisiones Manuales - Planta'))

//Switch a la ventana Account Charge Request
WebUI.switchToWindowTitle('Account Charge Request')

//Maximizamos
WebUI.maximizeWindow()

//Selecionamos el tipo de pago
WebUI.selectOptionByIndex(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/cbxTipoPago'), 1)

//Ingresamos la cuenta de debito 00545293967
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCuentaDebito'), '00430300691')

//Seleciono Codigo Concepto
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'), '18301CMI')

//Agregamos comentarios de observaciones
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'), 'PRUEBAS CRECER')

//Click en aceptar registro
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnAceptar Registro'))

//Click en aceptar alertas
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lnkAceptar Alertas'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))

def element = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))

assert element.contains('Txn Completa')


//Switch a la ventana Account Charge Request
WebUI.switchToWindowTitle('Account Charge Request')

//Maximizamos
WebUI.maximizeWindow()

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

def Moneda = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/spanARS'))


// Verifica el valor de check y reporta el resultado
if (Moneda == "ARS") {
	Moneda ==  ("Checkpoint estado: Coincide")
} else {
	Moneda == ("Checkpoint estado: No coincide")
}


WebUI.closeBrowser()


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en reversos
WebUI.click(findTestObject('Object Repository/02-Dashboard/spanReversos'))

//Click en reverso de operaciones
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkReverso de Operaciones'))

//Switch a la ventana BCCL.E.EB.CONS.REVE
WebUI.switchToWindowTitle('BCCL.E.EB.CONS.REVE')
 
//Maximizamos
WebUI.maximizeWindow()

//Ingresamos el numero de contrato
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/BCCL.E.EB.CONS.REVE/txtNroContrato'), terceraPalabra)

//Ingresamos en usuario
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/BCCL.E.EB.CONS.REVE/txtUsuario'), 'B.0043')

//Click en ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Click en reversar
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/BCCL.E.EB.CONS.REVE/lnkReversar'))

//Click en reversar un registro
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnReversarRegistro'))

//Ingresa el numero de operacion obtenido
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtComisiones Manuales-Caja'), terceraPalabra)

//Click en ver un registro
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnVerRegistro'))

//Click en audit
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/spanAudit'))

def Estado = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lnkEstado'))

// Verifica el valor de check y reporta el resultado
if (Estado == "REVE") {
	Estado ==  ("Checkpoint estado: Coincide")
} else {
	Estado == ("Checkpoint estado: No coincide")
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

