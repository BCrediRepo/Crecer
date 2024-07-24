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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,2), findTestData('MainData/Users').getValue(2,2))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresar "?1" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?1')

//Seleccionar boton de buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')

//Seleccionar "Sucursal Piloto"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/Temenos T24/lnkSucursalPiloto'))
	
//Seleccionar "D3 - CC3"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/Temenos T24/Sucursal Piloto/lnkD3-CC3'))

//Seleccionar "Cheques por Ventanilla"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/Temenos T24/Sucursal Piloto/D3 - CC3/lnkChequesporVentanilla'))

//Seleccionar "Consulta de reversos"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/Temenos T24/Sucursal Piloto/D3 - CC3/Cheques por Ventanilla/lnkConsultadereversos'))

//Cambiar ventana "BCCL.Q.CONS.REVE.CHQ"
WebUI.switchToWindowTitle('BCCL.Q.CONS.REVE.CHQ')

//Seteo de datos "Usuario", "Numero de Cuenta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Usuario', 'B.0489')
//CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Numero de Cuenta', '10730166496')

//Maximizar ventana
WebUI.maximizeWindow()

//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton de Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verificar "Id Operacion"
WebUI.verifyElementVisible(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.Q.CONS.REVE.CHQ/lblIdOperacion'))

//Capturar tiempo de finalización
long endTime = System.currentTimeMillis()
	
//Calcular diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime
println("Tiempo transcurrido: " + elapsedTime + " milisegundos")
	
//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))
TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))
println TotalRegistros

//Validar "Id Operacion"
idOperacion = WebUI.getText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.Q.CONS.REVE.CHQ/lblIdOperacion'))
assert idOperacion == "Id Operacion"

//Validar "Reversar"
reversar = WebUI.getText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.Q.CONS.REVE.CHQ/lnkReversar'))
assert reversar == "Reversar"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}