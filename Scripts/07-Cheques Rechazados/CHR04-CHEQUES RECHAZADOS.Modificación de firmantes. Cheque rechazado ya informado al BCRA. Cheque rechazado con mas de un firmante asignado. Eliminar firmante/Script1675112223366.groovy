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

////Config
//LocalDateTime now = LocalDateTime.now()
//DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE
//String nowString = formatter.format(now)

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,10), findTestData('MainData/Users').getValue(2,10))
WebUI.maximizeWindow()


WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CQ.CHRECH.MODFIRM.INFORMADO')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('CAMBIO DE FIRMANTES INFORMADOS')

WebUI.click(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/lnkNuevaSeleccion'))

WebUI.click(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/lnkEjecutar'))

WebUI.click(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/btnLupita'))

WebUI.setText(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/txtNCuenta_value111'), '02180086531')

WebUI.setText(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/txtNCheque_value211'), '10013')

WebUI.click(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/lnkEjecutar'))

WebUI.maximizeWindow()

WebUI.click(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/lnkModificarFirmantes'))

WebUI.click(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/btnAadirFirmante'))

WebUI.setText(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/txtFirmanteDNI'), '1000275776')

WebUI.click(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/btnAceptarRegistro'))

WebUI.takeScreenshot('Screenshot/ChequesRechazados/CHR04-CHEQUES RECHAZADOS.Modificación de firmantes. Cheque rechazado ya informado al BCRA. Cheque rechazado con mas de un firmante asignado. Eliminar firmante - 01.png')

WebUI.click(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/btnEliminarFirmante'))

WebUI.click(findTestObject('08-Cheques Rechazados/Cambio De Firmantes Informados/btnAceptarRegistro'))

WebUI.takeScreenshot('Screenshot/ChequesRechazados/CHR04-CHEQUES RECHAZADOS.Modificación de firmantes. Cheque rechazado ya informado al BCRA. Cheque rechazado con mas de un firmante asignado. Eliminar firmante - 02.png')

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'('Screenshot/Fails/ChequesRechazados/Error-CHR04-CHEQUES RECHAZADOS.Modificación de firmantes. Cheque rechazado ya informado al BCRA. Cheque rechazado con mas de un firmante asignado. Eliminar firmante - 02.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}


