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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()

//Se accede al menu Administracion de piezas
menuDesplegable = ["Administracion de Piezas con Tarjetas","Consultas al Maestro de Card-Carrier"]
link = "Seleccion por Nombre / Documento / Sucursal"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Cambiar ventana "BCCL.E.AP.ENQ.NOMBRE.DOC"
WebUI.switchToWindowTitle('BCCL.E.AP.ENQ.NOMBRE.DOC')

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '043')
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Selecciono una pieza de la tabla con estado 010 para luego modificarla
def estadoValor = "010"
def buscarElementoEnTabla(String estadoValor, String numPieza) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {
		WebElement cell = row.findElements(By.tagName("td"))[7]
		String cellText = cell.getText()
		if (cellText.equals(estadoValor)) {
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			WebElement tdElement = tdList[0]
			WebElement lnkElement = tdElement.findElement(By.tagName("a"))
			numPieza = lnkElement.getText()
			return true
		}
	}
	return false
}

/*
def encontrado = false
def numPieza
while (!encontrado) {
	encontrado = buscarElementoEnTabla(estadoValor, numPieza)
	if (!encontrado) {
		WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnSiguiente'))
		WebUI.delay(2)
	}
}


//Cambiar ventana principal
WebUI.switchToWindowIndex(0)

//Se accede al menu Administracion de piezas
menuDesplegable = ["Modificaciones sobre Card-Carrier"]
link = "Modificacion datos de Card-Carrier"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Cambiar ventana "BCCL.AP.PIEZAS"
WebUI.switchToWindowTitle('BCCL.AP.PIEZAS')
WebUI.maximizeWindow()

//Modificamos el numero de pieza elegido
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), numPieza)
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnModificarRegistro'))



if (valorPrimerEstado.equals('010') && valorPrimeraMarca.equals('CABAL')) {
	WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/txtNro.de Pieza'), valorPrimeraPieza)
	
	//Screenshot
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
	
	
	//Setear Marca VISA
	WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/txtMARCA'), 'VISA')
	
	//Seleccionar "Aceptar el registro"
	WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/btnAceptarRegistro'))
	
	//Verificar "Txn Completa"
	WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
	
	//Validar "Txn Completa"
	def element = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
	assert element.contains('Txn Completa')
	
	
} else {
	if (valorSegundoEstado.equals('010') && valorSegundaMarca.equals('CABAL')) {
		WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/txtNro.de Pieza'), valorSegundaPieza)
		
		//Screenshot
		CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
		
		//Seleccionar "Boton Modificar Registro"
		WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/btnModificarRegistro'))
		
		//Setear Marca VISA
		WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/txtMARCA'), 'VISA')
		
		//Seleccionar "Aceptar el registro"
		WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/btnAceptarRegistro'))
		
		//Verificar "Txn Completa"
		WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
		
		//Validar "Txn Completa"
		def element = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
		assert element.contains('Txn Completa')
		
		
	} else {
		if (valorTercerEstado.equals('010') && valorTerceraMarca.equals('CABAL')) {
			WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/txtNro.de Pieza'), valorTerceraPieza)
			
			//Screenshot
			CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
			
			//Seleccionar "Boton Modificar Registro"
			WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/btnModificarRegistro'))
			
			//Setear Marca VISA
			WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/txtMARCA'), 'VISA')
			
			//Seleccionar "Aceptar el registro"
			WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/btnAceptarRegistro'))
			
			//Verificar "Txn Completa"
			WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
			
			//Validar "Txn Completa"
			def element = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
			assert element.contains('Txn Completa')
			
			
		} else {
			WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/txtNro.de Pieza'), valorPrimeraPieza)
			
			//Screenshot
			CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
			
			//Seleccionar "Boton Modificar Registro"
			WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/btnModificarRegistro'))
			
			//Setear Marca CABAL
			WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/txtMARCA'), 'CABAL')
			
			//Seleccionar "Aceptar el registro"
			WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/btnAceptarRegistro'))
			
			//Verificar "Txn Completa"
			WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
			
			//Validar "Txn Completa"
			def element = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
			assert element.contains('Txn Completa')
		}
	}
}
*/
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