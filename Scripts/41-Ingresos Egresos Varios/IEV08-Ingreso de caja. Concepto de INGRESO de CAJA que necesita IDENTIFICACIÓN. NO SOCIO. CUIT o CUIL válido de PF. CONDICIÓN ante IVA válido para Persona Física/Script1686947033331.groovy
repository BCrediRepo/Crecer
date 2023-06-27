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
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
        2, 9))

WebUI.maximizeWindow()

WebUI.click(findTestObject('02-Dashboard/41-Ingresos Egresos Varios/lnkOperatoriaDeCajaReemplazo'))

WebUI.click(findTestObject('02-Dashboard/41-Ingresos Egresos Varios/Operatoria de caja - reemplazo/lnkVariosDeCaja'))

WebUI.click(findTestObject('02-Dashboard/41-Ingresos Egresos Varios/Operatoria de caja - reemplazo/Varios de caja/lnkIngresosVariosDeCaja'))

WebUI.switchToWindowTitle('TELLER')

WebUI.click(findTestObject('42-Ingresos Egresos Varios/TELLER-Ingresos/btnDropDownMoneda'))

WebUI.click(findTestObject('42-Ingresos Egresos Varios/TELLER-Ingresos/lblUSD'))

WebUI.setText(findTestObject('42-Ingresos Egresos Varios/TELLER-Ingresos/txtImporteUSD'), '10')

WebUI.click(findTestObject('42-Ingresos Egresos Varios/TELLER-Ingresos/btnDropDownConcepto'))

WebUI.click(findTestObject('42-Ingresos Egresos Varios/TELLER-Ingresos/lblChequeCancelatorioEmisionEfvo'))

WebUI.selectOptionByIndex(findTestObject('42-Ingresos Egresos Varios/TELLER-Ingresos/cbxSocio'), 1)

WebUI.click(findTestObject('42-Ingresos Egresos Varios/TELLER-Ingresos/txtCuilCuitNoSocio'))

WebUI.setText(findTestObject('42-Ingresos Egresos Varios/TELLER-Ingresos/txtCuilCuitNoSocio'), '20203798041')

WebUI.click(findTestObject('42-Ingresos Egresos Varios/TELLER-Ingresos/btnDropDownCondicionIVA'))

WebUI.click(findTestObject('42-Ingresos Egresos Varios/TELLER-Ingresos/lblConsumidorFinal'))

WebUI.click(findTestObject('42-Ingresos Egresos Varios/TELLER-Ingresos/btnAceptarRegistro'))

WebUI.click(findTestObject('42-Ingresos Egresos Varios/TELLER-Ingresos/lnkAceptarAlertas'))

WebUI.switchToWindowIndex(2)

WebUI.verifyElementVisible(findTestObject('42-Ingresos Egresos Varios/TicketReporte/formTicket'))

WebUI.switchToWindowTitle('TELLER')

WebUI.verifyElementVisible(findTestObject('42-Ingresos Egresos Varios/TELLER-Ingresos/lblTxnCompleta'))

label = WebUI.getText(findTestObject('42-Ingresos Egresos Varios/TELLER-Ingresos/lblTxnCompleta'))

assert label.contains('Txn Completa:') == true //Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

