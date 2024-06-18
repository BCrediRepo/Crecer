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
import org.openqa.selenium.support.ui.ExpectedConditions as EC
import org.openqa.selenium.support.ui.WebDriverWait

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(
        2, 3))

WebUI.click(findTestObject('02-Dashboard/lnkTransferenciasInternas'))

WebUI.click(findTestObject('02-Dashboard/11-Transferencias Internas/lnkAltaTransfInternaOrigenCuenta'))

WebUI.switchToWindowTitle('Movimiento de Fondos')

WebUI.setText(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtSucursalDestino'), '089')

WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtImporte'))

WebUI.setText(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtIDOrdenante'), '1003174696')

WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtImporte'))

WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/btnDrillDownCtaDebito'))

WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/lblCtaDebito'))

WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/Movimiento de Fondos/btnDrillDownMotivo'))

WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/Movimiento de Fondos/lblVarios'))

WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtImporte'))

WebUI.setText(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtImporte'), '15')

WebUI.setText(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtIDBeneficiario'), '1003174696')

WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtImporte'))

WebUI.click(findTestObject('44-TOB/Movimiento de Fondos/btnAceptarRegistroRecarga'))

WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/lnkAceptarAlertas'))

//Forzado y verificacion de firma
WebUI.switchToWindowTitle('Verificacion de Firmas - Fil.089 M.del Plata Ct')

WebUI.selectOptionByIndex(findTestObject('44-TOB/Verificacion de Firmas/cbxAccion'), 2)

WebUI.click(findTestObject('44-TOB/Verificacion de Firmas/btnAceptar'))

WebUI.verifyElementVisible(findTestObject('44-TOB/Verificacion de Firmas/lblFinalizada'))

Finalizada = WebUI.getText(findTestObject('44-TOB/Verificacion de Firmas/lblFinalizada'))

WebUI.verifyElementVisible(findTestObject('44-TOB/Verificacion de Firmas/lblAutorizada'))

Autorizada = WebUI.getText(findTestObject('44-TOB/Verificacion de Firmas/lblAutorizada'))

assert Finalizada == 'FINALIZADA'

assert Autorizada == 'AUTORIZADA'

//GUARDAR EL NUM DE TXN
Ft = WebUI.getText(findTestObject('Object Repository/12-Transferencias Internas/Movimiento de Fondos/lblFT'))

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verificacion de txn finalizada
WebUI.switchToWindowTitle('Movimiento de Fondos')

WebUI.verifyElementVisible(findTestObject('12-Transferencias Internas/Movimiento de Fondos/lblTxnCoimpleta'))

//VALIDO que la transaccion se haya completado y guardo el FT
Transaccion1 = WebUI.getText(findTestObject('12-Transferencias Internas/Movimiento de Fondos/lblTxnCoimpleta'))

// Dividir la cadena por espacios en blanco y tomar el segundo elemento
def partes = Transaccion1.split('\\s+')
def trx1 = partes[2]
assert Transaccion1.contains('Txn Completa:')

//Cierro las ventanas que ya no se usan y van a interceder con las proximas por tener el mismo nombre
WebUI.closeWindowTitle('Movimiento de Fondos')
WebUI.closeWindowTitle('Verificacion de Firmas - Fil.089 M.del Plata Ct')
//WebUI.closeWindowTitle('e-forms')

//Vuelvo al dashboard para ingresar al link de pago de TI
WebUI.switchToWindowIndex(0)
WebUI.click(findTestObject('Object Repository/02-Dashboard/11-Transferencias Internas/lnkPagoTransfInternaPosteo'))

WebUI.switchToWindowTitle('BCCL.E.TINTERNAS.APAGAR')

// Valor específico que estás buscando
String targetValue = trx1

//Esta funcion es invocada cuando se pregunta si el elemento que se quiere encontrar fue localizado en la tabla. Retorna un valor boolean
def buscarElementoEnTabla(String targetValue) {
	//Itero en la tabla buscado la FT originada y cliqueando en PAGAR
	// Obtén el elemento de la tabla
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	// Obtén todas las filas dentro de la tabla
	List<WebElement> rows = table.findElements(By.tagName("tr"))
    // Itera a través de las filas
    for (WebElement row : rows) {
        // Obtiene el tercer valor de la fila (índice 1, ya que las listas son base cero)
        WebElement cell = row.findElements(By.tagName("td"))[1]
        // Obtiene el texto de la celda
        String cellText = cell.getText()
		println(cellText)
		println(targetValue)
        // Compara el valor de la celda con el valor específico
        if (cellText.equals(targetValue)) {
            // Realiza las acciones necesarias si se encuentra el valor
            List<WebElement> tdList = row.findElements(By.tagName("td"))
            WebElement tdElement = tdList[13]
            WebElement pagar = tdElement.findElement(By.tagName("a"))
            pagar.click()
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
		WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/BCCL.E.TINTERNAS.APAGAR/btnSiguiente'))
		// Espera a que la nueva página se cargue completamente
		WebUI.delay(2)	
	}
}


WebUI.switchToWindowTitle('Movimiento de Fondos')

WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/Movimiento de Fondos/btnAceptarRegistro'))

WebUI.click(findTestObject('12-Transferencias Internas/Movimiento de Fondos/lnkAceptarAlertas'))

WebUI.switchToWindowTitle('Movimiento de Fondos')

Transaccion2 = WebUI.getText(findTestObject('Object Repository/12-Transferencias Internas/Movimiento de Fondos/lblTxnCoimpleta'))

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

