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
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter

//Configuracion del ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 5), findTestData('MainData/Users').getValue(2, 5))
WebUI.maximizeWindow()

//Accedo al menu de Devolucion de Impuestos
def menuDesplegable = ["Impuestos", "Operaciones de Impuestos por Descripcion"]
def link = "Devolucion de Impuestos en Cuenta Contable"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowIndex(1)
WebUI.maximizeWindow()

WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtCUITCUILSocio'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtCUITCUILSocio'), '30708114584')
WebUI.click(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtIdPersonaSocio'))
WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtMoneda'), 'ARS')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtSucursal'), '073')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtProvJurisdiccion'), '10')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtTipodeImpuesto'), 'DC')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtDescripciondelImpuesto'), '31182')
WebUI.click(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtPorcentaje'))
WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtPorcentaje'), '1,20')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtBaseImponible'), '100,00')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/12-Devolucion de Impuestos en Cuenta Contable/txtMontoaCobrar'),  '1,20')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Valido que se haya completado la transaccion
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
