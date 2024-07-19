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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Ingreso al menu ?399
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?399')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//ingreso temenos T24
WebUI.switchToWindowIndex(1)

WebUI.maximizeWindow()

//espando Transacciones Especiales
WebUI.click(findTestObject('Object Repository/29-Limites y Acumuladores/Temenos T24/lnkTransaEspeciales'))

WebUI.waitForElementPresent(findTestObject('Object Repository/29-Limites y Acumuladores/Temenos T24/Transacciones Especiales/lnkABMdeAcum'), 6)

//Click en ABM de Acumuladores
WebUI.click(findTestObject('Object Repository/29-Limites y Acumuladores/Temenos T24/Transacciones Especiales/lnkABMdeAcum'))

//ingreso Consulta ABM acumuladores
WebUI.switchToWindowIndex(2)

//Seteo de datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cod. acumulador','AC.COT.PGC.B112')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Aplica a','SUCURSAL')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Entidad','043')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Frecuencia','DIARIA')

//Seleccionar ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Click en Alta
WebUI.click(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.ABM.ACUMULADORES/lnkAltaAcum'))

//seteo Importe
WebUI.setText(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.EB.LM.ACUM.AUX/txtImporteAltaAcumulad'), '123')

//seteo cantidad de transacciones
WebUI.setText(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.EB.LM.ACUM.AUX/txtCantTransAcum'), '2')

//Click en aceptar registros
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnAceptarRegistro'))

//Definir Objeto
Transaccion1 = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))
//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion1.split('\\s+')
def trx1 = partes[2]
def Acum = trx1.split('-')
def NroAcum = Acum[0]

//volvemos a tememos T24
WebUI.switchToWindowIndex(1)

//espandimos Parametria Suc Piloto
WebUI.click(findTestObject('Object Repository/29-Limites y Acumuladores/Temenos T24/lnkParamSucPiloto'))

//entras a la tabla de Consulta de Acumuladores
WebUI.click(findTestObject('Object Repository/29-Limites y Acumuladores/Temenos T24/Parametria Suc Piloto/lnkTablaAcum'))

//switch a BCCL.E.EB.LM.ACUM.GEN
WebUI.switchToWindowIndex(3)

//Seteo de datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cod Acumulador',NroAcum)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Entidad','043')

//Seleccionar ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verifico que se encuentre el lbl Fecha inicio = 01 SEP 2023
WebUI.verifyElementVisible(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.EB.LM.ACUM.GEN/lblFechaInicio'))
def element2 = WebUI.getText(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.EB.LM.ACUM.GEN/lblFechaInicio'))
assert element2.contains("01 SEP 2023")

//Verifico que se encuentre el lbl frecuencia = diario
WebUI.verifyElementVisible(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.EB.LM.ACUM.GEN/lblFrecuenciaDiario'))
def element3 = WebUI.getText(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.EB.LM.ACUM.GEN/lblFrecuenciaDiario'))
assert element3.contains("Diario")

//volvemos a tememos 24
WebUI.switchToWindowIndex(1)

//Click en ABM de Acumuladores
WebUI.click(findTestObject('Object Repository/29-Limites y Acumuladores/Temenos T24/Transacciones Especiales/lnkABMdeAcum'))

//ingreso Consulta ABM acumuladores
WebUI.switchToWindowIndex(4)

//Seleccionar ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Click Baja
WebUI.click(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.ABM.ACUMULADORES/lnkBajaAcum'))

//Click en aceptar registros
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnAceptarRegistro'))

//Validar txn completa
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))
assert element.contains('Txn Completa:')


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
