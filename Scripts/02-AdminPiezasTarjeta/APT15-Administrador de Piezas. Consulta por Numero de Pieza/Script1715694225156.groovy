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

//Seleccionar "Administracion de Piezas con Tarjetas"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkAdministracionPiezasTarjetas'))

//Seleccionar "Consultas al Maestro de Card-Carrier"
WebUI.click(findTestObject('Object Repository/02-Dashboard/01-AdminPiezasConTarjetas/lnkConsultasalMaestrodeCard-Carrier (1)'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en "Consulta por Card-Carrier"
WebUI.click(findTestObject('Object Repository/02-Dashboard/01-AdminPiezasConTarjetas/04-ConsultaMaestroCardCarrier/lnkConsultaPorCard-Carrier'))

//Cambiar ventana "BCCL.AP.PIEZAS"
WebUI.switchToWindowTitle('BCCL.AP.PIEZAS')

//Maximizar pantalla
WebUI.maximizeWindow()

//Setear "Nro. de pieza"
WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/txtNro.de Pieza'), '0000000001')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Boton Ver un Registro"
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/btnVerRegistro'))

//Verificar "Estado"
WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblEstado'))

//Validar "Estado"
estado = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblEstado'))
assert estado == "090"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}