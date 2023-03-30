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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,14), findTestData('MainData/Users').getValue(2,14))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ir a operatoria de Caja
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkOperatoriadeCaja-Reemplazo'))

//Ir a Dispositivos
WebUI.click(findTestObject('Object Repository/02-Dashboard/9-OperatoriaDeCaja-Reemplazo/lnkDispositivos'))

//Ir a cierre de Dispositivos
WebUI.click(findTestObject('Object Repository/02-Dashboard/9-OperatoriaDeCaja-Reemplazo/01-Dispositivos/02-Cierre De Dispositivos/spanCierredeDispositivos'))

//Ir a Dispensado ATM/CD
WebUI.click(findTestObject('Object Repository/02-Dashboard/9-OperatoriaDeCaja-Reemplazo/01-Dispositivos/02-Cierre De Dispositivos/lnkDispensadodeATMCD'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Abre la ventana Dispensador de ATM/CD
WebUI.switchToWindowTitle('TELLER')

//Ingresa monto
WebUI.waitForElementVisible(findTestObject('Object Repository/26-Dispositivos/01-DispensadoDeATM-CD/txtMontoMN'), 6)
WebUI.setText(findTestObject('Object Repository/26-Dispositivos/01-DispensadoDeATM-CD/txtMontoMN'), "1000")

//Valida el registro
WebUI.click(findTestObject('Object Repository/26-Dispositivos/01-DispensadoDeATM-CD/btnValidarUnregistro'))

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Selecciona la pestaña Denominaciones CR y hace click en denominaciones 2
WebUI.waitForElementVisible(findTestObject('Object Repository/26-Dispositivos/01-DispensadoDeATM-CD/spanDenominacionesCR'),6)
WebUI.click(findTestObject('Object Repository/26-Dispositivos/01-DispensadoDeATM-CD/spanDenominacionesCR'))

//Setea la denominación
WebUI.waitForElementVisible(findTestObject('Object Repository/26-Dispositivos/01-DispensadoDeATM-CD/txtCantidadDenom2'),6)
WebUI.setText(findTestObject('Object Repository/26-Dispositivos/01-DispensadoDeATM-CD/txtCantidadDenom2'), "1")

//Aceptar el registro
WebUI.click(findTestObject('Object Repository/26-Dispositivos/01-DispensadoDeATM-CD/btnAceptarRegistro'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/26-Dispositivos/01-DispensadoDeATM-CD/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/26-Dispositivos/01-DispensadoDeATM-CD/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/26-Dispositivos/01-DispensadoDeATM-CD/lblTxnCompleta'))
assert element.contains('Txn Completa:')

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

