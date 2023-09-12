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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 4), findTestData('MainData/Users').getValue(
        2, 4))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.AC.COM.BONIFICACION')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('%Bon Com por Cuenta o Sucursal')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

WebUI.switchToWindowIndex(0)

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.AC.COM.BONIFICACION')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('%Bon Com por Cuenta o Sucursal')

WebUI.setText(findTestObject('06-Comisiones/Bon Com por Cuenta o Sucursal/txtTipoComision'), 'GUVE')

//Bloque limpieza filtros (REVISAR)
//WebUI.click(findTestObject('null'))
//WebUI.click(findTestObject('null'))
//WebUI.closeBrowser()
//WebUI.switchToWindowTitle('T24 - Fil.074 Caseros Centro')
//WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))
//WebUI.switchToWindowTitle('%Bon Com por Cuenta o Sucursal')
WebUI.setText(findTestObject('06-Comisiones/Bon Com por Cuenta o Sucursal/txtCuenta'), '00740025976')

WebUI.setText(findTestObject('06-Comisiones/Bon Com por Cuenta o Sucursal/txtFechaDesde'), '20210725')

WebUI.setText(findTestObject('06-Comisiones/Bon Com por Cuenta o Sucursal/txtFechaHasta'), '20220729')

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebUI.maximizeWindow()

TextoHeader = WebUI.getText(findTestObject('06-Comisiones/Bon Com por Cuenta o Sucursal/lblCuentaHeader'))

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

TextoDato = WebUI.getText(findTestObject('06-Comisiones/Bon Com por Cuenta o Sucursal/lblCuentaTabla'))

Bonificacion = WebUI.getText(findTestObject('06-Comisiones/Bon Com por Cuenta o Sucursal/lblBonificacion'))

WebUI.verifyTextPresent(Bonificacion, true)

assert TextoDato == TextoHeader /*WebUI.verifyMatch('TextoHeader', '00740025976', true)
//WebUI.verifyMatch('TextoDato', '00740025976', true)
WebUI.verifyElementAttributeValue(findTestObject('null'), '', '', 0)*/ //---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

