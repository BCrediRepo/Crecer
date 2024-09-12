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
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()
//CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()


// Ingreso al menu ?28
CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'('?28', 1)

WebUI.maximizeWindow()

def menuDesplegable = ["Tasas FCI"]
def link = "Consulta Devengamiento para FCI"

//si el menú que busco está en Temenos T24, uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)

WebUI.switchToWindowTitle('BCCL.E.DEVENGAMIENTO.FCI')

WebUI.maximizeWindow()
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cuenta', '01000395279')


// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//boton ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Validamos
WebUI.verifyElementVisible(findTestObject('Object Repository/32-Pago de intereses/BCCL.E.DEVENGAMIENTO.FCI/lblCuentaDevengamiento'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")
WebUI.verifyElementVisible(findTestObject('Object Repository/32-Pago de intereses/BCCL.E.DEVENGAMIENTO.FCI/lblCuentaDevengamiento'))
assert WebUI.getText(findTestObject('Object Repository/32-Pago de intereses/BCCL.E.DEVENGAMIENTO.FCI/lblCuentaDevengamiento')).contains('01000395279')
WebUI.click(findTestObject('Object Repository/32-Pago de intereses/BCCL.E.DEVENGAMIENTO.FCI/btnLupaDevengamiento'))

WebUI.switchToWindowTitle('BCCL.E.DEV.FCI.DETALLE')

WebUI.verifyElementVisible(findTestObject('Object Repository/32-Pago de intereses/BCCL.E.DEV.FCI.DETALLE/lblIntDevengados'))
assert WebUI.getText(findTestObject('Object Repository/32-Pago de intereses/BCCL.E.DEV.FCI.DETALLE/lblIntDevengados')).contains('Int. devengados')
WebUI.verifyElementVisible(findTestObject('Object Repository/32-Pago de intereses/BCCL.E.DEV.FCI.DETALLE/lblIntDevengadoCantidad'))
assert WebUI.getText(findTestObject('Object Repository/32-Pago de intereses/BCCL.E.DEV.FCI.DETALLE/lblIntDevengadoCantidad')).contains('0,07')

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
