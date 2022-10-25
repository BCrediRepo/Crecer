package pkgLogin
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When

import javax.swing.JFrame as JFrame
import javax.swing.JOptionPane as JOptionPane

class LGNDefaultLogin {

	@Given("La URL de Crecer")
	def openWeb() {
		println ('\n Abre la URL')

		//--- Accede a la URL ---
		def vURL = 'http://' + GlobalVariable.vTest10_IP + ':8080/' + GlobalVariable.vTest10Name + '/servlet/BrowserServlet'
		//def vURL = 'http://' + GlobalVariable.vTest702_IP + '/' + GlobalVariable.vTest702Name + '/servlet/BrowserServlet'
		//JOptionPane.showMessageDialog(null,vURL)
		WebUI.openBrowser(vURL)

	}

	@When("Ingreso credenciales")
	def introduceCredenciales() {
		println ('\n Ingreas User y Pswd')

		//--- Ingreso de credenciales ---
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLGNUser'), GlobalVariable.vUser)
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLGNPassword'), GlobalVariable.vPass)
		WebUI.click(findTestObject('Object Repository/01-Login/btnLGNSignIn'))
	}

	@Then("Accedo al mainpage del sistema")
	def validaAcceso() {
		println ('\n Validacion de acceso a Crecer')

		//--- Validación del acceso ---
		WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkMenuAutorizacionesModulos'))
		WebUI.delay(2)

	}
}