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
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory

//TERMINA CON UN CARTEL DE ERROR "ER"

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ejecutar en la linea de comando "ENQ BCCL.E.CONS.CABAL.CTA"
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("ENQ BCCL.E.CONS.CABAL.CTA", 1)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Id Cta CABAL', '1911127107354')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Esperar 3 seg a que cargue la tabla
WebUI.delay(3)

WebElement table = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
WebElement header = table.findElement(By.tagName("tr"))
List<WebElement> cells = header.findElements(By.tagName("th"))

//Validar que haya al menos 9 celdas
assert cells.size() >= 9 : "Expected at least 9 cells but found ${cells.size()}"

//Validar titulos de las columnas
assert cells[0].getText().contains('Nro Tarjeta') : "Expected 'Numero de Acuerdo' but found ${cells[0].getText()}"
assert cells[3].getText().contains('Apellido/Nombre') : "Expected 'Cuenta' but found ${cells[3].getText()}"
assert cells[6].getText().contains('Tipo Doc') : "Expected 'Denominacion' but found ${cells[6].getText()}"
assert cells[9].getText().contains('Nro Doc') : "Expected 'Estado' but found ${cells[9].getText()}"
assert cells[12].getText().contains('Sexo') : "Expected 'Fec. Inicio' but found ${cells[12].getText()}"
assert cells[15].getText().contains('Fecha Nac') : "Expected 'Fec. Vencimiento' but found ${cells[15].getText()}"
assert cells[18].getText().contains('Fecha Alta') : "Expected 'Numero de Acuerdo' but found ${cells[18].getText()}"
assert cells[21].getText().contains('Fecha Venc') : "Expected 'Cuenta' but found ${cells[21].getText()}"
assert cells[24].getText().contains('Limite Compra') : "Expected 'Denominacion' but found ${cells[24].getText()}"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}