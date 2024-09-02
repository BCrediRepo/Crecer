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

def suc='001'
def fechaD='20220801'
def fechaH='20220831'
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,10), findTestData('MainData/Users').getValue(2,10))
WebUI.maximizeWindow()

def menuDesplegable0 = ["Rechazo de Cheques", "Consultas"]
def link0 = "Cheques Rechazados"
//Si el menu que busco está en dashboard uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable0, link0)

//Cambiar ventana "BCCL.CHRECH.RECHAZADOS"
WebUI.switchToWindowTitle('BCCL.CHRECH.RECHAZADOS')

//Seteo de Datos "Sucursal", "Fecha rechazo desde", "Fecha rechazo hasta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', suc)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha rechazo desde', fechaD)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha rechazo hasta', fechaH)

//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verificar "fecha rechazo"
WebUI.verifyElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.CHRECH.RECHAZADOS/lblFecharechazo'))

//Capturar tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime
println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))
TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))
println TotalRegistros

//Validar "Fecha rechazo"
Fechrech = WebUI.getText(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.CHRECH.RECHAZADOS/lblFecharechazo'))
assert Fechrech == "Fecha rechazo"

//Validar "Suc"
suc = WebUI.getText(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.CHRECH.RECHAZADOS/lblSuc'))
assert suc == "Suc"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}