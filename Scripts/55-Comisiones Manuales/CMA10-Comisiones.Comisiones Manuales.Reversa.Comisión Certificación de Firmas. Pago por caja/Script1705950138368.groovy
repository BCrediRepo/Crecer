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
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.support.ui.Select
import java.awt.Robot
import java.awt.event.KeyEvent

def Moneda = 'ARS'
def idOrdenante = '1000755088'
def concepto = '18301CMI'

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,54), findTestData('MainData/Users').getValue(2,54))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Desde el menu principal, accedemos a la aplicación para generar una nueva comision manual en efectivo
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkComisiones'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/04-Comisiones/lnkCobro de Comisiones Manuales EN EFECTIVO'))
WebUI.switchToWindowTitle('Account Charge Request')
WebUI.maximizeWindow()

//cargamos los datos requeridos para la comision
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtMoneda'), Moneda)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/cbxEsSocio'))
WebUI.selectOptionByIndex(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/cbxEsSocio'), 2)
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtId Ordenante'), idOrdenante)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtCodigo Concepto'), concepto)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'))
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtObservaciones'), 'PRUEBAS CRECER')

//Click en aceptar registro
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnAceptar Registro'))

//validamos que la comision se haya generado con exito
WebUI.waitForElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))
def txn = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblTxn Completa'))
assert txn.contains('Txn Completa')
//Separamos en una variable el numero de la operacion
String[] palabras = txn.split(" ");
String operacion = palabras[2];

WebUI.switchToWindowTitle('Account Charge Request')
WebUI.maximizeWindow()


//ingresamos la transaccion obtenida previamente y validamos que la misma esté hecha en pesos
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtComisiones Manuales-Caja'), operacion)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnVerRegistro'))
def Mon = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/spanARS'))
assert Mon == "ARS"

//Deslogeamos y volvemos a logear con otro user para poder reversar la comision creada
WebUI.closeWindowTitle("Account Charge Request")
WebUI.switchToWindowIndex(0)
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnLogout'))

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,54), findTestData('MainData/Users').getValue(2,54))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Desde el dashboard iremos al aplicativo de reversos para poder buscar nuestra comision creada y reversarla
WebUI.click(findTestObject('Object Repository/02-Dashboard/spanReversos'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkReverso de Operaciones'))
WebUI.switchToWindowTitle('BCCL.E.EB.CONS.REVE')
WebUI.maximizeWindow()

//Ingresamos los datos de busqueda
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro. Contrato', operacion)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Usuario', 'B.2357')
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//abrimos menu de reversar y reversamos el registro
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/BCCL.E.EB.CONS.REVE/lnkReversar'))
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnReversarRegistro'))

//validamos que la reversa esté realizada correctameente viendo el estado en los detalles del registro
WebUI.setText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/txtComisiones Manuales-Caja'), operacion)
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/btnVerRegistro'))
WebUI.click(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/spanAudit'))
def Estado = WebUI.getText(findTestObject('Object Repository/56-Comisiones Manuales/Account Charge Request/lblRecordStatusPos1'))
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





