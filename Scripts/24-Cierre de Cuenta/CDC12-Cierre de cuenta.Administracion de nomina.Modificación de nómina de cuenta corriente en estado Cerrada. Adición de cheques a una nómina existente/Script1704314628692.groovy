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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 4), findTestData('MainData/Users').getValue(2, 4))
WebUI.maximizeWindow()

//Seleccionar Cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))

//Seleccionar Cierre de cuenta
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkCierredecuenta'))

//Seleccionar Nomina
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/10-Cierre de cuenta/lnkNomina'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar Administracion de Nomina
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/10-Cierre de cuenta/Nomina/lnkAdministracionNomina'))

//Cambiar ventana "BCCL.AC.ADM.NOMINA"
WebUI.switchToWindowTitle('BCCL.AC.ADM.NOMINA')

//Filtro limpieza
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Seleccionar Administracion de Nomina
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/10-Cierre de cuenta/Nomina/lnkAdministracionNomina'))

//Cambiar ventana "BCCL.AC.ADM.NOMINA"
WebUI.switchToWindowTitle('BCCL.AC.ADM.NOMINA')

//Maximizar pantalla
WebUI.maximizeWindow()

//Setear "No.Cuenta"
WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/12-BCCL.AC.ADM.NOMINA/txtNo.Cuenta'), '00560212394')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Modificacion Nomina"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/12-BCCL.AC.ADM.NOMINA/lnkModificacionNomina'))

//Cambiar ventana "BCCL.NOMINA.CH"
WebUI.switchToWindowTitle('BCCL.NOMINA.CH')
	
//Setear Monto del cheque
WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/02-BCCL.NOMINA.CH/txtMontodelcheque1'), '1,00')
	
//Seleccionar "btnAceptarelregistro"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/02-BCCL.NOMINA.CH/btnAceptarRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/25-Cierre de Cuenta/02-BCCL.NOMINA.CH/lblTxnCompleta'))

//Validar "Txn Completa"
def element = WebUI.getText(findTestObject('Object Repository/25-Cierre de Cuenta/02-BCCL.NOMINA.CH/lblTxnCompleta'))
assert element.contains('Txn Completa')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Setear en "AltaModifNomina"
WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/02-BCCL.NOMINA.CH/txtAltaModifNomina'), '00560212394')

//Seleccionar boton Modificar Registro
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/02-BCCL.NOMINA.CH/btnModificarRegistro'))
	
//Setear Monto del cheque
WebUI.setText(findTestObject('Object Repository/25-Cierre de Cuenta/02-BCCL.NOMINA.CH/txtMontodelcheque1'), '2,00')
	
//Seleccionar "btnAceptarelregistro"
WebUI.click(findTestObject('Object Repository/25-Cierre de Cuenta/02-BCCL.NOMINA.CH/btnAceptarRegistro'))

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}