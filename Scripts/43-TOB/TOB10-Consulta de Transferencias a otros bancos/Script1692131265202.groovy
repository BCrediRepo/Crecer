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

WebUI.callTestCase(findTestCase('43-TOB/TOB03-Transferencia OnLine a otros bancos. Completitud de Campo Moneda. Cuenta en Dólares'), 
    [:], FailureHandling.STOP_ON_FAILURE)

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 22), findTestData('MainData/Users').getValue(
        2, 22))

//Apertura app
WebUI.click(findTestObject('02-Dashboard/43-TOB/lnkTransferenciasACuentasdeOtrosBancosSNP'))

WebUI.click(findTestObject('02-Dashboard/43-TOB/Transferencias a Cuentas de Otros Bancos SNP/lnkConsultaDeTransfaOtrosBancos'))

WebUI.switchToWindowTitle('BCCL.E.SNP.FT.TRANSFER')

CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

WebUI.switchToWindowIndex(0)

WebUI.click(findTestObject('02-Dashboard/43-TOB/Transferencias a Cuentas de Otros Bancos SNP/lnkConsultaDeTransfaOtrosBancos'))

WebUI.switchToWindowTitle('BCCL.E.SNP.FT.TRANSFER')

WebUI.setText(findTestObject('44-TOB/BCCL.E.SNP.FT.TRANSFER/txtCuenta'), '21190118359')

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebUI.verifyElementVisible(findTestObject('44-TOB/BCCL.E.SNP.FT.TRANSFER/lblCuenta'))

Cuenta = WebUI.getText(findTestObject('44-TOB/BCCL.E.SNP.FT.TRANSFER/lblCuenta'))

assert Cuenta == '21190118359'

WebUI.verifyElementVisible(findTestObject('44-TOB/BCCL.E.SNP.FT.TRANSFER/lblExterno'))

externo = WebUI.getText(findTestObject('44-TOB/BCCL.E.SNP.FT.TRANSFER/lblExterno'))

assert externo == 'SANTANDER'


//Control Fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}


