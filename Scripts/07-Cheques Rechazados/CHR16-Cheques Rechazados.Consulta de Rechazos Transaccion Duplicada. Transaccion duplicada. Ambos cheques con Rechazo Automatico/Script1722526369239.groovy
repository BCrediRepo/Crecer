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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.Date
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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,24), findTestData('MainData/Users').getValue(2,24))
WebUI.maximizeWindow()

//Setear "ENQ BCCL.E.CQ.RECH.DUP" en el buscador
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("ENQ BCCL.E.CQ.RECH.DUP", 1)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//boton ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Esperar 8 segundos a que se cargue la pagina
WebUI.delay(8)

//Obtener elemento de la tabla
WebElement table = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
 
//Obtener la fila de encabezado
WebElement header = table.findElement(By.tagName("tr"))
 
//Obtener todas las celdas de la fila de encabezado
List<WebElement> cells = header.findElements(By.tagName("th"))
 
//Validar que haya al menos 8 celdas
assert cells.size() >= 8 : "Expected at least 8 cells but found ${cells.size()}"

//Validar los titulos de todas las columnas
assert cells[0].getText().contains('Num Chq') : "Expected 'Id Transaccion' but found ${cells[0].getText()}"
assert cells[3].getText().contains('Num Cta') : "Expected 'Cod Op' but found ${cells[3].getText()}"
assert cells[6].getText().contains('Fec Acreditacion') : "Expected 'Descripcion' but found ${cells[6].getText()}"
assert cells[9].getText().contains('Monto') : "Expected 'Cuenta Debito' but found ${cells[9].getText()}"
assert cells[12].getText().contains('Banco Presentante') : "Expected 'Cuenta Credito' but found ${cells[12].getText()}"
assert cells[15].getText().contains('Mot Rech') : "Expected 'Mon' but found ${cells[15].getText()}"
assert cells[18].getText().contains('Est Chq') : "Expected 'Importe' but found ${cells[18].getText()}"
assert cells[21].getText().contains('Leyenda') : "Expected 'Fec Valor' but found ${cells[21].getText()}"

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}
@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}