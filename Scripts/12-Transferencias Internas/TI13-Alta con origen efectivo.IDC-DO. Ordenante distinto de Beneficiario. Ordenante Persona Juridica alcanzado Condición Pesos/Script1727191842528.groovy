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

//Configuracion del ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()

def menuDesplegable = ["Transferencias Internas"]
def link = "Alta Transf. Interna Origen Efectivo"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowIndex(1)
WebUI.maximizeWindow()

//Completo los campos necesarios para el pase entre socios
WebUI.setText(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtSucursalDestino'), '074')
WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtIdOrdenante'))
WebUI.setText(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtIdOrdenante'), '2000514092')
WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtObservaciones'))
WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/btnDrillDownMotivo'))
WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/lblFAC'))
WebUI.setText(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtImporte'), '100')
WebUI.setText(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtIdBeneficiario'), '1000873562')
WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/01-Alta Transf. Interna Origen Efectivo/txtObservaciones'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Valido que se haya completado la transaccion
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert element.contains('Txn Completa:')

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
