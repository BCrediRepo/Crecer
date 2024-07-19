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
import com.kms.katalon.core.testobject.ConditionType
import internal.GlobalVariable

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 10), findTestData('MainData/Users').getValue(
        2, 10))

WebUI.maximizeWindow()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CQ.CHRECH.MODFIRM.INFORMADO')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('CAMBIO DE FIRMANTES INFORMADOS')

//Seteo de Datos "NUMERO DE CUENTA", "NUMERO CHEQUE"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('NUMERO DE CUENTA', '02180086531')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('NUMERO CHEQUE', '10013')

WebUI.click(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/lnkEjecutar'))

WebUI.maximizeWindow()

WebUI.click(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/lnkModificarFirmantes'))

WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/btnAniadirFirmante'))

WebUI.waitForElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/txtFIRMANTE3'),6)

WebUI.setText(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/txtFIRMANTE3'), '1001530154') //antes estaba: 1002089452

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/btnValidarRegistro'))

WebUI.waitForElementVisible(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/btnAceptarRegistro'), 6)

WebUI.click(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/btnAceptarRegistro'))

//Valido que la transaccion se complete correctamente
WebUI.waitForElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Cambio De Firmantes Informados/lblTxnCompleta'),6)

def element = WebUI.getText(findTestObject('Object Repository/08-Cheques Rechazados/Cambio De Firmantes Informados/lblTxnCompleta'))

assert element.contains('Txn Completa:')

//WebUI.waitForElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Cambio De Firmantes Informados/lnkAceptarAlertas'), 6)

//Agrego un nuevo firmante para luego eliminarlo validando el aceptar alertas
//if (WebUI.verifyElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Cambio De Firmantes Informados/lnkAceptarAlertas'), FailureHandling.CONTINUE_ON_FAILURE)) {
//    WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Cambio De Firmantes Informados/lnkAceptarAlertas'))
//}

//Proceso para eliminar el firmante agregado anteriormente

WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lnkIraCambiodeFirmantes'))

WebUI.click(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/lnkModificarFirmantes'))

//Para buscar el boton eliminar y que si se muevo por cambio en registros lo siga con el "Following"
TestObject imgTestObject = new TestObject().addProperty("xpath", ConditionType.EQUALS, '//a[@id=\'fieldCaption:TIPO.DOC.FIRM\' and text()=\'TIPO DOCUMENTO.2\']/following::img[2]')
WebUI.click(imgTestObject)

WebUI.click(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/btnAceptarRegistro'))

//if (WebUI.verifyElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Cambio De Firmantes Informados/lnkAceptarAlertas'), FailureHandling.CONTINUE_ON_FAILURE)) {
//    WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Cambio De Firmantes Informados/lnkAceptarAlertas'))
//}

//Valido que la transaccion se complete correctamente
WebUI.waitForElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Cambio De Firmantes Informados/lblTxnCompleta'), 
    6)

def element2 = WebUI.getText(findTestObject('Object Repository/08-Cheques Rechazados/Cambio De Firmantes Informados/lblTxnCompleta'))

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