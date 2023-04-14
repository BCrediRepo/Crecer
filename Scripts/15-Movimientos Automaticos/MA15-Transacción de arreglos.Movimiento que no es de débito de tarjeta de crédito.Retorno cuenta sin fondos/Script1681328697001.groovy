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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,18), findTestData('MainData/Users').getValue(2,18))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),'BCCL.MOV.AUT.INP.DATA L L')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña BCCL.MOV.AUT.INP.DATA
WebUI.switchToWindowTitle('%BCCL.MOV.AUT.INP.DATA')

//Nueva seleccion
WebUI.click(findTestObject('Object Repository/26-Dispositivos/BCCL.E.DEP.EFE.TAS/lnkNuevaSeleccion'))

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Ejecuta en la linea de comando BCCL.MOV.AUT.INP.DATA L L
WebUI.waitForElementVisible(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),6)
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),'BCCL.MOV.AUT.INP.DATA L L')

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Abre la pestaña BCCL.MOV.AUT.INP.DATA
WebUI.switchToWindowTitle('%BCCL.MOV.AUT.INP.DATA')

//Filtra por SBL.RETURN=2, SBL.STATUS=NOT.POSTED,SBL.COD-SUC: 063, SBL-TIP-COMPANY = 'N' (Empresa de Tarjeta de Crédito)
WebUI.waitForElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/txtTituloBCCL.MOV.AUT.INP.DATA'),6)
WebUI.waitForElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/inputSBL.RETURN'),6)
WebUI.setText(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/inputSBL.RETURN'), '2')
WebUI.setText(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/inputSBL.STATUS'), 'NOT.POSTED')
WebUI.setText(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/inputSBL.COD.SUC'), '063')
WebUI.setText(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/inputSBL.TIP.COMPANY'), 'N')

//Click en ejecutar
WebUI.click(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/btnEjecutar'))
WebUI.maximizeWindow()

//Toma un ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Verifica las columnas del registro Y EL titulo
WebUI.waitForElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/BCCL.MOV.AUT.INP.DATA - Lista Default/lblTituloBCCL.MOV.AUT.INP.DATA-ListaDefault'),6)
WebUI.waitForElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/BCCL.MOV.AUT.INP.DATA - Lista Default/lblld'),6)
WebUI.waitForElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/BCCL.MOV.AUT.INP.DATA - Lista Default/lblSblcodemp'),6)
WebUI.waitForElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/BCCL.MOV.AUT.INP.DATA - Lista Default/lblSblcodsuc'),6)
WebUI.waitForElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/BCCL.MOV.AUT.INP.DATA - Lista Default/lblSbldigit'),6)
WebUI.waitForElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/BCCL.MOV.AUT.INP.DATA - Lista Default/lblSblsecuence'),6)

//Ver primer registro
WebUI.waitForElementVisible(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/BCCL.MOV.AUT.INP.DATA - Lista Default/btnVerRegistro'),6)
WebUI.click(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/BCCL.MOV.AUT.INP.DATA - Lista Default/btnVerRegistro'))

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
