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
import java.util.Date
import java.util.Calendar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.DayOfWeek

//FALTA CUENTA QUE TENGA FTs 2 DIAS ANTES DE LA FECHACOB
def cuenta = ''

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(2, 3))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()


//Camino para hacer que el codigo no esté hardcodeado --------------------------------------------------------------------------

//Ir a la ventana "Movimientos de Ctas por Fecha Valor"
def menuDesplegable = ["Cuentas", "Consultas de Cuentas"]
def link = "Consulta de Mov. por Fecha Valor"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowIndex(1)

//Seteo de datos "Nro de Cuenta" y "Fecha Desde"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro de Cuenta', cuenta)

//Parseo fecha porque la busqueda de contrasiento total no permite FT del dia
fecha = GlobalVariable.vFechaCOB
LocalDate fechaParse = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyyMMdd"))
LocalDate fechaModificada = fechaParse.minusDays(2)
while (fechaModificada.getDayOfWeek() == DayOfWeek.SATURDAY || fechaModificada.getDayOfWeek() == DayOfWeek.SUNDAY) {
		fechaModificada = fechaModificada.minusDays(1)
}

DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd")
String fechaPasada = fechaModificada.format(formato)

DateTimeFormatter formatoAssert = DateTimeFormatter.ofPattern("dd-MM-yyyy")
String fechaAssert = fechaModificada.format(formatoAssert).toUpperCase()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Desde', fechaPasada)

fecha2 = GlobalVariable.vFechaCOB
LocalDate fechaParse2 = LocalDate.parse(fecha2, DateTimeFormatter.ofPattern("yyyyMMdd"))
DateTimeFormatter formatoAssert2 = DateTimeFormatter.ofPattern("dd-MM-yyyy")
String fechaAssert2 = fechaParse2.format(formatoAssert2).toUpperCase()
//ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))


def buscarElementoEnTabla(String fechaAssert) {		
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {		
		WebElement cell = row.findElements(By.tagName("td"))[0]		
		String cellText = cell.getText()
		if (cellText.equals(fechaAssert)) {			
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			WebElement tdElement = tdList[1]
			String ft = tdElement.getText()
			println(ft)
			GlobalVariable.vTxn=ft
			return true
		}
	}
	return false
}

def encontrado = false
while (!encontrado) {
	encontrado = buscarElementoEnTabla(fechaAssert)
	if (!encontrado) {
		WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnSiguiente'))
		WebUI.delay(2)
	}	
}

///Cambiar a la ventana del Dashboard
WebUI.switchToWindowIndex(0)
 
//-----------------------------------------------------------------------------------------------------------------------------
//Setear "ENQ BCCL.E.STMS.ENT.BOOK.CA" en el buscador
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.STMS.ENT.BOOK.CA')

//Seleccionar "boton buscar"
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana "Contrasiento Total"
WebUI.switchToWindowIndex(2)

//Verificar titulo Contrasiento total
WebUI.verifyElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/ContrasientoTotal/lblTituloContrasientoTotal'))

//Seteo de datos "Id Transaccion"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Id Transaccion', GlobalVariable.vTxn)

//ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Comprobar primera columna del registro
WebUI.verifyElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/ContrasientoTotal/lblIdTransaccion'))

//Capturar tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//Desplegar columna de "Descripcion"
WebUI.click(findTestObject('Object Repository/38-Ajustes Monetarios/ContrasientoTotal/btnDescripcion'))
 
//Obtener elemento de la tabla
WebElement table2 = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
 
//Obtener la fila de encabezado
WebElement header = table2.findElement(By.tagName("tr"))
 
//Obtener todas las celdas de la fila de encabezado
List<WebElement> cells = header.findElements(By.tagName("th"))
 
//Validar que haya al menos 8 celdas
assert cells.size() >= 8 : "Expected at least 8 cells but found ${cells.size()}"
 
//Validar los titulos de todas las columnas
assert cells[0].getText().contains('Id Transaccion') : "Expected 'Id Transaccion' but found ${cells[0].getText()}"
assert cells[3].getText().contains('Cod Op') : "Expected 'Cod Op' but found ${cells[3].getText()}"
assert cells[6].getText().contains('Descripcion') : "Expected 'Descripcion' but found ${cells[6].getText()}"
assert cells[9].getText().contains('Cuenta Debito') : "Expected 'Cuenta Debito' but found ${cells[9].getText()}"
assert cells[12].getText().contains('Cuenta Credito') : "Expected 'Cuenta Credito' but found ${cells[12].getText()}"
assert cells[15].getText().contains('Mon') : "Expected 'Mon' but found ${cells[15].getText()}"
assert cells[18].getText().contains('Importe') : "Expected 'Importe' but found ${cells[18].getText()}"
assert cells[21].getText().contains('Fec Valor') : "Expected 'Fec Valor' but found ${cells[21].getText()}"
	
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}