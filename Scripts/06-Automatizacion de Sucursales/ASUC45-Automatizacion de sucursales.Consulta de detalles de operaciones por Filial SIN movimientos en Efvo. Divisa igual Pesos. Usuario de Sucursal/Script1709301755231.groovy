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

//Ingresar "?70" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?70')

//Seleccionar boton de buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Temenos T24"
WebUI.switchToWindowTitle('Temenos T24')

//Seleccionar "Consultas Varias"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/lnkConsultasVarias'))

//Seleccionar "Consultas de Atencion a Usuarios"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/ConsultasVarias/lnkConsultasdeAtencionaUsuarios'))

//Seleccionar "Consultas de Operatoria en Linea de Cajas"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/ConsultasVarias/Consultas de Atencion a Usuarios/lnkConsultasdeOperatoriaenLineadeCajas'))

//Seleccionar "Consultas de Cierre Operatoria"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/ConsultasVarias/Consultas de Atencion a Usuarios/Consultas de Operatoria en Linea de Cajas/lnkConsultasdeCierreOperatoria'))

//Seleccionar "Detalle de Operaciones Sin Efvo (Filial)"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/ConsultasVarias/Consultas de Atencion a Usuarios/Consultas de Operatoria en Linea de Cajas/Consultas de Cierre Operatoria/lnkDetalleOpSinEfvo(Filial)'))

//Cambiar ventana "Totales Sucursal x Cod Oper."
WebUI.switchToWindowTitle('Totales Sucursal x Cod Oper.')

//Seteo de Datos "Moneda", "Sucursal"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Moneda', 'ARS')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '043')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Seleccionar "boton Ver Detalle"
WebUI.click(findTestObject('Object Repository/07-Automatizacion de Sucursales/Temenos T24/Consultas Totales Administrativos/DetalleOpSinEfectivoFILIAL/Totales Sucursal x Cod Oper/btnVerDetalle'))

//Cambiar ventana "Detalle Transacciones No Efectivo"
WebUI.switchToWindowTitle('Detalle Transacciones No Efectivo')

//Verificar la sucursal
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Automatizacion de Sucursales/Totales Usuario x Cod Oper/lblSucursal'))
def element = WebUI.getText(findTestObject('Object Repository/07-Automatizacion de Sucursales/Totales Usuario x Cod Oper/lblSucursal'))
assert element.contains('043')

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