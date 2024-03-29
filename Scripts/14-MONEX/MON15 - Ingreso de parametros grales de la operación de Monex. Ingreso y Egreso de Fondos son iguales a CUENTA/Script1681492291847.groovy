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
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.NOFILE.DET.OPER.MONEX')

//Click en el boton "Ejecutar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Det. de Oper de Compra-Venta MONEX')

WebUI.maximizeWindow()

WebUI.waitForElementVisible(findTestObject('15-MONEX/04-BCCL.E.COT.GRAL.FIL/lnkEjecutar'), 3)

WebUI.click(findTestObject('15-MONEX/04-BCCL.E.COT.GRAL.FIL/lnkEjecutar'))

WebUI.click(findTestObject('15-MONEX/02-Administracion Parametros de Sucursales/btnLupita'))

WebUI.setText(findTestObject('15-MONEX/02-Administracion Parametros de Sucursales/txtNroBoleto (1)'), 'OC222030092316400017')

WebUI.waitForElementVisible(findTestObject('15-MONEX/04-BCCL.E.COT.GRAL.FIL/lnkEjecutar'), 3)

WebUI.click(findTestObject('15-MONEX/04-BCCL.E.COT.GRAL.FIL/lnkEjecutar'))

WebUI.click(findTestObject('15-MONEX/08- ENQ BCCL.E.NOFILE.DET.OPER.MONEX/lblMasVerde'))

WebUI.click(findTestObject('15-MONEX/08- ENQ BCCL.E.NOFILE.DET.OPER.MONEX/lblBuscadorBoleto'))

WebUI.switchToWindowTitle('Compra-Venta Historico')

ImporteME = WebUI.verifyElementVisible(findTestObject('15-MONEX/09 - Consulta Operaciones de cambio.Filtro Nro de boleto/lblImporteME'))

assert ImporteME == true //---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

