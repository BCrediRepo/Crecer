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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 4), findTestData('MainData/Users').getValue(2, 4))
WebUI.maximizeWindow()

def menuDesplegable = ["Consultas Varias", "Consultas de Atencion a Usuarios", "Consultas de Cierre Operatoria"]
def link = "Detalle de Operaciones Sin Efvo (Filial)"

//Ejecutar en la linea de comando "?70"
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("?70", 1)

//Cambiar a la ventana "Temenos T24"
WebUI.switchToWindowIndex(1)

//Navegar por el menu Temenos T24
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Cambiar a la ventana "Totales Sucursal x Cod Oper."
WebUI.switchToWindowIndex(2)

//Seteo de Datos "Moneda", "Sucursal"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Moneda', 'ARS')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '043')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Seleccionar "boton Ver Detalle"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/Totales Sucursal x Cod Oper/btnVerDetalle'))

//Cambiar ventana "Detalle Transacciones No Efectivo"
WebUI.switchToWindowTitle('Detalle Transacciones No Efectivo')

//Verificar la sucursal
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Totales Usuario x Cod Oper/lblSucursal'))
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Totales Usuario x Cod Oper/lblSucursal'))
assert element.contains('043')

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