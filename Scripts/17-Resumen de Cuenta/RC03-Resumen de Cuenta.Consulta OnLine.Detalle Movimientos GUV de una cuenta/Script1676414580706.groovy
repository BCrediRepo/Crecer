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

//Config
LocalDateTime now = LocalDateTime.now()
DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE
String nowString = formatter.format(now)

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,5), findTestData('MainData/Users').getValue(2,5))
WebUI.maximizeWindow()

//Se accede al menu
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.B.RES.GUV')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))
//Switch a la ventana de busqueda de consulta
WebUI.switchToWindowTitle('BCCL.E.B.RES.GUV')
WebUI.waitForElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/03-BCCL.E.B.RES.GUV/txtCuenta'), 6)
WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/03-BCCL.E.B.RES.GUV/txtCuenta'), '05330013359')
WebUI.setText(findTestObject('Object Repository/18-Resumen de Cuenta/03-BCCL.E.B.RES.GUV/txtFechaValor'), '20220701')
WebUI.click(findTestObject('Object Repository/18-Resumen de Cuenta/03-BCCL.E.B.RES.GUV/lnkEjecutar'))
//Verifico
WebUI.waitForElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/03-BCCL.E.B.RES.GUV/lblCuentaValor'), 6)
WebUI.verifyElementVisible(findTestObject('Object Repository/18-Resumen de Cuenta/03-BCCL.E.B.RES.GUV/lblCuentaValor'))
WebUI.takeScreenshot('Screenshot/Resumen de Cuenta/Resumen de Cuenta.Consulta OnLine.Detalle Movimientos GUV de una cuenta' + nowString + '.png')


//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'('Screenshot/Fails/APT01Error.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}