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

//user: F00282 - suc 082 - pid 0099

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 66), findTestData('MainData/Users').getValue(2, 66))
WebUI.maximizeWindow()

//Seleccionar "Inventario Permanente"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkInventarioPermanente'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Baja de Partidas"
WebUI.click(findTestObject('Object Repository/02-Dashboard/25-Inventario Permanente/Alta de Partidas/lnkBajadePartidas'))

//Cambiar a la ventana "BCCL.E.IP.BAJA.PARTIDAS"
WebUI.switchToWindowIndex(1)

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Seleccionar "Baja de Partidas"
WebUI.click(findTestObject('Object Repository/02-Dashboard/25-Inventario Permanente/Alta de Partidas/lnkBajadePartidas'))

//Cambiar a la ventana "BCCL.E.IP.BAJA.PARTIDAS"
WebUI.switchToWindowIndex(1)

//Setear Sucursal
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.E.IP.BAJA.PARTIDAS/txtSucursal'), '082')

//Setear Codigo IP
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.E.IP.BAJA.PARTIDAS/txtCodigoIP'), '0099')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Cambiar opción del ComboBox a "Baja Cuenta Contable"
WebUI.selectOptionByIndex(findTestObject('Object Repository/27-Inventario Permanente/BCCL.E.IP.BAJA.PARTIDAS/cbBajaCuentaContable'), 1)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Select Drill down"
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.E.IP.BAJA.PARTIDAS/btnSelectDrilldown'))

//Cambiar a la ventana "BCCL.IP.PARTIDAS"
WebUI.switchToWindowIndex(1)

//Seleccionar "boton Drop down"
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/btnDropdownCuentaContableBaja'))

//Seleccionar ID de Cta Cble Baja
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/lblIDCuentaContableBaja'))

//Setear Fecha Valor Baja
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/txtFechaValorBaja'), GlobalVariable.vFechaCOBAmbTES10)

//Seleccionar "Validar Registro"
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/btnValidarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Registros"
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/btnAceptarRegistro'))

//Definir Objeto
Transaccion = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion.split('\\s+')
def trx1 = partes[2]
assert Transaccion.contains('Txn Completa:')

//Setear Transaccion
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/txtBajaPartidasIP-CuentaContable'), trx1)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Ver Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnVerRegistro'))

//Verificar "Codigo IP"
WebUI.verifyElementVisible(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/lblCodigoIP'))

//Validar "Codigo IP"
def element = WebUI.getText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/lblCodigoIP'))
assert element.contains('0099')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}