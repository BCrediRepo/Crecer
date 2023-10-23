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

WebUI.callTestCase(findTestCase('37-Ajustes Monetarios/AM01 - Ajustes monetarios. Alta de Nota de Débito Ajuste. Fecha valor permitida. Saldo suficiente entre fecha valor y fecha de proceso. Cuenta sin bloqueos'),
	[:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)


//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 14), findTestData('MainData/Users').getValue(
        2, 14))

//Se maximiza la ventana
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

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

WebUI.switchToWindowIndex(0)

//Espera a que sea visible el buscador.
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 3)

//Se realiza una busqueda
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.EB.CONS.REVE')

//Click en el boton "Ejecutar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Se cambia de ventana
WebUI.switchToWindowTitle('BCCL.E.EB.CONS.REVE')

//Se maximiza la ventana
WebUI.maximizeWindow()

//Se ingresa el valor "B.0043"
WebUI.setText(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/txtUsuario'), 'B.0273')

//Se espera a que sea visible "Ejecutar"
WebUI.waitForElementVisible(findTestObject('00-Utils/02-Filtros/lnkEjecutar'), 3)

//Se clickea en "Ejecutar"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Toma screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se espera a que este visible "Reversar"
WebUI.waitForElementVisible(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/lblFT222'), 3)

//Se clickea en "Reversar"
WebUI.click(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/lnkReversar'))

//Se clickea en btn "Reversar"
WebUI.click(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/btnReversar'))

//Se espera a que sea visible el mensaje de alertas
//WebUI.waitForElementVisible(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/btnAutorizacion'), 3)
Alerta = WebUI.verifyElementVisible(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/lnkAceptar Alertas'))

if (Alerta == true) {
    //Se aceptan las alertas
    WebUI.click(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/lnkAceptar Alertas'))
}

//Valido que se haya completado la transaccion
WebUI.waitForElementVisible(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/lblTxnCompletaFT222'), 6)

WebUI.verifyElementVisible(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/lblTxnCompletaFT222'))

def element = WebUI.getText(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/lblTxnCompletaFT222'))

assert element.contains('Txn Completa:')

//Toma screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se inserta el numero de transaccion en el input "Nota de Debito por Ajuste"
WebUI.setText(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/inputNotadeDebitoporAjuste'), 'FT22207670803238')

//Se clickea en btn "Ver Un Registro"
WebUI.click(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/btnVerunRegistro'))

//Toma screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se clickea en btm "Audit"
WebUI.click(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/btnAudit'))

//Toma screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se realiza un assert del estado del registro "REVE"
WebUI.waitForElementVisible(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/lblEstadodelRegistroREVE'), 3)

WebUI.verifyElementVisible(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/lblEstadodelRegistroREVE'))

def element2 = WebUI.getText(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/lblEstadodelRegistroREVE'))

assert element2.contains('REVE')


//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

