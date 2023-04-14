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

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
        2, 9))

//Se maximisa la ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 3)

//Se busca el TestBox de "Buscador"
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.NOFILE.TOT.OPER.MONEX')

//Click en el boton "Ejecutar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambia a la ventana nueva
WebUI.switchToWindowTitle('Total de Oper de Compra-Venta MONEX')

//Maximiza la nueva ventana 
WebUI.maximizeWindow()

//Espera a que el elemento "Ejecutar" sea Clickeable
WebUI.waitForElementClickable(findTestObject('15-MONEX/04-BCCL.E.COT.GRAL.FIL/lnkEjecutar'), 3)

//Clickea el elemento "Ejecutar"
WebUI.click(findTestObject('15-MONEX/04-BCCL.E.COT.GRAL.FIL/lnkEjecutar'))

//Espera a que sea visible el elemento "Cant. Oper"
WebUI.waitForElementVisible(findTestObject('15-MONEX/05-ENQ BCCL.E.NOFILE.TOT.OPER.MONEX/lblCantOper'), 3)

//Verifica que el mismo sea visible
Operadores = WebUI.verifyElementVisible(findTestObject('15-MONEX/05-ENQ BCCL.E.NOFILE.TOT.OPER.MONEX/lblCantOper'))

//Realizar un Assert del mismo elemento
assert Operadores == true //---------------------------------------------------------------------------------------------------------------------

