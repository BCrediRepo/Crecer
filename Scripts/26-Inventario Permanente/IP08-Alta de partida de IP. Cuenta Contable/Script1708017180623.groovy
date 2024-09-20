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
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter

import java.text.SimpleDateFormat
import java.util.Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(
        2, 3))

WebUI.maximizeWindow()

//Ir a Inventario Permanente, Alta de partidas, contra cuenta del socio
def menuDesplegable = ["Inventario Permanente", "Alta de Partidas"]
def link = "Contra Cuenta Contable"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowTitle('BCCL.IP.PARTIDAS')

//Verifica lbl cuenta contable
WebUI.verifyElementVisible(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/lblCuentaContable'))

//Ingresa codigo IP
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/txtCodigoIP'), '0099')

//btn Validar
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))

//Ingresa id persona
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/txtPersona'), '2000514092')

//Ingresa cuenta 
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/txtCuenta'), '13190056217')
WebUI.click(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtCtaContableAltaID'))
WebUI.waitForElementVisible(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtCtaContableAltaID'), 3)
WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtCtaContableAltaID'), '2921952')
WebUI.click(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtFecha'))
WebUI.waitForElementVisible(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtFecha'), 3)
WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtFecha'), '20240226')//20230901

//btn Validar
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))

//Ingresa monto
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/txtMonto'), '100')

//acepta registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Espera y recibe mensaje de tx completa
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert element.contains('Txn Completa:')

// Imprimir el numero de operacion en consola
println("El ID de la txt es: " + element)
 
//Dividir la oración en palabras individuales utilizando el espacio como separador
String[] palabras = element.split(" ");
 
// Obtener la tercera palabra (índice 2 ya que los índices comienzan en 0 en arrays)
String terceraPalabra = palabras[2];
 
// Imprimir la tercera palabra seleccionada
println("La tercera palabra es: " + terceraPalabra);

WebUI.closeWindowTitle('BCCL.IP.PARTIDAS')

WebUI.switchToWindowIndex(0)
WebUI.maximizeWindow()

//Ejecuta en la linea de comando ENQ BCCL.E.IP.PARTIDAS.FIL.ALTAS
WebUI.switchToWindowIndex(0)
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("ENQ BCCL.E.IP.PARTIDAS.FIL.ALTAS", 1)

//Ingresa numero de operacion 
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.E.IP.PARTIDAS.FIL.ALTAS/txtNroPartida'), terceraPalabra)

//Boton ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Boton ver partida
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/btnVerDetalleDePartida'))
WebUI.switchToWindowTitle('BCCL.IP.PARTIDAS')

CodigoIp = WebUI.verifyElementVisible(findTestObject('Object Repository/27-Inventario Permanente/Alta partidas - Caja/lblCodigoIP'))
assert CodigoIp == true

//----------------------------------------------Control de fin de script----------------------------------------------//
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}
@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}