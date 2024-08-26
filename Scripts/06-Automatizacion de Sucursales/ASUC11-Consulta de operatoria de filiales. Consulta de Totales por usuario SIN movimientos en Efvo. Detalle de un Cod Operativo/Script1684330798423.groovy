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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 4), findTestData('MainData/Users').getValue(2, 4))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando menu ?1
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("?1", 1)

//Se accede al menu Automatizacion de Sucursales
menuDesplegable = ["Sucursal Piloto","D2 - Automatizacion de Sucursales","CONSULTAS OPERATORIAS DE FILIALES","CONSULTA TOTALES ADMINISTRATIVOS"]
link = "DETALLE DE OPERACIONES SIN Efectivo (PARA EL"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)
WebUI.switchToWindowIndex(2)

//Verifica titulo de Detalle de operaciones sin efectivo y Seteo de Datos "Moneda", "Usuario"
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Moneda', 'ARS')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Usuario', 'B.0043')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))
WebUI.maximizeWindow()

// Validar los textos de las celdas directamente
WebElement table2 = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
WebElement header = table2.findElement(By.tagName("tr"))
List<WebElement> cells = header.findElements(By.tagName("th"))

assert cells[0].getText().contains('Cod. Operativo') : "Expected 'Cod. Operativo' but found ${cells[0].getText()}"
assert cells[3].getText().contains('Descripcion') : "Expected 'Descripcion' but found ${cells[1].getText()}"
assert cells[6].getText().contains('Cant Operaciones') : "Expected 'Cant Operaciones' but found ${cells[2].getText()}"
assert cells[9].getText().contains('Monto') : "Expected 'Monto' but found ${cells[3].getText()}"

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

def codigoOperativo = "18602" //El codigo operativo 18602 corresponde a "Nota de Credito Ajuste sin Imp"
// Obtén el elemento de la tabla
WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
 
// Obtén todas las filas dentro de la tabla
List<WebElement> rows = table.findElements(By.tagName("tr"))

// Valor específico que estás buscando
String targetValue = codigoOperativo
 
// Variable para rastrear si se encontró el valor específico
boolean foundTargetValue = false
 
// Itera a través de las filas
for (WebElement row : rows) {
	// Obtiene el tercer valor de la fila (índice 2, ya que las listas son base cero)
	WebElement cell = row.findElements(By.tagName("td"))[0]
 
	// Obtiene el texto de la celda
	String cellText = cell.getText()
 
	// Compara el valor de la celda con el valor específico
	if (cellText.equals(targetValue)) {
		foundTargetValue = true
			
		// Obtiene la lista de elementos td
		List<WebElement> tdList = row.findElements(By.tagName("td"))
		
		// Accede al elemento td en la posición 4 que es el detalle del codigo operativo buscado
		WebElement tdElement = tdList[4]
 
		// Intenta encontrar el elemento 'a' dentro del elemento td
		WebElement verDetalle = tdElement.findElement(By.tagName("a"))
 
		// Haz clic en el enlace
		verDetalle.click()
		
		break
	}
}

//Espera y verifica si se visualiza la primera columna del registro
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoUSUARIO/Detalle Transacciones No Efectivo/lblId'),6)
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoUSUARIO/Detalle Transacciones No Efectivo/lblId'))
assert element.contains('Id')

//Ver detalle de la primera transacción
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoUSUARIO/Detalle Transacciones No Efectivo/btnVerDetalle'))

//Valido elementos del detalle
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/Movimiento de Fondos/lblTransactionType'),6)
def element2 = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/Movimiento de Fondos/lblTransactionType'))
assert element2.contains('Transaction Type')	

WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/Movimiento de Fondos/lblCodOperativoC - FT'), 6)
def element3 = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/Movimiento de Fondos/lblCodOperativoC - FT'))
assert element3.contains(codigoOperativo)
	
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