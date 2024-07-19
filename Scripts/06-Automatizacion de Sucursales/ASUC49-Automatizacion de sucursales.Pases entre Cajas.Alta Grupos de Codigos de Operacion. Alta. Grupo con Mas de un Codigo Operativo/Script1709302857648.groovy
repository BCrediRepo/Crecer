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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 2), findTestData('MainData/Users').getValue(2, 2))
WebUI.maximizeWindow()

//Ingresar "?1" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?1')

//Seleccionar boton de buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')

//Seleccionar "Sucursal Piloto"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkSucursalPiloto'))

//Seleccionar "D2 - Automatizacion de Sucursales"
WebUI.click(findTestObject('Object Repository/17-Remesas/Temenos T24/lnkD2-AutomatizacionDeSuc'))

//Seleccionar "Administracion"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/Temenos T24/Sucursal Piloto/D2 - Automatizacion de Sucursales/lnkAdministracion'))

//Seleccionar "Alta o Modificacion de grupos de codigos oper"
WebUI.click(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/Temenos T24/Sucursal Piloto/D2 - Automatizacion de Sucursales/Administracion/lnkAltaoModificaciondegruposdecodigosoper'))

//Cambiar ventana "BCCL EB GRUPO CODOPE"
WebUI.switchToWindowTitle('BCCL EB GRUPO CODOPE')

//Setear Alta de grupos
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/txtAltadeGrupos'), '91')

//Maximizar Ventana
WebUI.maximizeWindow()

//Seleccionar "boton Modificar Registro"
WebUI.click(findTestObject('Object Repository/15-MONEX/14-GRUPO.COTIZACION/btnModificarRegistro'))

//Setear Descripcion
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/txtDescripcion'), 'PRUEBA ALTA GRUPO COD OP')

//Setear Descripcion corta
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/txtDescripcionCorta'), 'PRUEBA')

//Seleccionar "boton Expandir Multivalor"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/btnExpandirMultivalor'))
	
//Setear Codigo de Operacion 
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/txtCodigoOperacion'), '00743')

//Seleccionar "boton radio button SI"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/rbtnSI'))

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/02-TELLER,REPOSICION.POR.MENOS.PN099/btnAceptarRegistro'))

//Setear Alta de grupos
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/txtAltadeGrupos'), '91')

//Seleccionar "boton Ver Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnVerRegistro'))

//Verificar "Codigos de Operaciones"
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/lblCodigodeOperacion.2'))

//Validar "Codigos de Operaciones"
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/lblCodigodeOperacion.2'))
assert element.contains('Codigo de Operacion.2')

//Seleccionar "boton Volver Pantalla Aplicacion"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/btnVolverPantallaAplicacion'))

//Setear Alta de grupos 91
WebUI.setText(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/txtAltadeGrupos'), '91')

//Seleccionar "boton Modificar Registro"
WebUI.click(findTestObject('Object Repository/15-MONEX/14-GRUPO.COTIZACION/btnModificarRegistro'))

//Seleccionar "boton Borrar Codigo Operacion"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/BCCL EB GRUPO CODOPE/btnBorrarCodigoOperacion'))

//Seleccionar "boton Aceptar Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/02-TELLER,REPOSICION.POR.MENOS.PN099/btnAceptarRegistro'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/lblTxn Completa'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/lblTxn Completa'))
def element2 = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Recupero de Filiales/lblTxn Completa'))
assert element2.contains('Txn Completa:')

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