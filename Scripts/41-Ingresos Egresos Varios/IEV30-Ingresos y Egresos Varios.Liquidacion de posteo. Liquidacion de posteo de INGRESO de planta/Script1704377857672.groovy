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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 20), findTestData('MainData/Users').getValue(2, 20))

WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ejecuta en la linea de comando menu ?1
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?1')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Abre la pestaña del menú ?01
WebUI.switchToWindowTitle('Temenos T24')

//Maximiza la pantalla
WebUI.maximizeWindow()

//Ir a Sucursal piloto
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkSucursalPiloto'))

//Selecciona D2 AUTOMATIZACION DE SUCURSALES
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkD2AutomatizaciondeSucursales'))

//Selecciona INGRESOS Y EGRESOS
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkINGRESOSYEGRESOS'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ir a INGRESOS De PLANTA
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkINGRESOSDEPLANTA'))
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Maximiza la pantalla
WebUI.maximizeWindow()

//Verifica titulo
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/lnlTituloIngresosVariosdeCaja'))

//Selecciona moneda USD
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/txtMoneda'),6)
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/btnSeleccionMoneda'))
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/rbMonedaARS'))

//Ingresa importe 10
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/txtImporte'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/txtImporte'),'10')

//Ingresa nombre posteo
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/txtNombrePosteo'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/txtNombrePosteo'),'Prueba auto')

//Ingresa concepto 18108IEI (Ingresos varios posteo)
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/txtConcepto'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/txtConcepto'),'18108IEI')

//Click en Validar
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/btnValidarRegistro'))
WebUI.delay(3)

////Selecciona que SI es socio
//WebUI.waitForElementPresent(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/cbSocioNOSI'), 6)
//WebUI.selectOptionByIndex(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/cbSocioNOSI'), 2)
//
////Ingresa persona fisica id ordenante 1003589918
//WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/txtIdOrdenante'),6)
//WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/txtIdOrdenante'),'1003589918')

//Click en Validar
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/btnValidarRegistro'))
WebUI.delay(3) 

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en Aceptar Registro
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/btnAceptarRegistro'))
WebUI.delay(3)

//Click en Aceptar Alertas
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/btnAceptarAlertas'))
WebUI.delay(10)

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/IngresodePlanta/lblTxnCompleta'))
assert element.contains('Txn Completa:')

// Imprimir el numero de operacion en consola
println("El ID de la txt es: " + element)
 
//Dividir la oración en palabras individuales utilizando el espacio como separador
String[] palabras = element.split(" ");
 
// Obtener la tercera palabra (índice 2 ya que los índices comienzan en 0 en arrays)
String terceraPalabra = palabras[2];
 
// Imprimir la tercera palabra seleccionada
println("La tercera palabra es: " + terceraPalabra);



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