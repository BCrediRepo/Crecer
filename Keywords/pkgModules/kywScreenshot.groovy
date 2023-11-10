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
import pkgModules.kywGeneric
import org.openqa.selenium.Keys as Keys
import java.awt.Rectangle
import com.kms.katalon.core.webui.driver.DriverFactory


public class kywScreenshot {

	/*----------------------------------------------------------------------------------------------*
	 *CAPTURA DE PANTALLA EN CODIGO																			*
	 *																								*
	 *getTestCaseName: obtengo el nombre del caso actual, definido por las 4 caracteres inciales. 	*
	 *getTimeNow: obtener fecha actual.																*
	 *----------------------------------------------------------------------------------------------*/

	def kywG = new pkgModules.kywGeneric()

	@Keyword
	def takeScreenshotInScript(){
		def testCaseName = kywG.getTestCaseName()
		def date = kywG.getTimeNow()
		def folderCaseName = kywG.getFolderCaseName()
		def folderMainName = kywG.getFolderMainName()
		WebUI.delay(3)
		WebUI.takeScreenshot('Screenshot/'+folderMainName+'/'+folderCaseName+'/'+testCaseName+'/'+testCaseName+'-'+date+'.png')
	}

	// Obtén el tamaño del frame
	def frameSize = DriverFactory.getWebDriver().manage().window().getSize()

	// Crea un objeto Rectangle con las coordenadas y dimensiones del área completa del frame
	Rectangle areaToCapture = new Rectangle(0, 0, frameSize.width, frameSize.height)

	@Keyword
	def takeScreenshotInScriptFull(){
		def testCaseName = kywG.getTestCaseName()
		def date = kywG.getTimeNow()
		def folderCaseName = kywG.getFolderCaseName()
		def folderMainName = kywG.getFolderMainName()
		WebUI.delay(3)
//		aca estaria faltando la magia para la captura completa
	}
}








