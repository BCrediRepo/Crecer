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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,3), findTestData('MainData/Users').getValue(2,3))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'BCCL.EB.LM.LIM.PAR,MAN')

WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//ingreso a BCCL.EB.LM.LIM.PAR,MAN
WebUI.switchToWindowIndex(1)

//Ingreso el limite
WebUI.setText(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/txtAltadePartidasIP-CuentaSocioTransactionId'),"LM.LIM.PRUEBA.TES12")

// modifico el registro
WebUI.click(findTestObject('Object Repository/06-Comisiones/BCCL.AS.ACCOUNT/btnModificarRegistro'))

WebUI.click(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.EB.LM.LIM.PAR/btnDropdownSectResp'))

//valido si el id del sector responsable es uno y lo cambio al otro
SectResp1 = WebUI.getText(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.EB.LM.LIM.PAR/lblSectRespo'))

if(SectResp1.equals('4914000050000')) {
	WebUI.setText(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.EB.LM.LIM.PAR/txtSectResp'), "4914000060000")
	WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/btnAceptarRegistro'))
}else if (SectResp1.equals('4914000060000')) {
	WebUI.setText(findTestObject('Object Repository/29-Limites y Acumuladores/BCCL.EB.LM.LIM.PAR/txtSectResp'), "4914000050000")
	WebUI.click(findTestObject('Object Repository/27-Inventario Permanente/BCCL.IP.PARTIDAS/btnAceptarRegistro'))
}

	
//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))
 
//Validar "Txn Completa"
def element = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))
assert element.contains('Txn Completa')
	
	
	
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





