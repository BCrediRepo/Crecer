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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 9), findTestData('MainData/Users').getValue(
        2, 9))

WebUI.maximizeWindow()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.MM.VENCIDOS.PAGADOS ')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('PF Vencidos Inmovilizados Pagados')

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebUI.selectOptionByIndex(findTestObject('05-PlazoFijo/PF Vencidos Inmovilizados Pagados/cbxOpciones'), 1)

WebUI.click(findTestObject('05-PlazoFijo/PF Vencidos Inmovilizados Pagados/btnSeleccion'))

WebUI.switchToWindowTitle('PLAZO FIJO')

formPF = WebUI.verifyElementVisible(findTestObject('05-PlazoFijo/PLAZO FIJO/formPlazoFijo'))

assert formPF == true

WebUI.maximizeWindow()

WebUI.click(findTestObject('05-PlazoFijo/PLAZO FIJO/lnkVolverConsulta'))

WebUI.switchToWindowTitle('PF Vencidos Inmovilizados Pagados')

WebUI.selectOptionByIndex(findTestObject('05-PlazoFijo/PF Vencidos Inmovilizados Pagados/cbxOpciones'), 2)

WebUI.click(findTestObject('05-PlazoFijo/PF Vencidos Inmovilizados Pagados/btnSeleccion'))

WebUI.switchToWindowTitle('ARCHIVOS PERSONAS')

formTitular = WebUI.verifyElementVisible(findTestObject('05-PlazoFijo/ARCHIVOS PERSONAS/formTitular'))

assert formTitular == true

WebUI.maximizeWindow()

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}
