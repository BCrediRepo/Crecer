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
import com.kms.katalon.core.testobject.ConditionType
import internal.GlobalVariable

public class kywHabilitarCommandLine {

	def kywG = new pkgModules.kywGeneric()
	def kywS = new pkgModules.kywScreenshot()

	@Keyword
	def procesarUsuario(String usuario) {
		// Verificar el usuario es nulo o tiene menos de dos caractes
		if (usuario == null || usuario.length() < 2 || usuario == "CRECEREM" || usuario == "CRECERAB" || usuario == "CRECERAC" || usuario == "CRECERAD") {
			usuario = null
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
					usuario = null
				}
			} else {
				usuario = null
			}
		}
		return 'B.'+usuario;
	}

	@Keyword
	def habilitarCommandLine(String usuario) {

		//Se ingresa el comando USER,
		WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), ('USER,'))
		WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

		//Se cambia a la ventana USER PROFILE
		WebUI.switchToWindowTitle('USER PROFILE')
		WebUI.delay(1)
		WebUI.maximizeWindow()

		//Se ingresa el usuario a habilitar
		def user = procesarUsuario(usuario)
		def fechaPW = "12 DIC 2028 M0612"
		WebUI.setText(findTestObject('Object Repository/00-Utils/01-CommandLine/inputUSER,'),(user))
		WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/btnModificarRegistro'))
		
		WebUI.setText(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/txtPwValidity'), fechaPW)
		TestObject imgTestObject = new TestObject().addProperty("xpath", ConditionType.EQUALS, '//*[@id="fieldCaption:ATTRIBUTES"]/following::img[1]')
		WebUI.click(imgTestObject)

		WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/cbAtributos2'))
		WebUI.selectOptionByIndex(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/cbAtributos2'), 2)		
		kywS.takeScreenshotInScript()
		WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/btnAceptarRegistro'))
		try {
			WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
			WebUI.delay(3)
			WebUI.closeWindowIndex(1)
			WebUI.switchToWindowIndex(0)
		} catch (Exception e) {
			WebUI.closeWindowIndex(1)
			WebUI.switchToWindowIndex(0)
		}
	}

}
