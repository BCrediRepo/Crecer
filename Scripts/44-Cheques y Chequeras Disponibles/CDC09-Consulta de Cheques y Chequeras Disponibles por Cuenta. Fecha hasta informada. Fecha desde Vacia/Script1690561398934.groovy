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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando menu ?327
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?327')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Abre la pestaña del menú ?01
WebUI.switchToWindowTitle('Temenos T24')

//Selecciona CHEQUERAS
WebUI.click(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/Temenos T24/lnkChequeras'))

//Selecciona consulta
WebUI.click(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/Temenos T24/lnkConsulta'))

//Selecciona CONSULTA DE CHEQUERAS Y CHEQUES POR CUENTA
WebUI.click(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/Temenos T24/lnkConsultadeChequerasyChequesporCuenta'))

//Abre la pestaña de cheques y chequeras por cuenta
WebUI.switchToWindowTitle('BCCL.E.CONS.CHQ.CUENTA.FIL')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowTitle('Temenos T24')

//Selecciona CONSULTA DE CHEQUERAS Y CHEQUES POR CUENTA
WebUI.click(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/Temenos T24/lnkConsultadeChequerasyChequesporCuenta'))

//Abre la pestaña de cheques y chequeras por cuenta
WebUI.switchToWindowTitle('BCCL.E.CONS.CHQ.CUENTA.FIL')

//Verifica titulo de cheques y chequeras
WebUI.waitForElementVisible(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/lblTituloBCCL.E.CONS.CHQ.CUENTA.FIL'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/lblTituloBCCL.E.CONS.CHQ.CUENTA.FIL'))

//Ingresa Cuenta Debito
WebUI.waitForElementVisible(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/txtCuentaDB1'), 6)
WebUI.setText(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/txtCuentaDB1'), '00010623433')

//Ingresa FECHA DESDE
WebUI.waitForElementVisible(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/txtFechaDesde2'), 6)
WebUI.setText(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/txtFechaDesde2'), '')

//Ingresa FECHA HASTA
WebUI.waitForElementVisible(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/txtFechaHasta3'), 6)
WebUI.setText(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/txtFechaHasta3'), '20220728')

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Selecciona boton EJECUTAR
WebUI.click(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/btnEjecutar'))
WebUI.delay(15)

//Espera y Verifica mensaje de error
WebUI.waitForElementVisible(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/lblFECHADESDEESOBLIGATORIASIFECHAHASTAESINGRESADA'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/lblFECHADESDEESOBLIGATORIASIFECHAHASTAESINGRESADA'))
def element = WebUI.getText(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/lblFECHADESDEESOBLIGATORIASIFECHAHASTAESINGRESADA'))
assert element.contains('FECHA DESDE ES OBLIGATORIA SI FECHA HASTA ES INGRESADA')

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