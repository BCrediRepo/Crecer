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
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.webui.driver.DriverFactory
// Importar las clases necesarias de Apache POI para manipular archivos
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFSheet
import com.kms.katalon.core.webui.driver.DriverFactory


//Abrir archivo Excel existente
FileInputStream fileIn = new FileInputStream("Test Data/Compensacion Coelsa.xlsx")
XSSFWorkbook workbook = new XSSFWorkbook(fileIn)
XSSFSheet sheet = workbook.getSheet("Hoja1")

//Encuentro la primera fila vacía en la hoja para conseguir el numero de fila vacia en donde corta la iteracion
int filaNum = 0
while (sheet.getRow(filaNum) != null) {
	filaNum++
}

//Guardar el libro de Excel
fileIn.close()
FileOutputStream fileOut = new FileOutputStream("Test Data/Compensacion Coelsa.xlsx")
workbook.write(fileOut)
fileOut.close()

//Cerrar el libro de Excel
workbook.close()

for (def row = 1; row < filaNum; row++) {
	GlobalVariable.vIDCoelsa = findTestData('Compensacion Coelsa/Hoja1').getValue(1, row)
	GlobalVariable.vFTdelIDCoelsa = findTestData('Compensacion Coelsa/Hoja1').getValue(2, row)
    WebUI.callTestCase(findTestCase('TEST-8910 - Compensacion Coelsa/CC02-Validacion FT - Dia Posterior'), [:])
}

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

