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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,29), findTestData('MainData/Users').getValue(2,29))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando ENQ BCCL.E.IP.PARTIDAS.ACUM.FIL
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.IP.PARTIDAS.ACUM.FIL')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña BCCL.E.IP.PARTIDAS.ACUM.FI
WebUI.switchToWindowTitle('BCCL.E.IP.PARTIDAS.ACUM.FIL')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Ejecuta en la linea de comando ENQ BCCL.E.IP.PARTIDAS.ACUM.FIL
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.IP.PARTIDAS.ACUM.FIL')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Abre la pestaña BCCL.E.IP.PARTIDAS.ACUM.FIL
WebUI.switchToWindowTitle('BCCL.E.IP.PARTIDAS.ACUM.FIL')

//Verifica titulo BCCL.E.IP.PARTIDAS.ACUM.FIL
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.IP.PARTIDAS.ACUM.FIL/lblTituloBCCL.E.IP.PARTIDAS.ACUM.FIL'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.IP.PARTIDAS.ACUM.FIL/lblTituloBCCL.E.IP.PARTIDAS.ACUM.FIL'))

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Selecciona boton EJECUTAR
WebUI.click(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.IP.PARTIDAS.ACUM.FIL/btnEjecutar'))

//Espera y Verifica que se muestren las columnas del registro
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.IP.PARTIDAS.ACUM.FIL/lblCodigo IP'),30)
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.IP.PARTIDAS.ACUM.FIL/lblMoneda'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.IP.PARTIDAS.ACUM.FIL/lblCodigo IP'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.IP.PARTIDAS.ACUM.FIL/lblMoneda'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.IP.PARTIDAS.ACUM.FIL/lblMontoacumulado'))
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.IP.PARTIDAS.ACUM.FIL/lblTipo'))
def element = WebUI.getText(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.E.IP.PARTIDAS.ACUM.FIL/lblCodigo IP'))
assert element.contains('Codigo IP')

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