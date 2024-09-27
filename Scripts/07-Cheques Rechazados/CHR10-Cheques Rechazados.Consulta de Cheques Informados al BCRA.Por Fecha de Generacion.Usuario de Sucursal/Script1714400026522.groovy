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
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

def suc = '019'
def fecha = '20240116'
def cuenta = '00190239651'
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 38), findTestData('MainData/Users').getValue(2, 38))
WebUI.maximizeWindow()

//Ingresar "308" en el buscador
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'('?308', 1)
def menuDesplegable = ["Rechazo de cheques", "Operatoria con BCRA"]
def link = "Consulta de Cheques Informados al BCRA"

//si el menú que busco está en Temenos T24, uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Cambiar ventana "BCCL.E.CQ.CHRECH.REP.BCRA"
WebUI.switchToWindowTitle('BCCL.E.CQ.CHRECH.REP.BCRA')

//Seteo de Datos "Sucursal", "Fec Gen Info" "Numero Cuenta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', suc)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fec Gen Info', fecha)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Numero Cuenta', cuenta)

//Seleccionar Boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Conteo de registros
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/02-Filtros/lblResultados'))
TotalRegistros = WebUI.getText(findTestObject('Object Repository/00-Utils/02-Filtros/lblResultados'))
println(TotalRegistros)

//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton drilldown
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/btnSelectDrilldown'))

//Cambiar ventana "BCCL.CQ.CHRECH.REP.BCRA"
WebUI.switchToWindowTitle('BCCL.CQ.CHRECH.REP.BCRA')

//Verificar "Si" de Ind Envio BCRA 1
WebUI.verifyElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lblSI'))
	
//Capturar tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime
println(('Tiempo transcurrido: ' + elapsedTime) + ' milisegundos')

//Validar "Fecha de Generacion"
fechaGeneracion = WebUI.getText(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lblIndEnvioBcra.1'))
assert fechaGeneracion == "Ind Envio Bcra.1"

//Validar "Si" de Fec Gen Info.1
cuentaSi = WebUI.getText(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lblSI'))
assert cuentaSi == "SI"

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}
@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}