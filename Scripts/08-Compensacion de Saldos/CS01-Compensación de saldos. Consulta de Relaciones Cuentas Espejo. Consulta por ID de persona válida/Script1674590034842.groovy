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
CustomKeywords.'pkgModules.kywGeneric.Login'(GlobalVariable.vTest10_IP, GlobalVariable.vTest10Name, GlobalVariable.vF00289, GlobalVariable.vPass)

//Se accede al menu Compensacion de Saldos
WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/02-Dashboard/lknCompensaciondeSaldos'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/7-Compensacion de Saldos/lknConsultas'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/7-Compensacion de Saldos/01-Consultas/lknConsultadeRelaciones'))

WebUI.switchToWindowTitle('BCCL.E.ACCP.GROUP')

WebUI.setText(findTestObject('Object Repository/09-Compensacion de Saldos/01-Consulta de Relaciones/txtIDcuenta'), "00895279268")

WebUI.setText(findTestObject('Object Repository/09-Compensacion de Saldos/01-Consulta de Relaciones/txtTipoRegla'), "ESP")

WebUI.click(findTestObject('Object Repository/09-Compensacion de Saldos/01-Consulta de Relaciones/btnEjecutar'))

WebUI.verifyElementPresent(findTestObject('Object Repository/09-Compensacion de Saldos/01-Consulta de Relaciones/lblCtaEspejo'), 6)

WebUI.takeScreenshot("Screenshot/Compensacion de Saldos/Compensacion de saldos. Consulta de Relaciones Cuentas Espejo. Consulta por ID de persona valida" + nowString + ".png")



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



