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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 66), findTestData('MainData/Users').getValue(2, 66))
WebUI.maximizeWindow()

//Ingresar "?328" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?328')

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana "Temenos T24"
WebUI.switchToWindowIndex(1)

//Seleccionar "Posteo"
WebUI.click(findTestObject('Object Repository/37-Posteo/Temenos T24/lnkPosteo'))

//Seleccionar "Cobranza de Legales"
WebUI.click(findTestObject('Object Repository/37-Posteo/Temenos T24/Posteo/lnkCobranzadeLegales'))

//Seleccionar "Ingreso de Cobranzas"
WebUI.click(findTestObject('Object Repository/37-Posteo/Temenos T24/Posteo/Cobranza de Legales/lnkIngresodeCobranzas'))

//Cambiar a la ventana "BCCL.COBRANZAS.LEGALES"
WebUI.switchToWindowIndex(2)

//Setear Carpeta
WebUI.setText(findTestObject('Object Repository/37-Posteo/BCCL.COBRANZAS.LEGALES/txtCarpeta'), '15071')

//Maximizar Pantalla
WebUI.maximizeWindow()

//Setear Nombre del Socio
WebUI.setText(findTestObject('Object Repository/37-Posteo/BCCL.COBRANZAS.LEGALES/txtNombredelSocio'), 'TEST')

//Seleccionar "boton Dropdown Abogado"
WebUI.click(findTestObject('Object Repository/37-Posteo/BCCL.COBRANZAS.LEGALES/btnDropdownAbogado'))

//Seleccionar Abogado
WebUI.click(findTestObject('Object Repository/37-Posteo/BCCL.COBRANZAS.LEGALES/lblGonzalezHumberto'))

//Seleccionar "boton Dropdown Moneda"
WebUI.click(findTestObject('Object Repository/37-Posteo/BCCL.COBRANZAS.LEGALES/btnDropdownMoneda'))

//Seleccionar "ARS"
WebUI.click(findTestObject('Object Repository/37-Posteo/BCCL.COBRANZAS.LEGALES/lblARS'))

//Seleccionar "Capital" en el ComboBox de Concepto
WebUI.selectOptionByIndex(findTestObject('Object Repository/37-Posteo/BCCL.COBRANZAS.LEGALES/cbConcepto'), 1)

//Setear Importe
WebUI.setText(findTestObject('Object Repository/37-Posteo/BCCL.COBRANZAS.LEGALES/txtImporte'), '10')

//Seleccionar "rbTitular"
WebUI.click(findTestObject('Object Repository/37-Posteo/BCCL.COBRANZAS.LEGALES/rbTitular'))

//Seleccionar "boton Validar Registro"
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/btnValidarRegistro'))

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/btnAceptarRegistro'))

//Definir Transaccion
Transaccion = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion.split('\\s+')
def trx1 = partes[2]
assert Transaccion.contains('Txn Completa:')

//Setear en "BCCL.COBRANZAS.LEGALES"
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/txtBajaPartidasIP-CuentaContable'), trx1)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Ver Registro"
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/Deposito De Efectivo En Buzon A Toda Hora/TELLER/btnVerRegistro'))

//Definir ftPosteo
ftPosteo = WebUI.getText(findTestObject('Object Repository/37-Posteo/BCCL.COBRANZAS.LEGALES/lblFtPosteo'))

//Definir Variable Global
GlobalVariable.vTxn = ftPosteo

//Volver a Logearse con el usuario que liquida Posteos

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 67), findTestData('MainData/Users').getValue(2, 67))
WebUI.maximizeWindow()

//Ingresar "?303" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?303')

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))
	
//Cambiar ventana "Temenos T24"
WebUI.switchToWindowIndex(1)

//Seleccionar "Posteo"
WebUI.click(findTestObject('Object Repository/37-Posteo/Temenos T24/lnkPosteoLiquidacion'))

//Seleccionar "Transacciones Pendientes de Liquidacion"
WebUI.click(findTestObject('Object Repository/37-Posteo/Temenos T24/Posteo/lblTransaccionesPendientesdeLiquidacion'))

//Cambiar ventana "BCCL.E.EB.POSTEO.INAU"
WebUI.switchToWindowTitle('BCCL.E.EB.POSTEO.INAU')

//Maximizar Pantalla
WebUI.maximizeWindow()

//Definir GlobalVariable como "variable"
def variable = GlobalVariable.vTxn

//Esta funcion es invocada cuando se pregunta si el elemento que se quiere encontrar fue localizado en la tabla. Retorna un valor boolean
def buscarElementoEnTabla(String variable) {
	
	//Obtener elemento de la tabla
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	
	//Obtener todas las filas de la tabla
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	
	for (WebElement row : rows) {
		
		//Obtener tercer valor de la fila (índice 1, ya que las listas son base cero)
		WebElement cell = row.findElements(By.tagName("td"))[0]

		//Obtener texto
		String cellText = cell.getText()

		//Comparar valor de la celda con el valor especifico
		if (cellText.equals(variable)) {
			
			//Realizar acciones necesarias si se encuentra el valor
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			WebElement tdElement = tdList[8]
			String tdElementText = tdElement.getText()

			WebElement liquidar = tdElement.findElement(By.tagName("a"))
			
			//Seleccionar liquidar
			liquidar.click()
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
	encontrado = buscarElementoEnTabla(variable)
		
	//Si no se encontro el valor, Seleccionar boton "Siguiente" y buscar nuevamente
	if (!encontrado) {
		
		//Realizar busqueda nuevamente despues de Seleccionar "Siguiente"
		WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnSiguiente'))
		
		//Esperar 2 seg a que se cargue la pagina
		WebUI.delay(2)
	}
}

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Autorizar Registro"
WebUI.click(findTestObject('Object Repository/37-Posteo/Movimiento de Fondos/btnAutorizarRegistro'))

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