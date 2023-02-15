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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,11), findTestData('MainData/Users').getValue(2,11))
WebUI.maximizeWindow()

WebUI.click(findTestObject('02-Dashboard/lnkMovimientosAutomaticos'))

WebUI.click(findTestObject('02-Dashboard/14-Movimientos Automaticos/lnkImputarArreglos'))

WebUI.switchToWindowTitle('BCCL.E.MOV.AUT.TRANS.ARR')

WebUI.click(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.ARR/lnkNuevaSeleccion'))

WebUI.click(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.ARR/lnkEjecutar'))

WebUI.click(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.ARR/btnLupita'))

WebUI.setText(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.ARR/txtNroCuenta_value411'), '10430047640')

Cabecera = WebUI.getText(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.ARR/lblMovimientosrechazaninforme'))

if (Cabecera == true) {
    WebUI.maximizeWindow()

    WebUI.takeScreenshot('Screenshot/Movimientos Automaticos/MA03-Movimientos automaticos.Transaccion de arreglos.Ingreso por número de cuenta.png')
} else {
    WebUI.maximizeWindow()

    WebUI.takeScreenshot('Screenshot/Fails/Movimientos Automaticos/Error-MA03-Movimientos automaticos.Transaccion de arreglos.Ingreso por número de cuenta.png')
}

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
