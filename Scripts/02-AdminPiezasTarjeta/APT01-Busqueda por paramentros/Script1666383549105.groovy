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
import com.kms.katalon.entity.global.GlobalVariableEntity as GlobalVariableEntity
import javax.swing.JFrame as JFrame
import javax.swing.JOptionPane as JOptionPane
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(
        2, 1))

WebUI.maximizeWindow()

//Se accede al menu Administracion de piezas
//WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.AP.ENQ.NOMBRE.DOC')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana de Consulta Maestro Card-Carrier
WebUI.switchToWindowTitle('BCCL.E.AP.ENQ.NOMBRE.DOC')

//Aplico KYW de limpieza de busqueda
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

WebUI.switchToWindowTitle('T24 - Fil.001 Centro')

//WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.AP.ENQ.NOMBRE.DOC')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Vuelvo a la ventana de busqueda
WebUI.switchToWindowTitle('BCCL.E.AP.ENQ.NOMBRE.DOC')

WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/08-BCCL.E.AP.ENQ.NOMBRE.DOC/txtSucursal'), findTestData(
        'MainData/Users').getValue(3, 1))

WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/01-SeleccionNombreDocSuc/btnEjecutar'))

//WebUI.delay(30)

//WebUI.waitForElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/01-SeleccionNombreDocSuc/lblConsultadePiezas'), 
//    10)
WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/01-SeleccionNombreDocSuc/lblConsultadePiezas'))

consulta = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/01-SeleccionNombreDocSuc/lblConsultadePiezas'))
assert consulta == " Consulta de Piezas"

WebUI.delay(3 //---------------------------------------------------------------------------------------------------------------------
    ) //Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

