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
import java.text.SimpleDateFormat
import java.util.Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(
		2, 3))

WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ejecuta en la linea de comando ENQ BCCL.E.STMS.ENT.BOOK.CA
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.STMS.ENT.BOOK.CA')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))
//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Abre la pestaña del menú Contrasiento total
WebUI.switchToWindowTitle('Contrasiento Total')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Ejecuta en la linea de comando ENQ BCCL.E.STMS.ENT.BOOK.CA
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.STMS.ENT.BOOK.CA')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Abre la pestaña del menú Contrasiento total
WebUI.switchToWindowTitle('Contrasiento Total')

//Verifica titulo Contrasiento total
WebUI.waitForElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/ContrasientoTotal/lblTituloContrasientoTotal'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/ContrasientoTotal/lblTituloContrasientoTotal'))

//Maximiza la pantalla
WebUI.maximizeWindow()

//Ingresa ID de la transacción
WebUI.waitForElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/ContrasientoTotal/txtIdTransaccion'), 6)
WebUI.setText(findTestObject('Object Repository/38-Ajustes Monetarios/ContrasientoTotal/txtIdTransaccion'),'CHG222100002J')

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Click en boton ejecutar
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'),6)
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Espera y comprueba que se muestre la primera columna del registro
WebUI.waitForElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/ContrasientoTotal/lblIdTransaccion'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/ContrasientoTotal/lblIdTransaccion'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//boton ver detalle tx
WebUI.waitForElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/ContrasientoTotal/btnVerDetalleTxn'),6)
WebUI.click(findTestObject('Object Repository/38-Ajustes Monetarios/ContrasientoTotal/btnVerDetalleTxn'))

//Espera y comprueba que se muestre la primera columna del registro
WebUI.waitForElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/Account Charge Request/lblRequestType'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/Account Charge Request/lblRequestType'))
def element = WebUI.getText(findTestObject('Object Repository/38-Ajustes Monetarios/Account Charge Request/lblRequestType'))
assert element.contains('Request Type')

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
