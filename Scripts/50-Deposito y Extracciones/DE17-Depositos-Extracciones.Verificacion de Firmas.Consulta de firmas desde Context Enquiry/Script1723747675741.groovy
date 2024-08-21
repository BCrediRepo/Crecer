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

def cuenta = '02745972431'
def persona = '1002192774'
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,17), findTestData('MainData/Users').getValue(2,17))
WebUI.maximizeWindow()

//Selecciona Deposito en Ventanilla
def menuDesplegable = ["Depositos"]
def link = "Deposito en Ventanilla"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Abre la pestaña TELLER
WebUI.switchToWindowTitle('TELLER')

//Verifica titulo Deposito De Efectivo En Ventanilla
WebUI.waitForElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/lblTituloDepositoDeEfectivoEnVentanilla'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/lblTituloDepositoDeEfectivoEnVentanilla'))

//Maximiza la pantalla
WebUI.maximizeWindow()

//Llenar campo cuenta
WebUI.setText(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/txtCuenta'), cuenta)

//Validar registro para recargar pagina
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))

//Selecciona context enquiry
WebUI.click(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/btnContextEnquiry'))

//Abre la pestaña Consultas
WebUI.switchToWindowTitle('Consultas')

//Seleccionar Consulta de cuentas por firmantes
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/Consultas/lnkConsultaPorFirmante'))

//Cambio de ventana a "BCCL.E.AC.FIRM"
WebUI.switchToWindowTitle('BCCL.E.AC.FIRM')

//Click en Lupa "ventana de seleccion"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnPantallaDeSeleccion'))

//Cambio de ventana a "Consulta de Cuentas por Firmante"
WebUI.switchToWindowTitle('Consulta de Cuentas por Firmante')

//Seteo de datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Id. Persona', persona)
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Selecciono consulta de firmas
WebUI.selectOptionByIndex(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/Consulta de Cuentas por Firmante/cbxConsultarFirmas'), 3)

//Selecciono Drilldown
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/Consulta de Cuentas por Firmante/btnDrillDown'))

// Validar los textos de las celdas directamente
WebElement table = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
WebElement header = table.findElement(By.tagName("tr"))
List<WebElement> cells = header.findElements(By.tagName("th"))
 
assert cells[0].getText().contains('Firma') : "Expected 'Firma' but found ${cells[0].getText()}"
assert cells[3].getText().contains('Id Firmante') : "Expected 'Id Firmante' but found ${cells[1].getText()}"
assert cells[6].getText().contains('Firmante') : "Expected 'Firmante' but found ${cells[2].getText()}"
assert cells[9].getText().contains('Relacion') : "Expected 'Relacion' but found ${cells[3].getText()}"
assert cells[12].getText().contains('Fecha Baja') : "Expected 'Fecha Baja' but found ${cells[4].getText()}"
assert cells[15].getText().contains('Motivo Baja') : "Expected 'Motivo Baja' but found ${cells[5].getText()}"

//---------------------------------------------------------------------------------------------------------------------

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

