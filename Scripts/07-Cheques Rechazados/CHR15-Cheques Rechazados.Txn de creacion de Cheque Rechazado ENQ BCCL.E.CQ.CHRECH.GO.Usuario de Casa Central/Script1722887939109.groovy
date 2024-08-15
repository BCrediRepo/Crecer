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
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.Keys

def numeroCheque = '75963482'
def numeroCuenta = '01192463327'

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,8), findTestData('MainData/Users').getValue(2,8))
WebUI.maximizeWindow()

//Setear "ENQ BCCL.E.CQ.CHRECH.GO" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CQ.CHRECH.GO')

//Seleccionar boton buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana "ALTA DE CHEQUE RECHAZADO"
WebUI.switchToWindowIndex(1)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('NUMERO DE CUENTA', numeroCuenta)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('NUMERO CHEQUE', numeroCheque)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Seleccionar "Rechazar Cheque/Camb Fec cancel"
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/ALTA DE CHEQUE RECHAZADO/lnkRechazarChequeCambFeccancel'))

//Setear Monto del Cheque
WebUI.setText(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.CQ.CHEQUES.RECHAZADOS/txtMntCheque'), '100')

//Seleccionar boton Id Mot Rech
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.CQ.CHEQUES.RECHAZADOS/btnIdMotRech'))

//Seleccionar label "No Coincide Firma"
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.CQ.CHEQUES.RECHAZADOS/lblNoCoincideFirma'))

//Seleccionar opcion "No Cobrada" en el Combo Box
WebUI.selectOptionByIndex(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.CQ.CHEQUES.RECHAZADOS/cbEstadoMulta'), 1)

//Seleccionar boton Aceptar Registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Seleccionar Aceptar Alertas
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Verificar "BCCL.CQ.CHEQUES.RECHAZADOS,RECHXGO I"
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))

//Validar "BCCL.CQ.CHEQUES.RECHAZADOS,RECHXGO I"
def element = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert element.contains('BCCL.CQ.CHEQUES.RECHAZADOS,RECHXGO I')

//Cerrar Sesion
CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()

//Volver a Logearse con el usuario JANDINO

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 37), findTestData('MainData/Users').getValue(2, 37))
WebUI.maximizeWindow()

def menuDesplegable = ["Autorizaciones"]
def link = "Autorizaciones pendientes"

//Navegar en el Dashboard
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Cambiar a la ventana "BCCL.E.AUTHORIZATION"
WebUI.switchToWindowIndex(1)

//Esperar 3 segundos a que cargue la tabla
WebUI.delay(3)

//Maximizar Ventana
WebUI.maximizeWindow()

def buscarChequeEnTabla(String numeroCheque) {
	WebElement table2 = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	List<WebElement> rows2 = table2.findElements(By.tagName("tr"))
	for (WebElement row2 : rows2) {
		WebElement cell2 = row2.findElements(By.tagName("td"))[0]
		String cellText2 = cell2.getText()
		if (cellText2.equals(numeroCheque)) {
			List<WebElement> tdList2 = row2.findElements(By.tagName("td"))
			WebElement tdElement2 = tdList2[8]
			WebElement comboBox = tdElement2.findElement(By.tagName("select"))
			
			//Utilizar "Select" para interactuar con el comboBox
			def select = new Select(comboBox)
			select.selectByVisibleText("ELIMINAR TRANSACCION")
			
			//Encontrar elemento 'img' dentro del enlace 'a'
			WebElement imgElement = tdElement2.findElement(By.cssSelector("a[title='Select Drilldown'] img"))
			
			//Seleccionar elemento 'img'
			imgElement.click()
			
			return true
		}
	}
	return false
}

def encontrado2 = false
while (!encontrado2) {
	encontrado2 = buscarChequeEnTabla(numeroCheque)
	if (!encontrado2) {
		WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnSiguiente'))
		WebUI.delay(2)
	}
}

//Obtener el WebDriver actual
WebDriver driver = DriverFactory.getWebDriver()

//Seleccionar boton Eliminar Registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnEliminarRegistro'))

//Presionar "Enter"
driver.switchTo().alert().accept()

//Seleccionar Aceptar Alertas
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}