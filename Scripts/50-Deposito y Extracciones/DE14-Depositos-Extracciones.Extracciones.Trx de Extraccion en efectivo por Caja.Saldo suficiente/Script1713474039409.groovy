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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 45), findTestData('MainData/Users').getValue(2, 45))

//Maximizar Pantalla
WebUI.maximizeWindow()

//Seleccionar Extracciones
WebUI.click(findTestObject('02-Dashboard/lnkExtracciones'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar Retiro en Ventanilla (CA)
WebUI.click(findTestObject('02-Dashboard/47-Extracciones/lnkRetiroEnVentanilla'))

//Cambiar ventana "TELLER"
WebUI.switchToWindowTitle('TELLER')

//Setear Numero de cuenta
WebUI.setText(findTestObject('49-Extracciones/TELLER/txtNroCuenta'), '11190215166')

//Seleccionar "boton Validar Registro"
WebUI.click(findTestObject('49-Extracciones/TELLER/btnValidarRegistro'))

//Setear Monto
WebUI.setText(findTestObject('49-Extracciones/TELLER/txtMonto'), '5')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro
WebUI.click(findTestObject('49-Extracciones/TELLER/btnAceptarRegistro'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar alertas"
WebUI.click(findTestObject('49-Extracciones/TELLER/lnkAceptarAlertas'))

//Cambiar ventana "Verificacion de Firmas - Fil.019 La Plata"
WebUI.switchToWindowTitle('Verificacion de Firmas - Fil.019 La Plata')

//Seleccionar "FORZAR" en el combo box
WebUI.selectOptionByIndex(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/cbxAccion'), 2)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar el registro"
WebUI.click(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/btnAceptar'))

//Verificar "Autorizada"
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblAutorizada'))

//Validar "Autorizada"
autorizada = WebUI.getText(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblAutorizada'))
assert autorizada == "AUTORIZADA"

//Definir Objeto Txn
transaccion = WebUI.getText(findTestObject('Object Repository/49-Extracciones/TELLER/lblTxnAutorizada'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Cambiar ventana "TELLER"
WebUI.switchToWindowTitle('TELLER')

//Setear Transaccion
WebUI.setText(findTestObject('Object Repository/49-Extracciones/TELLER/txtRetiroDeEfectivoEnVentanilla'), transaccion)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Ver Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnVerRegistro'))

//Verificar "FORZADO FIRMA"
WebUI.verifyElementVisible(findTestObject('Object Repository/49-Extracciones/TELLER/lblFORZADOFIRMA'))

//Validar "FORZADO FIRMA"
forzadoFirma = WebUI.getText(findTestObject('Object Repository/49-Extracciones/TELLER/lblFORZADOFIRMA'))
assert forzadoFirma == "FORZADO FIRMA"

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Switch a la ventana del Dashboard
WebUI.switchToWindowIndex(0)

//Ingresar "TT S" + transaccion en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'TT S '+transaccion)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Switch a la segunda ventana de TELLER
WebUI.switchToWindowIndex(4)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Context Enquiry"
WebUI.click(findTestObject('Object Repository/49-Extracciones/TELLER/btnContextEnquiry'))

//Cambiar ventana "Consultas"
WebUI.switchToWindowTitle('Consultas')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Consulta de Cuentas por Cuenta"
WebUI.click(findTestObject('Object Repository/49-Extracciones/Consultas/lnkConsultadeCuentasporCuenta'))

//Cambiar ventana "Consulta de Cuentas por Cuenta"
WebUI.switchToWindowTitle('Consulta de Cuentas por Cuenta')

//Seleccionar la opcion "Ver Firmantes" en el combo box
WebUI.selectOptionByIndex(findTestObject('Object Repository/49-Extracciones/Consulta de Cuentas por Cuenta/cbVerFirmantes'), 1)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Drilldown"
WebUI.click(findTestObject('Object Repository/49-Extracciones/Consulta de Cuentas por Cuenta/btnSelectDrilldown'))

//Cambiar ventana "Consulta Firmantes Cuenta"
WebUI.switchToWindowTitle('Consulta Firmantes Cuenta')

//Verificar "Firmantes Cuenta"
WebUI.verifyElementVisible(findTestObject('Object Repository/49-Extracciones/Consulta Firmantes Cuenta/lblFirmantesCuenta'))

//Validar "Firmantes Cuenta"
firmantesCuenta = WebUI.getText(findTestObject('Object Repository/49-Extracciones/Consulta Firmantes Cuenta/lblFirmantesCuenta'))
assert firmantesCuenta == "Firmantes Cuenta:"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}