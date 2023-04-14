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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 8), findTestData('MainData/Users').getValue(2, 8))

// Accedo a menu ?MNU.BCCL.SP.D3.CC2
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscar'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscar'), "?MNU.BCCL.SP.D3.CC2")

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkInhabilitados'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/22-Inhabilitados/lnkConsulta y Modificacion de Inhabilitados prop'))

WebUI.switchToWindowTitle('BCCL.E.INHAB.PROPIOS')

//Aplico KYW de limpieza de busqueda
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowTitle('Temenos T24')
WebUI.click(findTestObject('Object Repository/02-Dashboard/22-Inhabilitados/lnkConsulta y Modificacion de Inhabilitados prop'))
WebUI.switchToWindowTitle('BCCL.E.INHAB.PROPIOS')

//Completo los campos de busqueda
//WebUI.setText(findTestObject('Object Repository/24-Inhabilitados/01-Consulta y Modificacion de Inhabilitados prop/txtCUITCUIL'), "20033555211")
//WebUI.setText(findTestObject('Object Repository/24-Inhabilitados/01-Consulta y Modificacion de Inhabilitados prop/txtIDPERSONA'), "1004020304")
WebUI.setText(findTestObject('Object Repository/24-Inhabilitados/01-Consulta y Modificacion de Inhabilitados prop/txtCUITCUIL'), "27177194064")
WebUI.setText(findTestObject('Object Repository/24-Inhabilitados/01-Consulta y Modificacion de Inhabilitados prop/txtIDPERSONA'), "1000779000")

WebUI.waitForElementVisible(findTestObject('Object Repository/24-Inhabilitados/01-Consulta y Modificacion de Inhabilitados prop/lnkEjecutar'), 6) 
WebUI.click(findTestObject('Object Repository/24-Inhabilitados/01-Consulta y Modificacion de Inhabilitados prop/lnkEjecutar'))

WebUI.waitForElementVisible(findTestObject('Object Repository/24-Inhabilitados/01-Consulta y Modificacion de Inhabilitados prop/lnkModificarElestado'), 6)
WebUI.click(findTestObject('Object Repository/24-Inhabilitados/01-Consulta y Modificacion de Inhabilitados prop/lnkModificarElestado'))

//INHABILITADOBAJA X CAUSA 51 O 52BAJA X ERROR CAUSA 50FINALIZADO X FILIALBAJA HISTORICANO INHAB X GOP
//WebUI.click(findTestObject('Object Repository/24-Inhabilitados/01-Consulta y Modificacion de Inhabilitados prop/btnValidarRegistro'))
WebUI.waitForElementVisible(findTestObject('Object Repository/24-Inhabilitados/01-Consulta y Modificacion de Inhabilitados prop/select_Estado'), 6)
Estado = WebUI.getText(findTestObject('Object Repository/24-Inhabilitados/01-Consulta y Modificacion de Inhabilitados prop/select_Estado'))
if (Estado.contains('BAJA HISTORICA')) {
	WebUI.selectOptionByIndex(findTestObject('Object Repository/24-Inhabilitados/01-Consulta y Modificacion de Inhabilitados prop/select_Estado'), 4)
}	else {
		WebUI.selectOptionByIndex(findTestObject('Object Repository/24-Inhabilitados/01-Consulta y Modificacion de Inhabilitados prop/select_Estado'), 5)
}
WebUI.click(findTestObject('Object Repository/24-Inhabilitados/01-Consulta y Modificacion de Inhabilitados prop/btnAceptarRegistro'))

//WebUI.takeScreenshot("Screenshot/Consulta y Modificación de Inhabilitados Propios. Cambio de Estado. Estado de inhabilitación válido." + nowString + ".png")


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












