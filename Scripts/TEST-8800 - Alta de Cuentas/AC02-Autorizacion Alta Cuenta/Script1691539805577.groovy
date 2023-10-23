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
import com.kms.katalon.core.testdata.TestDataFactory

//Test Case Name: Autorizacion de Alta de Cuenta

//Configuracion del ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))
WebUI.maximizeWindow()

//Ingresamos por el menu Autorizaciones
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lnkAutorizaciones'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkAutorizaciones'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/TEST-8800-Autorizaciones/lnkAutorizaciondeAltadeContratos'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/TEST-8800-Autorizaciones/lnkAutorizaciondeAltadeContratos'))

//Switch a la ventana de Autorizaciones
WebUI.switchToWindowTitle('Autorizacion - Fil.001 Centro')
WebUI.maximizeWindow()

//Ingresamos al link de autorizacion y completamos el alta
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAutorizaciondeContratos'), 6)
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAutorizaciondeContratos'))
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkVerPREALTA'), 6)
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkVerPREALTA'))

WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAutorizarProductoF3'), 6)
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAutorizarProductoF3'))

//Autorizamos el PRE ALTA y VALIDAMOS que la transaccion finalice correctamente
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnAutorizoPREALTAF3'), 6)
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnAutorizoPREALTAF3'))

WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblTRANSACCIONFINALIZADAF3'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblTRANSACCIONFINALIZADAF3'))
def element = WebUI.getText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblTRANSACCIONFINALIZADAF3'))
assert element.contains('TRANSACCION FINALIZADA')


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