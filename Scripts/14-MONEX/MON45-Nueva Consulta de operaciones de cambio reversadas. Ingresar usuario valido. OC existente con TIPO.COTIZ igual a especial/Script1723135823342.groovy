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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.Keys

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))
WebUI.maximizeWindow()

//Setear "ENQ BCCL.E.MONEX.OPER.IN" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.MONEX.OPER.IN')

//Seleccionar boton buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana "Ingreso de parametros generales de la operacion de Moneda Extranjera"
WebUI.switchToWindowIndex(1)

//Seteo datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Moneda Extranjera', 'USD')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Tipo Operacion', 'Socio Vende')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Tipo Cotizacion', 'E')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Ingreso Fondos', 'EFECTIVO')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Egreso Fondos', 'EFECTIVO')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Seleccionar "ingresar operacion"
WebUI.click(findTestObject('Object Repository/15-MONEX/Ingreso de parametros generales de la operacion de Moneda Extranjera/lnkIngresaroperacion'))

//Setear 100 dolares en importe en mon ext.
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtImporteenMon.Ext'), '100')

//Seleccionar txt "Cotizacion Especial x un."
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtCotizacionEspecialxun'))

//Setear 347,50
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtCotizacionEspecialxun'), '347,50')

//Seleccionar txt "ID Ordenante"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDOrdenante'))

//Setear ID ordenante del usuario Enrico
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtIDOrdenante'), '1000873562')

//Setear "PRUEBAS CRECER" en observaciones
WebUI.setText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/txtObservaciones'), 'PRUEBAS CRECER')

//Seleccionar boton "Aceptar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Definir Objeto
Transaccion = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion.split('\\s+')
def trx1 = partes[2]
assert Transaccion.contains('Txn Completa:')

//Cerrar Sesion
CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()

//Iniciar Sesion con el usuario NEFURNO

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,71), findTestData('MainData/Users').getValue(2,71))
WebUI.maximizeWindow()

def menuDesplegable = ["Autorizaciones"]
def link = "Autorizacion de operaciones"

//Navegar en el Dashboard
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Cambiar a la ventana "BCCL.E.AUTHORIZATION"
WebUI.switchToWindowIndex(1)

//Esperar 5 seg a que se cargue la tabla
WebUI.delay(5)

def autorizarComboBoxEnTabla(String trx1) {
	WebElement table2 = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	List<WebElement> rows2 = table2.findElements(By.tagName("tr"))
	for (WebElement row2 : rows2) {
		WebElement cell2 = row2.findElements(By.tagName("td"))[0]
		String cellText2 = cell2.getText()
		if (cellText2.equals(trx1)) {
			List<WebElement> tdList = row2.findElements(By.tagName("td"))
			WebElement tdElement = tdList[8]
			WebElement comboBox = tdElement.findElement(By.tagName("select"))
			WebElement tdElement1 = tdList[9]
			WebElement btn = tdElement.findElement(By.tagName("img"))
			
			//Utilizar "Select" para interactuar con el comboBox
			def select = new Select(comboBox)
			
			select.selectByVisibleText("AUTORIZAR TRANSACCION")
			
			btn.click()
			
			return true
	
		}
	}
	
	return false
	//Si no se encuentra el ComboBox
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

def encontrado2 = false
while (!encontrado2) {
	encontrado2 = autorizarComboBoxEnTabla(trx1)
	if (!encontrado2) {
		WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnSiguiente'))
		WebUI.delay(2)
	}
}

//Seleccionar boton "Autoriza el Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAutorizaRegistro'))

//Definir Objeto
Transaccion = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
//
////Dividir la cadena por espacios en blanco y tomar elemento
//def partes2 = Transaccion2.split('\\s+')
//def trx2 = partes2[2]
assert Transaccion.contains('Txn Completa:')

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))

//Cerrar Sesion
CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()

//Iniciar Sesion con el usuario F02055

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))
WebUI.maximizeWindow()

def menuDesplegable2 = ["Reversos"]
def link2 = "Reverso de Operaciones"

//Navegar en el Dashboard
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable2, link2)

//Cambiar a la ventana "BCCL.E.EB.CONS.REVE"
WebUI.switchToWindowIndex(1)

//Seteo datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Usuario', 'B.2055')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Esperar 12 seg a que se cargue la tabla
WebUI.delay(12)

def reversarEnTabla(String trx1) {
	WebElement table3 = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	List<WebElement> rows3 = table3.findElements(By.tagName("tr"))
	for (WebElement row3 : rows3) {
		WebElement cell3 = row3.findElements(By.tagName("td"))[1]
		String cellText3 = cell3.getText()
		
		if (cellText3.equals(trx1)) {
			List<WebElement> tdList3 = row3.findElements(By.tagName("td"))
			WebElement tdElement3 = tdList3[17]
			WebElement lnkReversar = tdElement3.findElement(By.tagName("a"))
			lnkReversar.click()
			return true
		}
	}
	return false
}

def encontrado3 = false
while (!encontrado3) {
	encontrado3 = reversarEnTabla(trx1)
	if (!encontrado3) {
		WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnSiguiente'))
		WebUI.delay(2)
	}
}

//Seleccionar boton "Reversar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnReversar'))

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Setear Transaccion en "Compra-Venta de Moneda Extranjera"
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), trx1)

//Seleccionar "Ver Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

//Verificar "E - Especial"
WebUI.verifyElementVisible(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/lblE-Especial'))

//Validar "E - Especial"
def especial = WebUI.getText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/lblE-Especial'))
assert especial.contains('E - Especial')

//Seleccionar "Auditoria"
WebUI.click(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/lblAuditoria'))

//Verificar "REVE"
WebUI.verifyElementVisible(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/lblREVE'))

//Validar "REVE"
def reve = WebUI.getText(findTestObject('Object Repository/15-MONEX/12-Compra-Venta/lblREVE'))
assert reve.contains('REVE')

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}