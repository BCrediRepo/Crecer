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

LocalDateTime now = LocalDateTime.now()
DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE
String nowString = formatter.format(now)


//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(GlobalVariable.vTest10_IP, GlobalVariable.vTest10Name, GlobalVariable.vF00289, GlobalVariable.vPass)

// Accedo al menu ?403

WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscar'), "?403")

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkChequesCertificados'))

WebUI.click(findTestObject('Object Repository/20-Cheque Certificados/lnkFormulasDeCertificacion'))

WebUI.click(findTestObject('Object Repository/20-Cheque Certificados/lnkReporteInvFormDeCertificacion'))

WebUI.switchToWindowTitle('Control Stock Certificacion Cheques')

WebUI.setText(findTestObject('Object Repository/20-Cheque Certificados/01-Reporte Inv Form de Certificacion/txtSUCURSAL'), '089')

WebUI.setText(findTestObject('Object Repository/20-Cheque Certificados/01-Reporte Inv Form de Certificacion/txtNUMERO DE CUENTA'), '00890172593')

WebUI.setText(findTestObject('Object Repository/20-Cheque Certificados/01-Reporte Inv Form de Certificacion/txtNUMERO DE CHEQUE'), '22182765')

WebUI.click(findTestObject('Object Repository/20-Cheque Certificados/01-Reporte Inv Form de Certificacion/btnEjecutar'))

WebUI.takeScreenshot("Screenshot/Cheque Cancelatorios. Consulta de Stock de Formulas. Usuario de Sucursal. Filtrar por Estado y Día de Emisión." + nowString + ".png")


//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'('Screenshot/Fails/CDC01Error.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()

}


