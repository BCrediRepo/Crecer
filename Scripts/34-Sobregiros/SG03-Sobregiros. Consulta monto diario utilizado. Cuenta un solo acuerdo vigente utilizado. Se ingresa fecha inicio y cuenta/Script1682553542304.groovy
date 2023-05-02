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

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/01-LOACC/spanLOACC'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/01-LOACC/span_Consulta'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/01-LOACC/lnkConsulta de LOACC'))

WebUI.switchToWindowTitle('Consulta de Sobregiros')

WebUI.maximizeWindow()

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/01-LOACC/lnkConsulta de LOACC'))

WebUI.switchToWindowTitle('Consulta de Sobregiros')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/35-Sobregiros/Consulta de sobregiros/txtCuenta'), '00430014075')

WebUI.setText(findTestObject('Object Repository/35-Sobregiros/Consulta de sobregiros/txtLinea de sobregiro'), 'ADSACT')

WebUI.click(findTestObject('Object Repository/35-Sobregiros/Consulta de sobregiros/lnkEjecutar'))

WebUI.waitForElementPresent(findTestObject('Object Repository/35-Sobregiros/Consulta de sobregiros/cbxConsultar'), 6)

WebUI.selectOptionByIndex(findTestObject('Object Repository/35-Sobregiros/Consulta de sobregiros/cbxConsultar'), 2)

WebUI.click(findTestObject('Object Repository/35-Sobregiros/Consulta de sobregiros/btnDrillDrown'))

WebUI.switchToWindowTitle('BCCL.AC.ACUERDO')

WebUI.waitForElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.AC.ACUERDO/lblCONSULTA'), 6)

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





