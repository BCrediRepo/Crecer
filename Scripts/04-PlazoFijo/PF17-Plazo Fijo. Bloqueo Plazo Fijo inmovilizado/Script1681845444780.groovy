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

//IMPORTANTE
//CUIDADO CON LA CANTIDAD DE VECES QUE SE CORRE EL SCRIPT
//LOS DATOS SE QUEMAN
//EN CASO DE QUEDARSE SIN DATOS, CAMBIAR USUARIO Y BUSCAR SEGUN LA SUCURSAL DEL MISMO

def buscarElementoEnTabla(String targetValue) {
	// Obtén el elemento de la tabla
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	
	// Obtén todas las filas dentro de la tabla
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	
	// Itera a través de las filas
	for (WebElement row : rows) {
		// Obtiene el tercer valor de la fila (índice 2, ya que las listas son base cero)
		WebElement cell = row.findElements(By.tagName("td"))[0]
	 
		// Obtiene el texto de la celda
		String cellText = cell.getText()
	 
		// Compara el valor de la celda con el valor específico
		if (cellText.equals(targetValue)) {
			// Obtiene la lista de elementos td
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			
			// Accede al elemento td en la posición 8
			WebElement tdElement = tdList[8]
			
			WebElement comboBox = tdElement.findElement(By.tagName("select"))
		// Utiliza Select para interactuar con el comboBox
		def select = new Select(comboBox)
		select.selectByVisibleText("AUTORIZAR TRANSACCION")
		// Encuentra el elemento 'img' dentro del enlace 'a'
		WebElement imgElement = tdElement.findElement(By.cssSelector("a[title='Select Drilldown'] img"))
		// Haz clic en el elemento 'img'
		imgElement.click()
		return true
	
		}
	}
	return false
	}
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
	
	//Login
	CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 7), findTestData('MainData/Users').getValue(
			2, 7))
	
	WebUI.maximizeWindow()
	WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?302')
	WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))
	WebUI.switchToWindowTitle('Temenos T24')
	WebUI.click(findTestObject('Object Repository/02-Dashboard/03-PlazoFijo/Temenos T24/lnkPlazoFijo'))
	WebUI.click(findTestObject('02-Dashboard/03-PlazoFijo/Temenos T24/Plazo Fijo/lnkModificacionDePlazoFijo'))
	WebUI.click(findTestObject('02-Dashboard/03-PlazoFijo/Temenos T24/Plazo Fijo/Modificacion de plazo fijo/lnkBloqueoyDesbloqueo'))
	WebUI.click(findTestObject('02-Dashboard/03-PlazoFijo/Temenos T24/Plazo Fijo/Modificacion de plazo fijo/Bloqueo y desbloqueo/lnk_BloqueoPlazoFijoInmovilizado'))
	WebUI.switchToWindowTitle('Bloqueo Plazo Fijo Inmovilizado')
	//Seteo de Datos "Nro. Operacion"
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
	CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro. Operacion', '0211579')
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
	
