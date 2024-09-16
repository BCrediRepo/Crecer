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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.DayOfWeek
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory

def multaConciliada= 'SI'
def cuenta = '00730109345' //cuenta hardcodeada debido a que sino timeout de consulta
def suc= '073'

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,37), findTestData('MainData/Users').getValue(2,37))
WebUI.maximizeWindow()

//Setear en la linea de comando
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("ENQ BCCL.CQ.CHRECH.MULTAS", 1)

//MODIFICO FECHA COB
fecha = GlobalVariable.vFechaCOB
LocalDate fechaParse = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyyMMdd"))
LocalDate fechaModificada = fechaParse.minusDays(15)
while (fechaModificada.getDayOfWeek() == DayOfWeek.SATURDAY || fechaModificada.getDayOfWeek() == DayOfWeek.SUNDAY) {
		fechaModificada = fechaModificada.minusDays(1)
}
DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd")
String fechaPasada = fechaModificada.format(formato)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Multa Conciliada', multaConciliada)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Rech Desde', fechaPasada)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Numero Cuenta', cuenta)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', suc)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

WebElement table = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
WebElement header = table.findElement(By.tagName("tr"))
List<WebElement> cells = header.findElements(By.tagName("th"))
 
// Validar los textos de las celdas directamente
assert cells[0].getText().contains('Fecha rechazo') : "Expected 'Fecha rechazo' but found ${cells[0].getText()}"
assert cells[3].getText().contains('Fec cobro/Dev') : "Expected 'Fec cobro/Dev' but found ${cells[3].getText()}"
assert cells[6].getText().contains('Estado multa') : "Expected 'Estado multa' but found ${cells[6].getText()}"
assert cells[9].getText().contains('Monto multa') : "Expected 'Monto multa' but found ${cells[9].getText()}"
assert cells[12].getText().contains('Sucursal') : "Expected 'Sucursal' but found ${cells[12].getText()}"
assert cells[15].getText().contains('Numero Cuenta') : "Expected 'Numero Cuenta' but found ${cells[15].getText()}"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

