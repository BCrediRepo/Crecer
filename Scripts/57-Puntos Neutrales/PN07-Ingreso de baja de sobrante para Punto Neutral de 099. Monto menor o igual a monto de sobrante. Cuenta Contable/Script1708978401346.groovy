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
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.WebDriver

//Ejecutar caso PN06
WebUI.callTestCase(findTestCase('57-Puntos Neutrales/PN06-Alta de Sobrante para Punto Neutral de 099. Monto MN ME valido. Ingresar comentarios. Dispositivo de la Sucursal. Sucursal tiene PN'), 
    [:], FailureHandling.STOP_ON_FAILURE)

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 53), findTestData('MainData/Users').getValue(2, 53))
WebUI.maximizeWindow()

//Ejecutar en la linea de comando "ENQ BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN"
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'("ENQ BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN", 1)

//Seteo de datos "Fecha Desde", "Sucursal", "Id Dispositivo", "Cartucho Gaveta"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Desde', '20200101')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '073')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Id Dispositivo', '70151')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cartucho/Gaveta', 'DEPOSITO')
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Definir la variable trx1 como "variable"
def variable = GlobalVariable.vTxn

//Desplegar la columna donde se muestra la info de las transacciones
WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnIdSobrante'))

//Logica para buscar el elemento en la tabla
def encontrado = false
//Bucle para buscar en multiples páginas
while (!encontrado) {
	//Logica para buscar el elemento en la tabla
	encontrado = CustomKeywords.'pkgModules.kywManejoDeTablas.seleccionComboboxConValidacion'('datadisplay', variable, 1, 1, 13)
		
	//Si no se encontro el valor, Seleccionar boton "Siguiente" y buscar nuevamente
	if (!encontrado) {
		//Realizar busqueda nuevamente despues de Seleccionar "Siguiente"
		WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnSiguiente'))
		WebUI.delay(2)
	}
}

//Cambiar ventana "Movimiento de Fondos"
WebUI.switchToWindowTitle('Movimiento de Fondos')
WebUI.setText(findTestObject('Object Repository/12-Transferencias Internas/Movimiento de Fondos/txtImporte'), '100')
WebUI.click(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/txtObservaciones'))
WebUI.setText(findTestObject('Object Repository/51-MAP/Movimiento de Fondos/txtObservaciones'), 'PRUEBAS CRECER')

WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//Validar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
def element = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
assert element.contains('Txn Completa')

//-----------------------------------------------------------------------------------
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}