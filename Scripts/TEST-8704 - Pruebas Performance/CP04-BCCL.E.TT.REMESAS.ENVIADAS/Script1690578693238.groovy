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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,28), findTestData('MainData/Users').getValue(2,28))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando ENQ BCCL.E.TT.REMESAS.ENVIADAS
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.TT.REMESAS.ENVIADAS')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña ENQ BCCL.E.TT.REMESAS.ENVIADAS
WebUI.switchToWindowTitle('BCCL.E.TT.REMESAS.ENVIADAS')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Ejecuta en la linea de comando ENQ BCCL.E.TT.REMESAS.ENVIADAS
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.TT.REMESAS.ENVIADAS')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Abre la pestaña BCCL.E.TT.REMESAS.ENVIADAS
WebUI.switchToWindowTitle('BCCL.E.TT.REMESAS.ENVIADAS')

//Verifica titulo BCCL.E.TT.REMESAS.ENVIADAS
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/lblTituloBCCL.E.TT.REMESAS.ENVIADAS'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/lblTituloBCCL.E.TT.REMESAS.ENVIADAS'))

//Ingresa Moneda
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/txtMoneda1'), 6)
WebUI.click(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/rbMoneda'))
WebUI.setText(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/txtMoneda1'), 'ARS')
WebUI.click(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/rbMoneda'))

//Ingresa Fecha
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/txtFecha2'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/txtFecha2'), '20220706')

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Selecciona boton EJECUTAR
WebUI.click(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/btnEjecutar'))

//Espera y Verifica que se muestren las columnas del registro
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/lblFECHA'),10)
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/lblFECHA'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/lblHORA'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/lblSUCORIGEN'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/lblSUCDESTINO'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/lblMONEDA'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/lblIMPORTE'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/lblTESORODEENVIO'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/lblIDTXN'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/lblTransaccion'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/lblRemito'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/lblEstado'))
def element = WebUI.getText(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.TT.REMESAS.ENVIADAS/lblFECHA'))
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