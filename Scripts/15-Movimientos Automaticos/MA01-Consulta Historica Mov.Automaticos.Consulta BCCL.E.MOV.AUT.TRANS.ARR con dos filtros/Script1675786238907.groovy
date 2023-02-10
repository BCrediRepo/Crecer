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

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(GlobalVariable.vTest10_IP, GlobalVariable.vTest10Name, GlobalVariable.vF00743, 
    GlobalVariable.vPass)

WebUI.click(findTestObject('02-Dashboard/lnkMovimientosAutomaticos'))

WebUI.click(findTestObject('02-Dashboard/14-Movimientos Automaticos/lnkConsultaMovimientosHistoricos'))

WebUI.switchToWindowTitle('BCCL.E.MOV.AUT.TRANS.HIST')

WebUI.click(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.HIST/lnkNueva Seleccion'))

WebUI.click(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.HIST/lnkEjecutar'))

WebUI.click(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.HIST/btnNuevaBusqueda'))

WebUI.setText(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.HIST/txtNroCta_value111'), '10430040953')

WebUI.click(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.HIST/lnkEjecutar'))

WebUI.click(findTestObject('16-Movimientos Automaticos/BCCL.E.MOV.AUT.TRANS.HIST/btnVista'))

WebUI.switchToWindowTitle('Mov-Automaticos Data')

trx = WebUI.verifyElementVisible(findTestObject('16-Movimientos Automaticos/Mov-Automaticos Data/lblOperacion'))

if (trx == true) {
    WebUI.maximizeWindow()

    WebUI.takeScreenshot('Screenshot/Movimientos Automaticos/MA01-Consulta Historica Mov.Automaticos.Consulta BCCL.E.MOV.AUT.TRANS.ARR con dos filtros.png')
}else {
	WebUI.maximizeWindow()
	
		WebUI.takeScreenshot('Screenshot/Fails/Movimientos Automaticos/Error - MA01-Consulta Historica Mov.Automaticos.Consulta BCCL.E.MOV.AUT.TRANS.ARR con dos filtros.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}


