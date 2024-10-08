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

////Configuracion de ambiente
//CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
//
////Login
//CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 43), findTestData('MainData/Users').getValue(2, 43))
//
//WebUI.maximizeWindow()
//CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
//
////Consultamos el Saldo Previo de la cuenta
//menuDesplegable = ['Cuentas', 'Consultas de Cuentas']
//link = 'Consulta de Saldo al Dia'
//CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
//
//
//WebUI.switchToWindowTitle('Saldos de Cuenta')
//WebUI.maximizeWindow()
//
//WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkNuevaSeleccion'))
//CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cuenta', '01020311343')
//WebUI.delay(5)
//
//
////Click en ejecutar
//WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))
//
////ASSERT
//WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblNro.deCuenta'), 6)
//WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblNro.deCuenta'))
//
//def element = WebUI.getText(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblNro.deCuenta'))
//
//assert element.contains('Nro. de Cuenta')

//WebUI.closeBrowser()

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 8), findTestData('MainData/Users').getValue(2, 8))

CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'('FUNDS.TRANSFER,BCCL.TRANS.FOND.MAP I F3', 1)

WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/btnDropdown'))
WebUI.click(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/lblMAPMEPEXE-Transferencia MEP Exenta'))

def encontrado = false
while(!encontrado) {
	encontrado = CustomKeywords.'pkgModules.kywManejoDeTablas.rellenarFormulario'('tab1', 'Nro. de Cuenta Debito', 0, '01020311343', 2)
	encontrado = CustomKeywords.'pkgModules.kywManejoDeTablas.rellenarFormulario'('tab1', 'Nro. de Cuenta Credito', 0, '13490019758', 2)
	encontrado = CustomKeywords.'pkgModules.kywManejoDeTablas.rellenarFormulario'('tab1', 'Importe', 0, '10', 2)
	encontrado = CustomKeywords.'pkgModules.kywManejoDeTablas.rellenarFormulario'('tab1', 'Observaciones', 0, 'Pruebas123', 2)
}

//Click en Aceptar Registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
def TxnInicial = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert TxnInicial.contains('Txn Completa')
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