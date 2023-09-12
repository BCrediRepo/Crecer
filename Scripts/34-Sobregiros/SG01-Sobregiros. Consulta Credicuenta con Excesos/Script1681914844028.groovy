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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(
        2, 3))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingreso en la linea de comando ENQ BCCL.E.ACDOS
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.ACDOS')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('BCCL.E.ACDOS')

WebUI.maximizeWindow()

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

WebUI.switchToWindowTitle('T24 - Fil.089 M.del Plata Ctr')

WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.ACDOS')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('BCCL.E.ACDOS')

WebUI.setText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS/txtFechaDesde'), '20220718')

WebUI.setText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS/txtFechaHasta'), '20220725')

WebUI.setText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS/txtEstado'), 'AC')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebUI.maximizeWindow()

WebUI.waitForElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS/lblCuenta'), 6)

WebUI.verifyElementVisible(findTestObject('35-Sobregiros/BCCL.E.ACDOS/lblCuenta'), FailureHandling.STOP_ON_FAILURE) 

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

