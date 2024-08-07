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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
        2, 9))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.AC.COM.POR.DIS.SUC')

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'), FailureHandling.STOP_ON_FAILURE)

WebUI.switchToWindowTitle('Consulta de Comisiones Cobradas')

////Filtro para limpiar selección
//CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
//
//WebUI.switchToWindowIndex(0)
//
//WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.AC.COM.POR.DIS.SUC')

//CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
//
//WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'), FailureHandling.STOP_ON_FAILURE)
//
//WebUI.switchToWindowTitle('Consulta de Comisiones Cobradas')
//
//WebUI.waitForElementVisible(findTestObject('Object Repository/06-Comisiones/Consulta de Comisiones Cobradas/txtFechaProceso'), 
//    6)

//def variable = GlobalVariable.vFechaCOB
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Proceso', GlobalVariable.vFechaCOB)

//WebUI.setText(findTestObject('Object Repository/06-Comisiones/Consulta de Comisiones Cobradas/txtFechaProceso'), '20230828')

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//WebUI.delay(40)

//WebUI.waitForElementVisible(findTestObject('06-Comisiones/Consulta de Comisiones Cobradas/lblSucOrigenH'), 70)
SucOrigenH = WebUI.getText(findTestObject('06-Comisiones/Consulta de Comisiones Cobradas/lblSucOrigenH'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println(('Tiempo transcurrido: ' + elapsedTime) + ' milisegundos')

//---------------------------
//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))

TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))

println(TotalRegistros)

//-----------------------------
SucOrigenT = WebUI.getText(findTestObject('06-Comisiones/Consulta de Comisiones Cobradas/lblSucOrigenT'))

SucDestinoH = WebUI.getText(findTestObject('06-Comisiones/Consulta de Comisiones Cobradas/lblSucDestinoH'))

SucDestinoT = WebUI.getText(findTestObject('06-Comisiones/Consulta de Comisiones Cobradas/lblSucDestinoT'))

assert SucOrigenH != null

assert SucOrigenT != null

assert SucDestinoH != null

assert SucDestinoT != null

WebUI.maximizeWindow( //---------------------------------------------------------------------------------------------------------------------
    ) //Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

