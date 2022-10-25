package pkgAdminPIezasTarjetas
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


class APTSeleccionPorNombreDocSuc {

	@Given("Accedo a Admin de piezas con tarjeta")
	def sccedeAdminPiezasTarjetas() {
		println("\n Se accede APT")

		//WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkMenuAdmPiezasTarjetas'))
	}

	@When("Accedo a consultas al Maestro CardCarrier")
	def accedeAcOnsultas() {
		println("\n Se accede a consultas Master-Carrier")

		//WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/lnkBusquedaNombreDoc'))
	}

	@And("Accedo a Seleccion por NombreDocSucursal")
	def seleccionDeBusqueda() {
		println("\n Se selecciona parametro de busqueda")
	}

	@And("Ingreso una sucursal")
	def seleccionDeSucursal() {
		println("\n Se selecciona una sucursal")
	}

	@And("Presiono Ejecutar")
	def ejecutaBusqueda() {
		println("\n Se realiza la busqueda")
	}

	@And("Ingreso un nombre")
	def ingresoNombre() {
		println("\n Se ingresa un nombre")
	}

	@And("Ingreso un documento")
	def ingresoDoc() {
		println("\n Se ingresa un documento")
	}

	@Then("El sistema me muestra los resultados de la busqueda")
	def muestraResultado() {
		println("\n Se muestran los resultados del la busqueda")
	}
}