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

//Esperar el merge de Gino

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 45), findTestData('MainData/Users').getValue(2, 45))
WebUI.maximizeWindow()

//Ingresar "?1" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?1')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton de buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')

//Seleccionar "Sucursal Piloto"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/07-Temenos T24/lnkSucursalPiloto'))

//Seleccionar "D2 - Posteo"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/07-Temenos T24/Sucursal Piloto/lnkD2-Posteo'))

//Seleccionar "POSTEO"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/07-Temenos T24/Sucursal Piloto/D2 - Posteo/lnkPOSTEO'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "PAGO EN EFECTIVO"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/07-Temenos T24/Sucursal Piloto/D2 - Posteo/POSTEO/lnkPagoEnEfectivo'))

//Cambiar ventana "Movimiento de Fondos"
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Maximizar pantalla
WebUI.maximizeWindow()

//Seleccionar "DrillDown Moneda"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/btnDrillDownMoneda'))
	
//Seleccionar "ARS"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/lblARS'))

//Seleccionar "DrillDown Concepto"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/btnDrillDownConcepto'))

//Seleccionar Concepto "18950PME"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/lblConcepto'))

//Setear "PRUEBAS CRECER" en Nombre Posteo
WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/txtNombrePosteo'), 'PRUEBAS CRECER')

//Setear "1000" en Importe
WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/txtImporte'), '1000')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "btnAceptarelregistro"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/lnkAceptarAlertas'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/lblTxnCompleta'))

//Cambiar ventana "T24 - Fil.019 La Plata"
WebUI.switchToWindowTitle('T24 - Fil.019 La Plata')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton de buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')

//Seleccionar "Sucursal Piloto"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/07-Temenos T24/lnkSucursalPiloto'))

//Seleccionar "D2 - Posteo"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/07-Temenos T24/Sucursal Piloto/lnkD2-Posteo'))

//Seleccionar "POSTEO"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/07-Temenos T24/Sucursal Piloto/D2 - Posteo/lnkPOSTEO'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "LIQUIDAR POSTEO EN CAJA"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/07-Temenos T24/Sucursal Piloto/D2 - Posteo/POSTEO/lnkLiquidarPosteoEnCaja'))

//Cambiar ventana "BCCL.E.EB.POSTEO.INAU"
WebUI.switchToWindowTitle('BCCL.E.EB.POSTEO.INAU')

//Maximizar pantalla
WebUI.maximizeWindow()

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Liquidar"
WebUI.click()

//Cambiar ventana "Movimiento de Fondos"
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Seleccionar "Audit"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/lblAudit'))

//Verificar "Autorizado Por
//Validar "Autorizado Por"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}
