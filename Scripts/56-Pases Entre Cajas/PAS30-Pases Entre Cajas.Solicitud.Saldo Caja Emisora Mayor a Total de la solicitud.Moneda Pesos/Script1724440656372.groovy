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


//Configuracion del ambiente y login
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,11), findTestData('MainData/Users').getValue(2,11))
WebUI.maximizeWindow()

def menuDesplegable0 = ["Operatoria de Caja- Reemplazo", "Pases" ]
def link0 = "Solicitud de Pase Entre Cajas"
 
//Si el menu que busco está en dashboard uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable0, link0)
 
//Switch a la ventana TELLER
WebUI.switchToWindowTitle('TELLER')
WebUI.maximizeWindow()
 
//Seteo de datos
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/btnDropdownDelaCaja'))
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/lblDelaCajaTerceraCaja'))
WebUI.waitForElementVisible(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/btnDropdownMoneda'), 3)
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/btnDropdownMoneda'))
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/lblARS'))
WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/txtMontoMN'), '1000')
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/txtComentarios'))
WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/txtComentarios'), 'PRUEBAS')
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert element.contains('Txn Completa')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()


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