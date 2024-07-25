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

//Setear "?1" en el buscador
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?1')

//Seleccionar boton buscar
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana "Temenos T24"
WebUI.switchToWindowIndex(1)

//Seleccionar "Sucursal Piloto"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkSucursalPiloto'))

//Maximizar ventana
WebUI.maximizeWindow()

//Seleccionar "Resumen de Cuentas"
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/Temenos T24/spanResumen de Cuentas'))

//Seleccionar "Consultas"
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/Temenos T24/spanConsultas'))

//Seleccionar "Saldo de Cuentas"
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/Temenos T24/lnkSALDO DE CUENTAS'))

//Cambiar a la ventana "Saldos de Cuenta"
WebUI.switchToWindowIndex(2)

//Ingresar la cuenta a consultar
WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/Saldos de Cuenta/txtCuenta'), '00010067819')

//Maximizar ventana
WebUI.maximizeWindow()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verificar que aparezca que la moneda es en pesos argentinos
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/Saldos de Cuenta/lblMonedaARS'))

//Validar "ARS PESOS ARGENTINOS"
def monedaARS = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/Saldos de Cuenta/lblMonedaARS'))
assert monedaARS.contains('ARS PESOS ARGENTINOS')

//Verificar estado
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/Saldos de Cuenta/lblEstadoActiva'))

//Validar "ACTIVA"
def estadoACTIVA = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/Saldos de Cuenta/lblEstadoActiva'))
assert estadoACTIVA.contains('ACTIVA')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}