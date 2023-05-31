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
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
        2, 9))

//Se maximiza la ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
//Esperamos a que sea visible el buscador
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 3)
//Buscamos TSA.SERVICE en el textbox
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'TSA.SERVICE')

//Click en el boton "Ejecutar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))
//Cambiamos a la ventana con el nombre "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('40-Tarjeta de Deposito/TSA.SERVICE/txtTSA.SERVICEId'), FailureHandling.STOP_ON_FAILURE)
//Escribimos en el textbox lo que queremos buscar.
WebUI.setText(findTestObject('40-Tarjeta de Deposito/TSA.SERVICE/txtTSA.SERVICEId'), 'BNK/BCCL.B.GEN.ABM.PAGOCOOP')
//Esperamos a que sea visible el boton Aceptar Registro.
WebUI.waitForElementVisible(findTestObject('40-Tarjeta de Deposito/ENQ.BCCL.E.PADRON.EMPRESA/btnAceptarRegistro'), 6)
//Clickeamos en el boton Modificar Registro.
WebUI.click(findTestObject('02-Dashboard/38-Tarjeta de Deposito/btnModificarRegistro'))
//Realizamos un assert del dato.
DatoTarjeta = WebUI.waitForElementVisible(findTestObject('40-Tarjeta de Deposito/TSA.SERVICE/txtDato'), 3)

assert DatoTarjeta == true
//Esperamos a que el dropdown este disponible
WebUI.waitForElementVisible(findTestObject('40-Tarjeta de Deposito/TSA.SERVICE/selectSTARTSTOP'), 0)
//Seleccionamos la opcion 1
WebUI.selectOptionByIndex(findTestObject('40-Tarjeta de Deposito/TSA.SERVICE/selectSTARTSTOP'), 1)

WebUI.click(findTestObject('40-Tarjeta de Deposito/TSA.SERVICE/txtfieldNameVALUE1'))
//Clickeamos en Aceptar el Registro
WebUI.click(findTestObject('40-Tarjeta de Deposito/TSA.SERVICE/btnAceptarelRegistro'))
//Buscamos lo que queremos en el textbox
WebUI.setText(findTestObject('40-Tarjeta de Deposito/TSA.SERVICE/txtTSA.SERVICEId'), 'BNK/BCCL.B.GEN.ABM.PAGOCOOP')
//Clickeamos en el boton Modificar Registro
WebUI.click(findTestObject('02-Dashboard/38-Tarjeta de Deposito/btnModificarRegistro'))

WebUI.waitForElementVisible(findTestObject('40-Tarjeta de Deposito/TSA.SERVICE/selectSTARTSTOP'), 0)
//Seleccionamos la opcion 3
WebUI.selectOptionByIndex(findTestObject('40-Tarjeta de Deposito/TSA.SERVICE/selectSTARTSTOP'), 3)

WebUI.click(findTestObject('40-Tarjeta de Deposito/TSA.SERVICE/btnAceptarelRegistro'))

//Valido que se haya completado la transaccion
WebUI.waitForElementVisible(findTestObject('40-Tarjeta de Deposito/ENQ.BCCL.E.PADRON.EMPRESA/txtTxnCompleta1'), 6)

WebUI.verifyElementVisible(findTestObject('40-Tarjeta de Deposito/ENQ.BCCL.E.PADRON.EMPRESA/txtTxnCompleta1'))

def element = WebUI.getText(findTestObject('40-Tarjeta de Deposito/ENQ.BCCL.E.PADRON.EMPRESA/txtTxnCompleta1'))

assert element.contains('Txn Completa:')

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

