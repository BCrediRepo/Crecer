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

WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkComisionesyBonificaciones'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/4-Comisiones/1-Comisiones y Bonificaciones/lnkComisiones'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/4-Comisiones/1-Comisiones y Bonificaciones/lnkConsulta de Comisiones Cobradas'))

//Abre la pestaña BCCL.E.AC.COM.COBRADA
WebUI.switchToWindowTitle('BCCL.E.AC.COM.COBRADA')

//Maximiza la pestaña
WebUI.maximizeWindow()

//Ingresa el numero de la cuenta
WebUI.setText(findTestObject('Object Repository/34-Riesgo Contingente/BCCL.E.AC.COM.COBRADA/txtCuenta'), '00890010860')

//Presiona botón ejecutar
WebUI.click(findTestObject('Object Repository/34-Riesgo Contingente/BCCL.E.AC.COM.COBRADA/lnkEjecutar'))

//Verifica que se muestre el titulo Tipo de comision
WebUI.waitForElementPresent(findTestObject('Object Repository/34-Riesgo Contingente/BCCL.E.AC.COM.COBRADA/lblTipo de Comision'), 6)

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


