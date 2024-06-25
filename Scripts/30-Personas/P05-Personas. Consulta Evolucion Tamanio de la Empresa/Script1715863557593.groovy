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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,10), findTestData('MainData/Users').getValue(2,10))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Ingreso al menu ?302

//WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?302')
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.PER.EVOL.TAMEMP')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//WebUI.switchToWindowTitle('Temenos T24')
//
//WebUI.maximizeWindow()
//
//WebUI.click(findTestObject('Object Repository/02-Dashboard/spanPersonas3'))
//
//WebUI.click(findTestObject('Object Repository/02-Dashboard/29-Personas/spanConsulta'))
//
//WebUI.click(findTestObject('Object Repository/02-Dashboard/29-Personas/Consultas/span_Consulta general'))
//
//WebUI.click(findTestObject('Object Repository/02-Dashboard/29-Personas/Consultas/Consulta General/lnk Consulta Evolucion Tamao Empresa'))

WebUI.switchToWindowTitle('BCCL.PER.EVOL.TAMEMP')
WebUI.maximizeWindow()

//Seteo de datos "Id. Persona"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Id. Persona', '1000000011')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//boton ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

WebUI.waitForElementVisible(findTestObject('Object Repository/31-Personas/BCCL.PER.EVOL.TAMEMP/lblNroPersona'), 6)

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

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


