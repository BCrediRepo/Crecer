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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 2), findTestData('MainData/Users').getValue(2, 2))
WebUI.maximizeWindow()

def menuDesplegable = ["Cuentas"]
def link = "Consulta de Cuentas por Cuenta"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Cambiar ventana "Consulta de Cuentas por Cuenta"
WebUI.switchToWindowTitle('Consulta de Cuentas por Cuenta')

//Seteo de datos "Nro. Cuenta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro. Cuenta', '00890012958')

//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verificar "Denominacion"
WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/Consulta de Cuentas por Cuenta/lblDenominacion'))

//Capturar tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime
println(('Tiempo transcurrido: ' + elapsedTime) + ' milisegundos')

//Conteo registros
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/02-Filtros/lblResultados'))
TotalRegistros = WebUI.getText(findTestObject('Object Repository/00-Utils/02-Filtros/lblResultados'))
println(TotalRegistros)

//Seleccionar Boton Drill Down
WebUI.click(findTestObject('Object Repository/39-Cuentas/Consulta de Cuentas por Cuenta/btnDrilldown'))

//Validar "ALTA"
altaCuenta = WebUI.getText(findTestObject('Object Repository/39-Cuentas/Consulta de Cuentas por Cuenta/lblALTA'))
assert altaCuenta == "ALTA"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}