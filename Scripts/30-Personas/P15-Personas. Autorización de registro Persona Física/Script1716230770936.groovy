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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(2, 1))

WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ejecuta en la linea de comando menu ?25
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?25')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Switch a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

//Maximizamos
WebUI.maximizeWindow()

//Click en Personas
WebUI.click(findTestObject('Object Repository/31-Personas/Temenos T24/spanPersonas'))

//Click en Autorizacion de Registro de Personas
WebUI.click(findTestObject('Object Repository/31-Personas/Temenos T24/Personas/spanAutorizacion de Registro de Personas'))

//Click en Autorizacion de registro persona fisica
WebUI.click(findTestObject('Object Repository/31-Personas/Temenos T24/Personas/lnkAutorizacion de registro persona fisica'))

//Switch a la ventana NAU Consulta para Autorizacion - PF
WebUI.switchToWindowTitle('NAU Consulta para Autorizacion - PF')

//Maximizamos
WebUI.maximizeWindow()

//Seteo de datos "Nombre(s)"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nombre(s)', 'O F DA ROSA')

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/31-Personas/NAU Consulta para Autorizacion - PF/lblOperador'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/31-Personas/NAU Consulta para Autorizacion - PF/lblOperador'))

def element = WebUI.getText(findTestObject('Object Repository/31-Personas/NAU Consulta para Autorizacion - PF/lblOperador'))

assert element.contains('Operador')


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

