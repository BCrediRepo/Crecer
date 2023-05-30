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

//Ejecuta en la linea de comando ENQ BCCL.E.DEP.EFE.TAS
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),'ENQ BCCL.AS.HIS.AJUS.DEPOSITOS')

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña BCCL.E.DEP.EFE.TAS
WebUI.switchToWindowTitle('BCCL.AS.HIS.AJUS.DEPOSITOS')
WebUI.maximizeWindow()

//Nueva seleccion
WebUI.click(findTestObject('Object Repository/26-Dispositivos/BCCL.AS.HIS.AJUS.DEPOSITOS/lnkNuevaSeleccion'))

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Ejecuta en la linea de comando ENQ BCCL.E.DEP.EFE.TAS
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),'ENQ BCCL.AS.HIS.AJUS.DEPOSITOS')

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña BCCL.E.DEP.EFE.TAS
WebUI.switchToWindowTitle('BCCL.AS.HIS.AJUS.DEPOSITOS')
WebUI.maximizeWindow()

//Setea Dispositivo
WebUI.waitForElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.AS.HIS.AJUS.DEPOSITOS/inputDispositivo'),6)
WebUI.setText(findTestObject('Object Repository/26-Dispositivos/BCCL.AS.HIS.AJUS.DEPOSITOS/inputDispositivo'), '07321')

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/26-Dispositivos/BCCL.AS.HIS.AJUS.DEPOSITOS/btnEjecutar'))

//Chequea que existan datos
WebUI.waitForElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.AS.HIS.AJUS.DEPOSITOS/lblFECHAPROCESO'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.AS.HIS.AJUS.DEPOSITOS/lblFECHAPROCESO'))
def element = WebUI.getText(findTestObject('Object Repository/26-Dispositivos/BCCL.AS.HIS.AJUS.DEPOSITOS/lblFECHAPROCESO'))
assert element.contains('FECHA PROCESO')

WebUI.waitForElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.AS.HIS.AJUS.DEPOSITOS/lblDISPOSITIVO'),6)
WebUI.waitForElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.AS.HIS.AJUS.DEPOSITOS/lblSECUENCIA'),6)
WebUI.waitForElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.AS.HIS.AJUS.DEPOSITOS/lblMONEDA'),6)
WebUI.waitForElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.AS.HIS.AJUS.DEPOSITOS/lblNROTARJETA'),6)
WebUI.waitForElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.AS.HIS.AJUS.DEPOSITOS/lblMONTOREAL'),6)
WebUI.waitForElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.AS.HIS.AJUS.DEPOSITOS/lblMONTOREGISTRADO'),6)
WebUI.waitForElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.AS.HIS.AJUS.DEPOSITOS/lblDIFERENCIA'),6)
WebUI.waitForElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.AS.HIS.AJUS.DEPOSITOS/lblESTADO'),6)
WebUI.waitForElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.AS.HIS.AJUS.DEPOSITOS/lblINGRESADO'),6)
WebUI.waitForElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.AS.HIS.AJUS.DEPOSITOS/lblAUTORIZADO'),6)
WebUI.waitForElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.AS.HIS.AJUS.DEPOSITOS/lblFECHAYHORA'),6)


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

