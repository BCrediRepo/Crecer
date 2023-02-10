//CHR03-Cheques rechazados.Consulta de Cheques Rechazados. Usuario de Casa Central. Ingresar filtro Sucursal y Rango de fechas de rechazo con datos válidos. Movimientos

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
CustomKeywords.'pkgModules.kywGeneric.Login'(GlobalVariable.vTest10_IP, GlobalVariable.vTest10Name, GlobalVariable.vF02653, 
    GlobalVariable.vPass)

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?302')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('02-Dashboard/lnkRechazoDeCheques'))

WebUI.click(findTestObject('02-Dashboard/6-Cheques rechazados/lnkConsultas'))

WebUI.click(findTestObject('02-Dashboard/6-Cheques rechazados/1-Consultas - Temenos T24/lnkChequesRechazados'))

WebUI.switchToWindowTitle('BCCL.CHRECH.RECHAZADOS')

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CHRECH.RECHAZADOS/lnkNuevaSeleccion'))

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CHRECH.RECHAZADOS/lnkEjecutar'))

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CHRECH.RECHAZADOS/btnNuevaBusqueda'))

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CHRECH.RECHAZADOS/btnAlmanaqueDesde'))

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CHRECH.RECHAZADOS/lnkFecha1'))

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CHRECH.RECHAZADOS/btnAlmanaqueHasta'))

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CHRECH.RECHAZADOS/lnkFecha25'))

WebUI.click(findTestObject('08-Cheques Rechazados/BCCL.CHRECH.RECHAZADOS/lnkEjecutar'))

WebUI.delay(10)

clickeable = WebUI.verifyElementClickable(findTestObject('08-Cheques Rechazados/BCCL.CHRECH.RECHAZADOS/lnkVerDetalle'))

if (clickeable == true) {
    WebUI.maximizeWindow()

    WebUI.takeScreenshot('Screenshot/ChequesRechazados/CHR03-Cheques rechazados.Consulta de Cheques Rechazados. Usuario de Casa Central. Ingresar filtro Sucursal y Rango de fechas de rechazo con datos válidos. Movimientos.png')
} else {
    WebUI.maximizeWindow()

    WebUI.takeScreenshot('Screenshot/Fails/ChequesRechazados/Error-CHR03-Cheques rechazados.Consulta de Cheques Rechazados. Usuario de Casa Central. Ingresar filtro Sucursal y Rango de fechas de rechazo con datos válidos. Movimientos.png')

    WebUI.closeBrowser()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

