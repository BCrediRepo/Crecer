package pkgModules

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.WebDriver

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
import org.openqa.selenium.By

import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory


public class kywBusquedaMenu {

	// Función para buscar y hacer clic en un <span>
	def buscarYHacerClickEnSpan(WebDriver driver, String textoSpan) {
		try {
			List<WebElement> spanElements = driver.findElements(By.tagName("span"))
			for (WebElement spanElement : spanElements) {
				if (spanElement.getText().equals(textoSpan)) {
					spanElement.click()
					return true
				}
			}
		} catch (Exception e) {
			println "Error: ${e.message}"
		}
		return false
	}

	// Función para buscar y hacer clic en un <a>
	def buscarYHacerClickEnHref(WebDriver driver, String textoHref) {
		try {
			List<WebElement> aElements = driver.findElements(By.tagName("a"))
			for (WebElement aElement : aElements) {
				if (aElement.getText().equals(textoHref)) {
					aElement.click()
					return true
				}
			}
		} catch (Exception e) {
			println "Error: ${e.message}"
		}
		return false
	}

	// Función para buscar y desplegar un menú recursivamente
	def buscarYDesplegarMenu(WebDriver driver, List<String> menuItems) {
		for (String menuItem : menuItems) {
			if (!buscarYHacerClickEnSpan(driver, menuItem)) {
				println "No se pudo encontrar el menú: ${menuItem}"
				return false
			}
			// Esperar un momento para que el submenú se despliegue (si es necesario)
			Thread.sleep(1000)
		}
		return true
	}

	@Keyword
	def navegacionMenu(List<String> menuDesplegables, String link) {

		// Obtener el WebDriver actual de Katalon
		WebDriver driver = DriverFactory.getWebDriver()

		try {
			
			// Desplegar los menús recursivamente
			if (!buscarYDesplegarMenu(driver, menuDesplegables)) {
				println "No se pudieron desplegar todos los menús"
				return false
			}

			// Buscar y hacer clic en el enlace final
			if (!buscarYHacerClickEnHref(driver, link)) {
				println "No se pudo encontrar el enlace: ${link}"
				return false
			}

			return true
		} catch (Exception e) {
			println "Error: ${e.message}"
			return false
		}
	}

	@Keyword
	def navegacionDashboard(List<String> menuDesplegables, String link) {
		def frame = findTestObject('Object Repository/02-Dashboard/frmDashboardMenu')
		// Obtener el WebDriver actual de Katalon
		WebDriver driver = DriverFactory.getWebDriver()

		try {
			// Cambiar al frame donde se encuentra el menú
			WebUI.switchToFrame(frame, 0)  // Ajusta el nombre del frame según sea necesario

			// Desplegar los menús recursivamente
			if (!buscarYDesplegarMenu(driver, menuDesplegables)) {
				println "No se pudieron desplegar todos los menús"
				return false
			}

			// Buscar y hacer clic en el enlace final
			if (!buscarYHacerClickEnHref(driver, link)) {
				println "No se pudo encontrar el enlace: ${link}"
				return false
			}

			return true
		} catch (Exception e) {
			println "Error: ${e.message}"
			return false
		}
	}
}
