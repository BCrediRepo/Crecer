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
//ESTE CASO REVERSA TXN de la tabla BCCL.E.EB.CONS.REVE, Para esto se deben generar transacciones que terminen en esa tabla. Por ejemplo NOTAS DE DEBITO SIN IMPUESTOS
//para la cuenta 11190118359


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
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.EB.CONS.REVE')
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))
//Switch a la ventana de busqueda de consulta
WebUI.switchToWindowTitle('BCCL.E.EB.CONS.REVE')
WebUI.waitForElementVisible(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/txtUsuario'), 6)
WebUI.setText(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/txtUsuario'), 'B.0073')
WebUI.click(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lnkEjecutar'))

WebUI.waitForElementVisible(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lblHora'), 10)
WebUI.waitForElementVisible(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lnkReversar'), 6)
WebUI.click(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lnkReversar'))

//Switch a la ventana de busqueda de reversa
WebUI.switchToWindowTitle('Movimiento de Fondos')
WebUI.waitForElementVisible(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/btnReversarRegistro'), 6)
WebUI.click(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/btnReversarRegistro'))
WebUI.waitForElementVisible(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lnkAceptarAlertas'), 6)
WebUI.click(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lnkAceptarAlertas'))
WebUI.waitForElementVisible(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lblTxnCompleta'), 6)
WebUI.verifyElementPresent(findTestObject('Object Repository/14-Recaudaciones/02-BCCL.E.EB.CONS.REVE/lblTxnCompleta'), 6)

WebUI.takeScreenshot('Screenshot/Recaudaciones/RECAUDACIONES.Reversa de pago de tarjeta CABAL' + nowString + '.png')


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

