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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 47), findTestData('MainData/Users').getValue(2, 47))
WebUI.maximizeWindow()

//Ingresar "BCCL.AC.CTASACERRAR" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.AC.CTASACERRAR')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton de buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "BCCL.AC.CTASACERRAR"
WebUI.switchToWindowTitle('BCCL.AC.CTASACERRAR')

//Seleccionar boton Drill Down Lista de Registros
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/04-BCCL.AC.CTASACERRAR/btnDrillDownListadeRegistros'))

//Definir Primera Cuenta id
TestObject primeraCuentaid = findTestObject('Object Repository/25-Cierre de Cuenta/04-BCCL.AC.CTASACERRAR/lblPrimeraCuentaid')

//Almacenar ValorPrimeraPieza
String valorPrimeraCuentaid = WebUI.getText(primeraCuentaid)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Cambiar ventana "T24 - CRECER XXI"
WebUI.switchToWindowTitle('T24 - CRECER XXI')

//Ingresar "TSA.SERVICE," en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'TSA.SERVICE,')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton de buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana ","
WebUI.switchToWindowTitle(',')

//Setear Valor Primera Cuenta id
WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/05-TSA.SERVICE,/txtTSA.SERVICE,'), valorPrimeraCuentaid)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
	
//Seleccionar Modificar un Registro
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/05-TSA.SERVICE,/btnModificarRegistro'))

//Cambiar ventana "TSA.SERVICE,"
WebUI.switchToWindowTitle('TSA.SERVICE,')

//Setear "B.0060" en User
WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/06-TSA.SERVICE,/txtUser'), 'B.0060')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar Aceptar el Registro
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/06-TSA.SERVICE,/btnAceptarRegistro'))

//Cambiar ventana ","
WebUI.switchToWindowTitle(',')

//Verificar "Tnx Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/25-Cierre de Cuenta/05-TSA.SERVICE,/lblTxnCompleta'))

//Validar "Tnx Completa"
def element = WebUI.getText(findTestObject('Object Repository/25-Cierre de Cuenta/05-TSA.SERVICE,/lblTxnCompleta'))
assert element.contains('Txn Completa')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Setear Valor Primera Cuenta id
WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/05-TSA.SERVICE,/txtTSA.SERVICE,'), valorPrimeraCuentaid)

//Seleccionar Modificar un Registro
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/05-TSA.SERVICE,/btnModificarRegistro'))

//Cambiar ventana "TSA.SERVICE,"
WebUI.switchToWindowTitle('TSA.SERVICE,')

//Setear "A.GIOIOSA" en User
WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/06-TSA.SERVICE,/txtUser'), 'A.GIOIOSA')

//Seleccionar Aceptar el Registro
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/06-TSA.SERVICE,/btnAceptarRegistro'))

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}