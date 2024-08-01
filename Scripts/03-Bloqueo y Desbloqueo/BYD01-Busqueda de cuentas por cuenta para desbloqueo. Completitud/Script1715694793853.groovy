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
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

//Configuracion del ambiente y login
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,19), findTestData('MainData/Users').getValue(2,19))
WebUI.maximizeWindow()

def cuenta = '00760481318'
def menuDesplegable0 = ["Cuentas", "Modificacion de cuenta", "Bloqueo y Desbloqueo", "Desbloqueo" ]
def link0 = "Seleccionando Cuenta"

//Si el menu que busco está en dashboard uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable0, link0)

//Switch a la ventana de Bloqueos por Cuenta
WebUI.switchToWindowTitle('BCCL.E.AC.DESBLO.POR.CTA')
WebUI.maximizeWindow()

//Busco la Cuenta a Desbloquear
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Numero Cuenta', cuenta)
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

try {
	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.POR.CTA/lblCuenta'))
	CuentaID = WebUI.getText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.POR.CTA/lblCuenta'))
	assert CuentaID == cuenta
	
}catch(Exception e) {
	WebUI.closeWindowTitle('BCCL.E.AC.DESBLO.POR.CTA')
	WebUI.switchToWindowIndex(0)
	def menuDesplegable1 = ["Desbloqueo", "Bloqueo"]	
	CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable1, link0)
	WebUI.switchToWindowTitle('BCCL.E.AC.BLO.POR.CTA')
	//Busco la Cuenta a bloquear
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
	CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cuenta', cuenta)
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
	//Hacemos un bloqueo general
	WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.CTA/lnkBloqueoGeneral'))	
	
	WebUI.switchToWindowTitle("CUENTAS")
	
	WebUI.setText(findTestObject('04-Bloqueo y Desbloqueo/CUENTAS/txtTipobloqueo'), '1')	
	WebUI.setText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/CUENTAS/txtMotivo'), 'AF')
	WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))	
	WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))	
	WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
	
	WebUI.closeWindowTitle("CUENTAS")
	WebUI.switchToWindowIndex(0)
	
	def menuDesplegable2 = ["Bloqueo", "Desbloqueo"]
	CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable1, link0)
	
	WebUI.switchToWindowTitle('BCCL.E.AC.DESBLO.POR.CTA')
	WebUI.maximizeWindow()
	
	//Busco la Cuenta a Desbloquear
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
	CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Numero Cuenta', cuenta)
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
	
	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.POR.CTA/lblCuenta'))
	CuentaID = WebUI.getText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.POR.CTA/lblCuenta'))
	assert CuentaID == cuenta	
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


