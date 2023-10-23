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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando ENQ BCCL.E.TIP.COMISION
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.TIP.COMISION')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña Tipos de Comision
WebUI.switchToWindowTitle('Tipos de Comision')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Ejecuta en la linea de comando ENQ ENQ BCCL.E.TIP.COMISION
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.TIP.COMISION')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Abre la pestaña Tipos de Comision
WebUI.switchToWindowTitle('Tipos de Comision')

//Espera titulo Tipos de Comision
WebUI.waitForElementVisible(findTestObject('Object Repository/06-Comisiones/Tipos de Comision/lblTiposdeComision'),5)

//Ingresa TIPO De Comision
WebUI.waitForElementVisible(findTestObject('Object Repository/06-Comisiones/Tipos de Comision/txtID'), 6)
WebUI.setText(findTestObject('Object Repository/06-Comisiones/Tipos de Comision/txtID'), 'BUZATH')

//Selecciona boton EJECUTAR
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Espera y Verifica titulo de mantenimiento de comisiones
WebUI.waitForElementVisible(findTestObject('Object Repository/06-Comisiones/Tipos de Comision/lblMantenimientodeComisiones'),5)
WebUI.verifyElementVisible(findTestObject('Object Repository/06-Comisiones/Tipos de Comision/lblMantenimientodeComisiones'))

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//click en boton ver tipos de comisiones
WebUI.click(findTestObject('Object Repository/06-Comisiones/Tipos de Comision/btnVerTipoComision'))

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Espera y Verifica que se muestren las columnas del registro
WebUI.waitForElementVisible(findTestObject('Object Repository/06-Comisiones/COMMISSION TYPE/lblMANTENIMIENTO'),10)
WebUI.verifyElementVisible(findTestObject('Object Repository/06-Comisiones/COMMISSION TYPE/lblMANTENIMIENTO'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

def element = WebUI.getText(findTestObject('Object Repository/06-Comisiones/COMMISSION TYPE/lblMANTENIMIENTO'))
assert element.contains('MANTENIMIENTO')

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