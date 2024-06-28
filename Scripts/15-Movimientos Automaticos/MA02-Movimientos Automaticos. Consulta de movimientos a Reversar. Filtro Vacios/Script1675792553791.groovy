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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,14), findTestData('MainData/Users').getValue(2,14))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Setear "ENQ BCCL.E.MOV.AUT.TRANS.REVM" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.MOV.AUT.TRANS.REVM')

//Seleccionar boton buscar
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana "BCCL.E.MOV.AUT.TRANS.REVM"
WebUI.switchToWindowIndex(1)

//Limpiar los registros de busqueda
WebUI.click(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.REVM/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()

//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

WebUI.click(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.REVM/lnkEjecutar'))
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

label = WebUI.verifyElementVisible(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.REVM/lblMovimientosrechazaninforme'))

//Capturar tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//Seleccionar boton Fix
WebUI.click(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.REVM/btnFix'))

//Verificar Informacion debito automatico
WebUI.verifyElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/Mov-Automaticos Data/lblInfodebitoautomatico'))

//Validar Informacion debito automatico
def element2 = WebUI.getText(findTestObject('Object Repository/16-Movimientos Automaticos/Mov-Automaticos Data/lblInfodebitoautomatico'))
assert element2.contains('Informacion del debito automatico')

//Verificar boton Aceptar Registro
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/02-TELLER,REPOSICION.POR.MENOS.PN099/btnAceptarRegistro'))

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}