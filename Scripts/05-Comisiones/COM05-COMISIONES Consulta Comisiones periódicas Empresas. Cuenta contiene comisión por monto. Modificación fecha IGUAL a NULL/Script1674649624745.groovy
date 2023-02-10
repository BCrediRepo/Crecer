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

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(GlobalVariable.vTest10_IP, GlobalVariable.vTest10Name, GlobalVariable.vF00274, 
    GlobalVariable.vPass)

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.EB.COM.PER.ESP')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('COM.PER.ESP')

WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnDrilldown'))

WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/lblBOLSIN.00010156010'))

WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnEditar'))

Fecha = WebUI.getText(findTestObject('06-Comisiones/COM.PER.ESP/txtFechaHasta'))

if (Fecha != null) {
    WebUI.setText(findTestObject('06-Comisiones/COM.PER.ESP/txtFechaHasta'), '')

    WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnAceptar'))

    WebUI.closeBrowser()
} else {
    WebUI.maximizeWindow()

    WebUI.takeScreenshot('Screenshot/Comisiones/COM05-COMISIONES Consulta Comisiones periódicas Empresas. Cuenta contiene comisión por monto. Modificación fecha IGUAL a NULL.png')

    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(GlobalVariable.vTest10_IP, GlobalVariable.vTest10Name, GlobalVariable.vF00474, 
    GlobalVariable.vPass)

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.EB.COM.PER.ESP')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('COM.PER.ESP')

WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnDrilldown'))

WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/lblBOLSIN.00010156010'))

WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnModificar'))

WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnAutorizarRegistro'))

WebUI.closeBrowser()

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(GlobalVariable.vTest10_IP, GlobalVariable.vTest10Name, GlobalVariable.vF00274, 
    GlobalVariable.vPass)

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.EB.COM.PER.ESP')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('COM.PER.ESP')

WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnDrilldown'))

WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/lblBOLSIN.00010156010'))

WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnModificar'))

WebUI.maximizeWindow()

WebUI.takeScreenshot('Screenshot/Comisiones/COM05-COMISIONES Consulta Comisiones periódicas Empresas. Cuenta contiene comisión por monto. Modificación fecha IGUAL a NULL.png')

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'('Screenshot/Fails/Comisiones/Error-COM05-COMISIONES Consulta Comisiones periódicas Empresas. Cuenta contiene comisión por monto. Modificación fecha IGUAL a NULL.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

