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
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 33), findTestData('MainData/Users').getValue(2, 33))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando menu ENQ BCCL.CQ.CHRECH.BAJA.FIR
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("ENQ BCCL.CQ.CHRECH.BAJA.FIR", 1)

//Seteo de Datos "SUCURSAL CUENTA"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('SUCURSAL CUENTA', '001')

//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Maximizar Ventana
WebUI.maximizeWindow()

//Verificar "Fecha para Baja"
WebUI.verifyElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Baja Firmantes Programada/lblFechaParaBaja'))

//Capturar tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime
println(('Tiempo transcurrido: ' + elapsedTime) + ' milisegundos')

//Verificar conteo de registros
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/02-Filtros/lblResultados'))
totalRegistros = WebUI.getText(findTestObject('Object Repository/00-Utils/02-Filtros/lblResultados'))
println(totalRegistros)

//Validar "Fecha para Baja"
fechaBaja = WebUI.getText(findTestObject('Object Repository/08-Cheques Rechazados/Baja Firmantes Programada/lblFechaParaBaja'))
assert fechaBaja == "FECHA PARA BAJA"

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}