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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 10), findTestData('MainData/Users').getValue(2, 10))
WebUI.maximizeWindow()

//Seleccionar "Rechazo de Cheques"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkRechazoDeCheques'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Rechazo Cheques de Caja"
WebUI.click(findTestObject('Object Repository/02-Dashboard/06-Cheques rechazados/lnkRechazoChequesdeCaja'))

//Cambiar ventana "Rechazo Chq Caja Filial - Fil.001 Centro"
WebUI.switchToWindowTitle('Rechazo Chq Caja Filial - Fil.001 Centro')

//Maximizar pantalla
WebUI.maximizeWindow()

//Seleccionar "Nueva Seleccion con filtro"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/Filtros con frame/lnkNuevaSeleccion'))

//Seleccionar boton Ejecutar con filtro
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/Filtros con frame/lnkEjecutar'))

//Seleccionar "Lupa con filtro"
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Rechazo Chq Caja Filial - Fil.001 Centro/btnPantallaSeleccion'))

//Setear "Numero de Cuenta"
WebUI.setText(findTestObject('Object Repository/08-Cheques Rechazados/Rechazo Chq Caja Filial - Fil.001 Centro/txtNumerodeCuenta'), '00010108387')

//Setear "Numero de Cheque"
WebUI.setText(findTestObject('Object Repository/08-Cheques Rechazados/Rechazo Chq Caja Filial - Fil.001 Centro/txtNumeroCheque'), '54598140')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Ejecutar con filtro"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/Filtros con frame/lnkEjecutar'))

//Seleccionar "Rechazo cheques de caja en filial"
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Rechazo Chq Caja Filial - Fil.001 Centro/lnkRechazochequesdecajaenfilial'))

//Verificar "EXISTE REGISTRO PENDIENTE AUTORIZACION"
WebUI.verifyElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/Rechazo Chq Caja Filial - Fil.001 Centro/lblExisteRegistroPendiente'))

//Validar "EXISTE REGISTRO PENDIENTE AUTORIZACION"
def element = WebUI.getText(findTestObject('Object Repository/08-Cheques Rechazados/Rechazo Chq Caja Filial - Fil.001 Centro/lblExisteRegistroPendiente'))
assert element.contains('EXISTE REGISTRO PENDIENTE AUTORIZACION')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}