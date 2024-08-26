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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()

def menuDesplegable = ['Cuentas', 'Modificacion de cuenta', 'Bloqueo y Desbloqueo', 'Consultas']
def link = "Bloqueos Activos"

//Si el menu que busco está en dashboard uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Cambiar ventana "BCCL.AC.CTABLOQ.SUC"
WebUI.switchToWindowTitle('BCCL.AC.CTABLOQ.SUC')

//Seteo de Datos "Sucursal"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '073')

//Setear Tipo Bloqueo
//WebUI.setText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.AC.CTABLOQ.SUC/txtTipoBloqueo'), '02')

//Capturar el tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton de Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Maximizar Ventana
WebUI.maximizeWindow()

//Verificar "Tipo Bloqueo"
WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.AC.CTABLOQ.SUC/lblTipoBloqueo'))

//Capturar el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime
println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))
TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))
println TotalRegistros

//Seleccionar Ver detalle de Bloqueo
WebUI.selectOptionByIndex(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.AC.CTABLOQ.SUC/cbVerdetalleBloqueo'), 2)

//Seleccionar btn Drilldown
WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.AC.CTABLOQ.SUC/btnSelectDrilldown'))

//Cambiar ventana "Account Blocking Details"
WebUI.switchToWindowTitle('Account Blocking Details')

//Validar "Tipo de Bloqueo"
assert WebUI.getText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.AC.CTABLOQ.SUC/lblTipodeBloqueo')).contains('Tipo de Bloqueo')

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}