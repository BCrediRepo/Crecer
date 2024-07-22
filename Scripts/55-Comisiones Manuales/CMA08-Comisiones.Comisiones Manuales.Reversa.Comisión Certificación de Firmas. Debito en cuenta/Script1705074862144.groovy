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


def cuenta = '00430300691'
def concepto = '18301CMI'
def observacion = 'PRUEBAS CRECER'
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Desde el menu principal, accedemos a la aplicación para cobrar una comision manual - planta
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkComisionesyBonificaciones'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkComisiones'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/04-Comisiones/lnkCobroComisiones Manuales - Planta'))
WebUI.switchToWindowTitle('Account Charge Request')
WebUI.maximizeWindow()

//Se cargan los datos los datos de la comision
WebUI.selectOptionByIndex(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/cbxTipoPago'), 1)
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCuentaDebito'), cuenta)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'), concepto)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'), observacion)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnAceptar Registro'))
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lnkAceptar Alertas'))

//validamos que la transaccion haya finalizado correctamente
WebUI.waitForElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))
def txn = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))
assert txn.contains('Txn Completa')

//separamos el numero de transaccion
String[] palabras = txn.split(" ")
String transaccion = palabras[2]

//Switch a la ventana Account Charge Request
WebUI.switchToWindowTitle('Account Charge Request')
WebUI.maximizeWindow()

//ingresamos la transaccion obtenida previamente y validamos que la misma esté hecha en pesos
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtComisiones Manuales-Caja'), transaccion)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnVerRegistro'))
def Moneda = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/spanARS'))
assert Moneda == "ARS"

//Deslogeamos y volvemos a logear con otro user para poder reversar la comision creada
WebUI.closeWindowTitle("Account Charge Request")
WebUI.switchToWindowIndex(0)
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnLogout'))

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Desde el dashboard iremos al aplicativo de reversos para poder buscar nuestra comision creada y reversarla
WebUI.click(findTestObject('Object Repository/02-Dashboard/spanReversos'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkReverso de Operaciones'))
WebUI.switchToWindowTitle('BCCL.E.EB.CONS.REVE')
WebUI.maximizeWindow()

//Ingresamos los datos de busqueda
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro. Contrato', transaccion)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Usuario', 'B.0043')
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//abrimos menu de reversar y reversamos el registro
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/BCCL.E.EB.CONS.REVE/lnkReversar'))
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnReversarRegistro'))

//validamos que la reversa esté realizada correctameente viendo el estado en los detalles del registro
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtComisiones Manuales-Caja'), transaccion)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnVerRegistro'))
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/spanAudit'))
def Estado = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblREVE'))
assert Estado.contains('REVE')


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

