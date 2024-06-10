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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 2), findTestData('MainData/Users').getValue(2, 2))

//Seleccionar "Extracciones"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkExtracciones'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Retiro (CC sin Chequera - Solo Titular)"
WebUI.click(findTestObject('Object Repository/02-Dashboard/47-Extracciones/lnkRetiro(CCsinChequera-SoloTitular)'))

//Canbiar a la ventana "TELLER"
WebUI.switchToWindowIndex(1)

//Seleccionar txt ID Cuenta
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/txtCuenta'))

//Setear ID Cuenta
WebUI.setText(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/txtCuenta'), '00540468975')

//Maximizar Pantalla
WebUI.maximizeWindow()

//Seleccionar Importe a Pagar
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/txtImporteaPagar'))

//Setear Importe a Pagar
WebUI.setText(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/txtImporteaPagar'), '100')

//Setear ID Persona
WebUI.setText(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/txtIdPersona'), '1003174696')

//Seleccionar "Aceptar Registro"
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito De Efectivo En Buzon A Toda Hora/TELLER/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito De Efectivo En Buzon A Toda Hora/TELLER/btnAceptarAlertas'))

//Cambiar ventana a Verificación de firmas
WebUI.switchToWindowIndex(2)

//Seleccionar "FORZAR" del combo box
WebUI.selectOptionByIndex(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/cbxAccion'), 2)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Maximizar Pantalla
WebUI.maximizeWindow()

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/btnAceptar'))

//Definir Transaccionforzada
Transaccionforzada = WebUI.getText(findTestObject('Object Repository/51-Deposito-Extracciones/Verificacion de Firmas/lblTransaccionForzada'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Cambiar ventana al menu del Dashboard
WebUI.switchToWindowIndex(0)

//Ingresar "?327" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?327')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana "Temenos T24"
WebUI.switchToWindowIndex(4)

//Seleccionar "Reversos"
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/Temenos T24/lnkReversos'))

//Maximizar Pantalla
WebUI.maximizeWindow()

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Reverso de Operaciones"
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/Temenos T24/Reversos/lnkReversodeOperaciones'))

//Cambiar a la ventana "BCCL.E.EB.CONS.REVE"
WebUI.switchToWindowIndex(5)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro. Contrato', Transaccionforzada)

//Maximizar Pantalla
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Usuario', 'B.0489')

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Seleccionar "Reversar"
WebUI.click(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/btnReversar'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Reversar un registro"
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito De Efectivo En Buzon A Toda Hora/TELLER/btnReversarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito De Efectivo En Buzon A Toda Hora/TELLER/btnAceptarAlertas'))

//Setear en Pago de Cheque Mostrador
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/txtBajaPartidasIP-CuentaContable'), Transaccionforzada)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Ver Registro"
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito De Efectivo En Buzon A Toda Hora/TELLER/btnVerRegistro'))

//Seleccionar "Audit"
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito De Efectivo En Buzon A Toda Hora/TELLER/lblAudit'))

//Verificar "REVE"
WebUI.verifyElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito De Efectivo En Buzon A Toda Hora/TELLER/lblREVE'))

//Validar "REVE"
def element = WebUI.getText(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito De Efectivo En Buzon A Toda Hora/TELLER/lblREVE'))
assert element.contains('REVE')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}