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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory

def buscarElementoEnTabla(String variable, int posLink) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {
		WebElement cell = row.findElements(By.tagName("td"))[0]
		String cellText = cell.getText()
		if (cellText.equals(variable)) {
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			WebElement tdElement = tdList[posLink]
			WebElement lnkElement = tdElement.findElement(By.tagName("a"))
			lnkElement.click()
			return true
		}
	}
	return false
}

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 56), findTestData('MainData/Users').getValue(
        2, 56))

def cuenta = '10200022000'
def fecha = GlobalVariable.vFechaCOB
def menuDesplegable0 = ["Cuentas", "Modificacion de cuenta", "Bloqueo y Desbloqueo", "Bloqueo" ]
def link0 = "Seleccionando Cuenta"

//Si el menu que busco está en dashboard uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable0, link0)

WebUI.switchToWindowTitle('BCCL.E.AC.BLO.POR.CTA')

//Seteo de Datos "Cuenta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cuenta', cuenta)

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

try {
	def encontrado1 = false
	while (!encontrado1) {
		encontrado1 = buscarElementoEnTabla(cuenta, 7)
	}
	WebUI.switchToWindowTitle("CUENTAS")
	WebUI.setText(findTestObject('04-Bloqueo y Desbloqueo/CUENTAS/txtTipobloqueo'), '1')
	WebUI.setText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/CUENTAS/txtMotivo'), 'AF')
	WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
	WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
	assert WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta')).contains("Txn Completa: ")
}catch(Exception e){
	WebUI.closeWindowTitle('CUENTAS')
	WebUI.switchToWindowIndex(0)
	def menuDesplegable1 = ["Bloqueo", "Desbloqueo" ]	
	
	//Si el menu que busco está en dashboard uso esta funcion
	CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable1, link0)
	WebUI.switchToWindowTitle('BCCL.E.AC.DESBLO.POR.CTA')
	//Busco la Cuenta a Desbloquear
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
	CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Numero Cuenta', cuenta)
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
	def encontrado = false
	while (!encontrado) {
		encontrado = buscarElementoEnTabla(cuenta, 7)
	}
	WebUI.switchToWindowTitle("CUENTAS")
	WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
	WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
	assert WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta')).contains("Txn Completa:")
	WebUI.closeWindowTitle("CUENTAS")
	WebUI.switchToWindowIndex(0)
	CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable1, link0)
	WebUI.switchToWindowTitle('BCCL.E.AC.BLO.POR.CTA')
	
	//Seteo de Datos "Cuenta"
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
	CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cuenta', cuenta)
	
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
	def encontrado1 = false
	while (!encontrado1) {
		encontrado1 = buscarElementoEnTabla(cuenta, 7)
	}
	WebUI.switchToWindowTitle("CUENTAS")
	WebUI.switchToWindowTitle("CUENTAS")
	WebUI.setText(findTestObject('04-Bloqueo y Desbloqueo/CUENTAS/txtTipobloqueo'), '1')
	WebUI.setText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/CUENTAS/txtMotivo'), 'AF')
	WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
	WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
	assert WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta')).contains("Txn Completa: ")
}


		

	
	

 //Control fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

