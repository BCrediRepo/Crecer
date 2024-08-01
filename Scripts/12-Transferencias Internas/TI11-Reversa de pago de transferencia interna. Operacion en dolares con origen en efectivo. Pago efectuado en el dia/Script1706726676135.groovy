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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
@Grab(module = 'jsoup', version = '1.14.3', group = 'org.jsoup')
import org.jsoup.Jsoup as Jsoup
import org.jsoup.nodes.Document as Document

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(
		2, 3))

//Ir a transferencias internas, alta transferencia interna origen efectivo
def menuDesplegable = ["Transferencias Internas"]
def link = "Alta Transf. Interna Origen Efectivo"

CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowTitle('Movimiento de Fondos')
WebUI.maximizeWindow()

//Ingresa datos (sucursal, id ordenante, importe, motivo, beneficiario)
WebUI.setText(findTestObject('12-Transferencias Internas/Movimiento de Fondos/txtSucursalDestino'), '089')
WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtIdOrdenante'))
WebUI.setText(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtIdOrdenante'), '1003174696')
WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtMoneda'))
WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/btnDrillDownMoneda'))
WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/lblUSD'))
WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtImporte'))
WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/btnDrillDownMotivo'))
WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/lblFAC'))
WebUI.setText(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtIdBeneficiario'), '1003174696')
WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtImporte'))
WebUI.setText(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtImporte'), '1')

//Acepto registro y alertas
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verificacion de txn finalizada
WebUI.switchToWindowTitle('Movimiento de Fondos')
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
Completada = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert Completada.contains('Txn Completa:')

//Forzado y verificacion de firma
WebUI.switchToWindowTitle('Movimiento de Fondos')
WebUI.maximizeWindow()

//VALIDO que la transaccion se haya completado y guardo el FT
Transaccion = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))

// Dividir la cadena por espacios en blanco y tomar el segundo elemento
def partes = Transaccion.split('\\s+')
def trx1 = partes[2]
assert Transaccion.contains('Txn Completa:')

//DESLOGUEO
WebUI.switchToWindowIndex(0)
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnLogout'))

//Nos volvemos a loguear con cajero para liquidar la transaccion hecha
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

//Maximiza la pantalla
WebUI.maximizeWindow()

//Limpieza
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Verifica titulo BCCL.E.FIRMAS.FISICA
WebUI.verifyElementVisible(findTestObject('Object Repository/37-Posteo/BCCL.E.EB.POSTEO.INAU/lblTituloBCCL.E.EB.POSTEO.INAU'))

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
WebUI.maximizeWindow()

//Autoriza registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAutorizaRegistro'))
WebUI.switchToWindowIndex(2)
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.switchToWindowTitle('Movimiento de Fondos')

//Valida la tx
assert Transaccion.contains('Txn Completa:') == true

//REVERSAMOS
//Ejecuta en la linea de comando ENQ BCCL.E.EB.CONS.REVE
WebUI.switchToWindowIndex(0)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.EB.CONS.REVE')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

///Abre la pestaña BCCL.E.EB.CONS.REVE
WebUI.switchToWindowTitle('BCCL.E.EB.CONS.REVE')
WebUI.maximizeWindow()

//Seteo de Datos "Usuario", "Nro. Contrato"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Usuario', 'B.0489')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro. Contrato', trx1)

//Selecciona botón Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Espera y verifica que se muestre al menos 1 dato de la tx buscada
WebUI.waitForElementVisible(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/lblIdOperacion'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/lblIdOperacion'))
def element2 = WebUI.getText(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/lblIdOperacion'))
assert element2.contains('Id Operacion')

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Selecciona boton Reversar
WebUI.click(findTestObject('Object Repository/55-Reversos/BCCL.E.EB.CONS.REVE/btnReversar'))

//Reversa el registro
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito De Efectivo En Buzon A Toda Hora/TELLER/btnReversarRegistro'))

//Acepto alertas
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'),6)
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Espera y recibe mensaje de tx completa reversada
assert Transaccion.contains('Txn Completa:')

//Toma screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se inserta el numero de transaccion en el input "Nota de Debito por Ajuste"
WebUI.setText(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/inputNotadeDebitoporAjuste'), trx1)

//Se clickea en btn "Ver Un Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

//Toma screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se clickea en btn "Audit"
WebUI.click(findTestObject('38-Ajustes Monetarios/ENQ BCCL.E.EB.CONS.REVE/btnAudit'))

//Toma screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se realiza un assert del estado del registro "REVE"
WebUI.waitForElementVisible(findTestObject('Object Repository/12-Transferencias Internas/Movimiento de Fondos/lblEstadodelRegistroREVE'), 3)
WebUI.verifyElementVisible(findTestObject('Object Repository/12-Transferencias Internas/Movimiento de Fondos/lblEstadodelRegistroREVE'))
def reversa = WebUI.getText(findTestObject('Object Repository/12-Transferencias Internas/Movimiento de Fondos/lblEstadodelRegistroREVE'))
assert reversa.contains('REVE')

//----------------------------------------------Control de fin de script----------------------------------------------//

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}