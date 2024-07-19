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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 31), findTestData('MainData/Users').getValue(2, 31))
WebUI.maximizeWindow()

//Ingresar "TELLER.ID,REASIGNA.USUARIO" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'TELLER.ID,REASIGNA.USUARIO')

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "TELLER ID"
WebUI.switchToWindowTitle('TELLER ID')

//Setear "Reasignar Caja"
WebUI.setText(findTestObject('Object Repository/17-Remesas/04-TELLER ID/txtReasignarCaja'), '0008')

//Seleccionar "boton Modificar Registro"
WebUI.click(findTestObject('Object Repository/15-MONEX/14-GRUPO.COTIZACION/btnModificarRegistro'))

//Definir Nombre de la Filial
TestObject nombreFilial = findTestObject('Object Repository/17-Remesas/04-TELLER ID/lblCajeroNombreFilial')

//Almacenar valorNumeroCajero
String valorNombreFilial = WebUI.getText(nombreFilial)

//Seleccionar "Boton Cajero"
WebUI.click(findTestObject('Object Repository/17-Remesas/04-TELLER ID/btnDropdownCajero'))

if (valorNombreFilial.equals('FILIAL 001 AI CENT')) {
	//Seleccionar "B.0805"
	WebUI.click(findTestObject('Object Repository/17-Remesas/04-TELLER ID/lblBOchocientoscinco'))
} else {
	//Seleccionar "B.0901"
	WebUI.click(findTestObject('Object Repository/17-Remesas/04-TELLER ID/lblBNovecientosuno'))
	}

//Seleccionar "Aceptar el registro"
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/btnAceptarRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lblTxnCompleta'))
def element2 = WebUI.getText(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lblTxnCompleta'))
assert element2.contains('Txn Completa')

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}