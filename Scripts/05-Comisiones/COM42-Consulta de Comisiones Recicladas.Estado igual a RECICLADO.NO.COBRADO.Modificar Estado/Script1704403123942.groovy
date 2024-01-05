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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(2, 1))
WebUI.maximizeWindow()

//Ingresar "BCCL.AC.COM.RECICLADA" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.AC.COM.RECICLADA')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "COMISIONES RECICLADAS"
WebUI.switchToWindowTitle('COMISIONES RECICLADAS')

//Setear Comision
WebUI.setText(findTestObject('Object Repository/06-Comisiones/COMISIONES RECICLADAS/txtBCCL.AC.COM.RECICLADA'), '00730029302-4375459946.03')

//Seleccionar "boton Modificar Registro"
WebUI.click(findTestObject('Object Repository/06-Comisiones/COMISIONES RECICLADAS/btnModificaregistro'))

//Cambiar los valores del cb
WebUI.selectOptionByIndex(findTestObject('Object Repository/06-Comisiones/COMISIONES RECICLADAS/cbRecicladoNoPagado'), 3)

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/06-Comisiones/COMISIONES RECICLADAS/btnAceptarRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/06-Comisiones/COMISIONES RECICLADAS/lblTxnCompleta'))

//Validar "Txn Completa"
def element = WebUI.getText(findTestObject('Object Repository/06-Comisiones/COMISIONES RECICLADAS/lblTxnCompleta'))
assert element.contains('Txn Completa')

//Setear Comision
WebUI.setText(findTestObject('Object Repository/06-Comisiones/COMISIONES RECICLADAS/txtBCCL.AC.COM.RECICLADA'), '00730029302-4375459946.03')

//Seleccionar "boton Modificar Registro"
WebUI.click(findTestObject('Object Repository/06-Comisiones/COMISIONES RECICLADAS/btnModificaregistro'))

//Cambiar los valores del cb
WebUI.selectOptionByIndex(findTestObject('Object Repository/06-Comisiones/COMISIONES RECICLADAS/cbRecicladoNoPagado'), 4)

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/06-Comisiones/COMISIONES RECICLADAS/btnAceptarRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/06-Comisiones/COMISIONES RECICLADAS/lblTxnCompleta'))

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}