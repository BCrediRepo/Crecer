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
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 2), findTestData('MainData/Users').getValue(2, 2))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

def menuDesplegable = ["Sucursal Piloto", "Resumen de Cuentas", "Consultas"]
def link = "MOVIMIENTOS CUENTA CERRADA"

//Ejecutar en la linea de comando "?1"
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("?1", 1)

//Cambiar a la ventana "Temenos T24"
WebUI.switchToWindowIndex(1)

//Navegar por el menu de Temenos T24
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

//Cambiar a la ventana "Movimientos Cuenta Cerrada"
WebUI.switchToWindowIndex(2)

//Seteo de datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Nro. Cuenta','01000021927')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Desde','20200901')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Hasta','20200902')

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Verificar estado
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/05-Movimientos Cuenta Cerrada/lblEstado'))

//Validar estado "CERRADA"
def estadoACTIVA = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/05-Movimientos Cuenta Cerrada/lblEstado'))
assert estadoACTIVA.contains('CERRADA')

//Seleccionar lupa de Detalle
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/05-Movimientos Cuenta Cerrada/btnVerDetalleCompletoTxn'))

//Validar Numero de cuenta
def numeroCuenta = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/04-Consulta Detalle Movs Agrupados/lblNumerodeCuenta'))
assert numeroCuenta.contains('01000021927')

//Validar Transaccion ID
def transaccionID = WebUI.getText(findTestObject('Object Repository/18-Resumen de Cuenta/04-Consulta Detalle Movs Agrupados/lblTransaccionID'))
assert transaccionID.contains('CHG20245TGCB0')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}