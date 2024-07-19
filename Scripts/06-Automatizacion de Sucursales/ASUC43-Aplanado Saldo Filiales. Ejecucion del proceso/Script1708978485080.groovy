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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,2), findTestData('MainData/Users').getValue(2,2))

WebUI.maximizeWindow()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'TSA.SERVICE,')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Abre la pestaña Movimiento de Fondos
WebUI.switchToWindowIndex(1)

//Maximiza la pantalla
WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/40-Tarjeta de Deposito/TSA.SERVICE/txtTSA.SERVICEId'), 'BNK/BCCL.B.AC.SALDOS.FILIAL')

//Selecciona Modificar un Registro
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/05-TSA.SERVICE,/btnModificarRegistro'))

WebUI.waitForElementVisible(findTestObject('Object Repository/25-Cierre de Cuenta/05-TSA.SERVICE,/select_ServiceControl'), 6)
WebUI.selectOptionByIndex(findTestObject('Object Repository/25-Cierre de Cuenta/05-TSA.SERVICE,/select_ServiceControl'), 3)

WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/btnAceptarRegistro'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/lblTxn Completa'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/lblTxn Completa'))
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/lblTxn Completa'))
assert element.contains('Txn Completa:')

//Toma un Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.setText(findTestObject('Object Repository/40-Tarjeta de Deposito/TSA.SERVICE/txtTSA.SERVICEId'), 'BNK/BCCL.B.AC.SALDOS.FILIAL')

//Selecciona Modificar un Registro
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/05-TSA.SERVICE,/btnModificarRegistro'))

WebUI.waitForElementVisible(findTestObject('Object Repository/25-Cierre de Cuenta/05-TSA.SERVICE,/select_ServiceControl'), 6)
WebUI.selectOptionByIndex(findTestObject('Object Repository/25-Cierre de Cuenta/05-TSA.SERVICE,/select_ServiceControl'), 4)

WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/btnAceptarRegistro'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/lblTxn Completa'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/lblTxn Completa'))
def element2 = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/lblTxn Completa'))
assert element2.contains('Txn Completa:')

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
