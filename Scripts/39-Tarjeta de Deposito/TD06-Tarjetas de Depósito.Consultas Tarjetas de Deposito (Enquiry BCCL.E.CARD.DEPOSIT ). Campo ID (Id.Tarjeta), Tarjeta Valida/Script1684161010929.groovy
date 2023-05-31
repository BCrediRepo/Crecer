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

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
        2, 9))

//Se maximisa la ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 3)

//Se busca el TestBox de "Buscador"
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?311')

//Click en el boton "Ejecutar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiamos a la ventana "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')

//Click en "Tarjetas"
WebUI.click(findTestObject('02-Dashboard/lnkTarjetas311'))

//Click en Tarjetas de Deposito"
WebUI.click(findTestObject('02-Dashboard/lnkTarjetasdeDeposito'))

//Click en Consulta Tarjetas de Deposito"
WebUI.click(findTestObject('02-Dashboard/38-Tarjeta de Deposito/lnkConsultaTarjetasdeDeposito'))

//Cambiamos a la ventana "BCCL.E.CARD.DEPOSIT"
WebUI.switchToWindowTitle('BCCL.E.CARD.DEPOSIT')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Cambiamos a la ventana "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')

//Click en Consulta Tarjetas de Deposito"
WebUI.click(findTestObject('02-Dashboard/38-Tarjeta de Deposito/lnkConsultaTarjetasdeDeposito'))

//Cambiamos a la ventana "BCCL.E.CARD.DEPOSIT"
WebUI.switchToWindowTitle('BCCL.E.CARD.DEPOSIT')

//Seteamos el dato "6042018000002609"
WebUI.setText(findTestObject('40-Tarjeta de Deposito/BCCL.E.CARD.DEPOSIT/txtIdTarjeta'), '6042018000002609')

//Ejecutamos
WebUI.click(findTestObject('40-Tarjeta de Deposito/BCCL.E.CARD.DEPOSIT/lnkEjecutar'))

//Esperamos que el dato sea equivalente al id de la busqueda.
Id = WebUI.waitForElementVisible(findTestObject('40-Tarjeta de Deposito/BCCL.E.CARD.DEPOSIT/lblIDTarjeta'), 6)

//Realizamos el assert.
assert Id == true //Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

