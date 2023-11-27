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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(2, 1))

WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ejecuta en la linea de comando menu ?1
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?1')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))
//WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Abre la pestaña del menú ?01
WebUI.switchToWindowTitle('Temenos T24')

//Ir a Sucursal piloto
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkSucursalPiloto'))

//Selecciona D2 AUTOMATIZACION DE SUCURSALES
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkD2AutomatizaciondeSucursales'))

//Selecciona CONSULTA OPERATORIA DE FILIALES
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkCONSULTASOPERATORIASDEFILIALES'))

//Selecciona CONSULTAS TOTALES ADMINITRATIVOS
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkCONSULTATOTALESADMINISTRATIVOS'))

//Ir a Detalle de operaciones sin efectivo (Para Filial)
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/lnkDetalleOperacionesSinEfectivoFILIAL'))

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.switchToWindowTitle('Totales Sucursal x Cod Oper.')

//Verifica titulo de Detalle de operaciones sin efectivo
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/lblTituloTotales Sucursal x Cod Oper'))

//Ingresa un monto en ARS
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/txtMonedaValue1'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/txtMonedaValue1'),'ARS')

//Ingresa Sucursal
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/txtSucursalValue2'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/txtSucursalValue2'),'001')

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))
//WebUI.delay(10)

//Espera y verifica que se muestren los registros de la tabla
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/lblCodOperativo'),10)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/lblCodOperativo'))
//WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/lblCantOperaciones'))
//WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/lblDescripcion'))
//WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/lblMonto'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//Maximiza la pantalla
WebUI.maximizeWindow()

//Espera y verifica que se muestren los registros de la tabla
//WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/lblCodOperativo'),10)
//WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/lblCodOperativo'))
//WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/lblCantOperaciones'))
//WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/lblDescripcion'))
//WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/lblMonto'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ver Detalle
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/Totales Sucursal x Cod Oper/btnVerDetalle'))

//Se mueve a la ventana Detalle Transacciones No Efectivo
WebUI.switchToWindowTitle('Detalle Transacciones No Efectivo')

//Espera y verifica si se visualiza la primera columna del registro
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/Detalle Transacciones No Efectivo/lblId'),6)
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/Detalle Transacciones No Efectivo/lblId'))
assert element.contains('Id')

//Ver detalle de la primera transacción
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/Detalle Transacciones No Efectivo/btnVerDetalle'))

//Se mueve a la ventana Compra-Venta
WebUI.switchToWindowTitle('Movimiento de Fondos')
//WebUI.switchToWindowTitle('Compra-Venta')
////Espera y verifica si se visualiza el titulo del detalle
//WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoUSUARIO/Detalle Transacciones No Efectivo/Compra-Venta/lblTituloParametrosGeneralesdelaOperacion'),6)
//def element2 = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoUSUARIO/Detalle Transacciones No Efectivo/Compra-Venta/lblTituloParametrosGeneralesdelaOperacion'))
//assert element2.contains('Parametros Generales de la Operacion')

WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoUSUARIO/Detalle Transacciones No Efectivo/Movimiento de Fondos/lblTransactionType'),6)
def element2 = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoUSUARIO/Detalle Transacciones No Efectivo/Movimiento de Fondos/lblTransactionType'))
assert element2.contains('Transaction Type')

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

