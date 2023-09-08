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
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
        2, 9))

//Se maximisa la ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 3)

//Se busca el TestBox de "Buscador"
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?58')

//Click en el boton "Ejecutar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambia a la ventana "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')

//Espera a que aparezca el elemento "Administracion Parametros...."
WebUI.waitForElementVisible(findTestObject('15-MONEX/02-Administracion Parametros de Sucursales/lnkAdministracion Parametros'), 
    3)

//Click en "Administracion Parametros..."
WebUI.click(findTestObject('15-MONEX/02-Administracion Parametros de Sucursales/lnkAdministracion Parametros'))

//Espera a que aparezca el elemento "Administracino Parametros de Sucursales"
WebUI.waitForElementVisible(findTestObject('15-MONEX/02-Administracion Parametros de Sucursales/lnkAdministracionParametrosdeSucursales'), 
    3)

//Click en "Administracion Parametros  de Sucursales"
WebUI.click(findTestObject('15-MONEX/02-Administracion Parametros de Sucursales/lnkAdministracionParametrosdeSucursales'))

//Cambia a la ventana "Consulta de Parametros de Filiales"
WebUI.switchToWindowTitle('Consulta de Parametros de Filiales')

WebUI.maximizeWindow()

WebUI.waitForElementVisible(findTestObject('15-MONEX/02-Administracion Parametros de Sucursales/txtFilial'), 3)

//Clickea en el boton "Nueva Seleccion" para borrar los datos de la busqueda anterior.
WebUI.click(findTestObject('15-MONEX/02-Administracion Parametros de Sucursales/lnkNuevaSeleccion'))

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Clickea en el boton "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Espera a que aparezca el link "Consultar"
WebUI.waitForElementVisible(findTestObject('15-MONEX/02-Administracion Parametros de Sucursales/lnkConsultar'), 3)

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//Clickea en "Consultar"
WebUI.click(findTestObject('15-MONEX/02-Administracion Parametros de Sucursales/lnkConsultar'))

Direccion = WebUI.verifyElementVisible(findTestObject('15-MONEX/02-Administracion Parametros de Sucursales/lblDireccion'))

//Realiza un assert de la direccion
assert Direccion == true

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

