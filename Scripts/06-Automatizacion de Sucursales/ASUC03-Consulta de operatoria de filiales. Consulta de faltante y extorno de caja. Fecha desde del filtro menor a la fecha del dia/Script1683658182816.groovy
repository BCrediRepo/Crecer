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
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 8), findTestData('MainData/Users').getValue(2, 8))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando menu ?1
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("?323", 1)

//Se accede al menu Automatizacion de Sucursales
menuDesplegable = ["Consulta de operatoria"]
link = "Consulta de Caja Faltantes/Extonos Caja"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

WebUI.switchToWindowTitle('BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA')

//Verifica titulo de Caja Faltantes Extonos Caja
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lbltituloBCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA'))
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Desde', '20230824')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cod. Transaccion', '4')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '001')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Presiona Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))
WebUI.maximizeWindow()
WebUI.delay(15)//Necesito tiempo de espera para que aparezcan resultados de la consulta

// Validar los textos de las celdas directamente
WebElement table = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
WebElement header = table.findElement(By.tagName("tr"))
List<WebElement> cells = header.findElements(By.tagName("th"))

assert cells[0].getText().contains('Cuenta Interna Caja') : "Expected 'Cuenta Interna Caja' but found ${cells[0].getText()}"
assert cells[3].getText().contains('Cuenta Interna') : "Expected 'Cuenta Interna' but found ${cells[1].getText()}"
assert cells[6].getText().contains('Filial') : "Expected 'Filial' but found ${cells[2].getText()}"
assert cells[9].getText().contains('Cajero') : "Expected 'Cajero' but found ${cells[3].getText()}"
assert cells[12].getText().contains('Legajo Cajero') : "Expected 'Legajo Cajero' but found ${cells[4].getText()}"
assert cells[15].getText().contains('Transaccion') : "Expected 'Transaccion' but found ${cells[5].getText()}"
assert cells[18].getText().contains('Descripcion') : "Expected 'Descripcion' but found ${cells[6].getText()}"
assert cells[21].getText().contains('Fecha') : "Expected 'Fecha' but found ${cells[7].getText()}"

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//Ver el primer registro
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/btnVerDetalles'))

//Espera y verifica si se visualiza la primera columna del registro
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/TELLER/lblTransaction Code'),6)
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/TELLER/lblTransaction Code'))
assert element.contains('Transaction Code')

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