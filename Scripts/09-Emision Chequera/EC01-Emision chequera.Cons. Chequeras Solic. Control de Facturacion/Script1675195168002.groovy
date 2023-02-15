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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()

//Se accede al menu Administracion de piezas
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CHQ.SOL.IMPRENTA')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))
//Switch a la ventana de busqueda de consulta
WebUI.switchToWindowTitle('BCCL.E.CHQ.SOL.IMPRENTA')

WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/01-BCCL.E.CHQ.SOL.IMPRENTA/txtImprenta'), 6)
WebUI.setText(findTestObject('Object Repository/11-Emision Chequera/01-BCCL.E.CHQ.SOL.IMPRENTA/txtImprenta'), '02')
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/01-BCCL.E.CHQ.SOL.IMPRENTA/lnkEjecutar'))

WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/01-BCCL.E.CHQ.SOL.IMPRENTA/lblImprentaTitulo'), 30)
WebUI.takeScreenshot('Screenshot/Emision Chequera/Emision chequera.Cons. Chequeras Solic. Control de Facturacion' + nowString + '01' + '.png')
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/01-BCCL.E.CHQ.SOL.IMPRENTA/lnkVerSolicitudes'))

//Switch a la ventana de tipo de chequera
WebUI.switchToWindowTitle('BCCL.E.CHQ.SOL.TIPO')

WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/01-BCCL.E.CHQ.SOL.IMPRENTA/lblTipochequeraTitulo'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/11-Emision Chequera/01-BCCL.E.CHQ.SOL.IMPRENTA/lblTipochequeraTitulo'), 6)
WebUI.takeScreenshot('Screenshot/Emision Chequera/Emision chequera.Cons. Chequeras Solic. Control de Facturacion' + nowString + '02' + '.png')

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
