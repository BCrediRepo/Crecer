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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 31), findTestData('MainData/Users').getValue(2, 31))
WebUI.maximizeWindow()

//Ingresar "TELLER,RECEPCION.REME.OBANCO I F3" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'TELLER,RECEPCION.REME.OBANCO I F3')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "TELLER"
WebUI.switchToWindowTitle('TELLER')

//Esperar "Monto DB"
WebUI.waitForElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoMN'), 3)

//Setear "Monto DB"
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoMN'), '100')

//Seleccionar "txtComentarios"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'))

//Setear "Comentarios"
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'), 'PRUEBAS CRECER')

//Seleccionar "boton Dropdown Destino"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnDropdownDestino-Origen'))

//Seleccionar "TF"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTF'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Denominaciones DB"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lblDenominaciones'))

//Setear 1 en la posicion de CantidadCien
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtCantidadCien'),'1')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnAceptarRegistro'))

//Definir Objeto
Transaccion1 = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion1.split('\\s+')
def trx1 = partes[2]
assert Transaccion1.contains('Txn Completa:')

//Switch a la ventana del Dashboard
WebUI.switchToWindowIndex(0)

//Ingresar "ENQ BCCL.E.REVE.RECEPCION" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.REVE.RECEPCION')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "BCCL.E.REVE.RECEPCION"
WebUI.switchToWindowTitle('BCCL.E.REVE.RECEPCION')

//Filtro limpieza
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "BCCL.E.REVE.RECEPCION"
WebUI.switchToWindowTitle('BCCL.E.REVE.RECEPCION')

//Maximizar pantalla
WebUI.maximizeWindow()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Esperar 10 segundos
WebUI.delay(10)

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

//Switch a la nueva ventana "TELLER"
WebUI.switchToWindowIndex(2)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Reversar Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnReversarRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Validar "Txn Completa"
def element = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))
assert element.contains('Txn Completa')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}