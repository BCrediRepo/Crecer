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
import java.text.SimpleDateFormat
import java.util.Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se accede al menu Transferencias Internas - Consulta de Altas Transf. Internas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkTransferenciasInternas'))

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

WebUI.click(findTestObject('Object Repository/02-Dashboard/11-Transferencias Internas/lnkConsultadeAltasTransfInternas'))

//Se espera la carga de BCCL.E.CONS.TINT.ALTAS
WebUI.switchToWindowIndex(1)
WebUI.waitForElementVisible(findTestObject('Object Repository/12-Transferencias Internas/BCCL.E.CONS.TINT.ALTAS/pBCCL.E.CONS.TINT.ALTAS'), 5)
WebUI.verifyElementVisible(findTestObject('Object Repository/12-Transferencias Internas/BCCL.E.CONS.TINT.ALTAS/pBCCL.E.CONS.TINT.ALTAS'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Se valida la existencia de objetos en BCCL.E.CONS.TINT.ALTAS
WebUI.waitForElementVisible(findTestObject('Object Repository/12-Transferencias Internas/BCCL.E.CONS.TINT.ALTAS/tdReferencia'),3)
WebUI.verifyElementVisible(findTestObject('Object Repository/12-Transferencias Internas/BCCL.E.CONS.TINT.ALTAS/tdReferencia'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

WebUI.waitForElementVisible(findTestObject('Object Repository/12-Transferencias Internas/BCCL.E.CONS.TINT.ALTAS/tdIdOrdenante'),3)
WebUI.verifyElementVisible(findTestObject('Object Repository/12-Transferencias Internas/BCCL.E.CONS.TINT.ALTAS/tdIdOrdenante'))

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
