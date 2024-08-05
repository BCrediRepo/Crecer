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
import java.awt.Robot
import java.awt.event.KeyEvent

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()

//Se accede al menu Administracion de piezas
menuDesplegable = ["Administracion de Piezas con Tarjetas","Consultas al Maestro de Card-Carrier"]
link = "Seleccion por Nombre / Documento / Sucursal"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Cambiar ventana "BCCL.E.AP.ENQ.NOMBRE.DOC"
WebUI.switchToWindowTitle('BCCL.E.AP.ENQ.NOMBRE.DOC')

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '073')
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))
WebUI.maximizeWindow()

//Almacenar el valor "010" del primer estado
def estadoPiezaRecibida = '010'
 
//Funcion invocada cuando se pregunta si el elemento que se quiere encontrar fue localizado en la tabla. Retorna un valor boolean
def buscarElementoEnTabla(String estadoPiezaRecibida) {
	
	//Obtener elemento tabla
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	
	//Obtener todas las filas que están adentro de la tabla
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	
	//Iterar a través de las filas
	for (WebElement row : rows) {
		
		//Obtener el valor del Estado de la fila
		WebElement cell = row.findElements(By.tagName("td"))[7]

		//Verificar que la celda no sea null
		if (cell != null) {
			//Obtener el texto de la celda
			String estado = cell.getText();
			println(estado);
		
			//Comparar el valor de la celda con el valor específico
			if (estado.equals(estadoPiezaRecibida)) {
			
			//Realizar las acciones necesarias si se encuentra el valor
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			WebElement tdElement = tdList[0]
			String idPieza = tdElement.getText()
			println(idPieza)
			GlobalVariable.vTxn = idPieza
			return true
			
			}
		}	
	}
	return false
}

//Lógica para buscar el elemento en la tabla
def encontrado = false

//Bucle para buscar en múltiples páginas
while (!encontrado) {
	
	//Lógica para buscar el elemento en la tabla
	encontrado = buscarElementoEnTabla(estadoPiezaRecibida)
		
	//Si no se encontró el valor, hacer clic en el botón "Siguiente" y buscar nuevamente
	if (!encontrado) {
		
		//Realiza la búsqueda nuevamente después de hacer clic en "Siguiente"
		WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnSiguiente'))
		
		//Esperar a que la nueva página se cargue completamente
		WebUI.delay(3)
	}
}

//Cambiar a la ventana del Dashboard
WebUI.switchToWindowIndex(0)

//Se accede al menu Administracion de piezas
menuDesplegable = ["Envio a otra filal o al Activador Comercial"]
link = "Envio o Entrega a AC de Card-Carrier"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Cambiar ventana "BCCL.AP.PIEZAS"
WebUI.switchToWindowTitle('BCCL.AP.PIEZAS')
WebUI.maximizeWindow()

//Setear Numero de Pieza
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), GlobalVariable.vTxn)

//Seleccionar "Boton Modificar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnModificarRegistro'))

//Seleccionar "boton Drop down de Estado"
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/btnDropdownEstado'))

//Seleccionar "Entregada a Activador Comercial"
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblEntregadaActivadorComercial'))

//Seleccionar "Aceptar el registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Validar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
assert element.contains('Txn Completa')

//Setear Numero de Pieza
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), GlobalVariable.vTxn)

//Ver registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

//Validar estado
def element2 = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblEstado'))
assert element2.contains('040')

//Cambiar a la ventana del Dashboard
WebUI.switchToWindowIndex(0)

//Se accede al menu Administracion de piezas
menuDesplegable = ["Reversa de Estado de Card-Carrier"]
link = "Reversa de Estado del Card-Carrier"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Cambiar a la ventana de "Reversa de Estado"
WebUI.switchToWindowIndex(3)

//Setear Numero de Pieza
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), GlobalVariable.vTxn)

//Seleccionar "Boton Modificar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnModificarRegistro'))

//Seleccionar "Aceptar el registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Validar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
def element3 = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
assert element3.contains('Txn Completa')

//Setear Numero de Pieza
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), GlobalVariable.vTxn)

//Ver registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

//Validar estado
def element4 = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblEstado'))
assert element4.contains('010')

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}