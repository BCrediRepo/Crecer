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

//Configuracion del ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando menu ?1
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("?1", 1)

//Se accede al menu Automatizacion de Sucursales
menuDesplegable = ["Sucursal Piloto","GL - Entregas Global Logic","Transferencias a Otros Bancos","Transferencias Online"]
link = "Transferencias a Otros Bancos"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)
WebUI.switchToWindowIndex(2)

WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/txtConcepto'), 6)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/txtConcepto'), '3')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/txtIdOrdenante'), '1000873562')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/txtNrodeCuentaDebito'), '11190118359')
WebUI.click(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/txtImporte'))
WebUI.setText(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/txtImporte'), '100')
WebUI.click(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/dpdMotivo'))
WebUI.click(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/lblFAC'))
WebUI.setText(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/txtCBUCuentaaAcreditar'), '0720120820000000332936')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/txtTipoCUIBeneficiario'), 'L')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/txtNroCUIBeneficiario'), '30537620893')
WebUI.click(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/txtNombreBeneficiario'))
WebUI.setText(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/txtNombreBeneficiario'), 'EVASIO MARMETTO S A')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Valido que se haya completado la transaccion
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'), 6)
def element = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert element.contains('Txn Completa:')

//Switch a la ventana de Forzar Firmas
WebUI.switchToWindowIndex(3)
WebUI.maximizeWindow()

//Fuerzo las firmas y valido que se haya completado la firma
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/select_CANCELARFORZARVERIFICAR'), 6)
WebUI.selectOptionByIndex(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/select_CANCELARFORZARVERIFICAR'), 2)
WebUI.click(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/btnAceptarRegistroFirmas'))

WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/lblFINALIZADA'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/lblFINALIZADA'))
def element2 = WebUI.getText(findTestObject('Object Repository/23-Impuestos/13-Transferencias a Otros Bancos/lblFINALIZADA'))
assert element2.contains('FINALIZADA')

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
