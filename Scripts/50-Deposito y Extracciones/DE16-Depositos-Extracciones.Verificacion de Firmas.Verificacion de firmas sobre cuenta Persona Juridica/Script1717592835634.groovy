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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 45), findTestData('MainData/Users').getValue(2, 45))

//Ir a Extracciones, Retiro (CC sin Chequera - Solo Titular) 
def menuDesplegable = ["Extracciones"]
def link = "Retiro (CC sin Chequera - Solo Titular)"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Canbiar a la ventana "TELLER"
WebUI.switchToWindowIndex(1)

//Esperar 3 seg a que se cargue la pagina
WebUI.delay(3)

//Seleccionar txt ID Cuenta
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/txtCuenta'))

//Setear ID Cuenta
WebUI.setText(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/txtCuenta'), '00190250373')

//Maximizar Pantalla
WebUI.maximizeWindow()

//Seleccionar Importe a Pagar
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/txtImporteaPagar'))

//Setear Importe a Pagar
WebUI.setText(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/txtImporteaPagar'), '5')

//Setear ID Persona
WebUI.setText(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/txtIdPersona'), '1002159673')

//Seleccionar "Aceptar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Acepto alertas
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'),6)
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Cambiar ventana a Verificación de firmas
WebUI.switchToWindowIndex(2)

//Seleccionar "FORZAR" del combo box
WebUI.selectOptionByIndex(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/cbxAccion'), 2)

//Maximizar Pantalla
WebUI.maximizeWindow()

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/btnAceptar'))

//Definir Transaccionforzada
Transaccionforzada = WebUI.getText(findTestObject('Object Repository/51-Deposito-Extracciones/Verificacion de Firmas/lblTransaccionForzada'))

//Verificar "FINALIZADA"
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblFinalizada'))

//Validar "FINALIZADA"
def estado1 = WebUI.getText(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblFinalizada'))
assert estado1.contains('FINALIZADA')

//Verificar "AUTORIZADA"
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblAutorizada'))

//Validar "AUTORIZADA"
def estado2 = WebUI.getText(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblAutorizada'))
assert estado2.contains('AUTORIZADA')

//Canbiar a la ventana "TELLER"
WebUI.switchToWindowIndex(1)

//Setear en Pago de Cheque Mostrador
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/txtBajaPartidasIP-CuentaContable'), Transaccionforzada)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Ver Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

//Verificar "FORZADO FIRMA"
WebUI.verifyElementVisible(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/lblForzadoFirma'))

//Validar "FORZADO FIRMA"
def firma = WebUI.getText(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/lblForzadoFirma'))
assert firma.contains('FORZADO FIRMA')

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}