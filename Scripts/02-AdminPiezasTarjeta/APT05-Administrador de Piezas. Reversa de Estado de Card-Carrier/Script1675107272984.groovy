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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))
WebUI.maximizeWindow()

//Se accede al menu Administracion de piezas
def menuDesplegable = ["Administracion de Piezas con Tarjetas","Reversa de Estado de Card-Carrier"]
def link = "Reversa de Estado del Card-Carrier"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Switch a la ventana de Reversa
WebUI.switchToWindowTitle('BCCL.AP.PIEZAS')
WebUI.waitForElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/05-Reversa de Estado de Card-Carrier/btnLista'), 6)
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/05-Reversa de Estado de Card-Carrier/btnLista'))

//Guardo el numero de transaccion a reversar
WebUI.waitForElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/05-Reversa de Estado de Card-Carrier/lblTxndeLista'), 6)
numTxn = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/05-Reversa de Estado de Card-Carrier/lblTxndeLista'))
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/05-Reversa de Estado de Card-Carrier/lblTxndeLista'))
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnHerramienta'))
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/06-ToolBar/btnReversar'), 6)
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnReversar'))
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'), 6)
WebUI.verifyElementPresent(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'), 6)

//Verifico que el estado de la pieza haya cambiado (REVERSADO)
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), 6)
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), numTxn)
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))
WebUI.waitForElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/05-Reversa de Estado de Card-Carrier/lblEstadoReve'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/05-Reversa de Estado de Card-Carrier/lblEstadoReve'))
estadoReve = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/05-Reversa de Estado de Card-Carrier/lblEstadoReve'))
assert estadoReve.contains("099")

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