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

def codOper = '00129'
def codOper2 = '00100'
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ejecutar en la linea de comando "BCCL.ENQ.PARAM.AGRP,INPUT"
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("BCCL.ENQ.PARAM.AGRP,INPUT", 1)

//Cambiar a la ventana "BCCL.ENQ.PARAM.AGRP"
WebUI.switchToWindowIndex(1)

//Maximizar ventana
WebUI.maximizeWindow()

//Setear la palabra "ONLINE"
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), 'ONLINE')

//Maximizar ventana
WebUI.maximizeWindow()

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton modificar registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnModificarRegistro'))

//Eliminar "Ids. Cod Oper.1.1"
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/btnDeleteValueCodOperUno'))

//Guardo en varible lo que se encuentra en cbx
//String tipAgrup = WebUI.getAttribute(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/cbxTipoAgrupamiento2'), 'value')
 
//if (tipAgrup == "AMB" || tipAgrup == "NOR") {
	//cambio el tipo de agrupamiento a COM y el cod. operativo
	//WebUI.selectOptionByIndex(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/cbxTipoAgrupamiento2'), 2)

	//WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/txtCodOper1.1'), codOper)

//}else if (tipAgrup == "COM") {
	//cambio el tipo de agrupamiento a AMB y el cod. operativo
	WebUI.selectOptionByIndex(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/cbxTipoAgrupamiento2'), 1)

	WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/txtCodOper1.1'), codOper2)
//}

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
	
//Seleccionar boton "Validar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))

//Seleccionar boton "Aceptar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Validar transaccion completa
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def txnCompleta = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert txnCompleta.contains('Txn Completa:')

//Setear la palabra "ONLINE"
WebUI.setText(findTestObject('Object Repository/00-Utils/06-ToolBar/txtTransactionId'), 'ONLINE')

//Seleccionar boton modificar registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnModificarRegistro'))

//Eliminar "Ids. Cod Oper.1.1"
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/btnDeleteValueCodOperUno'))

//Seleccionar boton "Validar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))

//Seleccionar boton "Aceptar Registro"
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Verificar txn Completa
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}