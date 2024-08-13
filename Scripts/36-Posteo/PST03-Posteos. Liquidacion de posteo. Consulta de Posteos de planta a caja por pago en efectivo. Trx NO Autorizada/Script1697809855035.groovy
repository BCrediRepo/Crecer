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
import java.text.SimpleDateFormat
import java.util.Date
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando ENQ BCCL.E.EB.POSTEO.INAU
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.EB.POSTEO.INAU')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña BCCL.E.EB.POSTEO.INAU
WebUI.switchToWindowTitle('BCCL.E.EB.POSTEO.INAU')

//Limpieza
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Verifica titulo BCCL.E.FIRMAS.FISICA
WebUI.verifyElementVisible(findTestObject('Object Repository/37-Posteo/BCCL.E.EB.POSTEO.INAU/lblTituloBCCL.E.EB.POSTEO.INAU'))

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Selecciona boton EJECUTAR
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Espera y Verifica que devuelva un registro
WebUI.waitForElementVisible(findTestObject('Object Repository/37-Posteo/BCCL.E.EB.POSTEO.INAU/lblIdTransaccion'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/37-Posteo/BCCL.E.EB.POSTEO.INAU/lblIdTransaccion'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

def element = WebUI.getText(findTestObject('Object Repository/37-Posteo/BCCL.E.EB.POSTEO.INAU/lblIdTransaccion'))
assert element.contains('Id Transaccion')

//---------------------------

//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))

TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))

println TotalRegistros
//-----------------------------

//Selecciona boton Liquidar de la primera tx
WebUI.click(findTestObject('Object Repository/37-Posteo/BCCL.E.EB.POSTEO.INAU/btnLiquidar'))

//cambio a pestaña Movimientos de Fondos
WebUI.switchToWindowIndex(1)

//Selecciono Audit
WebUI.click(findTestObject('Object Repository/37-Posteo/Movimiento de Fondos/btnAudit'))

//Valida el mensaje de las tx no autorizadas 
//Esta funcion busca en los resultados el contenido de: "Estado del registro"
//Siendo "INAO" el resultado esperado para las transacciones no autorizadas
//La posicion de ese label puede variar segun los ambientes
def variable = "Estado del Registro"
		
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("tab2"))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {		
		WebElement cell = row.findElements(By.tagName("td"))[0]		
		String cellText = cell.getText()
		println (cellText)
		if (cellText.equals(variable)) {			
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			WebElement tdElement = tdList[2]
			String texto = tdElement.getText()
			println (texto)
			assert texto == "INAO"
			break
			}
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

