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
CustomKeywords.'pkgModules.kywGeneric.Login'(GlobalVariable.vTest10_IP, GlobalVariable.vTest10Name, GlobalVariable.vF00289, GlobalVariable.vPass)

//Se accede al menu Plazo Fijo
WebUI.maximizeWindow()

WebUI.waitForElementVisible(('Object Repository/02-Dashboard/lnkPlazoFijo'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkPlazoFijo'))

WebUI.waitForElementPresent(findTestObject('Object Repository/02-Dashboard/3-PlazoFijo/lnkImpresiondeCertificadosyFormularios'), 3)

WebUI.click(findTestObject('Object Repository/02-Dashboard/3-PlazoFijo/lnkImpresiondeCertificadosyFormularios'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/3-PlazoFijo/01-Impresion de Certificados y Formularios/lnkLiquidaciondePlazoFijoAjustable'))

//WebUI.delay(3)
//Switch to other frame
WebUI.switchToWindowTitle(findTestData('Modulos/Modulos').getValue(4,4))

WebUI.waitForElementPresent(findTestObject('Object Repository/05-PlazoFijo/01-LiquidacionPlazoFijoAjustable/txtSucursal'), 3)
WebUI.click(findTestObject('Object Repository/05-PlazoFijo/01-LiquidacionPlazoFijoAjustable/txtSucursal'))

WebUI.setText(findTestObject('Object Repository/05-PlazoFijo/01-LiquidacionPlazoFijoAjustable/txtSucursal'),findTestData('MainData/Users').getValue(3,2))

WebUI.click(findTestObject('Object Repository/05-PlazoFijo/01-LiquidacionPlazoFijoAjustable/btnEjecutar'))

WebUI.verifyElementVisible(findTestObject('Object Repository/05-PlazoFijo/01-LiquidacionPlazoFijoAjustable/lblSucCertLiqUVA'))

WebUI.verifyElementVisible(findTestObject('Object Repository/05-PlazoFijo/01-LiquidacionPlazoFijoAjustable/lnkGenerarLiquidacion'))

WebUI.click(findTestObject('Object Repository/05-PlazoFijo/01-LiquidacionPlazoFijoAjustable/lnkGenerarLiquidacion'))

WebUI.verifyElementVisible(findTestObject('Object Repository/05-PlazoFijo/01-LiquidacionPlazoFijoAjustable/lblLiquidacionPlazoFijoAjustablexUVA'))

WebUI.verifyElementVisible(findTestObject('Object Repository/05-PlazoFijo/01-LiquidacionPlazoFijoAjustable/btnAceptarRegistro'))

WebUI.click(findTestObject('Object Repository/05-PlazoFijo/01-LiquidacionPlazoFijoAjustable/btnAceptarRegistro'))

WebUI.verifyElementVisible(findTestObject('Object Repository/05-PlazoFijo/01-LiquidacionPlazoFijoAjustable/lblTRANSACCION FINALIZADA'))

WebUI.takeScreenshot("Screenshot/Plazo Fijo/Plazo Fijo. Imprimir Liquidacion de Plazo Fijo Ajustable por UVA" + nowString + ".png")


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

