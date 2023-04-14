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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se accede al menu Administracion de piezas
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lnkChequeras'), 6)
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkChequeras'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/lnkConsulta'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/lnkConsulta'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/01-Consulta/lnkConsultaConfdeChequerasconErrores(21)'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/01-Consulta/lnkConsultaConfdeChequerasconErrores(21)'))

//Switch a la ventana de chequeras con errores
WebUI.switchToWindowTitle('Solicitudes Chequeras con Errores')
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/04-Solicitudes Chequeras con Errores/txtSucursal'), 6)
WebUI.setText(findTestObject('Object Repository/11-Emision Chequera/04-Solicitudes Chequeras con Errores/txtSucursal'), findTestData('MainData/Users').getValue(3,5))
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/04-Solicitudes Chequeras con Errores/btnEjecutar'))

//Resultados de la Sucursal
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/04-Solicitudes Chequeras con Errores/lblSUCURSAL'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/11-Emision Chequera/04-Solicitudes Chequeras con Errores/lblSUCURSAL'))
def element = WebUI.getText(findTestObject('Object Repository/11-Emision Chequera/04-Solicitudes Chequeras con Errores/lblSUCURSAL'))
assert element.contains('SUCURSAL')


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