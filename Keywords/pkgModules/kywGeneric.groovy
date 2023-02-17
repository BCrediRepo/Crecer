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
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import internal.GlobalVariable
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.configuration.RunConfiguration
import org.openqa.selenium.Keys as Keys

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
	def LoginConEnvironment(User, Password) {
		//--- Ingreso de credenciales ---
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLGNUser'), User)//GlobalVariable.vUser)
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLGNPassword'), Password)//GlobalVariable.vPass)
		WebUI.click(findTestObject('Object Repository/01-Login/btnLGNSignIn'))
		WebUI.delay(3)
	}
	
	@Keyword
	def LoginConEnvironment(User, Password, ServerIP, ServerTest) {
		//--- Ingreso de credenciales ---
		ConfigEnvironment(ServerIP, ServerTest)
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLGNUser'), User)//GlobalVariable.vUser)
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLGNPassword'), Password)//GlobalVariable.vPass)
		WebUI.click(findTestObject('Object Repository/01-Login/btnLGNSignIn'))
		WebUI.delay(3)
	}
	
	@Keyword
	def LoginValidacionCommandLine(User, Password) {
		//--- Ingreso de credenciales ---
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLGNUser'), User)//GlobalVariable.vUser)
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLGNPassword'), Password)//GlobalVariable.vPass)
		WebUI.click(findTestObject('Object Repository/01-Login/btnLGNSignIn'))
		WebUI.delay(3)
		
		if (WebUI.verifyElementNotPresent(findTestObject('Object Repository/00-Command Line/inputCommandLine'),3)) {
			//WebUI.closeBrowser()
			def kywHCL = new pkgModules.kywHabilitarCL()
			kywHCL.HabilitarCL(User)
			}else {
			println "Command Line habilitado."
		}
		
	}

	/*----------------------------------------------------------------------------------------------*
	 *CONTROL DE FALLAS																				*
	 *																								*
	 *getTestCaseName: obtengo el nombre del caso actual, definido por las 4 caracteres inciales. 	*
	 *getTimeNow: obtener fecha actual.																*
	 *----------------------------------------------------------------------------------------------*/

	@Keyword
	def getTestCaseName() {
		String testCaseName = RunConfiguration.getExecutionSource().toString().substring(RunConfiguration.getExecutionSource().toString().lastIndexOf("\\")+1)
		int indiceEspacio = testCaseName.indexOf(".");
		testCaseName = testCaseName.substring(0,indiceEspacio)
		return testCaseName
	}

	@Keyword
	def getFolderCaseName() {
		String folderCaseName = new File(RunConfiguration.getExecutionSource().toString()).getParentFile().getName();
		return folderCaseName
	}

	@Keyword
	def getFolderMainName() {
		String folderMainName = new File(RunConfiguration.getExecutionSource().toString()).getParentFile().getParentFile().getName();
		return folderMainName
	}

	@Keyword
	def getTimeNow() {
		Date date = new Date()
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss")
		String currentDate = dateFormat.format(date)
		return currentDate
	}

	@Keyword
	def fFailStatus(){
		def testCaseName = getTestCaseName()
		def date = getTimeNow()
		def folderCaseName = getFolderCaseName()
		def folderMainName = getFolderMainName()
		WebUI.takeScreenshot('Screenshot/Fails/'+folderMainName+'/'+folderCaseName+'/'+testCaseName+'/'+testCaseName+'-'+date+'.png')
		WebUI.delay(3)
		WebUI.closeBrowser()
	}

	@Keyword
	def fPassStatus(){
		def testCaseName = getTestCaseName()
		def date = getTimeNow()
		def folderCaseName = getFolderCaseName()
		def folderMainName = getFolderMainName()
		WebUI.takeScreenshot('Screenshot/'+folderMainName+'/'+folderCaseName+'/'+testCaseName+'/'+'finalStep'+testCaseName+'-'+date+'.png')
		WebUI.delay(3)
		WebUI.closeBrowser()
	}
	//--------------------------------------------------------------------------------------------


}