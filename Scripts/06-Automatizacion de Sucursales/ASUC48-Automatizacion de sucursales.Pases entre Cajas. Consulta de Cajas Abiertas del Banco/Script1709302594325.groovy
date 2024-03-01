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

//Ingresar "?70" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?70')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton de buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')

//Seleccionar "Consultas Varias"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkConsultasVarias'))

//Seleccionar "Consultas Auditoria Automatizacion Sucursales"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/ConsultasVarias/lnkConsultasAuditoriaAutomatizacionSucursales'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Consulta de Cajas Abiertas del Banco"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/ConsultasVarias/Consultas Auditoria Automatizacion Sucursales/lnkConsultadeCajasAbiertasdelBanco'))

//Cambiar ventana "Cajas Abiertas del Banco"
WebUI.switchToWindowTitle('Cajas Abiertas del Banco')

//Verificar "Estado Actual"
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Cajas Abiertas del Banco/lblEstadoActual'))

//Validar "Estado Actual"
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Cajas Abiertas del Banco/lblEstadoActual'))
assert element.contains('Estado Actual')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}