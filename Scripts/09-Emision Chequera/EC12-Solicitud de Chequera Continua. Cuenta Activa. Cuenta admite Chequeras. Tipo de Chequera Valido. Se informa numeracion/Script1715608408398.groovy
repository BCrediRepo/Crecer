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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 50), findTestData('MainData/Users').getValue(2, 50))
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'('?1', 1)
WebUI.switchToWindowTitle('Temenos T24')
def menuDesplegable = ['Sucursal Piloto', 'D3 - CC1', 'CC1 - Emision Chequera', 'Solicitudes de Chequeras / Boleteras']
def link = 'SOLICITUD DE CHEQUERAS'

CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Cambiar ventana "BCCL.CQ.SOLICITUD"
WebUI.switchToWindowTitle('BCCL.CQ.SOLICITUD')

//Setear id de Cuenta
WebUI.setText(findTestObject('Object Repository/11-Emision Chequera/BCCL.CQ.SOLICITUD/txtIdCuenta'), '01030166182')

//Seleccionar "boton dropdown" de Tipo de Chequera
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/BCCL.CQ.SOLICITUD/btnDropdownTipodeChequera'))

//Seleccionar FCCL
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/BCCL.CQ.SOLICITUD/lblFCCL'))

//Setear la Cantidad de Chequeras
WebUI.setText(findTestObject('Object Repository/11-Emision Chequera/BCCL.CQ.SOLICITUD/txtCantidadChequeras'), '1')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Alertas"
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))

//Cambiar ventana "Verificacion de Firmas - Fil.103 Necochea"
WebUI.switchToWindowTitle('Verificacion de Firmas - Fil.103 Necochea')

//Seleccionar "FORZAR" del combo box
WebUI.selectOptionByIndex(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/cbxAccion'), 2)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/btnAceptar'))

//Verificar "AUTORIZADA"
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblAutorizada'))

//Validar "AUTORIZADA"
def element = WebUI.getText(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblAutorizada'))
assert element.contains('AUTORIZADA')

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}