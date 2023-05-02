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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se accede al menu Administracion de piezas
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lnkChequeras'), 6)
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkChequeras'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/lnkModificaciondechequera'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/lnkModificaciondechequera'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/02-Modificacion de Chequera/lnkImpresionActadeDestruccion'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/02-Modificacion de Chequera/lnkImpresionActadeDestruccion'))

//Switch a la ventana de impresion de acta
WebUI.switchToWindowTitle('BCCL.CQ.STOCK.ENTRY.ACTA')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

WebUI.click(findTestObject('Object Repository/02-Dashboard/08-Emision Chequera/02-Modificacion de Chequera/lnkImpresionActadeDestruccion'))

//Switch a la ventana de impresion de acta
WebUI.switchToWindowTitle('BCCL.CQ.STOCK.ENTRY.ACTA')

WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/05-Impresión del Acta de Destrucción de Chequeras/txtFECHAESTADO'), 6)
WebUI.selectOptionByIndex(findTestObject('Object Repository/11-Emision Chequera/05-Impresión del Acta de Destrucción de Chequeras/selectorFECHA'), 1)
WebUI.setText(findTestObject('Object Repository/11-Emision Chequera/05-Impresión del Acta de Destrucción de Chequeras/txtFECHAESTADO'), '20220101')
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/05-Impresión del Acta de Destrucción de Chequeras/lnkEjecutar'))

//Seleccionamos Imprimir acta de un registro
WebUI.maximizeWindow()
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/05-Impresión del Acta de Destrucción de Chequeras/lnkImprimirActa'), 6)
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/05-Impresión del Acta de Destrucción de Chequeras/lnkImprimirActa'))

//Switch a la ventana acptar el registro de impresion
WebUI.switchToWindowTitle('STOCK.ENTRY')
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/05-Impresión del Acta de Destrucción de Chequeras/lnkActaDestrucciondeChequeras'), 6)
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/05-Impresión del Acta de Destrucción de Chequeras/btnAceptarRegistro'), 6)
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/05-Impresión del Acta de Destrucción de Chequeras/btnAceptarRegistro'))

//Se aceptan las alertas
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/05-Impresión del Acta de Destrucción de Chequeras/lnkAceptarAlertas'), 6)
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/05-Impresión del Acta de Destrucción de Chequeras/lnkAceptarAlertas'))

WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/05-Impresión del Acta de Destrucción de Chequeras/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/11-Emision Chequera/05-Impresión del Acta de Destrucción de Chequeras/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/11-Emision Chequera/05-Impresión del Acta de Destrucción de Chequeras/lblTxnCompleta'))
assert element.contains('Txn Completa:')

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
