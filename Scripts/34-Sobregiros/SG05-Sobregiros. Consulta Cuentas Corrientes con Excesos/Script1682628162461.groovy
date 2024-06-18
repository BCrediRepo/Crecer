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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Click en el menu LOACC del dashboard
WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/01-LOACC/spanLOACC'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/01-LOACC/span_Consulta'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/33-Sobregiros/01-LOACC/lnkConsulta de Acuerdos a Vencer'))

//Cambiar a la ventana "BCCL.E.ACU.PROX.A.VEC"
WebUI.switchToWindowIndex(1)

//Seteo de Datos
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Fecha Hasta', '20230925')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//boton ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verificar "Numero de Acuerdo"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACU.PROX.A.VEC/lblNumero de Acuerdo'))

//Validar "Numero de Acuerdo"
def element = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACU.PROX.A.VEC/lblNumero de Acuerdo'))
assert element.contains('Numero de Acuerdo')

//Verificar "Cuenta"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACU.PROX.A.VEC/lblCuenta'))

//Validar "Cuenta"
def element2 = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACU.PROX.A.VEC/lblCuenta'))
assert element2.contains('Cuenta')

//Verificar "Denominacion"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACU.PROX.A.VEC/lblDenominacion'))

//Validar "Denominacion"
def element3 = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACU.PROX.A.VEC/lblDenominacion'))
assert element3.contains('Denominacion')

//Verificar "Estado"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACU.PROX.A.VEC/lblEstado'))

//Validar "Estado"
def element4 = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACU.PROX.A.VEC/lblEstado'))
assert element4.contains('Estado')

//Verificar "Fec. Inicio"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACU.PROX.A.VEC/lblFec.Inicio'))

//Validar "Fec. Inicio"
def element5 = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACU.PROX.A.VEC/lblFec.Inicio'))
assert element5.contains('Fec. Inicio')

//Verificar "Fec. Vencimiento"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACU.PROX.A.VEC/lblFec.Vencimiento'))

//Validar "Fec. Vencimiento"
def element6 = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACU.PROX.A.VEC/lblFec.Vencimiento'))
assert element6.contains('Fec. Vencimiento')

//Verificar "Cod Linea Acuerdo"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACU.PROX.A.VEC/lblCodLineaAcuerdo'))

//Validar "Cod Linea Acuerdo"
def element7 = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACU.PROX.A.VEC/lblCodLineaAcuerdo'))
assert element7.contains('Cod Linea Acuerdo')

//Verificar "Tipo Acuerdo"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACU.PROX.A.VEC/lblTipoAcuerdo'))

//Validar "Tipo Acuerdo"
def element8 = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACU.PROX.A.VEC/lblTipoAcuerdo'))
assert element8.contains('Tipo Acuerdo')

//Verificar "Monto Del Acuerdo"
WebUI.verifyElementVisible(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACU.PROX.A.VEC/lblMontoDelAcuerdo'))

//Validar "Monto Del Acuerdo"
def element9 = WebUI.getText(findTestObject('Object Repository/35-Sobregiros/BCCL.E.ACU.PROX.A.VEC/lblMontoDelAcuerdo'))
assert element9.contains('Monto Del Acuerdo')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Captura el tiempo de finalización
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