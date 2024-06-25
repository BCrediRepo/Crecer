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
import java.text.SimpleDateFormat
import java.util.Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,18), findTestData('MainData/Users').getValue(2,18))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),'BCCL.MOV.RECHAZADOS L L')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña BCCL.MOV.RECHAZADOS
WebUI.switchToWindowTitle('%BCCL.MOV.RECHAZADOS')

//Validacion de pestaña correcta
WebUI.waitForElementVisible(findTestObject('Object Repository/36-MovimientosRechazados/BCCL.MOV.RECHAZADOS/tituloBCCL.MOV.RECHAZADOS'),6)

//Limpieza
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))
WebUI.maximizeWindow()

//Toma un ScreenShot
WebUI.waitForElementVisible(findTestObject('Object Repository/36-MovimientosRechazados/BCCL.MOV.RECHAZADOS/tituloBCCL.MOV.RECHAZADOS'),6)
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verifica los datos del registro
WebUI.waitForElementVisible(findTestObject('Object Repository/36-MovimientosRechazados/BCCL.MOV.RECHAZADOS - Lista Default/txtID'),6)
WebUI.verifyElementPresent(findTestObject('Object Repository/36-MovimientosRechazados/BCCL.MOV.RECHAZADOS - Lista Default/txtID'),6)

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

WebUI.waitForElementVisible(findTestObject('Object Repository/36-MovimientosRechazados/BCCL.MOV.RECHAZADOS - Lista Default/txtMovCanal'),6)

//Ver primer registro
WebUI.waitForElementVisible(findTestObject('Object Repository/36-MovimientosRechazados/BCCL.MOV.RECHAZADOS - Lista Default/btnVerRegistro'),6)
WebUI.click(findTestObject('Object Repository/36-MovimientosRechazados/BCCL.MOV.RECHAZADOS - Lista Default/btnVerRegistro'))

//Verifica los datos del registro
WebUI.waitForElementVisible(findTestObject('Object Repository/36-MovimientosRechazados/BCCL.MOV.RECHAZADOS/MovEnvGdp'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/36-MovimientosRechazados/BCCL.MOV.RECHAZADOS/MovEnvGdp'))
def element = WebUI.getText(findTestObject('Object Repository/36-MovimientosRechazados/BCCL.MOV.RECHAZADOS/MovEnvGdp'))
assert element.contains('Mov Env Gdp')

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
