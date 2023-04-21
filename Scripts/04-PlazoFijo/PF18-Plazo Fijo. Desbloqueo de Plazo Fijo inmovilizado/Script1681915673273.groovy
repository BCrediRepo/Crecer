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
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 7), findTestData('MainData/Users').getValue(
        2, 7))

WebUI.maximizeWindow()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?302')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('02-Dashboard/03-PlazoFijo/Temenos T24/lnkPlazoFijo1'))

WebUI.click(findTestObject('02-Dashboard/03-PlazoFijo/Temenos T24/Plazo Fijo/lnkModificacionDePlazoFijo'))

WebUI.click(findTestObject('02-Dashboard/03-PlazoFijo/Temenos T24/Plazo Fijo/Modificacion de plazo fijo/lnkBloqueoyDesbloqueo'))

WebUI.click(findTestObject('02-Dashboard/03-PlazoFijo/Temenos T24/Plazo Fijo/Modificacion de plazo fijo/Bloqueo y desbloqueo/lnkDesbloqueoPlazoFijoInmovilizado'))

WebUI.switchToWindowTitle('Desbloqueo Plazo Fijo Inmovilizado')

CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('02-Dashboard/03-PlazoFijo/Temenos T24/Plazo Fijo/Modificacion de plazo fijo/Bloqueo y desbloqueo/lnkDesbloqueoPlazoFijoInmovilizado'))

WebUI.switchToWindowTitle('Desbloqueo Plazo Fijo Inmovilizado')

WebUI.setText(findTestObject('05-PlazoFijo/Bloqueo Plazo Fijo Inmovilizado/txtNroOperacion_value111'), '0211579')

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebUI.click(findTestObject('05-PlazoFijo/Bloqueo Plazo Fijo Inmovilizado/lnkDesbloquearInmovilizado'))

WebUI.switchToWindowTitle('Plazo Fijo Inmovilizado')

WebUI.click(findTestObject('05-PlazoFijo/Plazo Fijo Inmovilizado/btnAceptarRegistro'))

WebUI.switchToWindowTitle('Plazo Fijo')

txPendiente = WebUI.verifyElementVisible(findTestObject('05-PlazoFijo/PLAZO FIJO/lblTXPENDIENTEDEAUTORIZACION'))

assert txPendiente == true

WebUI.closeBrowser()

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
        2, 9))

WebUI.click(findTestObject('02-Dashboard/lnkAutorizaciones'))

WebUI.click(findTestObject('02-Dashboard/lnkAutorizacionesPendientes'))

WebUI.switchToWindowTitle('BCCL.E.AUTHORIZATION')

WebUI.selectOptionByIndex(findTestObject('05-PlazoFijo/BCCL.E.AUTHORIZATION/cbxSeleccion'), 1)

WebUI.click(findTestObject('05-PlazoFijo/BCCL.E.AUTHORIZATION/btnSelect'))

WebUI.switchToWindowTitle('Plazo Fijo Inmovilizado')

WebUI.click(findTestObject('05-PlazoFijo/Plazo Fijo Inmovilizado/btnAutorizar'))

WebUI.switchToWindowTitle('Plazo Fijo')

txFinalizada = WebUI.verifyElementVisible(findTestObject('05-PlazoFijo/PLAZO FIJO/lblTXFINALIZADA'))

assert txFinalizada == true

WebUI.maximizeWindow()

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

