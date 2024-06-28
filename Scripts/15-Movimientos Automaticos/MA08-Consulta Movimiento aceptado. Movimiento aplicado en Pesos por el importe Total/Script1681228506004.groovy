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

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,14), findTestData('MainData/Users').getValue(2,14))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Setear "ENQ BCCL.E.MOV.AUT.ACPT" en el buscador
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),'ENQ BCCL.E.MOV.AUT.ACPT')

//Seleccionar boton buscar
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana "BCCL.E.MOV.AUT.ACPT"
WebUI.switchToWindowIndex(1)

//Limpiar Registros de Busqueda
WebUI.click(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/lnkNuevaSeleccion'))

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Account Id','00730011369')

//Maximizar Ventana
WebUI.maximizeWindow()

//Tomar ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Presiona botón ejecutar
WebUI.click(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/btnEjecutar'))

//Verificar que se mueste el titulo del informe de movimientos aceptados
WebUI.verifyElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/lblTituloMovimientosaceptadasinforme'))

//Verificar titulo Importe Pesos
WebUI.verifyElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/lblImportePesos'))

//Validar titulo Importe Pesos
def element = WebUI.getText(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/lblImportePesos'))
assert element.contains('Importe Pesos')

WebUI.scrollToElement(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/lblMontoAplicadoFinal'), 5)

//Verificar Monto Aplicado
WebUI.verifyElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/lblMontoAplicadoFinal'))

//Validar Monto Aplicado
def element2 = WebUI.getText(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/lblMontoAplicadoFinal'))
assert element2.contains('Monto Aplicado')

//Verificar primer valor Importe Pesos
WebUI.verifyElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/lblValorImportePesos'))

//Almacenar Valor Importe Pesos
def valorImportePesos = WebUI.getText(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.E.MOV.AUT.ACPT/lblValorImportePesos'))

if (valorImportePesos != null) {
			
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()

} else {

	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
	
	}

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}