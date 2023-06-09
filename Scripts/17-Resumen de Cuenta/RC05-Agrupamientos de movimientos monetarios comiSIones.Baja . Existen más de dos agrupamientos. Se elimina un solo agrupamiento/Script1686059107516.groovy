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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 4), findTestData('MainData/Users').getValue(2, 4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Ingreso en el commandline BCCL.ENQ.PARAM.AGRP,INPUT
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'BCCL.ENQ.PARAM.AGRP,INPUT')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Cambiamos de ventana
WebUI.switchToWindowTitle('BCCL.ENQ.PARAM.AGRP')

// Maximizamos
WebUI.maximizeWindow()

//Ingresamos datos
WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/txtAgrupadoDeMovMonetariosYComisiones'), 'ONLINE')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/btnModificarRegistro'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresamos los datos para el alta del nuevo agrupamiento
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/btnExpandirMultivalor'))

WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/cbxTipoAgrupamiento'))

WebUI.selectOptionByIndex(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/cbxTipoAgrupamiento'), 3)

WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/txtCodigoAgrupamiento'), 'AVGUZ')

WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/txtDescripcionAgrupamiento'), 'Acreditacion Valores Camara/C.Interno')

WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/txtDescCortaAgrupamiento'), 'ACREDVAL')

WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/txtIds Cod Oper'), '00105')

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/btnValidarRegistro'))

WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/btnAceptarRegistro'))

//Verificamos txnCompleta
WebUI.waitForElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/lblTxnCompleta'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/lblTxnCompleta'))

WebUI.closeBrowser()


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 4), findTestData('MainData/Users').getValue(2, 4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Ingreso en el commandline BCCL.ENQ.PARAM.AGRP,INPUT
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'BCCL.ENQ.PARAM.AGRP,INPUT')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Cambiamos de ventana
WebUI.switchToWindowTitle('BCCL.ENQ.PARAM.AGRP')

// Maximizamos
WebUI.maximizeWindow()

//Ingresamos en el command line "ONLINE"
WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/txtAgrupadoDeMovMonetariosYComisiones'), 'ONLINE')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Maximizamos
WebUI.maximizeWindow()

//Click en Modificar Registro
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/btnModificarRegistro'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/btnBorrarMultivalor'))

WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/btnValidarRegistro'))

WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/btnAceptarRegistro'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/lblTxnCompleta'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/lblTxnCompleta'))

def element = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/lblTxnCompleta'))

assert element.contains('Txn Completa:')

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


