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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 8), findTestData('MainData/Users').getValue(2, 8))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ejecutar en la linea de comando "ENQ BCCL.E.RES.CTA.MOV.CER"
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("ENQ BCCL.E.RES.CTA.MOV.CER", 1)

//Cambiar a la ventana "Movimientos Cuenta Cerrada"
WebUI.switchToWindowIndex(1)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro. Cuenta','03645259635')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Desde','20220707')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Hasta',GlobalVariable.vFechaCOB)

//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verificar Estado de la Cuenta
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/05-Movimientos Cuenta Cerrada/lblEstado'))

//Validar Estado de la Cuenta
def estadoCuenta = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/05-Movimientos Cuenta Cerrada/lblEstado'))
assert estadoCuenta.contains('CERRADA')

//Verificar que aparezca en el encabezado "Fecha Estado"
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/05-Movimientos Cuenta Cerrada/lblFechaEstado'))

//Validar Fecha Estado
def fechaEstado = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/05-Movimientos Cuenta Cerrada/lblFechaEstado'))
assert fechaEstado.contains('Fecha Estado')

//Verificar que aparezca en el encabezado "Fecha Cierre"
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/05-Movimientos Cuenta Cerrada/lblFechaCierre'))

//Validar Fecha Cierre
def fechaCierre = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/05-Movimientos Cuenta Cerrada/lblFechaCierre'))
assert fechaCierre.contains('Fecha Cierre')

//Validar primer valor de la columna Fecha
primeraFecha = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/05-Movimientos Cuenta Cerrada/lblValorFecha'))
assert primeraFecha.contains('07 JUL 2022')

//Capturar tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//-----------------------------------------------------------------------------------------------------------------------------------------------

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}