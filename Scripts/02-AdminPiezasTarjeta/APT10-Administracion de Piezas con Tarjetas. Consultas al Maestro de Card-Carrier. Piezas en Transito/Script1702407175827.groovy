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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Administracion de Piezas con Tarjetas"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkAdministracionPiezasTarjetas'))

//Seleccionar "Consultas al Maestro de Card-Carrier"
WebUI.click(findTestObject('Object Repository/02-Dashboard/01-AdminPiezasConTarjetas/lnkConsultasalMaestrodeCard-Carrier (1)'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Piezas en Transito"
WebUI.click(findTestObject('Object Repository/02-Dashboard/01-AdminPiezasConTarjetas/04-ConsultaMaestroCardCarrier/lnkPiezasenTransito'))

//Cambiar ventana "BCCL.AP.E.AP.LISTADO.EN.TRANSITO"
WebUI.switchToWindowTitle('BCCL.AP.E.AP.LISTADO.EN.TRANSITO')

//Filtro limpieza
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Seleccionar "Piezas en Transito"
WebUI.click(findTestObject('Object Repository/02-Dashboard/01-AdminPiezasConTarjetas/04-ConsultaMaestroCardCarrier/lnkPiezasenTransito'))

//Cambiar ventana "BCCL.AP.E.AP.LISTADO.EN.TRANSITO"
WebUI.switchToWindowTitle('BCCL.AP.E.AP.LISTADO.EN.TRANSITO')

//Maximizar pantalla
WebUI.maximizeWindow()

//Seleccionar "Boton Drill Down Marca"
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/10-BCCL.AP.E.AP.LISTADO.EN.TRANSITO/btnDrillDownMarca'))

//Seleccionar "Visa"
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/10-BCCL.AP.E.AP.LISTADO.EN.TRANSITO/lblVisa'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verificar "Pieza"
WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/10-BCCL.AP.E.AP.LISTADO.EN.TRANSITO/lblPieza'))

//Validar "Pieza"
pieza = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/10-BCCL.AP.E.AP.LISTADO.EN.TRANSITO/lblPieza'))
assert pieza == "Pieza"

//Validar "Producto"
producto = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/10-BCCL.AP.E.AP.LISTADO.EN.TRANSITO/lblProducto'))
assert producto == "Producto"

//Validar "Tarjeta"
tarjeta = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/10-BCCL.AP.E.AP.LISTADO.EN.TRANSITO/lblTarjeta'))
assert tarjeta == "Tarjeta"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}