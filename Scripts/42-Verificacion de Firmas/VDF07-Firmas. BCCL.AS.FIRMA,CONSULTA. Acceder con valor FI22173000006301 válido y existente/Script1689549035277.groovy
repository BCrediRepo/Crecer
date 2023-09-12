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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,2), findTestData('MainData/Users').getValue(2,2))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresamos la ENQ en el command line
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'BCCL.AS.FIRMA,CONSULTA')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana BCCL.AS.FIRMA
WebUI.switchToWindowTitle('BCCL.AS.FIRMA')

//Ingresamos los datos para la consulta
WebUI.setText(findTestObject('Object Repository/43-Verificacion de Firmas/07-BCCL.AS.FIRMA/txtVerificacion de firmas y facultades'), 'FI22173000006301')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en Modifica Registro
WebUI.click(findTestObject('Object Repository/43-Verificacion de Firmas/07-BCCL.AS.FIRMA/btnModificaRegistro'))

//Assert

WebUI.waitForElementVisible(findTestObject('Object Repository/43-Verificacion de Firmas/07-BCCL.AS.FIRMA/lblConsulta verificacion firmas y facultades'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/43-Verificacion de Firmas/07-BCCL.AS.FIRMA/lblConsulta verificacion firmas y facultades'))

def element = WebUI.getText(findTestObject('Object Repository/43-Verificacion de Firmas/07-BCCL.AS.FIRMA/lblConsulta verificacion firmas y facultades'))

assert element.contains('Consulta verificacion firmas y facultades')

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




