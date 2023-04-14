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
import com.kms.katalon.entity.global.GlobalVariableEntity

import javax.swing.JFrame as JFrame
import javax.swing.JOptionPane as JOptionPane

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))
WebUI.maximizeWindow()

//Se accede al menu Administracion de piezas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkAdministracionPiezasTarjetas'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/01-AdminPiezasConTarjetas/lnkConsultasMaestroCardCarrier'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/01-AdminPiezasConTarjetas/04-ConsultaMaestroCardCarrier/lnkSeleccionNombreDocSuc'))

WebUI.delay(5)

//Switch a la ventana de Consulta Maestro Card-Carrier
WebUI.switchToWindowTitle ('BCCL.E.AP.ENQ.NOMBRE.DOC')

WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/01-SeleccionNombreDocSuc/txtSucursal'))

WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/01-SeleccionNombreDocSuc/txtSucursal'), findTestData('MainData/Users').getValue(3,1))

WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/01-SeleccionNombreDocSuc/btnEjecutar'))

//Se limpia y realiza una nueva busqueda
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/01-SeleccionNombreDocSuc/icoReiniciarBusqueda'))

WebUI.clearText(findTestObject('Object Repository/03-AdminPiezasTarjetas/01-SeleccionNombreDocSuc/txtSucursal'))

WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/01-SeleccionNombreDocSuc/btnEjecutar'))

WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/01-SeleccionNombreDocSuc/msgNoSeEncontraronRegistros'))

WebUI.verifyTextPresent(findTestData('Errores/MensajesDeError').getValue(2,1), true)

WebUI.delay(3)

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
