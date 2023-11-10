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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,25), findTestData('MainData/Users').getValue(2,25))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando menu ENQ BCCL.E.MR.PARTIDAS.PENDIENTES
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.MR.PARTIDAS.PENDIENTES')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Abre la pestaña del menú BCCL.E.MR.PARTIDAS.PENDIENTES
WebUI.switchToWindowTitle('BCCL.E.MR.PARTIDAS.PENDIENTES')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Ejecuta en la linea de comando menu ENQ BCCL.E.MR.PARTIDAS.PENDIENTES
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),'ENQ BCCL.E.MR.PARTIDAS.PENDIENTES')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña del menú BCCL.E.MR.PARTIDAS.PENDIENTES
WebUI.switchToWindowTitle('BCCL.E.MR.PARTIDAS.PENDIENTES')

//Verifica titulo BCCL.E.MR.PARTIDAS.PENDIENTES
WebUI.verifyElementVisible(findTestObject('Object Repository/47-Mov rechazados pendientes de imputacion/BCCL.E.MR.PARTIDAS.PENDIENTES/lblTituloBCCL.E.MR.PARTIDAS.PENDIENTES'))

//Ingresa Sucursal
WebUI.waitForElementVisible(findTestObject('Object Repository/47-Mov rechazados pendientes de imputacion/BCCL.E.MR.PARTIDAS.PENDIENTES/txtSuc1'), 6)
WebUI.setText(findTestObject('Object Repository/47-Mov rechazados pendientes de imputacion/BCCL.E.MR.PARTIDAS.PENDIENTES/txtSuc1'), '')

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Selecciona boton EJECUTAR
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))
//WebUI.delay(15)

//Espera y Verifica la primera columna del registro
WebUI.waitForElementVisible(findTestObject('Object Repository/46-Movimientos Pendientes Cancelados/BCCL.E.MR.PARTIDAS.PEND.CANCELADAS/lblSuc'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/46-Movimientos Pendientes Cancelados/BCCL.E.MR.PARTIDAS.PEND.CANCELADAS/lblSuc'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

def element = WebUI.getText(findTestObject('Object Repository/46-Movimientos Pendientes Cancelados/BCCL.E.MR.PARTIDAS.PEND.CANCELADAS/lblSuc'))
assert element.contains('Suc.')

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