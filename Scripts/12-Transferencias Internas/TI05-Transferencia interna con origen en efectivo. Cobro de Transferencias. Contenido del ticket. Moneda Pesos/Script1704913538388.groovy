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
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
@Grab('org.jsoup:jsoup:1.14.3')
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(
        2, 3))

WebUI.click(findTestObject('02-Dashboard/lnkTransferenciasInternas'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/11-Transferencias Internas/lnkAltaTransfInternaOrigenEfectivo'))

WebUI.switchToWindowTitle('Movimiento de Fondos')

WebUI.setText(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtSucursalDestino'), '089')

WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtIdOrdenante'))

WebUI.setText(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtIdOrdenante'), '1003174696')

WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtImporte'))

WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/btnDrillDownMotivo'))

WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/lblFAC'))

WebUI.setText(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtIDBeneficiario'), '1003174696')

WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtImporte'))

WebUI.setText(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtImporte'), '100')

WebUI.click(findTestObject('44-TOB/Movimiento de Fondos/btnAceptarRegistroRecarga'))

WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/lnkAceptarAlertas'))

//Captura de Pantalla PDF
//WebUI.switchToWindowTitle('e-forms')
//
//WebUI.maximizeWindow()
//
//CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

////Forzado y verificacion de firma
WebUI.switchToWindowTitle('Movimiento de Fondos')

//VALIDO que la transaccion se haya completado y guardo el FT
Transaccion1 = WebUI.getText(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/lblTxnCompleta'))

// Dividir la cadena por espacios en blanco y tomar el segundo elemento
def partes = Transaccion1.split('\\s+')

def trx1 = partes[2]

assert Transaccion1.contains('Txn Completa:')

//DESLOGUEO
WebUI.switchToWindowIndex(0)

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnLogout'))

//Nos volvemos a loguear para liquidar con cajero para liquidar la transaccion hecha
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 2), findTestData('MainData/Users').getValue(
		2, 2))

//Ejecuta en la linea de comando ENQ BCCL.E.EB.POSTEO.INAU
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.EB.POSTEO.INAU')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña BCCL.E.EB.POSTEO.INAU
WebUI.switchToWindowTitle('BCCL.E.EB.POSTEO.INAU')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Ejecuta en la linea de comando ENQ BCCL.E.EB.POSTEO.INAU
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.EB.POSTEO.INAU')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña BCCL.E.EB.POSTEO.INAU
WebUI.switchToWindowTitle('BCCL.E.EB.POSTEO.INAU')

//Verifica titulo BCCL.E.FIRMAS.FISICA
WebUI.verifyElementVisible(findTestObject('Object Repository/37-Posteo/BCCL.E.EB.POSTEO.INAU/lblTituloBCCL.E.EB.POSTEO.INAU'))

//Maximiza la pantalla
WebUI.maximizeWindow()

//
//Selecciona boton EJECUTAR
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Espera y Verifica que devuelva un registro
WebUI.waitForElementVisible(findTestObject('Object Repository/37-Posteo/BCCL.E.EB.POSTEO.INAU/lblIdTransaccion'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/37-Posteo/BCCL.E.EB.POSTEO.INAU/lblIdTransaccion'))


def element = WebUI.getText(findTestObject('Object Repository/37-Posteo/BCCL.E.EB.POSTEO.INAU/lblIdTransaccion'))
assert element.contains('Id Transaccion')


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


//---------------------------------------------------
//Control Fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

