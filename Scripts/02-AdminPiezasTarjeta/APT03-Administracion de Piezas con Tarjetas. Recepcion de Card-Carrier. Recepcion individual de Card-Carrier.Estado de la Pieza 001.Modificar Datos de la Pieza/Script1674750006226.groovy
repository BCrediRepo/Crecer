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
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'BCCL.AP.PIEZAS L L')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))
WebUI.delay(3)
WebUI.closeWindowTitle('BCCL.AP.PIEZAS')

//Switch a la ventana de Consulta Maestro Card-Carrier
WebUI.switchToWindowTitle('%BCCL.AP.PIEZAS')

//Aplico KYW de limpieza de busqueda
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowTitle('T24 - Fil.001 Centro')
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'BCCL.AP.PIEZAS L L')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))
WebUI.switchToWindowTitle('%BCCL.AP.PIEZAS')

//Continuo con la busqueda de datos
WebUI.waitForElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/03-Recepcion Individual de CardCarrier/txtSUCURSALPIEZA'), 6)
WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/03-Recepcion Individual de CardCarrier/txtSUCURSALPIEZA'), '001')
WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/03-Recepcion Individual de CardCarrier/txtESTADOPIEZA'), '001')
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/03-Recepcion Individual de CardCarrier/lnkEjecutar'))

WebUI.waitForElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/03-Recepcion Individual de CardCarrier/lblNumPiezas'), 6)
numPieza = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/03-Recepcion Individual de CardCarrier/lblNumPiezas'))
WebUI.delay(3)
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/03-Recepcion Individual de CardCarrier/lblNumPiezas'))
WebUI.switchToWindowTitle('BCCL.AP.PIEZAS')
WebUI.waitForElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/03-Recepcion Individual de CardCarrier/editPiezasCargaManual'), 6)
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/03-Recepcion Individual de CardCarrier/editPiezasCargaManual'))
WebUI.waitForElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/03-Recepcion Individual de CardCarrier/selectEntrega'), 6)

entrega = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/03-Recepcion Individual de CardCarrier/selectEntrega'))
if (entrega.contains('Cabal')){
	WebUI.selectOptionByLabel(findTestObject('Object Repository/03-AdminPiezasTarjetas/03-Recepcion Individual de CardCarrier/selectEntrega'), 'CoopBan', false)
}else{
	WebUI.selectOptionByLabel(findTestObject('Object Repository/03-AdminPiezasTarjetas/03-Recepcion Individual de CardCarrier/selectEntrega'), 'Cabal RRHH', false)
}

WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/03-Recepcion Individual de CardCarrier/imgAceptarRegistro'))
WebUI.waitForElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/03-Recepcion Individual de CardCarrier/transactionIdPiezas'), 6)
WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/03-Recepcion Individual de CardCarrier/transactionIdPiezas'), numPieza)
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/03-Recepcion Individual de CardCarrier/imgVerRegistro'))


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
