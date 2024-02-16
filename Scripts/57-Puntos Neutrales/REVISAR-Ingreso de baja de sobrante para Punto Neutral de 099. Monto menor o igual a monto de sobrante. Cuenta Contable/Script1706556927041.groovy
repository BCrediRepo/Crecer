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

//Ejecutar caso PN06
//WebUI.callTestCase(findTestCase('57-Puntos Neutrales/REVISAR-Alta de Sobrante para Punto Neutral de 099. Monto MN ME valido. Ingresar comentarios. Dispositivo de la Sucursal. Sucursal tiene PN'), 
   // [:], FailureHandling.STOP_ON_FAILURE)

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 53), findTestData('MainData/Users').getValue(2, 53))
WebUI.maximizeWindow()

//Ingresar "ENQ BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN"
WebUI.switchToWindowTitle('BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN')

//Filtro limpieza
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN"
WebUI.switchToWindowTitle('BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN')

//Maximizar Ventana
WebUI.maximizeWindow()

//Setear "Fecha Desde"
WebUI.setText(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/txtFechaDesde'), '20200101')

//Setear "Sucursal"
WebUI.setText(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/txtSucursal'), '073')

//Setear "ID Dispositivo"
WebUI.setText(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/txtIdDispositivo'), '70151')

//Setear "Cartucho/Gaveta"
WebUI.setText(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/txtCartucho-Gaveta'), 'DEPOSITO')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

String targetValue = "TT23240212187613"

//Esta funcion es invocada cuando se pregunta si el elemento que se quiere encontrar fue localizado en la tabla. Retorna un valor boolean
def buscarElementoEnTabla(String targetValue) {
	//Itero en la tabla buscado la FT originada y cliqueando en PAGAR
	// Obtén el elemento de la tabla
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	// Obtén todas las filas dentro de la tabla
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	// Itera a través de las filas
	//Despliego la columna donde se muestra la info de las transacciones
	WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnIdSobrante'))
	for (WebElement row : rows) {
		// Obtiene el tercer valor de la fila (índice 1, ya que las listas son base cero)
		WebElement cell = row.findElements(By.tagName("td"))[1]

		// Obtiene el texto de la celda
		String cellText = cell.getText()
		
		// Compara el valor de la celda con el valor específico
		if (cellText.equals(targetValue)) {
			
			// Realiza las acciones necesarias si se encuentra el valor
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			WebElement tdElement = tdList[13]
			WebElement comboBox = tdElement.findElement(By.tagName("select"))
			
			// Utiliza Select para interactuar con el comboBox
			def select = new Select(comboBox)
			select.selectByVisibleText("Baja Sobrante Dispositivo - O Banco")
			
			// Encuentra el elemento 'img' dentro del enlace 'a'
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
	encontrado = buscarElementoEnTabla(targetValue)
		
	//Si no se encontró el valor, hacer clic en el botón "Siguiente" y buscar nuevamente
	if (!encontrado) {
		
		//Realiza la búsqueda nuevamente después de hacer clic en "Siguiente"
		WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnSiguiente'))
		//Espera a que la nueva página se cargue completamente
		WebUI.delay(2)
	}
}

//-----------------------------------------------------------------
//Switchear a la ventana de baja sobrante
//completar el registro con los datos mandatorios
//completar la baja

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}