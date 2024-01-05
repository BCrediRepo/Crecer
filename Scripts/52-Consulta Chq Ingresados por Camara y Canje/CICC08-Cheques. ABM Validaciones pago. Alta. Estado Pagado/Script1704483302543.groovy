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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,33), findTestData('MainData/Users').getValue(2,33))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresar "BCCL.CQ.VALIDACIONES.PAGO,INPUT" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.CQ.VALIDACIONES.PAGO,INPUT')

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Cambiar ventana "BCCL.CQ.VALIDACIONES.PAGO"
WebUI.switchToWindowTitle('BCCL.CQ.VALIDACIONES.PAGO')

//Maximizar pantalla
WebUI.maximizeWindow()

//Setear id en Validaciones pago
WebUI.setText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/txtBCCL.CQ.VALIDACIONES.PAGO,INPUT'), '24.01.05.40.10.00.00.00.00')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Modificar registro"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/btnModificaregistro'))
	
//Setear Accion
WebUI.setText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/txtAccion'), '1')
	
//Setear primer label de Cod OP Igual 191
WebUI.setText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/txtCodOPIgual191'), '00100')
	
//Setear Cod OP Menor Mayor 191
WebUI.setText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/txtCodOPMenorMayor191'), '00100')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/btnAceptarRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/lblTxnCompleta'))

//Validar "Txn Completa"
def element = WebUI.getText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/lblTxnCompleta'))
assert element.contains('Txn Completa')

//Setear id en Validaciones pago
WebUI.setText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/txtBCCL.CQ.VALIDACIONES.PAGO,INPUT'), '24.01.05.40.10.00.00.00.00')

//Seleccionar "boton Modificar registro"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/btnModificaregistro'))
	
//Setear Accion
WebUI.setText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/txtAccion'), '1')
	
//Setear primer label de Cod OP Igual 191
WebUI.setText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/txtCodOPIgual191'), '00101')
	
//Setear Cod OP Menor Mayor 191
WebUI.setText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/txtCodOPMenorMayor191'), '00100')

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/btnAceptarRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.CQ.VALIDACIONES.PAGO/lblTxnCompleta'))

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}