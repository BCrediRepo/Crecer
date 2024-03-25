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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,59), findTestData('MainData/Users').getValue(2,59))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click de cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkCuentas'))

//Click en consultas de cuenta
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/spanConsultas de Cuenta'))

//Click en consulta de saldo al dia
WebUI.click(findTestObject('Object Repository/02-Dashboard/37-Cuentas/lnkConsultadeSaldoalDia'))

//Switch a la ventana Saldos de Cuenta
WebUI.switchToWindowTitle('Saldos de Cuenta')

//Maximizamos
WebUI.maximizeWindow()

//Ingresamos la cuenta a consultar
WebUI.setText(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/txtCuenta'), '11300014928')

//Click en ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblNro.deCuenta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblNro.deCuenta'))

def element = WebUI.getText(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblNro.deCuenta'))

assert element.contains('Nro. de Cuenta')

WebUI.closeBrowser()

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en transferencias mep
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkTransferenciasMEP'))

//Click en trasnferencias MEP otros conceptos
WebUI.click(findTestObject('Object Repository/02-Dashboard/10-MEP/Transferencias MEP/lnkTransferenciasMEPOtrosConceptos'))

//Switch a la ventana BCCL.MEP.FT.TRANSFER
WebUI.switchToWindowTitle('BCCL.MEP.FT.TRANSFER')

//Maximizamos
WebUI.maximizeWindow()

//Seleccionamos el tipo de cuenta
WebUI.selectOptionByIndex(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/cbxTipoDeCuenta'), 1)

//Ingresamos el ID ordenante
WebUI.setText(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/txtIDOrdenante'), '1003174696')

//Ingresamos el nro de cuenta debito socio
WebUI.click(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/btnDrillDown'))
WebUI.click(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/lblIDcuenta'))

//Ingresamos el importe
WebUI.setText(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/txtImporte'), '50')

//Ingresamos la entidad acreedora
WebUI.setText(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/txtEntidadAcreedora'), 'BANCO DE LA NACION ARGENTINA')

//Seleccionamos el tipo de titularidad
WebUI.click(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/rbtnSI'))

//Seleccionamos la opcion correcta en conozca a su asociado
WebUI.selectOptionByIndex(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/cbxConoceAsociado'), 2)

//Ingresamos el CBU
WebUI.setText(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/txtCBU Beneficiario'), '1910054455005404689750')

//Ingresamos el CUIT/CUIL del beneficiario
WebUI.setText(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/txtCUILCUITBeneficiario'), '30711351937')

//Ingresamos el nombre del beneficiario
WebUI.setText(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/txtNombreBeneficiario'), 'E M Corbata Angelletti')

//Ingresamos las observaciones
WebUI.setText(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/txtObservaciones'), 'Pruebas')

//Click en validar registro
WebUI.click(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/btnValidarRegistro'))

//Click en aceptar registro
WebUI.click(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/btnAceptarRegistro'))

//Click en aceptar alertas
WebUI.click(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/lnkAceptarAlertas'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/13-MEP/Transferencias MEP/BCCL.MEP.FT.TRANSFER/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/13-MEP/Transferencias MEP/BCCL.MEP.FT.TRANSFER/lblTxnCompleta'))

def element2 = WebUI.getText(findTestObject('Object Repository/13-MEP/Transferencias MEP/BCCL.MEP.FT.TRANSFER/lblTxnCompleta'))

// Dividir la cadena por espacios en blanco y tomar el segundo elemento
def partes = element2.split('\\s+')
 
def trx1 = partes[2]

assert element2.contains('Txn Completa')

//Switch a la ventana Verificacion de Firmas - Fil.089 M.del Plata Ct
WebUI.switchToWindowTitle('Verificacion de Firmas - Fil.089 M.del Plata Ct')

//Maximizamos
WebUI.maximizeWindow()

//Selecionamos la opcion forzar
WebUI.selectOptionByIndex(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/cbxAccion'), 2)

//Aceptamos el registro
WebUI.click(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/btnAceptar'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblFinalizada'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblFinalizada'))

def element3 = WebUI.getText(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/lblFinalizada'))

assert element3.contains('FINALIZADA')

WebUI.closeBrowser()


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,8), findTestData('MainData/Users').getValue(2,8))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en autorizaciones
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkAutorizaciones'))

//Click en Autorizaciones Pendientes
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkAutorizacionesPendientes'))

//Switch a la ventana BCCL.E.AUTHORIZATION
WebUI.switchToWindowTitle('BCCL.E.AUTHORIZATION')

//Maximizamos
WebUI.maximizeWindow()

//Definir la variable trx1 como "variable"
def variable = trx1

//Esta funcion es invocada cuando se pregunta si el elemento que se quiere encontrar fue localizado en la tabla. Retorna un valor boolean
def buscarElementoEnTabla(String targetValue) {
	//Itero en la tabla buscado la FT originada y cliqueando en PAGAR
	// Obtén el elemento de la tabla
	WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))
	// Obtén todas las filas dentro de la tabla
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	// Itera a través de las filas
	//Despliego la columna donde se muestra la info de las transacciones
	//WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnIdSobrante'))
	for (WebElement row : rows) {
		// Obtiene el tercer valor de la fila (índice 1, ya que las listas son base cero)
		WebElement cell = row.findElements(By.tagName("td"))[0]
 
		// Obtiene el texto de la celda
		String cellText = cell.getText()
		println(cellText)
		println(targetValue)
		// Compara el valor de la celda con el valor específico
		if (cellText.equals(targetValue)) {
			// Realiza las acciones necesarias si se encuentra el valor
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			WebElement tdElement = tdList[8]
			WebElement comboBox = tdElement.findElement(By.tagName("select"))
			// Utiliza Select para interactuar con el comboBox
			def select = new Select(comboBox)
			select.selectByVisibleText("AUTORIZAR TRANSACCION")
			// Encuentra el elemento 'img' dentro del enlace 'a'
			WebElement imgElement = tdElement.findElement(By.cssSelector("a[title='Select Drilldown'] img"))
			// Haz clic en el elemento 'img'
			imgElement.click()
			return true
		}
	}
	return false
}
 
// Lógica para buscar el elemento en la tabla
def encontrado = false
 
// Bucle para buscar en múltiples páginas
while (!encontrado) {
	// Lógica para buscar el elemento en la tabla
	encontrado = buscarElementoEnTabla(variable)
	// Si no se encontró el valor, hacer clic en el botón "Siguiente" y buscar nuevamente
	if (!encontrado) {
		// Realiza la búsqueda nuevamente después de hacer clic en "Siguiente"
		WebUI.click(findTestObject('Object Repository/58-Puntos Neutrales/03-BCCL.E.BAJA.SOBRANTE.DISPO.GEOP.PN/btnSiguiente'))
		// Espera a que la nueva página se cargue completamente
		WebUI.delay(2)
	}
}


//Switch a la ventana BCCL.MEP.FT.TRANSFER
WebUI.switchToWindowTitle('BCCL.MEP.FT.TRANSFER')

//Click en autorizar registro
WebUI.waitForElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/02-Carga Manual de Card-Carrier No Encontrado/imgAutorizaRegistro'), 6)
WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/02-Carga Manual de Card-Carrier No Encontrado/imgAutorizaRegistro'))

//ASSERT
WebUI.waitForElementVisible(findTestObject('Object Repository/13-MEP/Transferencias MEP/BCCL.MEP.FT.TRANSFER/lblTxnCompleta'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/13-MEP/Transferencias MEP/BCCL.MEP.FT.TRANSFER/lblTxnCompleta'))

def element4 = WebUI.getText(findTestObject('Object Repository/13-MEP/Transferencias MEP/BCCL.MEP.FT.TRANSFER/lblTxnCompleta'))

assert element4.contains('Txn Completa')

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



