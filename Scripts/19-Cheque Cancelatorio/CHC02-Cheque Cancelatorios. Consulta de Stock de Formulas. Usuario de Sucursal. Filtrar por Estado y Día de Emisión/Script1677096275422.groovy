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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(2, 3))
WebUI.maximizeWindow()

// Accedo al menu ?403
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscar'), "?403")

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkChequesCertificados'))

WebUI.click(findTestObject('Object Repository/20-Cheque Cancelatorio/lnkFormulasDeCertificacion'))

WebUI.click(findTestObject('Object Repository/20-Cheque Cancelatorio/lnkReporteInvFormDeCertificacion'))

WebUI.switchToWindowTitle('Control Stock Certificacion Cheques')

//Aplico KYW de limpieza de busqueda
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowTitle('Temenos T24')
WebUI.click(findTestObject('Object Repository/20-Cheque Cancelatorio/lnkReporteInvFormDeCertificacion'))
WebUI.switchToWindowTitle('Control Stock Certificacion Cheques')

WebUI.setText(findTestObject('Object Repository/20-Cheque Cancelatorio/01-Reporte Inv Form de Certificacion/txtSUCURSAL'), '089')

WebUI.setText(findTestObject('Object Repository/20-Cheque Cancelatorio/01-Reporte Inv Form de Certificacion/txtNUMERO DE CUENTA'), '00890172593')

WebUI.setText(findTestObject('Object Repository/20-Cheque Cancelatorio/01-Reporte Inv Form de Certificacion/txtNUMERO DE CHEQUE'), '22182765')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Boton ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

WebUI.waitForElementVisible(findTestObject('Object Repository/20-Cheque Cancelatorio/01-Reporte Inv Form de Certificacion/lblDIADEINGRESO'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/20-Cheque Cancelatorio/01-Reporte Inv Form de Certificacion/lblDIADEINGRESO'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

def element = WebUI.getText(findTestObject('Object Repository/20-Cheque Cancelatorio/01-Reporte Inv Form de Certificacion/lblDIADEINGRESO'))
assert element.contains('DIA DE INGRESO')

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