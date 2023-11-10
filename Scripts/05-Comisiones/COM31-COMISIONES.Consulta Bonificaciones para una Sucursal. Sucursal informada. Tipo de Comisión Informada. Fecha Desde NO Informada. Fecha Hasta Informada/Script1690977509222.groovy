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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject


//Scrip Log-In
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 4), findTestData('MainData/Users').getValue(
		2, 4))

WebUI.maximizeWindow()


CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se busca el ENQ 
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.AC.COM.BONIFICACION')


WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))


WebUI.switchToWindowTitle('%Bon Com por Cuenta o Sucursal')


CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()


WebUI.switchToWindowIndex(0)


WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.AC.COM.BONIFICACION')


WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))


WebUI.switchToWindowTitle('%Bon Com por Cuenta o Sucursal')

//Se completan los filtros

WebUI.setText(findTestObject('06-Comisiones/Comision Bonificaciones/txtTipoComision'), 'ECHQJ')


WebUI.setText(findTestObject('06-Comisiones/Comision Bonificaciones/txtFechaHasta'), '20200630')


WebUI.setText(findTestObject('06-Comisiones/Comision Bonificaciones/txtSucursal'), '001')

//Se toma un screen
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Se ejecuta
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))


CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()


//ASSERT

//Assert SUCURSAL
WebUI.waitForElementVisible(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblSucursal001'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblSucursal001'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")
//---------------------------

//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))

TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))

println TotalRegistros
//-----------------------------

def element = WebUI.getText(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblSucursal001'))

assert element.contains('001') == true

//Assert Comision
WebUI.waitForElementVisible(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblTipoComision'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblTipoComision'))

def element0 = WebUI.getText(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblTipoComision'))

assert element0.contains('Com Echq C. Int') == true

//Assert Fecha
WebUI.waitForElementVisible(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblFechaHasta'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblFechaHasta'))

def element2 = WebUI.getText(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblFechaHasta'))



assert element2.contains('30 JUN 2020') == true 

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

