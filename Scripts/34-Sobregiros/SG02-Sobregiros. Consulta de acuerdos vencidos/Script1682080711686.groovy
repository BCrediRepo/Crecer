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

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/01-LOACC/spanLOACC'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/01-LOACC/span_Consulta'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/01-LOACC/a_Consulta de Acuerdos Vencidos'))

//Cambiar a la ventana "BCCL.E.ACDOS.VENCIDOS"
WebUI.switchToWindowIndex(1)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Cuenta', '00890010860')

//Maximizar Ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Desde', '20210719')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Hasta', '20220725')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar "boton Ejecutar"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Esperar elemento Cuenta
WebUI.waitForElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lblCuenta'), 6)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verificar "Cuenta"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lblCuenta'))

//Validar "Cuenta"
def element = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lblCuenta'))
assert element.contains('Cuenta')

//Verificar "Descripcion"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lblDescripcion'))

//Validar "Descripcion"
def element2 = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lblDescripcion'))
assert element2.contains('Descripcion')

//Verificar "Numero Acuerdo"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lblNumeroAcuerdo'))

//Validar "Numero Acuerdo"
def element3 = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lblNumeroAcuerdo'))
assert element3.contains('Numero Acuerdo')

//Verificar "Monto"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lblMonto'))

//Validar "Monto"
def element4 = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lblMonto'))
assert element4.contains('Monto')

//Verificar "Fecha inicio"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lblFechainicio'))

//Validar "Fecha inicio"
def element5 = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lblFechainicio'))
assert element5.contains('Fecha inicio')

//Verificar "Fecha vence"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lblFechavence'))

//Validar "Fecha vence"
def element6 = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lblFechavence'))
assert element6.contains('Fecha vence')

//Verificar "Codigo de garantia"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lblCodigodegarantia'))

//Validar "Codigo de garantia"
def element7 = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lblCodigodegarantia'))
assert element7.contains('Codigo de garantia')

//Verificar "Cod. Acdo RAC"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lblCod.AcdoRAC'))

//Validar "Cod. Acdo RAC"
def element8 = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACDOS.VENCIDOS/lblCod.AcdoRAC'))
assert element8.contains('Cod. Acdo RAC')

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