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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Ingreso al menu ?302
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?302')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

// Cambia a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

// Maximizamos
WebUI.maximizeWindow()

//Click en Cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/02-Temeno T24/spanCuentas'))

//Click en Consulta de Cuenta
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/04 - Ultima Modificacion - Temenos T24/spanConsultas de Cuenta'))

//Click en Consulta de modificaciones a cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/04 - Ultima Modificacion - Temenos T24/lnkConsulta de Modificaciones a Cuentas'))

//Swicht a la ventana Consulta de Modificaciones a Ctas
WebUI.switchToWindowTitle('Consulta de Modificaciones a Ctas')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

// Cambia a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

//Click en Consulta de modificaciones a cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/04 - Ultima Modificacion - Temenos T24/lnkConsulta de Modificaciones a Cuentas'))

//Swicht a la ventana Consulta de Modificaciones a Ctas
WebUI.switchToWindowTitle('Consulta de Modificaciones a Ctas')

// Maximizamos
WebUI.maximizeWindow()

//Ingresamos los datos para la consulta
WebUI.click(findTestObject('Object Repository/39-Cuentas2/Consulta de Modificaciones a Ctas/lnkNueva Seleccion'))

WebUI.setText(findTestObject('Object Repository/39-Cuentas2/Consulta de Modificaciones a Ctas/txtNroCuenta'), '01000395279')

WebUI.click(findTestObject('Object Repository/39-Cuentas2/Consulta de Modificaciones a Ctas/lnkEjecutar'))

//Seleccionamos del cbx la opcion Ultima Modificacion
WebUI.selectOptionByIndex(findTestObject('Object Repository/39-Cuentas2/Cons de mod a ctas - version actual/cbxVersion Actual'), 2)

//Click en btn Ejecutar
WebUI.click(findTestObject('Object Repository/39-Cuentas2/Cons de mod a ctas - version actual/btnEjecutar'))

//Verificamos el texto Cuentas Remuneradas Institucionales
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas2/CUENTAS - Vers Act/lblCuentasRemuneradasInstitucionales'), 6)

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

