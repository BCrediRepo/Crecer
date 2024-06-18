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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login antes el 4
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/01-LOACC/spanLOACC'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/01-LOACC/span_Consulta'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/01-LOACC/lnkConsulta de LOACC'))

//Cambiar a la ventana "Consulta de Sobregiros"
WebUI.switchToWindowIndex(1)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cuenta', '00890012958')

//Maximizar Ventana
WebUI.maximizeWindow()

//CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Linea de sobregiro', 'ADSACT')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar "boton Ejecutar"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Seleccionar opcion Consultar en el ComboBox
WebUI.selectOptionByIndex(findTestObject('Object Repository/35-Sobregiros/Consulta de sobregiros/cbxConsultar'), 2)

//Seleccionar boton Select Drilldown
WebUI.click(findTestObject('Object Repository/35-Sobregiros/Consulta de sobregiros/btnDrillDrown'))

//Verificar "Monto Acuerdo"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.AC.ACUERDO/lblMontoAcuerdo'))

//Validar "Monto Acuerdo"
def element = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.AC.ACUERDO/lblMontoAcuerdo'))
assert element.contains('Monto Acuerdo')

//Verificar "Fecha Inicio"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.AC.ACUERDO/lblFechaInicio'))

//Validar "Fecha Inicio"
def element2 = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.AC.ACUERDO/lblFechaInicio'))
assert element2.contains('Fecha Inicio')

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

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