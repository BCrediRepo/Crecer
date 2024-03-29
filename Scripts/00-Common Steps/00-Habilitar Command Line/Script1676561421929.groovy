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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,12), findTestData('MainData/Users').getValue(2,12))
//WebUI.maximizeWindow()
//CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se ingresa el comando USER,
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), ('USER,'))
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Se cambia a la ventana USER PROFILE
WebUI.switchToWindowTitle('USER PROFILE')
//WebUI.maximizeWindow()

//Se ingresa el usuario a habilitar
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
def user = CustomKeywords.'pkgModules.kywUserForCommandLine.procesarUsuario'(null)
WebUI.setText(findTestObject('Object Repository/00-Utils/01-CommandLine/inputUSER,'),(user))
WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/btnModificarRegistro'))