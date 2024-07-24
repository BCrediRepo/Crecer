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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import org.jsoup.Jsoup as Jsoup
import org.jsoup.nodes.Document as Document
import org.openqa.selenium.support.ui.Select as Select

//Generacion de posteo a traves de caso IP07
WebUI.callTestCase(findTestCase('26-Inventario Permanente/IP07-Alta de partida de IP. Caja'), [:], FailureHandling.STOP_ON_FAILURE)

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 59), findTestData('MainData/Users').getValue(2, 59))

WebUI.maximizeWindow()

//Seleccionar "Inventario Permanente"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkInventarioPermanente'))

//Seleccionar "Baja de Partidas"
WebUI.click(findTestObject('Object Repository/02-Dashboard/25-Inventario Permanente/Alta de Partidas/lnkBajadePartidas'))

//Cambiar a la ventana "BCCL.E.IP.BAJA.PARTIDAS"
WebUI.switchToWindowIndex(1)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Codigo IP', '0099')

//Maximizar Pantalla
WebUI.maximizeWindow()

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Cambiar opción del ComboBox a "Baja Cuenta Caja"
WebUI.selectOptionByIndex(findTestObject('Object Repository/27-Inventario Permanente/BCCL.E.IP.BAJA.PARTIDAS/cbBajaCuentaContable'), 
    3)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Select Drill down"
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.E.IP.BAJA.PARTIDAS/btnSelectDrilldown'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Aceptar Registro"
WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/btnAceptarRegistro'))

//Definir Objeto
Transaccion = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Dividir la cadena por espacios en blanco y tomar elemento
def partes = Transaccion.split('\\s+')
def trx1 = partes[2]
assert Transaccion.contains('Txn Completa:')

//Cambiar a la ventana del Dashboard
WebUI.switchToWindowIndex(0)

//Setear "BCCL.IP.PARTIDAS" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.IP.PARTIDAS')

//Seleccionar boton buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana "BCCL.IP.PARTIDAS"
WebUI.switchToWindowIndex(2)

//Setear Numero de Partida
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/txtBCCLIPPARTIDAS'), trx1)

//Maximizar Pantalla
WebUI.maximizeWindow()

//Seleccionar "btn Ver Registro"
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnVerRegistro'))

//Definir ftBaja
ftBaja = WebUI.getText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/lblFtBaja'))

//Definir Variable Global
GlobalVariable.vTxn = ftBaja

//Volver a Logearse con el usuario que liquida Posteos
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 2), findTestData('MainData/Users').getValue(2, 2))

WebUI.maximizeWindow()

//Ingresar "?303" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?303')

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "Temenos T24"
WebUI.switchToWindowIndex(1)

//Seleccionar "Posteo"
WebUI.click(findTestObject('Object Repository/37-Posteo/Temenos T24/lnkPosteoLiquidacion'))

//Seleccionar "Transacciones Pendientes de Liquidacion"
WebUI.click(findTestObject('Object Repository/37-Posteo/Temenos T24/Posteo/lblTransaccionesPendientesdeLiquidacion'))

//Cambiar ventana "BCCL.E.EB.POSTEO.INAU"
WebUI.switchToWindowIndex(2)

//Maximizar Pantalla
WebUI.maximizeWindow()

//Esperar 2 seg a que se cargue la pagina
WebUI.delay(2)

//Definir GlobalVariable como "variable"
def variable = GlobalVariable.vTxn

def encontrado = false

//Bucle para buscar en multiples páginas
while (!(encontrado)) {
    //Logica para buscar el elemento en la tabla
    encontrado = buscarElementoEnTabla(variable)

    //Si no se encontro el valor, Seleccionar boton "Siguiente" y buscar nuevamente
    if (!(encontrado)) {
        //Realizar busqueda nuevamente despues de Seleccionar "Siguiente"
        WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnSiguiente'))

        //Esperar 2 seg a que se cargue la pagina
        WebUI.delay(2)
    }
}

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Autorizar Registro"
WebUI.click(findTestObject('Object Repository/37-Posteo/Movimiento de Fondos/btnAutorizarRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Validar "Txn Completa"
def element = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

assert element.contains('Txn Completa') 

//Esta funcion es invocada cuando se pregunta si el elemento que se quiere encontrar fue localizado en la tabla. Retorna un valor boolean
def buscarElementoEnTabla(String variable) {
	//Obtener elemento de la tabla
	WebElement table = DriverFactory.getWebDriver().findElement(By.cssSelector('#datadisplay'))

    List<WebElement> rows = table.findElements(By.tagName('tr'))

    for (WebElement row : rows) {
        WebElement cell = row.findElements(By.tagName('td'))[0]

        String cellText = cell.getText()

        if (cellText.equals(variable)) {
            List<WebElement> tdList = row.findElements(By.tagName('td'))

            WebElement tdElement = tdList[8]

            String tdElementText = tdElement.getText()

            WebElement liquidar = tdElement.findElement(By.tagName('a'))
			
			//Seleccionar liquidar
            liquidar.click()

            return true
        }
    }
    
    return false
}

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

