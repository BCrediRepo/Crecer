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
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.support.ui.Select
//ESTE CASO REVERSA TXN de la tabla BCCL.E.EB.CONS.REVE, Para esto se deben generar transacciones que terminen en esa tabla. Por ejemplo NOTAS DE DEBITO SIN IMPUESTOS
//para la cuenta 11190118359

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()

//Generamos una nota de debito S/Imp para reversar
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkAjustesMonetarios'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/36-Ajustes Monetarios/lnkNotaDebitoAjustes'))

//Switch a la ventana Movimiento de Fondos
WebUI.switchToWindowTitle('Movimiento de Fondos')
WebUI.maximizeWindow()

//Ingresamos los datos para la nota de debito por ajustes
WebUI.setText(findTestObject('Object Repository/38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/txtNroCuenta'), '00730029258')
WebUI.click(findTestObject('Object Repository/38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/txtImporte'))
WebUI.setText(findTestObject('Object Repository/38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/txtImporte'), '1000')
WebUI.click(findTestObject('Object Repository/38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/btnListaRegistros'))
WebUI.click(findTestObject('Object Repository/38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/lblNotaDebAjs'))

//Aceptamos el registro
WebUI.click(findTestObject('Object Repository/38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/btnValidarRegistro'))
WebUI.click(findTestObject('Object Repository/38-Ajustes Monetarios/01 - Nota de Debito por Ajuste/btnAceptarRegistro'))
WebUI.click(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lnkAceptarAlertas'))

//VALIDO que la transaccion se haya completado y guardo el FT
Transaccion1 = WebUI.getText(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lblTxnCompleta'))
	
// Dividir la cadena por espacios en blanco y tomar el segundo elemento
def partes = Transaccion1.split('\\s+')

def trx1 = partes[2]

assert Transaccion1.contains('Txn Completa:')

//Switch al dashboard
WebUI.switchToWindowIndex(0)

//Se accede al menu
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.EB.CONS.REVE')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana de busqueda de consulta
WebUI.switchToWindowTitle('BCCL.E.EB.CONS.REVE')

//Seteo de Datos "Usuario"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Usuario', 'B.0073')

//Seleccionar Ejecutar
WebUI.click(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lnkEjecutar'))

//Validacion
//WebUI.waitForElementVisible(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lblHora'), 10)
//WebUI.waitForElementVisible(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lnkReversar'), 6)
//WebUI.click(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lnkReversar'))

// Valor específico que estás buscando
String targetValue = trx1

WebUI.delay(10)

def buscarElementoEnTabla(String targetValue) {
// Obtén el elemento de la tabla
WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))

// Obtén todas las filas dentro de la tabla
List<WebElement> rows = table.findElements(By.tagName("tr"))

// Itera a través de las filas
for (WebElement row : rows) {
	// Obtiene el tercer valor de la fila (índice 2, ya que las listas son base cero)
	WebElement cell = row.findElements(By.tagName("td"))[1]
 
	// Obtiene el texto de la celda
	String cellText = cell.getText()
 
	// Compara el valor de la celda con el valor específico
	if (cellText.equals(targetValue)) {
		// Obtiene la lista de elementos td
		List<WebElement> tdList = row.findElements(By.tagName("td"))
		
		// Accede al elemento td en la posición 8
		WebElement tdElement = tdList[17]
		
		WebElement lnkreversar = tdElement.findElement(By.tagName("a"))
		
		//Seleccionar elemento 'img'
		lnkreversar.click()
		
	// Utiliza Select para interactuar con el comboBox
	//def select = new Select(lnkreversar)
	//select.selectByVisibleText("Reversar")
	// Encuentra el elemento 'img' dentro del enlace 'a'
	return true

	}
}
return false
}


// Lógica para buscar el elemento en la tabla
def encontrado = false
 
// Bucle para buscar en múltiples páginas
while (!encontrado) {
	// Lógica para buscar el elemento en la tabla
	encontrado = buscarElementoEnTabla(targetValue)
	// Si no se encontró el valor, hacer clic en el botón "Siguiente" y buscar nuevamente
	if (!encontrado) {
		// Realiza la búsqueda nuevamente después de hacer clic en "Siguiente"
		WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnSiguiente'))
		// Espera a que la nueva página se cargue completamente
		WebUI.delay(2)
	}
}

//Switch a la ventana de busqueda de reversa
WebUI.switchToWindowIndex(3)
WebUI.waitForElementVisible(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/btnReversarRegistro'), 6)
WebUI.click(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/btnReversarRegistro'))

//try {
	//WebUI.waitForElementVisible(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lblTxnCompleta'), 6)
	//WebUI.verifyElementPresent(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lblTxnCompleta'), 6)
//}
//catch (Exception e) {
	//WebUI.waitForElementVisible(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lnkAceptarAlertas'), 6)
	//WebUI.click(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lnkAceptarAlertas'))
	//WebUI.waitForElementVisible(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lblTxnCompleta'), 6)
	//WebUI.verifyElementPresent(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lblTxnCompleta'), 6)
//}

//Verificar si el aceptar alerta está presente
if (WebUI.verifyElementPresent(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lnkAceptarAlertas'), 5, FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lnkAceptarAlertas'))
}


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

