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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 8), findTestData('MainData/Users').getValue(2, 8))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se accede al menu ?1
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?1')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

//Maximizamos
WebUI.maximizeWindow()

//Click en sucursal piloto
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/07-Temenos T24/lnkSucursalPiloto'))

//Click en compensacion de saldos
WebUI.click(findTestObject('Object Repository/09-Compensacion de Saldos/02-Temenos T24/spanD4-Compensacion de Saldos'))

//Click en parametrizacion
WebUI.click(findTestObject('Object Repository/09-Compensacion de Saldos/02-Temenos T24/spanParametrizacion'))

//Click en Productos y Circulares
WebUI.click(findTestObject('Object Repository/09-Compensacion de Saldos/02-Temenos T24/lnkProductos y Circulares'))

//Switch a la ventana BCCL.AC.CP.PROD
WebUI.switchToWindowTitle('BCCL.AC.CP.PROD')

//Maximizamos
WebUI.maximizeWindow()

//Click en la lista de registro
WebUI.click(findTestObject('Object Repository/09-Compensacion de Saldos/03-BCCL.AC.CP.PROD/btnLista De Registros'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/09-Compensacion de Saldos/03-BCCL.AC.CP.PROD/lblId'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/09-Compensacion de Saldos/03-BCCL.AC.CP.PROD/lblId'))

def element = WebUI.getText(findTestObject('Object Repository/09-Compensacion de Saldos/03-BCCL.AC.CP.PROD/lblId'))

assert element.contains('Id')


//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'('Screenshot/Fails/CDC01Error.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}


