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
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(2, 3))

WebUI.maximizeWindow()

//Ingreso al menu plazo fijo
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkPlazoFijo'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/03-PlazoFijo/lnkConsultasdePlazoFijo'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/03-PlazoFijo/02-Consultas de Plazo Fijo/lnkConsultasporPlazoFijo'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/03-PlazoFijo/02-Consultas de Plazo Fijo/02-Consulta por Plazo Fijo/lnkPlazosFijosActivos'))

WebUI.switchToWindowTitle('Plazo Fijos Activos')

//Seteo de Datos "Sucursal", "Moneda", "Nro. Operacion"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro. Operacion', '14094811')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '089')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Moneda', 'ARS')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//boton ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println(('Tiempo transcurrido: ' + elapsedTime) + ' milisegundos')

WebUI.click(findTestObject('Object Repository/05-PlazoFijo/07-Plazos Fijos Activos/lnkVerPlazoFijo'))

WebUI.switchToWindowTitle('PLAZO FIJO')

WebUI.verifyElementPresent(findTestObject('Object Repository/05-PlazoFijo/07-Plazos Fijos Activos/lblIMPOSICION'), 6) //---------------------------------------------------------------------------------------------------------------------

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'('Screenshot/Fails/CDC01Error.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

