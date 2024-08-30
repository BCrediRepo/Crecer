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
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.DayOfWeek

//CADA VEZ QUE SE ACTUALICE LA FECHA COB - LA FECHA DE VENCIMIENTO TIENE QUE SER UNA DIFERENCIA DE UN MES

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,72), findTestData('MainData/Users').getValue(2,72))
WebUI.maximizeWindow()

def menuDesplegable = ["Plazo Fijo", "Alta de Plazo Fijo"]
def link = "Alta Plazo Fijo Persona Fisica"

//Definir y parsear Fecha COB y fechaFutura
fecha = GlobalVariable.vFechaCOB
LocalDate fechaParse = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyyMMdd"))
LocalDate fechaModificada = fechaParse.plusDays(30)
while (fechaModificada.getDayOfWeek() == DayOfWeek.SATURDAY || fechaModificada.getDayOfWeek() == DayOfWeek.SUNDAY) {
		fechaModificada = fechaModificada.plusDays(1)
}
DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd")
String fechaFutura = fechaModificada.format(formato)
DateTimeFormatter formatoAssert = DateTimeFormatter.ofPattern("dd MMM yyyy")
String fechaAssert = fechaModificada.format(formatoAssert).toUpperCase()

//Navegar por el menu del Dashboard
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Cambiar a la ventana "Alta Plazo Fijo Persona Fisica"
WebUI.switchToWindowIndex(1)

//Seteo datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nombre(s)', 'HECTOR ANTONIO')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Apellido', 'ORTIZ')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Seleccionar "Alta de Plazo Fijo"
WebUI.click(findTestObject('Object Repository/05-PlazoFijo/Alta Plazo Fijo Persona Fisica/lnkAltadePlazoFijo'))

//Setear Circular
WebUI.setText(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/txtCircular'), '35')

//Seleccionar txt de Capital
WebUI.click(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/txtCapital'))

//Setear Capital
WebUI.setText(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/txtCapital'), '1000')

//Seleccionar txt de Fecha de vencimiento
WebUI.click(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/txtFechaVencimiento'))

//Setear fecha de vencimiento
WebUI.setText(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/txtFechaVencimiento'), fechaFutura)

//Seleccionar txt forma de operar
WebUI.click(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/txtFormaOperar'))

//Seleccionar btn Drop down de Forma de operar
WebUI.click(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/btndropdownFormaOperar'))

//Seleccionar lbl unipersonal
WebUI.click(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/lblUnipersonal'))

//Seleccionar combobox de uso
WebUI.click(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/cbUso'))

//Elegir la opcion "NOALCANZADO" del combobox de uso
WebUI.selectOptionByIndex(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/cbUso'), 4)

//Elegir la opcion "CUENTA" del combobox de tipo de imposicion
WebUI.selectOptionByIndex(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/cbTipoImposicion'), 2)

//Seleccionar txt Nro de cuenta imposicion
WebUI.click(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/txtNroCuentaImposicion'))

//Setear Numero de Cuenta
WebUI.setText(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/txtNroCuentaImposicion'), '11380045676')

//Seleccionar cb tipo de liquidacion
WebUI.click(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/cbTipoLiquidacion'))

//Seleccionar combobox de tipo de liquidacion
WebUI.click(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/cbTipoLiquidacion'))

//Elegir la opcion "CUENTA"
WebUI.selectOptionByIndex(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/cbTipoLiquidacion'), 3)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton "Aceptar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Esperar 3 seg a que se cargue la tabla
WebUI.delay(3)

//Obtener elemento de la tabla
WebElement table = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
 
//Obtener la fila de encabezado
WebElement header = table.findElement(By.tagName("tr"))
 
//Obtener todas las celdas de la fila de encabezado
List<WebElement> cells = header.findElements(By.tagName("th"))
 
//Validar que haya al menos 12 celdas
assert cells.size() >= 12 : "Expected at least 11 cells but found ${cells.size()}"
 
//Validar los titulos de todas las columnas
assert cells[0].getText().contains('Estado Transaccion') : "Expected 'Id Transaccion' but found ${cells[0].getText()}"
assert cells[3].getText().contains('Titular') : "Expected 'Cod Op' but found ${cells[3].getText()}"
assert cells[6].getText().contains('Id. Plazo Fijo') : "Expected 'Descripcion' but found ${cells[6].getText()}"
assert cells[9].getText().contains('Nro Operacion') : "Expected 'Cuenta Debito' but found ${cells[9].getText()}"
assert cells[12].getText().contains('Circular') : "Expected 'Cuenta Credito' but found ${cells[12].getText()}"
assert cells[15].getText().contains('Fecha Valor') : "Expected 'Mon' but found ${cells[15].getText()}"
assert cells[18].getText().contains('Fecha Vto') : "Expected 'Importe' but found ${cells[18].getText()}"
assert cells[21].getText().contains('Capital') : "Expected 'Fec Valor' but found ${cells[21].getText()}"
assert cells[24].getText().contains('Interes') : "Expected 'Fec Valor' but found ${cells[24].getText()}"
assert cells[27].getText().contains('Id.Tx.Monetaria') : "Expected 'Fec Valor' but found ${cells[27].getText()}"
assert cells[30].getText().contains('Usuario Ingreso') : "Expected 'Fec Valor' but found ${cells[30].getText()}"
assert cells[33].getText().contains('Usuario Autoriza') : "Expected 'Fec Valor' but found ${cells[33].getText()}"

//Verificar "id.TxMonetaria"
WebUI.verifyElementVisible(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/lblIdTxMonetaria'))
	
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}