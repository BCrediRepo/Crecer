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

//Funcion que setea la cantidad de denominaciones pasadas por parametro en 'tab2' con la denominacion 'CIEN PESOS'
def SetTabla(String tabla, String variable, int posVariable, int posInput, String cant) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id(tabla))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (int i = 0; i < rows.size(); i++) {
		WebElement row = rows[i]
		WebElement cell = row.findElements(By.tagName("td"))[posVariable]
		String cellText = cell.getText()
		if (cellText.equals(variable)) {
			// Incrementamos la fila (i + 1)
			if (i + 1 < rows.size()) {
				WebElement nextRow = rows[i + 1]
				List<WebElement> tdList = nextRow.findElements(By.tagName("td"))
				WebElement tdElement = tdList[posInput]
				WebElement lnkElement = tdElement.findElement(By.tagName("input"))
				lnkElement.sendKeys(cant)
				return true
			} else {
				println("No hay una fila siguiente.")
				return false
			}
		}
	}
	return false
}

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,7), findTestData('MainData/Users').getValue(2, 7))
WebUI.maximizeWindow()

//Se accede al menu Cajas
def menuDesplegable = ["Transacciones Especiales"]
def link = "Ajustes de Denominacion del Tesoro"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowIndex(1)
WebUI.maximizeWindow()

//Ingresamos el monto
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/txtMontoMN'), '100')

//Ingresamos el comentario
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/txtComentarios'))
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/txtComentarios'), 'Prueba de cambio de denominacion')

//Click en Denominaciones DB
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/spanDenominaciones DB'))

//Funcion que setea la cantidad de denominaciones pasadas por parametro en 'tab2' con la denominacion 'CIEN PESOS'
def encontrado = false
while (!encontrado) {
	encontrado = SetTabla('tab2','CIEN PESOS', 4, 3, '1')
}

//Click en Denominaciones CR
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/TELLER/spanDenominaciones CR'))

//Funcion que setea la cantidad de denominaciones pasadas por parametro en 'tab2' con la denominacion 'CIEN PESOS'
encontrado = false
while (!encontrado) {
	encontrado = SetTabla('tab3','CIEN PESOS', 4, 3, '1')
}

//Click en aceptar registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Click en aceptar alertas
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert element.contains('Txn Completa')

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
