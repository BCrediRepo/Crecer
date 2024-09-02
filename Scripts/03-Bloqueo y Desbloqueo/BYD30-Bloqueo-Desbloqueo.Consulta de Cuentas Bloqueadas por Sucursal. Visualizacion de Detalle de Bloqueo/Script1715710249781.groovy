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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 2), findTestData('MainData/Users').getValue(
        2, 2))

def menuDesplegable0 = ["Cuentas", "Modificacion de cuenta", "Bloqueo y Desbloqueo", "Consultas"]
def link0 = "Bloqueos Activos"
def fecha = GlobalVariable.vFechaCOB

CustomKeywords.'pkgModules.kywBusquedaMenu.seteoCommandLine'('?302', 1)
CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable0, link0)

WebUI.switchToWindowTitle('BCCL.AC.CTABLOQ.SUC')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Sucursal', '089')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Tipo Bloqueo', '02')


WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

WebUI.verifyElementVisible(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CTABLOQ.SUC/lnkTipodeBloqueo'))

TipoBloq = WebUI.getText(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CTABLOQ.SUC/lblTipoBloqueo'))
println TipoBloq

assert TipoBloq.contains('BLOQUEO PARCIAL')

WebUI.selectOptionByIndex(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CTABLOQ.SUC/cbVerdetalleBloqueo'), 2)

WebUI.click(findTestObject('04-Bloqueo y Desbloqueo/BCCL.AC.CTABLOQ.SUC/btnSelectDrilldown'))

WebUI.switchToWindowTitle('Account Blocking Details')

WebUI.verifyElementVisible(findTestObject('04-Bloqueo y Desbloqueo/Account Blocking Details/lblTipoBloqueo'))

descripcion = WebUI.getText(findTestObject('04-Bloqueo y Desbloqueo/Account Blocking Details/lblTipoBloqueo'))

assert descripcion.contains('BLOQUEO PARCIAL') //Control fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

