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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,11), findTestData('MainData/Users').getValue(2,11))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Movimientos Automaticos"
WebUI.click(findTestObject('02-Dashboard/lnkMovimientosAutomaticos'))

//Seleccionar "Imputar Arreglos"
WebUI.click(findTestObject('02-Dashboard/14-Movimientos Automaticos/lnkImputarArreglos'))

//Cambiar a la ventana "BCCL.E.MOV.AUT.TRANS.ARR"
WebUI.switchToWindowIndex(1)

//Seteo de Datos "SUCURSAL"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('SUCURSAL','043')

//ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Seleccionar boton Fix
WebUI.click(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.ARR/btnFix'))

//Setear Nueva Cuenta
WebUI.setText(findTestObject('Object Repository/16-Movimientos Automaticos/Mov-Automaticos Data/txtNuevaCuenta'), '')

//Seleccionar boton dropdown de Nueva Cuenta (CLICKEAR VARIAS VECES POR QUE A VECES CARGAN LOS LEABEL'S) 
WebUI.click(findTestObject('Object Repository/16-Movimientos Automaticos/Mov-Automaticos Data/btnDropdownNuevaCuenta'))

//Seleccionar boton dropdown de Nueva Cuenta (CLICKEAR VARIAS VECES POR QUE A VECES CARGAN LOS LEABEL'S)
WebUI.click(findTestObject('Object Repository/16-Movimientos Automaticos/Mov-Automaticos Data/btnDropdownNuevaCuenta'))

//Seleccionar boton dropdown de Nueva Cuenta (CLICKEAR VARIAS VECES POR QUE A VECES CARGAN LOS LEABEL'S)
WebUI.click(findTestObject('Object Repository/16-Movimientos Automaticos/Mov-Automaticos Data/btnDropdownNuevaCuenta'))

//Seleccionar primera Cuenta ID
WebUI.click(findTestObject('Object Repository/16-Movimientos Automaticos/Mov-Automaticos Data/lblCuentaID'))

//ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton Aceptar Registro
WebUI.click(findTestObject('Object Repository/17-Remesas/02-TELLER,REPOSICION.POR.MENOS.PN099/btnAceptarRegistro'))

//ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar Aceptar Alertas
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/lnkAceptarAlertas'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Validar "Txn Completa"
def element = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))
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