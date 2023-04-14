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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
        2, 9))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('02-Dashboard/lnkChequesCancelatorios'))

WebUI.click(findTestObject('02-Dashboard/18.1-Cheques Cancelatorios/spanConsultas'))

WebUI.click(findTestObject('02-Dashboard/18.1-Cheques Cancelatorios/01-Consultas/lnkConsultaMovimientoCtaInterna'))

WebUI.switchToWindowTitle('Consulta Mov de Cuentas Internas')

WebUI.click(findTestObject('20.1-Cheque Cancelatorio/Consulta Mov de Cuentas Internas/lnkNuevaSeleccion'))

WebUI.click(findTestObject('20.1-Cheque Cancelatorio/Consulta Mov de Cuentas Internas/lnkEjecutar'))

WebUI.switchToWindowTitle('BCCL.E.CHQ.CANCELA')

WebUI.click(findTestObject('20.1-Cheque Cancelatorio/BCCL.E.CHQ.CANCELA/btnLupa'))

WebUI.switchToWindowTitle('Consulta Mov de Cuentas Internas')

WebUI.setText(findTestObject('20.1-Cheque Cancelatorio/Consulta Mov de Cuentas Internas/txtCategoriaValue111'), '17403')

WebUI.setText(findTestObject('20.1-Cheque Cancelatorio/Consulta Mov de Cuentas Internas/txtMonedaValue211'), 'ARS')

WebUI.click(findTestObject('20.1-Cheque Cancelatorio/Consulta Mov de Cuentas Internas/lnkEjecutar'))

WebUI.verifyElementClickable(findTestObject('20.1-Cheque Cancelatorio/Consulta Mov de Cuentas Internas/btnLargavista'))

WebUI.click(findTestObject('20.1-Cheque Cancelatorio/Consulta Mov de Cuentas Internas/btnLargavista'))

WebUI.switchToWindowTitle('Account Statement')

WebUI.click(findTestObject('20.1-Cheque Cancelatorio/Account Statement/btnLargavistas'))


WebUI.switchToWindowTitle('Movimiento de Fondos')

formulario = WebUI.verifyElementVisible(findTestObject('20.1-Cheque Cancelatorio/Movimiento de Fondos/formTransaccion'))

WebUI.maximizeWindow()

if (formulario == true) {
    WebUI.takeScreenshot('Screenshot/Cheques Cancelatorios/CHC03 - Consulta movimientos Cta. Interna. Usuario de sucursal. Ingresar Categoría y Moneda en pesos y Fecha.png')
} else {
    WebUI.takeScreenshot('Screenshot/Cheques Cancelatorios/Fail/Error - CHC03 - Consulta movimientos Cta. Interna. Usuario de sucursal. Ingresar Categoría y Moneda en pesos y Fecha.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed

void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

