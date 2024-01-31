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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 52), findTestData('MainData/Users').getValue(2, 52))
WebUI.maximizeWindow()

//Seleccionar "Adm. de Dispositivos Neutrales NO Migrados"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkAdm.deDispositivosNeutralesNOMigrados'))

//Seleccionar "Registro Fallas en Dispos Neutral NO Migrados"
WebUI.click(findTestObject('Object Repository/02-Dashboard/56-Puntos Neutrales/01-Adm. de Dispositivos Neutrales NO Migrados/lnkRegistroFallasenDisposNeutralNOMigrados'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Alta de Sobrante en Dispositivo Neutral"
WebUI.click(findTestObject('Object Repository/02-Dashboard/56-Puntos Neutrales/01-Adm. de Dispositivos Neutrales NO Migrados/01-Registro Fallas en Dispos Neutral NO Migrados/lnkAltadeSobranteenDispositivoNeutral'))

//Cambiar ventana "TELLER"
WebUI.switchToWindowTitle('TELLER')

//Esperar "Monto MN"
WebUI.waitForElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoMN'), 3)

//Setear "Monto MN"
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoMN'), '100')

//Seleccionar "Comentarios"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'))

//Setear "Comentarios"
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'), 'TOMA DE EVIDENCIAS DEPOSITO')

//Seleccionar "boton Drop down ID Dispositivo"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/btnDropdownIdDispositivo'))

//Seleccionar "70111"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/lblidDispositivo70111'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lnkAceptarAlertas'))

//Definir Objeto
Transaccion1 = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion1.split('\\s+')
def trx1 = partes[2]
assert Transaccion1.contains('Txn Completa:')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Switch a la ventana del Dashboard
WebUI.switchToWindowIndex(0)

//Ingresar "TT S" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'TT S '+trx1)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Switch a la ventana "TELLER"
WebUI.switchToWindowIndex(2)

//Esperar "Alta Sobrante en Disp Pn 099 Retiro"
WebUI.waitForElementVisible(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/lblALTAFaltanteenDispPn099Retiro'), 3)

//Verificar "ALTA Sobrante en Disp Pn 099 Retiro"
WebUI.verifyElementVisible(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/lblALTASobranteenDispPn099Retiro'))

//Validar "ALTA Sobrante en Disp Pn 099 Retiro"
def element = WebUI.getText(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/lblALTASobranteenDispPn099Retiro'))
assert element.contains('ALTA Sobrante en Disp Pn 099 Retiro')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}