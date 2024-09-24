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
import org.jsoup.Jsoup as Jsoup
import org.jsoup.nodes.Document as Document
import org.openqa.selenium.support.ui.Select as Select

def clickLinkBotonTabla(String tabla, String variable, int posVariable, int posLink) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id(tabla))
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

//Generacion de posteo a traves de caso IP07
WebUI.callTestCase(findTestCase('26-Inventario Permanente/IP07-Alta de partida de IP. Caja'), [:], FailureHandling.STOP_ON_FAILURE)

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 59), findTestData('MainData/Users').getValue(2, 59))

menuDesplegable = ['Inventario Permanente']
link = 'Baja de Partidas'
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Cambiar a la ventana "BCCL.E.IP.BAJA.PARTIDAS"
WebUI.switchToWindowIndex(1)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Codigo IP', '0099')
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Cambiar opción del ComboBox a "Baja Cuenta Caja"
WebUI.selectOptionByIndex(findTestObject('Object Repository/27-Inventario Permanente/BCCL.E.IP.BAJA.PARTIDAS/cbBajaCuentaContable'), 3)
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.E.IP.BAJA.PARTIDAS/btnSelectDrilldown'))
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Definir Objeto
def TxnInicial = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def parts = TxnInicial.tokenize(' ')
def transaccion = parts[2]

assert TxnInicial.contains('Txn Completa')
println(transaccion)
//Cambiar a la ventana del Dashboard
WebUI.closeWindowIndex(1)
WebUI.switchToWindowIndex(0)

CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'('BCCL.IP.PARTIDAS', 1)

//Setear Numero de Partida
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), transaccion)
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))
ftBaja = WebUI.getText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/lblFtBaja'))
println(ftBaja)

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 2), findTestData('MainData/Users').getValue(2, 2))

CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'('?303', 1)
menuDesplegable1 = ['Posteo']
link1 = 'Transacciones Pendientes de Liquidacion'
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable1, link1)

//Cambiar ventana "BCCL.E.EB.POSTEO.INAU"
WebUI.switchToWindowTitle('BCCL.E.EB.POSTEO.INAU')

//Definir GlobalVariable como "variable"
def variable = ftBaja
def encontrado = false
while(!encontrado) {
	encontrado = clickLinkBotonTabla('datadisplay', ftBaja, 0, 8)
}


//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Autorizar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAutorizaRegistro'))
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert element.contains('Txn Completa') 

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

