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
def vWindowTitle = 'ENQ '+ findTestData('Modulos/Modulos').getValue(4,2)
LocalDateTime now = LocalDateTime.now()
DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE
String nowString = formatter.format(now)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(GlobalVariable.vTest10_IP, GlobalVariable.vTest10Name, GlobalVariable.vF00289, GlobalVariable.vPass)

//Se accede al menu Plazo Fijo
WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkPlazoFijo'))

WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/3-PlazoFijo/lnkConsultasdePlazoFijo'), 6)
WebUI.click(findTestObject('Object Repository/02-Dashboard/3-PlazoFijo/lnkConsultasdePlazoFijo'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/3-PlazoFijo/02-Consultas de Plazo Fijo/lnkConsultasporPlazoFijo'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/3-PlazoFijo/02-Consultas de Plazo Fijo/02-Consulta por Plazo Fijo/lnkPlazosFijosActivos'))
WebUI.switchToWindowTitle(findTestData('Modulos/Modulos').getValue(4,12)) 

WebUI.verifyElementVisible(findTestObject('Object Repository/05-PlazoFijo/07-Plazos Fijos Activos/lblPlazoFijosActivos'))
WebUI.setText(findTestObject('Object Repository/05-PlazoFijo/07-Plazos Fijos Activos/txtSucursal'), "089")
WebUI.click(findTestObject('Object Repository/05-PlazoFijo/07-Plazos Fijos Activos/lnkEjecutar'))

WebUI.waitForElementVisible(findTestObject('Object Repository/05-PlazoFijo/07-Plazos Fijos Activos/lnkVerPlazoFijo'),6)
WebUI.click(findTestObject('Object Repository/05-PlazoFijo/07-Plazos Fijos Activos/lnkVerPlazoFijo'))

WebUI.waitForElementVisible(findTestObject('Object Repository/05-PlazoFijo/07-Plazos Fijos Activos/lblIdPersona'),6)
WebUI.waitForElementVisible(findTestObject('Object Repository/05-PlazoFijo/07-Plazos Fijos Activos/lnkPlazoFijosActivos'),6)
WebUI.click(findTestObject('Object Repository/05-PlazoFijo/07-Plazos Fijos Activos/lnkPlazoFijosActivos'))
WebUI.switchToWindowTitle(findTestData('Modulos/Modulos').getValue(4,12))

WebUI.waitForElementVisible(findTestObject('Object Repository/05-PlazoFijo/07-Plazos Fijos Activos/lnkVerTitular'),6)
WebUI.click(findTestObject('Object Repository/05-PlazoFijo/07-Plazos Fijos Activos/lnkVerTitular'))

WebUI.waitForElementVisible(findTestObject('Object Repository/05-PlazoFijo/07-Plazos Fijos Activos/lblIdPersonaFisica'),6)
WebUI.verifyElementVisible(findTestObject('Object Repository/05-PlazoFijo/07-Plazos Fijos Activos/lblIdPersonaFisica'))
WebUI.takeScreenshot("Screenshot/Plazo Fijo/Plazo Fijo. Consultas por Plazo Fijo. Consulta de Activos" + nowString + ".png")

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'('Screenshot/Fails/CDC01Error.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}