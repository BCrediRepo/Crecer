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

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 31), findTestData('MainData/Users').getValue(
        2, 31))

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.RES.CTA.MOV.FECHA')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Movimientos por Fecha de Cuentas')

//Seteo de Datos "Nro de Cuenta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro de Cuenta','00540468975')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Desde','20220722')

//----------------------------
// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebUI.waitForElementVisible(findTestObject('18-Resumen de Cuenta/06-Movimientos por fecha de cuentas/Movimientos por Fecha de Cuentas/lblFecha'),60)

WebUI.verifyElementVisible(findTestObject('18-Resumen de Cuenta/06-Movimientos por fecha de cuentas/Movimientos por Fecha de Cuentas/lblFecha'))

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
Fecha = WebUI.getText(findTestObject('18-Resumen de Cuenta/06-Movimientos por fecha de cuentas/Movimientos por Fecha de Cuentas/lblFecha'))

WebUI.verifyElementVisible(findTestObject('18-Resumen de Cuenta/06-Movimientos por fecha de cuentas/Movimientos por Fecha de Cuentas/lblFechaValor'))

FechaValor = WebUI.getText(findTestObject('18-Resumen de Cuenta/06-Movimientos por fecha de cuentas/Movimientos por Fecha de Cuentas/lblFechaValor') //Control fin de script
    )

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