try {
	WebUI.click(findTestObject('05-PlazoFijo/Bloqueo Plazo Fijo Inmovilizado/lnkBloquearInmovilizado'))
	WebUI.switchToWindowTitle('Plazo Fijo Inmovilizado')
	WebUI.selectOptionByIndex(findTestObject('05-PlazoFijo/Plazo Fijo Inmovilizado/cbxMotivoBloqueo'), 2)
	WebUI.click(findTestObject('05-PlazoFijo/Plazo Fijo Inmovilizado/btnAceptarRegistro'))
	WebUI.switchToWindowTitle('Plazo Fijo')
	txnPendiente = WebUI.getText(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/lblTXFINALIZADA'))
	assert txnPendiente.contains("TX PENDIENTE")
	WebUI.switchToWindowIndex(0)
	WebUI.click(findTestObject('Object Repository/02-Dashboard/btnLogout'))
	
	//Configuracion de ambiente
	CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
	
	CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
			2, 9))
	
	WebUI.click(findTestObject('02-Dashboard/lnkAutorizaciones'))
	
	WebUI.click(findTestObject('02-Dashboard/lnkAutorizacionesPendientes'))
	
	WebUI.switchToWindowTitle('BCCL.E.AUTHORIZATION')
	
	String targetValue = "0211579"
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
	
	WebUI.switchToWindowTitle('Plazo Fijo Inmovilizado')

	WebUI.click(findTestObject('05-PlazoFijo/Plazo Fijo Inmovilizado/btnAutorizar'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/lblTXFINALIZADA'))
	txnAutorizada = WebUI.getText(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/lblTXFINALIZADA'))
	assert txnAutorizada.contains("TX FINALIZADA")
}catch(Exception e) {
	//Desbloqueo de PF
	WebUI.switchToWindowTitle('Temenos T24')
	WebUI.click(findTestObject('Object Repository/02-Dashboard/03-PlazoFijo/Temenos T24/Plazo Fijo/Modificacion de plazo fijo/Bloqueo y desbloqueo/lnkDesbloqueoPlazoFijoInmovilizado'))
	WebUI.switchToWindowTitle('Desbloqueo Plazo Fijo Inmovilizado')
	//Seteo de Datos "Nro. Operacion"
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
	CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro Operacion', '0211579')
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
	WebUI.click(findTestObject('Object Repository/05-PlazoFijo/Desbloqueo Plazo Fijo Inmovilizado/lnkDesbloquearInmovilizado'))
	WebUI.click(findTestObject('Object Repository/05-PlazoFijo/Bloqueo Plazo Fijo Inmovilizado/btnAceptar'))
	WebUI.switchToWindowTitle('Plazo Fijo')
	txnPendiente1 = WebUI.getText(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/lblTXFINALIZADA'))
	assert txnPendiente1.contains("TX PENDIENTE")
	WebUI.switchToWindowIndex(0)
	WebUI.click(findTestObject('Object Repository/02-Dashboard/btnLogout'))
	//AUTORIZACION DE DESBLOQUEO PARA DEJAR PF LISTO PARA BLOQUEO
	//(NECESARIO PARA CUMPLIR LA TAREA DEL SCRIPT)
	//Configuracion de ambiente
	CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
	
	CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
			2, 9))
	
	WebUI.click(findTestObject('02-Dashboard/lnkAutorizaciones'))
	
	WebUI.click(findTestObject('02-Dashboard/lnkAutorizacionesPendientes'))
	
	WebUI.switchToWindowTitle('BCCL.E.AUTHORIZATION')
	
	String targetValue = "0211579"
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
	
	WebUI.switchToWindowTitle('Plazo Fijo Inmovilizado')

	WebUI.click(findTestObject('05-PlazoFijo/Plazo Fijo Inmovilizado/btnAutorizar'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/lblTXFINALIZADA'))
	txnAutorizada1 = WebUI.getText(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/lblTXFINALIZADA'))
	assert txnAutorizada1.contains("TX FINALIZADA")
	
	//NUEVO LOGIN PARA PODER REALIZAR EL "CAMINO FELIZ" DEL CASO
	//ES DECIR, QUE EL SCRIPT CUMPLA SU PROPOSITO DE BLOQUEAR UN
	//PLAZO FIJO INMOVILIZADO	
	
	WebUI.switchToWindowIndex(0)
	WebUI.click(findTestObject('Object Repository/02-Dashboard/btnLogout'))
	//Configuracion de ambiente
	CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
	
	//Login
	CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 7), findTestData('MainData/Users').getValue(
			2, 7))
	
	WebUI.maximizeWindow()
	WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?302')
	WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))
	WebUI.switchToWindowTitle('Temenos T24')
	WebUI.click(findTestObject('Object Repository/02-Dashboard/03-PlazoFijo/Temenos T24/lnkPlazoFijo'))
	WebUI.click(findTestObject('02-Dashboard/03-PlazoFijo/Temenos T24/Plazo Fijo/lnkModificacionDePlazoFijo'))
	WebUI.click(findTestObject('02-Dashboard/03-PlazoFijo/Temenos T24/Plazo Fijo/Modificacion de plazo fijo/lnkBloqueoyDesbloqueo'))
	WebUI.click(findTestObject('02-Dashboard/03-PlazoFijo/Temenos T24/Plazo Fijo/Modificacion de plazo fijo/Bloqueo y desbloqueo/lnk_BloqueoPlazoFijoInmovilizado'))
	WebUI.switchToWindowTitle('Bloqueo Plazo Fijo Inmovilizado')
	//Seteo de Datos "Nro. Operacion"
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
	CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro Operacion', '0211579')
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
	WebUI.click(findTestObject('05-PlazoFijo/Bloqueo Plazo Fijo Inmovilizado/lnkBloquearInmovilizado'))
	WebUI.switchToWindowTitle('Plazo Fijo Inmovilizado')
	WebUI.selectOptionByIndex(findTestObject('05-PlazoFijo/Plazo Fijo Inmovilizado/cbxMotivoBloqueo'), 2)
	WebUI.click(findTestObject('05-PlazoFijo/Plazo Fijo Inmovilizado/btnAceptarRegistro'))
	WebUI.switchToWindowTitle('Plazo Fijo')
	txnPendiente = WebUI.getText(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/lblTXFINALIZADA'))
	assert txnPendiente.contains("TX PENDIENTE")
	WebUI.switchToWindowIndex(0)
	WebUI.click(findTestObject('Object Repository/02-Dashboard/btnLogout'))
	
	//Configuracion de ambiente
	CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
	
	CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
			2, 9))
	
	WebUI.click(findTestObject('02-Dashboard/lnkAutorizaciones'))
	
	WebUI.click(findTestObject('02-Dashboard/lnkAutorizacionesPendientes'))
	
	WebUI.switchToWindowTitle('BCCL.E.AUTHORIZATION')
	
	String targetValue1 = "0211579"
	// Lógica para buscar el elemento en la tabla
	def encontrado1 = false
	 
	// Bucle para buscar en múltiples páginas
	while (!encontrado1) {
		// Lógica para buscar el elemento en la tabla
		encontrado1 = buscarElementoEnTabla(targetValue)
		// Si no se encontró el valor, hacer clic en el botón "Siguiente" y buscar nuevamente
		if (!encontrado1) {
			// Realiza la búsqueda nuevamente después de hacer clic en "Siguiente"
			WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnSiguiente'))
			// Espera a que la nueva página se cargue completamente
			WebUI.delay(2)
		}
	}
	
	WebUI.switchToWindowTitle('Plazo Fijo Inmovilizado')

	WebUI.click(findTestObject('05-PlazoFijo/Plazo Fijo Inmovilizado/btnAutorizar'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/lblTXFINALIZADA'))
	txnAutorizada = WebUI.getText(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/lblTXFINALIZADA'))
	assert txnAutorizada.contains("TX FINALIZADA")
	
}


@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

