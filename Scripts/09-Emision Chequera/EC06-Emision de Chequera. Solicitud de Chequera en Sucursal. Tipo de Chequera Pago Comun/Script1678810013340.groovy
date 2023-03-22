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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se accede al menu Administracion de piezas
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lnkChequeras'), 6)
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkChequeras'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/8-Emision Chequera/lnkEmisiondechequera'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/8-Emision Chequera/lnkEmisiondechequera'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/8-Emision Chequera/03-Emision de Chequera/lnkPedidodeChequera'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/8-Emision Chequera/03-Emision de Chequera/lnkPedidodeChequera'))

//Switch a la ventana de impresion de acta
WebUI.switchToWindowTitle('BCCL.CQ.SOLICITUD')
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/txtID.DECUENTA'), 6)
WebUI.setText(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/txtID.DECUENTA'), '00730029258')
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/txtTIPODECHEQUERA'), 6)
WebUI.setText(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/txtTIPODECHEQUERA'), 'LCCA')
//WebUI.selectOptionByIndex(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/txtTIPODECHEQUERA'), 5)
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/txtCANTIDADDECHEQUERAS'), 6)
WebUI.setText(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/txtCANTIDADDECHEQUERAS'), '1')
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/btnValidarRegistro'), 6)
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/btnValidarRegistro'))
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/btnAceptarRegistro'), 6)
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/btnAceptarRegistro'))

//Se aceptan las alertas
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/lnkAceptarAlertas'), 6)
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/lnkAceptarAlertas'))

//Verificamos que la transaccion se haya completado
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/lblTxnCompleta'))
assert element.contains('Txn Completa:')

//Switch a la ventana de impresion de acta
WebUI.switchToWindowTitle('Verificacion de Firmas - Fil.073 Jujuy')
WebUI.maximizeWindow()
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/select_CANCELARFORZARVERIFICAR'), 6)
WebUI.selectOptionByIndex(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/select_CANCELARFORZARVERIFICAR'), 2)
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/btnAceptarFirmas'), 6)
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/btnAceptarFirmas'))

WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/lblTxnOriginal'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/lblTxnOriginal'))
def elemento = WebUI.getText(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/lblTxnOriginal'))
assert elemento.contains('AUTORIZADA')

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

