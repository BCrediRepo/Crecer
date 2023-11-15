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

//Seleccionar "Cuentas"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))

//Seleccionar "Modificación de Cuenta"
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkModificaciondDeCuenta'))

//Seleccionar "Bloqueo y Desbloqueo"
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/lnkBloqueoyDesbloqueo'))

//Seleccionar "Consultas"
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/lnkConsultas'))

//Seleccionar "Historial de Bloqueos inactivos"
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/03-Consultas/lnkHistorialdeBloqueosInactivos'))

//Cambiar ventana "Historial de Bloqueos"
WebUI.switchToWindowTitle('BCCL.AC.CONS.HIST.BLOQ')

//Filtro limpieza
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Seleccionar "Historial de Bloqueos inactivos"
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/08-Modificacion De Cuenta/01-Bloqueo y Desbloqueo/03-Consultas/lnkHistorialdeBloqueosInactivos'))

//Cambiar ventana "Historial de Bloqueos"
WebUI.switchToWindowTitle('BCCL.AC.CONS.HIST.BLOQ')

//Setear "Numero de cuenta"
WebUI.setText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.AC.CONS.HIST.BLOQ/txtCuenta'), '00010043082')

//Capturar el tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton de Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verificar "Fecha de Bloqueo"
WebUI.verifyElementVisible(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.AC.CONS.HIST.BLOQ/lblFechadeBloqueo'))

//Capturar el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime
println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))
TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))
println TotalRegistros

//Validar "Fecha de Bloqueo"
def fechaBloqueo = WebUI.getText(findTestObject('Object Repository/04-Bloqueo y Desbloqueo/BCCL.AC.CONS.HIST.BLOQ/lblFechadeBloqueo'))
assert fechaBloqueo.contains('Fecha de Bloqueo')
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