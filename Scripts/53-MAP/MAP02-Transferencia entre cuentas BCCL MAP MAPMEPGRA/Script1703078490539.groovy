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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 8), findTestData('MainData/Users').getValue(2, 8))

CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'('FUNDS.TRANSFER,BCCL.TRANS.FOND.MAP I F3', 1)

//Maximizamos
WebUI.maximizeWindow()

//Click en el dropdown
WebUI.click(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/btnDropdown'))

//Click en la opcion correspondiente
WebUI.click(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/lblMAPMEPGRA-Transferencia MEP Alcanzada'))

CustomKeywords.'pkgModules.kywManejoDeTablas.rellenarFormulario'('tab1', 'Nro. de Cuenta Debito', 0, '01020311343', 2)
CustomKeywords.'pkgModules.kywManejoDeTablas.rellenarFormulario'('tab1', 'Nro. de Cuenta Credito', 0, '13490019758', 2)
CustomKeywords.'pkgModules.kywManejoDeTablas.rellenarFormulario'('tab1', 'Importe', 0, '10', 2)
CustomKeywords.'pkgModules.kywManejoDeTablas.rellenarFormulario'('tab1', 'Observaciones', 0, 'Pruebas123', 2)

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