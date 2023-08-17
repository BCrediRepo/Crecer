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
import java.text.SimpleDateFormat
import java.util.Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,24), findTestData('MainData/Users').getValue(2,24))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando ENQ BCCL.E.TT.REMESAS.RECIBIDAS
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.TT.REMESAS.RECIBIDAS')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña BCCL.E.TT.REMESAS.RECIBIDAS
WebUI.switchToWindowTitle('BCCL.E.TT.REMESAS.RECIBIDAS')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Ejecuta en la linea de comando ENQ BCCL.E.TT.REMESAS.RECIBIDAS
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.TT.REMESAS.RECIBIDAS')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Abre la pestaña BCCL.E.TT.REMESAS.RECIBIDAS
WebUI.switchToWindowTitle('BCCL.E.TT.REMESAS.RECIBIDAS')

//Verifica titulo BCCL.E.TT.REMESAS.ENVIADAS
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/lblTituloBCCL.E.TT.REMESAS.RECIBIDAS'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/lblTituloBCCL.E.TT.REMESAS.RECIBIDAS'))

//Ingresa Moneda
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/txtMoneda1'), 6)
WebUI.click(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/rbMoneda'))
WebUI.setText(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/txtMoneda1'), 'ARS')
WebUI.click(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/rbMoneda'))

//Ingresa Fecha
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/txtFecha2'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/txtFecha2'), '20220706')

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Selecciona boton EJECUTAR
WebUI.click(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/btnEjecutar'))

//Espera y Verifica que se muestren las columnas del registro
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/lblFECHA'),10)
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/lblHORA'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/lblIdTXN'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/lblIMPORTE'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/lblMONEDA'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/lblRemito'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/lblSUCDESTINO'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/lblSUCORIGEN'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/lblTESORODERECEPCION'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/lblTransaccion'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/lblEstado'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/lblFECHA'))
def element = WebUI.getText(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.RECIBIDAS/lblFECHA'))
assert element.contains('FECHA')

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