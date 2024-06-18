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
import java.text.SimpleDateFormat
import java.util.Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
        2, 9))

//busqueda en sucursal piloto y apertura de aplicación
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?1')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('02-Dashboard/lnkSucursalPiloto'))

WebUI.click(findTestObject('17-Remesas/Temenos T24/lnkD2-AutomatizacionDeSuc'))

WebUI.click(findTestObject('17-Remesas/Temenos T24/lnkTesoreroGeneral'))

WebUI.click(findTestObject('17-Remesas/Temenos T24/lnkAdministracionDeTesoreroGeneral'))

WebUI.click(findTestObject('17-Remesas/Temenos T24/lnkConsultas'))

WebUI.click(findTestObject('17-Remesas/Temenos T24/lnkControlDeSaldosAseguradosEnLinea'))

WebUI.switchToWindowTitle('BCCL.E.TT.TESORERIA.GENERAL')

//Seteo de Datos "Fecha", "Sucursal"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha','20240729')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal','074')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//boton ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Verificiacion de mensaje de error
WebUI.verifyElementVisible(findTestObject('17-Remesas/BCCL.E.TT.TESORERIA.GENERAL/lblMensajeDeError'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

Mensaje = WebUI.getText(findTestObject('17-Remesas/BCCL.E.TT.TESORERIA.GENERAL/lblMensajeDeError'))
WebUI.verifyElementVisible(findTestObject('17-Remesas/BCCL.E.TT.TESORERIA.GENERAL/lblFechaMayorAHoy'))
label = WebUI.getText(findTestObject('17-Remesas/BCCL.E.TT.TESORERIA.GENERAL/lblFechaMayorAHoy'))
assert Mensaje == "Mensaje de Error"
assert label == "FECHA no debe ser mayor que la de hoy"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}