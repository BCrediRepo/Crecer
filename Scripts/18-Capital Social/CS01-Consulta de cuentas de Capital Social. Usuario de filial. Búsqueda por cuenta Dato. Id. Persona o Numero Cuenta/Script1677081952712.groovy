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
//CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 4), findTestData('MainData/Users').getValue(
//        2, 4))
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 32), findTestData('MainData/Users').getValue(
        2, 32))

WebUI.maximizeWindow()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CS.VER.CTA')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//WebUI.switchToWindowTitle('Temenos T24')
//
//WebUI.click(findTestObject('02-Dashboard/lnkCapitalSocial'))
//
//WebUI.click(findTestObject('02-Dashboard/17-Capital Social/lnkConsultaDeCuentasDeCapitalSocial'))
//
//WebUI.click(findTestObject('02-Dashboard/17-Capital Social/lnkConsultasDeCapitalSocial'))
//
//WebUI.click(findTestObject('02-Dashboard/17-Capital Social/lnkCuentasCapitalSocialPorPersonaOCuenta'))
WebUI.switchToWindowTitle('Consulta Cuenta Capital Social')

//Aplico KYW de limpieza de busqueda
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

//Se accede al menu
WebUI.switchToWindowIndex(0)

WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CS.VER.CTA')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//WebUI.switchToWindowTitle('Temenos T24')
//
//WebUI.click(findTestObject('02-Dashboard/17-Capital Social/lnkCuentasCapitalSocialPorPersonaOCuenta'))
WebUI.switchToWindowTitle('Consulta Cuenta Capital Social')

//Descomentar para regresion
//WebUI.setText(findTestObject('19-Capital Social/Consulta Cuenta Capital Social/txtNroCuenta_value411'), '90890008453')
WebUI.setText(findTestObject('19-Capital Social/Consulta Cuenta Capital Social/txtIDPersona'), '1002190185')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//boton ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

WebUI.maximizeWindow()

WebUI.verifyElementVisible(findTestObject('19-Capital Social/Consulta Cuenta Capital Social/lblCuenta'), FailureHandling.STOP_ON_FAILURE)

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println(('Tiempo transcurrido: ' + elapsedTime) + ' milisegundos')

//---------------------------
//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))

TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))

println(TotalRegistros)

//-----------------------------
//Control de fin de script
//Descomentar para regresiion
//WebUI.verifyElementVisible(findTestObject('19-Capital Social/Consulta Cuenta Capital Social/lblCuenta'))
//
//cuenta = WebUI.getText(findTestObject('19-Capital Social/Consulta Cuenta Capital Social/lblCuenta'))
//
//assert cuenta == '90890008453'
WebUI.verifyElementVisible(findTestObject('19-Capital Social/Consulta Cuenta Capital Social/lblIDPersona'))

persona = WebUI.getText(findTestObject('19-Capital Social/Consulta Cuenta Capital Social/lblIDPersona'))

assert persona == '1002190185'

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

