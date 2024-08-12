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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory

def validarElementoEnTabla(String variable) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {
		WebElement cell = row.findElements(By.tagName("td"))[1]
		String cellText = cell.getText()
		if (cellText.equals(variable)) {
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			assert tdList[8].getText().contains('Desbloqueo Parcial') : "Expected 'Desbloqueo Parcial' but found ${tdList[8].getText()}"
			String cuentauser = tdList[0].getText()
			GlobalVariable.vTxn = cuentauser
			return true
		}
	}
	return false
}

def buscarElementoEnTabla(String variable) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {
		WebElement cell = row.findElements(By.tagName("td"))[1]
		String cellText = cell.getText()
		if (cellText.equals(variable)) {
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			WebElement tdElement = tdList[7]
			WebElement lnkElement = tdElement.findElement(By.tagName("a"))
			lnkElement.click()
			return true
		}
	}
	return false
}

//Configuracion del ambiente y login
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,22), findTestData('MainData/Users').getValue(2,22))
WebUI.maximizeWindow()

def CUI = '20251843881'
def menuDesplegable0 = ["Cuentas", "Modificacion de cuenta", "Bloqueo y Desbloqueo", "Desbloqueo" ]
def link0 = "Seleccionando Persona"

//Si el menu que busco está en dashboard uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable0, link0)

//Switch a la ventana de Bloqueos por Cuenta
WebUI.switchToWindowTitle('BCCL.E.AC.DESBLO.POR.PER')
WebUI.maximizeWindow()

