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

//Seleccionar "Caja"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCaja'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Apertura de Caja"
WebUI.click(findTestObject('Object Repository/02-Dashboard/52-Caja/lnkAperturadeCaja'))

//Cambiar ventana "TELLER ID"
WebUI.switchToWindowTitle('TELLER ID')

//Maximizar pantalla
WebUI.maximizeWindow()

try {
	
	//Seleccionar "Boton Aceptar Registro"
	WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnAceptarRegistro'))

	//Verificar "IMPOSIBLE ABRIR CAJA QUE YA SE ENCUENTRA ABIERTA"
	WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/04-TELLER ID/lblImposibleAbrirCaja'))

	//Validar "IMPOSIBLE ABRIR CAJA QUE YA SE ENCUENTRA ABIERTA"
	def element = WebUI.getText(findTestObject('Object Repository/17-Remesas/04-TELLER ID/lblImposibleAbrirCaja'))
	assert element.contains('IMPOSIBLE ABRIR CAJA QUE YA SE ENCUENTRA ABIERTA')

	//Screenshot
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

	//Switch a la ventana del Dashboard
	WebUI.switchToWindowIndex(0)

	//Seleccionar "Consulta de Estado de Cajas"
	WebUI.click(findTestObject('Object Repository/02-Dashboard/52-Caja/lnkConsultadeEstadodeCajas'))

	//Cambiar ventana "BCCL.E.TID.CAJA.ESTADO"
	WebUI.switchToWindowTitle('BCCL.E.TID.CAJA.ESTADO')

	//Maximizar pantalla
	WebUI.maximizeWindow()
	
//	def element2
//	
//	// Obtén el elemento de la tabla
//	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
//	 
//	// Obtén todas las filas dentro de la tabla
//	List<WebElement> rows = table.findElements(By.tagName("tr"))
//	 
//	// Valor específico que estás buscando
//	String targetValue = 'B.0489'
//	 
//	// Variable para rastrear si se encontró el valor específico
//	boolean foundTargetValue = false
//	 
//	// Itera a través de las filas
//	for (WebElement row : rows) {
//		// Obtiene el tercer valor de la fila (índice 2, ya que las listas son base cero)
//		WebElement cell = row.findElements(By.tagName("td"))[1]
//	 
//		// Obtiene el texto de la celda
//		String cellText = cell.getText()
//	 
//		// Compara el valor de la celda con el valor específico
//		if (cellText.equals(targetValue)) {
//			foundTargetValue = true
//				
//			// Obtiene la lista de elementos td
//			List<WebElement> tdList = row.findElements(By.tagName("td"))
//			
//			// Accede al elemento td en la posición 3
//			WebElement tdElement = tdList[3]
//
//			// Intenta encontrar el elemento 'a' dentro del elemento td
//			WebElement liquidar = tdElement.findElement(By.tagName("td"))
//			println(liquidar)
////			findElement(By.tagName("a"))
//			// Haz clic en el enlace
//			element2 = liquidar.getText()
//			
//			break
//			
//		}
//	}
//	
//	
	

	//Verificar "OPEN"
	WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/05-BCCL.E.TID.CAJA.ESTADO/lblOPENTesoro'))

	//Validar "OPEN"
	def element2 = WebUI.getText(findTestObject('Object Repository/17-Remesas/05-BCCL.E.TID.CAJA.ESTADO/lblOPENTesoro'))
	assert element2.contains('OPEN')
}
catch (Exception e) {
	//Definir Objeto
	Transaccion1 = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))
	
	//Dividir la cadena por espacios en blanco y tomar elemento
	def partes = Transaccion1.split('\\s+')
	def trx1 = partes[2]
	assert Transaccion1.contains('Txn Completa:')
	
	//Switch a la ventana del Dashboard
	WebUI.switchToWindowIndex(0)

	//Seleccionar "Consulta de Estado de Cajas"
	WebUI.click(findTestObject('Object Repository/02-Dashboard/52-Caja/lnkConsultadeEstadodeCajas'))

	//Cambiar ventana "BCCL.E.TID.CAJA.ESTADO"
	WebUI.switchToWindowTitle('BCCL.E.TID.CAJA.ESTADO')

	//Maximizar pantalla
	WebUI.maximizeWindow()

	//Verificar "OPEN"
	WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/05-BCCL.E.TID.CAJA.ESTADO/lblOPENTesoro'))

	//Validar "OPEN"
	def element2 = WebUI.getText(findTestObject('Object Repository/17-Remesas/05-BCCL.E.TID.CAJA.ESTADO/lblOPENTesoro'))
	assert element2.contains('OPEN')
}


//----------------------------------------------------------
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}