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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 33), findTestData('MainData/Users').getValue(2, 33))
WebUI.maximizeWindow()

//Ingresar "BCCL.GRUPO.COTIZACION,ABM.PIZARRA.OO" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.GRUPO.COTIZACION,ABM.PIZARRA.OO')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "GRUPO.COTIZACION"
WebUI.switchToWindowTitle('GRUPO.COTIZACION')

//Setear Grupo Cotizacion
WebUI.setText(findTestObject('Object Repository/15-MONEX/14-GRUPO.COTIZACION/txtGrupoCotizacion'), 'BRLOO001')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Modificar Registro"
WebUI.click(findTestObject('Object Repository/15-MONEX/14-GRUPO.COTIZACION/btnModificarRegistro'))

//Setear Descripcion "Tipo de Cambio de Referencia BCRA"
WebUI.setText(findTestObject('Object Repository/15-MONEX/14-GRUPO.COTIZACION/txtDescripcion'), 'Tipo de Cambio de Referencia BCRA')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/15-MONEX/14-GRUPO.COTIZACION/btnAceptarRegistro'))

//Cambiar ventana "BCCL.E.ESTADO.PANTALLAS"
WebUI.switchToWindowTitle('BCCL.E.ESTADO.PANTALLAS')

//Verificar "AUTORIZADA"
WebUI.verifyElementVisible(findTestObject('Object Repository/15-MONEX/15-BCCL.E.ESTADO.PANTALLAS/lblDescripcionAUTORIZADA'))

//Validar "AUTORIZADA"
autorizada = WebUI.getText(findTestObject('Object Repository/15-MONEX/15-BCCL.E.ESTADO.PANTALLAS/lblDescripcionAUTORIZADA'))
assert autorizada == "AUTORIZADA"

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Switch a la ventana del Dashboard
WebUI.switchToWindowIndex(0)

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "GRUPO.COTIZACION"
WebUI.switchToWindowTitle('GRUPO.COTIZACION')

//Setear Grupo Cotizacion
WebUI.setText(findTestObject('Object Repository/15-MONEX/14-GRUPO.COTIZACION/txtGrupoCotizacion'), 'BRLOO001')

//Seleccionar "boton Modificar Registro"
WebUI.click(findTestObject('Object Repository/15-MONEX/14-GRUPO.COTIZACION/btnModificarRegistro'))

//Setear Descripcion "PRUEBAS CRECER ONLINE"
WebUI.setText(findTestObject('Object Repository/15-MONEX/14-GRUPO.COTIZACION/txtDescripcion'), 'PRUEBAS CRECER ONLINE')

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/15-MONEX/14-GRUPO.COTIZACION/btnAceptarRegistro'))

//Cambiar ventana "BCCL.E.ESTADO.PANTALLAS"
WebUI.switchToWindowIndex(2)

//Verificar "AUTORIZADA"
WebUI.verifyElementVisible(findTestObject('Object Repository/15-MONEX/15-BCCL.E.ESTADO.PANTALLAS/lblDescripcionAUTORIZADA'))

//Validar "AUTORIZADA"
autorizada2 = WebUI.getText(findTestObject('Object Repository/15-MONEX/15-BCCL.E.ESTADO.PANTALLAS/lblDescripcionAUTORIZADA'))
assert autorizada2 == "AUTORIZADA"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}