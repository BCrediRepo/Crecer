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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
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
WebUI.waitForElementVisible(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblMoneda'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblMoneda'))

def element = WebUI.getText(findTestObject('Object Repository/39-Cuentas/Saldos de Cuenta/lblMoneda'))

assert element.contains('Moneda')

//Switch a la ventana principal
WebUI.switchToWindowIndex(0)

//Click en transferencias mep
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkTransferenciasMEP'))

//Click en transferencias MEP entre cuentas
WebUI.click(findTestObject('Object Repository/02-Dashboard/10-MEP/Transferencias MEP/lnkTransferenciasMepEntreCuentas'))

//Switch a la ventana BCCL.MEP.FT.TRANSFER
WebUI.switchToWindowTitle('BCCL.MEP.FT.TRANSFER')

//Maximizamos
WebUI.maximizeWindow()

//Ingreso los datos
WebUI.setText(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/txtIDOrdenante'), '1004425912')

//Click en el btn lista de registro
WebUI.click(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/btnListaDeRegistro'))

//Seleccionamos la cuenta
WebUI.click(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/lblPAN FRANCES MDP SA'))

//Completamos el importe
WebUI.setText(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/txtImporte'), '100')

//Completamos el CBU
WebUI.setText(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/txtCBU Beneficiario'), '1980001730000000606629')

//Completamos el CUIT/CUIL Beneficiario
WebUI.setText(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/txtCUILCUITBeneficiario'), '27040510740')

//Completo el nombre beneficiario
WebUI.setText(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/txtNombreBeneficiario'), 'TEST')

//Misma titularidad
WebUI.click(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/rbtnNO'))

//Selecionamos la opcion en conozca a su asociado
WebUI.selectOptionByIndex(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/cbxConoceAsociado'), 2)

//Seleccionamos el motivo de transferencia
WebUI.click(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/btnMotivodeTransferencia'))
WebUI.click(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/spanDL1ALQ'))

//Completamos al descripcion motivo
WebUI.setText(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/txtDescripcionMotivo'), 'Pruebas')

//Click en validar registro
WebUI.click(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/btnValidarRegistro'))

//Click en aceptar registro
WebUI.click(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/btnAceptarRegistro'))

//Click en aceptar alertas
WebUI.click(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/lnkAceptarAlertas'))

//ASSERT
label = WebUI.getText(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/lblTxnCompleta'))

assert label.contains('Txn Completa:') == true

//VALIDO que la transaccion se haya completado y guardo el FT
Transaccion1 = WebUI.getText(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/lblTxnCompleta'))

// Dividir la cadena por espacios en blanco y tomar el segundo elemento
def partes = Transaccion1.split('\\s+')

def trx1 = partes[2]

assert Transaccion1.contains('Txn Completa:')


//Switch a la ventana Verificacion de Firmas - Fil.089 M.del Plata Ct
WebUI.switchToWindowTitle('Verificacion de Firmas - Fil.089 M.del Plata Ct')

//Maximizamos
WebUI.maximizeWindow()

//Selecionamos la opcion forzar
WebUI.selectOptionByIndex(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/cbxAccion'), 2)

//Click en aceptar registro
WebUI.click(findTestObject('Object Repository/00-Utils/03-Verificacion de Firmas/btnAceptar'))

//ASSERT
//WebUI.waitForElementVisible(findTestObject('Object Repository/13-MEP/Verificacion de Firmas - Fil.089 M.del Plata Ct/lblESTADO'), 2)
//WebUI.verifyElementVisible(findTestObject('Object Repository/13-MEP/Verificacion de Firmas - Fil.089 M.del Plata Ct/lblESTADO'))
//def element2 = WebUI.getText(findTestObject('Object Repository/13-MEP/Verificacion de Firmas - Fil.089 M.del Plata Ct/lblESTADO'))
//assert element2.contains('ESTADO')

WebUI.closeBrowser()

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,8), findTestData('MainData/Users').getValue(2,8))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresamos a Autorizaciones Pendientes
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkAutorizaciones'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkAutorizacionesPendientes'))

//Cambiamos a la ventana BCCL.E.AUTHORIZATION
WebUI.switchToWindowTitle('BCCL.E.AUTHORIZATION')

//Maximizamos
WebUI.maximizeWindow()

// Valor específico que estás buscando
String targetValue = trx1

def buscarElementoEnTabla(String targetValue) {
// Obtén el elemento de la tabla
WebElement table = DriverFactory.getWebDriver().findElement(By.id("datadisplay"))

// Obtén todas las filas dentro de la tabla
List<WebElement> rows = table.findElements(By.tagName("tr"))

// Itera a través de las filas
for (WebElement row : rows) {
	// Obtiene el tercer valor de la fila (índice 2, ya que las listas son base cero)
	WebElement cell = row.findElements(By.tagName("td"))[0]
 
	// Obtiene el texto de la celda
	String cellText = cell.getText()
 
	// Compara el valor de la celda con el valor específico
	if (cellText.equals(targetValue)) {
		// Obtiene la lista de elementos td
		List<WebElement> tdList = row.findElements(By.tagName("td"))
		
		// Accede al elemento td en la posición 8
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
	encontrado = buscarElementoEnTabla(targetValue)
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

//Click en autorizar el registro
WebUI.waitForElementVisible(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/btnAutorizaRegistro'), 6)
WebUI.click(findTestObject('Object Repository/13-MEP/BCCL.MEP.FT.TRANSFER/btnAutorizaRegistro'))

//ASSERT
label = WebUI.getText(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/lblTxnCompleta'))

assert label.contains('Txn Completa:') == true

//---------------------------------------------------
//Control Fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

