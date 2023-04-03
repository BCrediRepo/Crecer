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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 7), findTestData('MainData/Users').getValue(
        2, 7))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.IP.PARTIDAS.CC.ALTAS')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('BCCL.E.IP.PARTIDAS.CC.ALTAS')

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.E.IP.PARTIDAS.CC.ALTAS/lnkNuevaSeleccion'))

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.E.IP.PARTIDAS.CC.ALTAS/lnkEjecutar'))

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.E.IP.PARTIDAS.CC.ALTAS/btnLupita'))

WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.E.IP.PARTIDAS.CC.ALTAS/txtSucursal'), '001')

WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.E.IP.PARTIDAS.CC.ALTAS/txtCodigoIP'), '1008')

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.E.IP.PARTIDAS.CC.ALTAS/lnkEjecutar'))

WebUI.maximizeWindow()

WebUI.verifyElementVisible(findTestObject('27-Inventario Permanente/BCCL.E.IP.PARTIDAS.CC.ALTAS/lblSucursal'))

Sucursal = WebUI.getText(findTestObject('27-Inventario Permanente/BCCL.E.IP.PARTIDAS.CC.ALTAS/lblSucursal'))

if (Sucursal == '001') {
    CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
} else {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

WebUI.click(findTestObject('27-Inventario Permanente/BCCL.E.IP.PARTIDAS.CC.ALTAS/lnkVerDetalle'))

WebUI.switchToWindowTitle('BCCL.IP.PARTIDAS')

WebUI.maximizeWindow()

Formulario = WebUI.verifyElementVisible(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/formDetalle'))

if (Formulario == true) {
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
}else {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}
@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

