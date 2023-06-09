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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,19), findTestData('MainData/Users').getValue(2,19))
WebUI.maximizeWindow()

//Accedo al menu de Bloqueo y Desbloqueo
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lnkCuentas'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkModificaciondDeCuenta'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkModificaciondDeCuenta'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/lnkBloqueoyDesbloqueo'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/lnkBloqueoyDesbloqueo'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/lnkDesbloqueo'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/lnkDesbloqueo'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/02-Desbloqueo/lnkSeleccionandoCuenta'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/02-Desbloqueo/lnkSeleccionandoCuenta'))

//Switch a la ventana de Bloqueos por Cuenta
WebUI.switchToWindowTitle('BCCL.E.AC.DESBLO.POR.CTA')
WebUI.maximizeWindow()

//Busco la Cuenta a Desbloquear
WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/txtCuenta'), 6)
WebUI.setText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/txtCuenta'), '00760481318')
WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/btnEjecutar'), 6)
WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/btnEjecutar'))

boolean isVisible = false
try {
	isVisible = WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/lblCuentaADesbloq'))
	//Verifico que sea la cuenta que se muestra y procedemos al bloqueo General
	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/lblCuentaADesbloq'), 6)
	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/lblCuentaADesbloq'))
	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/lnkDesbloqueoGeneral'), 6)
	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/lnkDesbloqueoGeneral'))
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
} catch (Exception e) {
	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/lblNOSEENCONTRARONREGISTROS'), 6)
	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/lblNOSEENCONTRARONREGISTROS'))
	WebUI.switchToWindowIndex(0)
	WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/lnkBloqueo'), 6)
	WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/lnkBloqueo'))
	WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/01-Bloqueo/lnkSeleccionandoCuenta'), 6)
	WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/01-Bloqueo/lnkSeleccionandoCuenta'))
	
	//Switch a la ventana de Bloqueos por Cuenta
	WebUI.switchToWindowTitle('BCCL.E.AC.BLO.POR.CTA')
	WebUI.maximizeWindow()
	
	//Busco la Cuenta a Bloquear
	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/txtCuenta'), 6)
	WebUI.setText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/txtCuenta'), '00760481318')
	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/btnEjecutar'), 6)
	WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/btnEjecutar'))
	
	//Verifico que sea la cuenta que se muestra y procedemos al bloqueo General
	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lblCuenta'), 6)
	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lblCuenta'))
	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lnkBloqueoGeneral'), 6)
	WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lnkBloqueoGeneral'))
	
	//Switch a la ventana de CUENTAS
	WebUI.switchToWindowTitle('CUENTAS')
	WebUI.maximizeWindow()
	
	//Completo el registro de bloqueo y acepto el registro
	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/txtTipoBloqueo'), 6)
	WebUI.setText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/txtTipoBloqueo'), '1')
	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/txtMotivo'), 6)
	WebUI.setText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/txtMotivo'), 'AF')
	WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/btnAceptarRegistro'))
	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lnkAceptarAlertas'), 6)
	WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lnkAceptarAlertas'))
	
	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lblTxnCompleta'), 6)
	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lblTxnCompleta'))
	def element = WebUI.getText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lblTxnCompleta'))
	assert element.contains('Txn Completa:')
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
	//-----------------------------------------------------------------
	WebUI.switchToWindowIndex(0)
	WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/02-Desbloqueo/lnkSeleccionandoCuenta'), 6)
	WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/02-Desbloqueo/lnkSeleccionandoCuenta'))
	
	//Switch a la ventana de Bloqueos por Cuenta
	WebUI.switchToWindowTitle('BCCL.E.AC.DESBLO.POR.CTA')
	WebUI.maximizeWindow()
	
	//Busco la Cuenta a Desbloquear
	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/txtCuenta'), 6)
	WebUI.setText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/txtCuenta'), '00760481318')
	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/btnEjecutar'), 6)
	WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/btnEjecutar'))
	
	//Verifico que sea la cuenta que se muestra y procedemos al bloqueo General
	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/lblCuentaADesbloq'), 6)
	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/lblCuentaADesbloq'))
	WebUI.waitForElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/lnkDesbloqueoGeneral'), 6)
	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/lnkDesbloqueoGeneral'))
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
	isVisible = false
}


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

