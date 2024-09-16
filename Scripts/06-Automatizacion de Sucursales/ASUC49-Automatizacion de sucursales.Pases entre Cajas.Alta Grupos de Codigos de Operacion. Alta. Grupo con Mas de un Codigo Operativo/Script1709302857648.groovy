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

//Ejecutar en la linea de comando "?1"
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("?1", 1)

//Navegar por el menu Temenos T24
def menuDesplegable = ["Sucursal Piloto", "D2 - Automatizacion de Sucursales", "Administracion"]
def link = "Alta o Modificacion de grupos de codigos oper"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)
//Cambiar ventana "BCCL EB GRUPO CODOPE"
WebUI.switchToWindowIndex(2)

//Setear Alta de grupos
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/txtAltadeGrupos'), '91')
WebUI.maximizeWindow()

//Seleccionar "boton Modificar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnModificarRegistro'))

//Completo el Registro - Formulario
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/txtDescripcion'), 'PRUEBA ALTA GRUPO COD OP')
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/txtDescripcionCorta'), 'PRUEBA')
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/btnExpandirMultivalor'))
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/txtCodigoOperacion'), '00743')
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/txtCodigoOperacion2'), '00743')
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/rbtnSI'))
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/txtAltadeGrupos'), '91')
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/lblCodigodeOperacion.2'))

//Validar "Codigos de Operaciones"
def codigoOperacion = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/lblCodigodeOperacion.2'))
assert codigoOperacion.contains('Codigo de Operacion.2')

//Seleccionar "boton Volver Pantalla Aplicacion"
//WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/btnVolverPantallaAplicacion'))
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVolver'))

//Setear Alta de grupos 91
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/txtAltadeGrupos'), '91')

//Seleccionar "boton Modificar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnModificarRegistro'))

//Seleccionar "boton Borrar Codigo Operacion"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/btnBorrarCodigoOperacion'))

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def txnCompleta = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert txnCompleta.contains('Txn Completa:')

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