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
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory
def suc = '043'
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()

//Se accede al menu Administracion de piezas
menuDesplegable = ["Administracion de Piezas con Tarjetas","Modificaciones sobre Card-Carrier","Consultas al Maestro de Card-Carrier"]
link = "Seleccion por Persona/Sucursal/Lote"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Cambiar ventana "BCCL.E.AP.ENQ.NOMBRE.DOC"
WebUI.switchToWindowTitle('BCCL.E.AP.ENQ.NOMBRE.DOC')

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', suc)
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))
	
//Variable booleana de corte
def accionRealizada = false
int i = 0
WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
List<WebElement> rows = table.findElements(By.tagName("tr"))

//Bucle interno para recorrer las filas de la tabla que no se por que pero en el codigo HTML vienen dado por un indice que va de dos en dos
while (i < rows.size() && !accionRealizada) {
	WebElement row = rows.get(i)
	WebElement cell = row.findElements(By.tagName("td"))[0]
	String numPieza = cell.getText()
	try {
		//Cambiar ventana principal
		WebUI.switchToWindowIndex(0)
		
		//Se accede al menu Administracion de piezas
						menuDesplegable = []
						link = "Modificacion datos de Card-Carrier"
						CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
						
		//Cambiar ventana "BCCL.AP.PIEZAS"
						WebUI.switchToWindowTitle('BCCL.AP.PIEZAS')
						WebUI.maximizeWindow()
						
		//Modificamos el numero de pieza elegido
						WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), numPieza)
						WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnModificarRegistro'))
						
		//OBTENGO EL VALUE DE UN INPUT
						WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblAPMarca'))
						WebElement inputElement = DriverFactory.getWebDriver().findElement(By.id("fieldName:AP.MARCA"))
						String marca = inputElement.getAttribute("value")
						
		//Pregunto el valor predeterminado para realizar la modificacion de la marca
						if (marca == "CABAL") {
							WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblAPMarca'), "VISA")
						} else {
							WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblAPMarca'), "CABAL")
						}
				
		//Seleccionar "Aceptar el registro"
						WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
				
		//Validar "Txn Completa"
						WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
						def element = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
						assert element.contains('Txn Completa')
		//Condicion de corte
						accionRealizada = true
		
	} catch (Exception e) {
		WebUI.closeWindowIndex(2)
		WebUI.switchToWindowIndex(1)
		accionRealizada = false
        // Manejo de errores
        WebUI.comment("Error al realizar la acción: " + e.message)
          	}	
	i = i+2
}


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