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


//REVISAR LINEA 170 

//Ejecutar caso PN03
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

//Seleccionar "btn Id Sobrante"
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnIdSobrante'))

//-----------------------------------------------------------------------------------------------Comienza el codigo
//Obtén el elemento de la tabla
WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))

//Obtén todas las filas dentro de la tabla
List<WebElement> rows = table.findElements(By.tagName('tr'))
//List<WebElement> rows = table.findElements(By.cssSelector('#r'))

//Valor específico que estás buscando
//String targetValue = GlobalVariable.vTxn

//Valor específico que estás buscando
String targetValue = "TT23240869300925"

//Imprimir valor en la consola
println(targetValue)

//Indicar maximo de filas
int maxFilas = 19

//Definir "valorDeseado" del comboBox
String valorDeseado = "Baja Sobrante Dispositivo - O Banco"

//Variable para rastrear si se encontró el valor específico
boolean foundTargetValue = false
 
int i = 0
while (i < rows.size() && !foundTargetValue) {
	WebElement row = rows[i]
 
	//Obtén el valor de la fila
	WebElement cell = row.findElements(By.tagName('td'))[1]
	String cellText = cell.getText()
 
	//Compara el valor de la celda con el valor específico
	if (cellText.equals(targetValue)) {
		foundTargetValue = true
 
		//Obtén el elemento select dentro del td en la posición 13
		WebElement comboBox = row.findElements(By.tagName('select'))[13]

		//Seleccionar comboBox
		comboBox.click()
 
//	//Crear objeto Select utilizando el WebElement del ComboBox
//       Select selection = new Select(comboBox)
//	   
//	//Seleccionar valor deseado en el ComboBox
//	selection.selectByVisibleText(valorDeseado)
 
		// Haz clic en el elemento 'img' dentro del td
//		WebElement aceptar = tdElement.findElement(By.tagName("img"))
//		aceptar.click()
	}
 
	i++
	
	// Si hemos llegado a la última fila, reinicia el índice
	if (i == maxFilas) {
		i = 0
		//Seleccionar "boton Siguiente"
		WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnSiguiente'))
		
		//Esperar 3 segundos
		WebUI.delay(3)
		
		//Obtén todas las filas dentro de la tabla
		//List<WebElement> rows = table.findElements(By.tagName('tr'))
		
		//Seleccionar "boton + verde"
		WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnIdSobrante'))
		
	}

	//Compara el valor de la celda con el valor específico
	if (cellText.equals(targetValue)) {
		foundTargetValue = true
 
		//Obtén el elemento select dentro del td en la posición 13
		WebElement comboBox = row.findElements(By.tagName('option'))[13]
		
		//Seleccionar comboBox
		comboBox.click()
 
	}

	i++
	
	// Si hemos llegado a la última fila, reinicia el índice
	if (i == maxFilas) {
		i = 0
		//Seleccionar "boton Siguiente"
		WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnSiguiente'))
		
		//Esperar 3 segundos
		WebUI.delay(3)
		
		//Seleccionar "boton + verde"
		WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnIdSobrante'))
	}

	//Compara el valor de la celda con el valor específico
	if (cellText.equals(targetValue)) {
		foundTargetValue = true
 
		//Obtén el elemento select dentro del td en la posición 13
		WebElement comboBox = row.findElements(By.tagName('select'))[13]
		
		//Seleccionar comboBox
		comboBox.click()
 
	}
	
}

//// Itera a través de las filas (desde 1 hasta 19)
//for (int i = 1; i < 20; i++) {
//	WebElement row = rows[i]
//	println(i)
//
//	//Obtener valor de la fila (las listas son base cero)
//	WebElement cell = row.findElements(By.tagName('td'))[1]
//
//	//Obtiene el texto de la celda
//	String cellText = cell.getText()
//	
//	if(i==19) {
//		WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnSiguiente'))
//		i=1
//		WebUI.delay(5)
//		WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnIdSobrante'))
//	}
//	
//	//Comparar el valor de la celda con el valor específico
//	if (cellText.equals(targetValue)) {
//		foundTargetValue = true
//
//		//Obtener lista de elementos td
//		List<WebElement> tdList = row.findElements(By.tagName('td'))
//
//		//Accede al elemento td en la posición 13
//		WebElement tdElement = tdList[13]
//		
//		//Intenta encontrar el elemento 'select' dentro del elemento td
//		WebElement comboBox = tdElement.findElement(By.tagName('select'))
//
//		// Crear un objeto Select utilizando el WebElement del ComboBox
//		Select selection = new Select(comboBox)
//		
//		//Definir valor Deseado
//		String valorDeseado = "Baja Sobrante Dispositivo - O Banco"
//		
//		//Seleccionar tipo de baja dentro del comboBox
//		selection.selectByValue(valorDeseado)
//		
//		//Intenta encontrar el elemento 'img' dentro del elemento td
//		WebElement aceptar = tdElement.findElement(By.tagName("img"))
//		
//		//Haz clic en el enlace
//		aceptar.click()
//		
//		break
//	}
	
//	// Compara el valor de la celda con "0,00"
//	if (!cellText.equals("0")) {
//		foundNonZeroValue = true
//		break
//	}
//}

////Itera a través de las filas
//for (WebElement row : rows) {
//
//	println(row.getText())
//	println(row.)
//	if (row.findElements(By.id('r19')) ) {
//		WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnSiguiente'))
//		WebUI.delay(5)
//		row = 0
//	}
//	
//    //Obtener valor de la fila (las listas son base cero)
//    WebElement cell = row.findElements(By.tagName('td'))[1]
//
//    //Obtiene el texto de la celda
//    String cellText = cell.getText()
//
//    //Comparar el valor de la celda con el valor específico
//    if (cellText.equals(targetValue)) {
//        foundTargetValue = true
//
//        //Obtener lista de elementos td
//        List<WebElement> tdList = row.findElements(By.tagName('td'))
//
//        //Accede al elemento td en la posición 13
//        WebElement tdElement = tdList[13]
//		
//		//Intenta encontrar el elemento 'select' dentro del elemento td
//		WebElement comboBox = tdElement.findElement(By.tagName('select'))
//
//		// Crear un objeto Select utilizando el WebElement del ComboBox
//		Select selection = new Select(comboBox)
//		
//		//Definir valor Deseado
//		String valorDeseado = "Baja Sobrante Dispositivo - O Banco"
//		
//		//Seleccionar tipo de baja dentro del comboBox
//		selection.selectByValue(valorDeseado)
//		
//		//Intenta encontrar el elemento 'img' dentro del elemento td
//		WebElement aceptar = tdElement.findElement(By.tagName("img"))
//		
//		//Haz clic en el enlace
//		aceptar.click()
//        
//		break
//    }
//} 

////Control de fin de script
//@com.kms.katalon.core.annotation.TearDownIfFailed
//void fTakeFailScreenshot() {
//    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
//}
//
//@com.kms.katalon.core.annotation.TearDownIfPassed
//void fPassScript() {
//    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
//}