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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
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
WebUI.setText(findTestObject('Object Repository/43-Verificacion de Firmas/05-Verificacion de firmas y facultades/txtNumero de Cuenta'), '00150044569')
//CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha (AAAAMMDD)', '20230801')

//Click en ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/43-Verificacion de Firmas/06-BCCL.E.AS.FIRMA/lblFECHA es de Ingreso Obligatorio'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/43-Verificacion de Firmas/06-BCCL.E.AS.FIRMA/lblFECHA es de Ingreso Obligatorio'))
def element = WebUI.getText(findTestObject('Object Repository/43-Verificacion de Firmas/06-BCCL.E.AS.FIRMA/lblFECHA es de Ingreso Obligatorio'))
assert element.contains('FECHA es de Ingreso Obligatorio')

//Switch a la ventana principal
//WebUI.switchToWindowIndex(0)

//WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Click nueva seleccion
//WebUI.click(findTestObject('Object Repository/43-Verificacion de Firmas/05-Verificacion de firmas y facultades/lnkNueva Seleccion'))
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Ingresamos los datos
WebUI.setText(findTestObject('Object Repository/43-Verificacion de Firmas/05-Verificacion de firmas y facultades/txtNumero de Cuenta'), '00150044569')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha (AAAAMMDD)', '20230801')

//Screenshot
//CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Switch a la ventana Verificacion de firmas y facultades
WebUI.switchToWindowTitle('Verificacion de firmas y facultades')

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/43-Verificacion de Firmas/05-Verificacion de firmas y facultades/lblCanal'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/43-Verificacion de Firmas/05-Verificacion de firmas y facultades/lblCanal'))
def element2 = WebUI.getText(findTestObject('Object Repository/43-Verificacion de Firmas/05-Verificacion de firmas y facultades/lblCanal'))
assert element2.contains('Canal')

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



