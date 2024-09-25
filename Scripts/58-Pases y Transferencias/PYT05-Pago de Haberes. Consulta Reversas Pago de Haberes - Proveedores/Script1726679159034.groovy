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

def validarElementoEnTabla(String tabla, String variable, int colVariable, String razon, int colRazon) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id(tabla))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {
		WebElement cell = row.findElements(By.tagName("td"))[colVariable]
		String cellText = cell.getText()
		if (cellText.equals(variable)) {
			List<WebElement> tdList = row.findElements(By.tagName("td"))
					String resultado = tdList[colRazon].getText()
					println(resultado)
			assert tdList[colRazon].getText().contains(razon) : "Expected " + razon + " but found ${tdList[colRazon].getText()}"
			GlobalVariable.vTxn = resultado
			return true
		}
	}
	return false
}

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

//Llamo al caso 02 para realizar un alta
WebUI.callTestCase(findTestCase('58-Pases y Transferencias/PYT02-Pago de Haberes.Alta de transferencia multiple.Consulta de datos'), [:], FailureHandling.STOP_ON_FAILURE)

String trx = GlobalVariable.vTxn

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 22), findTestData('MainData/Users').getValue(2, 22))
WebUI.maximizeWindow()

//ingreso a "Reverso de Operaciones"
def menuDesplegable = ["Reversos"]
def link = "Reverso de Operaciones"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowIndex(1)

//Seteo de Datos "Usuario"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Usuario', 'B.0319')
WebUI.click(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/lnkEjecutar'))

//Espero Para que cargue datos
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/06-ToolBar/btnPantallaDeSeleccion'),6)

//Click en lnk Reversar
def encontrado = false
while (!encontrado) {
	encontrado = clickLinkBotonTabla(trx,1,17)
}

//Click en reversar registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnReversar'))

//Valido txn completa
Transaccion1 = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert Transaccion1.contains('Txn Completa:')

//Chequeo Status REVERSADO
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), trx)
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

//Click en lnk Reversar
encontrado = false
while (!encontrado) {
	encontrado = validarElementoEnTabla('tab1', 'Status', 0,'REVERSADO', 2)
}


//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}