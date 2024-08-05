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
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import org.openqa.selenium.Keys as Keys
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFSheet
import com.kms.katalon.core.webui.driver.DriverFactory

// ESTE SCRIPT REALIZA UN ALTA MASIVA DE CUENTAS. SE DEBE INGRESAR LOS DATOS EN LA PESTAÑA "DATOSNCD" DEL ARCHIVO MAINDATA.XLSX
// ADICIONALMENTE ESCRIBE EN UNA COLUMNA DEL MISMO ARCHIVO SI LA CARGA FUE EXITOSA O NO CON EL NUMERO DE CUENTA CORRESPONDIENTE QUE SE DIO DE ALTA

// Inicializa la variable de Resultado Positivo
def resultadoPositivo = "SI"
def resultadoNegativo = "NO"

try {
	
// Abrir el archivo Excel existente
FileInputStream fileIn = new FileInputStream("Test Data/MainData.xlsx")
XSSFWorkbook workbook = new XSSFWorkbook(fileIn)
XSSFSheet sheet = workbook.getSheet("DatosNCD")

//Encuentro la primera fila vacía en la hoja que va a ser el limite de la siguiente iteracion
int filaNum = 0
while (sheet.getRow(filaNum) != null) {
	filaNum++
}

for (int row = 1; row <= filaNum; row++) {
    GlobalVariable.vDNI = findTestData('MainData/DatosNCD').getValue(1, row)
    GlobalVariable.vFECHA = findTestData('MainData/DatosNCD').getValue(2, row)
	GlobalVariable.vCODPROD = findTestData('MainData/DatosNCD').getValue(3, row)
	GlobalVariable.vTARIFARIO = findTestData('MainData/DatosNCD').getValue(4, row)
	
	try {
		//---------------------------------------------------------------------------------------------------------------------
		//Test Case Name: Alta de Cuenta
		//Configuracion del ambiente
		CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
		
		//Login
		CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,30), findTestData('MainData/Users').getValue(2,30))
		WebUI.maximizeWindow()
		
		//Ingresamos por el menu al Alta de Cuentas
		menuDesplegable = ["Cuentas","Alta de Cuenta"]
		link = "Alta de Cuenta Persona Fisica"
		CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)
		
		//Switch a la ventana de Alta de Cuentas
		WebUI.switchToWindowIndex(1)
		WebUI.maximizeWindow()
		
		//Ingresamos al alta de Cuenta y seteamos el DNI de la persona a la que se le va a dar de ALTA la CUENTA
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAltadeCuentaPersonaFisica'), 6)
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAltadeCuentaPersonaFisica'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkNuevaSeleccionF2'), 6)
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkNuevaSeleccionF2'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnEjecutarF2'), 6)
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnEjecutarF2'))
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAltadeCuentaPersonaFisica'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtNroDniF2'), 6)
		WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtNroDniF2'), GlobalVariable.vDNI)
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnEjecutarF2'), 6)
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnEjecutarF2'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAltaCuentaF2'), 6)
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAltaCuentaF2'))
		
		//Cargamos los datos del PREALTA
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtGrupoCuentasF3'), 6)
		WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtGrupoCuentasF3'), 'CAHR')
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtCodigoProductoF3'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtCodigoProductoF3'), 6)
		WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtCodigoProductoF3'), GlobalVariable.vCODPROD)
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtUsoCuentaF3'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtUsoCuentaF3'), 6)
		WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtUsoCuentaF3'), 'PERSONAL')
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnAceptarRegistroF3'), 6)
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnAceptarRegistroF3'))
		def numPreAlta = WebUI.getText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblPREnumAltaF3'))
		
		//Ingresamos al Link de ALTA y completamos las diferentes tablas
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAltaF2'), 6)
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAltaF2'))
		
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtFormadeOperarCuentaF3S1'), 6)
		WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtFormadeOperarCuentaF3S1'), '01')
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtTarifarioF3S1'), 6)
		WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtTarifarioF3S1'), GlobalVariable.vTARIFARIO)
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtSectorBCRAF3S1'), 6)
		WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtSectorBCRAF3S1'), '2')
		WebUI.selectOptionByIndex(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/selectMotHab'), 1)
		
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/tabDatosComercialesF3S2'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtCampaaF3S2'), 6)
		WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtCampaaF3S2'), 'CAM0000000000')
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtIdentificacionF3S2'), 6)
		WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtIdentificacionF3S2'), 'AFORNASAR')
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtCanalF3S2'), 6)
		WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtCanalF3S2'), '09')
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtFechaVentaF3S2'), 6)
		WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtFechaVentaF3S2'), GlobalVariable.vFECHA)
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtSegmentoF3S2'), 6)
		WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtSegmentoF3S2'), '04')
		
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/tabFirmantesF3S3'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtFormadeOperarF3S3'), 6)
		WebUI.setText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtFormadeOperarF3S3'), '01')
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtDomicilioF3S3'), 6)
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/dpdDomicilioF3S3'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblDomicilioF3S3'), 6)
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblDomicilioF3S3'))
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/txtFormadeOperarF3S3'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnAceptarRegistroF3'), 6)
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnAceptarRegistroF3'))
		
		//Aceptamos las alertas y validamos el estado de la transaccion
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAceptarAlertasF3'), 6)
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAceptarAlertasF3'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblINAUF3'), 6)
		WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblINAUF3'))
		def element = WebUI.getText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblINAUF3'))
		assert element.contains('INAU')
		WebUI.closeBrowser()
		
		//---------------------------------------------------------------------------------------------------------------------
		//Test Case Name: Autorizacion de Alta de Cuenta
		//Configuracion del ambiente
		CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
		
		//Login
		CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))
		WebUI.maximizeWindow()
		
		//Ingresamos por el menu Autorizaciones
		menuDesplegable = ["Autorizaciones"]
		link = "Autorizacion de Alta de Contratos"
		CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)
		
		//Switch a la ventana de Autorizaciones
		WebUI.switchToWindowIndex(1)
		WebUI.maximizeWindow()
		
		//Ingresamos al link de autorizacion y completamos el alta
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAutorizaciondeContratos'), 6)
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAutorizaciondeContratos'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkVerPREALTA'), 6)
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkVerPREALTA'))
		
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAutorizarProductoF3'), 6)
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lnkAutorizarProductoF3'))
		
		//Autorizamos el PRE ALTA y VALIDAMOS que la transaccion finalice correctamente
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnAutorizoPREALTAF3'), 6)
		WebUI.click(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/btnAutorizoPREALTAF3'))
		
		WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblTRANSACCIONFINALIZADAF3'), 6)
		WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblTRANSACCIONFINALIZADAF3'))
		def element2 = WebUI.getText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblTRANSACCIONFINALIZADAF3'))
		assert element2.contains('TRANSACCION FINALIZADA')
		def numeroCuenta
		numeroCuenta = WebUI.getText(findTestObject('Object Repository/TEST-8800 - Alta de Cuenta/lblNumNewCuenta'))
		
		// Verificar si la fila existe, si no, crearla
		XSSFRow excelRow = sheet.getRow(row)
		WebUI.println(excelRow)
		if (excelRow == null) {
		excelRow = sheet.createRow(row)
		}
		XSSFCell cell = excelRow.createCell(4) // Suponiendo que la columna adicional es la quinta columna (índice 4)
		cell.setCellValue(resultadoPositivo)
		WebUI.println(cell)
		XSSFCell cellCuenta = excelRow.createCell(5) // Guardo el numero de cuenta creado
		cell.setCellValue(numeroCuenta)
		WebUI.println(numeroCuenta)
		WebUI.closeBrowser()
		
	} catch (Exception e) {
		// Escribir "NO" en la columna adicional
		// Verificar si la fila existe, si no, crearla
		XSSFRow excelRow = sheet.getRow(row) // Obtener fila actual
        if (excelRow == null) {
        excelRow = sheet.createRow(row)
        }
		XSSFCell cell = excelRow.createCell(4) // Suponiendo que la columna adicional es la quinta columna (índice 4)
		cell.setCellValue(resultadoNegativo)
		WebUI.closeBrowser()
	}
	
	// Escritura de datos en la hoja de Excel
	FileOutputStream fileOut = new FileOutputStream("Test Data/MainData.xlsx")
	workbook.write(fileOut)
	fileOut.close()
}

// Cerrar flujo de entrada de archivo
fileIn.close()
workbook.close()

} catch (Exception e) {
	e.printStackTrace()
}


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

