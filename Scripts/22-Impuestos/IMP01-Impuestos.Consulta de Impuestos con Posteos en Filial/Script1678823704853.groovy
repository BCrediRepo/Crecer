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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,24), findTestData('MainData/Users').getValue(2,24))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Accedo a la ventana BCCL.E.IMPTOS.COBDEV.PLANTA

//Busqueda de aplicacion por ENQ
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.IMPTOS.COBDEV.PLANTA')
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Accedo al menu de Impuestos
//WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lnkImpuestos'), 6)
//WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkImpuestos'))
//WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/21-Impuestos/lnkOperacionesdeImpuestosPorDescripcion'), 6)
//WebUI.click(findTestObject('Object Repository/02-Dashboard/21-Impuestos/lnkOperacionesdeImpuestosPorDescripcion'))
//WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/21-Impuestos/01-Operaciones de Impuestos por Descripcion/lnkOperacionesdeImpuestosPorPosteo'), 6)
//WebUI.click(findTestObject('Object Repository/02-Dashboard/21-Impuestos/01-Operaciones de Impuestos por Descripcion/lnkOperacionesdeImpuestosPorPosteo'))

//Switch a la ventana de impuestos por Posteo
WebUI.switchToWindowTitle('BCCL.E.IMPTOS.COBDEV.PLANTA')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/01-Operaciones de Impuestos por Posteo/lnkVer'), 6)

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")


WebUI.click(findTestObject('Object Repository/23-Impuestos/01-Operaciones de Impuestos por Posteo/lnkVer'))

//Switch a la ventana de Movimiento de Fondos
WebUI.switchToWindowTitle('Movimiento de Fondos')
WebUI.maximizeWindow()
//WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/01-Operaciones de Impuestos por Posteo/lblTipoOperacion'), 6)
def element = WebUI.getText(findTestObject('Object Repository/23-Impuestos/01-Operaciones de Impuestos por Posteo/lblTipoOperacion'))
assert element.contains('Tipo Operacion')


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
