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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


import internal.GlobalVariable

public class kywGeneric {

	/*----------------------------------------------------------------------------------------------*
	 *CONFIGURACION DE AMBIENTE					    																		*
	 *Configuracion de ambiente por defecto																			*
	 *												    											*
	 *Parametros:																					*
	 *IP servidor																					*
	 *Servidor de pruebas																			*
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def ConfigEnvironment(ServerIP, SeverTest) {
		//Configuracion de ambiente y generacion de URL.
		def vURL = 'http://' + ServerIP + '/' + SeverTest + '/servlet/BrowserServlet'
		WebUI.openBrowser(vURL)
	}
	/*----------------------------------------------------------------------------------------------*
	 *LOGIN																							*
	 *					    																		*
	 *Login por defecto 																			*
	 *												    											*
	 *Parametros:																					*
	 *User																					*
	 *Password																		*
	 *----------------------------------------------------------------------------------------------*/

	@Keyword
	def Login(User, Password) {
		//--- Ingreso de credenciales ---
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLGNUser'), User)//GlobalVariable.vUser)
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLGNPassword'), Password)//GlobalVariable.vPass)
		WebUI.click(findTestObject('Object Repository/01-Login/btnLGNSignIn'))

		//--- Validación del acceso ---
		//WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkMenuAutorizacionesModulos'))
		//WebUI.delay(2)
	}


	/*----------------------------------------------------------------------------------------------*
	 *CONTROL DE FALLAS																				*
	 *																								*
	 *vImgPath: Ubicacion de la imagen capturada con el error.										*
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def fFailStatus(def vImgPath){
		WebUI.takeScreenshot(vImgPath)
		WebUI.delay(3)
		WebUI.closeBrowser()
	}

	@Keyword
	def fPassStatus(){
		//WebUI.click(findTestObject('Object Repository/02-Dashboard/btnLogout'))
		//WebUI.delay(3)
		WebUI.closeBrowser()
	}
	//--------------------------------------------------------------------------------------------

}