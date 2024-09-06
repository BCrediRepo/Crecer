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
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory

def validarElementoEnTabla(String tabla, String variable, int colVariable, String razon, int colRazon) {
	WebElement table = DriverFactory.getWebDriver().findElement(By.id(tabla))
	List<WebElement> rows = table.findElements(By.tagName("tr"))
	for (WebElement row : rows) {
		WebElement cell = row.findElements(By.tagName("td"))[colVariable]
		String cellText = cell.getText()
		if (cellText.equals(variable)) {
			List<WebElement> tdList = row.findElements(By.tagName("td"))
					String resultado = tdList[colRazon].getText()
					println(resultado)
			assert tdList[colRazon].getText().contains(razon) : "Expected " + razon + " but found ${tdList[colRazon].getText()}"
			GlobalVariable.vTxn = resultado
			return true
		}
	}
	return false
}

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,1), findTestData('MainData/Users').getValue(2,1))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Setear "BCCL.MOV.AUT.INP.DATA L L" en el buscador
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'),'BCCL.MOV.AUT.INP.DATA L L')

//Seleccionar boton buscar
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana "%BCCL.MOV.AUT.INP.DATA"
WebUI.switchToWindowIndex(2)

//Filtra por SBL.RETURN=0, SBL.STATUS=POSTED,SBL.COD.SUC: 001, SBL.SETTLEMENT= S
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Maximizar Ventana
WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('SBL.RETURN','0')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('SBL.STATUS','POSTED')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('SBL.COD.SUC','076')
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('SBL.SETTLEMENT','S')

//ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/btnEjecutar'))

//Seleccionar primer registro
WebUI.click(findTestObject('Object Repository/16-Movimientos Automaticos/BCCL.MOV.AUT.INP.DATA/BCCL.MOV.AUT.INP.DATA - Lista Default/btnVerRegistro'))

//Valido "Sbl Status"
def encontrado = false
while (!encontrado) {
	encontrado = validarElementoEnTabla('tab1','Sbl Cod Suc', 0, "076", 2)
}
String Estado = GlobalVariable.vTxn
assert Estado.contains('076')

//Valido "Sbl Status"
encontrado = false
while (!encontrado) {
	encontrado = validarElementoEnTabla('tab1','Sbl Return', 0, "0", 2)
}
String Estado1 = GlobalVariable.vTxn
assert Estado1.contains('0')

//Valido "Sbl Status"
encontrado = false
while (!encontrado) {
	encontrado = validarElementoEnTabla('tab1','Sbl Status', 0, "POSTED", 2)
}
String Estado2 = GlobalVariable.vTxn
assert Estado2.contains('POSTED')

//Valido "Sbl Settlement"
encontrado = false
while (!encontrado) {
	encontrado = validarElementoEnTabla('tab1','Sbl Settlement', 0, "S", 2)
}
String Estado3 = GlobalVariable.vTxn
assert Estado3.contains('S')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}