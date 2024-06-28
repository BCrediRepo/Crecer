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

//Ejecuta en la linea de comando ENQ BCCL.E.PER.GEN.PF
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.PER.GEN.PF')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña Consulta General de personas Fisica
WebUI.switchToWindowTitle('Consulta General de personas Fisica')

//Verifica titulo //Abre la pestaña Consulta General de personas Fisica
WebUI.verifyElementVisible(findTestObject('Object Repository/31-Personas/Consulta General de personas Fisica/lblTituloConsulta General de personas Fisica'))

//Seteo de datos "Id Persona"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
WebUI.waitForElementVisible(findTestObject('Object Repository/31-Personas/Consulta General de personas Fisica/txtIdPersona'), 6)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Id Persona', '1002570602')

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Selecciona boton EJECUTAR
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Espera y Verifica que devuelva un registro
WebUI.waitForElementVisible(findTestObject('Object Repository/31-Personas/Consulta General de personas Fisica/lblPersonaID'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/31-Personas/Consulta General de personas Fisica/lblPersonaID'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

def element = WebUI.getText(findTestObject('Object Repository/31-Personas/Consulta General de personas Fisica/lblPersonaID'))
assert element.contains('Persona ID')

//---------------------------

//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))

TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))

println TotalRegistros
//-----------------------------

//Selecciona ver datos Personas fisicas
WebUI.click(findTestObject('Object Repository/31-Personas/Consulta General de personas Fisica/btnVerDatosPersonaFisica'))

//Abre la pestaña BCCL.E.PER.GEN.PF
WebUI.switchToWindowTitle('ARCHIVOS PERSONAS')

//Espera y Verifica titulo de la ventana de la registros
WebUI.waitForElementVisible(findTestObject('Object Repository/31-Personas/ARCHIVOS PERSONAS/lblIdentificaciondelaPersonaFisica'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/31-Personas/ARCHIVOS PERSONAS/lblIdentificaciondelaPersonaFisica'))
def element2 = WebUI.getText(findTestObject('Object Repository/31-Personas/ARCHIVOS PERSONAS/lblIdentificaciondelaPersonaFisica'))
assert element2.contains('Identificacion de la Persona Fisica')

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