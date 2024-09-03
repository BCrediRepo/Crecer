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
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.support.ui.Select
import java.awt.Robot
import java.awt.event.KeyEvent

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 7), findTestData('MainData/Users').getValue(2, 7))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando menu ?1
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("?1", 1)

//Se accede al menu Automatizacion de Sucursales
menuDesplegable = ["Sucursal Piloto","D2 - Automatizacion de Sucursales","CONSULTAS OPERATORIAS DE FILIALES","DETALLE DE OPERACIONES"]
link = "EXISTENCIA POR DENOMINACION"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)
WebUI.switchToWindowIndex(2)

//Verifica titulo de BCCL.E.TT.CASH.DENOM y Seteo de Datos "Divisa", "Sucursal"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Divisa', 'USD')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '074')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro de Caja', '9074')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))
WebUI.maximizeWindow()

// Validar los textos de las celdas directamente
WebElement table2 = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
WebElement header = table2.findElement(By.tagName("tr"))
List<WebElement> cells = header.findElements(By.tagName("th"))

assert cells[0].getText().contains('FILAL') : "Expected 'FILAL' but found ${cells[0].getText()}"
assert cells[3].getText().contains('CATEGORIA') : "Expected 'CATEGORIA' but found ${cells[3].getText()}"
assert cells[6].getText().contains('DESC CATEGORIA') : "Expected 'DESC CATEGORIA' but found ${cells[6].getText()}"
assert cells[9].getText().contains('ID CAJERO') : "Expected 'ID CAJERO' but found ${cells[9].getText()}"
assert cells[12].getText().contains('DENOMINACION') : "Expected 'DENOMINACION' but found ${cells[12].getText()}"
assert cells[15].getText().contains('CANTIDAD') : "Expected 'CANTIDAD' but found ${cells[15].getText()}"
assert cells[18].getText().contains('IMPORTEPOR DENOMINACION') : "Expected 'IMPORTEPOR DENOMINACION' but found ${cells[18].getText()}"
assert cells[21].getText().contains('IMPORTEPOR MONEDA') : "Expected 'IMPORTEPOR MONEDA' but found ${cells[21].getText()}"

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

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