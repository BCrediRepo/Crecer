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
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(2, 3))
WebUI.maximizeWindow()

def menuDesplegable = ["Transferencias Mep"]
def link = "Transferencias Mep otros Conceptos"

//Navegar por el menu del Dashboard
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Cambiar a la ventana "BCCL.MEP.FT.TRANSFER"
WebUI.switchToWindowIndex(1)

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

//WebUI.click(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/rbtnNO'))

WebUI.click(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/rbtnNO - Copy'))

WebUI.selectOptionByIndex(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/cbxConoceAsociado'), 2)

WebUI.setText(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/txtCUILCUITBeneficiario'), '23592335034')

WebUI.setText(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/txtNombreBeneficiario'), 'RAGNAR COVELL')

WebUI.setText(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/txtObservaciones'), 'CRECER')

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Validacion y aceptacion de registro
WebUI.click(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/btnValidarRegistro'))

WebUI.click(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/btnAceptarRegistro'))

WebUI.delay(4)

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/lnkAceptarAlertas'))

//Definir Objeto
Transaccion = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion.split('\\s+')
def trx1 = partes[2]
GlobalVariable.vTxn = trx1

//Primer control assert
label = WebUI.getText(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/lblTxnCompleta'))

assert label.contains('Txn Completa:') == true

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//captura de pantalla PDF
//WebUI.switchToWindowTitle('Microsoft Word - 18505MP.doc - e-forms')

//Maximizar ventana
WebUI.maximizeWindow()

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verificacion de Firmas
WebUI.switchToWindowIndex(2)

WebUI.selectOptionByIndex(findTestObject('13-MEP/Verificacion de Firmas - Fil.074 Caseros Centro/cbxAccion'), 2)

WebUI.click(findTestObject('13-MEP/Verificacion de Firmas - Fil.074 Caseros Centro/btnAceptarRegistro'))

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