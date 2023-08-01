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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,10), findTestData('MainData/Users').getValue(2,10))

WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'FUNDS.TRANSFER,NOCOM.FILIAL.SUSCRIPCION')

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Abre la pestaña Movimiento de Fondos
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verifica que estemos en la ventana correcta
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/lblTituloImputaciondeSuscripNOCOMSuc-PR'))

//Selecciona añadir un nuevo registro
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/btnNuevoRegistro'))

//Verifica el titulo de la pantalla
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/lblTitulo2ImputaciondeSuscripNOCOMSuc-PR'))

//Ingresa un monto
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/txtMonto'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/txtMonto'),"10")

//Ingresa una sucursal
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/txtSucursalDestino'),6)
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/rbSucursalDestino'))
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/txtSucursalDestino'),'100')
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/rbSucursalDestino'))

//Valida el registro
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/btnValidarRegistro'))

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Acepta el registro
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/btnAceptarRegistro'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/lblTxn Completa'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/lblTxn Completa'))
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/lblTxn Completa'))
assert element.contains('Txn Completa:')

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


