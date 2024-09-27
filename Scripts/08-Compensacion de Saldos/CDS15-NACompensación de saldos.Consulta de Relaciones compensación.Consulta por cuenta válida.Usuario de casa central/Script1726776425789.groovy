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

//Configuracion del ambiente y login
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))
WebUI.maximizeWindow()

def menuDesplegable = ["Compensacion de Saldos", "Consultas" ]
def link = "Consulta de Relaciones"

//Si el menu que busco está en dashboard uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Switch a la ventana BCCL.E.ACCP.GROUP
WebUI.switchToWindowTitle('BCCL.E.ACCP.GROUP')
WebUI.maximizeWindow()

//Seteo de datos
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('ID Cuenta', '00730010304')
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

WebElement table = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
WebElement header = table.findElement(By.tagName("tr"))
List<WebElement> cells = header.findElements(By.tagName("th"))
 
//Validar que haya al menos 10 celdas
assert cells.size() >= 10 : "Expected at least 8 cells but found ${cells.size()}"

// Validar los textos de las celdas directamente
assert cells[0].getText().contains('Cta Espejo') : "Expected 'Cta Espejo' but found ${cells[0].getText()}"
assert cells[3].getText().contains('Cta Haberes') : "Expected 'Cta Haberes' but found ${cells[3].getText()}"
assert cells[6].getText().contains('Regla') : "Expected 'Regla' but found ${cells[6].getText()}"
assert cells[9].getText().contains('Tipo de Regla') : "Expected 'Tipo de Regla' but found ${cells[9].getText()}"
assert cells[12].getText().contains('Monto minimo') : "Expected 'Monto minimo' but found ${cells[12].getText()}"
assert cells[15].getText().contains('Monto maximo') : "Expected 'Monto maximo' but found ${cells[15].getText()}"
assert cells[18].getText().contains('Plazo') : "Expected 'Plazo' but found ${cells[18].getText()}"
assert cells[21].getText().contains('Circular') : "Expected 'Circular' but found ${cells[21].getText()}"
assert cells[24].getText().contains('Fecha de Alta') : "Expected 'Fecha de Alta' but found ${cells[24].getText()}"
assert cells[27].getText().contains('Fecha de baja') : "Expected 'Fecha de baja' but found ${cells[27].getText()}"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}


