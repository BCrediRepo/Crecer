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

//Se accede al menu
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.RES.CTA.MOV.FECHA')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))
//Switch a la ventana de busqueda de consulta
WebUI.switchToWindowTitle('Movimientos por Fecha de Cuentas')

//Aplico KYW de limpieza de busqueda
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

WebUI.switchToWindowTitle('T24 - Fil.073 Jujuy')
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.RES.CTA.MOV.FECHA')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Completo la busqueda
WebUI.switchToWindowTitle('Movimientos por Fecha de Cuentas')
WebUI.waitForElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/01-BCCL.E.RES.CTA.MOV.FECHA/txtNumCuenta'), 6)
WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/01-BCCL.E.RES.CTA.MOV.FECHA/txtNumCuenta'), '05330013359')
WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/01-BCCL.E.RES.CTA.MOV.FECHA/txtFechaDesde'), '20220701')
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/01-BCCL.E.RES.CTA.MOV.FECHA/btnEjecutar'))

WebUI.waitForElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/01-BCCL.E.RES.CTA.MOV.FECHA/btnVerDetalleCompleto'), 10)
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/01-BCCL.E.RES.CTA.MOV.FECHA/btnVerDetalleCompleto'))
WebUI.delay(4)
WebUI.switchToWindowTitle('Account Charge Request')
WebUI.maximizeWindow()
WebUI.waitForElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/01-BCCL.E.RES.CTA.MOV.FECHA/lblRequestTypeValue'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/01-BCCL.E.RES.CTA.MOV.FECHA/lblRequestTypeValue'))

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
