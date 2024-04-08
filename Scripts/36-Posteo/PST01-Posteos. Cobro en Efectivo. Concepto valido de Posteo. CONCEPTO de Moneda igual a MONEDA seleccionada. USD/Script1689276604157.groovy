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
import org.openqa.selenium.Keys as Keys
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
@Grab('org.jsoup:jsoup:1.14.3')
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 14), findTestData('MainData/Users').getValue(
		2, 14))

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

//Selecciona POSTEO PLANTA CAJA
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/lnkPOSTEOPLANTACAJA'))

//Selecciona POSTEO
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/lnkPOSTEO'))

//Ir a cobro en Efectivo
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/lnkCOBROENEFECTIVO'))

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se mueve a la ventana Movimiento de Fondos
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Maximiza la pantalla
WebUI.maximizeWindow()

//Verifica titulo de Detalle de operaciones sin efectivo
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/lblTituloPosteoCobroenEfectivo'))

//Ingresa moneda en USD
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/txtMoneda'),6)
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/btnMonedaDropdown'))
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/txtMoneda'),"USD")
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/btnMonedaDropdown'))

//Ingresa CONCEPTO 18030PMI (Legales - Cobranza en efectivo)
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/txtConcepto'),6)
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/btnConceptoDropdown'))
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/txtConcepto'),'18030PMI')
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/btnConceptoDropdown'))

//Ingreso nombre posteo
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/txtNombrePosteo'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/txtNombrePosteo'),'COBRO EN EFECTIVO')

//Ingresa monto
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/txtImporte'),6)
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/txtImporte'),'5')

//Validar la operación
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/btnValidar'))

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click Aceptar
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/btnAceptar'))

//Click Aceptar Alertas
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/btnAceptarAlertas'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/PosteoPlantaCaja/Posteo/Cobro en Efectivo/lblTxnCompleta'))
// Dividir la cadena por espacios en blanco y tomar el segundo elemento
def partes = element.split('\\s+')
def trx1 = partes[2]
assert element.contains('Txn Completa:')


//Me deslogueo para poder autorizar la transaccion con otro usuario
WebUI.switchToWindowIndex('0')

WebUI.click(findTestObject('02-Dashboard/btnLogout'))

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 49), findTestData('MainData/Users').getValue(
		2, 49))

WebUI.click(findTestObject('02-Dashboard/lnkPosteo'))

WebUI.click(findTestObject('02-Dashboard/35-Posteos/lnkTransaccionesPendientesdeLiquidacion'))

WebUI.switchToWindowTitle('BCCL.E.EB.POSTEO.INAU')

// Obtén el elemento de la tabla
WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
 
// Obtén todas las filas dentro de la tabla
List<WebElement> rows = table.findElements(By.tagName("tr"))
 
// Valor específico que estás buscando
String targetValue = trx1
 
// Variable para rastrear si se encontró el valor específico
boolean foundTargetValue = false
 
// Itera a través de las filas
for (WebElement row : rows) {
	// Obtiene el tercer valor de la fila (índice 2, ya que las listas son base cero)
	WebElement cell = row.findElements(By.tagName("td"))[0]
 
	// Obtiene el texto de la celda
	String cellText = cell.getText()
 
	// Compara el valor de la celda con el valor específico
	if (cellText.equals(targetValue)) {
		foundTargetValue = true
			
		// Obtiene la lista de elementos td
		List<WebElement> tdList = row.findElements(By.tagName("td"))
		
		// Accede al elemento td en la posición 8
		WebElement tdElement = tdList[8]
 
		// Intenta encontrar el elemento 'a' dentro del elemento td
		WebElement liquidar = tdElement.findElement(By.tagName("a"))
 
		// Haz clic en el enlace
		liquidar.click()
		
		break
		
	}
}


WebUI.switchToWindowTitle('Movimiento de Fondos')

WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/btnAutorizar'))

WebUI.switchToWindowIndex(2)

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.switchToWindowTitle('Movimiento de Fondos')

Transaccion2 = WebUI.getText(findTestObject('37-Posteo/Movimiento de Fondos/lblTxnCompleta'))

assert Transaccion2.contains('Txn Completa:') == true

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