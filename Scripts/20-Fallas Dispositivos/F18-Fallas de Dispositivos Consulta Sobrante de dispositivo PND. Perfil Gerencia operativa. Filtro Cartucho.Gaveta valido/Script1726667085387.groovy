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
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.chrome.ChromeDriver
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,8), findTestData('MainData/Users').getValue(2,8))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ejecutar en la linea de comando "ENQ BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN"
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("ENQ BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN", 1)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Desde', '20230831')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '099')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Hasta', GlobalVariable.vFechaCOB)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cartucho/Gaveta', 'RETIRO')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Esperar 3 seg a que cargue la tabla
WebUI.delay(3)

WebElement table = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
WebElement header = table.findElement(By.tagName("tr"))
List<WebElement> cells = header.findElements(By.tagName("th"))

//Seleccionar boton ID
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnID'))

//Seleccionar boton Id Sobrante
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnIdSobrante'))

//Seleccionar boton Observaciones
WebUI.click(findTestObject('Object Repository/11-Fallas de Dispositivos/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnObservaciones'))

//Validar que haya al menos 13 celdas
assert cells.size() >= 13 : "Expected at least 8 cells but found ${cells.size()}"

//Validar titulos de las columnas
assert cells[0].getText().contains('ID') : "Expected 'Numero de Acuerdo' but found ${cells[0].getText()}"
assert cells[3].getText().contains('Id Sobrante') : "Expected 'Cuenta' but found ${cells[3].getText()}"
assert cells[6].getText().contains('Fecha Sobrante') : "Expected 'Denominacion' but found ${cells[6].getText()}"
assert cells[9].getText().contains('Moneda') : "Expected 'Estado' but found ${cells[9].getText()}"
assert cells[12].getText().contains('Monto') : "Expected 'Fec. Inicio' but found ${cells[12].getText()}"
assert cells[15].getText().contains('Saldo') : "Expected 'Fec. Vencimiento' but found ${cells[15].getText()}"
assert cells[18].getText().contains('Cartucho/Gaveta') : "Expected 'Numero de Acuerdo' but found ${cells[18].getText()}"
assert cells[21].getText().contains('Dispositivo') : "Expected 'Cuenta' but found ${cells[21].getText()}"
assert cells[24].getText().contains('Origen') : "Expected 'Denominacion' but found ${cells[24].getText()}"
assert cells[27].getText().contains('Filial') : "Expected 'Estado' but found ${cells[27].getText()}"
assert cells[30].getText().contains('Reclasificacion') : "Expected 'Fec. Inicio' but found ${cells[30].getText()}"
assert cells[33].getText().contains('Fecha real falla') : "Expected 'Fec. Vencimiento' but found ${cells[33].getText()}"
assert cells[36].getText().contains('Observaciones') : "Expected 'Fec. Vencimiento' but found ${cells[36].getText()}"

//Localizar el combobox usando el Test Object capturado
WebElement comboBox = WebUI.findWebElement(findTestObject('Object Repository/11-Fallas de Dispositivos/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/cbxOpciones'))

//Crear un objeto Select para interactuar con el combobox
Select select = new Select(comboBox)

//Obtener todas las opciones del combobox
List<WebElement> options = select.getOptions()

//Validar las 3 primeras opciones
println "Validando las tres primeras opciones:"
println "1. ${options[0].getText()}"
println "2. ${options[1].getText()}"
println "3. ${options[2].getText()}"

//Realizar la validación que las opciones sean correctas
assert options[0].getText() == "Baja Sobrante Dispositivo - Socio"
assert options[1].getText() == "Baja Sobrante Dispositivo - O Banco"
assert options[2].getText() == "Baja Sobrante Disp - Cta Contable"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}