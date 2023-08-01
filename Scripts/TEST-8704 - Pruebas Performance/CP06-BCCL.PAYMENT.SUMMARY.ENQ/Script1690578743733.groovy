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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,25), findTestData('MainData/Users').getValue(2,25))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando ENQ BCCL.PAYMENT.SUMMARY.ENQ
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.PAYMENT.SUMMARY.ENQ')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña BCCL.PAYMENT.SUMMARY.ENQ
WebUI.switchToWindowTitle('BCCL.PAYMENT.SUMMARY.ENQ')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Ejecuta en la linea de comando ENQ BCCL.PAYMENT.SUMMARY.ENQ
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.PAYMENT.SUMMARY.ENQ')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Abre la pestaña BCCL.PAYMENT.SUMMARY.ENQ
WebUI.switchToWindowTitle('BCCL.PAYMENT.SUMMARY.ENQ')

//Verifica titulo ENQ BCCL.E.TT.CASH.DENOM
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.PAYMENT.SUMMARY.ENQ/lblTituloBCCL.PAYMENT.SUMMARY.ENQ'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.PAYMENT.SUMMARY.ENQ/lblTituloBCCL.PAYMENT.SUMMARY.ENQ'))

////Ingresa Estado del Movimiento
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.PAYMENT.SUMMARY.ENQ/txtEstadodelMovimiento2'), 6)
WebUI.click(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.PAYMENT.SUMMARY.ENQ/rbEstadoDelMovimientoDropdown'))
WebUI.setText(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.PAYMENT.SUMMARY.ENQ/txtEstadodelMovimiento2'), 'APLICADO')
WebUI.click(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.PAYMENT.SUMMARY.ENQ/rbEstadoDelMovimientoDropdown'))

//Ingresa Fecha valor
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.PAYMENT.SUMMARY.ENQ/txtFechaValor3'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.PAYMENT.SUMMARY.ENQ/txtFechaValor3'), '20220722')

//Ingresa Codigo Sucursal
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.PAYMENT.SUMMARY.ENQ/txtCodigoSucursal4'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.PAYMENT.SUMMARY.ENQ/txtCodigoSucursal4'), '001')

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Selecciona boton EJECUTAR
WebUI.click(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.PAYMENT.SUMMARY.ENQ/btnEjecutar'))

//Selecciona boton EJECUTAR 708
//WebUI.click(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.PAYMENT.SUMMARY.ENQ-708/btnEjecutar708'))

//Espera y Verifica que se muestren las columnas del registro
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.PAYMENT.SUMMARY.ENQ/lblTituloConsultaPagoHaberesProveedores'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.PAYMENT.SUMMARY.ENQ/lblTituloConsultaPagoHaberesProveedores'))

//Espera y Verifica que se muestren el registro 708
//WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.PAYMENT.SUMMARY.ENQ-708/lblConsultaPagoHaberesProveedores708'),6)
//WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.PAYMENT.SUMMARY.ENQ-708/lblConsultaPagoHaberesProveedores708'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

def element = WebUI.getText(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.PAYMENT.SUMMARY.ENQ/lblTituloConsultaPagoHaberesProveedores'))
assert element.contains('Consulta Pago Haberes/Proveedores')

//ASSERT 708
//def element = WebUI.getText(findTestObject('Object Repository/TEST-8704 - Pruebas Performance/BCCL.PAYMENT.SUMMARY.ENQ-708/lblConsultaPagoHaberesProveedores708'))
//assert element.contains('Consulta Pago Haberes/Proveedores')

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