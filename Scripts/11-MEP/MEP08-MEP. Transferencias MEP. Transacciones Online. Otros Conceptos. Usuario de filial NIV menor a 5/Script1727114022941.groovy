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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 51), findTestData('MainData/Users').getValue(2, 51))
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'('?328', 1)

def menuDesplegable = ["Transferencias Mep"]
def link = "Transferencias Mep otros Conceptos"

//Navegar por el menu del Dashboard
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Cambiar a la ventana "BCCL.MEP.FT.TRANSFER"
WebUI.switchToWindowIndex(2)

//Seteo de datos
WebUI.selectOptionByIndex(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/cbxTipoDeCuenta'), 1)

//Maximizar ventana
WebUI.maximizeWindow()

WebUI.click(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/txtImporte'))

WebUI.waitForElementVisible(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/txtIDOrdenante'), 3)

WebUI.setText(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/txtIDOrdenante'), '1000873562')

WebUI.click(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/btnDrillDown'))

WebUI.click(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/lblCuentaARS'))

WebUI.setText(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/txtImporte'), '100')

WebUI.setText(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/txtEntidadAcreedora'), 'CRECER')

WebUI.click(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/rbtnNO - Copy'))

WebUI.selectOptionByIndex(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/cbxConoceAsociado'), 2)

WebUI.setText(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/txtCUILCUITBeneficiario'), '23592335034')

WebUI.setText(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/txtNombreBeneficiario'), 'RAGNAR COVELL')

WebUI.setText(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/txtObservaciones'), 'CRECER')

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Validacion y aceptacion de registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))

WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

WebUI.delay(4)

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Definir Objeto
Transaccion = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion.split('\\s+')
def trx1 = partes[2]
GlobalVariable.vTxn = trx1

//Primer control assert
label = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))

assert label.contains('Txn Completa:') == true

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Maximizar ventana
WebUI.maximizeWindow()

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verificacion de Firmas
WebUI.switchToWindowIndex(3)

WebUI.selectOptionByIndex(findTestObject('13-MEP/Verificacion de Firmas - Fil.074 Caseros Centro/cbxAccion'), 2)

WebUI.click(findTestObject('13-MEP/Verificacion de Firmas - Fil.074 Caseros Centro/btnAceptarRegistro'))

// Verifica si el elemento está presente
if (WebUI.verifyElementPresent(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lnkAceptarAlertas'), 5, FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lnkAceptarAlertas'))
}

//Segundo control assert
labelFinalizada = WebUI.getText(findTestObject('13-MEP/Verificacion de Firmas - Fil.074 Caseros Centro/lblFinalizada'))
assert labelFinalizada.contains('FINALIZADA') == true

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}