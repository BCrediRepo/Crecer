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

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 4), findTestData('MainData/Users').getValue(
        2, 4))

WebUI.maximizeWindow()

//Toma Screen
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresa el ENQ en el Buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.AC.COM.BONIFICACION')

//Clickea en el btn "Ejecutar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambia a ventana nueva
WebUI.switchToWindowTitle('%Bon Com por Cuenta o Sucursal')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

WebUI.switchToWindowIndex(0)

//Ingresa el ENQ en el Buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.AC.COM.BONIFICACION')

//Clickea en el btn "Ejecutar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambia a ventana nueva
WebUI.switchToWindowTitle('%Bon Com por Cuenta o Sucursal')

//Ingresar Cuenta "00010021329"
WebUI.setText(findTestObject('06-Comisiones/Comision Bonificaciones/txtCuenta'), '00010021329')

//Ingresar Fecha Desde
WebUI.setText(findTestObject('06-Comisiones/Comision Bonificaciones/txtFechaDesde'), '20180427')

//Ingresar Fecha Hasta
WebUI.setText(findTestObject('06-Comisiones/Comision Bonificaciones/txtFechaHasta'), '20220725')

//Ingresar Sucursal "001"
WebUI.setText(findTestObject('06-Comisiones/Comision Bonificaciones/txtSucursal'), '001')

//Toma Screen
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click Ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Toma Screen
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Assert Cuenta

WebUI.waitForElementVisible(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblCuenta1'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblCuenta1'))

def element0 = WebUI.getText(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblCuenta1'))

assert element0.contains('00010021329') == true

//Assert Fecha Desde
WebUI.waitForElementVisible(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblFechaDes'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblFechaDes'))

def element1 = WebUI.getText(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblFechaDes'))

assert element1.contains('27 ABR 2021') == true

//Asserts Fecha Hasta
WebUI.waitForElementVisible(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblFechaHasta'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblFechaHasta'))

def element2 = WebUI.getText(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblFechaHasta'))

assert element2.contains('25 OCT 2021') == true 

//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

