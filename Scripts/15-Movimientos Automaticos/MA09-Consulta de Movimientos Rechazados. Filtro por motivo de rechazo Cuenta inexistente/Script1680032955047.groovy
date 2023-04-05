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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,14), findTestData('MainData/Users').getValue(2,14))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ejecuta en la linea de comando ENQ BCCL.E.MOV.AUT.REJ
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),'ENQ BCCL.E.MOV.AUT.REJ')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña BCCL.E.MOV.AUT.REJ
WebUI.switchToWindowTitle('BCCL.E.MOV.AUT.REJ')

//Maximiza la pestaña
WebUI.maximizeWindow()

//Verifica que se visualice el titulo BCCL.E.MOV.AUT.REJ
WebUI.waitForElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.REJ/lblTituloBCCL.E.MOV.AUT.REJ'), 6)

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Ejecuta en la linea de comando ENQ BCCL.E.MOV.AUT.REJ
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),'ENQ BCCL.E.MOV.AUT.REJ')

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña BCCL.E.MOV.AUT.REJ
WebUI.switchToWindowTitle('BCCL.E.MOV.AUT.REJ')

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Maximiza la pestaña
WebUI.maximizeWindow()

//Ingresa el estado 1 (cuenta inexistente)
WebUI.setText(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.REJ/inputError(Estado)'),'1')

//Presiona botón ejecutar
WebUI.click(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.REJ/btnEjecutar'))

//Verifica que se mueste el titulo del informe de movimientos rechazados
WebUI.waitForElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.REJ/lblTituloMovimientosrechazaninforme'),6)

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
