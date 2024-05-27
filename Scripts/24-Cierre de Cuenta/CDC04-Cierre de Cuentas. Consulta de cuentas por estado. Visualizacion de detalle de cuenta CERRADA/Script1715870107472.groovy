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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
        2, 9))

//Se accede al menu
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.AC.CONSULTA.X.ESTADO')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//WebUI.click(findTestObject('02-Dashboard/lnkCuentas'))
//
//WebUI.click(findTestObject('02-Dashboard/23-Cierre de Cuenta/lnkConsultasdeCuentas'))
//
//WebUI.click(findTestObject('02-Dashboard/23-Cierre de Cuenta/Consulta de Cuentas/lnkConsultadeCuentasporEstado'))
//
WebUI.switchToWindowTitle('BCCL.AC.CONSULTA.X.ESTADO')

//Aplico KYW de limpieza de busqueda
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

//Se accede al menu
WebUI.switchToWindowIndex(0)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.AC.CONSULTA.X.ESTADO')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//WebUI.click(findTestObject('02-Dashboard/23-Cierre de Cuenta/Consulta de Cuentas/lnkConsultadeCuentasporEstado'))

WebUI.switchToWindowTitle('BCCL.AC.CONSULTA.X.ESTADO')

WebUI.setText(findTestObject('25-Cierre de Cuenta/BCCL.AC.CONSULTA.X.ESTADO/txtClienteID'), '1000873562')

WebUI.setText(findTestObject('25-Cierre de Cuenta/BCCL.AC.CONSULTA.X.ESTADO/txtEstado'), 'CER')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//boton ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebUI.verifyElementVisible(findTestObject('25-Cierre de Cuenta/BCCL.AC.CONSULTA.X.ESTADO/lblClienteID'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

ID = WebUI.getText(findTestObject('25-Cierre de Cuenta/BCCL.AC.CONSULTA.X.ESTADO/lblClienteID'))

Estado = WebUI.getText(findTestObject('25-Cierre de Cuenta/BCCL.AC.CONSULTA.X.ESTADO/lblEstado'))

assert ID == '1000873562'

assert Estado == 'CER'

WebUI.maximizeWindow()

//---------------------------------------------------------------------------------------------------------------------

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

