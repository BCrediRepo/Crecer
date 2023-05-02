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

// Ingreso al menu ?302

WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?302')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/02-Dashboard/spanPersonas3'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/29-Personas/spanConsulta'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/29-Personas/Consultas/spanConsultaUltimasModificaciones'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/29-Personas/Consultas/Consulta Ultimas Modificaciones/lnkConsultaUltimas modif a Pers Fisica'))

WebUI.switchToWindowTitle('Consulta Gral Pers Fisica Historia')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('Object Repository/02-Dashboard/29-Personas/Consultas/Consulta Ultimas Modificaciones/lnkConsultaUltimas modif a Pers Fisica'))

WebUI.switchToWindowTitle('Consulta Gral Pers Fisica Historia')

//WebUI.click(findTestObject('Object Repository/31-Personas/Consulta Gral Pers Fisica Historia/lnkNueva Seleccion'))
//
//WebUI.click(findTestObject('Object Repository/31-Personas/Consulta Gral Pers Fisica Historia/lnkEjecutar'))

//WebUI.switchToWindowTitle('BCCL.E.PER.GEN.PF.HIS')

//WebUI.click(findTestObject('Object Repository/31-Personas/Consulta Gral Pers Fisica Historia/btnLupa'))

WebUI.setText(findTestObject('Object Repository/31-Personas/Consulta Gral Pers Fisica Historia/txtIDpersona'), '1000873562')

WebUI.setText(findTestObject('Object Repository/31-Personas/Consulta Gral Pers Fisica Historia/txtNroDocumento'), '20144835')

WebUI.setText(findTestObject('Object Repository/31-Personas/Consulta Gral Pers Fisica Historia/txtApellido'), 'ENRICO')

WebUI.click(findTestObject('Object Repository/31-Personas/Consulta Gral Pers Fisica Historia/lnkEjecutar'))

WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/31-Personas/Consulta Gral Pers Fisica Historia/lnkVer Ult Modif'))

WebUI.click(findTestObject('Object Repository/31-Personas/Consulta Gral Pers Fisica Historia/spanConsulta Gral Pers Fisica Historia'))

WebUI.click(findTestObject('Object Repository/31-Personas/Consulta Gral Pers Fisica Historia/lnkVer Historico'))

WebUI.waitForElementVisible(findTestObject('Object Repository/31-Personas/Consulta Gral Pers Fisica Historia/lblLISTADOHISTORICO'), 6)

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







