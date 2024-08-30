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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,2), findTestData('MainData/Users').getValue(2,2))
WebUI.maximizeWindow()

def menuDesplegable = ["Plazo Fijo", "Modificacion de Plazo Fijo", "Reversas y Bajas"]
def link = "Baja de Plazo Fijo con Renovacion Automatica"
def menuDesplegable2 = ["Autorizaciones"]
def link2 = "Autorizaciones Pendientes"

//Setear "?302" en la linea de comando
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("?302", 1)

//Cambiar a la ventana "Temenos T24"
WebUI.switchToWindowIndex(1)

//Navegar por el menu Temenos T24
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Cambiar a la ventana "Baja P.Fijo c/Renovacion Automatica"
WebUI.switchToWindowIndex(2)

//Seteo datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '089')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Seleccionar "Baja P.Fijo c/Renovacion Automatica"
WebUI.click(findTestObject('Object Repository/05-PlazoFijo/Baja P.Fijo c Renovacion Automatica/lnkBajaP.FijocRenovacionAutomatica'))

//Seleccionar boton "Aceptar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Localizar elemento "Id Plazo Fijo"
TestObject idPlazoFijo = findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/lblIdPlazoFijo')

//Extraer el texto
String trx1 = WebUI.getText(idPlazoFijo)

//Cerrar Sesion
CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()

//Volver a Logearse con un nuevo usuario

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 41), findTestData('MainData/Users').getValue(2, 41))
WebUI.maximizeWindow()

//Setear "?302" en la linea de comando
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("?302", 1)

//Cambiar a la ventana "Temenos T24"
WebUI.switchToWindowIndex(1)

//Navegar por el menu Temenos T24
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable2, link2)

//Cambiar a la ventana "BCCL.E.AUTHORIZATION"
WebUI.switchToWindowIndex(2)

//Esperar 3 seg a que se cargue la tabla
WebUI.delay(3)

//Maximizar Ventana
WebUI.maximizeWindow()

def autorizarComboBoxEnTabla(String trx1) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {
		WebElement cell = row.findElements(By.tagName("td"))[0]
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

//Seleccionar boton "Autorizar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAutorizaRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verificar "TX Finalizada"
WebUI.verifyElementVisible(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/lblTxFinalizadaSinReplicacion'))

//Cambiar a la ventana del Dashboard
WebUI.switchToWindowIndex(0)

//Setear "MM.MONEY.MARKET" en la linea de comando
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("MM.MONEY.MARKET", 3)

//Setear transaccion en "MM.MONEY.MARKET"
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), trx1)

//Maximizar Ventana
WebUI.maximizeWindow()

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton "Ver Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

//Verificar "Customer Id"
WebUI.verifyElementVisible(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/lblCustomerId'))

//Validar "Customer Id"
def element = WebUI.getText(findTestObject('Object Repository/05-PlazoFijo/PLAZO FIJO/lblCustomerId'))
assert element.contains('Customer Id')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}