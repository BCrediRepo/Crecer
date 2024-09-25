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

def menuDesplegable = ["Chequeras", "Emision de chequera"]
def link = "Pedido de Chequera"

CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Switch a la ventana de impresion de acta
WebUI.switchToWindowTitle('BCCL.CQ.SOLICITUD')

//Ingreso datos
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/txtID.DECUENTA'), 6)
WebUI.setText(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/txtID.DECUENTA'), '00730029258')
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/txtTIPODECHEQUERA'), 6)
WebUI.setText(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/txtTIPODECHEQUERA'), 'LCCA')
//WebUI.selectOptionByIndex(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/txtTIPODECHEQUERA'), 5)
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/txtCANTIDADDECHEQUERAS'), 6)
WebUI.setText(findTestObject('Object Repository/11-Emision Chequera/06-Pedido de Chequera/txtCANTIDADDECHEQUERAS'), '1')

//Valido y acepto registro
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'), 6)
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Se aceptan las alertas
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'), 6)
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Verificamos que la transaccion se haya completado
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert element.contains('Txn Completa:')

//Forzamos la firma de la tx realizada
WebUI.switchToWindowIndex(2)
WebUI.maximizeWindow()
WebUI.selectOptionByIndex(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/cbxAccion'),2)

//Acepta el registro de la firma
WebUI.click(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/btnAceptar'))

//Espera y recibe Estado FINALIZADA
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblFinalizada'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblFinalizada'))
def estado1 = WebUI.getText(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblFinalizada'))
assert estado1.contains('FINALIZADA')

//Espera y recibe Estado AUTORIZADA
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblAutorizada'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblAutorizada'))
def estado2 = WebUI.getText(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblAutorizada'))
assert estado2.contains('AUTORIZADA')

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}
@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}