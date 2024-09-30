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

//Accedo al menu de Pases y Transferencias
def menuDesplegable = ["Pases y Transferencias entre Socios"]
def link = "Pase entre Cuentas Misma Titularidad"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowIndex(1)
WebUI.maximizeWindow()

//Completo los campos necesarios para el pase entre socios
WebUI.waitForElementVisible(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/01-Pase entre Cuentas Misma Titularidad/txtIdOrdenante'), 6)
WebUI.setText(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/01-Pase entre Cuentas Misma Titularidad/txtIdOrdenante'), '2000514092')
WebUI.setText(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/01-Pase entre Cuentas Misma Titularidad/txtNro.deCuentaDebito'), '03195011374')
WebUI.click(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/01-Pase entre Cuentas Misma Titularidad/txtNro.deCuentaCredito'))
WebUI.setText(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/01-Pase entre Cuentas Misma Titularidad/txtNro.deCuentaCredito'), '13190056217')
WebUI.setText(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/01-Pase entre Cuentas Misma Titularidad/txtImporte'), '10')
WebUI.setText(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/01-Pase entre Cuentas Misma Titularidad/txtObservaciones'), 'CRECER TEST')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Valido que se haya completado la transaccion
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'), 6)
def element = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert element.contains('Txn Completa:')

//Switch a la ventana de Forzar Firmas
WebUI.switchToWindowIndex(2)
WebUI.maximizeWindow()

//Fuerzo las firmas y valido que se haya completado la firma
WebUI.waitForElementVisible(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/01-Pase entre Cuentas Misma Titularidad/select_CANCELARFORZARVERIFICAR'), 6)
WebUI.selectOptionByIndex(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/01-Pase entre Cuentas Misma Titularidad/select_CANCELARFORZARVERIFICAR'), 2)
WebUI.click(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/01-Pase entre Cuentas Misma Titularidad/btnAceptarRegistroFirmas'))

WebUI.waitForElementVisible(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/01-Pase entre Cuentas Misma Titularidad/lblFINALIZADA'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/01-Pase entre Cuentas Misma Titularidad/lblFINALIZADA'))
def element2 = WebUI.getText(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/01-Pase entre Cuentas Misma Titularidad/lblFINALIZADA'))
assert element2.contains('FINALIZADA')

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
