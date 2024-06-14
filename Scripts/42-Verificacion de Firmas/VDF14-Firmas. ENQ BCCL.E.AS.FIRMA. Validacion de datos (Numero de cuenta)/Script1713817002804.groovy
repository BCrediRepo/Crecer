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

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresamos la ENQ en el command line
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.AS.FIRMA')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana BCCL.AS.FIRMA,AUDIT
WebUI.switchToWindowTitle('Verificacion de firmas y facultades')

//Maximizamos
WebUI.maximizeWindow()

//Click nueva seleccion
WebUI.click(findTestObject('Object Repository/43-Verificacion de Firmas/05-Verificacion de firmas y facultades/lnkNueva Seleccion'))

//Ingresamos los datos
WebUI.setText(findTestObject('Object Repository/43-Verificacion de Firmas/05-Verificacion de firmas y facultades/txtNumero de Cuenta'), '10430033951')

WebUI.setText(findTestObject('Object Repository/43-Verificacion de Firmas/05-Verificacion de firmas y facultades/txtFecha'), '20220628')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/43-Verificacion de Firmas/05-Verificacion de firmas y facultades/lnkEjecutar'))

//ASSERT
<<<<<<< HEAD:Scripts/42-Verificacion de Firmas/VDF14-Firmas. ENQ BCCL.E.AS.FIRMA. Validacion de datos (Numero de cuenta)/Script1713817002804.groovy
//WebUI.waitForElementVisible(findTestObject('Object Repository/43-Verificacion de Firmas/06-BCCL.E.AS.FIRMA/lblNO EXISTEN REGISTROS EN LA FECHA INDICADA PARA LA CUENTA 10430033951'), 6)

//WebUI.verifyElementVisible(findTestObject('Object Repository/43-Verificacion de Firmas/06-BCCL.E.AS.FIRMA/lblNO EXISTEN REGISTROS EN LA FECHA INDICADA PARA LA CUENTA 10430033951'))

//def element = WebUI.getText(findTestObject('Object Repository/43-Verificacion de Firmas/06-BCCL.E.AS.FIRMA/lblNO EXISTEN REGISTROS EN LA FECHA INDICADA PARA LA CUENTA 10430033951'))

//assert element.contains('NO EXISTEN REGISTROS EN LA FECHA INDICADA PARA LA CUENTA 10430033951')

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/43-Verificacion de Firmas/05-Verificacion de firmas y facultades/lblNumero de Cuenta'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/43-Verificacion de Firmas/05-Verificacion de firmas y facultades/lblNumero de Cuenta'))

def element = WebUI.getText(findTestObject('Object Repository/43-Verificacion de Firmas/05-Verificacion de firmas y facultades/lblNumero de Cuenta'))

assert element.contains('Numero de Cuenta')
=======
WebUI.waitForElementVisible(findTestObject('Object Repository/43-Verificacion de Firmas/05-Verificacion de firmas y facultades/lblNumero de Cuenta'), 6)

WebUI.verifyElementVisible(findTestObject('Object Repository/43-Verificacion de Firmas/05-Verificacion de firmas y facultades/lblNumero de Cuenta'))

def element = WebUI.getText(findTestObject('Object Repository/43-Verificacion de Firmas/05-Verificacion de firmas y facultades/lblNumero de Cuenta'))

assert element.contains('Numero de Cuenta')

//ASSERT
//WebUI.waitForElementVisible(findTestObject('Object Repository/43-Verificacion de Firmas/06-BCCL.E.AS.FIRMA/lblNO EXISTEN REGISTROS EN LA FECHA INDICADA PARA LA CUENTA 10430033951'), 6)

//WebUI.verifyElementVisible(findTestObject('Object Repository/43-Verificacion de Firmas/06-BCCL.E.AS.FIRMA/lblNO EXISTEN REGISTROS EN LA FECHA INDICADA PARA LA CUENTA 10430033951'))

//def element = WebUI.getText(findTestObject('Object Repository/43-Verificacion de Firmas/06-BCCL.E.AS.FIRMA/lblNO EXISTEN REGISTROS EN LA FECHA INDICADA PARA LA CUENTA 10430033951'))

//assert element.contains('NO EXISTEN REGISTROS EN LA FECHA INDICADA PARA LA CUENTA 10430033951')
>>>>>>> 9c6d651b45b2b4c4610f7b585794b5b75cda7d68:Scripts/42-Verificacion de Firmas/VDF14-Firmas. ENQ BCCL.E.AS.FIRMA. Validación de datos (Número de cuenta)/Script1690817053201.groovy

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


