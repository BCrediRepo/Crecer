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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(2, 3))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click menu Personas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkPersonas'))

//Click en consulta
WebUI.click(findTestObject('Object Repository/02-Dashboard/29-Personas/Consultas/span_Consulta'))

//Click en Consulta Gral
WebUI.click(findTestObject('Object Repository/02-Dashboard/29-Personas/Consultas/Consulta General/spanConsulta Gral'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en Busqueda y Consulta General de personas Fisica
WebUI.click(findTestObject('Object Repository/02-Dashboard/29-Personas/Consultas/Consulta General/lnkBusqueda y consulta general Persona Fisica'))

//Switch a la ventana Consulta General de personas Fisica
WebUI.switchToWindowTitle('Consulta General de personas Fisica')

//Maximizamos
WebUI.maximizeWindow()

//Seteo de datos "Nro. Documento"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('No. Documento', '20144835')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Validacion
WebUI.waitForElementVisible(findTestObject('Object Repository/31-Personas/Consulta General de personas Fisica/lblNo.Documento'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/31-Personas/Consulta General de personas Fisica/lblNo.Documento'))

//Click en la opcion "Consulta de firma" del cbx
WebUI.waitForElementVisible(findTestObject('Object Repository/31-Personas/Consulta General de personas Fisica/cbxConsultaGralPersonaFisica'), 6)

WebUI.selectOptionByIndex(findTestObject('Object Repository/31-Personas/Consulta General de personas Fisica/cbxConsultaGralPersonaFisica'), 5)

WebUI.click(findTestObject('Object Repository/31-Personas/Consulta General de personas Fisica/btnSelectDrillDown'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/31-Personas/Consulta General de personas Fisica/lblNumeroDocumento'),6)
def element = WebUI.getText(findTestObject('Object Repository/31-Personas/Consulta General de personas Fisica/lblNumeroDocumento'))
assert element.contains('20144835')

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