//Busco la Cuenta a Desbloquear
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro CUI', CUI)
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
def titular = "ESPOSITO PEDRO ALEJANDRO"
def encontrado = false
try {	
	while (!encontrado) {
		encontrado = validarElementoEnTabla(titular)
	}
	String cuentita = GlobalVariable.vTxn
	assert cuentita.contains('0119')	
}catch(Exception e) {
	WebUI.closeWindowTitle('BCCL.E.AC.DESBLO.POR.PER')
	WebUI.switchToWindowIndex(0)
	def menuDesplegable1 = ["Desbloqueo", "Bloqueo"]
	CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable1, link0)
	WebUI.switchToWindowTitle('BCCL.E.AC.BLO.POR.PER')
	//Busco la Cuenta a bloquear
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
	CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro CUI', CUI)
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
	encontrado = false
	while (!encontrado) {
		encontrado = buscarElementoEnTabla(titular)
	}
	
	WebUI.switchToWindowTitle("CUENTAS")
	
	WebUI.setText(findTestObject('04-Bloqueo y Desbloqueo/CUENTAS/txtTipobloqueo'), '1')
	WebUI.setText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/CUENTAS/txtMotivo'), 'AF')
	WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
	WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
	
	WebUI.closeWindowTitle("CUENTAS")
	WebUI.switchToWindowIndex(0)
	
	def menuDesplegable2 = ["Bloqueo", "Desbloqueo"]
	CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable1, link0)
	
	WebUI.switchToWindowTitle('BCCL.E.AC.DESBLO.POR.PER')
	WebUI.maximizeWindow()
	
	//Busco la Cuenta a Desbloquear
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
	CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro CUI', CUI)
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
	encontrado = false
	while (!encontrado) {
		encontrado = validarElementoEnTabla(titular)
	}
	String cuentita = GlobalVariable.vTxn
	assert cuentita.contains('0119')
}
////Test Case Name: Búsqueda de cuentas por persona para desbloqueo. Búsqueda por Nro de CUI.
//
////Configuracion del ambiente
//CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
//
////Login
//CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,19), findTestData('MainData/Users').getValue(2,19))
//WebUI.maximizeWindow()
//
////Accedo al menu de Bloqueo y Desbloqueo
//WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lnkCuentas'), 6)
//WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))
//WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkModificaciondDeCuenta'), 6)
//WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkModificaciondDeCuenta'))
//WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/lnkBloqueoyDesbloqueo'), 6)
//WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/lnkBloqueoyDesbloqueo'))
//WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/lnkDesbloqueo'), 6)
//WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/lnkDesbloqueo'))
//WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/02-Desbloqueo/lnkSeleccionandoPersona'), 6)
//WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/02-Desbloqueo/lnkSeleccionandoPersona'))
//
////Switch a la ventana de Bloqueos por Persona
//WebUI.switchToWindowTitle('BCCL.E.AC.DESBLO.POR.PER')
//WebUI.maximizeWindow()
//
////Seteo de Datos Persona a Desbloquear por CUI
//WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
//WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/03-Desbloqueo seleccionando Persona/txtNroCUI'), 6)
//CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro CUI', '30605261716')
//
////Seleccionar "Ejecutar"
//WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/btnEjecutar'), 6)
//WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/btnEjecutar'))
//
//boolean isVisible = false
//try {
//	isVisible = WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/03-Desbloqueo seleccionando Persona/lblCuentaADesbloquear'))
//	//Verifico que sea la cuenta que se muestra y procedemos al bloqueo General
//	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/03-Desbloqueo seleccionando Persona/lblCuentaADesbloquear'), 6)
//	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/03-Desbloqueo seleccionando Persona/lblCuentaADesbloquear'))
//	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/03-Desbloqueo seleccionando Persona/lnkDesbloqueoGeneral'), 6)
//	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/03-Desbloqueo seleccionando Persona/lnkDesbloqueoGeneral'))
//	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
//} catch (Exception e) {
//	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/03-Desbloqueo seleccionando Persona/lblNOSEENCONTRARONREGISTROS'), 6)
//	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/03-Desbloqueo seleccionando Persona/lblNOSEENCONTRARONREGISTROS'))
//	WebUI.closeWindowIndex(1)
//	WebUI.switchToWindowIndex(0)
//	WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/lnkBloqueo'), 6)
//	WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/lnkBloqueo'))
//	WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/01-Bloqueo/lnkSeleccionandoCuenta'), 6)
//	WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/01-Bloqueo/lnkSeleccionandoCuenta'))
//	
//	//Switch a la ventana de Bloqueos por Cuenta
//	WebUI.switchToWindowTitle('BCCL.E.AC.BLO.POR.CTA')
//	WebUI.maximizeWindow()
//	
//	//Busco la Cuenta a Bloquear
//	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/txtCuenta'), 6)
//	WebUI.setText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/txtCuenta'), '00760480575')
//	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/btnEjecutar'), 6)
//	WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/btnEjecutar'))
//	
//	//Verifico que sea la cuenta que se muestra y procedemos al bloqueo General
//	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lblCuenta'), 6)
//	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lblCuenta'))
//	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lnkBloqueoGeneral'), 6)
//	WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lnkBloqueoGeneral'))
//	
//	//Switch a la ventana de CUENTAS
//	WebUI.switchToWindowTitle('CUENTAS')
//	WebUI.maximizeWindow()
//	
//	//Completo el registro de bloqueo y acepto el registro
//	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/txtTipoBloqueo'), 6)
//	WebUI.setText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/txtTipoBloqueo'), '1')
//	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/txtMotivo'), 6)
//	WebUI.setText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/txtMotivo'), 'AF')
//	WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/btnAceptarRegistro'))
//	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lnkAceptarAlertas'), 6)
//	WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lnkAceptarAlertas'))
//	
//	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lblTxnCompleta'), 6)
//	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lblTxnCompleta'))
//	def element = WebUI.getText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lblTxnCompleta'))
//	assert element.contains('Txn Completa:')
//	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
//	//-----------------------------------------------------------------
//	WebUI.switchToWindowIndex(0)
//	WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/02-Desbloqueo/lnkSeleccionandoPersona'), 6)
//	WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/02-Desbloqueo/lnkSeleccionandoPersona'))
//	WebUI.switchToWindowTitle('BCCL.E.AC.DESBLO.POR.PER')
//	WebUI.maximizeWindow()
//	
//	//Seteo de Datos (Persona a Desbloquear por CUI)
//	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
//	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/03-Desbloqueo seleccionando Persona/txtNroCUI'), 6)
//	CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro CUI', '30605261716')
//	
//	//Seleccionar "Ejecutar"
//	WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/btnEjecutar'))
//	
//	//Verifico que sea la cuenta que se muestra y verificamos
//	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/03-Desbloqueo seleccionando Persona/lblCuentaADesbloquear'), 6)
//	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/03-Desbloqueo seleccionando Persona/lblCuentaADesbloquear'))
//	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/03-Desbloqueo seleccionando Persona/lnkDesbloqueoGeneral'), 6)
//	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/03-Desbloqueo seleccionando Persona/lnkDesbloqueoGeneral'))
//	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
//	isVisible = false
//}

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
