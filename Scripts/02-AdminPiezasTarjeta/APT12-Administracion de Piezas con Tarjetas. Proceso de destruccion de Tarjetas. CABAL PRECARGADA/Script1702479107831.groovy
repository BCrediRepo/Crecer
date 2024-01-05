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

//Seleccionar "Proceso de destruccion de Tarjetas"
WebUI.click(findTestObject('Object Repository/02-Dashboard/01-AdminPiezasConTarjetas/lnkProcesodeDestrucciondeTarjetas'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Impresion del ACTA DE DESTRUCCION"
WebUI.click(findTestObject('Object Repository/02-Dashboard/01-AdminPiezasConTarjetas/06-Proceso de Destruccion de Tarjetas/lnkImpresiondelACTADEDESTRUCCION'))

//Cambiar ventana "Imprimir Acta de Destruccion"
WebUI.switchToWindowTitle('Imprimir Acta de Destruccion')

//Maximizar pantalla
WebUI.maximizeWindow()

//Limpiar todos los txt
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Seleccionar Drill Down Marca
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/12-Imprimir Acta de Destruccion/btnDrillDownMarca'))

//Seleccionar "CABAL"
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/12-Imprimir Acta de Destruccion/lblCABAL'))

//Seleccionar Drill Down Producto
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/12-Imprimir Acta de Destruccion/btnDrillDownProducto'))

//Seleccionar "PRECARGADA"
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/12-Imprimir Acta de Destruccion/lblPRECARGADA'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verificar "Vencimiento"
WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/12-Imprimir Acta de Destruccion/lblVencimiento'))

//Validar "Vencimiento"
vencimiento = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/12-Imprimir Acta de Destruccion/lblVencimiento'))
assert vencimiento == "Vencimiento"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}