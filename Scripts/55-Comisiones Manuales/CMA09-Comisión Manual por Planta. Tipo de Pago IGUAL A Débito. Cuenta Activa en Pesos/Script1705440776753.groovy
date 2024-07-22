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
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.support.ui.Select
import java.awt.Robot
import java.awt.event.KeyEvent

//Definimos los datos a utilizar
def cuenta = '10700010299'
def  concepto = '18301CMI'

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Por medio del dashboard accedemos al aplicativo de consulta de cuentas por cuenta
//En esa ventana vamos a poder validar que la cuenta este activa y sea una cuenta en pesos (condiciones iniciales del caso)
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkConsultasdeCuentas'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/04-Consulta de cuentas/lnkConsultaDeCuentasPorCuenta'))
WebUI.switchToWindowTitle("Consulta de Cuentas por Cuenta")
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro. Cuenta', cuenta)
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Recorremos la tabla consultada buscando el numero de cuenta que ingresamos para la busqueda
WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
List<WebElement> rows = table.findElements(By.tagName("tr"))
for (WebElement row : rows) {		
	WebElement cell = row.findElements(By.tagName("td"))[1]
	String cellText = cell.getText()	
	if (cellText.equals(cuenta)) {				
		List<WebElement> tdList = row.findElements(By.tagName("td"))		
		//Aqui estamos validando que la cuenta este activa y en pesos
		assert tdList[4].getText().contains('ARS') : "Expected 'ARS' but found ${tdList[4].getText()}"
		assert tdList[5].getText().contains('Activa') : "Expected 'Activa' but found ${tdList[5].getText()}"
		return true
	}
}
//cerramnos la ventana una vez terminada la verificacion y nos dirigimos al dashboard de nuevo
//ahora si, para hacer la comision manual requerida en el caso
WebUI.closeWindowIndex(1)
WebUI.switchToWindowIndex(0)
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkComisionesyBonificaciones'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkComisiones'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/04-Comisiones/lnkCobroComisiones Manuales - Planta'))
WebUI.switchToWindowTitle('Account Charge Request')
WebUI.maximizeWindow()

//cargamos los datos de la comision. Pago en debito, cuenta debito activa en pesos, comision por cerificacion de firmas
//sin bonificacion 
WebUI.selectOptionByIndex(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/cbxTipoPago'), 1)
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCuentaDebito'), cuenta)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'), concepto)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'), 'COBRO DE FIRMAS')

//validamos la carga
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnAceptar Registro'))

//verificamos que la transaccion haya sido completada con exito
WebUI.waitForElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))
def Txn = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))
assert Txn.contains('Txn Completa')

//nos guardamos el numero de transaccion para ver en detalles de que se haya cargado correctamente, validando por ultimo
//que el tipo de pago sea debito
def parts = Txn.tokenize(' ')
def transaccion = parts[2]
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtComisiones Manuales-Caja'), transaccion)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnVerRegistro'))
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTipoDePago'))
TipoPago = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTipoDePago'))
assert TipoPago.contains('DEBITO')



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


