package pkgModules

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class kywHabilitarCL {

	def kywG = new pkgModules.kywGeneric()
	def kywS = new pkgModules.kywScreenshot()
	def kywCL = new pkgModules.kywUserForCommandLine()

	@Keyword
	def HabilitarCL(String userH) {
		//Configuracion de ambiente
		kywG.ConfigEnvironment(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

		//--- Ingreso de credenciales para el Login ---
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLGNUser'),findTestData('MainData/Users').getValue(1,12))//GlobalVariable.vUser)
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLGNPassword'), findTestData('MainData/Users').getValue(2,12))//GlobalVariable.vPass)
		WebUI.click(findTestObject('Object Repository/01-Login/btnLGNSignIn'))
		WebUI.delay(3)
		WebUI.maximizeWindow()
		kywS.takeScreenshotInScript()

		//Se ingresa el comando USER,
		WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), ('USER,'))
		WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

		//Se cambia a la ventana USER PROFILE
		WebUI.switchToWindowTitle('USER PROFILE')
		//WebUI.maximizeWindow()

		//Se ingresa el usuario a habilitar
		def user = kywCL.procesarUsuario(userH)
		WebUI.setText(findTestObject('Object Repository/00-Command Line/inputUSER,'),(user))
		WebUI.click(findTestObject('Object Repository/00-Command Line/btnModificarRegistro'))
		WebUI.click(findTestObject('Object Repository/00-Command Line/USER.PROFILE/btnAgregarAtributos1'))
		WebUI.click(findTestObject('Object Repository/00-Command Line/USER.PROFILE/cbAtributos2'))
		WebUI.selectOptionByIndex(findTestObject('Object Repository/00-Command Line/USER.PROFILE/cbAtributos2'), 2)
		kywS.takeScreenshotInScript()
		WebUI.click(findTestObject('Object Repository/00-Command Line/USER.PROFILE/btnAceptarRegistro'))
		WebUI.click(findTestObject('Object Repository/00-Command Line/USER.PROFILE/lnkAceptarAlertas'))
		
		WebUI.closeBrowser()
	}
}
