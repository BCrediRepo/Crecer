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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se accede al menú BCCL.E.TINTERNAS.APAGAR
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), ('?343'))
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))
WebUI.switchToWindowTitle('Temenos T24')
WebUI.maximizeWindow()
WebUI.click(findTestObject('Object Repository/21-Fallas/02-Temenos T24/spanRegistro de Fallas de Caja'))
WebUI.click(findTestObject('Object Repository/21-Fallas/02-Temenos T24/lnkBaja de Sobrante de Caja'))
WebUI.switchToWindowTitle('BCCL.E.TT.BAJA.SOBRANTE.CAJA')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

WebUI.switchToWindowTitle('Temenos T24')
WebUI.maximizeWindow()
WebUI.click(findTestObject('Object Repository/21-Fallas/02-Temenos T24/lnkBaja de Sobrante de Caja'))
WebUI.switchToWindowTitle('BCCL.E.TT.BAJA.SOBRANTE.CAJA')

WebUI.setText(findTestObject('Object Repository/21-Fallas/01-BCCL.E.TT.BAJA.SOBRANTE.CAJA/inputFechaDesde'), '20200725')
WebUI.click(findTestObject('Object Repository/21-Fallas/a_Ejecutar'))
WebUI.waitForElementVisible(findTestObject('Object Repository/21-Fallas/01-BCCL.E.TT.BAJA.SOBRANTE.CAJA/tdId'),3)
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