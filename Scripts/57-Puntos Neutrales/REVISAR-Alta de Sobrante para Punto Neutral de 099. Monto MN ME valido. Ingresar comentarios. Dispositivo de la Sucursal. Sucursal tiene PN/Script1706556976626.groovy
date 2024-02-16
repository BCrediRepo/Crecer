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


//SE NECESITA QUE EL CODIGO DE VALIDACION DEL ALTA, BUSQUE LA TNX(ESO IMPLICA ITERAR EN LAS DISTINTAS PAGINAS) CORRESPONDIENTE.

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 6), findTestData('MainData/Users').getValue(2, 6))
WebUI.maximizeWindow()

//Ingresar "?327" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?327')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')

//Maximizar pantalla
WebUI.maximizeWindow()

//Seleccionar "Dispositivos"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/02-Temenos T24/lnkDispositivos'))

//Seleccionar "Registro de Fallas en Dispositivos "
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/02-Temenos T24/Dispositivos/lnkRegistrodeFallasenDispositivos'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Alta de Sobrantes Puntos Neutrales"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/02-Temenos T24/Dispositivos/Registro de Fallas en Dispositivos/lnkAltadeSobrantesPuntosNeutrales'))

//Cambiar ventana "TELLER"
WebUI.switchToWindowTitle('TELLER')

//Seleccionar "Monto MN"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoMN'))

//Setear "Monto MN"
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtMontoMN'), '100')

//Seleccionar "Comentarios"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'))

//Setear "Comentarios"
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtComentarios'), 'PRUEBAS CRECER')

//Seleccionar "DEPOSITO"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/rbtnDeposito'))

//Seleccionar "boton Drop down ID Dispositivo"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/btnDropdownIdDispositivo'))

//Seleccionar "70151"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/lblidDispositivo70151'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Denominaciones DB"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lblDenominaciones'))

//Setear 1 en la posicion de CantidadCien
WebUI.setText(findTestObject('Object Repository/17-Remesas/03-TELLER/txtCantidadCien'), '1')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lnkAceptarAlertas'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Validar "Txn Completa"
def element = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))
assert element.contains('Txn Completa')

//Definir Objeto
Transaccion1 = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion1.split('\\s+')
def trx1 = partes[2]
GlobalVariable.vTxn = trx1
assert Transaccion1.contains('Txn Completa:')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Switch a la ventana "Temenos T24"
WebUI.switchToWindowIndex(1)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Consulta Sobrantes en Puntos Neutrales"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/02-Temenos T24/Dispositivos/Registro de Fallas en Dispositivos/lnkConsultaSobrantesenPuntosNeutrales'))

//Cambiar ventana "BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN"
WebUI.switchToWindowTitle('BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN')

//Filtro limpieza
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(1)

//Seleccionar "Consulta Sobrantes en Puntos Neutrales"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/02-Temenos T24/Dispositivos/Registro de Fallas en Dispositivos/lnkConsultaSobrantesenPuntosNeutrales'))

//Cambiar ventana "BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN"
WebUI.switchToWindowTitle('BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN')

//Maximizar Ventana
WebUI.maximizeWindow()

//Setear "Fecha Desde"
WebUI.setText(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/txtFechaDesde'), '20230828')

//Setear "Sucursal"
WebUI.setText(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/txtSucursal'), '073')

//Setear "ID Dispositivo"
WebUI.setText(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/txtIdDispositivo'), '70151')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Seleccionar "btn Id Sobrante"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnIdSobrante'))

//--------------------------------------------------------------------------------------------------Empieza el código
//Esta funcion es invocada cuando se pregunta si el elemento que se quiere encontrar fue localizado en la tabla. Retorna un valor boolean
def buscarElementoEnTabla(String trx1) {
	//Obtén el elemento de la tabla
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	
	//Obtén todas las filas dentro de la tabla
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	
	// Itera a través de las filas
	//Despliego la columna donde se muestra la info de las transacciones
	WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnIdSobrante'))
	for (WebElement row : rows) {
		// Obtiene el tercer valor de la fila (índice 1, ya que las listas son base cero)
		WebElement cell = row.findElements(By.tagName("td"))[1]

		//Obtiene el texto de la celda
		String cellText = cell.getText()
		
		//Compara el valor de la celda con el valor específico
		if (cellText.equals(trx1)) {
			
			//Realiza las acciones necesarias si se encuentra el valor
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			WebElement tdElement = tdList[13]
			WebElement comboBox = tdElement.findElement(By.tagName("select"))
			
			//Utiliza Select para interactuar con el comboBox
			def select = new Select(comboBox)
			select.selectByVisibleText("Baja Sobrante Dispositivo - O Banco")
			
			//Encuentra el elemento 'img' dentro del enlace 'a'
			WebElement imgElement = tdElement.findElement(By.cssSelector("a[title='Select Drilldown'] img"))
			
			// Haz clic en el elemento 'img'
			imgElement.click()
			return true
		}
	}
	return false
}

//Lógica para buscar el elemento en la tabla
def encontrado = false

//Bucle para buscar en múltiples páginas
while (!encontrado) {
	
	//Lógica para buscar el elemento en la tabla
	encontrado = buscarElementoEnTabla(trx1)
		
	//Si no se encontró el valor, hacer clic en el botón "Siguiente" y buscar nuevamente
	if (!encontrado) {
		
		//Realiza la búsqueda nuevamente después de hacer clic en "Siguiente"
		WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnSiguiente'))
		
		//Espera a que la nueva página se cargue completamente
		WebUI.delay(2)
	}
}

				
////Cambiar opcion a "Ver Detalle" En el combo box
//WebUI.selectOptionByIndex(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/cbVerDetallesyBajaOBanco'), 6)
//
////Screenshot
//CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
//
////Seleccionar "boton Drill down"
//WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnSelectDrilldown'))
//
////Switch a la nueva ventana de "TELLER"
//WebUI.switchToWindowIndex(3)
//
////Verificar "ALTA Sobrante en Disp Pn - Deposito ALTA Sobrante en Disp Pn"
//WebUI.verifyElementVisible(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/lblALTASobranteenDispPn-DepositoALTASobranteenDispPn'))
//
////Validar "ALTA Sobrante en Disp Pn - Deposito ALTA Sobrante en Disp Pn"
//def element2 = WebUI.getText(findTestObject('Object Repository/58-Puntos Neutrales/01-TELLER/lblALTASobranteenDispPn-DepositoALTASobranteenDispPn'))
//assert element2.contains('ALTA Sobrante en Disp Pn - Deposito ALTA Sobrante en Disp Pn')
//
////Control de fin de script
//@com.kms.katalon.core.annotation.TearDownIfFailed
//void fTakeFailScreenshot() {
//	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
//}
//
//@com.kms.katalon.core.annotation.TearDownIfPassed
//void fPassScript() {
//	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
//}