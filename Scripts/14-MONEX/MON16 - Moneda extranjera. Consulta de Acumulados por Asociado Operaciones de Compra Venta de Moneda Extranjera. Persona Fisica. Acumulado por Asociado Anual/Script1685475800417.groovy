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

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
        2, 9))

//Se maximisa la ventana
WebUI.maximizeWindow()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.PER.GEN.PF.FIN')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowIndex(1)

//Seteo de Datos "Id Persona"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Id Persona', '1000873562')

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

WebUI.click(findTestObject('15-MONEX/Consulta General de personas Fisica/btnLargavista'))

WebUI.switchToWindowTitle('BCCL.E.ACUM.OPER.MONEX')

//Verificación de encabezado
WebUI.verifyElementVisible(findTestObject('15-MONEX/BCCL.E.ACUM.OPER.MONEX/lblIDAsociado'))

ID = WebUI.getText(findTestObject('15-MONEX/BCCL.E.ACUM.OPER.MONEX/lblIDAsociado'))

assert ID == ' ID Asociado:'

WebUI.verifyElementVisible(findTestObject('15-MONEX/BCCL.E.ACUM.OPER.MONEX/lblNombreyApellido'))

NyA = WebUI.getText(findTestObject('15-MONEX/BCCL.E.ACUM.OPER.MONEX/lblNombreyApellido'))

assert NyA == ' Nombre y Apellido:'

WebUI.verifyElementVisible(findTestObject('15-MONEX/BCCL.E.ACUM.OPER.MONEX/lblTipoDoc'))

TipoDoc = WebUI.getText(findTestObject('15-MONEX/BCCL.E.ACUM.OPER.MONEX/lblTipoDoc'))

assert TipoDoc == ' Tipo Doc:'

WebUI.verifyElementVisible(findTestObject('15-MONEX/BCCL.E.ACUM.OPER.MONEX/lblNro.Doc'))

NumDoc = WebUI.getText(findTestObject('15-MONEX/BCCL.E.ACUM.OPER.MONEX/lblNro.Doc'))

assert NumDoc == ' Nro. Doc:'

//verificación acumulado anual
WebUI.verifyElementVisible(findTestObject('15-MONEX/BCCL.E.ACUM.OPER.MONEX/lblAnualCompra'))

WebUI.verifyElementVisible(findTestObject('15-MONEX/BCCL.E.ACUM.OPER.MONEX/lblImporteUSDCompraAnual'))

//Importe = WebUI.getText(findTestObject('15-MONEX/BCCL.E.ACUM.OPER.MONEX/lblImporteUSDCompraAnual'))
//
//assert Importe != '0,00'

WebUI.maximizeWindow()

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

