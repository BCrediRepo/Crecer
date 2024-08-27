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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()

//Ingresamos el menu correspondiente
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?327')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))
WebUI.switchToWindowTitle("Temenos T24")
WebUI.maximizeWindow()

def menuDesplegable = ["Transacciones Especiales"]
def link = "Pases entre Cajas - Jefe"

//si el menú que busco está en Temenos T24, uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Switch a la ventana TELLER
WebUI.switchToWindowIndex(2)
WebUI.maximizeWindow()

//Seteo de datos
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/btnDropdownDelaCaja'))
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/lblDelaCajaPrimeraCaja'))
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/btnDropdownAlaCaja'))
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/lblAlaCajaSegundaCaja'))
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/btnDropdownMoneda'))
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/lblARS'))
WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/03-TELLER/txtMontoMN'), '1000')
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
//WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

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



