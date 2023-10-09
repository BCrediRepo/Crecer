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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 7), findTestData('MainData/Users').getValue(
        2, 7))

WebUI.maximizeWindow()

//Busqueda de  app
WebUI.click(findTestObject('02-Dashboard/lnkExtracciones'))

WebUI.click(findTestObject('02-Dashboard/47-Extracciones/lnkRetiroEnVentanilla'))

WebUI.switchToWindowTitle('TELLER')

//Seteo de datos
WebUI.setText(findTestObject('49-Extracciones/TELLER/txtNroCuenta'), '10740022960')

WebUI.click(findTestObject('49-Extracciones/TELLER/btnValidarRegistro'))

WebUI.setText(findTestObject('49-Extracciones/TELLER/txtMonto'), '10')

WebUI.click(findTestObject('49-Extracciones/TELLER/btnAceptarRegistro'))

WebUI.click(findTestObject('49-Extracciones/TELLER/lnkAceptarAlertas'))

WebUI.switchToWindowIndex(3)

WebUI.waitForElementVisible(findTestObject('49-Extracciones/Impresion de Ticket/formTicket'), 3)

//Impresion de ticket
ticket = WebUI.getAttribute(findTestObject('49-Extracciones/Impresion de Ticket/formTicket'), 'innerText')

println(ticket)

WebUI.closeWindowIndex(3)

//se verifica la firma
WebUI.switchToWindowTitle('Verificacion de Firmas - Fil.074 Caseros Centro')

WebUI.selectOptionByIndex(findTestObject('00-Utils/03-Verificacion de Firmas/cbxAccion'), 2)

WebUI.click(findTestObject('00-Utils/03-Verificacion de Firmas/btnAceptar'))

WebUI.closeWindowTitle('Verificacion de Firmas - Fil.074 Caseros Centro')

WebUI.switchToWindowTitle('TELLER')

//Se verifica la txn completa
txn = WebUI.getText(findTestObject('49-Extracciones/TELLER/lblTxnCompleta'))

assert txn.contains('Txn Completa:') == true

assert ticket.contains('Importe: $ **********************10,00') == true 

//------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}