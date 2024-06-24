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


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ejecuta en la linea de comando ENQ BCCL.E.FIRMAS.CUENTA
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.FIRMAS.FISICA')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana BCCL.E.FIRMAS.FISICA
WebUI.switchToWindowTitle('BCCL.E.FIRMAS.FISICA')

//Maximiza la pantalla
WebUI.maximizeWindow()

//Seteo de datos "ID Persona Fisica"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/BCCL.E.FIRMAS.FISICA/txtIdPersonaFisica'), 6)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('ID Persona Fisica', '1000096115')

//Selecciona boton EJECUTAR
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/BCCL.E.FIRMAS.FISICA/lblFirma'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/BCCL.E.FIRMAS.FISICA/lblFirma'))

def element = WebUI.getText(findTestObject('Object Repository/39-Cuentas/BCCL.E.FIRMAS.FISICA/lblFirma'))

assert element.contains('Firma')


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

