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
import org.openqa.selenium.WebElement

import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory
import internal.GlobalVariable
import org.openqa.selenium.support.ui.Select

public class kywManejoDeTablas {

	@Keyword
	def clickLinkBotonTabla(String tabla, String variable, int posVariable, int posLink) {
		WebElement table = DriverFactory.getWebDriver().findElement(By.id(tabla))
		List<WebElement> rows = table.findElements(By.tagName("tr"))
		for (WebElement row : rows) {
			WebElement cell = row.findElements(By.tagName("td"))[posVariable]
			String cellText = cell.getText()
			if (cellText.equals(variable)) {
				List<WebElement> tdList = row.findElements(By.tagName("td"))
				WebElement tdElement = tdList[posLink]
				WebElement lnkElement = tdElement.findElement(By.tagName("a"))
				lnkElement.click()
				return true
			}
		}
		return false
	}

	@Keyword
	def rellenarFormulario(String tabla, String variable, int posVariable, String valor, int posValor) {
		WebElement table = DriverFactory.getWebDriver().findElement(By.id(tabla))
		List<WebElement> rows = table.findElements(By.tagName("tr"))
		for (WebElement row : rows) {
			WebElement cell = row.findElements(By.tagName("td"))[posVariable]
			String cellText = cell.getText()
			if (cellText.equals(variable)) {
				List<WebElement> tdList = row.findElements(By.tagName("td"))
				WebElement tdElement = tdList[posValor]
				WebElement lnkElement = tdElement.findElement(By.tagName("input"))
				lnkElement.sendKeys(valor)
				WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))
				return true
			}
		}
		return false
	}

	@Keyword
	def validarElementoEnTabla(String tabla, String variable, int colVariable, String razon, int colRazon) {
		WebElement table = DriverFactory.getWebDriver().findElement(By.id(tabla))
		List<WebElement> rows = table.findElements(By.tagName("tr"))
		for (WebElement row : rows) {
			WebElement cell = row.findElements(By.tagName("td"))[colVariable]
			String cellText = cell.getText()
			if (cellText.equals(variable)) {
				List<WebElement> tdList = row.findElements(By.tagName("td"))
				String resultado = tdList[colRazon].getText()
				println(resultado)
				assert tdList[colRazon].getText().contains(razon) : "Expected " + razon + " but found ${tdList[colRazon].getText()}"
				GlobalVariable.vTxn = resultado
				return true
			}
		}
		return false
	}

	@Keyword
	def seleccionCombobox(String tabla, String variable, int posVariable, int opcionIndex, int posCB) {
		WebElement table2 = DriverFactory.getWebDriver().findElement(By.id(tabla))
		List<WebElement> rows2 = table2.findElements(By.tagName("tr"))
		for (WebElement row2 : rows2) {
			WebElement cell2 = row2.findElements(By.tagName("td"))[posVariable]
			String cellText2 = cell2.getText()
			if (cellText2.equals(variable)) {
				List<WebElement> tdList2 = row2.findElements(By.tagName("td"))
				WebElement tdElement2 = tdList2[posCB]
				WebElement comboBox = tdElement2.findElement(By.tagName("select"))
				def select = new Select(comboBox)
				select.selectByIndex(opcionIndex)
				return true
			}
		}
		return false
	}

	@Keyword
	def seleccionComboboxConValidacion(String tabla, String variable, int posVariable, int opcionIndex, int posCB) {
		WebElement table2 = DriverFactory.getWebDriver().findElement(By.id(tabla))
		List<WebElement> rows2 = table2.findElements(By.tagName("tr"))
		for (WebElement row2 : rows2) {
			WebElement cell2 = row2.findElements(By.tagName("td"))[posVariable]
			String cellText2 = cell2.getText()
			if (cellText2.equals(variable)) {
				List<WebElement> tdList2 = row2.findElements(By.tagName("td"))
				WebElement tdElement2 = tdList2[posCB]
				WebElement comboBox = tdElement2.findElement(By.tagName("select"))
				def select = new Select(comboBox)
				select.selectByIndex(opcionIndex)
				WebElement imgElement = tdElement2.findElement(By.cssSelector("a[title='Select Drilldown'] img"))
				imgElement.click()
				return true
			}
		}
		return false
	}
}
