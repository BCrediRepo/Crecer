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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Cheque Electrónico"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkChequeElectronico'))

//Seleccionar "Monto en Echeq Pendientes de Pago X Persona"
WebUI.click(findTestObject('Object Repository/02-Dashboard/48-Echeq/lnkMontoenEcheqPendientesdePagoXPersona'))

//Cambiar ventana "BCCL.E.CQ.ACUM.ECHEQ"
WebUI.switchToWindowTitle('BCCL.E.CQ.ACUM.ECHEQ')

//Seteo de datos "Id Persona/Nro Doc"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Id Persona/Nro Doc', '1003398047')

//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

//Click en el boton de Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))
	
//Verificar "Monto pendiente de pago"
WebUI.verifyElementVisible(findTestObject('Object Repository/50-Echeq/BCCL.E.CQ.ACUM.ECHEQ/lblMontopendientedepago'))

//Capturar tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime
println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))
TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))
println TotalRegistros

//Validar ID Persona
idPersona = WebUI.getText(findTestObject('Object Repository/50-Echeq/BCCL.E.CQ.ACUM.ECHEQ/lblIdpersona'))
assert idPersona == "Id persona"

//Validar Monto pendiente de pago
montoPendientePago = WebUI.getText(findTestObject('Object Repository/50-Echeq/BCCL.E.CQ.ACUM.ECHEQ/lblMontopendientedepago'))
assert montoPendientePago == "Monto pendiente de pago"

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