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
import java.text.SimpleDateFormat
import com.kms.katalon.core.configuration.RunConfiguration

public class kywScreenshot {

	/*----------------------------------------------------------------------------------------------*
	 *CAPTURA DE PANTALLA EN CODIGO																			*
	 *																								*
	 *getTestCaseName: obtengo el nombre del caso actual, definido por las 4 caracteres inciales. 	*
	 *getTimeNow: obtener fecha actual.																*
	 *----------------------------------------------------------------------------------------------*/

	@Keyword
	def getTestCaseName() {
		String testCaseName = RunConfiguration.getExecutionSource().toString().substring(RunConfiguration.getExecutionSource().toString().lastIndexOf("\\")+1)
		testCaseName = testCaseName.substring(0,4)
		return testCaseName
	}

	@Keyword
	def getFolderName() {
		String folderName = new File(RunConfiguration.getExecutionSource().toString()).getParentFile().getName();
		return folderName
	}

	@Keyword
	def getTimeNow() {
		Date date = new Date()
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss")
		String currentDate = dateFormat.format(date)
		return currentDate
	}

	@Keyword
	def takeScreenshotInScript(){
		def testCaseName = getTestCaseName()
		def date = getTimeNow()
		def folderName = getFolderName()
		WebUI.delay(3)
		WebUI.takeScreenshot('Screenshot/'+ folderName +'/'+testCaseName+'/'+ testCaseName + '-' + date +'.png')
	}
}
