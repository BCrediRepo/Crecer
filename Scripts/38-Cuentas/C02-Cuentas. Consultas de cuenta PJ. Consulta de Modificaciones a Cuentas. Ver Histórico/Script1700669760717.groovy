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

//Ingresamos el menu ?302 en el command line
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?302')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

//Maximizamos
WebUI.maximizeWindow()

//Click en cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/01-Temenos T24/spanCuentas'))

//Click en consulta de cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/04 - Ultima Modificacion - Temenos T24/spanConsultas de Cuenta'))

//click en consulta de modificaciones a cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/04 - Ultima Modificacion - Temenos T24/lnkConsulta de Modificaciones a Cuentas'))

//Switch a la ventana Consulta de Modificaciones a Ctas
WebUI.switchToWindowTitle('Consulta de Modificaciones a Ctas')

//Maximizamos
WebUI.maximizeWindow()

//Seteo de datos "Id. Persona"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Id. Persona', '1000000011')

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/39-Cuentas/Consulta de Modificaciones a Ctas/lnkEjecutar'))

//Selecionamos del cbx la opcion ver historico
WebUI.selectOptionByIndex(findTestObject('Object Repository/39-Cuentas/Consulta de Modificaciones a Ctas/cbxVerHistorico'), 1)

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/39-Cuentas/Consulta de Modificaciones a Ctas/btnEjecutar'))

//Switch a la ventana Consulta Historica de Cuenta
WebUI.switchToWindowTitle('Consulta Historica de Cuenta')

//Click en ver historico
WebUI.click(findTestObject('Object Repository/39-Cuentas/Consulta historica de cuenta/lnkVer Historico'))

//Switch a la ventana CUENTAS
WebUI.switchToWindowTitle('CUENTAS')

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Consulta historica de cuenta/CUENTAS/lblCategoria'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/Consulta historica de cuenta/CUENTAS/lblCategoria'))

def element = WebUI.getText(findTestObject('Object Repository/39-Cuentas/Consulta historica de cuenta/CUENTAS/lblCategoria'))

assert element.contains('Categoria')

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

