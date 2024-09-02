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

def moneda = 'ARS'
def fechaD = '20200301'
def dispositivo = '00000'
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 2), findTestData('MainData/Users').getValue(2, 2))
WebUI.maximizeWindow()

//Ingresar "ENQ BCCL.E.TXN.PNEUTRAL.CAT" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.TXN.PNEUTRAL.CAT')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "BCCL.E.TXN.PNEUTRAL.CAT"
WebUI.switchToWindowTitle('BCCL.E.TXN.PNEUTRAL.CAT')

//Filtro limpieza
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "BCCL.E.TXN.PNEUTRAL.CAT"
WebUI.switchToWindowTitle('BCCL.E.TXN.PNEUTRAL.CAT')

//Maximizar Ventana
WebUI.maximizeWindow()

//Busco la Cuenta a Desbloquear
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Moneda', moneda)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha desde', fechaD)
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Dispositivo', dispositivo)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verificar "Puntos neutrales de la 099"
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/07-BCCL.E.TXN.PNEUTRAL.CAT/lblPuntosneutralesdela099'))

//Validar "Puntos neutrales de la 099"
def element = WebUI.getText(findTestObject('Object Repository/17-Remesas/07-BCCL.E.TXN.PNEUTRAL.CAT/lblPuntosneutralesdela099'))
assert element.contains(' Puntos neutrales de la 099')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}