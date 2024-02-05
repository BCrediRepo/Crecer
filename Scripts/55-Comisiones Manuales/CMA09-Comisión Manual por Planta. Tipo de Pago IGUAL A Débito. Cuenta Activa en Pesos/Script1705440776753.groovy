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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()


//Click en comisiones y bonificaciones
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkComisionesyBonificaciones'))

//Click en comisiones
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkComisiones'))

//Click en Cobro Comisiones Manuales
WebUI.click(findTestObject('Object Repository/02-Dashboard/04-Comisiones/lnkCobroComisiones Manuales - Planta'))

//Switch a la ventana Account Charge Request
WebUI.switchToWindowTitle('Account Charge Request')

//Maximizamos
WebUI.maximizeWindow()

//Selecionamos el tipo de pago
WebUI.selectOptionByIndex(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/cbxTipoPago'), 1)

//Ingresamos la cuenta de debito 00545293967
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCuentaDebito'), '10700010299')

//Seleciono Codigo Concepto
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'), '18301CMI')

//Agregamos comentarios de observaciones
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'), 'COBRO DE FIRMAS')

//Click en aceptar registro
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnAceptar Registro'))

//Click en aceptar alertas
//WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lnkAceptar Alertas'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))

def element = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))

assert element.contains('Txn Completa')

//Switch a la ventana principal
WebUI.switchToWindowIndex(0)

//Click en cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))

//Click consultas de cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkConsultasdeCuentas'))

//Click en Consulta de Mov. por Fecha Valor
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkConsulta de Mov. por Fecha Valor'))

//Switch a la ventana Movimientos de Ctas por Fecha Valor
WebUI.switchToWindowTitle('Movimientos de Ctas por Fecha Valor')

//Maximizamos
WebUI.maximizeWindow()

//Click en nueva seleccion
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Ingresamos Nro de cuenta
WebUI.setText(findTestObject('Object Repository/39-Cuentas/Movimientos de Ctas por Fecha Valor/txtNro de Cuenta'), '10700010299')

//Ingresamos la Fecha desde
WebUI.setText(findTestObject('Object Repository/39-Cuentas/Movimientos de Ctas por Fecha Valor/txtFechaDesde'), '20220726')

//Click en ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Click en ver detalle completo txn
WebUI.click(findTestObject('Object Repository/39-Cuentas/Movimientos de Ctas por Fecha Valor/btnVerDetalleCompletotxn'))

//Switch a la ventana Consulta Mov. de una TXN
WebUI.switchToWindowTitle('Consulta Mov. de una TXN')

//Click en boton ver
WebUI.click(findTestObject('Object Repository/39-Cuentas/Consulta Mov. de una TXN/btnVer'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblRequest Type'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblRequest Type'))

def element2 = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblRequest Type'))

assert element2.contains('Request Type')


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


