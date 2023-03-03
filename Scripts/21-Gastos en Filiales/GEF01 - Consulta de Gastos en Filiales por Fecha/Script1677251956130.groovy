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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(
        1, 4))

WebUI.maximizeWindow()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?1')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.click(findTestObject('02-Dashboard/lnkSucursalPiloto'))

WebUI.click(findTestObject('02-Dashboard/5-SucursalPiloto/lnkGL-EntregasGlobalLogic'))

WebUI.click(findTestObject('02-Dashboard/5-SucursalPiloto/Global Logic/lnkGastosenFiliales'))

WebUI.click(findTestObject('02-Dashboard/5-SucursalPiloto/Global Logic/lnkConsultadeGastosenFiliales'))

WebUI.switchToWindowTitle('BCCL.E.GASTOS.FILIALES')

WebUI.click(findTestObject('22 - Gastos en Filiales/BCCL.E.GASTOS.FILIALES/lnkNuevaSeleccion'))

WebUI.setText(findTestObject('22 - Gastos en Filiales/BCCL.E.GASTOS.FILIALES/txtFecha_value111'), '20220713')

WebUI.click(findTestObject('22 - Gastos en Filiales/BCCL.E.GASTOS.FILIALES/lnkEjecutar'))

WebUI.waitForElementVisible(findTestObject('22 - Gastos en Filiales/BCCL.E.GASTOS.FILIALES/lblFecha'), 20)

WebUI.maximizeWindow()

FechaCabecera = WebUI.verifyElementVisible(findTestObject('22 - Gastos en Filiales/BCCL.E.GASTOS.FILIALES/lblFecha'))

FechaNumerica = WebUI.verifyElementVisible(findTestObject('22 - Gastos en Filiales/BCCL.E.GASTOS.FILIALES/lbl13072022'), 
    FailureHandling.STOP_ON_FAILURE)

if ((FechaCabecera == true) && (FechaNumerica == true)) {
    WebUI.takeScreenshot('Screenshot/Gastos en Filiales/GEF01 - Consulta de Gastos en Filiales por Fecha.png')
} else {
    WebUI.takeScreenshot('Screenshot/Fails/Gastos en Filiales/Error - GEF01 - Consulta de Gastos en Filiales por Fecha.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

