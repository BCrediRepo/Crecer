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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 21), findTestData('MainData/Users').getValue(2, 21))

WebUI.maximizeWindow()

//Ejecuta en la linea de comando menu ?323
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?323')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña del menú ?323
WebUI.switchToWindowTitle('Temenos T24')

//Ir a consulta de operatoria
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkConsultadeoperatoria'))

//Selecciona Consulta de Caja Faltantes Extonos Caja
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkConsultadeCajaFaltantesExtonosCaja'))

WebUI.switchToWindowTitle('BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA')

//Verifica titulo de Caja Faltantes Extonos Caja
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lbltituloBCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA'))
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Desde', '20230824')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cod. Transaccion', '4')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '089')
//CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('No. Legajo', '13')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Presiona Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))
WebUI.delay(10)

//Maximiza la pantalla
WebUI.maximizeWindow()

//Espera y verifica que se muestren los registros de la tabla
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblCuentaInternaCaja'),20)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblCuentaInternaFaltante'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblCuentaInternaCaja'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblDescripcion'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblFecha'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblFilial'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblImporte'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblLegajoCajero'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblMoneda'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblTxnId'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblTransaccion'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblTotalExtorno'))
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL.E.TT.CONSULTA.FAL.SOB.EXT.CAJA/lblCajero'))


// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

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