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

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(GlobalVariable.vTest10_IP, GlobalVariable.vTest10Name, GlobalVariable.vF00474, 
    GlobalVariable.vPass)

WebUI.maximizeWindow()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CONS.PARAM.COM.CREDEB')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('CONSULTA PARAMETRIA COMISIONES')

WebUI.setText(findTestObject('06-Comisiones/CONSULTA PARAMETRIA COMISIONES/txt_value111_TipoComision'), 'CREIB')

WebUI.setText(findTestObject('06-Comisiones/CONSULTA PARAMETRIA COMISIONES/txtvalue211Abonado'), 'A00243A')

WebUI.click(findTestObject('06-Comisiones/CONSULTA PARAMETRIA COMISIONES/lnkEjecutar'))

WebUI.maximizeWindow()

monto = WebUI.getText(findTestObject('06-Comisiones/CONSULTA PARAMETRIA COMISIONES/lblMonto'))

WebUI.click(findTestObject('06-Comisiones/CONSULTA PARAMETRIA COMISIONES/lnkModificarComision'))

WebUI.switchToWindowTitle('FT.COMMISSION.TYPE')

montoInc = monto

if (montoInc == '100') {
    WebUI.setText(findTestObject('06-Comisiones/FT.COMMISSION.TYPE/txtMontoFijo'), '50')
} else {
    WebUI.setText(findTestObject('06-Comisiones/FT.COMMISSION.TYPE/txtMontoFijo'), '100')
}

WebUI.setText(findTestObject('06-Comisiones/FT.COMMISSION.TYPE/txtFechaVigencia'), '20220725')

//WebUI.click(findTestObject('06-Comisiones/FT.COMMISSION.TYPE/btnValidarRegistro'))
WebUI.click(findTestObject('06-Comisiones/FT.COMMISSION.TYPE/btnAceptarRegistro'))

linkAlertas = WebUI.verifyElementVisible(findTestObject('06-Comisiones/FT.COMMISSION.TYPE/lnkAceptarAlertas'))

if (linkAlertas == true) {
    WebUI.click(findTestObject('06-Comisiones/FT.COMMISSION.TYPE/lnkAceptarAlertas'))
}

WebUI.closeBrowser()

//Login gerente operativo
CustomKeywords.'pkgModules.kywGeneric.Login'(GlobalVariable.vTest10_IP, GlobalVariable.vTest10Name, GlobalVariable.vANOVELLO, 
    GlobalVariable.vPass)

WebUI.click(findTestObject('02-Dashboard/lnkComisiones'))

WebUI.click(findTestObject('02-Dashboard/4-Comisiones/lnkParametrizacion'))

WebUI.click(findTestObject('02-Dashboard/4-Comisiones/lnkAutorizarEliminarCambiosComisiones'))

WebUI.switchToWindowTitle('Comisiones Pendientes de Autorizar')

WebUI.click(findTestObject('06-Comisiones/Comisiones Pendientes de Autorizar/lnkEjecutar'))

WebUI.click(findTestObject('06-Comisiones/Comisiones Pendientes de Autorizar/btnAutorizar'))

WebUI.switchToWindowTitle('FT.COMMISSION.TYPE')

WebUI.click(findTestObject('06-Comisiones/FT.COMMISSION.TYPE/btnAutorizar'))

WebUI.closeBrowser()

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(GlobalVariable.vTest10_IP, GlobalVariable.vTest10Name, GlobalVariable.vF00474, 
    GlobalVariable.vPass)

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CONS.PARAM.COM.CREDEB')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('CONSULTA PARAMETRIA COMISIONES')

WebUI.setText(findTestObject('06-Comisiones/CONSULTA PARAMETRIA COMISIONES/txt_value111_TipoComision'), 'CREIB')

WebUI.setText(findTestObject('06-Comisiones/CONSULTA PARAMETRIA COMISIONES/txtvalue211Abonado'), 'A00243A')

WebUI.click(findTestObject('06-Comisiones/CONSULTA PARAMETRIA COMISIONES/lnkEjecutar'))

WebUI.maximizeWindow()

nuevoMonto = WebUI.getText(findTestObject('06-Comisiones/CONSULTA PARAMETRIA COMISIONES/lblMonto'))

assert monto != nuevoMonto

WebUI.takeScreenshot('Screenshot/Comisiones/COMISIONES. Modificacion de Comisiones de Interbanking (ENQ BCCL.E.CONS.PARAM.COM.CREDEB). Modificar monto de tarifa plana.png')

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'('Screenshot/Fails/Comisiones/CDC01Error.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}
