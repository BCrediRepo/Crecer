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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 4), findTestData('MainData/Users').getValue(2, 4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Ingreso en el commandline el menu ?302
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?302')
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana Temenos T24
WebUI.switchToWindowTitle('Temenos T24')

//Maximizamos
WebUI.maximizeWindow()

//Click en cuentas
WebUI.click(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/Temenos T24/lnkCuentas'))

//Click en Cierrre de cuenta
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/07-Temenos T24/Cuentas/spanCierre de cuenta'))

//Click en Cierre de Cuenta con cancelacion de Saldo
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/07-Temenos T24/Cuentas/lnkCierre de Cuenta CON Cancelacion de Saldo'))

//Switch a la ventana BCCL.AC.CIERRE.CTA.CANC.SALDO
WebUI.switchToWindowTitle('BCCL.AC.CIERRE.CTA.CANC.SALDO')

//Seteo de Datos "Numero Cuenta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Numero Cuenta','12420106863')

//Click en ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Click en cierre en efectivo
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/BCCL.AC.CIERRE.CTA.CANC.SALDO/lnkCierre en Efectivo'))

//Switch a la ventana BCCL.AC.CIERRE.CUENTA
WebUI.switchToWindowTitle('BCCL.AC.CIERRE.CUENTA')

//Ingresamos el motivo de cierre
WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/17-BCCL.AC.CIERRE.CUENTA/txtMotivoCierre'), '18')

//Click en validar registro
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/17-BCCL.AC.CIERRE.CUENTA/btnValidarRegistro'))

//Click en aceptar registro
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/17-BCCL.AC.CIERRE.CUENTA/btnAceptarRegistro'))







