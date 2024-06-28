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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Setear "?1" en el comandline
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscar'), '?1')

//Seleccionar boton buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana de "Temenos T24"
WebUI.switchToWindowIndex(1)

//Seleccionar "Sucursal Piloto"
WebUI.click(findTestObject('02-Dashboard/lnkSucursalPiloto'))

//Maximizar Ventana
WebUI.maximizeWindow()

//Seleccionar "D3 - CC1"
WebUI.click(findTestObject('02-Dashboard/05-SucursalPiloto/spanD3-CC1'))

//Seleccionar "CC1 - Cheques certificados y Financieros"
WebUI.click(findTestObject('02-Dashboard/05-SucursalPiloto/CC1/spanCC1-ChequesCertificadosyFinancieros'))

//Seleccionar "CHEQUES FINANCIEROS"
WebUI.click(findTestObject('02-Dashboard/05-SucursalPiloto/CC1/Cheques Certificados y Financieros/spanChequesFinancieros'))

//Seleccionar "AUTORIZACION DE BAJA DE CHEQUES FINANCIEROS"
WebUI.click(findTestObject('Object Repository/02-Dashboard/05-SucursalPiloto/CC1/Cheques Certificados y Financieros/CHEQUES FINANCIEROS/lnkAUTORIZACIONDEBAJADECHEQUESFINANCIEROS'))

//Cambiar a la ventana "BCCL.CQ.CF.AUT.BAJA"
WebUI.switchToWindowIndex(2)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cuenta Cheque', '10430033951')

//Maximizar Pantalla
WebUI.maximizeWindow()

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Ejecutar"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Seleccionar "Autorizar"
WebUI.click()

//Seleccionar "boton Autorizar Registro"
WebUI.click(findTestObject('Object Repository/37-Posteo/Movimiento de Fondos/btnAutorizarRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Validar "Txn Completa"
def element = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))
assert element.contains('Txn Completa')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}