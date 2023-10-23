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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(2, 3))
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

//Click en consultas de faltantes en dispositivos
WebUI.click(findTestObject('Object Repository/21-Fallas/07- Temenos T24 III/lnkConsultas de Faltantes en Dispositivos'))

//Switch a la ventana BCCL.E.EXTORNO.DISPO.GEOP
WebUI.switchToWindowTitle('BCCL.E.EXTORNO.DISPO.GEOP')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Switch a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

//Click en consultas de faltantes en dispositivos
WebUI.click(findTestObject('Object Repository/21-Fallas/07- Temenos T24 III/lnkConsultas de Faltantes en Dispositivos'))

//Switch a la ventana BCCL.E.EXTORNO.DISPO.GEOP
WebUI.switchToWindowTitle('BCCL.E.EXTORNO.DISPO.GEOP')

//Maximizamos
WebUI.maximizeWindow()

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/21-Fallas/09-BCCL.E.EXTORNO.DISPO.GEOP/lnkEjecutar'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/21-Fallas/09-BCCL.E.EXTORNO.DISPO.GEOP/lblDispositivo'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/21-Fallas/09-BCCL.E.EXTORNO.DISPO.GEOP/lblDispositivo'))

def element = WebUI.getText(findTestObject('Object Repository/21-Fallas/09-BCCL.E.EXTORNO.DISPO.GEOP/lblDispositivo'))

assert element.contains('Dispositivo')


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


