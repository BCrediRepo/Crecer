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
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
//CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 24), findTestData('MainData/Users').getValue(2, 24))

WebUI.maximizeWindow()

//Se accede al menu
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)

WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.AC.CONSULTA.NOMINA')

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//WebUI.click(findTestObject('Object Repository/02-Dashboard/spanCuentas'))
//
//WebUI.click(findTestObject('Object Repository/02-Dashboard/23-Cierre de Cuenta/lnkCierreDeCuenta'))
//
//WebUI.click(findTestObject('Object Repository/02-Dashboard/23-Cierre de Cuenta/01-Cierre de cuenta/lnkNomina'))
//
//WebUI.click(findTestObject('Object Repository/02-Dashboard/23-Cierre de Cuenta/01-Cierre de cuenta/01-Nomina/lnkConsultaNomina'))
WebUI.switchToWindowTitle('BCCL.AC.CONSULTA.NOMINA')

WebUI.maximizeWindow()

//Seteo de Datos "No. Cuenta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('No. Cuenta','00010056136')

//WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/txtNumeroDeCuenta'), '00010070802')
WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/txtNumeroDeCuenta'), '00010056136')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

WebUI.maximizeWindow()

WebUI.verifyElementVisible(findTestObject('25-Cierre de Cuenta/01-BCCL.AC.CONSULTA.NOMINA/lnkConsultaNominaCuenta'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println(('Tiempo transcurrido: ' + elapsedTime) + ' milisegundos')

//---------------------
//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))

TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))

println(TotalRegistros)

//-----------------------------
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/01-BCCL.AC.CONSULTA.NOMINA/lnkConsultaNominaCuenta'))

WebUI.waitForElementVisible(findTestObject('Object Repository/25-Cierre de Cuenta/02-BCCL.NOMINA.CH/lblAltaModif Nomina'), 
    6) //Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

