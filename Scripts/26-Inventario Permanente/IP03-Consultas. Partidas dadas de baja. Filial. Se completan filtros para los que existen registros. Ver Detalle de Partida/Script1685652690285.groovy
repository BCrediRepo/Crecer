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
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date

//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 15), findTestData('MainData/Users').getValue(
        2, 15))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.IP.PARTIDAS.FIL.BAJAS')

WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

WebUI.switchToWindowTitle('BCCL.E.IP.PARTIDAS.FIL.BAJAS')

WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('ID Persona', '1003227493')

//WebUI.setText(findTestObject('27-Inventario Permanente/BCCL.E.IP.PARTIDAS.FIL.BAJAS/txtIDPersona_value611'), '1003227493')
// Captura el tiempo de inicio
long startTime = System.currentTimeMillis()

//boton ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//WebUI.delay(120)
WebUI.maximizeWindow()

estado = WebUI.getText(findTestObject('27-Inventario Permanente/BCCL.E.IP.PARTIDAS.FIL.BAJAS/lblEstadoCuenta'))

// Captura el tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcula la diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println(('Tiempo transcurrido: ' + elapsedTime) + ' milisegundos')

if (estado == 'INACTIVA') {
    CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

    WebUI.click(findTestObject('27-Inventario Permanente/BCCL.E.IP.PARTIDAS.FIL.BAJAS/lnkVerDetalles'))
} else {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

WebUI.switchToWindowTitle('BCCL.IP.PARTIDAS')

WebUI.verifyElementVisible(findTestObject('27-Inventario Permanente/BCCL.IP.PARTIDAS/formDetalleBaja'))

WebUI.maximizeWindow()

CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'( //Control de fin de script
    )

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

