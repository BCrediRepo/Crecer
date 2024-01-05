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

//Seleccionar "D3 - CC1"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/Temenos T24/Sucursal Piloto/lnkD3-CC1'))

//Seleccionar "CC1 - Emision Chequera"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/Temenos T24/Sucursal Piloto/D3 - CC1/lnkCC1-EmisionChequera'))

//Seleccionar "Solicitudes de Chequeras / Boleteras"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/Temenos T24/Sucursal Piloto/D3 - CC1/CC1 - Emision Chequera/lnkSolicitudesdeChequerasBoleteras'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "SOLICITUD DE CHEQUERAS"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/Temenos T24/Sucursal Piloto/D3 - CC1/CC1 - Emision Chequera/Solicitudes de Chequeras Boleteras/lnkSolicituddeChequeras'))

//Cambiar ventana "BCCL.CQ.SOLICITUD"
WebUI.switchToWindowTitle('BCCL.CQ.SOLICITUD')

//Setear "Id de Cuenta"
WebUI.setText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.SOLICITUD/txtIddeCuenta'), '00010043082')

//Seleccionar "Drill Down TipodeChequera"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.SOLICITUD/btnDrillDownTipodeChequera'))

//Seleccionar "LCCA"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.SOLICITUD/lblLCCA'))

//Setear "Cantidad de Chequeras"
WebUI.setText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.SOLICITUD/txtCantidaddeChequeras'), '10')

//Seleccionar "Radio boton Normal"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.SOLICITUD/rbNormal'))

//Seleccionar "Aceptar el registro"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.SOLICITUD/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.SOLICITUD/lnkAceptarAlertas'))

//Cambiar ventana "Verificacion de Firmas - Fil.001 Centro"
WebUI.switchToWindowTitle('Verificacion de Firmas - Fil.001 Centro')

//Seleccionar "FORZAR" en el combo box
WebUI.selectOptionByIndex(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/cbxAccion'), 2)

//Seleccionar "Aceptar el registro"
WebUI.click(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/btnAceptar'))

//Verificar "Autorizada
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblAutorizada'))

//Validar "Finalizada"
finalizada = WebUI.getText(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblFinalizada'))
assert finalizada == "FINALIZADA"

//Validar "Autorizada"
finalizada = WebUI.getText(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblAutorizada'))
assert finalizada == "AUTORIZADA"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}