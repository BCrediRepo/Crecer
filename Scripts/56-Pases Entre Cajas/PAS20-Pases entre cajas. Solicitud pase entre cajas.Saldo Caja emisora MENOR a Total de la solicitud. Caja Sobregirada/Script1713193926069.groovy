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
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 2), findTestData('MainData/Users').getValue(2, 2))
WebUI.maximizeWindow()

def menuDesplegable = ["Pases"]
def link = "Solicitud de Pase Entre Cajas"

//Navegar por el menu del Dashboard
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Cambiar a la ventana "TELLER"
WebUI.switchToWindowIndex(1)

//Esperar "boton De la Caja"
WebUI.waitForElementVisible(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/btnDropdownDelaCaja'), 3)

//Seleccionar "boton De la Caja"
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/btnDropdownDelaCaja'))

//Seleccionar Cuarta Caja
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/lblDelaCajaCuartaCaja'))

//Maximizar ventana
WebUI.maximizeWindow()

//Esperar "boton Moneda"
WebUI.waitForElementVisible(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/btnDropdownMonedaPASCajero'), 3)

//Seleccionar "boton Moneda"
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/btnDropdownMonedaPASCajero'))

//Seleccionar "ARS"
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/lblARS'))

//Esperar "Monto MN"
WebUI.waitForElementVisible(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/txtMontoMN'), 3)

//Setear "Monto MN"
WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/txtMontoMN'), '36.065.000.000,00')

//Seleccionar "txtComentarios"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'))

//Setear "Comentarios"
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'), 'PRUEBA DE SOLICITUD')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnAceptarRegistro'))

//Verificar "Caja Sobregirada"
WebUI.verifyElementVisible(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/lblCajaSobregirada'))

//Validar "Caja Sobregirada"
def element = WebUI.getText(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/lblCajaSobregirada'))
assert element.contains('Caja Sobregirada')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lnkAceptarAlertas'))

//Definir Objeto
Transaccion1 = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion1.split('\\s+')
def trx1 = partes[2]

//Guardar transaccion en una Variable Global
GlobalVariable.vTxn = trx1

assert Transaccion1.contains('Txn Completa:')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}