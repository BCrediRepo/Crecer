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
import java.text.SimpleDateFormat
import java.util.Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(2, 3))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Setear "ENQ BCCL.E.RES.CTA.MOV.FECHA.VALOR" en el buscador 
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.RES.CTA.MOV.FECHA.VALOR')

//Seleccionar boton buscar
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

////Click en cuentas
//WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))
//
////Click en Consultas de cuentas
//WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkConsultasdeCuentas'))
//
////Screenshot
//CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
//
////Click en Consulta de Mov. por fecha valor
//WebUI.click(findTestObject('Object Repository/02-Dashboard/16-Resumen de Cuentas/lnkConsulta de Mov. por Fecha Valor'))

//Cambiar a la ventana "Movimientos de Ctas por Fecha Valor"
WebUI.switchToWindowIndex(1)

//Seteo de Datos "Nro de Cuenta", "Fecha Desde"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro de Cuenta','00430014075')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Desde','20220727')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verificar si se ve el texto "Nro de Cuenta"
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/Movimientos de Ctas por Fecha Valor/lblNrodeCuenta'))

//Capturar tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//Validar que aparezca en el encabezado el texto "Nro de cuenta"
def nroCuenta = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/Movimientos de Ctas por Fecha Valor/lblNrodeCuenta'))
assert nroCuenta.contains('Nro. de Cuenta')

//Validar que aparezca la columna "Fec Valor"
def fecValor = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/Movimientos de Ctas por Fecha Valor/lblFecValor'))
assert fecValor.contains('Fec Valor')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}