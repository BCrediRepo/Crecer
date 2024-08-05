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

// ESTE SCRIPT REALIZA UNA CARGA MASIVA DE SALDO. SE DEBE INGRESAR LOS DATOS EN LA PESTAÑA "CARGA SALDOS" DEL ARCHIVO MAINDATA.XLSX
// ADICIONALMENTE ESCRIBE EN UNA COLUMNA DEL MISMO ARCHIVO SI LA CARGA FUE EXITOSA O NO

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()

//Accedo al menu de Ajustes Monetarios Alta de Nota de Credito por Ajuste y completo el registro
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lnkAjustesMonetarios'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkAjustesMonetarios'))

// Inicializa la variable de Resultado Positivo
def resultadoPositivo = "SI"
def resultadoNegativo = "NO"

try {
	
// Abrir el archivo Excel existente
FileInputStream fileIn = new FileInputStream("Test Data/MainData.xlsx")
XSSFWorkbook workbook = new XSSFWorkbook(fileIn)
XSSFSheet sheet = workbook.getSheet("Carga Saldos")

//Encuentro la primera fila vacía en la hoja que va a ser el limite de la siguiente iteracion
int filaNum = 0
while (sheet.getRow(filaNum) != null) {
	filaNum++
}

// Iterar sobre cada fila en la tabla de datos 
for (int row = 1; row <= filaNum; row++) {
	String cuenta = findTestData('MainData/Carga Saldos').getValue(1, row)
	String importe = findTestData('MainData/Carga Saldos').getValue(2, row)
	String concepto = findTestData('MainData/Carga Saldos').getValue(3, row)
	
	WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/21-Impuestos/05-Ajustes Monetarios/lnkNotadeCreditoporAjustes'), 6)
	WebUI.click(findTestObject('Object Repository/02-Dashboard/21-Impuestos/05-Ajustes Monetarios/lnkNotadeCreditoporAjustes'))
	
	//Switch a la ventana de Nota de Credito por Ajustes
	WebUI.switchToWindowTitle('Movimiento de Fondos')
	WebUI.maximizeWindow()
	
	//Seteo de datos para la carga de saldo
	WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/txtNroCuenta'), 6)
	WebUI.setText(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/txtNroCuenta'), cuenta)
	WebUI.click(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/txtImporte'))
	WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/txtImporte'), 6)
	WebUI.setText(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/txtImporte'), importe)
	WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/txtConcepto'), 6)
	WebUI.setText(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/txtConcepto'), concepto)
	WebUI.click(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/txtImporte'))
	WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/btnAceptarRegistro'), 6)
	WebUI.click(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/btnAceptarRegistro'))
	
	//Acepto las Alertas y completo la transaccion
	WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/lnkAceptarAlertas'), 1)
	WebUI.click(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/lnkAceptarAlertas'))
	try {
		WebUI.waitForElementVisible(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/lblTxnCompleta'), 6)
		def element = WebUI.getText(findTestObject('Object Repository/23-Impuestos/08-Nota de Credito por Ajustes/lblTxnCompleta'))
		assert element.contains('Txn Completa:')
		WebUI.println(element)
		WebUI.println(cuenta)
		// Verificar si la fila existe, si no, crearla
		XSSFRow excelRow = sheet.getRow(row) 
		WebUI.println(excelRow)
        if (excelRow == null) {
        excelRow = sheet.createRow(row)
        }
		XSSFCell cell = excelRow.createCell(3) // Suponiendo que la columna adicional es la quinta columna (índice 4)
		cell.setCellValue(resultadoPositivo)
		WebUI.println(cell)
		WebUI.closeWindowTitle('Movimiento de Fondos')
		WebUI.closeWindowTitle('BCCL.CC.NOTA18505N.pdf', FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.switchToWindowIndex(0)
	} catch (Exception e) {
		// Escribir "NO" en la columna adicional
		// Verificar si la fila existe, si no, crearla
		XSSFRow excelRow = sheet.getRow(row) // Obtener fila actual
        if (excelRow == null) {
        excelRow = sheet.createRow(row)
        }
		XSSFCell cell = excelRow.createCell(3) // Suponiendo que la columna adicional es la quinta columna (índice 4)
		cell.setCellValue(resultadoNegativo)
		
		WebUI.closeWindowTitle('Movimiento de Fondos')
		WebUI.switchToWindowIndex(0)
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
