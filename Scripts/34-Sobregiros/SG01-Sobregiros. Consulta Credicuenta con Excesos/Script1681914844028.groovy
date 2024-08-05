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


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login antes con el 3
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingreso en la linea de comando ENQ BCCL.E.ACDOS

WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.ACDOS')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('BCCL.E.ACDOS')

WebUI.maximizeWindow()

//Seteo de datos "Fecha Desde", "Fecha Hasta", "Estado"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
//CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Desde', '20220718')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Desde', '20150725')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Hasta', GlobalVariable.vFechaCOB)
//CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Estado', 'AC')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Estado', 'VC')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//WebUI.click(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS/lnkEjecutar'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS/lblCuenta'), 20)

WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS/lblCuenta'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

def element = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS/lblCuenta'))

assert element.contains('Cuenta')

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




