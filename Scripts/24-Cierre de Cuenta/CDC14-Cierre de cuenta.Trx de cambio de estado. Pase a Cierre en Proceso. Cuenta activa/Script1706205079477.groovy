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

//ES NECESARIO EJECUTAR PRIMERO EL CASO 16 POR QUE LLAMA EN CASCADA EL CASO 15 (EL CASO 15 LLAMA AL 14)

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 4), findTestData('MainData/Users').getValue(2, 4))
WebUI.maximizeWindow()

//Seleccionar "Cuentas"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))

//Seleccionar "Modificacion de cuenta"
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkModificaciondDeCuenta'))

//Seleccionar "Cambio / Mantenimiento de Estado"
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/lnkCambio-MantenimientodeEstado'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "A Cierre en Proceso"
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/02-Cambio-Mantenimiento de Estado/lnkACierreenProceso'))

//Cambiar ventana "BCCL.AC.POSIB.CIERRE.EN.PROC"
WebUI.switchToWindowTitle('BCCL.AC.POSIB.CIERRE.EN.PROC')

//Filtro limpieza
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Seleccionar "A Cierre en Proceso"
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/02-Cambio-Mantenimiento de Estado/lnkACierreenProceso'))

//Cambiar ventana "BCCL.AC.POSIB.CIERRE.EN.PROC"
WebUI.switchToWindowTitle('BCCL.AC.POSIB.CIERRE.EN.PROC')

//Maximizar Ventana
WebUI.maximizeWindow()

//Setear "Numero Cuenta"
WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/13-BCCL.AC.POSIB.CIERRE.EN.PROC/txtNumeroCuenta'), '01730054895')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Pase a Cierre en Proceso"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/13-BCCL.AC.POSIB.CIERRE.EN.PROC/lnkPaseaCierreenProceso'))

//Cambiar ventana "ACCT.CLOSURE"
WebUI.switchToWindowTitle('ACCT.CLOSURE')

//Seleccionar "boton Dropdown de Motivo Cierre"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/14-ACCT.CLOSURE/btnDropdownMotivoCierre'))

//Seleccionar "01"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/14-ACCT.CLOSURE/lbl01'))

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/14-ACCT.CLOSURE/btnAceptarRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/lblTxnCompleta'))

//Validar "Txn Completa"
def element = WebUI.getText(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/lblTxnCompleta'))
assert element.contains('Txn Completa')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}