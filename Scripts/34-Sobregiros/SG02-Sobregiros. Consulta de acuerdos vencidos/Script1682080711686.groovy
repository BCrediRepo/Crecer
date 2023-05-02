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

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/01-LOACC/spanLOACC'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/01-LOACC/span_Consulta'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/01-LOACC/a_Consulta de Acuerdos Vencidos'))

WebUI.maximizeWindow()

WebUI.switchToWindowTitle('BCCL.E.ACDOS.VENCIDOS')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/01-LOACC/a_Consulta de Acuerdos Vencidos'))

WebUI.switchToWindowTitle('BCCL.E.ACDOS.VENCIDOS')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/txtCuenta'), '00890010860')

WebUI.setText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/txtFecha Desde'), '20210719')

WebUI.setText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/txtFecha Hasta'), '20220725')

WebUI.click(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lnkEjecutar'))

WebUI.waitForElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lblCuenta'), 6)

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



