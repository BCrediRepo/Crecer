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

//TEMPORALMENTE POR AQUI
//GlobalVariable.vIDCoelsa = 'DLMORZP90Y6D81YNEGJ468D'

//Ingreso a la tabla BCCL.AS.DDIF.PASES L L
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'BCCL.AS.DDIF.PASES L L')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la ventana BCCL.AS.DDIF.PASES
WebUI.switchToWindowTitle('%BCCL.AS.DDIF.PASES')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.closeWindowIndex(1)
WebUI.switchToWindowIndex(0)

//Ingreso a la tabla BCCL.AS.DDIF.PASES L L
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'BCCL.AS.DDIF.PASES L L')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la ventana BCCL.AS.DDIF.PASES
WebUI.switchToWindowTitle('%BCCL.AS.DDIF.PASES')

//Ingresa Filtro SECUENCIA
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/txtSECUENCIA'), 6)
WebUI.setText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/txtSECUENCIA'), GlobalVariable.vIDCoelsa)
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Abre la ventana BCCL.AS.DDIF.PASES
WebUI.switchToWindowTitle('BCCL.AS.DDIF.PASES - Lista Default')

//Valido variables de la tabla
WebUI.waitForElementVisible(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/txtBCCL.AS.DDIF.PASES'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/txtBCCL.AS.DDIF.PASES'))
def element = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/txtBCCL.AS.DDIF.PASES'))
assert element.contains('BCCL.AS.DDIF.PASES')

//ACA SE CAPTURA LA TABLE DDIF.PASES
//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Guardo el FT obtenido
GlobalVariable.vFTdelIDCoelsa = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/txtFT'))

//Ingreso al detalle de la transaccion FT para capturar evidencias
WebUI.click(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/imgLupaDDIFPASES'))

//Me muevo a la venta TXN DOC DIF
WebUI.closeWindowIndex(1)
WebUI.switchToWindowTitle('Txn Doc Dif')

//Maximizamos
WebUI.maximizeWindow()

//ACA SE CAPTURA LA TABLE DDIF.PASES
//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Me muevo al dashboard para consultar la FT
WebUI.switchToWindowIndex(0)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'FT S ' + GlobalVariable.vFTdelIDCoelsa)
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Me muevo a la ventana de la FT
WebUI.switchToWindowTitle('Movimiento de Fondos')

//Maximizamos
WebUI.maximizeWindow()


//SCREEN DE LA VENTANA COMPLETA

//Guardo los valores que deseo
GlobalVariable.vDenomBen = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblL.DENOM.BEN.BANCA'))
GlobalVariable.vCreditAcc = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblCREDIT.ACCT.NO'))
GlobalVariable.vDebitAcc = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblDEBIT.ACCT.NO'))
GlobalVariable.vDebitAmount = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblDEBIT.AMOUNT'))
GlobalVariable.vCUIBen = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblL.CUI.BEN.1.BANCA'))
GlobalVariable.vCuitCuil = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblL.CUIT.CUIL'))
GlobalVariable.vDescEspC = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblL.DESC.ESP.CR'))
GlobalVariable.vDescEspD = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblL.DESC.ESP.DB'))
GlobalVariable.vCodOprC = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblL.GEN.COD.OPR.C'))
GlobalVariable.vCodOprD = WebUI.getText(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/lblL.GEN.COD.OPR.D'))

//Maximizamos
WebUI.maximizeWindow()
//WebUI.switchToFrame(findTestObject('Object Repository/TEST-8910 - Compensacion Coelsa/frmFullFtCoelsa2'), 1)

//Toma un ScreenShot a pagina completa
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
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
	XSSFSheet sheet = workbook.getSheet("Hoja1")

	//Arreglo con valores que van a ser escritos en el archivo Excel segun orden 
	def valores = [GlobalVariable.vIDCoelsa, GlobalVariable.vFTdelIDCoelsa, GlobalVariable.vDenomBen, GlobalVariable.vCreditAcc, GlobalVariable.vDebitAcc, 
		GlobalVariable.vDebitAmount, GlobalVariable.vCUIBen, GlobalVariable.vCuitCuil, GlobalVariable.vDescEspC, GlobalVariable.vDescEspD, GlobalVariable.vCodOprC, GlobalVariable.vCodOprD]
	
	//Encuentro la primera fila vacía en la hoja
	int filaNum = 0
	while (sheet.getRow(filaNum) != null) {
		filaNum++
	}

	//Número de fila en la que deseas escribir los datos
    def fila = sheet.createRow(filaNum)

    //Indice de columna para comenzar a escribir datos
    int columnaNum = 0
    for (def valor in valores) {
        def celda = fila.createCell(columnaNum)
        celda.setCellValue(valor)
        columnaNum++ // Incrementa el índice de columna para la siguiente celda
    }

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
