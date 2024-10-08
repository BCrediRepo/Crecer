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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,68), findTestData('MainData/Users').getValue(2,68))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Setear "BCCL.MOV.AUT.INP.DATA L L" en el buscador
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),'BCCL.MOV.AUT.INP.DATA L L')

//Seleccionar boton buscar
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana "%BCCL.MOV.AUT.INP.DATA"
WebUI.switchToWindowIndex(2)

//Filtra por SBL.RETURN=6, SBL.STATUS=NOT.POSTED,SBL.COD.SUC: 063
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('SBL.RETURN','6')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('SBL.STATUS','NOT.POSTED')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('SBL.COD.SUC','030')

//ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/btnEjecutar'))

//Seleccionar primer registro
WebUI.click(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/BCCL.MOV.AUT.INP.DATA - Lista Default/btnVerRegistro'))

//Verificar valor de "Sbl Cod Suc"
WebUI.verifyElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/Mov-Automaticos Data/lblSblCodSuc'))

//Validar valor de "Sbl Cod Suc"
def element = WebUI.getText(findTestObject('Object Repository/16-Movimientos Automaticos/Mov-Automaticos Data/lblSblCodSuc'))
assert element.contains('030')

//Verificar valor de "Sbl Return CGU"
WebUI.verifyElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/Mov-Automaticos Data/lblSblReturnCGU'))

//Validar valor de "Sbl Return CGU"
def element2 = WebUI.getText(findTestObject('Object Repository/16-Movimientos Automaticos/Mov-Automaticos Data/lblSblReturnCGU'))
assert element2.contains('6')

//Verificar valor de "Sbl Status NOT POSTED CGU"
WebUI.verifyElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/Mov-Automaticos Data/lblSblStatusNOTPOSTED'))

//Validar valor de "Sbl Status NOT POSTED CGU"
def element3 = WebUI.getText(findTestObject('Object Repository/16-Movimientos Automaticos/Mov-Automaticos Data/lblSblStatusNOTPOSTED'))
assert element3.contains('NOT.POSTED')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}