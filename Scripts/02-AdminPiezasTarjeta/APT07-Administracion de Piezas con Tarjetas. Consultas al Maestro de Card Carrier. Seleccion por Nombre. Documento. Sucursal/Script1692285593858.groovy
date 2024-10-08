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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()

//Se accede al menu ?302
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?302')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')
WebUI.maximizeWindow()

//Se accede al menu Administracion de piezas
menuDesplegable = ["Administracion de Piezas con Tarjetas","Consultas al Maestro de Card-Carrier"]
link = "Seleccion por Persona/Sucursal/Lote"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Switch a la ventana BCCL.E.AP.ENQ.NOMBRE.DOC
WebUI.switchToWindowTitle('BCCL.E.AP.ENQ.NOMBRE.DOC')
WebUI.maximizeWindow()

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Apellido', 'RODRIGUEZ ROCIO')
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Validacion de Marca
WebUI.waitForElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/08-BCCL.E.AP.ENQ.NOMBRE.DOC/lblMarca'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/08-BCCL.E.AP.ENQ.NOMBRE.DOC/lblMarca'))
def element = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/08-BCCL.E.AP.ENQ.NOMBRE.DOC/lblMarca'))
assert element.contains('CABAL')

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
