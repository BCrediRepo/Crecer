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

//Config
LocalDateTime now = LocalDateTime.now()
DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE
String nowString = formatter.format(now)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(GlobalVariable.vTest10_IP, GlobalVariable.vTest10Name, GlobalVariable.vF00073, GlobalVariable.vPass)

//Se accede al menu
WebUI.maximizeWindow()
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'TELLER,REPOSICION.POR.MENOS.PN099 I F3')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))
//Switch a la ventana de busqueda de consulta
WebUI.switchToWindowTitle('TELLER')
WebUI.waitForElementVisible(findTestObject('Object Repository/17-Remesas/TELLER,REPOSICION.POR.MENOS.PN099/txtMonto'), 6)
WebUI.setText(findTestObject('Object Repository/17-Remesas/TELLER,REPOSICION.POR.MENOS.PN099/txtMonto'), '100')
WebUI.waitForElementVisible(findTestObject('Object Repository/17-Remesas/TELLER,REPOSICION.POR.MENOS.PN099/btnAceptarRegistro'), 6)
WebUI.click(findTestObject('Object Repository/17-Remesas/TELLER,REPOSICION.POR.MENOS.PN099/btnAceptarRegistro'))
WebUI.click(findTestObject('Object Repository/17-Remesas/TELLER,REPOSICION.POR.MENOS.PN099/btnAceptarRegistro'))
WebUI.waitForElementVisible(findTestObject('Object Repository/17-Remesas/TELLER,REPOSICION.POR.MENOS.PN099/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/TELLER,REPOSICION.POR.MENOS.PN099/lblTxnCompleta'))
WebUI.takeScreenshot('Screenshot/Remesas/REMESAS.Reposición por menos. TELLER,REPOSICION.POR.MENOS.PN099' + nowString + '.png')

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'('Screenshot/Fails/APT01Error.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}
