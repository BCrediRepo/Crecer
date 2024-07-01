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
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date

//Funcion para desbloquear cuenta
def desbloqueo(String TipoBloq){
	
	if (TipoBloq == 'BLOQUEO PARCIAL') {
		WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.POR.CTA/lnkDesbloqueoParcial'))
		WebUI.switchToWindowTitle('BCCL.E.AC.DESBLO.PAR')
		WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.PAR/lnkDesbloqueoParcial'))
		WebUI.switchToWindowTitle('LOCKED EVENTS')
		WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/btnReversarRegistro'))
		WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/LOCKED EVENTS/lnkAceptarAlertas'))
		
	}else {
		WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.POR.CTA/lnkDesbloqueoGeneral'))
		WebUI.switchToWindowTitle('CUENTAS')
		WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/LOCKED EVENTS/btnAceptarRegistro'))
		WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/LOCKED EVENTS/lnkAceptarAlertas'))
		
	}
	WebUI.closeWindowIndex(1)
	WebUI.switchToWindowIndex(0)
	WebUI.click(findTestObject('02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/01-Bloqueo/lnkSeleccionandoCuenta'))
	WebUI.switchToWindowTitle('BCCL.E.AC.BLO.POR.CTA')
	//Seteo de Datos "Id. Persona"
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
	CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cuenta', '13220021806') 
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
	//WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.PER/lblTipoBloqueValor'))
	TipoBloq = WebUI.getText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.PER/lblTipoBloqueValor'))
	
	if (TipoBloq == '') {
		return true
	}else {
		return false
	}
}

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 58), findTestData('MainData/Users').getValue(
        2, 58))

WebUI.click(findTestObject('02-Dashboard/lnkCuentas'))

WebUI.click(findTestObject('02-Dashboard/37-Cuentas/lnkModificaciondDeCuenta'))

WebUI.click(findTestObject('02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/lnkBloqueoyDesbloqueo'))

WebUI.click(findTestObject('02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/lnkBloqueo'))
WebUI.click(findTestObject('02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/lnkDesbloqueo'))
WebUI.click(findTestObject('02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/01-Bloqueo/lnkSeleccionandoCuenta'))

WebUI.switchToWindowTitle('BCCL.E.AC.BLO.POR.CTA')

//Seteo de Datos "Cuenta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cuenta', '13220021806')

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

try {
	WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.CTA/lnkBloqueoGeneral'))
	
	WebUI.setText(findTestObject('04-Bloqueo y Desbloqueo/CUENTAS/txtTipobloqueo'), '1')
	
	WebUI.setText(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/txtMotivo'), 'AF')
	
	WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/btnAceptarRegistro'))
	
	WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/lnkAceptarAlertas'))
	
	WebUI.verifyElementVisible(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/lblTxnCompleta'))
	
	txn = WebUI.getText(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/lblTxnCompleta'))
	
	assert txn.contains('Txn Completa: ')
	
	WebUI.closeWindowIndex(1)
	
	WebUI.switchToWindowIndex(0)	
	
	WebUI.click(findTestObject('02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/02-Desbloqueo/lnkSeleccionandoCuenta'))
	
	WebUI.switchToWindowTitle('BCCL.E.AC.DESBLO.POR.CTA')
	
	WebUI.setText(findTestObject('04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.POR.CTA/txtCuentaID'), '13220021806')
	
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
	
	bloqueo = WebUI.getText(findTestObject('04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.POR.CTA/lblTipoBloqueo'))
	
	assert bloqueo == "BLOQUEO CREDITOS"
	
	WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.POR.CTA/lnkDesbloqueoGeneral'))
	
	WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/btnAceptarRegistro'))
	
	WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/CUENTAS/lnkAceptarAlertas'))
	
	txn1 = WebUI.getText(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/lblTxnCompleta'))
	
	assert txn1.contains('Txn Completa: ')
}catch(Exception e) {
	def encontrado = false
	while(!encontrado) {
		WebUI.closeWindowIndex(1)
	
		WebUI.switchToWindowIndex(0)
	
		WebUI.click(findTestObject('02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/02-Desbloqueo/lnkSeleccionandoCuenta'))
	
		WebUI.switchToWindowTitle('BCCL.E.AC.DESBLO.POR.CTA')
	
		//Seteo de Datos "Id. Persona"
		WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
	
		CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Numero Cuenta', '13220021806')
	
		WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
	
		WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.PER/lblTipoBloqueValor'))
	
		TipoBloq = WebUI.getText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.PER/lblTipoBloqueValor'))
		encontrado = desbloqueo(TipoBloq)
	}
	WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.CTA/lnkBloqueoGeneral'))
	
	WebUI.setText(findTestObject('04-Bloqueo y Desbloqueo/CUENTAS/txtTipobloqueo'), '1')
	
	WebUI.setText(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/txtMotivo'), 'AF')
	
	WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/btnAceptarRegistro'))
	
	WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/lnkAceptarAlertas'))
	
	WebUI.verifyElementVisible(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/lblTxnCompleta'))
	
	txn = WebUI.getText(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/lblTxnCompleta'))
	
	assert txn.contains('Txn Completa: ')
	
	WebUI.closeWindowIndex(1)
	
	WebUI.switchToWindowIndex(0)
	
	WebUI.click(findTestObject('02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/lnkDesbloqueo'))
	
	WebUI.click(findTestObject('02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/02-Desbloqueo/lnkSeleccionandoCuenta'))
	
	WebUI.switchToWindowTitle('BCCL.E.AC.BLO.POR.CTA')
	
	WebUI.setText(findTestObject('04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.POR.CTA/txtCuentaID'), '13220021806')
	
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
	
	bloqueo = WebUI.getText(findTestObject('04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.POR.CTA/lblTipoBloqueo'))
	
	assert bloqueo == "BLOQUEO CREDITOS"
	
	WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.POR.CTA/lnkDesbloqueoGeneral'))
	
	WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/btnAceptarRegistro'))
	
	WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/CUENTAS/lnkAceptarAlertas'))
	
	txn1 = WebUI.getText(findTestObject('04-Bloqueo y Desbloqueo/LOCKED EVENTS/lblTxnCompleta'))
	
	assert txn1.contains('Txn Completa: ')
}

 //Control fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

