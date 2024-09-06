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
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import java.text.SimpleDateFormat
import java.util.Date
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.support.ui.Select
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.DayOfWeek

//Es valido que aparezca el texto "SIN MOVIMIENTOS" por que cuando el campo 'Fecha desde' esta vacio, muestra los movimientos de la fecha today.En este caso no tiene movimientos.

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(2, 3))

def menuDesplegable = ["Cuentas", "Consultas de Cuentas"]
def link = "Consulta de Mov. por Fecha Valor"

//Navegar por el Dashboard
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Cambiar a la ventana "Movimientos de Ctas por Fecha Valor"
WebUI.switchToWindowIndex(1)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro de Cuenta','00540468975')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Desagrupar Monetarios (Ingresar SI)','SI')
//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Parsear FechaCOB
fecha = GlobalVariable.vFechaCOB
	
//Parsear la fecha de String a LocalDate
DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("yyyyMMdd")
LocalDate fechaParseo = LocalDate.parse(fecha, formatoEntrada)
		
//Convertir la fecha al nuevo formato dd-MM-yyyy
DateTimeFormatter formatoSalida = DateTimeFormatter.ofPattern("dd-MM-yyyy")
String fechaParseada = fechaParseo.format(formatoSalida)

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Verificar que aparezca en la cabezera "Nro. de Cuenta"
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/Movimientos de Ctas por Fecha Valor/lblNrodeCuenta'))

//Capturar tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))
TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))
println TotalRegistros

//Validar que aparezca en la cabezera "Nro. de Cuenta"
nroCuenta = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/Movimientos de Ctas por Fecha Valor/lblNrodeCuenta'))
assert nroCuenta == "Nro. de Cuenta"

//Obtener elemento de la tabla
WebElement table = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
 
//Obtener la fila de encabezado
WebElement header = table.findElement(By.tagName("tr"))
 
//Obtener todas las celdas de la fila de encabezado
List<WebElement> cells = header.findElements(By.tagName("th"))
 
//Validar que haya al menos 9 celdas
assert cells.size() >= 9 : "Expected at least 9 cells but found ${cells.size()}"

//Validar los titulos de todas las columnas
assert cells[0].getText().contains('Fec Valor') : "Expected 'Id Transaccion' but found ${cells[0].getText()}"
assert cells[3].getText().contains('ID Transaccion') : "Expected 'Cod Op' but found ${cells[3].getText()}"
assert cells[6].getText().contains('Codigo') : "Expected 'Descripcion' but found ${cells[6].getText()}"
assert cells[9].getText().contains('Descripcion') : "Expected 'Cuenta Debito' but found ${cells[9].getText()}"
assert cells[12].getText().contains('Monto Debito') : "Expected 'Cuenta Credito' but found ${cells[12].getText()}"
assert cells[15].getText().contains('Monto Credito') : "Expected 'Mon' but found ${cells[15].getText()}"
assert cells[18].getText().contains('Saldo') : "Expected 'Importe' but found ${cells[18].getText()}"
assert cells[21].getText().contains('Fecha Ingr') : "Expected 'Fec Valor' but found ${cells[21].getText()}"
assert cells[24].getText().contains('Combte') : "Expected 'Fec Valor' but found ${cells[24].getText()}"

//Validar primer resultado de la columna "fecha valor"
def fechaValor = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/Movimientos de Ctas por Fecha Valor/lblNumFecValor'))
assert fechaValor.contains(fechaParseada)

//Control Fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}