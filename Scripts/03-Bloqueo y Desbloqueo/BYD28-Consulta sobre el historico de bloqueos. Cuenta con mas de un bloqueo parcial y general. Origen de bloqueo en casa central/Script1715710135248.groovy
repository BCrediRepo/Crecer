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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(
        2, 1))

def menuDesplegable0 = ["Cuentas", "Modificacion de cuenta", "Bloqueo y Desbloqueo", "Consultas"]
def link0 = "Historial de Bloqueos Inactivos"

//Si el menu que busco está en dashboard uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable0, link0)

WebUI.switchToWindowTitle('BCCL.AC.CONS.HIST.BLOQ')//BCCL.AC.CTABLOQ.SUC

//Seteo de Datos "Cuenta" y "Fecha de Bloqueo"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cuenta', '00010035377')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha de Bloqueo', '20230828')

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebUI.verifyElementVisible(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CONS.HIST.BLOQ/lblFechaBloqueo'))

fecha = WebUI.getText(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CONS.HIST.BLOQ/lblFechaBloqueo'))

assert fecha == '20230828'

WebUI.verifyElementVisible(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CONS.HIST.BLOQ/lblTipoBloqueo'))

tipobloq = WebUI.getText(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CONS.HIST.BLOQ/lblTipoBloqueo'))

assert tipobloq == '1'

WebUI.verifyElementVisible(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CONS.HIST.BLOQ/lblMotivoBloqueo'))

motivobloq = WebUI.getText(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CONS.HIST.BLOQ/lblMotivoBloqueo'))

assert motivobloq == 'Oficio AFIP'

WebUI.verifyElementVisible(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CONS.HIST.BLOQ/lblTipoBloqueo2'))

tipobloq = WebUI.getText(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CONS.HIST.BLOQ/lblTipoBloqueo2'))

assert tipobloq == '2'

WebUI.verifyElementVisible(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CONS.HIST.BLOQ/lblMotivoBloqueo2'))

motivobloq = WebUI.getText(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CONS.HIST.BLOQ/lblMotivoBloqueo2'))

assert motivobloq == 'Oficio Papel' //Control fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

