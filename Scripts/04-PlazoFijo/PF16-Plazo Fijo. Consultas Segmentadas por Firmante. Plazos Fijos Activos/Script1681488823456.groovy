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
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(
        2, 1))

WebUI.maximizeWindow()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?36')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('Object Repository/02-Dashboard/03-PlazoFijo/Temenos T24/lnkPlazoFij'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/03-PlazoFijo/Temenos T24/Plazo Fijo/lnkConsultasPlazoFijo'))


WebUI.click(findTestObject('02-Dashboard/03-PlazoFijo/Temenos T24/Plazo Fijo/lnkConsultasSegmentadasPorFirmantes'))

WebUI.click(findTestObject('02-Dashboard/03-PlazoFijo/Temenos T24/Plazo Fijo/Consulta de plazo fijo/lnkPlazosFijosActivos'))

WebUI.switchToWindowTitle('Activos Segmentados por Persona')

CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('02-Dashboard/03-PlazoFijo/Temenos T24/Plazo Fijo/Consulta de plazo fijo/lnkPlazosFijosActivos'))

WebUI.switchToWindowTitle('Activos Segmentados por Persona')

WebUI.setText(findTestObject('05-PlazoFijo/Acreditados en Cuenta Segmentado/txtDNI_value211'), '12633779')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebUI.waitForElementVisible(findTestObject('05-PlazoFijo/Activos Segmentados por Persona/lnkVerPlazoFijo'), 4)

WebUI.verifyElementVisible(findTestObject('05-PlazoFijo/Activos Segmentados por Persona/lnkVerPlazoFijo'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

WebUI.click(findTestObject('05-PlazoFijo/Activos Segmentados por Persona/lnkVerPlazoFijo'))

WebUI.switchToWindowTitle('PLAZO FIJO')

FormPF = WebUI.verifyElementVisible(findTestObject('05-PlazoFijo/PLAZO FIJO/formPlazoFijo'))

assert FormPF == true

WebUI.click(findTestObject('05-PlazoFijo/PLAZO FIJO/lnkVolverConsulta'))

WebUI.switchToWindowTitle('Activos Segmentados por Persona')

WebUI.waitForElementVisible(findTestObject('05-PlazoFijo/Activos Segmentados por Persona/lnkVerTitular'), 5)

WebUI.click(findTestObject('05-PlazoFijo/Activos Segmentados por Persona/lnkVerTitular'))

WebUI.switchToWindowTitle('ARCHIVOS PERSONAS')

formTitular = WebUI.verifyElementVisible(findTestObject('05-PlazoFijo/ARCHIVOS PERSONAS/formTitular'))

assert formTitular == true

WebUI.maximizeWindow()

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

