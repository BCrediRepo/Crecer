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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 6), findTestData('MainData/Users').getValue(2, 6))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

def menuDesplegable = ["Dispositivos", "Registro de Fallas en Dispositivos"]
def link = "Alta de Sobrantes Puntos Neutrales"

//Ejecutar en la linea de comando "?327"
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("?327", 1)

//Cambiar a la ventana "Temenos T24"
WebUI.switchToWindowIndex(1)

//Maximizamos
WebUI.maximizeWindow()

//Navegar por el menu Temenos T24
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Cambiar a la ventana "TELLER"
WebUI.switchToWindowIndex(2)

//Ingresamos el monto
WebUI.setText(findTestObject('Object Repository/21-Fallas/10-TELLER Alt falt PN/txtMonto'), '100')

//Maximizamos
WebUI.maximizeWindow()

//Seleccionar txt comentarios
WebUI.click(findTestObject('Object Repository/21-Fallas/10-TELLER Alt falt PN/txtComentarios'))

//Setear Comentario
WebUI.setText(findTestObject('Object Repository/21-Fallas/10-TELLER Alt falt PN/txtComentarios'), 'CRECER')

//Setear numero de dispositivo
WebUI.setText(findTestObject('Object Repository/21-Fallas/10-TELLER Alt falt PN/txtIDdispositivo'), '70151')

//Click en retiro
WebUI.click(findTestObject('Object Repository/21-Fallas/10-TELLER Alt falt PN/btnRetiro'))
WebUI.click(findTestObject('Object Repository/21-Fallas/10-TELLER Alt falt PN/btnRetiro'))

//Seleccionar boton "Aceptar Registro"
WebUI.click(findTestObject('21-Fallas/06-TELLER/btnAceptarRegistro'))

//Seleccionar cartel de error
WebUI.click(findTestObject('21-Fallas/06-TELLER/btnError'))

//Seleccionar txt denominacion
WebUI.click(findTestObject('Object Repository/21-Fallas/10-TELLER Alt falt PN/txtDenominacion'))
	
//Setear Denominacion
WebUI.setText(findTestObject('Object Repository/21-Fallas/10-TELLER Alt falt PN/txtDenominacion'), '1')

//Aceptamos el registro
WebUI.click(findTestObject('21-Fallas/06-TELLER/btnAceptarRegistro'))

//Aceptamos las alertas
WebUI.click(findTestObject('Object Repository/21-Fallas/10-TELLER Alt falt PN/lnkAceptar Alertas'))

//Verificamos y Validamos la Transaccion Completa
WebUI.verifyElementVisible(findTestObject('Object Repository/21-Fallas/10-TELLER Alt falt PN/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/21-Fallas/10-TELLER Alt falt PN/lblTxnCompleta'))
assert element.contains('Txn Completa') 

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