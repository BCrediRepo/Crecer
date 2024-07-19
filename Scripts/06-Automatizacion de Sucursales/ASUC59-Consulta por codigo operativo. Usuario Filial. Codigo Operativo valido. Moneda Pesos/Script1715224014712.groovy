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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,31), findTestData('MainData/Users').getValue(2,31))
WebUI.maximizeWindow()

//Ingresamos el menu ?1 en el buscador
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?1')
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

//Maximizamos
WebUI.maximizeWindow()

//Click en sucursal piloto
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkSucursalPiloto'))

//Click en D2 automatizacion de sucursales
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkD2AutomatizaciondeSucursales'))

//Click en consultas operatorias de filiales
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkCONSULTASOPERATORIASDEFILIALES'))

//Click en detalle de operaciones
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkDETALLEDEOPERACIONES'))

//Click en consulta por codigo operativo
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Detalle de operaciones/lnkCONSULTA POR CODIGO OPERATIVO'))

//Switch a la ventana Consulta Por Codigo Operativo
WebUI.switchToWindowTitle('Consulta Por Codigo Operativo')

//Maximizamos
WebUI.maximizeWindow()

//Limpio campos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizamos
WebUI.maximizeWindow()

//Ingresamos el cod operativo
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Consulta Por Codigo Operativo/txtCodigoOperativo'), '00101')

//Ingresamos la moneda
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Consulta Por Codigo Operativo/txtMoneda'), 'ARS')

//Ingresamos el codigo sucursal
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Consulta Por Codigo Operativo/txtCodSucursal'), '001')

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Consulta Por Codigo Operativo/lblMonto'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Consulta Por Codigo Operativo/lblMonto'))
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Consulta Por Codigo Operativo/lblMonto'))
assert element.contains('Monto')

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
