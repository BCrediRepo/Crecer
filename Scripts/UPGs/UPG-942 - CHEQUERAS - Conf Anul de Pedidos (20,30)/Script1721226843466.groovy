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


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se accede al menu Conf / Anul de Pedidos (20,30) 
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lnkChequeras'), 6)
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkChequeras'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/lnkEmisiondechequera'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/lnkEmisiondechequera'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/01-Emision de Chequera/lnkConfAnuldePedidos'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/01-Emision de Chequera/lnkConfAnuldePedidos'))

//Switch a la ventana de Change Status Checkbook Req
WebUI.switchToWindowTitle('Change Status Checkbook Req')

//Verifico que estoy en la ventana correcta
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/Change Status Checkbook Req/lblChangeStatusCheckbookReq'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/11-Emision Chequera/Change Status Checkbook Req/lblChangeStatusCheckbookReq'))
def element = WebUI.getText(findTestObject('Object Repository/11-Emision Chequera/Change Status Checkbook Req/lblChangeStatusCheckbookReq'))
assert element.contains('Change Status Checkbook Req')

//Ejecuto la busqueda
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verifico que haya resultados en la tabla
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/Change Status Checkbook Req/lblMANDATOVIG'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/11-Emision Chequera/Change Status Checkbook Req/lblMANDATOVIG'))
def element2 = WebUI.getText(findTestObject('Object Repository/11-Emision Chequera/Change Status Checkbook Req/lblMANDATOVIG'))
assert element2.contains('MANDATO VIG')

WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/Change Status Checkbook Req/lblOK'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/11-Emision Chequera/Change Status Checkbook Req/lblOK'))
def element3 = WebUI.getText(findTestObject('Object Repository/11-Emision Chequera/Change Status Checkbook Req/lblOK'))
assert element3.contains('OK')

//cierro ventana actual
WebUI.closeWindowTitle('Change Status Checkbook Req')

//Se accede al menu Conf / Anul de Pedidos (20,30)
WebUI.switchToWindowIndex(0)
WebUI.click(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/01-Emision de Chequera/lnkConfAnuldePedidos'))

//Switch a la ventana de Change Status Checkbook Req
WebUI.switchToWindowTitle('Change Status Checkbook Req')

//Verifico que estoy en la ventana correcta
assert element.contains('Change Status Checkbook Req')

//Seteo de Datos NUMERO DE CUENTA
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('CUENTA', '00010043082')

//Ejecuto y valido la busqueda
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))
assert element2.contains('MANDATO VIG')
assert element3.contains('OK')

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

