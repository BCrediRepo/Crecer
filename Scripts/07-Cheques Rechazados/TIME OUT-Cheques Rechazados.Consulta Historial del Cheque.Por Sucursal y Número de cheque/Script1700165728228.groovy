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
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 10), findTestData('MainData/Users').getValue(2, 10))
WebUI.maximizeWindow()

//Seleccionar "Rechazo de Cheques"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkRechazodecheques'))

//Seleccionar "consultas"
WebUI.click(findTestObject('Object Repository/02-Dashboard/06-Cheques rechazados/lnkConsultas'))

//Seleccionar "Historial Cheques"
WebUI.click(findTestObject('Object Repository/02-Dashboard/06-Cheques rechazados/1-Consultas - Temenos T24/lnkHistorialCheque'))

//Cambia de ventana "Consulta historico de cheques rechazados - Fil.001 Centro"
WebUI.switchToWindowTitle('Consulta historico de cheques rechazados - Fil.001 Centro')

//Seleccionar "Nueva Seleccion"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/Filtros con frame/lnkNuevaSeleccion'))

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/Filtros con frame/lnkEjecutar'))

//Cerrar ventana
WebUI.closeWindowIndex(1)

//Cambiar a la ventana de Dashboard
WebUI.switchToWindowIndex(0)

//Seleccionar "Historial de Cheques"
WebUI.click(findTestObject('Object Repository/02-Dashboard/06-Cheques rechazados/1-Consultas - Temenos T24/lnkHistorialCheque'))

//Cambiar ventana "Consulta historico de cheques rechazados - Fil.001 Centro"
WebUI.switchToWindowTitle('Consulta historico de cheques rechazados - Fil.001 Centro')

//Setear "Numero de cuenta"
WebUI.setText(findTestObject('Object Repository/08-Cheques Rechazados/Consulta historico de cheques rechazados - Fil.001 Centro/txtNumerocuenta'), '00010043082')

//Setear "Numero de cheque desde"
WebUI.setText(findTestObject('Object Repository/08-Cheques Rechazados/Consulta historico de cheques rechazados - Fil.001 Centro/txtNumerochequedesde'), '6463')

//Setear "Numero de cheque hasta"
WebUI.setText(findTestObject('Object Repository/08-Cheques Rechazados/Consulta historico de cheques rechazados - Fil.001 Centro/txtNumerochequehasta'), '6463')

//Setear "Sucursal"
WebUI.setText(findTestObject('Object Repository/08-Cheques Rechazados/Consulta historico de cheques rechazados - Fil.001 Centro/txtSucursal'), '001')

//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton de Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/Filtros con frame/lnkEjecutar'))



//NO SE PUEDE SEGUIR AVANZANDO POR QUE DA TIME OUT



//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}