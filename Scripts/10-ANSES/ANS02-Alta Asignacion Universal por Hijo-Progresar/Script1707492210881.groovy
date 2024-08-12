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
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date

CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(
        2, 1))

def menuDesplegable = ["ANSES"]
def link = "Alta Asignacion Universal por Hijo/Progresar"

CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)
WebUI.switchToWindowTitle('BCCL.ANSES.BEN')

//Seteo de datos
WebUI.setText(findTestObject('10-ANSES/BCCL.ANSES.BEN/txtCuilBeneficiario'), '27175137209')

WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnModificarRegistro'))
WebUI.click(findTestObject('10-ANSES/BCCL.ANSES.BEN/rbtnSI'))
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))

//verificacion de txn completa
WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
txn = WebUI.getText(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
WebUI.closeWindowIndex(3)
WebUI.closeWindowIndex(2)
assert txn.contains('Txn Completa:') == true
WebUI.closeWindowIndex(1)
WebUI.switchToWindowIndex(0)

//apertura de app para verificación final
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.ANSES.BEN')
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))
WebUI.switchToWindowTitle('BCCL.ANSES.BEN')

//Busqueda y verificacion de cambio realizado en AUH
WebUI.setText(findTestObject('10-ANSES/BCCL.ANSES.BEN/txtCuilBeneficiario'), '27175137209')
WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnVerRegistro'))
WebUI.verifyElementVisible(findTestObject('10-ANSES/BCCL.ANSES.BEN/lblAUH'))
AUH = WebUI.getText(findTestObject('10-ANSES/BCCL.ANSES.BEN/lblAUH'))
assert AUH.contains('SI') == true

 //Control fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

