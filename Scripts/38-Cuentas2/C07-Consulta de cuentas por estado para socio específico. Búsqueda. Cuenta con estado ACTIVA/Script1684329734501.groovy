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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))

//Click en consultas de cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkConsultasdeCuentas'))

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en Consulta de Cuentas Por Estado
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkConsultaDeCuentasPorEstado'))

//Swicht a la ventana BCCL.AC.CONSULTA.X.ESTADO

WebUI.switchToWindowTitle('BCCL.AC.CONSULTA.X.ESTADO')

WebUI.maximizeWindow()

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

WebUI.switchToWindowTitle('T24 - Fil.073 Jujuy')

//Click en Consulta de Cuentas Por Estado
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkConsultaDeCuentasPorEstado'))

WebUI.switchToWindowTitle('BCCL.AC.CONSULTA.X.ESTADO')

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Validamos que aparezca el texto BCCL.AC.CONSULTA.X.ESTADO
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas2/BCCL.AC.CONSULTA.X.ESTADO/lblBCCL.AC.CONSULTA.X.ESTADO'), 6)

//Ingresamos los datos para la consulta
WebUI.click(findTestObject('Object Repository/39-Cuentas2/BCCL.AC.CONSULTA.X.ESTADO/btnNueva Seleccion'))

WebUI.setText(findTestObject('Object Repository/39-Cuentas2/BCCL.AC.CONSULTA.X.ESTADO/txtIDCliente'), '1000873562')

WebUI.click(findTestObject('Object Repository/39-Cuentas2/BCCL.AC.CONSULTA.X.ESTADO/btndropdown'))

WebUI.click(findTestObject('Object Repository/39-Cuentas2/BCCL.AC.CONSULTA.X.ESTADO/lblACT'))

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/39-Cuentas2/BCCL.AC.CONSULTA.X.ESTADO/btnEjecutar'))

//validamos que aparezca el txt Nro de cuenta
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas2/BCCL.AC.CONSULTA.X.ESTADO/lblNroCuenta'), 6)

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






