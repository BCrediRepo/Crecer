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
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

//def usuario
// Obtener el número de filas en la tabla de datos
TestData usersData = findTestData('MainData/Users')

int rowCount = usersData.getRowNumbers()
int consecutivos = 0
int user = 0

// Iterar sobre cada fila en la tabla de datos y llamar a la funcion de habilitar por cada user
for (int row = 1; row <= rowCount; row++) {
	
    String usuario = findTestData('MainData/Users').getValue(1, row)
	user++

	if (usuario == null || usuario == "CRECEREM" || usuario == "CRECERAB" || usuario == "CRECERAC" || usuario == "CRECERAD") {
		
		usuario = null
		consecutivos++
		if (consecutivos == 2) {
			println "--------------------------------------"
			println "APERTURA DE CAJAS FINALIZADA CON EXITO"
			println "--------------------------------------"
			break
		}
		continue
	}else {
		consecutivos = 0
		println usuario + "intenta logear"
		try {
			//Configuracion de ambiente
			CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
	
			//Login con un user generico
			CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, user), findTestData('MainData/Users').getValue(
					2, user))
			println usuario + " " + "logeado con exito"
			
			try {
	
				WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), '?327')
	
				WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))
	
				WebUI.switchToWindowTitle('Temenos T24')
	
				def menuDesplegable = ["Caja"]
				def link = "Apertura de Caja"

				//Si el menu que busco está en dashboard uso esta funcion
				CustomKeywords.'pkgModules.kywBusquedaMenu.navegacionMenu'(menuDesplegable, link)
	
				WebUI.switchToWindowTitle('TELLER ID')
		
				WebUI.waitForElementVisible(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'), 6)			
			
				WebUI.click(findTestObject('Object Repository/00-Utils/06-ToolBar/btnAceptarRegistro'))	
	
				WebUI.verifyElementVisible(findTestObject('Object Repository/00-Utils/07-Mensajes/lblTxnCompleta'))
				
				println "apertura exitosa para la caja del usuario" + " " + usuario
	
				WebUI.closeWindowTitle('TELLER ID')
	
				WebUI.closeWindowTitle('Temenos T24')
	
				WebUI.switchToWindowIndex(0)
	
				WebUI.click(findTestObject('02-Dashboard/btnLogout'))
			
				continue
				}
			catch (Exception e) {
				WebUI.closeWindowTitle('TELLER ID')
			
				WebUI.closeWindowTitle('Temenos T24')
			
				WebUI.switchToWindowIndex(0)
			
				WebUI.click(findTestObject('02-Dashboard/btnLogout'))
					
				continue
			}
		}catch(Exception err) {
				WebUI.verifyElementVisible(findTestObject('Object Repository/01-Login/lblErrMsgLogin'))
				msg = WebUI.getText(findTestObject('Object Repository/01-Login/lblErrMsgLogin'))
				if (msg.contains("TOO MANY ATEMPTS") || (msg.contains("Please check your Login Credential and/or access rights"))) {
					println "-------------------------------------------------------------------"
					println "el usario" + " " + usuario + " " + "no pudo logear correctamente en el ambiente"
					println GlobalVariable.vServerNameRun				
					println "-------------------------------------------------------------------"
					continue
				} else {
					println "-------------------------------------------------------------------"
					println "el usario" + " " + usuario + " " + "no existe en el ambiente"
					println GlobalVariable.vServerNameRun
					println "-------------------------------------------------------------------"
					continue
				}
			}			
		}
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