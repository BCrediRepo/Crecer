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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click de cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))

//Click en consultas de cuenta
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/spanConsultas de Cuenta'))

//Click en consulta de saldo al dia
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkConsultadeSaldoalDia'))

//Switch a la ventana Saldos de Cuenta
WebUI.switchToWindowTitle('Saldos de Cuenta')

//Maximizamos
WebUI.maximizeWindow()

//Ingresamos la cuenta a consultar
WebUI.setText(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/txtCuenta'), '11300014928')

//Click en ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblMoneda'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblMoneda'))

def element = WebUI.getText(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblMoneda'))

assert element.contains('Moneda')

WebUI.closeBrowser()

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en transferencias mep
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkTransferenciasMEP'))

//Click en transferencias MEP entre cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/10-MEP/Transferencias MEP/lnkTransferenciasMepEntreCuentas'))

//Switch a la ventana BCCL.MEP.FT.TRANSFER
WebUI.switchToWindowTitle('BCCL.MEP.FT.TRANSFER')

//Maximizamos
WebUI.maximizeWindow()
















