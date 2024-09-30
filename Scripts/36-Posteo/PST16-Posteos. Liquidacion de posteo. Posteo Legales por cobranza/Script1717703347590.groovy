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
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.support.ui.Select


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 66), findTestData('MainData/Users').getValue(2, 66))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'('?328', 1)
menuDesplegable=['Posteo', 'Cobranza de Legales']
link = 'Ingreso de Cobranzas'
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Cambiar a la ventana "BCCL.COBRANZAS.LEGALES"
WebUI.switchToWindowIndex(2)
WebUI.delay(5)
def encontrado = false
while(!encontrado) {	
	encontrado = CustomKeywords.'pkgModules.kywManejoDeTablas.rellenarFormulario'('tab1', 'Carpeta', 0, '15071', 2)
	encontrado = CustomKeywords.'pkgModules.kywManejoDeTablas.rellenarFormulario'('tab1', 'Nombre del Socio', 0, 'TEST', 2)
	encontrado = CustomKeywords.'pkgModules.kywManejoDeTablas.rellenarFormulario'('tab1', 'Abogado', 0, '2098', 2)
	encontrado = CustomKeywords.'pkgModules.kywManejoDeTablas.rellenarFormulario'('tab1', 'Moneda', 0, 'ARS', 2)
	encontrado = CustomKeywords.'pkgModules.kywManejoDeTablas.seleccionCombobox'('tab1', 'Concepto.1', 0, 1, 2)
	encontrado = CustomKeywords.'pkgModules.kywManejoDeTablas.rellenarFormulario'('tab1', 'Importe.1', 0, '10', 2)
	WebUI.click(findTestObject('Object Repository/37-Posteo/BCCL.COBRANZAS.LEGALES/rbTitular'))
}


//Maximizar Pantalla
WebUI.maximizeWindow()

//Seleccionar "boton Validar Registro"
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/btnValidarRegistro'))

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/btnAceptarRegistro'))

//Definir Transaccion
Transaccion = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion.split('\\s+')
def trx1 = partes[2]
assert Transaccion.contains('Txn Completa:')

//Setear en "BCCL.COBRANZAS.LEGALES"
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), trx1)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Ver Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

//Definir ftPosteo
ftPosteo = WebUI.getText(findTestObject('Object Repository/37-Posteo/BCCL.COBRANZAS.LEGALES/lblFtPosteo'))

//Volver a Logearse con el usuario que liquida Posteos

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 67), findTestData('MainData/Users').getValue(2, 67))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'('?303', 1)
menuDesplegable1 = ['Posteo']
link1 = 'Transacciones Pendientes de Liquidacion'
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable1, link1)

//Cambiar ventana "BCCL.E.EB.POSTEO.INAU"
WebUI.switchToWindowTitle('BCCL.E.EB.POSTEO.INAU')

//Maximizar Pantalla
WebUI.maximizeWindow()

encontrado = false
while(!encontrado) {
	encontrado = CustomKeywords.'pkgModules.kywManejoDeTablas.clickLinkBotonTabla'('datadisplay', ftPosteo, 0, 8)
}

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Autorizar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAutorizaRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))

//Validar "Txn Completa"
def element = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert element.contains('Txn Completa')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}