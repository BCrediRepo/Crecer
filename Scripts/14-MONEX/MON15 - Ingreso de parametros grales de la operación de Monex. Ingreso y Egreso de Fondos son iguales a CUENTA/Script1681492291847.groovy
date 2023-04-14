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

CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
        2, 9))

//Se maximisa la ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 3)

//Se busca el TestBox de "Buscador"
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?58')

//Click en el boton "Ejecutar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambia a la ventana "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')

//Espera a que aparezca el elemento "Administracion Parametros...."
WebUI.waitForElementVisible(findTestObject('15-MONEX/02-Administracion Parametros de Sucursales/lnkAdministracion Parametros'), 
    3)

//Click en "Administracion Parametros..."
WebUI.click(findTestObject('15-MONEX/02-Administracion Parametros de Sucursales/lnkAdministracion Parametros'))

//Espera a que aparezca el elemento "Administracino Parametros de Sucursales"
WebUI.waitForElementVisible(findTestObject('15-MONEX/02-Administracion Parametros de Sucursales/lnkAdministracionParametrosdeSucursales'), 
    3)

//Click en "Administracion Parametros  de Sucursales"
WebUI.click(findTestObject('15-MONEX/02-Administracion Parametros de Sucursales/lnkAdministracionParametrosdeSucursales'))

//Cambia a la ventana "Consulta de Parametros de Filiales"
WebUI.switchToWindowTitle('Consulta de Parametros de Filiales')

WebUI.sendKeys(findTestObject('15-MONEX/11 - Administracion Parametros de Sucursales - Grupo de Cotizacion/Page_Consulta de Parametros de Filiales/inputcGupoCotizacion'), 
    'USDPR001')

WebUI.click(findTestObject('15-MONEX/01-BCCL.E.NOFILE.DET.OPER.MONEX/lnkEjecutar'))

WebUI.maximizeWindow()

WebUI.click(findTestObject('15-MONEX/08- ENQ BCCL.E.NOFILE.DET.OPER.MONEX/lblMasVerde'))

WebUI.click(findTestObject('15-MONEX/11 - Administracion Parametros de Sucursales - Grupo de Cotizacion/Page_Consulta de Parametros de Filiales/lnkConsultar'))

WebUI.switchToWindowTitle('Tabla de Sucursales')

Direccion = WebUI.verifyElementVisible(findTestObject('15-MONEX/11 - Administracion Parametros de Sucursales - Grupo de Cotizacion/Page_Tabla de Sucursales/lblDireccionSucursal'))

assert Direccion == true

