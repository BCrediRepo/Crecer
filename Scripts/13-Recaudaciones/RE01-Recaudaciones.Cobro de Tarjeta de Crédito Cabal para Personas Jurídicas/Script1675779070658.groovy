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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 64), findTestData('MainData/Users').getValue(
        2, 64))

WebUI.maximizeWindow()

//Se accede al menu 
//WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.VISION.PAGO.TJ')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana de busqueda de consulta
WebUI.switchToWindowTitle('BCCL.E.VISION.PAGO.TJ')

//Seteo de Datos "Persona"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Persona', '1000000011') //1004140888

//Seleccionar Ejecutar
WebUI.click(findTestObject('Object Repository/14-Recaudaciones/01-BCCL.E.VISION.PAGO.TJ/lnkEjecutar'))

//Seleccionar Pagar
WebUI.click(findTestObject('Object Repository/14-Recaudaciones/01-BCCL.E.VISION.PAGO.TJ/lnkPagar'))

//Switch a la ventana de busqueda de seleccion de pago
WebUI.switchToWindowTitle('Pago de Tarjetas S/ Cod Barra. - Fil.042 Saavedra')

//WebUI.waitForElementVisible(findTestObject('Object Repository/14-Recaudaciones/01-BCCL.E.VISION.PAGO.TJ/selectTipo'), 6)
WebUI.selectOptionByIndex(findTestObject('Object Repository/14-Recaudaciones/01-BCCL.E.VISION.PAGO.TJ/selectTipo'), '1')

//WebUI.waitForElementVisible(findTestObject('Object Repository/14-Recaudaciones/01-BCCL.E.VISION.PAGO.TJ/txtImporte'), 6)
WebUI.setText(findTestObject('Object Repository/14-Recaudaciones/01-BCCL.E.VISION.PAGO.TJ/txtImporte'), '1')

WebUI.click(findTestObject('Object Repository/14-Recaudaciones/01-BCCL.E.VISION.PAGO.TJ/imgAceptarRegistro'))

//Switch a la ventana de pago realizado
WebUI.switchToWindowTitle('BCCL.E.EST.PAGO.TARJ')

//Validacion del Pago
WebUI.verifyElementVisible(findTestObject('Object Repository/14-Recaudaciones/01-BCCL.E.VISION.PAGO.TJ/lblPAGODETARJETACABAL'))

def noRec = WebUI.getText(findTestObject('Object Repository/14-Recaudaciones/01-BCCL.E.VISION.PAGO.TJ/lblPAGODETARJETACABAL'))

assert noRec.contains('PAGO DE TARJETA:') //---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

