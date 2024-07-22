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

def idOrdenante = '1000027871'
def concepto = '18301CMI'
def bonificacion = '99,99'
def observaciones = 'PRUEBA'

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,17), findTestData('MainData/Users').getValue(2,17))
WebUI.maximizeWindow()

//Desde el menu principal accedemos a la aplicacion de cobro de comisiones manuales en efectivo
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkComisiones'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/04-Comisiones/lnkCobro de Comisiones Manuales EN EFECTIVO'))
WebUI.switchToWindowTitle('Account Charge Request')
WebUI.maximizeWindow()

//Cargamos los datos de la comision segun lo requerido - moneda pesos, es socio (id ordenante), concepto permite bonificacion, bonificacion maxima
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btndropdown'))
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblPesos Argentinos'))
WebUI.selectOptionByIndex(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/cbxEsSocio'), 2)
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtId Ordenante'), idOrdenante)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'), concepto)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtBonificacion'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtBonificacion'), bonificacion)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'), observaciones)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnAceptar Registro'))
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lnkAceptar Alertas'))

//validamos que la operacion haya finalizado con exito y guardamos el numero de la misma
WebUI.waitForElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))
def TxnInicial = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))
assert TxnInicial.contains('Txn Completa:')
def parts = TxnInicial.tokenize(' ')
def transaccion = parts[2]

//Deslogueamos
WebUI.closeWindowTitle('Account Charge Request')
WebUI.switchToWindowIndex(0)
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnLogout'))

//Login con user NIV 5 para autorizar la transaccion anterior
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,39), findTestData('MainData/Users').getValue(2,39))
WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/02-Dashboard/06-Cheques rechazados/Cheques rechazados/Autorizacion/lnkAutorizaciones'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/06-Cheques rechazados/Cheques rechazados/Autorizacion/lnkAutorizacionesPendientes'))
WebUI.switchToWindowTitle('BCCL.E.AUTHORIZATION')

	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {
		WebElement cell = row.findElements(By.tagName("td"))[0]
		String cellText = cell.getText()
		
		if (cellText.equals(transaccion)) {
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			WebElement tdElement = tdList[10]
			WebElement lnkElement = tdElement.findElement(By.tagName("a"))
			lnkElement.click()	
			break
		}
		
	}

	//validaciones finales - Pago en EFECTIVO y bonificacion maxima (99,99%)
WebUI.switchToWindowTitle('Account Charge Request')
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTipoDePago'))
TipoPago = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTipoDePago'))
assert TipoPago == "EFECTIVO"
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblBonificacion'))
Bonificacion = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblBonificacion'))
assert Bonificacion == "99,99"



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


