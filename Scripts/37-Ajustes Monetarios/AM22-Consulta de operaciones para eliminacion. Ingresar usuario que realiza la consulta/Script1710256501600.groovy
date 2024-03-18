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
//TEST NAME: Impuestos.Ajustes monetarios. Alta de Nota de Débito Ajuste. Cuenta en pesos. Persistencia

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Accedo al menu Autorizaciones
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/lnkAutorizaciones'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkAutorizaciones'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkConsElimindeTxPendientes'))

//Switch a la ventana de Nota de Debito por Ajustes
WebUI.switchToWindowTitle('BCCL.E.EB.CONS.DEL.NAU')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Accedo al menu Autorizaciones
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkConsElimindeTxPendientes'))

//Switch a la ventana de Nota de Debito por Ajustes
WebUI.switchToWindowTitle('BCCL.E.EB.CONS.DEL.NAU')

WebUI.setText(findTestObject('Object Repository/38-Ajustes Monetarios/BCCL.E.EB.CONS.DEL.NAU/txtUsuario'),'B.0289')
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Espera y recibe mensaje de tx completa reversada
WebUI.waitForElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/BCCL.E.EB.CONS.DEL.NAU/lblHora'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/BCCL.E.EB.CONS.DEL.NAU/lblHora'))
def element = WebUI.getText(findTestObject('Object Repository/38-Ajustes Monetarios/BCCL.E.EB.CONS.DEL.NAU/lblHora'))
assert element.contains('Hora')

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

