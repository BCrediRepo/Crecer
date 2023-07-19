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

//BLOQUEADO POR ISSUE EN PADRONES --> NO ADMITE USUARIO DE VENTA//BLOQUEADO POR ISSUE EN PADRONES --> NO ADMITE USUARIO DE VENTA
//BLOQUEADO POR ISSUE EN PADRONES --> NO ADMITE USUARIO DE VENTA//BLOQUEADO POR ISSUE EN PADRONES --> NO ADMITE USUARIO DE VENTA
//BLOQUEADO POR ISSUE EN PADRONES --> NO ADMITE USUARIO DE VENTA//BLOQUEADO POR ISSUE EN PADRONES --> NO ADMITE USUARIO DE VENTA
//BLOQUEADO POR ISSUE EN PADRONES --> NO ADMITE USUARIO DE VENTA//BLOQUEADO POR ISSUE EN PADRONES --> NO ADMITE USUARIO DE VENTA
//BLOQUEADO POR ISSUE EN PADRONES --> NO ADMITE USUARIO DE VENTA//BLOQUEADO POR ISSUE EN PADRONES --> NO ADMITE USUARIO DE VENTA

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
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CARD.EMPRESA.PER')

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Cambiamos de ventana
WebUI.switchToWindowTitle('Busqueda de Persona')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

WebUI.switchToWindowIndex(0)

//Se busca el TestBox de "Buscador"
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CARD.EMPRESA.PER')

//Click en Ejecutar
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Cambiamos de ventana
WebUI.switchToWindowTitle('Busqueda de Persona')

//Esperamos a que este visible el objeto
WebUI.waitForElementVisible(findTestObject('40-Tarjeta de Deposito/ENQ BCCL.E.CARD.EMPRESA.PER/txtPersonaDNICUIT'), 3)

//Ingresamos el ID  de persona
WebUI.setText(findTestObject('40-Tarjeta de Deposito/ENQ BCCL.E.CARD.EMPRESA.PER/txtPersonaDNICUIT'), '1000097988')

//Click en Ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Click en ALTA
WebUI.click(findTestObject('40-Tarjeta de Deposito/ENQ BCCL.E.CARD.EMPRESA.PER/lnkAlta'))

//Clickeamos en btnMasRotulos
WebUI.click(findTestObject('40-Tarjeta de Deposito/ENQ BCCL.E.CARD.EMPRESA.PER/btnMasRotulos'))

//Rellenar ROTULO 2
WebUI.waitForElementPresent(findTestObject('40-Tarjeta de Deposito/ENQ BCCL.E.CARD.EMPRESA.PER/txtROTULO2'), 6)
WebUI.setText(findTestObject('40-Tarjeta de Deposito/ENQ BCCL.E.CARD.EMPRESA.PER/txtROTULO2'), 'RSANCH')

WebUI.waitForElementPresent(findTestObject('Object Repository/40-Tarjeta de Deposito/ENQ BCCL.E.CARD.EMPRESA.PER/txtCuentaReceptora2'), 6)
WebUI.setText(findTestObject('Object Repository/40-Tarjeta de Deposito/ENQ BCCL.E.CARD.EMPRESA.PER/txtCuentaReceptora2'), '00340034758')


//Rellenar nombre de fantasia
WebUI.setText(findTestObject('40-Tarjeta de Deposito/ENQ BCCL.E.CARD.EMPRESA.PER/txtNOMBREFANTASIA2'), 'ROBERTO SANCHEZ')

//Rellenar mail2
WebUI.setText(findTestObject('40-Tarjeta de Deposito/ENQ BCCL.E.CARD.EMPRESA.PER/txtMAIL2'), 'prueba@gmail.com')

//Rellenar CANAL VENTA 2
WebUI.setText(findTestObject('40-Tarjeta de Deposito/ENQ BCCL.E.CARD.EMPRESA.PER/txtCANALVENTA2'), '01')

WebUI.setText(findTestObject('Object Repository/40-Tarjeta de Deposito/ENQ BCCL.E.CARD.EMPRESA.PER/txtFECHAVENTA2'), '20220729')

//Ingresar USUARIOVENTA2
WebUI.setText(findTestObject('40-Tarjeta de Deposito/ENQ BCCL.E.CARD.EMPRESA.PER/txtUSUARIOVENTA2'), 'SAN')

//Click en Aceptar el registro.
WebUI.click(findTestObject('40-Tarjeta de Deposito/ENQ BCCL.E.CARD.EMPRESA.PER/btnAceptarElRegistro2'))

//BLOQUEADO POR ISSUE EN PADRONES --> NO ADMITE USUARIO DE VENTA

//*FIN DE SCRIPT*-------------------------------------------

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}
