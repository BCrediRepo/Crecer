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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()

//Ejecutar en la linea de comando "ENQ BCCL.E.NOF.PREDIC.ATM.OPTICASH"
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("ENQ BCCL.E.NOF.PREDIC.ATM.OPTICASH", 1)

//Seteo de Datos "Sucursal"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('SUCURSAL', '043')
//CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('SUCURSAL', '115') // Suc con datos en r21
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

WebElement table = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
WebElement header = table.findElement(By.tagName("tr"))
List<WebElement> cells = header.findElements(By.tagName("td"))
 
// Validar los textos de las celdas directamente
assert cells[0].getText().contains('Dispositivo') : "Expected 'Dispositivo' but found ${cells[0].getText()}"
assert cells[3].getText().contains('Sucursal') : "Expected 'Sucursal' but found ${cells[3].getText()}"
assert cells[6].getText().contains('Carga/Descarga') : "Expected 'Carga/Descarga' but found ${cells[6].getText()}"
assert cells[9].getText().contains('Denominacion') : "Expected 'Denominacion' but found ${cells[9].getText()}"
assert cells[12].getText().contains('Monto') : "Expected 'Monto' but found ${cells[12].getText()}"
assert cells[15].getText().contains('ID') : "Expected 'ID' but found ${cells[15].getText()}"

WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lnkVer'))

//Valida un elemento en la tabla de resultados --> valida el contenido de "variable" que exista en la tabla resultante
def variable = "Fecha Proceso"
def validarElementoEnTabla(String variable) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("tab1"))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {
		WebElement cell = row.findElements(By.tagName("td"))[0]
		String cellText = cell.getText()
		if (cellText.equals(variable)) {
			return true
		}
	}
	return false
}

def encontrado = false
while (!encontrado) {
	encontrado = validarElementoEnTabla(variable)
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
