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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 11), findTestData('MainData/Users').getValue(2, 11))

//Seleccionar "Movimientos Automaticos"
WebUI.click(findTestObject('02-Dashboard/lnkMovimientosAutomaticos'))

//Seleccionar "Movimientos Historicos"
WebUI.click(findTestObject('02-Dashboard/14-Movimientos Automaticos/lnkConsultaMovimientosHistoricos'))

//Cambiar a la ventana "BCCL.E.MOV.AUT.TRANS.HIST"
WebUI.switchToWindowIndex(1)

//Seteo de Datos "Numero de Cuenta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Numero de Cuenta','10430040953')

//Maximizar Ventana
WebUI.maximizeWindow()

//Seleccionar la opcion "Mayor que" en el ComboBox
WebUI.selectOptionByIndex(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.HIST/cbFechadeProceso'), 6)

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha de Proceso', GlobalVariable.vFechaCOB)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Seleccionar icono de larga vista
WebUI.click(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.HIST/btnVista'))

//Sacar Captura
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verificar "Informacion del debito automatico"
WebUI.verifyElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/Mov-Automaticos Data/lblInfodebitoautomatico'))

//Validar "Informacion del debito automatico"
def element = WebUI.getText(findTestObject('Object Repository/16-Movimientos Automaticos/Mov-Automaticos Data/lblInfodebitoautomatico'))
assert element.contains('Informacion del debito automatico')

//Capturar tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}