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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 2), findTestData('MainData/Users').getValue(2, 2))

//Setear "ENQ BCCL.E.RES.CTA.MOV.CER" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.RES.CTA.MOV.CER')

//Seleccionar boton buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana "Movimientos Cuenta Cerrada"
WebUI.switchToWindowIndex(1)

//Seteo de Datos "Nro. Cuenta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro. Cuenta','01000021927')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Desde','20220901')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Hasta',GlobalVariable.vFechaCOB)

//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebUI.verifyElementVisible(findTestObject('18-Resumen de Cuenta/05-Movimientos Cuenta Cerrada/lblCuenta'))

//Capturar tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println(('Tiempo transcurrido: ' + elapsedTime) + ' milisegundos')

//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))
TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))
println(TotalRegistros)

//Validar numero de cuenta, Estado, Fecha Estado y Fecha Cierre
cuenta = WebUI.getText(findTestObject('18-Resumen de Cuenta/05-Movimientos Cuenta Cerrada/lblCuenta'))
assert cuenta.contains('01000021927')
estado = WebUI.getText(findTestObject('18-Resumen de Cuenta/05-Movimientos Cuenta Cerrada/lblEstadoCerrado'))
assert estado.contains('CERRADA') 
fechaEstado = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/05-Movimientos Cuenta Cerrada/lblFechaEstado'))
assert fechaEstado.contains('Fecha Estado')
fechaCierre = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/05-Movimientos Cuenta Cerrada/lblFechaCierre'))
assert fechaCierre.contains('Fecha Cierre')
//---------------------------------------------------------------------------------------------------------------------
//Control Fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}