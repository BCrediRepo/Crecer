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
import java.text.SimpleDateFormat
import java.util.Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,11))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando menu ?327
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CONS.CHQ.CUENTA.FIL')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

////Abre la pestaña del menú ?01
//WebUI.switchToWindowTitle('Temenos T24')
//
////Selecciona CHEQUERAS
//WebUI.click(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/Temenos T24/lnkChequeras'))
//
////Selecciona consulta
//WebUI.click(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/Temenos T24/lnkConsulta'))
//
////Selecciona CONSULTA DE CHEQUERAS Y CHEQUES POR CUENTA
//WebUI.click(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/Temenos T24/lnkConsultadeChequerasyChequesporCuenta'))

//Abre la pestaña de cheques y chequeras por cuenta
WebUI.switchToWindowTitle('BCCL.E.CONS.CHQ.CUENTA.FIL')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

//Se accede al menu
WebUI.switchToWindowIndex(0)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CONS.CHQ.CUENTA.FIL')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//WebUI.switchToWindowTitle('Temenos T24')

////Selecciona CONSULTA DE CHEQUERAS Y CHEQUES POR CUENTA
//WebUI.click(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/Temenos T24/lnkConsultadeChequerasyChequesporCuenta'))

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
WebUI.setText(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/txtFechaDesde2'), '20220729')

//Ingresa FECHA HASTA 
WebUI.waitForElementVisible(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/txtFechaHasta3'), 6)
WebUI.setText(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/txtFechaHasta3'), '20220729')

//Ingresa CHEQUE DESDE
WebUI.waitForElementVisible(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/txtChequeDesde4'), 6)
WebUI.setText(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/txtChequeDesde4'), '#@$&!')

//Ingresa CHEQUE HASTA
WebUI.waitForElementVisible(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/txtChequeHasta5'), 6)
WebUI.setText(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/txtChequeHasta5'), '#@$&!')

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Selecciona boton EJECUTAR
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))
WebUI.delay(15)

//Espera y Verifica Mensaje de Error
WebUI.waitForElementVisible(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/lblNOSEENCONTRARONREGISTROS'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/lblNOSEENCONTRARONREGISTROS'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

def element = WebUI.getText(findTestObject('Object Repository/45-Cheques y Chequeras Disponibles/BCCL.E.CONS.CHQ.CUENTA.FIL/lblNOSEENCONTRARONREGISTROS'))
assert element.contains('NO SE ENCONTRARON REGISTROS')

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