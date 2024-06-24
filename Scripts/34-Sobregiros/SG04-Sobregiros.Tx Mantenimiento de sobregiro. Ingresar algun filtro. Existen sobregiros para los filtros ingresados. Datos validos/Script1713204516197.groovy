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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se ingresa el menu ?1 en el command line
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscar'), '?1')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.maximizeWindow()

//Se ingresa al menu "sucursal Piloto"
WebUI.click(findTestObject('02-Dashboard/lnkSucursalPiloto'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/02-Temenos T24/spanD3-CC4'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/02-Temenos T24/spanMantenimiento de Sobregiros'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/02-Temenos T24/spanMantenimiento'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/02-Temenos T24/lnkMantenimento de Sobregiro'))

WebUI.switchToWindowTitle('Consulta de Sobregiros')

WebUI.maximizeWindow()

//Seteo de Datos "Cuenta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cuenta', '00430014075')

//Click en btn ejecutar
WebUI.click(findTestObject('Object Repository/35-Sobregiros/Consulta de sobregiros/lnkEjecutar'))

//Verificamos el lbl cuenta
WebUI.waitForElementVisible(findTestObject('Object Repository/35-Sobregiros/Consulta de sobregiros/lblCuenta'), 6)

//ASSERT
def element = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/Consulta de sobregiros/lblCuenta'))
assert element.contains('00430014075')

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






