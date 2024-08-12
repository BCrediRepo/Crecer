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

def id = '1002132489'

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(2, 1))
WebUI.maximizeWindow()

//ingreso por dashboard
def menuDesplegable0 = ["Cuentas", "Cierre de cuenta"]
def link0 = "Cierre de Cuenta CON Cancelacion de Saldo"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable0, link0)

WebUI.switchToWindowIndex(1)

//Seteo de Datos "Persona"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Persona',id)
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Valido contenido de lnk cierre en efectivo
WebUI.verifyElementVisible(findTestObject('Object Repository/25-Cierre de Cuenta/BCCL.AC.CIERRE.CTA.CANC.SALDO/lnkCierre en Efectivo'))
lnk = WebUI.getText(findTestObject('Object Repository/25-Cierre de Cuenta/BCCL.AC.CIERRE.CTA.CANC.SALDO/lnkCierre en Efectivo'))
assert lnk == "Cierre en Efectivo"

//Valido contenido de lnk cierre con deb - cred
WebUI.verifyElementVisible(findTestObject('Object Repository/25-Cierre de Cuenta/BCCL.AC.CIERRE.CTA.CANC.SALDO/lnkCierreConDebCred'))
lnk = WebUI.getText(findTestObject('Object Repository/25-Cierre de Cuenta/BCCL.AC.CIERRE.CTA.CANC.SALDO/lnkCierreConDebCred'))
assert lnk == "Cierre con Deb/Cred Cta Cliente"

//Valido que la Persona ingresada tenga cuentas en diferentes sucursales
def buscarElementoEnTabla() {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	WebElement row1 = rows[0]
	WebElement cell1 = row1.findElements(By.tagName("td"))[0]
	String suc1 = cell1.getText()
	// Extraer los tres caracteres iniciales excepto el primero
	def extChars = suc1.length() > 1 ? suc1.substring(1, 4) : ""
	println("Los tres caracteres extraídos son: " + extChars)
	for (WebElement row : rows) {
		WebElement cell = row.findElements(By.tagName("td"))[0]
		String cellText = cell.getText()
		// Extraer los tres caracteres iniciales excepto el primero
		def extChars2 = cellText.length() > 1 ? cellText.substring(1, 4) : ""
		println("COMPARACION: Los tres caracteres extraídos son: " + extChars2)
		if (!extChars2.equals(extChars)) {
			return true
		}
	}
	return false
}

//Valido si se encontro persona con distintas cuentas, de lo contrario caso falla
encontrado = buscarElementoEnTabla()
assert encontrado
 

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}