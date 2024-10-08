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
//TEST CASE NAME: IMPUESTOS. Depósito de Monedas en Caudales. Depósito de Monedas. Moneda Pesos. Fecha del dia. Titular alcanzado por el impuesto IDC.

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))

//Ejecutar en la linea de comando "TELLER,RECAUDACION.MONEDAS"
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("TELLER,RECAUDACION.MONEDAS", 1)
WebUI.maximizeWindow()

WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/06-RECAUDACION.MONEDAS/btnNuevoRegistro'), 6)
WebUI.click(findTestObject('Object Repository/23-Impuestos/06-RECAUDACION.MONEDAS/btnNuevoRegistro'))

//Completo el registro de Recaudacion de Monedas
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/06-RECAUDACION.MONEDAS/txtMonto'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/06-RECAUDACION.MONEDAS/txtMonto'), '100')
WebUI.click(findTestObject('Object Repository/23-Impuestos/06-RECAUDACION.MONEDAS/txtCuentaCliente'))
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/06-RECAUDACION.MONEDAS/txtCuentaCliente'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/06-RECAUDACION.MONEDAS/txtCuentaCliente'), '00730029258')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/06-RECAUDACION.MONEDAS/txtComentarios'), 'TEST CRECER')
WebUI.click(findTestObject('Object Repository/23-Impuestos/06-RECAUDACION.MONEDAS/lblDenominaciones'))
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/06-RECAUDACION.MONEDAS/txtCantidad.8'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/06-RECAUDACION.MONEDAS/txtCantidad.8'), '1')
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/06-RECAUDACION.MONEDAS/lblRecaudacionDeMonedas'), 6)
WebUI.click(findTestObject('Object Repository/23-Impuestos/06-RECAUDACION.MONEDAS/lblRecaudacionDeMonedas'))
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Valido que la transaccion haya finalizado con exito
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'), 6)
def element = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert element.contains('Txn Completa:')

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
