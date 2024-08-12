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
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent

def buscarElementoEnTabla(String variable) {
	WebElement table1 = DriverFactory.getWebDriver().findElement(By.cssSelector("#datadisplay"))
	List<WebElement> rows = table1.findElements(By.tagName("tr"))
	for (WebElement row : rows) {
		WebElement cell = row.findElements(By.tagName("td"))[0]
		String cellText = cell.getText()
		println cellText
		if (cellText.equals(variable)) {
			List<WebElement> tdList = row.findElements(By.tagName("td"))
			WebElement tdElement0 = tdList[8]
			WebElement selectElement = tdElement0.findElement(By.tagName("select"))
			Select select = new Select(selectElement)
			select.selectByIndex(1)
			WebElement btnElement = tdElement0.findElement(By.cssSelector(".iconLink"))
			btnElement.click()
			return true
		}
	}
	return false
}

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 10), findTestData('MainData/Users').getValue(
        2, 10))

WebUI.maximizeWindow()

//Seleccionar "Rechazo de Cheques"
WebUI.click(findTestObject('02-Dashboard/06-Cheques rechazados/Cheques rechazados/lnkChequesRechazados'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Rechazo Cheques de Caja"
WebUI.click(findTestObject('02-Dashboard/06-Cheques rechazados/Cheques rechazados/lnkChequesRechazadosCaja'))

//Cambiar ventana "Rechazo Chq Caja Filial - Fil.001 Centro"
WebUI.switchToWindowTitle('Rechazo Chq Caja Filial - Fil.001 Centro')

//Maximizar pantalla
WebUI.maximizeWindow()

//Seleccionar "Nueva Seleccion con filtro"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/Filtros con frame/lnkNuevaSeleccion'))

WebUI.switchToFrame(findTestObject('00-Utils/02-Filtros/Filtros con frame/frmFiltro'), 0)

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('NUMERO DE CUENTA', '00010108387')

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('NUMERO CHEQUE', '54598140')


//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Ejecutar con filtro"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
WebUI.switchToWindowTitle('Rechazo Chq Caja Filial - Fil.001 Centro')

//Seleccionar "Rechazo cheques de caja en filial"
WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Rechazo Chq Caja Filial - Fil.001 Centro/lnkRechazochequesdecajaenfilial'))

try {
	//valida el rechazo del cheque y devuelve la txn
	WebUI.switchToFrame(findTestObject('Object Repository/00-Utils/04-Frames/frmInferior'), 3)	
	WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))
	WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))
	WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
	WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
	assert WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta')).contains("Txn Completa: ")
}catch (Exception e) {
	WebUI.switchToWindowIndex(0)
	WebUI.click(findTestObject('Object Repository/02-Dashboard/btnLogout'))
	
	
	//logea con usuario NIV4 o superior (en este caso, el 2055, que es Niv5
	//Configuracion de ambiente
	CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)	
	//Login
	CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(
			2, 1))	
	WebUI.maximizeWindow()
	
	//ingresa en la ventana de autorizaciones pendientes desde el dashboard
	WebUI.click(findTestObject('Object Repository/02-Dashboard/06-Cheques rechazados/Cheques rechazados/Autorizacion/lnkAutorizaciones'))
	WebUI.click(findTestObject('Object Repository/02-Dashboard/06-Cheques rechazados/Cheques rechazados/Autorizacion/lnkAutorizacionesPendientes'))
	WebUI.switchToWindowTitle("BCCL.E.AUTHORIZATION")
	
	//se guarda el dato a buscar en tabla en una variable
	String idTransaccion = "254598140"
	
	//utilizando listas y manejo de  dato en tablas, se busca la variable anterior (descripcion) en la tabla actual
	//por medio del metodo select podemos manipular los combobox. En este caso, para seleccionar la segunda opcion de este cbx
	//que es "autorizar registro"
	def encontrado = false
	while (!encontrado) {
		encontrado = buscarElementoEnTabla(idTransaccion)
	}

	
	WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.CHRECH.RECHAZADOS/btnAutorizar'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.CHRECH.RECHAZADOS/lblTxncompleta'))
	txn1 = WebUI.getText(findTestObject('Object Repository/08-Cheques Rechazados/BCCL.CHRECH.RECHAZADOS/lblTxncompleta'))
	assert txn1.contains("Txn Completa: ")
	
	WebUI.switchToWindowIndex(0)
	WebUI.click(findTestObject('Object Repository/02-Dashboard/btnLogout'))
	//Configuracion de ambiente
	CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
	
	//Login
	CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 10), findTestData('MainData/Users').getValue(
			2, 10))
	
	WebUI.maximizeWindow()
	
	//Seleccionar "Rechazo de Cheques"
	WebUI.click(findTestObject('02-Dashboard/06-Cheques rechazados/Cheques rechazados/lnkChequesRechazados'))
	
	//Screenshot
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
	
	//Seleccionar "Rechazo Cheques de Caja"
	WebUI.click(findTestObject('02-Dashboard/06-Cheques rechazados/Cheques rechazados/lnkChequesRechazadosCaja'))
	
	//Cambiar ventana "Rechazo Chq Caja Filial - Fil.001 Centro"
	WebUI.switchToWindowTitle('Rechazo Chq Caja Filial - Fil.001 Centro')
	
	//Maximizar pantalla
	WebUI.maximizeWindow()
	
	//Seleccionar "Nueva Seleccion con filtro"
	WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/Filtros con frame/lnkNuevaSeleccion'))
	
	WebUI.switchToFrame(findTestObject('00-Utils/02-Filtros/Filtros con frame/frmFiltro'), 0)
	
	CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('NUMERO DE CUENTA', '00010108387')
	
	CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('NUMERO CHEQUE', '54598140')
	
	
	//Screenshot
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
	
	//Seleccionar "boton Ejecutar con filtro"
	WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))
	WebUI.switchToWindowTitle('Rechazo Chq Caja Filial - Fil.001 Centro')
	
	//Seleccionar "Rechazo cheques de caja en filial"
	WebUI.click(findTestObject('Object Repository/08-Cheques Rechazados/Rechazo Chq Caja Filial - Fil.001 Centro/lnkRechazochequesdecajaenfilial'))
	
	//valida el rechazo del cheque y devuelve la txn
	
	WebUI.switchToFrame(findTestObject('Object Repository/00-Utils/04-Frames/frmInferior'), 3)	
	WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))
	WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnValidarRegistro'))
	WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))
	WebUI.click(findTestObject('Object Repository/00-Utils/01-CommandLine/USER.PROFILE/lnkAceptarAlertas'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
	assert WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta')).contains("Txn Completa: ")
		
}



@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

