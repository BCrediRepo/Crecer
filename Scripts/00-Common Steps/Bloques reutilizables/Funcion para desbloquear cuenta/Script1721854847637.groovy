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

//Funcion para desbloquear cuenta por persona
def desbloqueoPersona(String TipoBloq, String persona){
	String link = "Seleccionando Persona"
	if (TipoBloq == 'BLOQUEO PARCIAL') {
		WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.POR.CTA/lnkDesbloqueoParcial'))
		WebUI.switchToWindowTitle('BCCL.E.AC.DESBLO.PAR')
		WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.PAR/lnkDesbloqueoParcial'))
		WebUI.switchToWindowTitle('LOCKED EVENTS')
		WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnReversar'))
		WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
		
	}else {
		WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.POR.CTA/lnkDesbloqueoGeneral'))
		WebUI.switchToWindowTitle('CUENTAS')
		WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
		WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
		
	}
	WebUI.closeWindowIndex(1)
	WebUI.switchToWindowIndex(0)
	CustomKeywords.'pkgModules.kywBusquedaMenu.clickearEnLinkDashboard'(link)
	WebUI.switchToWindowTitle('BCCL.E.AC.BLO.POR.PER')
	//Seteo de Datos "Id. Persona"
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
	CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Id Persona', persona)
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
	TipoBloq = WebUI.getText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.PER/lblTipoBloqueValor'))
	
	if (TipoBloq == '') {
		return true
	}else {
		return false
	}
}


//Funcion para desbloquear cuenta por persona
def desbloqueoCuenta(String TipoBloq, String cuenta){
	String link = "Seleccionando Cuenta"
	if (TipoBloq == 'BLOQUEO PARCIAL') {
		WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.POR.CTA/lnkDesbloqueoParcial'))
		WebUI.switchToWindowTitle('BCCL.E.AC.DESBLO.PAR')
		WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.PAR/lnkDesbloqueoParcial'))
		WebUI.switchToWindowTitle('LOCKED EVENTS')
		WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnReversar'))
		WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
		
	}else {
		WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/BCCL.E.AC.DESBLO.POR.CTA/lnkDesbloqueoGeneral'))
		WebUI.switchToWindowTitle('CUENTAS')
		WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
		WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
		
	}
	WebUI.closeWindowIndex(1)
	WebUI.switchToWindowIndex(0)
	CustomKeywords.'pkgModules.kywBusquedaMenu.clickearEnLinkDashboard'(link)
	WebUI.switchToWindowTitle('BCCL.E.AC.BLO.POR.CTA')
	//Seteo de Datos "Id. Persona"
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
	CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cuenta', cuenta)
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
	TipoBloq = WebUI.getText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.PER/lblTipoBloqueValor'))
	
	if (TipoBloq == '') {
		return true
	}else {
		return false
	}
}