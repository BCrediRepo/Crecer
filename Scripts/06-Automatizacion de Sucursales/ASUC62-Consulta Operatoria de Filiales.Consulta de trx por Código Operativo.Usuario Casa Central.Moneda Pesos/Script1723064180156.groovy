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
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory



//Configuracion del ambiente y login
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,21), findTestData('MainData/Users').getValue(2,21))
WebUI.maximizeWindow()

//Ingresamos la enq correspondiente
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?1')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))
WebUI.switchToWindowTitle("Temenos T24")
WebUI.maximizeWindow()

def CodOperativo = '00101'
def Moneda = 'ARS'

def menuDesplegable = ["Sucursal Piloto", "D2 - Automatizacion de Sucursales", "CONSULTAS OPERATORIAS DE FILIALES", "DETALLE DE OPERACIONES"]
def link = "CONSULTA POR CODIGO OPERATIVO"

//si el menú que busco está en Temenos T24, uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Switch a la ventana Consulta Por Codigo Operativo
WebUI.switchToWindowTitle('Consulta Por Codigo Operativo')
WebUI.maximizeWindow()

//Seteo de datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cod Operativo', CodOperativo)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Moneda', Moneda)
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebElement table = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
WebElement header = table.findElement(By.tagName("tr"))
List<WebElement> cells = header.findElements(By.tagName("th"))

//Validar que haya al menos 10 celdas
assert cells.size() >= 10 : "Expected at least 8 cells but found ${cells.size()}"

// Validar los textos de las celdas directamente
assert cells[0].getText().contains('Sucursal') : "Expected 'Sucursal' but found ${cells[0].getText()}"
assert cells[3].getText().contains('Cod. Oprtvo') : "Expected 'Cod. Oprtvo' but found ${cells[3].getText()}"
assert cells[6].getText().contains('Moneda') : "Expected 'Moneda' but found ${cells[6].getText()}"
assert cells[9].getText().contains('Id Transaccion') : "Expected 'Id Transaccion' but found ${cells[9].getText()}"
assert cells[12].getText().contains('Cuenta No') : "Expected 'Cuenta No' but found ${cells[12].getText()}"
assert cells[15].getText().contains('Monto') : "Expected 'Monto' but found ${cells[15].getText()}"
assert cells[18].getText().contains('Fec. Proceso') : "Expected 'Fec. Proceso' but found ${cells[18].getText()}"
assert cells[21].getText().contains('Fecha Valor') : "Expected 'Fecha Valor' but found ${cells[21].getText()}"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}



