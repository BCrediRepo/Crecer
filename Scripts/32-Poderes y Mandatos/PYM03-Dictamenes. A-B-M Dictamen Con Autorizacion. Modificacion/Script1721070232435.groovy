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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,8), findTestData('MainData/Users').getValue(2,8))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Ingreso al menu ?29

WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?29')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/02-Dashboard/31-Poderes y Mandatos/01-Dictamenes/spanDictamenes'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/31-Poderes y Mandatos/01-Dictamenes/lnkABM Dictamen'))

WebUI.switchToWindowTitle('Ingreso de Relaciones JUR')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/33-Poderes y Mandatos/Ingreso de Relaciones JUR/txtIDPersona'), '1004425912')

WebUI.click(findTestObject('Object Repository/33-Poderes y Mandatos/Ingreso de Relaciones JUR/lnkEjecutar'))

WebUI.click(findTestObject('Object Repository/33-Poderes y Mandatos/Ingreso de Relaciones JUR/btnDrilldown'))

WebUI.switchToWindowTitle('Relacion Entre Socios')

//Click en el "+" para agregar socio relacionado
WebUI.click(findTestObject('Object Repository/33-Poderes y Mandatos/Relacion Entre Socios/btnExpandirMultivalor'))

//Seteo datos para el nuevo socio relacionado
WebUI.setText(findTestObject('Object Repository/33-Poderes y Mandatos/Relacion Entre Socios/txtIdSocio2'), "1004417459")
WebUI.setText(findTestObject('Object Repository/33-Poderes y Mandatos/Relacion Entre Socios/txtTipoCargoSocio2'), "2")
WebUI.setText(findTestObject('Object Repository/33-Poderes y Mandatos/Relacion Entre Socios/txtGrupoFirmaSocio2'), "01")
WebUI.setText(findTestObject('Object Repository/33-Poderes y Mandatos/Relacion Entre Socios/txtTipoFirmaSocio2'), "00")

//click "aceptar registro"
WebUI.click(findTestObject('Object Repository/33-Poderes y Mandatos/Relacion Entre Socios/btnAceptarRegistro'))

WebUI.waitForElementPresent(findTestObject('Object Repository/33-Poderes y Mandatos/Relacion Entre Socios/lblTxnCompleta'), 6)

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/33-Poderes y Mandatos/Relacion Entre Socios/lblTxnCompleta'))

//Validar "Txn Completa"
def element = WebUI.getText(findTestObject('Object Repository/33-Poderes y Mandatos/Relacion Entre Socios/lblTxnCompleta'))
assert element.contains('Txn Completa')

//pasos para eliminar el socio que hemos creado
WebUI.click(findTestObject('Object Repository/33-Poderes y Mandatos/Relacion Entre Socios/lnkIngresoRelacJUR'))

WebUI.click(findTestObject('Object Repository/33-Poderes y Mandatos/Ingreso de Relaciones JUR/btnDrilldown'))

//elimino el socio relacionado creado anteriormente
WebUI.click(findTestObject('Object Repository/33-Poderes y Mandatos/Relacion Entre Socios/btnDeleteValue'))

//click "aceptar registro"
WebUI.click(findTestObject('Object Repository/33-Poderes y Mandatos/Relacion Entre Socios/btnAceptarRegistro'))

//click "aceptar registro"
WebUI.click(findTestObject('Object Repository/33-Poderes y Mandatos/Relacion Entre Socios/btnAceptarRegistro'))

WebUI.waitForElementPresent(findTestObject('Object Repository/33-Poderes y Mandatos/Relacion Entre Socios/lblTxnCompleta'), 6)

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/33-Poderes y Mandatos/Relacion Entre Socios/lblTxnCompleta'))

//Validar "Txn Completa"
def element2 = WebUI.getText(findTestObject('Object Repository/33-Poderes y Mandatos/Relacion Entre Socios/lblTxnCompleta'))
assert element2.contains('Txn Completa')

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







