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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 52), findTestData('MainData/Users').getValue(2, 52))
WebUI.maximizeWindow()

//Seleccionar "Remesas"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkRemesas'))

//Seleccionar "Administracion de Tesoros"
WebUI.click(findTestObject('Object Repository/02-Dashboard/15-Remesas/lnkAdministraciondeTesoros'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Selecionar "Consulta de Totales Anaquel/Prosegur"
WebUI.click(findTestObject('Object Repository/02-Dashboard/15-Remesas/02-Administracion de Tesoros/lnkConsultadeTotalesAnaquel-Prosegur'))

//Cambiar ventana "BCCL.E.NOF.CONS.ANAQUEL"
WebUI.switchToWindowTitle('BCCL.E.NOF.CONS.ANAQUEL')

//Maximizar Ventana
WebUI.maximizeWindow()

//Seteo de Datos "Moneda", "Tipo"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Moneda','ARS')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Tipo','ANAQUEL')

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verificar "Hora"
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/01-BCCL.E.NOF.CONS.ANAQUEL/lblHora'))

//Validar "Hora"
def element = WebUI.getText(findTestObject('Object Repository/17-Remesas/01-BCCL.E.NOF.CONS.ANAQUEL/lblHora'))
assert element.contains('Hora')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}