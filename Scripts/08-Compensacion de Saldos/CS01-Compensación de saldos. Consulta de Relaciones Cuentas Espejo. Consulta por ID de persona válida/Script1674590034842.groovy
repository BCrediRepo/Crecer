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
import java.text.SimpleDateFormat
import java.util.Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 4), findTestData('MainData/Users').getValue(
        2, 4))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lknCompensaciondeSaldos'), 6)

WebUI.click(findTestObject('Object Repository/02-Dashboard/lknCompensaciondeSaldos'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/07-Compensacion de Saldos/lknConsultas'))

WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/07-Compensacion de Saldos/01-Consultas/lnkConsultadeRelaciones'), 6)

WebUI.click(findTestObject('Object Repository/02-Dashboard/07-Compensacion de Saldos/01-Consultas/lnkConsultadeRelaciones'))

WebUI.switchToWindowTitle('BCCL.E.ACCP.GROUP')

//Aplico KYW de limpieza de busqueda
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowTitle('T24 - Fil.043 Villa Mitre')
WebUI.click(findTestObject('Object Repository/02-Dashboard/07-Compensacion de Saldos/01-Consultas/lnkConsultadeRelaciones'))
WebUI.switchToWindowTitle('BCCL.E.ACCP.GROUP')

WebUI.setText(findTestObject('Object Repository/09-Compensacion de Saldos/01-Consulta de Relaciones/txtIDcuenta'), '00895279268')

WebUI.setText(findTestObject('Object Repository/09-Compensacion de Saldos/01-Consulta de Relaciones/txtTipoRegla'), 'ESP')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//boton ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebUI.verifyElementPresent(findTestObject('Object Repository/09-Compensacion de Saldos/01-Consulta de Relaciones/lblCtaEspejo'), 6) 

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'('Screenshot/Fails/CDC01Error.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

