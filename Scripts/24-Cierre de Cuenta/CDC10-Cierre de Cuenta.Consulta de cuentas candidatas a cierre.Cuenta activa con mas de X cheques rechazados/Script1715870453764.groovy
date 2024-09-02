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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(2, 1))
WebUI.maximizeWindow()

//Ingresar "ENQ BCCL.E.CANDT.CIERRE" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CANDT.CIERRE')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton de buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "BCCL.E.CANDT.CIERRE"
WebUI.switchToWindowTitle('BCCL.E.CANDT.CIERRE')

//Maximizar Ventana
WebUI.maximizeWindow()

//Seteo de Datos "Sucursal", "Motivo"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('SUCURSAL','001')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('MOTIVO','07')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Obtiene datos de la tabla
WebElement table = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
WebElement header = table.findElement(By.tagName("tr"))
List<WebElement> cells = header.findElements(By.tagName("th"))
 
// Validar los textos de las celdas directamente
assert cells[0].getText().contains('FECHA DE CIERRE') : "Expected 'FECHA DE CIERRE' but found ${cells[0].getText()}"
assert cells[3].getText().contains('ID CUENTA') : "Expected 'ID CUENTA' but found ${cells[3].getText()}"
assert cells[6].getText().contains('NOMBRE CTA') : "Expected 'NOMBRE CTA' but found ${cells[6].getText()}"
assert cells[9].getText().contains('MOTIVO') : "Expected 'MOTIVO' but found ${cells[9].getText()}"
assert cells[12].getText().contains('NRO DE PRORROGA') : "Expected 'NRO DE PRORROGA' but found ${cells[12].getText()}"
assert cells[15].getText().contains('CTA ORIG RECH') : "Expected 'CTA ORIG RECH' but found ${cells[15].getText()}"
assert cells[18].getText().contains('ID FIRMANTE') : "Expected 'ID FIRMANTE' but found ${cells[18].getText()}"
assert cells[21].getText().contains('NOMBRE FIRMANTE') : "Expected 'NOMBRE FIRMANTE' but found ${cells[21].getText()}"
assert cells[24].getText().contains('PLAZO EXT.') : "Expected 'PLAZO EXT.' but found ${cells[24].getText()}"
assert cells[27].getText().contains('PRE-AVISO') : "Expected 'PRE-AVISO' but found ${cells[27].getText()}"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}
