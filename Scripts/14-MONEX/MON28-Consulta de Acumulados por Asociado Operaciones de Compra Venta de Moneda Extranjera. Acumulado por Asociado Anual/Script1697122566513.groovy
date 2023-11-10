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
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 33), findTestData('MainData/Users').getValue(
        2, 33))

//Busqueda ENQ
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.PER.GEN.PF.FIN')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Consulta General de personas Fisica')

//Seteo de datos
WebUI.setText(findTestObject('15-MONEX/Consulta General personas Juridicas/txtIDPersona'), '1000873562')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Busqueda de consulta
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebUI.verifyElementVisible(findTestObject('15-MONEX/Consulta General personas Juridicas/lblAsociado'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println(('Tiempo transcurrido: ' + elapsedTime) + ' milisegundos')

//---------------------------
//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))

TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))

println(TotalRegistros)

persona = WebUI.getText(findTestObject('15-MONEX/Consulta General personas Juridicas/lblAsociado'))

//Verificacion de que es la persona que filtramos
assert persona == '1000873562'

//Detalle de consulta por transacciones acumuladas MONEX (click en boton largavistas)
WebUI.click(findTestObject('15-MONEX/Consulta General personas Juridicas/btnAcumuladoPorAsociado'))

WebUI.switchToWindowTitle('BCCL.E.ACUM.OPER.MONEX')

// Obtén el elemento de la tabla
WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay")) 

WebElement fila = table.findElements(By.tagName("tr"))[2]
WebElement celda1 = fila.findElements(By.tagName("td"))[1]
WebElement celda2 = fila.findElements(By.tagName("td"))[2]
WebElement celda3 = fila.findElements(By.tagName("td"))[3]
String celda1Text = celda1.getText()
String celda2Text = celda2.getText()
String celda3Text = celda3.getText()

String Cadena = "Frecuencia:" + ' ' + celda1Text + ", Importe en USD:" + ' ' + celda2Text + ", Cantidad de Txns:" +' ' + celda3Text
println Cadena

//assert Cadena == "Frecuencia: Anual, Importe en USD: 10,00, Cantidad de Txns: 2" //TES10
assert Cadena == "Frecuencia: Anual, Importe en USD: 792,00, Cantidad de Txns: 4" //708

//Control fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}
