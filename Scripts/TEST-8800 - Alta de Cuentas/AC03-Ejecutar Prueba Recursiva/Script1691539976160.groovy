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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory

def myTest1 = new TestCase('Test Cases/TEST-8800 - Alta de Cuentas/AC01-Alta de Cuenta')
def myTest2 = new TestCase('Test Cases/TEST-8800 - Alta de Cuentas/AC02-Autorizacion Alta Cuenta')

def data = TestDataFactory.findTestData('datos.csv')
//el archivo podria estar en la carpeta Test Data 
def totalRows = data.getRowNumbers()

for (def row = 1; row <= totalRows; row++) {
	def Parametro1 = data.getValue('DNI', row)
	def Parametro2 = data.getValue('Fecha', row)
	

	// Define tus variables de caso de prueba aquí (si es necesario)
	myTest1.setVariable('DNI', Parametro1)
	myTest1.setVariable('Fecha', Parametro2)
	
	// Ejecuta el caso de prueba
//	CustomKeywords.'com.example.TestKeywords.AC01-Alta de Cuenta'(Parametro1, Parametro2)
//	CustomKeywords.'com.example.TestKeywords.AC02-Autorizacion Alta Cuenta'()
	myTest1.run()
	myTest2.run()
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
