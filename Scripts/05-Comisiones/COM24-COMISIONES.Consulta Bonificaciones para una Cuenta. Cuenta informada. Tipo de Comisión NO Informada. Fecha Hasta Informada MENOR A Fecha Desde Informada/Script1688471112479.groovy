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

//Seteo de Datos "Cuenta", "Fecha Desde", "Fecha Hasta" "Sucursal"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cuenta', '00010021329')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Desde', '20180427')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Hasta', GlobalVariable.vFechaCOB)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '001')

//Toma Screen
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Click Ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Toma Screen
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Assert Cuenta

WebUI.waitForElementVisible(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblCuenta1'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/06-Comisiones/Comision Bonificaciones/lblCuenta1'))

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

