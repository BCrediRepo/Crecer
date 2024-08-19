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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 57), findTestData('MainData/Users').getValue(2, 57))
WebUI.maximizeWindow()

//Ir a Pases y Transferencias, Transferencia a Cuentas del Banco
def menuDesplegable = ["Pases y Transferencias"]
def link = "Transferencia a Cuentas del Banco"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Cambiar ventana "Movimiento de Fondos"
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Esperar elemento "ID Ordenante"
WebUI.waitForElementVisible(findTestObject('Object Repository/12-Transferencias Internas/Movimiento de Fondos/txtIDOrdenante'), 3)

//Setear ID Ordenante
WebUI.setText(findTestObject('Object Repository/12-Transferencias Internas/Movimiento de Fondos/txtIDOrdenante'), '1000873562')

//Maximizar Ventana
WebUI.maximizeWindow()

//Setear Nro. de Cuenta Debito
WebUI.setText(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/txtNro. de Cuenta Debito'), '21190118359')

//Seleccionar campo de "Nro. de cuenta Credito"
WebUI.click(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/txtNro. de Cuenta Credito'))

//Setear Nro. de Cuenta Credito
WebUI.setText(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/txtNro. de Cuenta Credito'), '20890227804')

//Setear Importe
WebUI.setText(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/txtImporte'), '10')

//Seleccionar "boton Dropdown Motivo"
WebUI.click(findTestObject('Object Repository/59-Pases y Transferencias/Movimiento de Fondos/btnDropdownMotivo'))

//Seleccionar CUO
WebUI.click(findTestObject('Object Repository/59-Pases y Transferencias/Movimiento de Fondos/lblCUO'))

//Setear Observaciones
WebUI.setText(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/txtObservaciones'), 'PRUEBAS CRECER')

//Click en boton validar
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))

//Click boton aceptar
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Acepto alertas
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'),6)
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Cambiar ventana "Verificacion de Firmas - Fil.074 Caseros Centro"
WebUI.switchToWindowTitle('Verificacion de Firmas - Fil.074 Caseros Centro')

//Seleccionar "FORZAR" del combo box
WebUI.selectOptionByIndex(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/cbxAccion'), 2)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/btnAceptar'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lnkAceptarAlertas'))

//Verificar "Finalizada"
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblFinalizada'))

//Validar "Finalizada"
def estado = WebUI.getText(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblFinalizada'))
assert estado.contains('FINALIZADA')

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}