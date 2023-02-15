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

//Se accede al menu Administracion de piezas
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lnkChequeras'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkChequeras'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/8-Emision Chequera/lnkConsulta'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/8-Emision Chequera/lnkConsulta'))
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/8-Emision Chequera/01-Consulta/lnkConsultadeChequerahastaEmitida(40)'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/8-Emision Chequera/01-Consulta/lnkConsultadeChequerahastaEmitida(40)'))

//Switch a la ventana de busqueda de consulta de chequeras
WebUI.switchToWindowTitle('BCCL.CQ.CHEQUERAS')
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/03-Consulta De Chequeta Hasta Emitida (40)/txtFechaDesde'), 6)
WebUI.setText(findTestObject('Object Repository/11-Emision Chequera/03-Consulta De Chequeta Hasta Emitida (40)/txtFechaDesde'), '20220704')
WebUI.setText(findTestObject('Object Repository/11-Emision Chequera/03-Consulta De Chequeta Hasta Emitida (40)/txtFechaHasta'), '20220718')
WebUI.click(findTestObject('Object Repository/11-Emision Chequera/03-Consulta De Chequeta Hasta Emitida (40)/lnkEjecutar'))
WebUI.waitForElementVisible(findTestObject('Object Repository/11-Emision Chequera/03-Consulta De Chequeta Hasta Emitida (40)/lblIDCuenta'), 6)
WebUI.verifyElementPresent(findTestObject('Object Repository/11-Emision Chequera/03-Consulta De Chequeta Hasta Emitida (40)/lblIDCuenta'), 6)

//WebUI.takeScreenshot('Screenshot/Emision Chequera/Emisión de Chequera.Consulta de chequeras serv adm .Consulta con registro cambiado hace más de 3 días' + nowString + '.png')

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