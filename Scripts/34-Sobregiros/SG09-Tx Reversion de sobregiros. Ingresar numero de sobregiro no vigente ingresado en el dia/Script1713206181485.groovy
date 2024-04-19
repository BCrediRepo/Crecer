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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()


//Ingreso en la linea de comando el Menu "?1"
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?1')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.maximizeWindow()

//Se ingresa al menu "sucursal Piloto"
WebUI.click(findTestObject('02-Dashboard/lnkSucursalPiloto'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/02-Temenos T24/spanD3-CC4'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/02-Temenos T24/spanMantenimiento de Sobregiros'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/02-Temenos T24/spanMantenimiento'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/02-Temenos T24/lnkReversion de Sobregiro'))

//Swicht a la ventana "Overdraft reverses"

WebUI.switchToWindowTitle('Overdraft reverses')

WebUI.maximizeWindow()

//Ingresamos los datos para la consulta

WebUI.setText(findTestObject('Object Repository/35-Sobregiros/Overdraft reverses/txtCuenta'), '00010000010')

WebUI.click(findTestObject('Object Repository/35-Sobregiros/Overdraft reverses/a_Ejecutar'))


//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.SGIRO.REV/lblNoHayDatosDisponibles'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.SGIRO.REV/lblNoHayDatosDisponibles'))

def element = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.SGIRO.REV/lblNoHayDatosDisponibles'))

assert element.contains('No hay datos disponibles')


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



