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
import com.kms.katalon.core.webui.driver.DriverFactory
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.support.ui.Select

def cuenta='00890018048'
def importe = '5500000'
def idpers = '1003478481'

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,17), findTestData('MainData/Users').getValue(2,17))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando menu Teller pago de cheque por mostrador
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("TELLER,BCCL.PAGO.CQMOS I F3", 1)

//completo campos
WebUI.setText(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/txtCuenta'), cuenta)
WebUI.click(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/txtImporteaPagar'))
WebUI.setText(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/txtImporteaPagar'), importe)
WebUI.setText(findTestObject('Object Repository/51-Deposito-Extracciones/TELLER/txtIdPersona'), idpers)

//valido, acepto registro y acepto alertas
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Fuerzo firmas
WebUI.switchToWindowTitle('Verificacion de Firmas - Fil.089 M.del Plata Ct')

//Seleccionar "FORZAR" del combo box
WebUI.selectOptionByIndex(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/cbxAccion'), 2)
WebUI.click(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/btnAceptar'))
WebUI.click(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lnkAceptarAlertas'))

//Validar "FINALIZADA"
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblFinalizada'))
def element = WebUI.getText(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblFinalizada'))
assert element.contains('FINALIZADA')

//cambio a pestaña teller para tomar id transaccion
WebUI.switchToWindowIndex(1)

//Definir Objeto
Transaccion = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))
 
//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion.split('\\s+')
def trx1 = partes[2]
println(trx1)
//cambio al dashboard
WebUI.switchToWindowIndex(0)

//Cerrar Sesion
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnLogout'))
WebUI.closeBrowser()

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()

//ingreso a transacciones pendientes
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("ENQ BCCL.E.AUTHORIZATION", 1)
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkNuevaSeleccion'))
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

def autorizarComboBoxEnTabla(String trx1) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {
		WebElement cell = row.findElements(By.tagName("td"))[0]
		println(cell)
		String cellText = cell.getText()
		if (cellText.equals(trx1)) {
			List<WebElement> tdList = row.findElements(By.tagName("td"))
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
 
def encontrado = false
while (!encontrado) {
	encontrado = autorizarComboBoxEnTabla(trx1)
	if (!encontrado) {
		WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnSiguiente'))
		WebUI.delay(2)
	}
}

//Seleccionar "boton Autorizar Registro"
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/BCCL.CQ.SOLICITUD/btnAutorizarRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/11-Emision Chequera/BCCL.CQ.SOLICITUD/lblTxnCompleta'))

//Validar "Txn Completa"
def element2 = WebUI.getText(findTestObject('Object Repository/11-Emision Chequera/BCCL.CQ.SOLICITUD/lblTxnCompleta'))
assert element2.contains('Txn Completa')

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}
@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}