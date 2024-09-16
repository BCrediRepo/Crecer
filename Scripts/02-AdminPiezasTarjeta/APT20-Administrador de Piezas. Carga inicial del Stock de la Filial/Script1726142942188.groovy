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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))
WebUI.maximizeWindow()

def menuDesplegable = ["Administracion de Piezas con Tarjetas", "Carga inicial del Stock de Card-Carrier"]
def link = "Carga inicial del Stock de la Filial"
def menuDesplegable2 = ["Reversa de Estado de Card-Carrier"]
def link2 = "Reversa de Estado del Card-Carrier"

//Navegar por el menu del Dashboard
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Cambiar a la ventana "BCCL.E.AP.VUELCO.INICIAL"
WebUI.switchToWindowIndex(1)

//Seteo datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Marca', 'CABAL')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Producto', 'PRECARGADA')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Seleccionar Checkbox numero de pieza
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/15-BCCL.E.AP.VUELCO.INICIAL/chckbxNumeroPieza'))

//Seleccionar boton Realizar Vuelco Inicial
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/15-BCCL.E.AP.VUELCO.INICIAL/btnRealizarVuelcoInicial'))

//Cambiar a la ventana "Temenos T24"
WebUI.switchToWindowIndex(2)

//Verificar "ID successfully"
WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/06-Temenos T24/lblIDsuccessfully'))

//Validar "ID successfully"
def idsuccessfully = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/06-Temenos T24/lblIDsuccessfully'))
assert idsuccessfully.contains('successfully')

//Definir Objeto
Transaccion = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/06-Temenos T24/lblIDsuccessfully'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion.split('\\s+')
def trx1 = partes[1]

//Cambiar a la ventana del Dashboard
WebUI.switchToWindowIndex(0)

//Navegar por el menu del Dashboard
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable2, link2)

//Cambiar a la ventana "BCCL.AP.PIEZAS"
WebUI.switchToWindowIndex(3)

//Setear Numero de Pieza
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), trx1)

//Maximizar Ventana
WebUI.maximizeWindow()

//Seleccionar boton "Modificar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnModificarRegistro'))

//Seleccionar boton "Aceptar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))

//Validar "Txn Completa"
def txnCompleta = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert txnCompleta.contains('Txn Completa')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}