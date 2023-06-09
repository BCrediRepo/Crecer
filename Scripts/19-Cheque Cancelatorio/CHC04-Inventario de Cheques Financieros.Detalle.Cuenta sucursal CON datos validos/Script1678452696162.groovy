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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(2, 9))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()


WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscar'), '?1')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('02-Dashboard/lnkSucursalPiloto'))

WebUI.click(findTestObject('02-Dashboard/05-SucursalPiloto/spanD3-CC1'))

WebUI.click(findTestObject('02-Dashboard/05-SucursalPiloto/CC1/spanCC1-ChequesCertificadosyFinancieros'))

WebUI.click(findTestObject('02-Dashboard/05-SucursalPiloto/CC1/Cheques Certificados y Financieros/spanChequesFinancieros'))

WebUI.click(findTestObject('02-Dashboard/05-SucursalPiloto/CC1/Cheques Certificados y Financieros/lnkInventarioDeChequesFinancieros'))

WebUI.switchToWindowTitle('BCCL.CQ.CF.INVENTARIO')

WebUI.click(findTestObject('20-Cheque Cancelatorio/BCCL.CQ.CF.INVENTARIO/lnkNuevaSeleccion'))

WebUI.click(findTestObject('20-Cheque Cancelatorio/BCCL.CQ.CF.INVENTARIO/lnkEjecutar'))

WebUI.click(findTestObject('20-Cheque Cancelatorio/BCCL.CQ.CF.INVENTARIO/btnLupita'))

WebUI.setText(findTestObject('20-Cheque Cancelatorio/BCCL.CQ.CF.INVENTARIO/txtCuentaDeSucursal'), '074')

WebUI.click(findTestObject('20-Cheque Cancelatorio/BCCL.CQ.CF.INVENTARIO/lnkEjecutar'))

WebUI.maximizeWindow()

Sucursal = WebUI.verifyElementVisible(findTestObject('20-Cheque Cancelatorio/BCCL.CQ.CF.INVENTARIO/lblSucursal'), FailureHandling.STOP_ON_FAILURE)

NumeroSucursal = WebUI.verifyElementVisible(findTestObject('20-Cheque Cancelatorio/BCCL.CQ.CF.INVENTARIO/lbl074'), FailureHandling.STOP_ON_FAILURE)

if ((Sucursal == true) && (NumeroSucursal == true)) {
    WebUI.takeScreenshot('Screenshot/Cheques Cancelatorios/CHC04 - Inventario de Cheques Financieros.Detalle Cuenta sucursal CON datos Validos.png')
} else {
    WebUI.takeScreenshot('Screenshot/Cheques Cancelatorios/Fails/Error - CHC04 - Inventario de Cheques Financieros.Detalle Cuenta sucursal CON datos Validos.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

