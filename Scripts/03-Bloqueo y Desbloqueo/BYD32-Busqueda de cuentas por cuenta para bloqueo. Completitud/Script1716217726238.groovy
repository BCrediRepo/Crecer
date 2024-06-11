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

//Configuracion del ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,19), findTestData('MainData/Users').getValue(2,19))
WebUI.maximizeWindow()

//Seleccionar "Cuentas"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))

//Seleccionar "Modificación de Cuenta"
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkModificaciondDeCuenta'))

//Seleccionar "Bloqueo y Desbloqueo"
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/lnkBloqueoyDesbloqueo'))

//Seleccionar "Bloqueo"
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/lnkBloqueo'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Seleccionando Cuenta"
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/01-Bloqueo/lnkSeleccionandoCuenta'))

//Cambiar ventana "BCCL.E.AC.BLO.POR.CTA"
WebUI.switchToWindowTitle('BCCL.E.AC.BLO.POR.CTA')

//Seteo de Datos "Cuenta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cuenta', '00760481318')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Ejecutar"
WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/btnEjecutar'))

boolean isVisible = false
try {
	isVisible = WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lblCuenta'))
	//Verifico que sea la cuenta que se muestra y procedemos al bloqueo General
	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lblCuenta'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lnkBloqueoGeneral'))
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
} catch (Exception e) {
	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/lblNOSEENCONTRARONREGISTROS'))
	WebUI.switchToWindowIndex(0)
	WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/lnkDesbloqueo'))
	WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/02-Desbloqueo/lnkSeleccionandoCuenta'))
	
	//Switch a la ventana de Desbloqueos por Cuenta
	WebUI.switchToWindowTitle('BCCL.E.AC.DESBLO.POR.CTA')
	
	//Maximizar Ventana
	WebUI.maximizeWindow()
	
	//Busco la Cuenta a Desbloquear
	WebUI.setText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/txtCuenta'), '00760481318')
	WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/btnEjecutar'))
	
	//Verifico que sea la cuenta que se muestra y procedemos al Desbloqueo General
	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/lblCuentaADesbloq'))
	WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/lnkDesbloqueoGeneral'))
	
	//Switch a la ventana de CUENTAS
	WebUI.switchToWindowTitle('CUENTAS')
	
	//Maximizar Ventana
	WebUI.maximizeWindow()
	
	//Completo el registro de desbloqueo y acepto el registro
	WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/btnAceptarRegistro'))
	WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/02-Desbloqueo seleccionando Cuenta/lnkAceptarAlertas'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lblTxnCompleta'))
	def element = WebUI.getText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lblTxnCompleta'))
	assert element.contains('Txn Completa:')
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
	
	//Cambiar a la ventana del Dashboard
	WebUI.switchToWindowIndex(0)
	
	//Seleccionar "Seleccionando Cuenta"
	WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/01-Bloqueo/lnkSeleccionandoCuenta'))
	
	//Switch a la ventana de Bloqueos por Cuenta
	WebUI.switchToWindowTitle('BCCL.E.AC.BLO.POR.CTA')
	
	//Maximizar Ventana
	WebUI.maximizeWindow()
	
	//Busco la Cuenta a Bloquear
	WebUI.setText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/txtCuenta'), '00760481318')
	WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/btnEjecutar'))
	
	//Verifico que sea la cuenta que se muestra y verificamos
	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lblCuenta'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/01-Bloqueo seleccionando Cuenta/lnkBloqueoGeneral'))
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
	isVisible = false
}

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}