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

//Configuracion del ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()

//Accedo al menu de Devolucion de Impuestos
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lnkImpuestos'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkImpuestos'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/21-Impuestos/lnkOperacionesdeImpuestosPorDescripcion'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/21-Impuestos/lnkOperacionesdeImpuestosPorDescripcion'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/21-Impuestos/01-Operaciones de Impuestos por Descripcion/lnkDevoluciondeImpuestosenCuentaContable'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/21-Impuestos/01-Operaciones de Impuestos por Descripcion/lnkDevoluciondeImpuestosenCuentaContable'))

//Switch a la ventana de Movimiento de Fondos y completo los campos
WebUI.switchToWindowTitle('Movimiento de Fondos')
WebUI.maximizeWindow()
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtCUITCUILSocio'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtCUITCUILSocio'), '30708114584')
WebUI.click(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtIdPersonaSocio'))
//WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtIdPersonaSocio'), 6)
//WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtIdPersonaSocio'), '1003699096')
//WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtNombrePersona'), 6)
//WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtNombrePersona'), 'WAKI TINKINA ASMEM JUJUY')
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtMoneda'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtMoneda'), 'ARS')
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtSucursal'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtSucursal'), '073')
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtProvJurisdiccion'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtProvJurisdiccion'), '10')
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtTipodeImpuesto'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtTipodeImpuesto'), 'SE')
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtDescripciondelImpuesto'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtDescripciondelImpuesto'), '31000')
WebUI.click(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtPorcentaje'))
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtPorcentaje'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtPorcentaje'), '1,20')
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtBaseImponible'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtBaseImponible'), '100,00')
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtMontoaCobrar'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtMontoaCobrar'), '1,20')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/btnAceptarRegistro'), 6)
WebUI.click(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/btnAceptarRegistro'))

//Valido que se haya completado la transaccion
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/lblTxnCompleta'))
assert element.contains('Txn Completa:')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()


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

