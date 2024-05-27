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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(2, 1))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresamos el menu ?1 en el command line
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?1')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

//Maximizamos
WebUI.maximizeWindow()

//Click en Suc Piloto
WebUI.click(findTestObject('Object Repository/09-Compensacion de Saldos/02-Temenos T24/spanSucursal Piloto'))

//Click en D4
WebUI.click(findTestObject('Object Repository/09-Compensacion de Saldos/02-Temenos T24/spanD4-Compensacion de Saldos'))

//Click en Parametrizacion
WebUI.click(findTestObject('Object Repository/09-Compensacion de Saldos/02-Temenos T24/spanParametrizacion'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en Productos y Circulares
WebUI.click(findTestObject('Object Repository/09-Compensacion de Saldos/02-Temenos T24/lnkProductos y Circulares'))

//Switch a la ventana BCCL.AC.CP.PROD
WebUI.switchToWindowTitle('BCCL.AC.CP.PROD')

//Maximizamos
WebUI.maximizeWindow()

//Ingresamos los datos
WebUI.click(findTestObject('Object Repository/09-Compensacion de Saldos/03-BCCL.AC.CP.PROD/btnLista De Registros'))

WebUI.click(findTestObject('Object Repository/09-Compensacion de Saldos/03-BCCL.AC.CP.PROD/lbl1001.2000'))

WebUI.click(findTestObject('Object Repository/09-Compensacion de Saldos/03-BCCL.AC.CP.PROD/btnModificarRegistro'))

//Ingresamos Circulares
WebUI.setText(findTestObject('Object Repository/09-Compensacion de Saldos/03-BCCL.AC.CP.PROD/txtCirculares.1'), '01')

//Validamos
WebUI.click(findTestObject('Object Repository/09-Compensacion de Saldos/03-BCCL.AC.CP.PROD/btnValidarRegistro'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/09-Compensacion de Saldos/03-BCCL.AC.CP.PROD/lblNo se encuentra archivo BCCL.CIRCULARES'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/09-Compensacion de Saldos/03-BCCL.AC.CP.PROD/lblNo se encuentra archivo BCCL.CIRCULARES'))

def element = WebUI.getText(findTestObject('Object Repository/09-Compensacion de Saldos/03-BCCL.AC.CP.PROD/lblNo se encuentra archivo BCCL.CIRCULARES'))

assert element.contains('No se encuentra archivo BCCL.CIRCULARES')

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


