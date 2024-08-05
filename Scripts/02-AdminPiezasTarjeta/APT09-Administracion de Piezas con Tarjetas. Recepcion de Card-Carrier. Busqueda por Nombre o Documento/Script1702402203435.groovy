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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,2), findTestData('MainData/Users').getValue(2,2))
WebUI.maximizeWindow()

//NAVEGACION DASHBOARD?

//Acceder menu "?302"
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?302')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')
WebUI.maximizeWindow()

//Seleccionar "Administracion de Piezas con Tarjetas"
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/06-Temenos T24/spanAdministracion de Piezas con Tarjetas'))

//Seleccionar "Recepcion de Card-Carrier"
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/06-Temenos T24/Administracion de Piezas con Tarjetas/lnkRecepciondeCard-Carrier'))

//Seleccionar en "Busqueda por Nombre o Documento"	
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/06-Temenos T24/Administracion de Piezas con Tarjetas/Recepcion de Card-Carrier/lnkBusquedaporNombreoDocumento'))

//Cambiar ventana "BCCL.E.AP.ENQ.NOMBRE.DOC"
WebUI.switchToWindowTitle('BCCL.E.AP.ENQ.NOMBRE.DOC')

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro. Documento', '25580852')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Id Persona', '1000873562')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Apellido', 'MARMETTO')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '089')
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Validar "Nombre y apellido"
WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/08-BCCL.E.AP.ENQ.NOMBRE.DOC/lblApellidoyNombre'))
nombreYapellido = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/08-BCCL.E.AP.ENQ.NOMBRE.DOC/lblApellidoyNombre'))
assert nombreYapellido.contains("Apellido y Nombre")
documento = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/08-BCCL.E.AP.ENQ.NOMBRE.DOC/lblDocumento'))
assert documento.contains("Documento")

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