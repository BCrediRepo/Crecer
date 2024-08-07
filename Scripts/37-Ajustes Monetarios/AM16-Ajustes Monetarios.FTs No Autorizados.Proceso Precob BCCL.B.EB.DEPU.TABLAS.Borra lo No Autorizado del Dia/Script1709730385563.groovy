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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 4), findTestData('MainData/Users').getValue(
        2, 4))

WebUI.maximizeWindow()

//Ejecuta en la linea de comando TSA.SERVICE, BNK/BCCL.B.EB.DEPU.TABLAS
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)

WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'TSA.SERVICE, BNK/BCCL.B.EB.DEPU.TABLAS')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch TSA.SERVICE
WebUI.switchToWindowTitle('TSA.SERVICE,')

//Selecciona boton estado Start
WebUI.selectOptionByIndex(findTestObject('Object Repository/38-Ajustes Monetarios/TSA.SERVICE,/cbxAUTODEBUGSTARTSTOP'), 3)

//Selecciona boton aceptar registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Espera y Verifica que se muestren las columnas del registro
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))

def txn = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))

assert txn.contains('Txn Completa:')

WebUI.switchToWindowIndex(0)

//Ejecuta en la linea de comando TSA.SERVICE, BNK/BCCL.B.EB.DEPU.TABLAS
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)

WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'TSA.SERVICE, BNK/BCCL.B.EB.DEPU.TABLAS')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch TSA.SERVICE
WebUI.switchToWindowTitle('TSA.SERVICE,')

//Selecciona boton estado Start
WebUI.selectOptionByIndex(findTestObject('Object Repository/38-Ajustes Monetarios/TSA.SERVICE,/cbxAUTODEBUGSTARTSTOP'), 
    4)

//Selecciona boton aceptar registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

assert txn.contains('Txn Completa:')

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

