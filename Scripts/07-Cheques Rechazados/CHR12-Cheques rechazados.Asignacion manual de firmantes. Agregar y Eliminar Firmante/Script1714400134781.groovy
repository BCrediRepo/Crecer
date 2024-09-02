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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 18), findTestData('MainData/Users').getValue(2, 18))
WebUI.maximizeWindow()

//Ingresar "ENQ BCCL.E.CQ.CHRECH.MODFIRM.INFORMADO" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CQ.CHRECH.MODFIRM.INFORMADO')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))
	
//Cambiar ventana "CAMBIO DE FIRMANTES INFORMADOS"
WebUI.switchToWindowTitle('CAMBIO DE FIRMANTES INFORMADOS')

//Maximizar pantalla
WebUI.maximizeWindow()

//Seteo de Datos "NUMERO DE CUENTA", "NUMERO CHEQUE"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('NUMERO DE CUENTA', '02180086531')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('NUMERO CHEQUE', '10014')

//Seleccionar "boton Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Cambio De Firmantes Informados"
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Cambio De Firmantes Informados/lnkModificarFirmantes'))

//Cambiar ventana "BCCL.CQ.CHRECH.REP.BCRA"
WebUI.switchToWindowTitle('BCCL.CQ.CHRECH.REP.BCRA')

//Definir Numero Documento 1
TestObject numeroDocumento = findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lblNumerodeDocumento1')

//Almacenar valorNumeroDocumento
String valorNumeroDocumento = WebUI.getText(numeroDocumento)

if (valorNumeroDocumento.equals('20105693614')) {
	//Seleccionar "Boton Añadir Firmante"
	WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/btnAniadirFirmante'))
	
	//Setear "Txt Firmante 2"
	WebUI.setText(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/txtIdFirmante2'), '1000413328')
	
	//Screenshot
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
	
	//Seleccionar "Aceptar el registro"
	WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/btnAceptarRegistro'))
	
	// Verifica si el elemento está presente
	if (WebUI.verifyElementPresent(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lnkAceptarAlertas'), 5, FailureHandling.OPTIONAL)) {
		WebUI.click(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lnkAceptarAlertas'))
	}
	
	//Verificar "Txn Completa"
	WebUI.verifyElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lblTxnCompleta'))
	
	//Validar "Txn Completa"
	def element = WebUI.getText(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lblTxnCompleta'))
	assert element.contains('Txn Completa')

	//Screenshot
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
		
} else {
//	//Seleccionar "Boton Añadir Firmante"
	WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/btnAniadirFirmante'))
	
	//Setear "Txt Firmante 2"
	WebUI.setText(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/txtIdFirmante2'), '1000275776')
	
	//Screenshot
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
	
	//Seleccionar "Aceptar el registro"
	WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/btnAceptarRegistro'))
	
	// Verifica si el elemento está presente
	if (WebUI.verifyElementPresent(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lnkAceptarAlertas'), 5, FailureHandling.OPTIONAL)) {
		WebUI.click(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lnkAceptarAlertas'))
	}
	
	//Verificar "Txn Completa"
	WebUI.verifyElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lblTxnCompleta'))
	
	//Validar "Txn Completa"
	def element2 = WebUI.getText(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lblTxnCompleta'))
	assert element2.contains('Txn Completa')

	//Screenshot
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
	
	}

//Cerrar Sesion
CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()

//Volver a Logearse con un nuevo usuario

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 38), findTestData('MainData/Users').getValue(2, 38))
WebUI.maximizeWindow()

//Ingresar "ENQ BCCL.E.CQ.CHRECH.MODFIRM.INFORMADO" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CQ.CHRECH.MODFIRM.INFORMADO')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))
	
//Cambiar ventana "CAMBIO DE FIRMANTES INFORMADOS"
WebUI.switchToWindowTitle('CAMBIO DE FIRMANTES INFORMADOS')

//Seteo de Datos "NUMERO DE CUENTA", "NUMERO CHEQUE"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('NUMERO DE CUENTA', '02180086531')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('NUMERO CHEQUE', '10014')

//Seleccionar "boton Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Cambio De Firmantes Informados"
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Cambio De Firmantes Informados/lnkModificarFirmantes'))

//Cambiar ventana "BCCL.CQ.CHRECH.REP.BCRA"
WebUI.switchToWindowTitle('BCCL.CQ.CHRECH.REP.BCRA')

if (valorNumeroDocumento.equals('20105693614')) {
	//Seleccionar "Boton Eliminar Firmante"
	WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/btnEliminarFirmante3'))
	
	//Screenshot
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
	
	//Seleccionar "Aceptar el registro"
	WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/btnAceptarRegistro'))
	
	// Verifica si el elemento está presente
	if (WebUI.verifyElementPresent(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lnkAceptarAlertas'), 5, FailureHandling.OPTIONAL)) {
		WebUI.click(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lnkAceptarAlertas'))
	}
	
	//Verificar "Txn Completa"
	WebUI.verifyElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lblTxnCompleta'))
	
	//Validar "Txn Completa"
	def element3 = WebUI.getText(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lblTxnCompleta'))
	assert element3.contains('Txn Completa')
	
} else {
	//Seleccionar "Boton Eliminar Firmante"
	WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/btnEliminarFirmante3'))
	
	//Screenshot
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
	
	//Seleccionar "Aceptar el registro"
	WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/btnAceptarRegistro'))
	
	// Verifica si el elemento está presente
	if (WebUI.verifyElementPresent(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lnkAceptarAlertas'), 5, FailureHandling.OPTIONAL)) {
		WebUI.click(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lnkAceptarAlertas'))
	}
	
	//Verificar "Txn Completa"
	WebUI.verifyElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lblTxnCompleta'))
	
	//Validar "Txn Completa"
	def element4 = WebUI.getText(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lblTxnCompleta'))
	assert element4.contains('Txn Completa')
	
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