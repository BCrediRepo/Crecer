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

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(GlobalVariable.vTest10_IP, GlobalVariable.vTest10Name, GlobalVariable.vF00474, 
    GlobalVariable.vPass)

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CQ.CHRECH.REP.BCRA')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('BCCL.E.CQ.CHRECH.REP.BCRA')

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lnkNuevaSeleccion'))

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lnkEjecutar'))

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/btnBusqueda'))

WebUI.setText(findTestObject('08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/txtFechaRepvalue511'), '20220725')

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.E.CQ.CHRECH.REP.BCRA/lnkEjecutar'))

WebUI.maximizeWindow()

Fecha = WebUI.verifyTextPresent('Fech Rep', true)

BCRA = WebUI.verifyTextPresent('Env BCRA', true)

Cuenta = WebUI.verifyTextPresent('Cuenta', true)

assert Fecha == true

assert BCRA == true

assert Cuenta == true

WebUI.takeScreenshot('Screenshot/ChequesRechazados/CHR01-Consulta Cheques a Reportar a BCRA.Ver Detalle.png')

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'('Screenshot/Fails/ChequesRechazados/Error-CHR01-Consulta Cheques a Reportar a BCRA.Ver Detalle.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}
