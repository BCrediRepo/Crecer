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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.testdata.TestDataFactory

//Test Case Name: Alta de Cuenta

//Configuracion del ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,30), findTestData('MainData/Users').getValue(2,30))
WebUI.maximizeWindow()

//Ingresamos por el menu al Alta de Cuentas
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lnkCuentas'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkAltadeCuenta'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkAltadeCuenta'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/37-Cuentas/09-Alta de Cuenta/lnkAltadeCuentaPersonaFisica'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/09-Alta de Cuenta/lnkAltadeCuentaPersonaFisica'))

//Switch a la ventana de Alta de Cuentas
WebUI.switchToWindowTitle('Alta de Cuentas - Fil.001 Centro')
WebUI.maximizeWindow()

//Ingresamos al alta de Cuenta y seteamos el DNI de la persona a la que se le va a dar de ALTA la CUENTA
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAltadeCuentaPersonaFisica'), 6)
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAltadeCuentaPersonaFisica'))
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkNuevaSeleccionF2'), 6)
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkNuevaSeleccionF2'))
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnEjecutarF2'), 6)
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnEjecutarF2'))
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAltadeCuentaPersonaFisica'))
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtNroDniF2'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtNroDniF2'), GlobalVariable.vDNI)
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnEjecutarF2'), 6)
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnEjecutarF2'))
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAltaCuentaF2'), 6)
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAltaCuentaF2'))

//Cargamos los datos del PREALTA
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtGrupoCuentasF3'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtGrupoCuentasF3'), 'CAHR')
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtCodigoProductoF3'))
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtCodigoProductoF3'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtCodigoProductoF3'), GlobalVariable.vCODPROD)
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtUsoCuentaF3'))
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtUsoCuentaF3'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtUsoCuentaF3'), 'PERSONAL')
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnAceptarRegistroF3'), 6)
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnAceptarRegistroF3'))
def numPreAlta = WebUI.getText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblPREnumAltaF3'))

//Ingresamos al Link de ALTA y completamos las diferentes tablas
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAltaF2'), 6)
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAltaF2'))

WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtFormadeOperarCuentaF3S1'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtFormadeOperarCuentaF3S1'), '01')
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtTarifarioF3S1'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtTarifarioF3S1'), GlobalVariable.vTARIFARIO)
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtSectorBCRAF3S1'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtSectorBCRAF3S1'), '2')
WebUI.selectOptionByIndex(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/selectMotHab'), 1)


WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/tabDatosComercialesF3S2'))
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtCampaaF3S2'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtCampaaF3S2'), 'CAM0000000000')
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtIdentificacionF3S2'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtIdentificacionF3S2'), 'AFORNASAR')
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtCanalF3S2'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtCanalF3S2'), '09')
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtFechaVentaF3S2'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtFechaVentaF3S2'), GlobalVariable.vFECHA)
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtSegmentoF3S2'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtSegmentoF3S2'), '04')

WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/tabFirmantesF3S3'))
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtFormadeOperarF3S3'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtFormadeOperarF3S3'), '01')
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtDomicilioF3S3'), 6)
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/dpdDomicilioF3S3'))
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblDomicilioF3S3'), 6)
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblDomicilioF3S3'))

//WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtDomicilioF3S3'), '1002783169-1-CALLE 1002783169')
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtFormadeOperarF3S3'))
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnAceptarRegistroF3'), 6)
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnAceptarRegistroF3'))

//Aceptamos las alertas y validamos el estado de la transaccion
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAceptarAlertasF3'), 6)
WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAceptarAlertasF3'))
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblINAUF3'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblINAUF3'))
def element = WebUI.getText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblINAUF3'))
assert element.contains('INAU')


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