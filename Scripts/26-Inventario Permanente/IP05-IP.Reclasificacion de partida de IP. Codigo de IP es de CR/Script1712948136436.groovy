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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(
        2, 3))

WebUI.maximizeWindow()

WebUI.click(findTestObject('02-Dashboard/lnkInventarioPermanente'))

WebUI.click(findTestObject('02-Dashboard/25-Inventario Permanente/spanAltaDePartidas'))

WebUI.click(findTestObject('02-Dashboard/25-Inventario Permanente/Alta de Partidas/lnkContraCuentaDelSocio'))

WebUI.switchToWindowTitle('BCCL.IP.PARTIDAS')

WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtCodigoIP'), '0099')

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtPersonaID'))

WebUI.waitForElementVisible(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtPersonaID'), 3)

WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtPersonaID'), '1002388512')

WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtCuentaSocio'), '00890170146')

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtFecha'))

WebUI.waitForElementVisible(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtFecha'), 3)

WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtFecha'), '20230829')

WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/txtMonto'), '2000')

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/btnValidarRegistro'))

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/btnAceptarRegistro'))

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/lnkAceptarAlertas'))

txn = WebUI.verifyElementVisible(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/lblTxnCompletada'))

assert txn == true

WebUI.closeWindowTitle('BCCL.IP.PARTIDAS')

WebUI.switchToWindowIndex(0)

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.IP.RECLA.PARTIDAS')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('BCCL.E.IP.RECLA.PARTIDAS')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

WebUI.switchToWindowIndex(0)

WebUI.maximizeWindow()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.IP.RECLA.PARTIDAS')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('BCCL.E.IP.RECLA.PARTIDAS')

WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.E.IP.RECLA.PARTIDAS/txtCodigoIP'), ' 0099')

WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.E.IP.RECLA.PARTIDAS/txtIDPersona'), '1002388512')

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.E.IP.RECLA.PARTIDAS/lnkEjecutar'))

//WebUI.delay(120)

WebUI.selectOptionByIndex(findTestObject('27-Inventario Permanente/BCCL.E.IP.RECLA.PARTIDAS/cbxOpciones'), 1)

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.E.IP.RECLA.PARTIDAS/btnVisto'))

WebUI.switchToWindowTitle('BCCL.IP.PARTIDAS')

WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.E.IP.RECLA.PARTIDAS/Validacion Registro (BCCL.IP.PARTIDAS)/txtCodigoIPReclasificacion'), 
    '0795')

WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.E.IP.RECLA.PARTIDAS/Validacion Registro (BCCL.IP.PARTIDAS)/txtFechaReclasificacion'), 
    '20230829')

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.E.IP.RECLA.PARTIDAS/Validacion Registro (BCCL.IP.PARTIDAS)/btnAceptarRegistro'))

txn = WebUI.verifyElementVisible(findTestObject('27-Inventario Permanente/BCCL.E.IP.RECLA.PARTIDAS/Validacion Registro (BCCL.IP.PARTIDAS)/lblTXNcompleta'))

assert txn == true

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

