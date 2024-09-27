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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(2, 3))
WebUI.maximizeWindow()

//Ingresar "308" en el buscador
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'('?308', 1)
def menuDesplegable = ["Rechazo de cheques", "Consultas"]
def link = "CONCILIACION DE MULTAS"

//si el menú que busco está en Temenos T24, uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Cambiar ventana "CONSULTA DE CONCILIACION DE MULTAS"
WebUI.switchToWindowTitle('CONSULTA DE CONCILIACION DE MULTAS')

//Seteo de Datos "Sucursal", "Fecha Cobro Desde", "Fecha Cobro Hasta", "Conciliado"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '089')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Cobro Desde', '20210910')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Cobro Hasta', GlobalVariable.vFechaCOB)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Conciliado', 'Si')

//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verificar Fecha Cobro BCCL
WebUI.verifyElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/CONSULTA DE CONCILIACION DE MULTAS/lblFechaCobroBCCL'))

//Capturar tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime
println(('Tiempo transcurrido: ' + elapsedTime) + ' milisegundos')

//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))
TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))
println (TotalRegistros)

//Validar Sucursal
sucursal = WebUI.getText(findTestObject('Object Repository/08-Cheques Rechazados/CONSULTA DE CONCILIACION DE MULTAS/lblSucursal'))
assert sucursal == "Sucursal"

//Validar Fecha Cobro BCCL
fechaBccl = WebUI.getText(findTestObject('Object Repository/08-Cheques Rechazados/CONSULTA DE CONCILIACION DE MULTAS/lblFechaCobroBCCL'))
assert fechaBccl == "Fecha Cobro BCCL"

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}
@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}