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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()

//Se accede al menú BCCL.E.TINTERNAS.APAGAR
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), ('ENQ ' + findTestData('Modulos/Modulos').getValue(4,18)))
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))
WebUI.waitForPageLoad(3)
WebUI.switchToWindowTitle(findTestData('Modulos/Modulos').getValue(4,18))
WebUI.maximizeWindow()

//Se clickea en Ejecutar y se valida la existencia de campos principales
WebUI.click(findTestObject('Object Repository/12-Transferencias Internas/BCCL.E.TINTERNAS.APAGAR/lnkEjecutar'))
WebUI.waitForElementVisible(findTestObject('Object Repository/12-Transferencias Internas/BCCL.E.TINTERNAS.APAGAR/tdReferencia'),3)
WebUI.waitForElementVisible(findTestObject('Object Repository/12-Transferencias Internas/BCCL.E.TINTERNAS.APAGAR/tdFTEnvio'),3)

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'('Screenshot/Fails/'+ 'TI02' +'.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}
