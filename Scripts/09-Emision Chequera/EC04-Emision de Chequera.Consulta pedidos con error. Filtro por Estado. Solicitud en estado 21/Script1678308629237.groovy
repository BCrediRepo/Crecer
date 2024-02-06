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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 32), findTestData('MainData/Users').getValue(
        2, 32))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se accede al menu Administracion de piezas
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lnkChequeras'), 6)

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkChequeras'))

WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/lnkConsulta'), 6)

WebUI.click(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/lnkConsulta'))

WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/01-Consulta/lnkConsultaConfdeChequerasconErrores(21)'), 
    6)

WebUI.click(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/01-Consulta/lnkConsultaConfdeChequerasconErrores(21)'))

//Switch a la ventana de chequeras con errores
WebUI.switchToWindowTitle('Solicitudes Chequeras con Errores')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

WebUI.switchToWindowIndex(0)

WebUI.click(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/01-Consulta/lnkConsultaConfdeChequerasconErrores(21)'))

//Switch a la ventana de chequeras con errores
WebUI.switchToWindowTitle('Solicitudes Chequeras con Errores')

WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/04-Solicitudes Chequeras con Errores/txtSucursal'), 
    6)


//Descomentar esta linea para el caso de regresion 
WebUI.setText(findTestObject('Object Repository/11-Emision Chequera/04-Solicitudes Chequeras con Errores/txtSucursal'), findTestData('MainData/Users').getValue(3,9))

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//boton ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Resultados de la Sucursal
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/04-Solicitudes Chequeras con Errores/lblSUCURSAL'), 
    6)

WebUI.verifyElementVisible(findTestObject('Object Repository/11-Emision Chequera/04-Solicitudes Chequeras con Errores/lblSUCURSAL'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println(('Tiempo transcurrido: ' + elapsedTime) + ' milisegundos')

//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))

TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))

println(TotalRegistros)

//-----------------------------
def element = WebUI.getText(findTestObject('Object Repository/11-Emision Chequera/04-Solicitudes Chequeras con Errores/lblSUCURSAL'))

assert element.contains('SUCURSAL') 

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

