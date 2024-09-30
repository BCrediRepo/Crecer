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

//TEST NAME: Alta de Nota de Crédito Transitoria. Filial de la cuenta igual a la del usuario. Cuenta admite saldo deudor. Se ingresa observación
//Configuro el ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando ACCOUNT, 00730029258
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("ACCOUNT, 00730029258", 1)

//Verifico que la cuenta admita SALDO DEUDOR
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/10-Nota de Credito Transitoria/rdbSaldoDEUDOR'), 6)
WebUI.click(findTestObject('Object Repository/23-Impuestos/10-Nota de Credito Transitoria/rdbSaldoDEUDOR'))
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.delay(3)
WebUI.switchToWindowIndex(0)

//Navegar por el menu de Ajustes Monetarios
def menuDesplegable = ["Ajustes Monetarios"]
def link = "Nota de Credito Transitoria"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowIndex(2)
WebUI.maximizeWindow()

//Completo los campos necesarios para la nota de Credito
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/10-Nota de Credito Transitoria/txtNroCuenta'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/10-Nota de Credito Transitoria/txtNroCuenta'), '00730029258')
WebUI.click(findTestObject('Object Repository/23-Impuestos/10-Nota de Credito Transitoria/txtImporte'))
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/10-Nota de Credito Transitoria/txtImporte'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/10-Nota de Credito Transitoria/txtImporte'), '100')
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/10-Nota de Credito Transitoria/txtConcepto'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/10-Nota de Credito Transitoria/txtConcepto'), '18699NTE')
WebUI.click(findTestObject('Object Repository/23-Impuestos/10-Nota de Credito Transitoria/txtImporte'))
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Acepto las Alertas y completo la transaccion
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
