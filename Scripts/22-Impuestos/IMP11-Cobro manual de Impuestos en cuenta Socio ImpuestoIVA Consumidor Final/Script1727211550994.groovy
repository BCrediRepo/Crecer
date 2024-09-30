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
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,2), findTestData('MainData/Users').getValue(2,2))
WebUI.maximizeWindow()

//Ejecuta en la linea de comando
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("BCCL.PERSONA", 1)
WebUI.maximizeWindow()

//Ingresamos los datos
WebUI.setText(findTestObject('Object Repository/23-Impuestos/15-ARCHIVOS PERSONAS/txtBCCL.PERSONA'), '1000740898')
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

//Validamos campo Per Co Cprov
WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/15-ARCHIVOS PERSONAS/lblPer Co Cprov'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/23-Impuestos/15-ARCHIVOS PERSONAS/lblPer Co Cprov'))
def element = WebUI.getText(findTestObject('Object Repository/23-Impuestos/15-ARCHIVOS PERSONAS/lblPer Co Cprov'))
assert element.contains('Per Co Cprov')

//Switch a la ventana Principal
WebUI.switchToWindowIndex(0)
//Ejecuta en la linea de comando
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("BCCL.ALICUOTAS", 2)
WebUI.maximizeWindow()

//Ingresamos los datos
WebUI.setText(findTestObject('Object Repository/23-Impuestos/16-BCCL.ALICUOTAS/txtBCCL.ALICUOTAS'), 'IV00F.20150706')
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

// Verifica el valor de check y reporta el resultado
def Descripcion = WebUI.getText(findTestObject('Object Repository/23-Impuestos/16-BCCL.ALICUOTAS/spanAlic Consumidor Final IVA'))
if (Descripcion == "Alic Consumidor Final IVA") {
	Descripcion ==  ("Checkpoint estado: Coincide")
} else {
	Descripcion == ("Checkpoint estado: No coincide")
}
WebUI.closeBrowser()


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,39), findTestData('MainData/Users').getValue(2,39))
WebUI.maximizeWindow()

def menuDesplegable = ["Impuestos", "Cobro de Impuestos"]
def link = "Cobro Impuestos en Cuenta (Socio)"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowIndex(1)
WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txt ID PersonaSocio'), '1000740898')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtCuentaDebito'), '00895279312')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtProv Jurisdiccion'), '00')
WebUI.click(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/cbxMvtoGravadoOriginal'))
WebUI.selectOptionByIndex(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/cbxMvtoGravadoOriginal'), 1)
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtTipo de impuesto'), 'IV')
WebUI.click(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtMonto a Cobrar'))
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtMonto a Cobrar'), '1050')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtBase Imponible'), '5000')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtID Alicuota'), 'IV00F.20150706')
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Valido la transaccion completa
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def element2 = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert element2.contains('Txn Completa')

// Imprimir el numero de operacion en consola
println("El ID de la txt es: " + element2)

//Dividir la oración en palabras individuales utilizando el espacio como separador
String[] palabras = element2.split(" ");

// Obtener la tercera palabra (índice 2 ya que los índices comienzan en 0 en arrays)
String terceraPalabra = palabras[2];
 
// Imprimir la tercera palabra seleccionada
println("La tercera palabra es: " + terceraPalabra);

//Ingresa el numero de operacion obtenido
WebUI.setText(findTestObject('Object Repository/23-Impuestos/17-Movimiento de Fondos/txtFUNDS.TRANSFER,BCCL.IMPTOS.COB.CTA'), terceraPalabra)

//Click en ver un registro
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))

//Switch a la ventana Principal
WebUI.switchToWindowIndex(0)

def menuDesplegable2 = ["Cuentas", "Consultas de Cuentas"]
def link2 = "Consulta de Mov. por Fecha Proceso"
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable2, link2)
WebUI.switchToWindowIndex(2)
WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkNuevaSeleccion'))
WebUI.setText(findTestObject('Object Repository/23-Impuestos/18-Movimientos por Fecha de Cuentas/txtNroDeCuenta'), '00895279312')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/18-Movimientos por Fecha de Cuentas/txtFechaDesde'), '20230817')
WebUI.setText(findTestObject('Object Repository/23-Impuestos/18-Movimientos por Fecha de Cuentas/txtFechaHasta'), '20230818')
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebElement table = DriverFactory.getWebDriver().findElement(By.id("headingdisplay"))
WebElement header = table.findElement(By.tagName("tr"))
List<WebElement> cells = header.findElements(By.tagName("th"))
 
// Validar los textos de las celdas directamente
assert cells[0].getText().contains('Fecha') : "Expected 'Fecha' but found ${cells[0].getText()}"
assert cells[3].getText().contains('ID Transaccion') : "Expected 'ID Transaccion' but found ${cells[3].getText()}"
assert cells[6].getText().contains('Codigo') : "Expected 'Codigo' but found ${cells[6].getText()}"
assert cells[9].getText().contains('Descripcion') : "Expected 'Descripcion' but found ${cells[9].getText()}"
assert cells[12].getText().contains('Monto Debito') : "Expected 'Monto Debito' but found ${cells[12].getText()}"
assert cells[15].getText().contains('Monto Credito') : "Expected 'Monto Credito' but found ${cells[15].getText()}"
assert cells[18].getText().contains('Saldo') : "Expected 'Saldo' but found ${cells[18].getText()}"
assert cells[21].getText().contains('Fec Valor') : "Expected 'Fec Valor' but found ${cells[21].getText()}"
assert cells[24].getText().contains('Combte') : "Expected 'Combte' but found ${cells[24].getText()}"

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
