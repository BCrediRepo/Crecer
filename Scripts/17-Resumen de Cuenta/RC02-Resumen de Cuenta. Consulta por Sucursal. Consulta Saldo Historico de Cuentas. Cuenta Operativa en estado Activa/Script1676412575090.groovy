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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
//CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,24), findTestData('MainData/Users').getValue(2,24))
WebUI.maximizeWindow()

//Setear "ENQ BCCL.E.RES.CTA.HIS.SALDO" en el buscador
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.RES.CTA.HIS.SALDO')

//Seleccionar boton buscar
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana "saldos historicos de Cuenta"
WebUI.switchToWindowIndex(1)

//Seteo de Datos "Cuenta", "Fecha"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cuenta','00010026843')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha','20220701')

//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verificar "Estado de la cuenta"
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/02-Saldos Historicos de Cuenta/lblEstadoActiva'))

//Validar "Estado de la cuenta"
def element = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/02-Saldos Historicos de Cuenta/lblEstadoActiva'))
assert element.contains('ACTIVA')

//Verificar "Nro. de Cuenta"
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/02-Saldos Historicos de Cuenta/lblNro.deCuenta'))

//Validar "00010026843 CARNES WAGYU ARG SA"
def element2 = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/02-Saldos Historicos de Cuenta/lblNro.deCuenta'))
assert element2.contains('00010026843')

//Verificar "Saldo Cuenta del Día seteado"
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/02-Saldos Historicos de Cuenta/lblSaldoCuentaalDia'))

//Validar "Saldo Cuenta del Día seteado"
def element3 = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/02-Saldos Historicos de Cuenta/lblSaldoCuentaalDia'))
assert element3.contains('Saldo cuenta al 01/07/2022')

//Verificar "Total Creditos Dia"
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/02-Saldos Historicos de Cuenta/lblTotalCreditosDia'))

//Validar "Total Creditos Dia"
def element4 = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/02-Saldos Historicos de Cuenta/lblTotalCreditosDia'))
assert element4.contains('Total Creditos Dia')

//Verificar "Total Debitos Dia"
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/02-Saldos Historicos de Cuenta/lblTotalDebitosDia'))

//Validar "Total Debitos Dia"
def element5 = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/02-Saldos Historicos de Cuenta/lblTotalDebitosDia'))
assert element5.contains('Total Debitos Dia')

//Capturar tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))

TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))

println TotalRegistros

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}