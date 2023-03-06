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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,11), findTestData('MainData/Users').getValue(2,11))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('02-Dashboard/lnkMovimientosAutomaticos'))
WebUI.click(findTestObject('02-Dashboard/14-Movimientos Automaticos/lnkConsultaMovimientosHistoricos'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.switchToWindowTitle('BCCL.E.MOV.AUT.TRANS.HIST')
WebUI.click(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.HIST/lnkNueva Seleccion'))
WebUI.click(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.HIST/lnkEjecutar'))
WebUI.click(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.HIST/btnNuevaBusqueda'))
WebUI.setText(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.HIST/txtNroCta_value111'), '10430040953')
WebUI.click(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.HIST/lnkEjecutar'))
WebUI.click(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.HIST/btnVista'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.switchToWindowTitle('Mov-Automaticos Data')
trx = WebUI.verifyElementVisible(findTestObject('16-Movimientos Automaticos/Mov-Automaticos Data/lblOperacion'))

if (trx == true) {
    WebUI.maximizeWindow()
    CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
	}
else {
	WebUI.maximizeWindow()
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
}

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
