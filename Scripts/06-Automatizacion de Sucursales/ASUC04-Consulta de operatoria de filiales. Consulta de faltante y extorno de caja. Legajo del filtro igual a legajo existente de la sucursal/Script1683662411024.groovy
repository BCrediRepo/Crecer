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
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(
		2, 3))

WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ejecuta en la linea de comando menu ?323
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?323')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Abre la pestaña del menú ?323
WebUI.switchToWindowTitle('Temenos T24')

//Ir a consulta de operatoria
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkConsultadeoperatoria'))

//Selecciona Consulta de Caja Faltantes Extonos Caja
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkConsultadeCajaFaltantesExtonosCaja'))
//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.switchToWindowTitle('BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA')

//Verifica titulo de Caja Faltantes Extonos Caja
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lbltituloBCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA'))

//Ingresa fecha desde
Label = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblFechaDesdePosicion3'))

if (Label == 'Fecha Desde') {
	WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/txtFechaDesdevalue3'), '20220601')
} else {
	WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/txtCodTransaccionvalue1'), '20220601')
}

//Ingresa Codigo de Transaccion
Label = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblCodTransaccionPosicion1'))

if (Label == 'Cod. Transaccion') {
	WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/txtCodTransaccionvalue1'), '13')
} else {
	WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/txtNroLegajovalue2'), '13')
}

//Borra el campo de sucursal
if (Label == 'Sucursal') {
	WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/txtSucursalvalue5'), '')
} else {
	WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/txtFechaDesdevalue3'), '')
}

//Ingresa Número de Legajo
Label = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblNroLegajoPosicion2'))
if (Label == 'No. Legajo') {
	WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/txtNroLegajovalue2'), '113465')
} else {
	WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/txtFechaDesdevalue3'), '113465')
}

//if (Label == 'No. Legajo') {
//	WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/txtNroLegajovalue2'), '113465')
////} else if (Label != 'No. Legajo') {
////	WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/txtFechaHastavalue3'), '113465')
//} else {
//	WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/txtFechaDesdevalue4'), '113465')
//}

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Presiona Ejecutar
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/btnEjecutar'))
WebUI.delay(20)

//Maximiza la pantalla
WebUI.maximizeWindow()

//Espera y verifica que se muestren los registros de la tabla
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblCuentaInternaCaja'),10)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblCuentaInternaFaltante'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblCuentaInternaCaja'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblDescripcion'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblFecha'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblFilial'))
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblImporte'),10)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblLegajoCajero'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblMoneda'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblTxnId'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblTransaccion'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblTotalExtorno'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblCajero'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ver el primer registro
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/btnVerDetalles'))

//Espera y verifica si se visualiza la primera columna del registro
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/TELLER/lblTransaction Code'),6)
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/TELLER/lblTransaction Code'))
assert element.contains('Transaction Code')


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