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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 2), findTestData('MainData/Users').getValue(2, 2))
WebUI.maximizeWindow()

//Seleccionar "Pases"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkPases'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Solicitud de Pase Entre Cajas"
WebUI.click(findTestObject('Object Repository/02-Dashboard/55-Pases Entre Cajas/lnkSolicituddePaseEntreCajas'))

//Cambiar ventana "TELLER"
WebUI.switchToWindowTitle('TELLER')

//Esperar "boton De la Caja"
WebUI.waitForElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/btnDropdownMoneda'), 3)

//Seleccionar "boton De la Caja"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnDropdownMoneda'))

//Selecciono la cuarta caja
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/lblDelaCajaCuartaCaja'))

//Esperar "boton Moneda"
WebUI.waitForElementVisible(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/btnDropdownMoneda'), 3)

//Seleccionar "boton Moneda"
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/btnDropdownMoneda'))

//Seleccionar "EUR"
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/lblEUR'))

//Esperar "Monto ME"
WebUI.waitForElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoME'), 3)

//Setear "Monto ME"
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoME'), '100')

//Seleccionar "txtComentarios"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'))

//Setear "Comentarios"
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'), 'PRUEBA DE SOLICITUD')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lnkAceptarAlertas'))

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

//Volver a Logearse con un nuevo usuario

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 17), findTestData('MainData/Users').getValue(2, 17))
WebUI.maximizeWindow()

//Seleccionar "Autorizaciones"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkAutorizacionesPasesEntreCajas'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Autorizacion de Pase Entre Cajas"
WebUI.click(findTestObject('Object Repository/02-Dashboard/55-Pases Entre Cajas/lnkAutorizaciondePaseEntreCajas'))

//Cambiar ventana "BCCL.E.TT.PASE.ENTRE.CAJAS"
WebUI.switchToWindowTitle('BCCL.E.TT.PASE.ENTRE.CAJAS')

//Esta funcion es invocada cuando se pregunta si el elemento que se quiere encontrar fue localizado en la tabla. Retorna un valor boolean
def buscarElementoEnTabla(String trx1) {
	
	//Obtener elemento de la tabla
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	
	//Obtener todas las filas de la tabla
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	
	//Desplegar la columna donde se muestra la info de las transacciones
	for (WebElement row : rows) {
		
		//Obtener tercer valor de la fila (índice 1, ya que las listas son base cero)
		WebElement cell = row.findElements(By.tagName("td"))[0]

		//Obtener texto
		String cellText = cell.getText()
		
		//Comparar valor de la celda con el valor especifico
		if (cellText.equals(trx1)) {
			
			//Realizar acciones necesarias si se encuentra el valor
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			WebElement tdElement = tdList[6]
			
			// Intenta encontrar el elemento 'a' dentro del elemento td
			WebElement lnkElement = tdElement.findElement(By.tagName("a"))
			
			//Seleccionar elemento 'lnk'
			lnkElement.click()
			return true
		}
	}
	return false
}

//Logica para buscar el elemento en la tabla
def encontrado = false

//Bucle para buscar en multiples páginas
while (!encontrado) {
	
	//Logica para buscar el elemento en la tabla
	encontrado = buscarElementoEnTabla(trx1)
		
	//Si no se encontro el valor, Seleccionar boton "Siguiente" y buscar nuevamente
	if (!encontrado) {
		
		//Realizar busqueda nuevamente despues de Seleccionar "Siguiente"
		WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnSiguiente'))
		
		//Esperar 2 seg a que se cargue la pagina
		WebUI.delay(2)
	}
}	
			
//Seleccionar "Audit"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/08-Movimiento de Fondos/lblAudit'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verificar "Fecha y Hora.1"
WebUI.verifyElementVisible(findTestObject('Object Repository/57-Pases Entre Cajas/01-BCCL.E.TT.PASE.ENTRE.CAJAS/lblFechayHora'))

//Validar "Fecha y Hora.1"
def element = WebUI.getText(findTestObject('Object Repository/57-Pases Entre Cajas/01-BCCL.E.TT.PASE.ENTRE.CAJAS/lblFechayHora'))
assert element.contains('Fecha y Hora.1')

//Seleccionar "boton Autorizar Registro"
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/01-BCCL.E.TT.PASE.ENTRE.CAJAS/btnAutorizarRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Validar "Txn Completa"
def element2 = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))
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