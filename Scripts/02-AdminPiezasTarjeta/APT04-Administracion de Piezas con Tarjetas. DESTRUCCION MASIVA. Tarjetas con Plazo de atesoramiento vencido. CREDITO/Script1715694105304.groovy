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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 63), findTestData('MainData/Users').getValue(2, 63))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//El caso realiza correctamente su flujo, DEPENDE DE QUE EXISTAN DATOS EN LA SUCURSAL CORRESPONDIENTES AL CREDITO

//Se accede al menu Administracion de piezas
//WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lnkAdministracionPiezasTarjetas'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkAdministracionPiezasTarjetas'))

//WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/01-AdminPiezasConTarjetas/lnkProcesodeDestrucciondeTarjetas'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/01-AdminPiezasConTarjetas/lnkProcesodeDestrucciondeTarjetas'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/01-AdminPiezasConTarjetas/06-Proceso de Destruccion de Tarjetas/lnkTarjetasConPlazodeAtesoramientoVencido'))

//Switch a la ventana de Destruccion Masiva
WebUI.switchToWindowTitle('BCCL.AP.E.AP.DESTRUCCION.MASIVA')

//WebUI.waitForElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/04-Tarjetas con Plazo de Atesoramiento Vencido/txtProducto'), 6)
WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/04-Tarjetas con Plazo de Atesoramiento Vencido/txtProducto'), 
    'CREDITO')

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/04-Tarjetas con Plazo de Atesoramiento Vencido/lnkEjecutar'))
//WebUI.waitForElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/04-Tarjetas con Plazo de Atesoramiento Vencido/ChckBxTarjeta'), 
//    6)

WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/04-Tarjetas con Plazo de Atesoramiento Vencido/ChckBxTarjeta'))

WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/04-Tarjetas con Plazo de Atesoramiento Vencido/imgAceptarRegistroDestruccion'))

WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/04-Tarjetas con Plazo de Atesoramiento Vencido/imgAceptarRegistroDestruccion'))

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Switch a la ventana de Confirmacion
WebUI.switchToWindowTitle('Temenos T24')

//WebUI.waitForElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/04-Tarjetas con Plazo de Atesoramiento Vencido/lblEnquiry'), 
//    6)

WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/04-Tarjetas con Plazo de Atesoramiento Vencido/lblEnquiry')) //---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

