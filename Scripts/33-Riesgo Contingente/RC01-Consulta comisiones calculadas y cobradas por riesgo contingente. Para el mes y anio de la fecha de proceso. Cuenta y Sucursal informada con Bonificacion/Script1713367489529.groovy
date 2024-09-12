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


def validarElementoEnTabla(String tabla, String variable, int colVariable, String razon, int colRazon) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id(tabla))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {
		WebElement cell = row.findElements(By.tagName("td"))[colVariable]
		String cellText = cell.getText()
		if (cellText.equals(variable)) {
			List<WebElement> tdList = row.findElements(By.tagName("td"))
					String resultado = tdList[colRazon].getText()
					println(resultado)
			assert tdList[colRazon].getText().contains(razon) : "Expected " + razon + " but found ${tdList[colRazon].getText()}"
			GlobalVariable.vTxn = resultado
			return true
		}
	}
	return false
}

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'('ENQ BCCL.E.AC.COM.COBRADA', 1)
//Maximiza la pestaña
WebUI.maximizeWindow()

//Seteo de datos "Cuenta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cuenta', '00890010860')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Presiona botón ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verifica que se muestre el titulo Tipo de comision
WebUI.verifyElementVisible(findTestObject('Object Repository/34-Riesgo Contingente/BCCL.E.AC.COM.COBRADA/lblTipo de Comision'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()
long elapsedTime = endTime - startTime
println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

def encontrado = false
while(!encontrado) {
	encontrado = validarElementoEnTabla('enquiryHeaderContainer', 'Cuenta :', 0, '00890010860', 1)
	result = GlobalVariable.vTxn
	assert result == '00890010860'
}

assert WebUI.getText(findTestObject('Object Repository/34-Riesgo Contingente/BCCL.E.AC.COM.COBRADA/lblTipo de Comision')) != null

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


