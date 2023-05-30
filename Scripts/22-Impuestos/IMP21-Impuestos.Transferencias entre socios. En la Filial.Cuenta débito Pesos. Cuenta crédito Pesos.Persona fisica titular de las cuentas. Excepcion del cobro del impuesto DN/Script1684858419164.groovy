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
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lnkPasesyTransferenciasEntreSocios'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkPasesyTransferenciasEntreSocios'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/39-Pases y Transferencias Entre Socios/lnkTransferenciaaCuentasdelBanco'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/39-Pases y Transferencias Entre Socios/lnkTransferenciaaCuentasdelBanco'))

//Switch a la ventana de Movimiento de Fondos
WebUI.switchToWindowTitle('Movimiento de Fondos')
WebUI.maximizeWindow()

//Completo los campos necesarios para el pase entre socios (Transferencia a Cuentas del Banco)
WebUI.waitForElementVisible(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/txtIdOrdenante'), 6)
WebUI.setText(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/txtIdOrdenante'), '1002143103')
WebUI.waitForElementVisible(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/txtNrodeCuentaDebito'), 6)
WebUI.setText(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/txtNrodeCuentaDebito'), '00740000115')
WebUI.click(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/txtNrodeCuentaCredito'))
WebUI.waitForElementVisible(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/txtNrodeCuentaCredito'), 6)
WebUI.setText(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/txtNrodeCuentaCredito'), '00740001323')
WebUI.waitForElementVisible(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/txtImporte'), 6)
WebUI.setText(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/txtImporte'), '100,00')
WebUI.waitForElementVisible(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/btnMotivo'), 6)
WebUI.click(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/btnMotivo'))
WebUI.waitForElementVisible(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/lblFAC'), 6)
WebUI.click(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/lblFAC'))
WebUI.waitForElementVisible(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/btnAceptarRegistro'), 6)
WebUI.click(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/btnAceptarRegistro'))

//Acepto las Alertas
WebUI.waitForElementVisible(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/lnkAceptarAlertas'), 6)
WebUI.click(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/lnkAceptarAlertas'))

//Valido que se haya completado la transaccion
WebUI.waitForElementVisible(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/lblTxnCompleta'))
assert element.contains('Txn Completa:')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Switch a la ventana de Forzar Firmas
WebUI.switchToWindowTitle('Verificacion de Firmas - Fil.073 Jujuy')
WebUI.maximizeWindow()

//Fuerzo las firmas y valido que se haya completado la firma
WebUI.waitForElementVisible(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/select_CANCELARFORZARVERIFICAR'), 6)
WebUI.selectOptionByIndex(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/select_CANCELARFORZARVERIFICAR'), 2)
WebUI.click(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/btnAceptarRegistroFirmas'))

WebUI.waitForElementVisible(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/lblFINALIZADA'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/lblFINALIZADA'))
def element2 = WebUI.getText(findTestObject('Object Repository/41-Pases y Transferencias entre Socios/02-Transferencia a Cuentas del Banco/lblFINALIZADA'))
assert element2.contains('FINALIZADA')
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
