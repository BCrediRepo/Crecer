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
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.ConditionType
import org.openqa.selenium.Keys as Keys

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 4), findTestData('MainData/Users').getValue(2, 4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Setear "BCCL.ENQ.PARAM.AGRP,INPUT" en el buscador
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'BCCL.ENQ.PARAM.AGRP,INPUT')

//Seleccionar boton buscar
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana "BCCL.ENQ.PARAM.AGRP"
WebUI.switchToWindowIndex(1)

//Ingresar datos
WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/txtAgrupadoDeMovMonetariosYComisiones'), 'ONLINE')

//Maximizar ventana
WebUI.maximizeWindow()

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton Moficar Registro
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/btnModificarRegistro'))

//Ingresar datos para el alta del nuevo agrupamiento
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/btnExpandirMultivalor'))
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/cbxTipoAgrupamiento'))
WebUI.selectOptionByIndex(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/cbxTipoAgrupamiento'), 3)
WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/txtCodigoAgrupamiento'), 'TEST')
WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/txtDescripcionAgrupamiento'), 'Acreditacion Valores Camara/C.Interno')
WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/txtDescCortaAgrupamiento'), 'TEST')
WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/txtIds Cod Oper'), '00105')

//Seleccionar boton validar registro
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/btnValidarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar Aceptar Registro
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/btnAceptarRegistro'))

//Verificar si se ve el texto de transaccion completa
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/lblTxnCompleta'))

//Validar transaccion completa
def element = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/lblTxnCompleta'))
assert element.contains('Txn Completa:')

//Ingresar datos
WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/txtAgrupadoDeMovMonetariosYComisiones'), 'ONLINE')

//Seleccionar boton Moficar Registro
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/btnModificarRegistro'))

//Eliminar Ids Codigo de Operacion
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/btnBorrarMultivalor'))

//Seleccionar Validar Registro
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/btnValidarRegistro'))

//Seleccionar Aceptar Registro
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/btnAceptarRegistro'))

//Verificar si se ve el texto de transaccion completa
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/BCCL.ENQ.PARAM.AGRP/lblTxnCompleta'))

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}