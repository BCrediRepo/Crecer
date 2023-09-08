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
import java.text.SimpleDateFormat
import java.util.Date


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 8), findTestData('MainData/Users').getValue(2, 8))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se accede al menu
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.RES.CTA.MOV.FECHA')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Swicht a la ventana Movimientos por fecha de cuentas
WebUI.switchToWindowTitle('Movimientos por Fecha de Cuentas')

//Maximizamos
WebUI.maximizeWindow()

//Aplico KYW de limpieza de busqueda
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

//Switch a la ventana de busqueda de consulta
WebUI.switchToWindowTitle('T24 - Fil.102 Gerencia Operat')
WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.RES.CTA.MOV.FECHA')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Swicht a la ventana Movimientos por fecha de cuentas
WebUI.switchToWindowTitle('Movimientos por Fecha de Cuentas')
WebUI.maximizeWindow()

//Completamos los datos para la consulta
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/06-Movimientos por fecha de cuentas/lnkNueva Seleccion'))
WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/06-Movimientos por fecha de cuentas/txtNroDeCuenta'), '10430033951')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Boton ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/06-Movimientos por fecha de cuentas/lblFechaEstado'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/06-Movimientos por fecha de cuentas/lblFechaEstado'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

def element = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/06-Movimientos por fecha de cuentas/lblFechaEstado'))

assert element.contains('Fecha Estado')

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