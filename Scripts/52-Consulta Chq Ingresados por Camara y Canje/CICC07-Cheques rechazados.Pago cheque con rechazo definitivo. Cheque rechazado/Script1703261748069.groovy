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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,40), findTestData('MainData/Users').getValue(2,40))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Consulta Chq Ingresados por Camara y Canje"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkConsultaChqIngresadosporCamarayCanje'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Cheques Pagados"
WebUI.click(findTestObject('Object Repository/02-Dashboard/51-Consulta Chq Ingresados por Camara y Canje/lnkChequesPagados'))

//Cambiar ventana "BCCL.E.CHQ.PAGADOS"
WebUI.switchToWindowTitle('BCCL.E.CHQ.PAGADOS')

//Seteo de datos "Fecha Desde", "Fecha Hasta", "Sucursal"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Desde', '20210901')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Hasta', '20210922')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '089')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verificar "Pagado"
WebUI.verifyElementVisible(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.E.CHQ.PAGADOS/lblPagado'))

//Validar "Pagado"
pagado = WebUI.getText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.E.CHQ.PAGADOS/lblPagado'))
assert pagado == "Pagado"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}