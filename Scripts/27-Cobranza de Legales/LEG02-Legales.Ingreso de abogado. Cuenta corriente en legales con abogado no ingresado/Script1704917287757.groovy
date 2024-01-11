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


//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Accedo al menu de Legales BCCL.COBRANZAS.LEGALES,INPUT F3
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.AC.CTAS.LEG')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana de Legales BCCL.E.AC.CTAS.LEG y se completan los datos de sucursal 073
WebUI.switchToWindowTitle('BCCL.E.AC.CTAS.LEG')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Accedo al menu de Legales BCCL.COBRANZAS.LEGALES,INPUT F3
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.AC.CTAS.LEG')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Switch a la ventana de Legales BCCL.E.AC.CTAS.LEG y se completan los datos de sucursal 073
WebUI.switchToWindowTitle('BCCL.E.AC.CTAS.LEG')

WebUI.waitForElementVisible(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/txtSucursal'), 6)
WebUI.setText(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/txtSucursal'), findTestData('MainData/Users').getValue(3,5))
WebUI.click(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/lnkEjecutar'))

//Cargo datos del abogado
WebUI.maximizeWindow()
WebUI.waitForElementVisible(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/lnkCargaDatosAbogado'), 6)
WebUI.click(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/lnkCargaDatosAbogado'))

//Switch a la ventana de cambio de datos del Abogado
WebUI.switchToWindowTitle('Settlement Account Balance')
WebUI.maximizeWindow()
WebUI.waitForElementVisible(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/txtNombreAbogado'), 6)
def abogadoNombre = WebUI.getText(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/txtNombreAbogado'))
WebUI.click(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/txtNombreAbogado'))

if (abogadoNombre.contains('1234'))
	WebUI.setText(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/txtNombreAbogado'), 'CRECERTEST')
	else
		WebUI.setText(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/txtNombreAbogado'), 'TEST1234')

WebUI.waitForElementVisible(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/btnAceptarRegistro'), 6)
WebUI.click(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/btnAceptarRegistro'))

def regSinCambios = WebUI.getText(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/lblRegSinCambios'))
if (regSinCambios.contains('El registro informado no presenta cambios')) {
	WebUI.waitForElementVisible(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/lnkBCCL.E.AC.CTAS.LEG'), 6)
	WebUI.click(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/lnkBCCL.E.AC.CTAS.LEG'))
	WebUI.waitForElementVisible(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/lnkCargaDatosAbogado'), 6)
	WebUI.click(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/lnkCargaDatosAbogado'))
	WebUI.waitForElementVisible(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/txtNombreAbogado'), 6)
	WebUI.setText(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/txtNombreAbogado'), 'CRECERTEST')
	WebUI.waitForElementVisible(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/btnAceptarRegistro'), 6)
	WebUI.click(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/btnAceptarRegistro'))
}

//Verifico la txn Completa
WebUI.waitForElementVisible(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/lblTxnCompleta'), 6)
def element = WebUI.getText(findTestObject('Object Repository/28-Cobranza de Legales/02-BCCL.E.AC.CTAS.LEG/lblTxnCompleta'))
assert element.contains('Txn Completa:')


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
