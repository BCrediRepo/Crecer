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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Ingreso al menu ?5
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?5')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

// Cambia a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

// Maximizamos
WebUI.maximizeWindow()

WebUI.click(findTestObject('null'))
WebUI.click(findTestObject('null'))

// Cambia a la ventana BCCL.E.CTA.MOD.DENOM
WebUI.switchToWindowTitle('BCCL.E.CTA.MOD.DENOM')

// Maximizamos
WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/39-Cuentas/BCCL.E.CTA.MOD.DENOM/lnkNueva Seleccion'))

//Ingresamos Nro de cuenta valido
WebUI.setText(findTestObject('Object Repository/39-Cuentas/BCCL.E.CTA.MOD.DENOM/txtNroDeCuenta'), '00890010860')
WebUI.click(findTestObject('Object Repository/39-Cuentas/BCCL.E.CTA.MOD.DENOM/lnkEjecutar'))

//Clickeamos en modificar denominacion
WebUI.click(findTestObject('Object Repository/39-Cuentas/BCCL.E.CTA.MOD.DENOM/lnkModificar Denominacion'))

//Cambia a la ventana CUENTAS
WebUI.switchToWindowTitle('CUENTAS')

//Modificamos los nombres
WebUI.setText(findTestObject('Object Repository/39-Cuentas/CUENTAS/txtNombre1'), 'RETIRO')

WebUI.setText(findTestObject('Object Repository/39-Cuentas/CUENTAS/txtNombreCorto'), 'RETIRO')

//Validamos el registro
WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS/btnValidarRegistro'))

//Aceptamos el registro
WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS/btnAceptarRegistro'))

//Verificamos que se realizo la txn
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/CUENTAS/lblTxnCompleta'), 6)

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








