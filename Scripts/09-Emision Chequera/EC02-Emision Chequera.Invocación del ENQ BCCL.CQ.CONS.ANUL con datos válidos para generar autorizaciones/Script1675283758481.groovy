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
def vWindowTitle = 'ENQ '+ findTestData('Modulos/Modulos').getValue(4,2)
LocalDateTime now = LocalDateTime.now()
DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE
String nowString = formatter.format(now)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(GlobalVariable.vTest10_IP, GlobalVariable.vTest10Name, GlobalVariable.vF02055, GlobalVariable.vPass)

//Se accede al menu Administracion de piezas
WebUI.maximizeWindow()
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.CQ.CONS.ANUL')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana de busqueda de consulta de pedidos
WebUI.switchToWindowTitle('Consulta de Pedidos')
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/02-BCCL.CQ.CONS.ANUL/lblConsulta de Pedidos'), 6)
WebUI.verifyElementPresent(findTestObject('Object Repository/11-Emision Chequera/02-BCCL.CQ.CONS.ANUL/lblConsulta de Pedidos'), 6)
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/02-BCCL.CQ.CONS.ANUL/lnkEjecutar'))

WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/02-BCCL.CQ.CONS.ANUL/lblNODEPEDIDO'), 6)
WebUI.verifyElementPresent(findTestObject('Object Repository/11-Emision Chequera/02-BCCL.CQ.CONS.ANUL/lblNODEPEDIDO'), 6)
WebUI.maximizeWindow()
WebUI.takeScreenshot('Screenshot/Emision Chequera/Emision Chequera.Invocación del ENQ BCCL.CQ.CONS.ANUL con datos válidos para generar autorizaciones' + nowString + '.png')

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
