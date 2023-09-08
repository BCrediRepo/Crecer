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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(2, 9))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('02-Dashboard/lnkChequesCancelatorios'))

WebUI.click(findTestObject('02-Dashboard/18-Cheques Cancelatorios/spanConsultas'))

WebUI.click(findTestObject('02-Dashboard/18-Cheques Cancelatorios/01-Consultas/lnkConsultaMovimientoCtaInterna'))

WebUI.switchToWindowTitle('Consulta Mov de Cuentas Internas')

//Se aplica la limpieza de busqueda en vez de utilizar la keyword de limpieza

WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkNuevaSeleccion'))

WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

WebUI.switchToWindowTitle('BCCL.E.CHQ.CANCELA')

WebUI.click(findTestObject('20-Cheque Cancelatorio/BCCL.E.CHQ.CANCELA/btnLupa'))

WebUI.switchToWindowTitle('Consulta Mov de Cuentas Internas')

WebUI.setText(findTestObject('20-Cheque Cancelatorio/Consulta Mov de Cuentas Internas/txtCategoriaValue111'), '17403')

WebUI.setText(findTestObject('20-Cheque Cancelatorio/Consulta Mov de Cuentas Internas/txtMonedaValue211'), 'ARS')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//boton ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

WebUI.verifyElementClickable(findTestObject('20-Cheque Cancelatorio/Consulta Mov de Cuentas Internas/btnLargavista'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

WebUI.click(findTestObject('20-Cheque Cancelatorio/Consulta Mov de Cuentas Internas/btnLargavista'))

WebUI.delay(30)

WebUI.switchToWindowTitle('Account Statement')

WebUI.click(findTestObject('20-Cheque Cancelatorio/Account Statement/btnLargavistas'))

WebUI.switchToWindowTitle('Movimiento de Fondos')

WebUI.waitForElementVisible(findTestObject('20-Cheque Cancelatorio/Movimiento de Fondos/formTransaccion'), 12)

formulario = WebUI.verifyElementVisible(findTestObject('20-Cheque Cancelatorio/Movimiento de Fondos/formTransaccion'))

WebUI.maximizeWindow()

assert formulario == true


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

