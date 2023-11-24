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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,10), findTestData('MainData/Users').getValue(2,10))
WebUI.maximizeWindow()

//Seleccionar "Rechazo de Cheques"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkRechazodecheques'))
	
//Seleccionar "Consultas"
WebUI.click(findTestObject('Object Repository/02-Dashboard/06-Cheques rechazados/lnkConsultas'))
	
//Seleccionar "Multas Pendientes"
WebUI.click(findTestObject('Object Repository/02-Dashboard/06-Cheques rechazados/1-Consultas - Temenos T24/lnkMultaspendientes'))

//Cambiar ventana "BCCL.CHRECH.MULTAS.PENDIENTES"
WebUI.switchToWindowTitle('BCCL.CHRECH.MULTAS.PENDIENTES')

//Filtro limpieza
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Seleccionar "Multas Pendientes"
WebUI.click(findTestObject('Object Repository/02-Dashboard/06-Cheques rechazados/1-Consultas - Temenos T24/lnkMultaspendientes'))

//Cambiar ventana "BCCL.CHRECH.MULTAS.PENDIENTES"
WebUI.switchToWindowTitle('BCCL.CHRECH.MULTAS.PENDIENTES')

//Setear "Sucursal"
WebUI.setText(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.CHRECH.MULTAS.PENDIENTES/txtSucursal'), '001')

//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//NO SE PUEDE CONTINUAR POR QUE DA UN TIME OUT



//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}