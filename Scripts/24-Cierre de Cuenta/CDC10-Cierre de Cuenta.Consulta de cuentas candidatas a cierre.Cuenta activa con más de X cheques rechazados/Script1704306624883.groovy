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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(2, 1))
WebUI.maximizeWindow()

//Ingresar "ENQ BCCL.E.CANDT.CIERRE" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CANDT.CIERRE')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton de buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "BCCL.E.CANDT.CIERRE"
WebUI.switchToWindowTitle('BCCL.E.CANDT.CIERRE')

//Filtro limpieza
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Seleccionar boton de buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "BCCL.E.CANDT.CIERRE"
WebUI.switchToWindowTitle('BCCL.E.CANDT.CIERRE')

//Maximizar Ventana
WebUI.maximizeWindow()

//Setear "Sucursal"
WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/10-BCCL.E.CANDT.CIERRE/txtSucursal'), '001')

//Setear "Motivo"
WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/10-BCCL.E.CANDT.CIERRE/txtMotivo'), '07')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Cambiar valor en el cb por "Ver cta cant cierre"
WebUI.selectOptionByIndex(findTestObject('Object Repository/25-Cierre de Cuenta/10-BCCL.E.CANDT.CIERRE/cbVerctacantcierre'), 1)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "btn Select Drill Down"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/10-BCCL.E.CANDT.CIERRE/btnSelectDrilldown'))

//Cambiar ventana "BCCL.AC.CANDT.CIERRE"
WebUI.switchToWindowTitle('BCCL.AC.CANDT.CIERRE')

//Verificar Cuenta
WebUI.verifyElementVisible(findTestObject('Object Repository/25-Cierre de Cuenta/11-BCCL.AC.CANDT.CIERRE/lblCuenta'))

//Validar Cuenta
ctaOrigChrech = WebUI.getText(findTestObject('Object Repository/25-Cierre de Cuenta/11-BCCL.AC.CANDT.CIERRE/lblCuenta'))
assert ctaOrigChrech == "02460051271"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}
