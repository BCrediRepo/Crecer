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

//Config
LocalDateTime now = LocalDateTime.now()
DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE
String nowString = formatter.format(now)

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()

//Se accede al menu
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?1')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))
//Switch a la ventana de busqueda de consulta
WebUI.switchToWindowTitle('Temenos T24')
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lnkSucursalPiloto'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkSucursalPiloto'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/5-SucursalPiloto/lnkResumendeCuentas'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/5-SucursalPiloto/lnkResumendeCuentas'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/5-SucursalPiloto/Resumen de Cuentas/lnkConsultas'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/5-SucursalPiloto/Resumen de Cuentas/lnkConsultas'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/5-SucursalPiloto/Resumen de Cuentas/Consultas/lnk SALDO HISTORICO DE CUENTAS'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/5-SucursalPiloto/Resumen de Cuentas/Consultas/lnk SALDO HISTORICO DE CUENTAS'))

//Switch a la ventana de saldos historicos de cuenta
WebUI.switchToWindowTitle('Saldos Historicos de Cuenta')
WebUI.waitForElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/02-Saldos Historicos de Cuenta/txtCuenta'), 6)
WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/02-Saldos Historicos de Cuenta/txtCuenta'), '05330013359')
WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/02-Saldos Historicos de Cuenta/txtFecha'), '20220701')
WebUI.waitForElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/02-Saldos Historicos de Cuenta/lnkEjecutar'), 6)
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/02-Saldos Historicos de Cuenta/lnkEjecutar'))

WebUI.waitForElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/02-Saldos Historicos de Cuenta/lblNumCuentaValue'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/02-Saldos Historicos de Cuenta/lblNumCuentaValue'))
WebUI.takeScreenshot('Screenshot/Resumen de Cuenta/Resumen de Cuenta. Consulta por Sucursal. Consulta Saldo Historico de Cuentas. Cuenta Operativa en estado Activa' + nowString + '.png')

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'('Screenshot/Fails/APT01Error.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}