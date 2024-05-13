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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(2, 1))
WebUI.maximizeWindow()

//Ingresar "?1" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?1')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')

//Seleccionar "Sucursal Piloto"
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/07-Temenos T24/lnkSucursalPiloto'))

//Seleccionar "D3 - CC1"
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/07-Temenos T24/Sucursal Piloto/lnkD3-CC1'))

//Seleccionar "CC1 - Emision Chequera"
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/07-Temenos T24/Sucursal Piloto/D3 - CC1/lnkCC1-EmisionChequera'))

//Seleccionar "Consultas Chequera Boletera"
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/07-Temenos T24/Sucursal Piloto/D3 - CC1/CC1 - Emision Chequera/lnkConsultasChequeraBoletera'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "BAJA DE CHEQUES"
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/07-Temenos T24/Sucursal Piloto/D3 - CC1/CC1 - Emision Chequera/Consultas Chequera Boletera/lnkBAJADECHEQUES'))

//Cambiar ventana "BCCL.CQ.BAJA.CHEQUES"
WebUI.switchToWindowTitle('BCCL.CQ.BAJA.CHEQUES')

//Filtro limpieza
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(1)

//Seleccionar "BAJA DE CHEQUES"
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/07-Temenos T24/Sucursal Piloto/D3 - CC1/CC1 - Emision Chequera/Consultas Chequera Boletera/lnkBAJADECHEQUES'))

//Cambiar ventana "BCCL.CQ.BAJA.CHEQUES"
WebUI.switchToWindowTitle('BCCL.CQ.BAJA.CHEQUES')

//Setear Número de Cuenta
WebUI.setText(findTestObject('Object Repository/11-Emision Chequera/09-BCCL.CQ.BAJA.CHEQUES/txtCuenta'), '00010001266')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Baja Cheque"
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/09-BCCL.CQ.BAJA.CHEQUES/lnkBajaCheque'))

//Cambiar ventana "BCCL.CQ.CHEQUES"
WebUI.switchToWindowTitle('BCCL.CQ.CHEQUES')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Reversar Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnReversarRegistro'))

//Definir Objeto
Transaccion = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion.split('\\s+')
def trx1 = partes[2]
assert Transaccion.contains('Txn Completa:')

//Setear trx1 en "Baja de Cheques/Form.Cert. ONP"
WebUI.setText(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/txtFaltantesenDispositivosPn'), trx1)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Ver Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnVerRegistro'))

//Verificar Estado Normal
WebUI.verifyElementVisible(findTestObject('Object Repository/11-Emision Chequera/10-BCCL.CQ.CHEQUES/lblNormal'))

//Validar Estado Normal
def element2 = WebUI.getText(findTestObject('Object Repository/11-Emision Chequera/10-BCCL.CQ.CHEQUES/lblNormal'))
assert element2.contains('Normal')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}