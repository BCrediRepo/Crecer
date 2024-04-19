import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
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
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
        2, 9))

//Se maximisa la ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 3)

//Se busca el TestBox de "Buscador"
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?311')

//Click en el boton "Ejecutar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiamos a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

//Clickeamos en Tarjetas
WebUI.click(findTestObject('02-Dashboard/lnkTarjetas311'))

//Clickeamos en Tarjetas de Deposito
WebUI.click(findTestObject('02-Dashboard/lnkTarjetasdeDeposito'))

//Clickeamos en Tarjetas de Deposito PAGOCOOP
WebUI.click(findTestObject('40-Tarjeta de Deposito/ENQ.BCCL.E.PADRON.EMPRESA/lnkAltaTPAGOCOOP'))

//Cambiamos a la ventana con el nombre "Tarjetas de Depositos Pago Coop"
WebUI.switchToWindowTitle('Tarjetas de Depositos Pagos Coop')

//Clickeamos en el boton lupita para resetear la aplicacion.
WebUI.click(findTestObject('40-Tarjeta de Deposito/ENQ.BCCL.E.PADRON.EMPRESA/btnLupita'))

//Ingresamos los datos en ID INTERNO
WebUI.setText(findTestObject('40-Tarjeta de Deposito/ENQ.BCCL.E.PADRON.EMPRESA/txtidINTERNO'), '1000097988')

//Clickeamos en Ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Clickeamos en el Dropdown
WebUI.click(findTestObject('40-Tarjeta de Deposito/BCCL.E.CARD.DEPOSIT/btnDropdown'))

//Ingresamos el roturo "CPIZZU02"
WebUI.setText(findTestObject('40-Tarjeta de Deposito/ENQ.BCCL.E.PADRON.EMPRESA/txtRotulo'), 'CPIZZU02')

//Clickeamos en Nombre Fantasia
WebUI.click(findTestObject('40-Tarjeta de Deposito/ENQ.BCCL.E.PADRON.EMPRESA/txtNombreFantasia2'))

//Esperamos a que sea visible Nombre Fantasia
WebUI.waitForElementVisible(findTestObject('40-Tarjeta de Deposito/ENQ.BCCL.E.PADRON.EMPRESA/txtNombreFantasia2'), 6)

//Ingresamos en Nombre Fantasia el siguiente datos "INST. P.A. PIZZURNO"
WebUI.setText(findTestObject('40-Tarjeta de Deposito/ENQ.BCCL.E.PADRON.EMPRESA/txtNombreFantasia2'), 'INST. P.A. PIZZURNO ')

//Click en Aceptar Registro.
WebUI.click(findTestObject('40-Tarjeta de Deposito/ENQ.BCCL.E.PADRON.EMPRESA/btnAceptarRegistro'))

//Valido que se haya completado la transaccion
WebUI.waitForElementVisible(findTestObject('40-Tarjeta de Deposito/ENQ.BCCL.E.PADRON.EMPRESA/txtTxnCompleta1'), 6)

WebUI.verifyElementVisible(findTestObject('40-Tarjeta de Deposito/ENQ.BCCL.E.PADRON.EMPRESA/txtTxnCompleta1'))

def element = WebUI.getText(findTestObject('40-Tarjeta de Deposito/ENQ.BCCL.E.PADRON.EMPRESA/txtTxnCompleta1'))

assert element.contains('Txn Completa:')

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'() 
//FIN DEL SCRIPT......

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

