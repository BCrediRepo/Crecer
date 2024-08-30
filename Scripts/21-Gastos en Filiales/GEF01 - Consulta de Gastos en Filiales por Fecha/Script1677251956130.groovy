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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 4), findTestData('MainData/Users').getValue(2, 4))
WebUI.maximizeWindow()

def menuDesplegable = ["Sucursal Piloto", "GL - Entregas Global Logic", "Gastos en Filiales"]
def link = "Consulta de Gastos en Filiales"

//Ejecutar en la linea de comando "?1"
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("?1", 1)

//Cambiar a la ventana "Temenos T24"
WebUI.switchToWindowIndex(1)

//Navegar por el menu Temenos T24
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Cambiar a la venana "BCCL.E.GASTOS.FILIALES"
WebUI.switchToWindowIndex(2)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizamos
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha','20220713')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/21-Fallas/09-BCCL.E.EXTORNO.DISPO.GEOP/lnkEjecutar'))

//WebUI.delay(15)

WebUI.waitForElementVisible(findTestObject('22 - Gastos en Filiales/BCCL.E.GASTOS.FILIALES/lblFecha'), 6)
WebUI.verifyElementVisible(findTestObject('22 - Gastos en Filiales/BCCL.E.GASTOS.FILIALES/lblFecha'))

//Capturar tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

WebUI.waitForElementVisible(findTestObject('Object Repository/22 - Gastos en Filiales/BCCL.E.GASTOS.FILIALES/lnkVerDetalle'), 6)

WebUI.click(findTestObject('Object Repository/22 - Gastos en Filiales/BCCL.E.GASTOS.FILIALES/lnkVerDetalle'))

WebUI.waitForElementVisible(findTestObject('Object Repository/22 - Gastos en Filiales/BCCL.E.GASTOS.FILIALES/lblRubro'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/22 - Gastos en Filiales/BCCL.E.GASTOS.FILIALES/lblRubro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

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