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
 
def clickLinkBotonTabla(String variable, int posVariable, int posLink) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {
		WebElement cell = row.findElements(By.tagName("td"))[posVariable]
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
 
def frame = findTestObject('Object Repository/00-Utils/02-Filtros/Filtros con frame/frmFiltro')
def frame2 = findTestObject('Object Repository/00-Utils/04-Frames/frmInferior')
def cuenta = '00730238724'
def cheque = '67998106'
def variable = cuenta
 
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,40), findTestData('MainData/Users').getValue(2,40))
 
//Si el menu que busco está en dashboard uso esta funcion
def menuDesplegable = ["Rechazo de cheques", "Transacciones Especiales" ]
def link = "Cambio de Motivo de Rechazo"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
 
//Cambio de pestaña a Cambio De Motivo De Rechazo
WebUI.switchToWindowIndex(1)
WebUI.switchToFrame(frame, 3)
 
//Seteo de Datos
WebUI.waitForElementVisible(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'),6)
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('NUMERO DE CUENTA', cuenta)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('NUMERO CHEQUE', cheque)
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))
 
//Click en lnk Cambio de motivo de rechazo
def encontrado = false
while (!encontrado) {
	encontrado = clickLinkBotonTabla(variable,0,4)
}
 
//Cambio a frame inferior
WebUI.switchToWindowIndex(1)
WebUI.switchToFrame(frame2, 3)

//Selecciono los tres distintos valores desde los desplegables
WebUI.waitForElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/btnSelectDrilldownCausa'),6)
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/btnSelectDrilldownCausa'))
WebUI.waitForElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/lblOtrosSegunLeyCheques'),6)
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/lblOtrosSegunLeyCheques')) //917

WebUI.waitForElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/btnSelectDrilldownSubCausa'),6)
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/btnSelectDrilldownSubCausa'))
WebUI.waitForElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/lblFuerzaMayor'),6)
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/lblFuerzaMayor')) //98

WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/txtSubCausaPrueba'))//Este click es solo para que se recargue la pag
WebUI.waitForElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/btnSelectDrilldownMotivoRech'),6)
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/btnSelectDrilldownMotivoRech'))
WebUI.waitForElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/lblFuerzaMayorMotRech'),6)
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/lblFuerzaMayorMotRech')) //81

WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
 
// Verifica si el elemento "Aceptar Alertas" está presente y lo clickea
if (WebUI.verifyElementPresent(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'), 5, FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
}

//Valido Txn Completa y tomo num de txn
def TxnInicial = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert TxnInicial.contains('Txn Completa')
def parts = TxnInicial.tokenize(' ')
def transaccion = parts[2]
 
//Lleno con num de TXN en la barra
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), transaccion)
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnModificarRegistro'))

//Selecciono los tres distintos valores desde los desplegables
//Modifico para que quede en ONP
WebUI.waitForElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/btnSelectDrilldownMotivoRech'),6)
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/btnSelectDrilldownMotivoRech'))
WebUI.waitForElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/lblOrdenDeNoPagar'),6)
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/lblOrdenDeNoPagar')) //08
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

WebUI.waitForElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/btnSelectDrilldownCausa'),6)
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/btnSelectDrilldownCausa'))
WebUI.waitForElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/lblCausaONP'),6)
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/lblCausaONP')) //908

WebUI.waitForElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/btnSelectDrilldownSubCausa'),6)
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/btnSelectDrilldownSubCausa'))
WebUI.waitForElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/lblSubCausaONP'),6)
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Motivo Rechazo/lblSubCausaONP')) //24

WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
 
//Acepto alertas
if (WebUI.verifyElementPresent(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'), 5, FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
}
 
//Valido txn 
def TxnInicial2 = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert TxnInicial2.contains('Txn Completa')

//------------------------------------------------------------------------------------------------------
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}
 
@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}