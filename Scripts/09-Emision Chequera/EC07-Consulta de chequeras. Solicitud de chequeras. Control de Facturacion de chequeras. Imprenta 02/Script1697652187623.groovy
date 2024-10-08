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
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 4), findTestData('MainData/Users').getValue(2, 4))

WebUI.maximizeWindow()

//Ejecuta en la linea de comando ENQ BCCL.E.CHQ.SOL.IMPRENTA
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("ENQ BCCL.E.CHQ.SOL.IMPRENTA", 1)

//Verifica titulo BCCL.E.CHQ.SOL.IMPRENTA
WebUI.verifyElementVisible(findTestObject('Object Repository/11-Emision Chequera/01-BCCL.E.CHQ.SOL.IMPRENTA/lblTituloBCCL.E.CHQ.SOL.IMPRENTA'))

//Seteo de datos "Imprenta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Imprenta', '02')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Desde', '20230806')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Hasta', '20230825')
//Maximiza la pantalla
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Selecciona boton EJECUTAR
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Espera y Verifica que devuelva un registro
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/01-BCCL.E.CHQ.SOL.IMPRENTA/lblTipodeChequera'),6)

WebUI.verifyElementVisible(findTestObject('Object Repository/11-Emision Chequera/01-BCCL.E.CHQ.SOL.IMPRENTA/lblTipodeChequera'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println(('Tiempo transcurrido: ' + elapsedTime) + ' milisegundos')

def element = WebUI.getText(findTestObject('Object Repository/11-Emision Chequera/01-BCCL.E.CHQ.SOL.IMPRENTA/lblTipodeChequera'))

assert element.contains('Tipo de Chequera')

//---------------------------
//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))

TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))

println(TotalRegistros)

//-----------------------------
//Selecciona Ver Solicitudes
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/01-BCCL.E.CHQ.SOL.IMPRENTA/lnkVerSolicitudes'))

//Espera y Verifica que devuelva un registro
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/BCCL.E.CHQ.SOL.TIPO/lblSolicitud'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/11-Emision Chequera/BCCL.E.CHQ.SOL.TIPO/lblSolicitud'))

def element2 = WebUI.getText(findTestObject('Object Repository/11-Emision Chequera/BCCL.E.CHQ.SOL.TIPO/lblSolicitud'))

assert element2.contains('Solicitud')

//Selecciona Ver Detalle
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/BCCL.E.CHQ.SOL.TIPO/lnkVerDetalle'))

//Espera y Verifica que devuelva un registro
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/BCCL.CQ.SOLICITUD/lblCqCta'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/11-Emision Chequera/BCCL.CQ.SOLICITUD/lblCqCta'))

def element3 = WebUI.getText(findTestObject('Object Repository/11-Emision Chequera/BCCL.CQ.SOLICITUD/lblCqCta'))

assert element3.contains('Cq Cta')

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}
@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}