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

//TEST CASE NAME: Devolución manual de Impuestos en efectivo. Socio. Usuario de Filial. Impuestos.  SELLOS otras Operatorias. 
//Alcanzado en Córdoba. 04. Condición para el impuesto en la jurisdicción es AA

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()

//Navegar por el menu Temenos T24
def menuDesplegable = ["Impuestos", "Operatorias especiales", "Cobro Sellado (Suc Santa Fe y Cordoba)"]
def link = "Devol. Sellado en Efec (Suc.Santa Fe/Cordoba)"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowIndex(1)
WebUI.maximizeWindow()

WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/03-Devol. Sellado en Efec (Suc.Santa Fe-Cordoba)/txtCUITCUIL'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/03-Devol. Sellado en Efec (Suc.Santa Fe-Cordoba)/txtCUITCUIL'), '30708114584')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/03-Devol. Sellado en Efec (Suc.Santa Fe-Cordoba)/txtNombrePersona'), 'TEST CRECER')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/03-Devol. Sellado en Efec (Suc.Santa Fe-Cordoba)/txtProv.Jurisdiccion'), '10')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/03-Devol. Sellado en Efec (Suc.Santa Fe-Cordoba)/txtBaseImponible'), '1,00')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/03-Devol. Sellado en Efec (Suc.Santa Fe-Cordoba)/txtId.Alicuota'), 'SO04AA.20170426')
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'), 6)
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'), 6)
def element = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
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
