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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,7), findTestData('MainData/Users').getValue(2,7))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresar "ENQ BCCL.E.AS.AJUS.DEPOSITOS" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.AS.AJUS.DEPOSITOS')

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana "BCCL.E.AS.AJUS.DEPOSITOS"
WebUI.switchToWindowIndex(1)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '074')

//Maximizar Pantalla
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Moneda', 'ARS')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Ejecutar"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Seleccionar "boton ID"
WebUI.click(findTestObject('Object Repository/26-Dispositivos/BCCL.E.AS.AJUS.DEPOSITOS/btnID'))

//Verificar "ID"
WebUI.verifyElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.E.AS.AJUS.DEPOSITOS/lblID'))

//Validar "ID"
def element = WebUI.getText(findTestObject('Object Repository/26-Dispositivos/BCCL.E.AS.AJUS.DEPOSITOS/lblID'))
assert element.contains('ID')

//Verificar "TARJETA"
WebUI.verifyElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.E.AS.AJUS.DEPOSITOS/lblTARJETA'))

//Validar "TARJETA"
def element2 = WebUI.getText(findTestObject('Object Repository/26-Dispositivos/BCCL.E.AS.AJUS.DEPOSITOS/lblTARJETA'))
assert element2.contains('TARJETA')

//Verificar "SECUENCIA"
WebUI.verifyElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.E.AS.AJUS.DEPOSITOS/lblSECUENCIA'))

//Validar "SECUENCIA"
def element3 = WebUI.getText(findTestObject('Object Repository/26-Dispositivos/BCCL.E.AS.AJUS.DEPOSITOS/lblSECUENCIA'))
assert element3.contains('SECUENCIA')

//Verificar "MONTO DEPOSITADO"
WebUI.verifyElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.E.AS.AJUS.DEPOSITOS/lblMONTODEPOSITADO'))

//Validar "MONTO DEPOSITADO"
def element4 = WebUI.getText(findTestObject('Object Repository/26-Dispositivos/BCCL.E.AS.AJUS.DEPOSITOS/lblMONTODEPOSITADO'))
assert element4.contains('MONTO DEPOSITADO')

//Verificar "MONTO REAL DEPOSITO"
WebUI.verifyElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.E.AS.AJUS.DEPOSITOS/lblMONTOREALDEPOSITO'))

//Validar "MONTO REAL DEPOSITO"
def element5 = WebUI.getText(findTestObject('Object Repository/26-Dispositivos/BCCL.E.AS.AJUS.DEPOSITOS/lblMONTOREALDEPOSITO'))
assert element5.contains('MONTO REAL DEPOSITO')

//Verificar "DIFERENCIA"
WebUI.verifyElementVisible(findTestObject('Object Repository/26-Dispositivos/BCCL.E.AS.AJUS.DEPOSITOS/lblDIFERENCIA'))

//Validar "DIFERENCIA"
def element6 = WebUI.getText(findTestObject('Object Repository/26-Dispositivos/BCCL.E.AS.AJUS.DEPOSITOS/lblDIFERENCIA'))
assert element6.contains('DIFERENCIA')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}