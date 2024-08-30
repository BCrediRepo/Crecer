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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 8), findTestData('MainData/Users').getValue(2, 8))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se accede al menu ?21
//WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?21')
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//WebUI.switchToWindowTitle('Temenos T24')
//WebUI.maximizeWindow()
//WebUI.click(findTestObject('Object Repository/21-Fallas/02-Temenos T24/spanConsulta Dispositivos Puntos Neutrales'))
//WebUI.click(findTestObject('Object Repository/21-Fallas/02-Temenos T24/lnkConsultaDeSobrantesEnPuntosNeutrales'))
WebUI.switchToWindowTitle('BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN')
WebUI.maximizeWindow()

//Seteo de Datos "Sucursal", "Fecha Desde"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Desde','20230831')
//WebUI.setText(findTestObject('Object Repository/21-Fallas/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/txtFechaDesde'), GlobalVariable.vFechaCOB)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal','073')
//WebUI.setText(findTestObject('Object Repository/21-Fallas/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/txtIdDispositivo'), '04312100000035')
//WebUI.setText(findTestObject('Object Repository/21-Fallas/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/txtCartuchoGaveta'), 'RETIRO')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

WebUI.waitForElementVisible(findTestObject('Object Repository/21-Fallas/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/cbxSeleccion'), 6)

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println(('Tiempo transcurrido: ' + elapsedTime) + ' milisegundos')

WebUI.selectOptionByIndex(findTestObject('Object Repository/21-Fallas/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/cbxSeleccion'), 6)

WebUI.click(findTestObject('Object Repository/21-Fallas/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/img'))

//Switch a la ventana TELLER
WebUI.switchToWindowTitle('TELLER')

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/21-Fallas/06-TELLER/lblALTA Sobrante en Disp Pn - Deposito ALTA Sobrante en Disp Pn'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/21-Fallas/06-TELLER/lblALTA Sobrante en Disp Pn - Deposito ALTA Sobrante en Disp Pn'))
def element = WebUI.getText(findTestObject('Object Repository/21-Fallas/06-TELLER/lblALTA Sobrante en Disp Pn - Deposito ALTA Sobrante en Disp Pn'))
assert element.contains('ALTA Sobrante en Disp Pn - Retiro ALTA Sobrante en Disp Pn -')

//WebUI.waitForElementVisible(findTestObject('Object Repository/21-Fallas/04-Movimientos de Fondos/lblSOBRANTES EN DISPOSITIVO MAP'), 6) 

//---------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}