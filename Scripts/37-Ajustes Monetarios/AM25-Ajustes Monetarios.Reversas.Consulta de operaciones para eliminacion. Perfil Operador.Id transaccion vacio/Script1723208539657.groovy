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
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.Keys

def buscarComboBoxEnTabla(String moneda) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {
		WebElement cell = row.findElements(By.tagName("td"))[1]
		String cellText = cell.getText()
		if (cellText.equals(moneda)) {
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			WebElement tdElement = tdList[8]
			WebElement comboBox = tdElement.findElement(By.tagName("select"))
			WebElement tdElement1 = tdList[9]
			WebElement btn = tdElement.findElement(By.tagName("img"))
			
			//Utilizar "Select" para interactuar con el comboBox
			def select = new Select(comboBox)
			//def boton = new Select(btn)
			select.selectByVisibleText("ELIMINAR TRANSACCION")
			List<WebElement> opciones = select.getOptions();
			
			for (WebElement opcion : opciones) {
				if (opcion.getText().equals("ELIMINAR TRANSACCION")) {
					//Seleccionar la opción "ELIMINAR TRANSACCION" si está disponible
					select.selectByVisibleText("ELIMINAR TRANSACCION")
					btn.click()
					
					return true; // Retorna true si se selecciona la opción
				}
			}
			// Si no encuentra la opcion Eliminar
			CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
					return false;
					
	return true
	
		}
	}
	
	return false
	//Si no se encuentra el ComboBox
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
	
}

def moneda = 'ARS'

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,7), findTestData('MainData/Users').getValue(2,7))
WebUI.maximizeWindow()

def menuDesplegable = ["Autorizaciones"]
def link = "Autorizaciones Pendientes"

//Navegar en el Dashboard
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Cambiar a la ventana "BCCL.E.AUTHORIZATION"
WebUI.switchToWindowIndex(1)

//Esperar 3 segundos a que cargue la tabla
WebUI.delay(3)

//Maximizar Ventana
WebUI.maximizeWindow()
			
def encontrado = false
while (!encontrado) {
	encontrado = buscarComboBoxEnTabla(moneda)
}

//Verificar y validar "boton eliminar"
assert WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/06-ToolBar/btnEliminarRegistro'))

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}