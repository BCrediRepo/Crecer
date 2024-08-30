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

WebUI.callTestCase(findTestCase('11-MEP/MEP01-MEP. Transferencias MEP. Transacciones Online. Otros Conceptos. Usuario de filial NIV5'), 
    [:], FailureHandling.STOP_ON_FAILURE)

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(
        2, 3))

WebUI.maximizeWindow()

WebUI.click(findTestObject('02-Dashboard/lnkAutorizaciones'))

WebUI.click(findTestObject('02-Dashboard/10-MEP/Autorizaciones/lnkConsElimTxPropiasNoAutorizadas'))

WebUI.switchToWindowTitle('AUTORIZAC. PENDIENTES')

WebUI.click(findTestObject('13-MEP/AUTORIZAC. PENDIENTES/lnkELIMINARTRANSACCION'))

//WebUI.switchToWindowTitle('BCCL.MEP.FT.TRANSFER.HIS')
//WebUI.switchToWindowTitle('Movimiento de Fondos')
WebUI.switchToWindowTitle('BCCL.CQ.SOLICITUD')

//WebUI.click(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER.HIS/btnValidarRegistro'))
//WebUI.click(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER.HIS/btnAceptarRegistro'))

WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnEliminarRegistro'))

label = WebUI.getText(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER.HIS/lblTXNCompleta'))

assert label.contains('Txn Completa:') == true

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}


