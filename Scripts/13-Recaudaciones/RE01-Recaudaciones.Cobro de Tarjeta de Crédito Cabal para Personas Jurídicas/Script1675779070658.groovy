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
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory


def buscarElementoEnTabla(String variable1, String variable2) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {
		WebElement cell1 = row.findElements(By.tagName("td"))[2]
		String cellText1 = cell1.getText()
		WebElement cell2 = row.findElements(By.tagName("td"))[6]
		String cellText2 = cell2.getText()
		if (cellText1.equals(variable1)&&cellText2.equals(variable2)) {
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			WebElement tdElement = tdList[19]
			WebElement lnkElement = tdElement.findElement(By.tagName("a"))
			lnkElement.click()
			return true
		}
	}
	return false
}

def ValidarElementoEnTabla(String variable1, String variable2) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {
		WebElement cell1 = row.findElements(By.tagName("td"))[0]
		assert cell1.getText().contains(variable1)
		WebElement cell2 = row.findElements(By.tagName("td"))[1]
		assert cell2.getText().contains(variable2)
		return true
	}
	return false
}

//Configuracion de ambiente y login
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 64), findTestData('MainData/Users').getValue(
        2, 64))

WebUI.maximizeWindow()

//Vamos a la tabla ENQ BCCL.E.VISION.PAGO.TJ
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.VISION.PAGO.TJ')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))
WebUI.switchToWindowTitle('BCCL.E.VISION.PAGO.TJ')

//Seteo los datos del script
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Persona', '1000922908')
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//validamos en la tabla que sea una tarjeta CABAL de tipo EMPRESA
def Producto = "CB"
def TipoCuenta= "EMPRESA"
def encontrado = false
while (!encontrado) {
	encontrado = buscarElementoEnTabla(Producto,TipoCuenta)
	if (!encontrado) {
		WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnSiguiente'))
		WebUI.delay(2)
	}
}

//Cargamos los valores EFECTIVO y 50
WebUI.switchToWindowTitle('Pago de Tarjetas S/ Cod Barra. - Fil.042 Saavedra')
WebUI.selectOptionByIndex(findTestObject('Object Repository/14-Recaudaciones/01-BCCL.E.VISION.PAGO.TJ/selectTipo'), '3')
WebUI.setText(findTestObject('Object Repository/14-Recaudaciones/01-BCCL.E.VISION.PAGO.TJ/txtImporte'), '50')
WebUI.switchToFrame(findTestObject('Object Repository/00-Utils/04-Frames/frmInferior'), 3)
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
def TxnInicial = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert TxnInicial.contains('Txn Completa')
def parts = TxnInicial.tokenize(' ')
GlobalVariable.vTxn = parts[2]
WebUI.switchToWindowTitle('BCCL.E.EST.PAGO.TARJ')

//Validamos que el pago de la tarjeta haya sido autorizado
def Descripcion = "CABAL"
def Estado = "AUTORIZADA"

WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
List<WebElement> rows = table.findElements(By.tagName("tr"))
for (WebElement row : rows) {
	WebElement cell3 = row.findElements(By.tagName("td"))[0]
	WebElement cell4 = row.findElements(By.tagName("td"))[1]
	
	assert cell3.getText().contains(Descripcion)
	assert cell4.getText().equals(Estado)
	
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

