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
import java.text.SimpleDateFormat
import java.util.Date
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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))

def menuDesplegable = ["Chequeras", "Consulta"]
def link = "Consulta de Chequeras y Cheques por Cuenta"

//Ejecuta en la linea de comando menu ?327
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("?327", 1)
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Abre la pestaña de cheques y chequeras por cuenta
WebUI.switchToWindowTitle('BCCL.E.CONS.CHQ.CUENTA.FIL')

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('CUENTA.DB', "00010623433")
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('FECHA.DESDE', "20220329")
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('FECHA.HASTA', "20220329")
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('CHQ.DESDE', "55827941")
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('CHQ.HASTA', "55827941")

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Espera y Verifica que se muestren las columnas de los registros
WebUI.waitForElementVisible(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/lblFechadeAlta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/lblFechadeAlta'))
// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

// Validar los textos de las celdas directamente
WebElement table2 = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
WebElement header = table2.findElement(By.tagName("tr"))
List<WebElement> cells = header.findElements(By.tagName("th"))

assert cells[0].getText().contains('Fecha de Alta') : "Expected 'Cod. Operativo' but found ${cells[0].getText()}"
assert cells[3].getText().contains('Tipo de Cheque') : "Expected 'Descripcion' but found ${cells[3].getText()}"
assert cells[6].getText().contains('Nro. Chequera') : "Expected 'Cant Operaciones' but found ${cells[6].getText()}"
assert cells[9].getText().contains('Total de Cheques') : "Expected 'Monto' but found ${cells[9].getText()}"
assert cells[12].getText().contains('Cheques Disponibles') : "Expected 'Monto' but found ${cells[12].getText()}"


//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}