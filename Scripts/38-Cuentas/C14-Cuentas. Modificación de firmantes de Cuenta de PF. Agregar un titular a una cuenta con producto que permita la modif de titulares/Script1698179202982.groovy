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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 72), findTestData('MainData/Users').getValue(2, 72))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresamos el menu ?302 en el command line
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?302')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

//Maximizamos
WebUI.maximizeWindow()

//Click en cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/01-Temenos T24/spanCuentas'))

//Click en modificacion de cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/01-Temenos T24/spanModificacion de Cuenta'))

//Click en modificacion y baja de firmantes
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/Baja Logica PF - Temenos T24/lnkModificacion y Baja de Firmantes'))

//Switch a la ventana Firmas
WebUI.switchToWindowTitle('Firmas')

//Maximizamos
WebUI.maximizeWindow()

//Ingresamos la cuenta
//WebUI.setText(findTestObject('Object Repository/39-Cuentas/Firmas/txtNroCuenta'), '00890093805')
WebUI.setText(findTestObject('Object Repository/39-Cuentas/Firmas/txtNroCuenta'), '10220064365')

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/39-Cuentas/Firmas/lnkEjecutar'))

//Click en modificacion de firmas
WebUI.click(findTestObject('Object Repository/39-Cuentas/Firmas/lnkAlta-Modificacion de Firmas'))

//Switch a la ventana CUENTAS
WebUI.switchToWindowTitle('CUENTAS')

//Click en expandir subvalor
WebUI.click(findTestObject('Object Repository/39-Cuentas/Modificacion de Firmantes de CTA/btnExpandirSubvalor'))

//Agregamos el codigo de firmante
//WebUI.setText(findTestObject('Object Repository/39-Cuentas/Modificacion de Firmantes de CTA/txtCodigo de Firmante-Socio.2'), '1000607997')
WebUI.setText(findTestObject('Object Repository/39-Cuentas/Modificacion de Firmantes de CTA/txtCodigo de Firmante-Socio.2'), '1000755088')
//WebUI.delay(3)

//Click en tipo de relacion
WebUI.click(findTestObject('Object Repository/39-Cuentas/Modificacion de Firmantes de CTA/txtTipoRelacion'))

WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Modificacion de Firmantes de CTA/txtTipoRelacion'), 6)

WebUI.setText(findTestObject('Object Repository/39-Cuentas/Modificacion de Firmantes de CTA/txtTipoRelacion'), '1')

//Click en Forma de operar.3
WebUI.click(findTestObject('Object Repository/39-Cuentas/Modificacion de Firmantes de CTA/txtFormaDeOperar'))

WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Modificacion de Firmantes de CTA/txtFormaDeOperar'), 6)

WebUI.setText(findTestObject('Object Repository/39-Cuentas/Modificacion de Firmantes de CTA/txtFormaDeOperar'), '00')

//Validamos el registro
WebUI.click(findTestObject('Object Repository/39-Cuentas/Modificacion de Firmantes de CTA/btnValidarRegistro'))

//Aceptamos el registro
WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS/btnAceptarRegistro'))

//Aceptamos Alertas
WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS/lnkAceptarAlertas'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/CUENTAS/lblTRANSACCION FINALIZADA'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/CUENTAS/lblTRANSACCION FINALIZADA'))

def element = WebUI.getText(findTestObject('Object Repository/39-Cuentas/CUENTAS/lblTRANSACCION FINALIZADA'))
assert element.contains('TRANSACCION FINALIZADA')

WebUI.closeBrowser()

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 72), findTestData('MainData/Users').getValue(2, 72))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresamos ACCOUNT, en el command line
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ACCOUNT,')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana CUENTAS
WebUI.switchToWindowTitle('CUENTAS')

//Ingresamos la cuenta
WebUI.setText(findTestObject('Object Repository/39-Cuentas/CUENTAS/txtACCOUNT_transactionId'), '10220064365')

//Click en Modificar Registro
WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS/btnModificaRegistro'))

//Eliminamos el nuevo titular
WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS/btnDeleteValue'))

//Click en Validar Registro
WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS/btnValidarRegistro'))

//Click en Aceptar Registro
WebUI.click(findTestObject('Object Repository/39-Cuentas/CUENTAS/btnAceptarRegistro'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/CUENTAS/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/CUENTAS/lblTxnCompleta'))

def element2 = WebUI.getText(findTestObject('Object Repository/39-Cuentas/CUENTAS/lblTxnCompleta'))
assert element2.contains('Txn Completa')

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

