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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresar al menu ?1

WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), '?1')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('Temenos T24')

WebUI.maximizeWindow()

WebUI.click(findTestObject('02-Dashboard/lnkSucursalPiloto'))

WebUI.waitForElementPresent(findTestObject('Object Repository/02-Dashboard/27-Suc.Piloto/spanLimites y Acumuladores'), 6)

WebUI.click(findTestObject('Object Repository/02-Dashboard/27-Suc.Piloto/spanLimites y Acumuladores'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/27-Suc.Piloto/Limites y Acumuladores/lnkConsultaAcumuladores'))

WebUI.switchToWindowTitle('BCCL.E.EB.LM.ACUM.GEN')

WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.EB.LM.ACUM.GEN/lnkNuevaSeleccion'))

WebUI.click(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.EB.LM.ACUM.GEN/btnCodAcumulador'))

WebUI.verifyElementVisible(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.EB.LM.ACUM.GEN/lblID'))

//Verificar "ID"
WebUI.verifyElementVisible(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.EB.LM.ACUM.GEN/lblID'))

//Validar "ID"
def element = WebUI.getText(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.EB.LM.ACUM.GEN/lblID'))
assert element.contains('Id')

//Verificar "Description"
WebUI.verifyElementVisible(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.EB.LM.ACUM.GEN/lblDescription'))

//Validar "Description"
def element2 = WebUI.getText(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.EB.LM.ACUM.GEN/lblDescription'))
assert element2.contains('Description')

//Verificar "Desc corta"
WebUI.verifyElementVisible(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.EB.LM.ACUM.GEN/lblDesccorta'))

//Validar "Desc corta"
def element3 = WebUI.getText(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.EB.LM.ACUM.GEN/lblDesccorta'))
assert element3.contains('Desc corta')

//Verificar "Apl"
WebUI.verifyElementVisible(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.EB.LM.ACUM.GEN/lblApl'))

//Validar "Apl"
def element4 = WebUI.getText(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.EB.LM.ACUM.GEN/lblApl'))
assert element4.contains('Apl')

//Verificar "Mon"
WebUI.verifyElementVisible(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.EB.LM.ACUM.GEN/lblMon'))

//Validar "Mon"
def element5 = WebUI.getText(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.E.EB.LM.ACUM.GEN/lblMon'))
assert element5.contains('Mon')

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