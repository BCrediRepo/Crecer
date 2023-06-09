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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(2, 3))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))

//Click en Consultas de cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkConsultasdeCuentas'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en Consulta de Mov. por fecha valor
WebUI.click(findTestObject('Object Repository/02-Dashboard/16-Resumen de Cuentas/lnkConsulta de Mov. por Fecha Valor'))

//Cambiamos a la ventana "Movimientos de Ctas por Fecha Valor"
WebUI.switchToWindowTitle('Movimientos de Ctas por Fecha Valor')

//Maximizamos
WebUI.maximizeWindow()

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

WebUI.switchToWindowTitle('T24 - Fil.089 M.del Plata Ctr')

WebUI.click(findTestObject('Object Repository/02-Dashboard/16-Resumen de Cuentas/lnkConsulta de Mov. por Fecha Valor'))

WebUI.switchToWindowTitle('Movimientos de Ctas por Fecha Valor')

//Maximizamos
WebUI.maximizeWindow()

//Completamos los datos para la consulta
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/Movimientos de Ctas por Fecha Valor/lnkNueva Seleccion'))

WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/Movimientos de Ctas por Fecha Valor/txtNroDeCuenta'), '00430014075')

WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/Movimientos de Ctas por Fecha Valor/txtFechaDesde'), '20220727')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/Movimientos de Ctas por Fecha Valor/lnkEjecutar'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/Movimientos de Ctas por Fecha Valor/lblNrodeCuenta'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/Movimientos de Ctas por Fecha Valor/lblNrodeCuenta'))

def element = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/Movimientos de Ctas por Fecha Valor/lblNrodeCuenta'))

assert element.contains('Nro. de Cuenta')

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


