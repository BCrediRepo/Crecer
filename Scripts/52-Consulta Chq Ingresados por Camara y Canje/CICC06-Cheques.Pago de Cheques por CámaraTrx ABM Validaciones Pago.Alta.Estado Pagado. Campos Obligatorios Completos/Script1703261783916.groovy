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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Acceder menu "?1"
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?1')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton GO"
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')

//Maximizar pantalla
WebUI.maximizeWindow()

//Seleccionar "Sucursal Piloto"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/Temenos T24/lnkSucursalPiloto'))

//Seleccionar "D3 - Pago Cheque Camara"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/Temenos T24/Sucursal Piloto/lnkD3-PagoChequeCamara'))
	
//Seleccionar "Administracion"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/Temenos T24/Sucursal Piloto/D3 - PAGO CHEQUE CAMARA/lnkAdmintracion'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Validaciones Pago"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/Temenos T24/Sucursal Piloto/D3 - PAGO CHEQUE CAMARA/Admintracion/lnkValidacionesPago'))

//Cambiar ventana "BCCL.CQ.VALIDACIONES.PAGO"
WebUI.switchToWindowTitle('BCCL.CQ.VALIDACIONES.PAGO')

//Seleccionar "Drill Down Lista de Registros"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/btnDrillDownListadeRegistros'))

//Seleccionar Primera id
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/lblidValidacionesPago'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Ver un Registro"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/btnVerRegistro'))

//Verificar Estado "Pagado"
WebUI.verifyElementVisible(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/lblEstadoPagado'))

//Validar Estado "Pagado"
pagado = WebUI.getText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/lblEstadoPagado'))
assert pagado == "Pagado"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}