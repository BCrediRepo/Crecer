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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.EB.COM.PER.ESP')
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))
WebUI.switchToWindowTitle('COM.PER.ESP')
WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnDrilldown'))
WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/lblBOLSIN.00010156010'))
WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnEditar'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

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

//Configuracion de ambiente y Login
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,6), findTestData('MainData/Users').getValue(2,6))
WebUI.maximizeWindow()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.EB.COM.PER.ESP')
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))
WebUI.switchToWindowTitle('COM.PER.ESP')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnDrilldown'))
WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/lblBOLSIN.00010156010'))
WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnModificar'))
//WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnAutorizarRegistro'))
//CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.closeBrowser()

//Configuracion de ambiente y Login
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.EB.COM.PER.ESP')
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))
WebUI.switchToWindowTitle('COM.PER.ESP')
WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnDrilldown'))
WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/lblBOLSIN.00010156010'))
WebUI.click(findTestObject('06-Comisiones/COM.PER.ESP/btnModificar'))
WebUI.maximizeWindow()

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
