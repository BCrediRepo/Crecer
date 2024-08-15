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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(
        2, 3))

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.MONEX.OPER.IN')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Ingreso de parametros generales de la operacion de Moneda Extranjera')

//Seteo datos
WebUI.setText(findTestObject('15-MONEX/Ingreso de parametros generales de la operacion de Moneda Extranjera/txtMonedaExtranjera'), 
    'USD')

WebUI.setText(findTestObject('15-MONEX/Ingreso de parametros generales de la operacion de Moneda Extranjera/txtTipoOperacion'), 
    'Socio Compra')

WebUI.setText(findTestObject('15-MONEX/Ingreso de parametros generales de la operacion de Moneda Extranjera/txtTipoCotizacion'), 
    'V')

WebUI.setText(findTestObject('15-MONEX/Ingreso de parametros generales de la operacion de Moneda Extranjera/txtIngresoFondos'), 
    'CUENTA')

WebUI.setText(findTestObject('15-MONEX/Ingreso de parametros generales de la operacion de Moneda Extranjera/txtEgresoFondos'), 
    'CUENTA')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//----------------------------
//Inicio Busqueda de consulta
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Resultados
WebUI.verifyElementVisible(findTestObject('15-MONEX/Ingreso de parametros generales de la operacion de Moneda Extranjera/lblEspecial'))

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
Cotizacion = WebUI.getText(findTestObject('15-MONEX/Ingreso de parametros generales de la operacion de Moneda Extranjera/lblEspecial'))

//Verificacion
assert Cotizacion == 'V - Vigente'

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}
