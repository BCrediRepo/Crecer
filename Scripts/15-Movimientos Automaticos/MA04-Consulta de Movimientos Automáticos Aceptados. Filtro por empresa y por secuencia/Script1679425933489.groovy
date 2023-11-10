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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ejecuta en la linea de comando ENQ BCCL.E.MOV.AUT.ACPT
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),'ENQ BCCL.E.MOV.AUT.ACPT')


WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña BCCL.E.MOV.AUT.ACPT
WebUI.switchToWindowTitle('BCCL.E.MOV.AUT.ACPT')

//Maximiza la pestaña
WebUI.maximizeWindow()

//Verifica que se visualice el titulo BCCL.E.MOV.AUT.ACPT
WebUI.waitForElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/lblTituloBCCL.E.MOV.AUT.ACPT'), 6)

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Ejecuta en la linea de comando ENQ BCCL.E.MOV.AUT.ACPT
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),'ENQ BCCL.E.MOV.AUT.ACPT')

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña BCCL.E.MOV.AUT.ACPT
WebUI.switchToWindowTitle('BCCL.E.MOV.AUT.ACPT')

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Maximiza la pestaña
WebUI.maximizeWindow()

//Verifica que se visualice el titulo BCCL.E.MOV.AUT.ACPT
WebUI.waitForElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/lblTituloBCCL.E.MOV.AUT.ACPT'), 6)

//Ingresa el codigo de empresa
WebUI.waitForElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/inputcCodEmpresa'),6)
WebUI.setText(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/inputcCodEmpresa'),'853')

//Ingresa la secuencia
WebUI.waitForElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/inputSecuencia'),6)
WebUI.setText(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/inputSecuencia'),'0361224')

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Presiona botón ejecutar
WebUI.click(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/btnEjecutar'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verifica que se visualice el registro filtrado
WebUI.verifyElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/lblMovimientosaceptadasinforme'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//Verifica si están los elementos secuencia y codigo de empresa
WebUI.waitForElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/lblCodEmpresa'),6)
WebUI.waitForElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/inputSecuencia'),6)

//Ver detalle del registro
WebUI.waitForElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/btnVerRegistro'),6)
WebUI.click(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/btnVerRegistro'))

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

