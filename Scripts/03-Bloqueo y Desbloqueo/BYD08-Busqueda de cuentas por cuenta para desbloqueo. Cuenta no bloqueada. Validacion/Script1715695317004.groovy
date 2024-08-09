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

def buscarElementoEnTabla(String variable) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {
		WebElement cell = row.findElements(By.tagName("td"))[0]
		String cellText = cell.getText()
		if (cellText.equals(variable)) {
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			assert tdList[8].getText().contains('Desbloqueo Parcial') : "Expected 'Id Transaccion' but found ${tdList[0].getText()}"
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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))
WebUI.maximizeWindow()

def cuenta = '00010056136'
def menuDesplegable0 = ["Cuentas", "Modificacion de cuenta", "Bloqueo y Desbloqueo", "Desbloqueo" ]
def link0 = "Seleccionando Cuenta"

//Si el menu que busco está en dashboard uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable0, link0)

//Switch a la ventana de Bloqueos por Cuenta
WebUI.switchToWindowTitle('BCCL.E.AC.DESBLO.POR.CTA')
WebUI.maximizeWindow()

//Busco la Cuenta a Desbloquear
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Numero Cuenta', cuenta)
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

try {
	WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblMensajeErrorEnBusqueda'))
	assert WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblMensajeErrorEnBusqueda')).contains("NO SE ENCONTRARON REGISTROS")
	
}catch(Exception e) {
	def encontrado = false
	while (!encontrado) {
		encontrado = buscarElementoEnTabla(cuenta)
	}
	WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
	WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
	assert WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta')).contains("Txn Completa:")
	WebUI.closeWindowTitle('BCCL.E.AC.DESBLO.POR.CTA')
	WebUI.switchToWindowIndex(0)
	CustomKeywords.'pkgModules.kywBusquedaMenu.clickearEnLinkDashboard'(link0)
	WebUI.maximizeWindow()
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
	CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Numero Cuenta', cuenta)
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblMensajeErrorEnBusqueda'))
	assert WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblMensajeErrorEnBusqueda')).contains("NO SE ENCONTRARON REGISTROS")
		
}
////Test Case Name: Búsqueda de cuentas por cuenta para desbloqueo. Cuenta no bloqueada. Validación.
//
////Configuracion del ambiente
//CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
//
////Login
//CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,20), findTestData('MainData/Users').getValue(2,20))
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
//WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/02-Desbloqueo/lnkSeleccionandoCuenta'), 6)
//WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/02-Desbloqueo/lnkSeleccionandoCuenta'))
//
////Switch a la ventana de Bloqueos por Cuenta
//WebUI.switchToWindowTitle('BCCL.E.AC.DESBLO.POR.CTA')
//WebUI.maximizeWindow()
//
////Busco la Cuenta a Desbloquear
//WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/txtCuenta'), 6)
//WebUI.setText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/txtCuenta'), '00010056136')
//WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/btnEjecutar'), 6)
//WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/btnEjecutar'))
//
////Verificamos que la cuenta no este bloqueada
//WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/lblNOSEENCONTRARONREGISTROS'), 6)
//WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/lblNOSEENCONTRARONREGISTROS'))
//def element = WebUI.getText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/lblNOSEENCONTRARONREGISTROS'))
//assert element.contains('NO SE ENCONTRARON REGISTROS')


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
