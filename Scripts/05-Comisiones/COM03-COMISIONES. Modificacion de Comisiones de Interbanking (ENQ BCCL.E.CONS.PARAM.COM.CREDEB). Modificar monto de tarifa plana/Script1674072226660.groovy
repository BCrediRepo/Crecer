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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 7), findTestData('MainData/Users').getValue(2, 7))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CONS.PARAM.COM.CREDEB')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('CONSULTA PARAMETRIA COMISIONES')

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Tipo comision', 'CREIB')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Abonado', 'A00243A')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.click(findTestObject('06-Comisiones/CONSULTA PARAMETRIA COMISIONES/lnkEjecutar'))

WebUI.maximizeWindow()

monto = WebUI.getText(findTestObject('06-Comisiones/CONSULTA PARAMETRIA COMISIONES/lblMonto'))

WebUI.click(findTestObject('06-Comisiones/CONSULTA PARAMETRIA COMISIONES/lnkModificarComision'))

WebUI.switchToWindowTitle('FT.COMMISSION.TYPE')

def desaparecio = 0

if (monto == '10,00') {
    WebUI.setText(findTestObject('06-Comisiones/FT.COMMISSION.TYPE/txtMontoFijo'), '20,00')
} else {
    WebUI.setText(findTestObject('06-Comisiones/FT.COMMISSION.TYPE/txtMontoFijo'), '10,00')

    desaparecio = 1
}

WebUI.setText(findTestObject('06-Comisiones/FT.COMMISSION.TYPE/txtFechaVigencia'), GlobalVariable.vFechaCOB)

WebUI.click(findTestObject('06-Comisiones/FT.COMMISSION.TYPE/btnAceptarRegistro'))
// Verifica si el elemento está presente
if (WebUI.verifyElementPresent(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lnkAceptarAlertas'), 5, FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/39-Cuentas/BCCL.RES.CTA.PEDIDO/lnkAceptarAlertas'))
}

// Valido txn completa
def TxnInicial = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert TxnInicial.contains('Txn Completa')
// Usa split para dividir la cadena en el asterisco y obtener la parte que sigue
def creibConId = TxnInicial.split('\\*')[1].trim()
// Extrae los tres números después del asterisco
def id = creibConId.substring(0, 3)
println(id)

//Log out
WebUI.switchToWindowIndex(0)
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnLogout'))
WebUI.closeBrowser()

//Login gerente operativo
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 8), findTestData('MainData/Users').getValue(
        2, 8))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Autorizo comisiones pendientes
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.COM.FTC.AUTH')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Comisiones Pendientes de Autorizar')

//ACA DEBERIA INGRESAR ID
//Limpia
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('ID', id)

WebUI.click(findTestObject('06-Comisiones/Comisiones Pendientes de Autorizar/lnkEjecutar'))

WebUI.click(findTestObject('06-Comisiones/Comisiones Pendientes de Autorizar/btnAutorizar'))

WebUI.switchToWindowTitle('FT.COMMISSION.TYPE')

WebUI.click(findTestObject('06-Comisiones/FT.COMMISSION.TYPE/btnAutorizar'))

WebUI.closeBrowser()

//Login y Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 7), findTestData('MainData/Users').getValue(
        2, 7))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CONS.PARAM.COM.CREDEB')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('CONSULTA PARAMETRIA COMISIONES')

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Tipo comision', 'CREIB')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Abonado', 'A00243A')

WebUI.click(findTestObject('06-Comisiones/CONSULTA PARAMETRIA COMISIONES/lnkEjecutar'))

WebUI.maximizeWindow()

nuevoMonto = WebUI.getText(findTestObject('06-Comisiones/CONSULTA PARAMETRIA COMISIONES/lblMonto'))

assert monto != nuevoMonto 

//---------------------------------------------------------------------------------------------------------------------
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

