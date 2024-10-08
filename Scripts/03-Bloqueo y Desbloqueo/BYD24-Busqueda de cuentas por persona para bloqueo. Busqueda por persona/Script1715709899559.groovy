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
import groovy.inspect.swingui.BytecodeCollector as BytecodeCollector
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import org.jsoup.Jsoup as Jsoup
import org.jsoup.nodes.Document as Document
import org.openqa.selenium.support.ui.Select as Select
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent

//Funcion para desbloquear cuenta
def desbloqueo(String TipoBloq, String cuenta){	
	
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
	def menuDesplegableFuncion = ["Desbloqueo", "Bloqueo"]
	def linkFuncion = "Seleccionando Cuenta"
	CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegableFuncion, linkFuncion)
	WebUI.switchToWindowTitle('BCCL.E.AC.BLO.POR.PER')
	//Seteo de Datos "Id. Persona"
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
	CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Id Persona', cuenta)
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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 42), findTestData('MainData/Users').getValue(
        2, 42))

def menuDesplegable0 = ["Cuentas", "Modificacion de cuenta", "Bloqueo y Desbloqueo", "Bloqueo" ]
def link0 = "Seleccionando Persona"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable0, link0)

def persona = '1003747667'

WebUI.switchToWindowTitle('BCCL.E.AC.BLO.POR.PER')

//Seteo de Datos "Id. Persona"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Id Persona', persona)

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

try {
    WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.PER/lblTipoBloqueValor'))

    TipoBloq = WebUI.getText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.PER/lblTipoBloqueValor'))

    if (TipoBloq == '') {
        WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.PER/lblTipoBloqueValor'))
		TipoBloq = WebUI.getText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.PER/lblTipoBloqueValor'))
		assert TipoBloq.contains("")
    } else {
        throw new RuntimeException('Condicion no cumplida, forzando el catch')
    }
}
catch (Exception e) {  

	def encontrado = false
	while(!encontrado) {
			WebUI.closeWindowIndex(1)
		
			WebUI.switchToWindowIndex(0)			
			def menuDesplegable1 = ["Desbloqueo", "Bloqueo"]
			CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable1, link0)
			WebUI.click(findTestObject('02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/02-Desbloqueo/lnkSeleccionandoPersona'))
		
			WebUI.switchToWindowTitle('BCCL.E.AC.DESBLO.POR.PER')
		
			//Seteo de Datos "Id. Persona"
			WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
		
			CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Id Firmante', persona)
		
			WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
		
			WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.PER/lblTipoBloqueValor'))
		
			TipoBloq = WebUI.getText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.PER/lblTipoBloqueValor'))
			encontrado = desbloqueo(TipoBloq, persona)		
	}	
	WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.PER/lblTipoBloqueValor'))
    TipoBloq = WebUI.getText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.E.AC.BLO.POR.PER/lblTipoBloqueValor'))
	assert TipoBloq.contains("")
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

