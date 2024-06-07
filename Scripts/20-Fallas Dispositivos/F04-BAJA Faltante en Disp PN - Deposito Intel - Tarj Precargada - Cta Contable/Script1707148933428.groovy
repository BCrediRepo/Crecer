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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 8), findTestData('MainData/Users').getValue(
        2, 8))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se accede al menu ?327
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?327')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

//Maximizamos
WebUI.maximizeWindow()

//Click en Dispositivos
WebUI.click(findTestObject('Object Repository/21-Fallas/05-Temenos T24/spanDispositivos'))

//Click en Registro de Fallas
WebUI.click(findTestObject('Object Repository/21-Fallas/05-Temenos T24/spanRegistro de Fallas en Dispositivos'))

//Click en consulta de faltantes en puntos neutrales
WebUI.click(findTestObject('Object Repository/21-Fallas/07- Temenos T24 III/lnkConsulta Faltantes en Puntos Neutrales'))

//Switch a la ventana BCCL.E.EXTORNO.DISPO.GEOP.PN
WebUI.switchToWindowTitle('BCCL.E.EXTORNO.DISPO.GEOP.PN')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

WebUI.switchToWindowIndex(0)

//Switch a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

//Click en consulta de faltantes en puntos neutrales
WebUI.click(findTestObject('Object Repository/21-Fallas/07- Temenos T24 III/lnkConsulta Faltantes en Puntos Neutrales'))

//Switch a la ventana BCCL.E.EXTORNO.DISPO.GEOP.PN
WebUI.switchToWindowTitle('BCCL.E.EXTORNO.DISPO.GEOP.PN')

//Maximizamos
WebUI.maximizeWindow()

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.setText(findTestObject('21-Fallas/08-BCCL.E.EXTORNO.DISPO.GEOP.PN/txtFechaDesde'), '20230823')

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/21-Fallas/08-BCCL.E.EXTORNO.DISPO.GEOP.PN/lnkEjecutar'))

//ASSERT
//WebUI.waitForElementVisible(findTestObject('Object Repository/21-Fallas/08-BCCL.E.EXTORNO.DISPO.GEOP.PN/lblMoneda'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/21-Fallas/08-BCCL.E.EXTORNO.DISPO.GEOP.PN/lblMoneda'))

def element = WebUI.getText(findTestObject('Object Repository/21-Fallas/08-BCCL.E.EXTORNO.DISPO.GEOP.PN/lblMoneda'))

assert element.contains('Moneda') 

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

