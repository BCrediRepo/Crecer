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

def validacionTxt (String campo) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("selectiondisplay"))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {
		WebElement cell = row.findElements(By.tagName("td"))[0]
		String cellText = cell.getText()
		if (cellText.equals(campo)) {
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			WebElement tdElement = tdList[2]
			WebElement txtElement = tdElement.findElement(By.tagName("input"))
			txtElement.isEnabled().TRUE
			return true
		}
	}
	return false
}

//Configuracion del ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,19), findTestData('MainData/Users').getValue(2,19))
WebUI.maximizeWindow()

def menuDesplegable0 = ["Cuentas", "Modificacion de cuenta", "Bloqueo y Desbloqueo", "Bloqueo" ]
def link0 = "Seleccionando Cuenta"

//Si el menu que busco está en dashboard uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable0, link0)

//Cambiar ventana "BCCL.E.AC.BLO.POR.CTA"
WebUI.switchToWindowTitle('BCCL.E.AC.BLO.POR.CTA')

//Seteo de Datos "Cuenta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))


//definimos una bandera que inicie en falso. Los while van a cortar cuando la bandera se vuelva true.
//el assert valida que la bandera este en true y luego se la reinicia para el siguiente while
//de esta manera se pueden validar los txt. Si encuentran una forma mejor, aplicarlo.
def flag = false
String variable
while (!flag) {
	variable = "Id Firmante"
	flag = validacionTxt(variable)
}
assert flag == true
//-------
flag = false
while (!flag) {
	variable = "Nro Documento"
	flag = validacionTxt(variable)
}
assert flag == true
//-------
while (!flag) {
	variable = "Nro CUI"
	flag = validacionTxt(variable)
}
assert flag == true

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}