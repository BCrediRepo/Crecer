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
import java.text.SimpleDateFormat
import java.util.Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,30), findTestData('MainData/Users').getValue(2,30))
WebUI.maximizeWindow()

//Ingreso a la tabla BCCL.AS.DDIF.PASES L L
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'BCCL.AS.DDIF.PASES L L')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Abre la ventana BCCL.AS.DDIF.PASES
WebUI.switchToWindowTitle('%BCCL.AS.DDIF.PASES')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.closeWindowIndex(1)
WebUI.switchToWindowIndex(0)

//Ingreso a la tabla BCCL.AS.DDIF.PASES L L
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'BCCL.AS.DDIF.PASES L L')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la ventana BCCL.AS.DDIF.PASES
WebUI.switchToWindowTitle('%BCCL.AS.DDIF.PASES')

//Ingresa Filtro ID COELSA
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/txtL.ID.TRX.ORIGEN'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/txtL.ID.TRX.ORIGEN'), 'DLMORZP90Y6D81YNEGJ468D')
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Abre la ventana BCCL.AS.DDIF.PASES
WebUI.switchToWindowTitle('BCCL.AS.DDIF.PASES - Lista Default')

//Valido variables de la tabla
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/txtBCCL.AS.DDIF.PASES'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/txtBCCL.AS.DDIF.PASES'))
def element = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/txtBCCL.AS.DDIF.PASES'))
assert element.contains('BCCL.AS.DDIF.PASES')

//Guardo el FT obtenido
def FTvariable = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/txtFT'))
println(FTvariable)

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
