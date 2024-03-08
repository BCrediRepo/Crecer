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
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
@Grab('org.jsoup:jsoup:1.14.3')
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 48), findTestData('MainData/Users').getValue(
        2, 48))

WebUI.maximizeWindow()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?1')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('02-Dashboard/lnkSucursalPiloto'))

WebUI.click(findTestObject('02-Dashboard/05-SucursalPiloto/lnkD2-Posteo'))

WebUI.click(findTestObject('02-Dashboard/05-SucursalPiloto/D2 - Posteos/lnkPosteo'))

WebUI.click(findTestObject('02-Dashboard/05-SucursalPiloto/D2 - Posteos/Posteo/lnkCobroEnEfectivo'))

WebUI.switchToWindowTitle('Movimiento de Fondos')

WebUI.click(findTestObject('Object Repository/37-Posteo/Movimiento de Fondos/btnDesplegarMoneda'))

WebUI.setText(findTestObject('Object Repository/37-Posteo/Movimiento de Fondos/txtMoneda'),'USD')

WebUI.click(findTestObject('Object Repository/37-Posteo/Movimiento de Fondos/btnValidarRegistro'))

WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/btnDesplegarConcepto'))

WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/lblConcepto2'))

WebUI.setText(findTestObject('37-Posteo/Movimiento de Fondos/txtNombrePosteo'), 'Pruebas Crecer')

WebUI.setText(findTestObject('37-Posteo/Movimiento de Fondos/txtImporte'), '5')

WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/btnAceptarRegistro'))

WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/lnkAceptarAlertas'))

Transaccion1 = WebUI.getText(findTestObject('37-Posteo/Movimiento de Fondos/lblTxnCompleta'))

// Dividir la cadena por espacios en blanco y tomar el segundo elemento
def partes = Transaccion1.split('\\s+')

def trx1 = partes[2]

assert Transaccion1.contains('Txn Completa:')

//WebUI.switchToWindowTitle('e-forms')

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.switchToWindowIndex('0')

WebUI.click(findTestObject('02-Dashboard/btnLogout'))

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 49), findTestData('MainData/Users').getValue(
        2, 49))

WebUI.click(findTestObject('02-Dashboard/lnkPosteo'))

WebUI.click(findTestObject('02-Dashboard/35-Posteos/lnkTransaccionesPendientesdeLiquidacion'))

WebUI.switchToWindowTitle('BCCL.E.EB.POSTEO.INAU')

// Obtén el elemento de la tabla
WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
 
// Obtén todas las filas dentro de la tabla
List<WebElement> rows = table.findElements(By.tagName("tr"))
 
// Valor específico que estás buscando
String targetValue = trx1
 
// Variable para rastrear si se encontró el valor específico
boolean foundTargetValue = false
 
// Itera a través de las filas
for (WebElement row : rows) {
    // Obtiene el tercer valor de la fila (índice 2, ya que las listas son base cero)
    WebElement cell = row.findElements(By.tagName("td"))[0]
 
    // Obtiene el texto de la celda
    String cellText = cell.getText()
 
    // Compara el valor de la celda con el valor específico
    if (cellText.equals(targetValue)) {
        foundTargetValue = true
			
		// Obtiene la lista de elementos td
		List<WebElement> tdList = row.findElements(By.tagName("td"))
		
		// Accede al elemento td en la posición 8
		WebElement tdElement = tdList[8]
 
		// Intenta encontrar el elemento 'a' dentro del elemento td
		WebElement liquidar = tdElement.findElement(By.tagName("a"))
 
		// Haz clic en el enlace
		liquidar.click()		
		
        break
		
    }
}


WebUI.switchToWindowTitle('Movimiento de Fondos')

WebUI.click(findTestObject('37-Posteo/Movimiento de Fondos/btnAutorizar'))

WebUI.switchToWindowIndex(2)

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.switchToWindowTitle('Movimiento de Fondos')

Transaccion2 = WebUI.getText(findTestObject('37-Posteo/Movimiento de Fondos/lblTxnCompleta'))

assert Transaccion2.contains('Txn Completa:') == true


//FIN DE SCRIPT----------------------------------------------------
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

