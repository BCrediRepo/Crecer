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

//def myTest1 = findTestCase('Test Cases/TEST-8800 - Alta de Cuentas/AC01-Alta de Cuenta')

//def myTest2 = findTestCase('Test Cases/TEST-8800 - Alta de Cuentas/AC02-Autorizacion Alta Cuenta')

//def data = TestDataFactory.findTestData('Data Files/MainData/DatosNCD')
//el archivo podria estar en la carpeta Test Data 
//def totalRows = data.getRowNumbers()
for (def row = 1; row <= 15; row++) {
    GlobalVariable.vDNI = findTestData('MainData/DatosNCD').getValue(1, row)
    GlobalVariable.vFECHA = findTestData('MainData/DatosNCD').getValue(2, row)
	GlobalVariable.vCODPROD = findTestData('MainData/DatosNCD').getValue(3, row)
	GlobalVariable.vTARIFARIO = findTestData('MainData/DatosNCD').getValue(4, row)
	WebUI.callTestCase(findTestCase('TEST-8800 - Alta de Cuentas/AC01-Alta de Cuenta'), [:])
	WebUI.callTestCase(findTestCase('TEST-8800 - Alta de Cuentas/AC02-Autorizacion Alta Cuenta'), [:])

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

