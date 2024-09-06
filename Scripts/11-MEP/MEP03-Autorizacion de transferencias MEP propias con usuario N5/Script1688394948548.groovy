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

WebUI.callTestCase(findTestCase('11-MEP/MEP01-MEP. Transferencias MEP. Transacciones Online. Otros Conceptos. Usuario de filial NIV5'), 
    [:], FailureHandling.STOP_ON_FAILURE)

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 8), findTestData('MainData/Users').getValue(2, 8))

def menuDesplegable = ["Autorizaciones"]
def link = "Autorizaciones Pendientes"

//Si el menu que busco está en dashboard uso esta funcion
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionDashboard'(menuDesplegable, link)

//Cambiar de ventana
WebUI.switchToWindowIndex(1)

WebUI.selectOptionByIndex(findTestObject('13-MEP/BCCL.E.AUTHORIZATION/cbxAccion'), 1)

WebUI.click(findTestObject('13-MEP/BCCL.E.AUTHORIZATION/btnSeleccion'))

WebUI.switchToWindowTitle('BCCL.MEP.FT.TRANSFER')

WebUI.click(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/btnAutorizar'))

label = WebUI.getText(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/lblTxnCompleta'))

def stringCompleto = label

def partes = stringCompleto.split(' ')

def token = partes[2]

assert label.contains('Txn Completa:')

WebUI.closeWindowTitle('BCCL.MEP.FT.TRANSFER')

WebUI.switchToWindowIndex(0)

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.MEP.FT.TRANSFER')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('BCCL.MEP.FT.TRANSFER')

WebUI.setText(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/txtBuscarTransferMEP'), partes[2])

WebUI.click(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/btnBuscar'))

autorizado = WebUI.getText(findTestObject('13-MEP/BCCL.MEP.FT.TRANSFER/lblAutorizado'))

assert autorizado.contains('Autorizado Map')

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}