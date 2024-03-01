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
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(
        2, 1))

//Apertura de app
WebUI.click(findTestObject('02-Dashboard/lnkANSES'))
WebUI.click(findTestObject('02-Dashboard/57-ANSES/lnkDetalleHistoricoBeneficioPagoImpago'))
WebUI.switchToWindowTitle('BCCL.NOF.ANSES.GENERAL')

//Limpieza de filtro
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)
WebUI.click(findTestObject('02-Dashboard/57-ANSES/lnkDetalleHistoricoBeneficioPagoImpago'))
WebUI.switchToWindowTitle('BCCL.NOF.ANSES.GENERAL')

//Seteo de datos
WebUI.setText(findTestObject('10-ANSES/BCCL.NOF.ANSES.GENERAL/txtNroBeneficiario'), '88141171370')
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Verifica que el beneficiario tenga un estado "historico" y lo valida con un assert
WebUI.verifyElementVisible(findTestObject('10-ANSES/BCCL.NOF.ANSES.GENERAL/lblHistorico'))
estado = WebUI.getText(findTestObject('10-ANSES/BCCL.NOF.ANSES.GENERAL/lblHistorico'))
assert estado.contains("Historico") == true

WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Control fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}


