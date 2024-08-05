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
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 5), findTestData('MainData/Users').getValue(2, 5))
WebUI.maximizeWindow()

//Se accede al menu Administracion de piezas
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'BCCL.AP.PIEZAS L L')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana de Consulta Maestro Card-Carrier
WebUI.switchToWindowTitle('%BCCL.AP.PIEZAS')

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('AP.MARCA', 'CABAL')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('AP.SUCURSAL', '073')
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Obtengo el num de pieza
WebUI.waitForElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/02-Carga Manual de Card-Carrier No Encontrado/lblNumPieza'), 6)
numPieza = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/02-Carga Manual de Card-Carrier No Encontrado/lblNumPieza'))
WebUI.delay(3)
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/02-Carga Manual de Card-Carrier No Encontrado/lblNumPieza'))
WebUI.switchToWindowTitle('BCCL.AP.PIEZAS')
//-------------
//Aqui reviso el contenido del producto para luego modificarlo
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'), 6)
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))
WebUI.waitForElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/02-Carga Manual de Card-Carrier No Encontrado/lblProductoPieza'), 6)
def prod = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/02-Carga Manual de Card-Carrier No Encontrado/lblProductoPieza'))
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVolver'), 6)
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVolver'))
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), 6)
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), numPieza)
//-------------
WebUI.waitForElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/02-Carga Manual de Card-Carrier No Encontrado/EditarPiezas'), 6)
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/02-Carga Manual de Card-Carrier No Encontrado/EditarPiezas'))
WebUI.waitForElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/02-Carga Manual de Card-Carrier No Encontrado/txtPRODUCTOPIEZAS'), 6)
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Modifico el registro
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/02-Carga Manual de Card-Carrier No Encontrado/txtPRODUCTOPIEZAS'))
if (prod.contains('PROGRESAR')) {
    WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/02-Carga Manual de Card-Carrier No Encontrado/txtPRODUCTOPIEZAS'), 'CREDITO')
} else {
    WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/02-Carga Manual de Card-Carrier No Encontrado/txtPRODUCTOPIEZAS'), 'PROGRESAR')
}

WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'), 6)
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.closeBrowser()

//Login - Me vuelvo a loguear para autorizar el cambio realizado anteriormente. Utilizando otro user de la misma suc.
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 6), findTestData('MainData/Users').getValue(2, 6))
WebUI.maximizeWindow()

//Se accede al menu Administracion de piezas
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'BCCL.AP.PIEZAS')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana de Consulta Maestro Card-Carrier
WebUI.switchToWindowTitle('BCCL.AP.PIEZAS')
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), 6)
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), numPieza)
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnHerramienta'))
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAutorizaRegistro'), 6)
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAutorizaRegistro'))
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta')) 

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
