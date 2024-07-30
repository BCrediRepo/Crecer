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

WebUI.callTestCase(findTestCase('37-Ajustes Monetarios/AM01 - Ajustes monetarios. Alta de Nota de Debito Ajuste. Fecha valor permitida. Saldo suficiente entre fecha valor y fecha de proceso. Cuenta sin bloqueos'),
	[:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 14), findTestData('MainData/Users').getValue(2, 14))
WebUI.maximizeWindow()

//Toma screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Espera a que sea visible el buscador.
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 3)

//Se realiza una busqueda
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.EB.CONS.REVE')

//Toma screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en el boton "Ejecutar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Se cambia de ventana 
WebUI.switchToWindowTitle('BCCL.E.EB.CONS.REVE')

//Se maximiza la ventana
WebUI.maximizeWindow()

//Seteo de datos "Fecha Desde", "Usuario"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Usuario', 'B.0273')

//Se clickea en "Ejecutar"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Toma screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se clickea en lnk "Reversar"
WebUI.click(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/lnkReversar'))

//Realiza la Reversa
WebUI.click(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/btnReversar'))

//Se espera a que sea visible el mensaje de alertas
Alerta = WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

if (Alerta == true) {
    //Se aceptan las alertas
    WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
}

//Valido que se haya completado la transaccion
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))

//Definir Objeto
Transaccion = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento solo el número de transacción
def partes = Transaccion.split('\\s+')
def trx1 = partes[2]
assert Transaccion.contains('Txn Completa:')

//Toma screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Completa numero de transaccion en "Nota de Debito por Ajuste"
WebUI.setText(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/inputNotadeDebitoporAjuste'), trx1)

//Se clickea en btn "Ver Un Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

//Toma screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se clickea en btn "Audit"
WebUI.click(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/btnAudit'))

//Toma screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se realiza un assert del estado del registro "REVE"
WebUI.waitForElementVisible(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/lblEstadodelRegistroREVE'), 3)
WebUI.verifyElementVisible(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/lblEstadodelRegistroREVE'))
def reversa = WebUI.getText(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/lblEstadodelRegistroREVE'))
assert reversa.contains('REVE')

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

