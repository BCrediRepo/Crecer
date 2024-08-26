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

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 10), findTestData('MainData/Users').getValue(2,10))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ir Ajustes Monetarios, Nota Debito Ajustes"
def menuDesplegable = ["Ajustes Monetarios"]
def link = "Nota de Debito por Ajustes"

CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Cambiamos a la ventana "Movimiento de Fondos"
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Esperamos a que el txt "Nro de Cuenta" sea visible
WebUI.waitForElementVisible(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/txtNroCuenta'), 6)

//Seteamos cuenta
WebUI.setText(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/txtNroCuenta'), '13206207767')

//Validamos para que se apliquen los cambios
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))

//Ingresamos importe
WebUI.setText(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/txtImporte'), '100000')

//Click en "Lista de Registros"
WebUI.click(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/btnListaRegistros'))

//Selecciono concepto "NotaDebAjs"
WebUI.click(findTestObject('38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblNotaDebAjs'))

//SCREENSHOT
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en "Aceptar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

// Verifica si el elemento está presente
if (WebUI.verifyElementPresent(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lnkAceptarAlertas'), 5, FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lnkAceptarAlertas'))
}

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblAutorizacion'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblAutorizacion'))
def element = WebUI.getText(findTestObject('Object Repository/38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblAutorizacion'))
assert element.contains('PENDIENTES DE AUTORIZACION') 

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}