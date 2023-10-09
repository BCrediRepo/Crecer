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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,17), findTestData('MainData/Users').getValue(2,17))
WebUI.maximizeWindow()

//Selecciona Deposito
WebUI.click(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/Fil.089 M.del Plata Ctr/btnDepositos'))

//Selecciona Deposito en Ventanilla
WebUI.click(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/Fil.089 M.del Plata Ctr/btnDepositoenVentanilla'))

//Abre la pestaña TELLER
WebUI.switchToWindowTitle('TELLER')

//Verifica titulo Deposito De Efectivo En Ventanilla
WebUI.waitForElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/lblTituloDepositoDeEfectivoEnVentanilla'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/lblTituloDepositoDeEfectivoEnVentanilla'))

//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Selecciona tipo boton dropdown Tipo Identif.(CUIL-CUIT-CDI)
WebUI.click(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/rbDropdownCUIL-CUIT-CDI'))

//Ingresa Tipo Identif.(CUIL-CUIT-CDI)
WebUI.waitForElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtTipoID(CUIL-CUIT-CDI)'), 6)
WebUI.setText(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/txtTipoID(CUIL-CUIT-CDI)'),'T')

//Click en boton validar
WebUI.waitForElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/btnValidar'),6)
WebUI.click(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/btnValidar'))

//Espera y Verifica tipo DNI depositante
WebUI.waitForElementVisible(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/lblTCUIT'),6)
def element = WebUI.getText(findTestObject('Object Repository/48-Deposito en Efectivo Por Caja/TELLER/lblTCUIT'))
assert element.contains('CUIT')


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