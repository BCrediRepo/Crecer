import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//librerias necesarias

import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory

//Esta función sirve para una búsqueda más rapida, donde solo queremos validar un dato en una tabla
//haciendo el assert ahí mismo
//Este ejemplo está hecho para validar títulos en un header tabla, pero si cambiamos el tagname "th" por "td"
//y el nombre de la tabla "headingdisplay" por la que corresponda al body table, podremos buscar datos dentro 
//de ella

WebElement table = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
WebElement header = table.findElement(By.tagName("tr"))
List<WebElement> cells = header.findElements(By.tagName("th"))
 
// Validar los textos de las celdas directamente
assert cells[0].getText().contains('Id Transaccion') : "Expected 'Id Transaccion' but found ${cells[0].getText()}"
assert cells[1].getText().contains('Cod Op') : "Expected 'Cod Op' but found ${cells[1].getText()}"
assert cells[2].getText().contains('Descripcion') : "Expected 'Descripcion' but found ${cells[2].getText()}"
assert cells[3].getText().contains('Cuenta Debito') : "Expected 'Cuenta Debito' but found ${cells[3].getText()}"
assert cells[4].getText().contains('Cuenta Credito') : "Expected 'Cuenta Credito' but found ${cells[4].getText()}"
assert cells[5].getText().contains('Mon') : "Expected 'Mon' but found ${cells[5].getText()}"
assert cells[6].getText().contains('Importe') : "Expected 'Importe' but found ${cells[6].getText()}"
assert cells[7].getText().contains('Fec Valor') : "Expected 'Fec Valor' but found ${cells[7].getText()}"
