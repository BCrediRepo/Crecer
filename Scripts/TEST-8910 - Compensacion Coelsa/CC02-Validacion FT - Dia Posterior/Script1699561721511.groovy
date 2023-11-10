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
import java.text.SimpleDateFormat
import java.util.Date
// Importar las clases necesarias de Apache POI para manipular archivos
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFSheet
import com.kms.katalon.core.webui.driver.DriverFactory

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,30), findTestData('MainData/Users').getValue(2,30))
WebUI.maximizeWindow()

//Ingreso el FT a comparar
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'FT S ' + GlobalVariable.vFTdelIDCoelsa)
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Me muevo a la ventana de la FT
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Maximizamos
WebUI.maximizeWindow()

//Guardo los valores que deseo
GlobalVariable.vDenomBen = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/L.DENOM.BEN.1'))
GlobalVariable.vCreditAcc = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblCREDIT.ACCT.NO'))
GlobalVariable.vDebitAcc = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblDEBIT.ACCT.NO'))
GlobalVariable.vDebitAmount = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblDEBIT.AMOUNT'))
GlobalVariable.vCUIBen = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblL.CUI.BEN.1'))
GlobalVariable.vCuitCuil = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblL.CUIT.CUIL'))
GlobalVariable.vDescEspC = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblL.DESC.ESP.CR'))
GlobalVariable.vDescEspD = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblL.DESC.ESP.DB'))
GlobalVariable.vCodOprC = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblL.GEN.COD.OPR.C'))
GlobalVariable.vCodOprD = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblL.GEN.COD.OPR.D'))

//Maximizamos
WebUI.maximizeWindow()
//WebUI.switchToFrame(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/frmFullFtCoelsa2'), 1)

//Toma un ScreenShot a pagina completa
//CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
WebUI.takeElementScreenshot(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblDEBIT.ACCT.NO'))
WebUI.takeElementScreenshot(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/L.DENOM.BEN.1'))

//Me muevo a la ventana de la FT
//WebUI.switchToWindowTitle('Movimiento de Fondos')

//TEMA ARCHIVO
//---------------------------------------------------------------------------------------------------------------------
try {
	//Abrir archivo Excel existente
	FileInputStream fileIn = new FileInputStream("Test Data/Compensacion Coelsa.xlsx")
	XSSFWorkbook workbook = new XSSFWorkbook(fileIn)
	XSSFSheet sheet1 = workbook.getSheet("Hoja1")
	XSSFSheet sheet2 = workbook.getSheet("Hoja2")

	//Arreglo con valores que van a ser escritos en el archivo Excel segun orden 
	def valores = [GlobalVariable.vIDCoelsa, GlobalVariable.vFTdelIDCoelsa, GlobalVariable.vDenomBen, GlobalVariable.vCreditAcc, GlobalVariable.vDebitAcc, 
		GlobalVariable.vDebitAmount, GlobalVariable.vCUIBen, GlobalVariable.vCuitCuil, GlobalVariable.vDescEspC, GlobalVariable.vDescEspD, GlobalVariable.vCodOprC, GlobalVariable.vCodOprD]
	
	//Encuentro la primera fila vacía en la hoja
	int filaNum = 0
	while (sheet2.getRow(filaNum) != null) {
		filaNum++
	}

	//Número de fila en la que deseas escribir los datos
    def fila = sheet2.createRow(filaNum)

    //Indice de columna para comenzar a escribir datos
    int columnaNum = 0
    for (def valor in valores) {
        def celda = fila.createCell(columnaNum)
        celda.setCellValue(valor)
        columnaNum++ // Incrementa el índice de columna para la siguiente celda
    }
	
	// Inicializa la variable de Resultado Positivo
	def resultadoPositivo = "OK"
	
	// Itera sobre las columnas comparando celda a celda entre las dos Hojas correspondientes al dia previo y al dia posterior
	for (int colNum = 0; colNum < 12; colNum++) {
		// Obtiene los valores de las celdas a comparar de cada Hoja
		def valorCeldaHoja1 = sheet1.getRow(filaNum)?.getCell(colNum)?.toString() ?: ""
		def valorCeldaHoja2 = sheet2.getRow(filaNum)?.getCell(colNum)?.toString() ?: ""

		// Compara los valores entre las celdas
		if (!valorCeldaHoja1.equals(valorCeldaHoja2)) {
			// Obtengo el título de la columna en caso de que la comparacion haya fallado para poder identificar en la planilla que fue lo que fallo.
			resultadoPositivo = sheet2.getRow(0)?.getCell(colNum)?.toString() ?: "Columna ${colNum + 1}" 
			break
		}
	}

	//	Escribo el resultado en la columna "COMPARACION" que ese indice lo lleva "columnaNum" que indica donde termino de escribir los datos en "Hoja2"
	def celdaComparacion = fila.createCell(columnaNum)
	celdaComparacion.setCellValue(resultadoPositivo)
		
	//Guardar el libro de Excel
	fileIn.close()
	FileOutputStream fileOut = new FileOutputStream("Test Data/Compensacion Coelsa.xlsx")
	workbook.write(fileOut)
	fileOut.close()

	//Cerrar el libro de Excel
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
