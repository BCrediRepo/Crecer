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
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.support.ui.Select

WebUI.callTestCase(findTestCase('11-MEP/MEP01-MEP. Transferencias MEP. Transacciones Online. Otros Conceptos. Usuario de filial NIV5'), 
    [:], FailureHandling.STOP_ON_FAILURE)

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(2, 3))
WebUI.maximizeWindow()

def menuDesplegable = ["Autorizaciones"]
def link = "Cons/Elimin de Tx Propias NO Autorizadas"

//Navegar por el menu del Dashboard
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Cambiar de ventana
WebUI.switchToWindowIndex(1)

//Definir la variable trx1 como "variable"
def variable = GlobalVariable.vTxn

//Esperar 3 seg a que se cargue la tabla
WebUI.delay(3)

//Maximizar ventana
WebUI.maximizeWindow()

//Esta funcion es invocada cuando se pregunta si el elemento que se quiere encontrar fue localizado en la tabla. Retorna un valor boolean
def buscarElementoEnTabla(String variable) {
	
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
		if (cellText.equals(variable)) {
			
			//Realizar acciones necesarias si se encuentra el valor
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			WebElement tdElement = tdList[9]
			WebElement eliminarTxn = tdElement.findElement(By.tagName("a"))
		
			//Seleccionar "Eliminar Transaccion"
			eliminarTxn.click()
			
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

//WebUI.click(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER.HIS/btnValidarRegistro'))
//WebUI.click(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER.HIS/btnAceptarRegistro'))

//Obtener el WebDriver actual
WebDriver driver = DriverFactory.getWebDriver()

//Seleccionar boton "Eliminar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnEliminarRegistro'))

//Enviar la tecla "Enter" cuando el popup este presente
driver.switchTo().alert().accept()

//Validar transaccion completa
label = WebUI.getText(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER.HIS/lblTXNCompleta'))
assert label.contains('Txn Completa:') == true

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}