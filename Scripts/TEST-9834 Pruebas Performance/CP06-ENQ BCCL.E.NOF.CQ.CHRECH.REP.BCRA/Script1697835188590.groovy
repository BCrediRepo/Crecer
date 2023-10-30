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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,41), findTestData('MainData/Users').getValue(2,41))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando ENQ BCCL.E.CQ.CHRECH.REP.BCRA
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CQ.CHRECH.REP.BCRA')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana BCCL.E.CQ.CHRECH.REP.BCRA
WebUI.switchToWindowTitle('BCCL.E.CQ.CHRECH.REP.BCRA')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Swicht a la ventana principal
WebUI.switchToWindowIndex(0)
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana BCCL.E.CQ.CHRECH.REP.BCRA
WebUI.switchToWindowTitle('BCCL.E.CQ.CHRECH.REP.BCRA')

//Maximizamos
WebUI.maximizeWindow()

//Ingresamos los datos para la consulta
//WebUI.setText(findTestObject('Object Repository/TEST-9834 Pruebas Performance/BCCL.E.CQ.CHRECH.REP.BCRA/txtFecGenInfo'), '20220715')

//Ingresamos la sucursal
WebUI.setText(findTestObject('Object Repository/TEST-9834 Pruebas Performance/BCCL.E.CQ.CHRECH.REP.BCRA/txtSucursal'), '142')

//Ingresamos el numero de cuenta
WebUI.setText(findTestObject('Object Repository/TEST-9834 Pruebas Performance/BCCL.E.CQ.CHRECH.REP.BCRA/txtNumeroCuenta'), '01420142707')

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-9834 Pruebas Performance/BCCL.E.CQ.CHRECH.REP.BCRA/lblCuenta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-9834 Pruebas Performance/BCCL.E.CQ.CHRECH.REP.BCRA/lblCuenta'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

def element = WebUI.getText(findTestObject('Object Repository/TEST-9834 Pruebas Performance/BCCL.E.CQ.CHRECH.REP.BCRA/lblCuenta'))

assert element.contains('Cuenta')

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



