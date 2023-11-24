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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()

//Ingresar "?308" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?308')

//Seleccionar boton de buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')

//Seleccionar "Rechazo de Cheques"
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Temenos T24/lnkRechazodecheques'))

//Seleccionar "Consultas"
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Temenos T24/Rechazo de Cheques/lnkConsultas'))

//Seleccionar "Rechazos Masivos"
WebUI.click(findTestObject('Object Repository/02-Dashboard/06-Cheques rechazados/1-Consultas - Temenos T24/lnkRechazosMasivos'))

//Cambiar ventana "BCCL.E.CQ.RECH.MASIVO.CONSULTA"
WebUI.switchToWindowTitle('BCCL.E.CQ.RECH.MASIVO.CONSULTA')

//Filtro limpieza
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(1)

//Seleccionar "Rechazos Masivos"
WebUI.click(findTestObject('Object Repository/02-Dashboard/06-Cheques rechazados/1-Consultas - Temenos T24/lnkRechazosMasivos'))

//Cambia a la ventana de Rechazos Masivos
WebUI.switchToWindowTitle('BCCL.E.CQ.RECH.MASIVO.CONSULTA')

//Setear "Sucursal girada"
WebUI.setText(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.RECH.MASIVO.CONSULTA/txtSucursalGirada'), '089')

//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//NO SE PUEDE CONTINUAR POR QUE NO TIENE DATA

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}