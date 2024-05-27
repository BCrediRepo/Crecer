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

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 2), findTestData('MainData/Users').getValue(
        2, 2))

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?302')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/Temenos T24/lnkCuentas'))

WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/Temenos T24/Cuentas/lnkModificacionDeCuentas'))

WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/Temenos T24/Cuentas/Modificacion de Cuenta/lnkBloqueoYDesbloqueo'))

WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/Temenos T24/Cuentas/Modificacion de Cuenta/Bloqueo y Desbloqueo/lnkConsultas'))

WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/Temenos T24/Cuentas/Modificacion de Cuenta/Bloqueo y Desbloqueo/Consultas/lnkBloqueosActivos'))

WebUI.switchToWindowTitle('BCCL.AC.CTABLOQ.SUC')

WebUI.setText(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CTABLOQ.SUC/txtSucursal'), '089')

WebUI.setText(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CTABLOQ.SUC/txtTipoBloqueo'), '02')

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebUI.verifyElementVisible(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CTABLOQ.SUC/lblTipodeBloqueo'))

TipoBloq = WebUI.getText(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CTABLOQ.SUC/lblTipoBloqueo'))
println TipoBloq

assert TipoBloq.contains('BLOQUEO PARCIAL')

WebUI.selectOptionByIndex(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CTABLOQ.SUC/cbVerdetalleBloqueo'), 2)

WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CTABLOQ.SUC/btnSelectDrilldown'))

WebUI.switchToWindowTitle('Account Blocking Details')

WebUI.verifyElementVisible(findTestObject('04-Bloqueo y Desbloqueo/Account Blocking Details/lblTipoBloqueo'))

descripcion = WebUI.getText(findTestObject('04-Bloqueo y Desbloqueo/Account Blocking Details/lblTipoBloqueo'))

assert descripcion.contains('BLOQUEO PARCIAL') //Control fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

