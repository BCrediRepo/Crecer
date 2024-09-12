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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))
WebUI.maximizeWindow()

def menuDesplegable = ["Chequeras", "Consulta"]
def link = "Consulta de Chequeras y Cheques por Cuenta"

//Ejecuta en la linea de comando menu ?327
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("?327", 1)
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Abre la pestaña de cheques y chequeras por cuenta
WebUI.switchToWindowTitle('BCCL.E.CONS.CHQ.CUENTA.FIL')

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('CUENTA.DB', "00010623433")
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('FECHA.DESDE', "20220329")
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('FECHA.HASTA', "20220329")
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('CHQ.DESDE', "123")
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('CHQ.HASTA', "124")
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Espera y Verifica Mensaje de Error
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblMensajeErrorEnBusqueda'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblMensajeErrorEnBusqueda'))
def element = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblMensajeErrorEnBusqueda'))
assert element.contains('NO SE ENCONTRARON REGISTROS')

//---------------------------------------------------------------------------------------------------------------------

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}