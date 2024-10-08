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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 26), findTestData('MainData/Users').getValue(2, 26))
WebUI.maximizeWindow()

def menuDesplegable = ["Dispositivos", "Atencion a Dispositivos"]
def link = "Pase de Caja a ATM/CD"

//Ejecutar en la linea de comando "?303"
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("?303", 1)

//Cambiar ventana "Temenos T24"
WebUI.switchToWindowIndex(1)

//Navegar por el menu Temenos T24
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Cambiar ventana "TELLER"
WebUI.switchToWindowIndex(2)

//Setear "Monto MN"
WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/txtMontoMN'), '1000000')

//Maximizar ventana
WebUI.maximizeWindow()

//Seleccionar "Comentarios"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'))

//Setear "Comentarios"
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'), 'PRUEBAS CRECER')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Denominaciones DB"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lblDenominaciones'))

//Seleccionar "Cantidad Mil Pesos"
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/txtCantidadMilPesos'))

//Setear "Cantidad Mil Pesos"
WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/txtCantidadMilPesos'), '1000')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lnkAceptarAlertas'))

//Definir Objeto
Transaccion1 = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion1.split('\\s+')
def trx1 = partes[2]
assert Transaccion1.contains('Txn Completa:')

//Setear "Envio A Atm Desde Caja"
WebUI.setText(findTestObject('Object Repository/17-Remesas/04-TELLER ID/txtReasignarCaja'), trx1)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton ver registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnVerRegistro'))

//Verificar "MontoUnMillon"
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/lblMontoUnMillonPesos'))

//Validar "MontoUnMillon"
def element = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblMontoUnMillonPesos'))
assert element.contains('1.000.000,00')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}