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

public class kywHabilitarCommandLine {

	def kywG = new pkgModules.kywGeneric()
	def kywS = new pkgModules.kywScreenshot()
	
	@Keyword
	def procesarUsuario(String usuario) {
		// Verificar el usuario es nulo o tiene menos de dos caractes
		if (usuario == null || usuario.length() < 2) {
			usuario = "Usuario no valido";
		} else {
			char segundoCaracter = usuario.charAt(1);
			// Verificar si el segundo caracter es una letra
			if (Character.isLetter(segundoCaracter)) {
				usuario = usuario;
			} else if (Character.isDigit(segundoCaracter)) {
				// Verificar si el usuario tiene al menos 4 caracteres
				if (usuario.length() >= 4) {
					usuario = usuario.substring(usuario.length() - 4);
				} else {
					usuario = "Usuario no valido";
				}
			} else {
				usuario = "Usuario no valido";
			}
		}
		return 'B.'+usuario;
	}

	@Keyword
	def habilitarCommandLine(String usuario) {
		//Configuracion de ambiente
		kywG.ConfigEnvironment(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

		//--- Ingreso de credenciales para el Login ---
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLGNUser'),findTestData('MainData/Users').getValue(1,12))
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLGNPassword'), findTestData('MainData/Users').getValue(2,12))
		WebUI.click(findTestObject('Object Repository/01-Login/btnLGNSignIn'))
		WebUI.delay(3)
		WebUI.maximizeWindow()
		kywS.takeScreenshotInScript()

		//Se ingresa el comando USER,
		WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), ('USER,'))
		WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

		//Se cambia a la ventana USER PROFILE
		WebUI.switchToWindowTitle('USER PROFILE')
		WebUI.delay(1)
		WebUI.maximizeWindow()

		//Se ingresa el usuario a habilitar
		def user = procesarUsuario(usuario)
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
