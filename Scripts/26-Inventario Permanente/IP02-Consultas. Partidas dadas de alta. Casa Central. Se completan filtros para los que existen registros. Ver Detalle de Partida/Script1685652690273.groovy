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
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 7), findTestData('MainData/Users').getValue(2, 7))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ejecuta en la linea de comando menu ?1
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("ENQ BCCL.E.IP.PARTIDAS.CC.ALTAS", 1)
WebUI.maximizeWindow()

//Seteo de Datos "Sucursal", "Codigo IP"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '001')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Codigo IP', '1008')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//boton ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

WebUI.maximizeWindow()

WebUI.verifyElementVisible(findTestObject('27-Inventario Permanente/BCCL.E.IP.PARTIDAS.CC.ALTAS/lblSucursal'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

WebUI.verifyElementVisible(findTestObject('Object Repository/27-Inventario Permanente/BCCL.E.IP.PARTIDAS.CC.ALTAS/lblSucursal'))
Sucursal = WebUI.getText(findTestObject('27-Inventario Permanente/BCCL.E.IP.PARTIDAS.CC.ALTAS/lblSucursal'))
assert Sucursal.contains('001')

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.E.IP.PARTIDAS.CC.ALTAS/lnkVerDetalle'))
WebUI.switchToWindowTitle('BCCL.IP.PARTIDAS')
WebUI.maximizeWindow()

WebUI.verifyElementVisible(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/lblCodigoIP'))

codigoIP = WebUI.getText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/lblCodigoIP'))
println(codigoIP)
assert codigoIP.contains('Codigo Ip')

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}
@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}
