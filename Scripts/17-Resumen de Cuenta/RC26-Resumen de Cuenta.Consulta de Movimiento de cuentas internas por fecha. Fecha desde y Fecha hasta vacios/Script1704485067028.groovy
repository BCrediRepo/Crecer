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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.DayOfWeek

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 31), findTestData('MainData/Users').getValue(2, 31))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ejecutar en la linea de comando "ENQ BCCL.E.RES.CTA.MOV.INT.FECHA"
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("ENQ BCCL.E.RES.CTA.MOV.INT.FECHA", 1)

//Cambiar a la ventana "Movimientos por Fecha Ctas Internas"
WebUI.switchToWindowIndex(1)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro de Cuenta','ARS1001070011001')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verificar que aparezca el texto "Nro. de Cuenta"
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Movimientos por Fecha Ctas Internas/lblNro. de Cuenta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/Movimientos por Fecha Ctas Internas/lblNro. de Cuenta'))

//Parsear FechaCOB
fecha = GlobalVariable.vFechaCOB
	
//Parsear la fecha de String a LocalDate
DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("yyyyMMdd")
LocalDate fechaParseo = LocalDate.parse(fecha, formatoEntrada)
		
//Convertir la fecha al nuevo formato dd-MM-yyyy
DateTimeFormatter formatoSalida = DateTimeFormatter.ofPattern("dd-MM-yyyy")
String fechaParseada = fechaParseo.format(formatoSalida)

//Validar que aparezca en el encabezado "Nro. de Cuenta"
def nroCuenta = WebUI.getText(findTestObject('Object Repository/39-Cuentas/Movimientos por Fecha Ctas Internas/lblNro. de Cuenta'))
assert nroCuenta.contains('Nro. de Cuenta')

//Validar que aparezca en el encabezado la Fecha COB
def fechaCob = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/Movimientos por Fecha Ctas Internas/lblSaldoAl'))
assert fechaCob.contains(fechaParseada)

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}