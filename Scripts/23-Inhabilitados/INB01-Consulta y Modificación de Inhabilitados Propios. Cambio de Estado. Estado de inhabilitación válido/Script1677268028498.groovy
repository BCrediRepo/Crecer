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


LocalDateTime now = LocalDateTime.now()
DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE
String nowString = formatter.format(now)


//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(GlobalVariable.vTest10_IP, GlobalVariable.vTest10Name, GlobalVariable.vANOVELLO, GlobalVariable.vPass)

// Accedo a menu ?MNU.BCCL.SP.D3.CC2

WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscar'), "?MNU.BCCL.SP.D3.CC2")

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkInhabilitados'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/22-Inhabilitados/lnkConsulta y Modificacion de Inhabilitados prop'))

WebUI.switchToWindowTitle('BCCL.E.INHAB.PROPIOS')

WebUI.setText(findTestObject('Object Repository/24-Inhabilitados/txtCUITCUIL'), "20033555211")

WebUI.setText(findTestObject('Object Repository/24-Inhabilitados/txtIDPERSONA'), "1004020304")

WebUI.click(findTestObject('Object Repository/24-Inhabilitados/lnkEjecutar'))

WebUI.click(findTestObject('Object Repository/24-Inhabilitados/lnkModificarElestado'))

WebUI.click(findTestObject('Object Repository/24-Inhabilitados/btnValidarRegistro'))

WebUI.click(findTestObject('Object Repository/24-Inhabilitados/btnAceptarRegistro'))

WebUI.takeScreenshot("Screenshot/Consulta y Modificación de Inhabilitados Propios. Cambio de Estado. Estado de inhabilitación válido." + nowString + ".png")


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












