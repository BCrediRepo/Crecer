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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Ingreso al menu ?1

WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?1')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.maximizeWindow()

WebUI.click(findTestObject('02-Dashboard/lnkSucursalPiloto'))

WebUI.waitForElementPresent(findTestObject('Object Repository/02-Dashboard/27-Suc.Piloto/spanLimites y Acumuladores'), 6)

WebUI.click(findTestObject('Object Repository/02-Dashboard/27-Suc.Piloto/spanLimites y Acumuladores'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/27-Suc.Piloto/Limites y Acumuladores/lnkParametrizacionAcumuladores'))

WebUI.switchToWindowTitle('BCCL.EB.LM.ACU.PAR')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.EB.LM.ACU.PAR/txtTransactionId'), 'AC.ACRED.ATP.IFE')

WebUI.click(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.EB.LM.ACU.PAR/btnVerRegistro'))

WebUI.verifyElementVisible(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.EB.LM.ACU.PAR/lblDescCorta'))

WebUI.maximizeWindow()

//Verificar "Frecuencia"
WebUI.verifyElementVisible(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.EB.LM.ACU.PAR/lblFrecuencia'))

//Validar "Frecuencia"
def element = WebUI.getText(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.EB.LM.ACU.PAR/lblFrecuencia'))
assert element.contains('Frecuencia.1')

//Verificar "Frecuencia Anual"
WebUI.verifyElementVisible(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.EB.LM.ACU.PAR/lblFrecuenciaAnual'))

//Validar "Frecuencia Anual"
def element2 = WebUI.getText(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.EB.LM.ACU.PAR/lblFrecuenciaAnual'))
assert element2.contains('ANUAL')

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
