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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 2), findTestData('MainData/Users').getValue(2, 2))

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

//Click en Alta
WebUI.click(findTestObject('Object Repository/31-Personas/Temenos T24/spanAlta'))

//Click en Alta Persona Juridica
WebUI.click(findTestObject('Object Repository/31-Personas/Temenos T24/lnkAlta Persona Juridica'))

//Switch a la ventana Lista de PJ - Alta
WebUI.switchToWindowTitle('Lista de PJ - Alta')

//Maximizamos
WebUI.maximizeWindow()

//Seteo de datos "No. CUI"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('No. CUI', '30709901482')

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Click en Seleccionar la Persona Juridica
WebUI.click(findTestObject('Object Repository/31-Personas/Lista de PJ - Alta/lnkSeleccionar la Persona Juridica'))

//Switch a la ventana ARCHIVOS PERSONAS
WebUI.switchToWindowTitle('ARCHIVOS PERSONAS')

//Click en Aceptar Registro
WebUI.click(findTestObject('Object Repository/31-Personas/ARCHIVOS PERSONAS/btnAceptarRegistro'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/31-Personas/ARCHIVOS PERSONAS/lblTxn Completa'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/31-Personas/ARCHIVOS PERSONAS/lblTxn Completa'))

def element = WebUI.getText(findTestObject('Object Repository/31-Personas/ARCHIVOS PERSONAS/lblTxn Completa'))

assert element.contains('Txn Completa')


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


