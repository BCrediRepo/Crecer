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
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

//FALTA CUENTA QUE TENGA FTs 2 DIAS ANTES DE LA FECHACOB
def cuenta = '11370083398'

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

//----------------------------------------------------------------------------------------------------------------------------------------------------

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

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Seleccionar btn Ver Detalle
WebUI.click(findTestObject('Object Repository/38-Ajustes Monetarios/ContrasientoTotal/btnVerDetalleTxn'))

//Definir valor Debit Amount
String debitAmount = WebUI.getText(findTestObject('Object Repository/38-Ajustes Monetarios/Movimiento de Fondos/lblDebitAmount'))

//Configurar DecimalFormat para utilizar coma como separador decimal
DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault())
symbols.setDecimalSeparator(',' as char)

DecimalFormat decimalFormat = new DecimalFormat()
decimalFormat.setDecimalFormatSymbols(symbols)

//Parsear la cadena de texto a un número decimal
Number valorNumero = decimalFormat.parse(debitAmount)

//Convertir el número a un double
double valor = valorNumero.doubleValue()

//Validar que es mayor a 0
assert valor >= 0,01

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}