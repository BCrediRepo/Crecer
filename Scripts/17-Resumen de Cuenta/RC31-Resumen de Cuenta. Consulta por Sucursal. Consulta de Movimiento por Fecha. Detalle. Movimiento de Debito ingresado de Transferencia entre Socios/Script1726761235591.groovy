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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,57), findTestData('MainData/Users').getValue(2,57))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ejecutar en la linea de comando "ENQ BCCL.E.RES.CTA.MOV.FECHA"
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("ENQ BCCL.E.RES.CTA.MOV.FECHA", 1)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro de Cuenta', '00740000115')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Codigo Operativo: (Separar los valores con un espacio)', '00050')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Desde', GlobalVariable.vFechaCOB)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Hasta', GlobalVariable.vFechaCOB)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Esperar 3 seg a que cargue la tabla
WebUI.delay(3)

WebElement table = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
WebElement header = table.findElement(By.tagName("tr"))
List<WebElement> cells = header.findElements(By.tagName("th"))

//Verificar si aparece Nro. de cuenta en el encabezado
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/06-Movimientos por fecha de cuentas/Movimientos por Fecha de Cuentas/lblNro.deCuenta'))

//Validar Nro. de cuenta
def nroCuenta = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/06-Movimientos por fecha de cuentas/Movimientos por Fecha de Cuentas/lblNro.deCuenta'))
assert nroCuenta.contains('Nro. de Cuenta')

//Validar que haya al menos 9 celdas
assert cells.size() >= 9 : "Expected at least 8 cells but found ${cells.size()}"

//Validar titulos de las columnas
assert cells[0].getText().contains('Fecha') : "Expected 'Numero de Acuerdo' but found ${cells[0].getText()}"
assert cells[3].getText().contains('ID Transaccion') : "Expected 'Cuenta' but found ${cells[3].getText()}"
assert cells[6].getText().contains('Codigo') : "Expected 'Denominacion' but found ${cells[6].getText()}"
assert cells[9].getText().contains('Descripcion') : "Expected 'Estado' but found ${cells[9].getText()}"
assert cells[12].getText().contains('Monto Debito') : "Expected 'Fec. Inicio' but found ${cells[12].getText()}"
assert cells[15].getText().contains('Monto Credito') : "Expected 'Fec. Vencimiento' but found ${cells[15].getText()}"
assert cells[18].getText().contains('Saldo') : "Expected 'Numero de Acuerdo' but found ${cells[18].getText()}"
assert cells[21].getText().contains('Fec Valor') : "Expected 'Cuenta' but found ${cells[21].getText()}"
assert cells[24].getText().contains('Combte') : "Expected 'Denominacion' but found ${cells[24].getText()}"

//Definir la variable para la palabra Transfer.
def encontrado = false
while (!encontrado) {
	encontrado = validarElementoEnTabla('datadisplay', '00050', 2, 'Transfer. e/Cuentas de Distinto Titular', 3)
	String resultadoFinal = GlobalVariable.vTxn
	assert resultadoFinal.contains('Transfer. e/Cuentas de Distinto Titular')
}

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}