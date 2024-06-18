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

//****************************************************************//
//********** preparación de dato para caso CHR05 *****************//
//****************************************************************//
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login 
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 7), findTestData('MainData/Users').getValue(
        2, 7))

WebUI.maximizeWindow()

WebUI.delay(15, FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.CQ.CHEQUES')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('BCCL.CQ.CHEQUES')

WebUI.setText(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/txtNumCheque'), '502004')

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/btnEditarRegistro'))

WebUI.selectOptionByIndex(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/cbEstadoCheque'), '7')

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/btnAceptarRegistro'))

WebUI.closeWindowIndex(1)

WebUI.switchToWindowIndex(0)

WebUI.click(findTestObject('02-Dashboard/btnLogout'))

//WebUI.closeWindowIndex(0)
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login para autorizar dato y finalizar preparación de caso CHR05
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
        2, 9))

WebUI.maximizeWindow()

WebUI.delay(15, FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.CQ.CHEQUES')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('BCCL.CQ.CHEQUES')

WebUI.setText(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/txtNumCheque'), '502004')

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/btnAutorizarRegistro'))

WebUI.maximizeWindow()

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/btnAutorizarCambiosEnElRegistro'))

WebUI.verifyElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lblTxnCompleta'))

txn = WebUI.getText(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lblTxnCompleta'))

assert txn.contains('Txn Completa: ')

//**************************************************************//
//******************* INICIO DEL CASO CHR05 ********************//
//**************************************************************//
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login 
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 7), findTestData('MainData/Users').getValue(
        2, 7))

WebUI.maximizeWindow()

WebUI.delay(15, FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.CQ.CHEQUES')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('BCCL.CQ.CHEQUES')

WebUI.setText(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/txtNumCheque'), '502004')

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/btnEditarRegistro'))

WebUI.selectOptionByIndex(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/cbEstadoCheque'), '9')

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/btnAceptarRegistro'))

WebUI.closeWindowIndex(1)

WebUI.switchToWindowIndex(0)

WebUI.click(findTestObject('02-Dashboard/btnLogout'))

//WebUI.closeWindowIndex(0)
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login para autorizar dato y finalizar preparación de caso CHR05
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
        2, 9))

WebUI.maximizeWindow()

WebUI.delay(15, FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.CQ.CHEQUES')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('BCCL.CQ.CHEQUES')

WebUI.setText(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/txtNumCheque'), '502004')

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/btnAutorizarRegistro'))

WebUI.maximizeWindow()

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/btnAutorizarCambiosEnElRegistro'))

WebUI.verifyElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lblTxnCompleta'))

txn = WebUI.getText(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lblTxnCompleta'))

assert txn.contains('Txn Completa: ' ////login final de validacion
    ) //CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 7), findTestData('MainData/Users').getValue(
//        2, 7))
//
//WebUI.maximizeWindow()
//
//WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.CQ.CHEQUES')
//
//WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))
//
//WebUI.switchToWindowTitle('BCCL.CQ.CHEQUES')
//
//WebUI.setText(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/txtNumCheque'), '502004')
//
//WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/btnAutorizarRegistro'))
//
//WebUI.maximizeWindow()
//
//WebUI.verifyElementVisible(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/lblUltimasModificaciones'))
//
//CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
//
//WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/btnAutorizarCambiosEnElRegistro'))
//
//WebUI.verifyElementVisible(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/lblValorAnterior'))
//
//anterior = WebUI.getText(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/lblValorAnterior'))
//
//WebUI.verifyElementVisible(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/lblValorActual'))
//
//actual = WebUI.getText(findTestObject('08-Cheques Rechazados/BCCL.CQ.CHEQUES/lblValorActual'))
//
//assert anterior.contains('7')
//
//assert actual.contains('9')
	
//---------------------------------------------------------------------------------------------------
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

