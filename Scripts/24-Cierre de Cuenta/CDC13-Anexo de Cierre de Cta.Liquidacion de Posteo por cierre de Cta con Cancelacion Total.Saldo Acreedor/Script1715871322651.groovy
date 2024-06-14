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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 48), findTestData('MainData/Users').getValue(2, 48))
WebUI.maximizeWindow()

//Ingresar "?1" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?1')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
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

//Esperar "boton Drilldown Moneda"
WebUI.waitForElementVisible(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/btnDrillDownMoneda'), 3)

//Seleccionar "boton Drilldown Moneda"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/btnDrillDownMoneda'))

//Seleccionar "ARS"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/lblARS'))

//Seleccionar "boton Drill down Concepto"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/btnDrillDownConcepto'))

//Seleccionar "18950PME"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/lblConcepto'))

//Setear "Nombre Posteo" 
WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/txtNombrePosteo'), 'PRUEBAS CRECER')

//Setear "Importe"
WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/txtImporte'), '1000')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/lnkAceptarAlertas'))

//Definir Objeto
Transaccion1 = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion1.split('\\s+')
def trx1 = partes[2]
assert Transaccion1.contains('Txn Completa:')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Cerrar Sesion
CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()

//Volver a Logearse con un usuario que pueda hacer las Liquidaciones

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 45), findTestData('MainData/Users').getValue(2, 45))
WebUI.maximizeWindow()

//Ingresar "?1" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?1')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
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

//Obtén el elemento de la tabla
WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
 
//Obtén todas las filas dentro de la tabla
List<WebElement> rows = table.findElements(By.tagName("tr"))
 
//Valor específico que estás buscando
String targetValue = trx1
 
//Variable para rastrear si se encontró el valor específico
boolean foundTargetValue = false
 
//Itera a través de las filas
for (WebElement row : rows) {
	//Obtiene el tercer valor de la fila (índice 2, ya que las listas son base cero)
	WebElement cell = row.findElements(By.tagName("td"))[0]
 
	//Obtiene el texto de la celda
	String cellText = cell.getText()
 
	//Compara el valor de la celda con el valor específico
	if (cellText.equals(targetValue)) {
		foundTargetValue = true
			
		//Obtiene la lista de elementos td
		List<WebElement> tdList = row.findElements(By.tagName("td"))
		
		//Accede al elemento td en la posición 8
		WebElement tdElement = tdList[8]
 
		//Intenta encontrar el elemento 'a' dentro del elemento td
		WebElement liquidar = tdElement.findElement(By.tagName("a"))
 
		//Haz clic en el enlace
		liquidar.click()
		
		break
		
	}
}

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar ventana "Audit"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/lblAudit'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verificar "INAO"
WebUI.verifyElementVisible(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/lblINAO'))

//Validar "INAO"
def element = WebUI.getText(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/lblINAO'))
assert element.contains('INAO')

//Seleccionar "boton Autorizar Registro"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/btnAutorizarRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/lblTxnCompleta'))

//Validar "Txn Completa"
def element2 = WebUI.getText(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/lblTxnCompleta'))
assert element2.contains('Txn Completa')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}