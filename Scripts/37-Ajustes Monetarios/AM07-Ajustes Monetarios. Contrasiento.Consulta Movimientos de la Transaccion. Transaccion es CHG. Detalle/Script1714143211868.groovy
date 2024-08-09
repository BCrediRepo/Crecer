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
import java.text.SimpleDateFormat
import java.util.Date
import java.text.SimpleDateFormat
import java.util.Calendar


//HACER UNA LOGICA PARA LA FECHA. RECORRER VARIAS FECHAS ATRAS EN CASO DE NO ENCONTRAR RESULTADOS A LA PRIMERA.
//ALGUN CICLO WHILE O FOR QUE RECORRA 2 O 3 SEMANAS ATRAS, Y EN CASO DE NO ENCONTRAR DATOS AHI, DAR POR FAILED EL TEST CASE
//Configuracion de ambiente
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 3), findTestData('MainData/Users').getValue(2, 3))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Setear "AC.CHARGE.REQUEST L L" en el buscador
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'AC.CHARGE.REQUEST L L')

//Seleccionar boton buscar
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana "Contrasiento Total"
WebUI.switchToWindowIndex(2)

//Seteo de datos "CHARGE.DATE"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))

//Formato de la fecha en la cadena de texto
String dateFormat = "yyyyMMdd"

//Fecha inicial como cadena de texto
String initialDate = GlobalVariable.vFechaCOB

//Convertir la cadena de texto en un objeto Date
SimpleDateFormat sdf = new SimpleDateFormat(dateFormat)
Date date = sdf.parse(initialDate)

//Usar Calendar para manipular la fecha
Calendar calendar = Calendar.getInstance()
calendar.setTime(date)
calendar.add(Calendar.DAY_OF_MONTH, -1) // Restar 1 día

//Asegurar fecha resultante sea un día hábil
while (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
	calendar.add(Calendar.DAY_OF_MONTH, -1) // Restar 1 día si es fin de semana
}

//Convertir fecha modificada de vuelta a una cadena de texto
Date modifiedDate = calendar.getTime()
String resultDate = sdf.format(modifiedDate)

CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('CHARGE.DATE', resultDate)

//Maximizar Ventana
WebUI.maximizeWindow()

//ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Definir valor de la transaccion CHG
variableGHC = WebUI.getText(findTestObject('Object Repository/38-Ajustes Monetarios/AC.CHARGE.REQUEST/lblIdOperacionCHG'))

//Cambiar a la ventana del Dashboard
WebUI.switchToWindowIndex(0)

//Setear "ENQ BCCL.E.STMS.ENT.BOOK.CA" en el buscador
WebUI.setText(findTestObject('Object Repository/02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.STMS.ENT.BOOK.CA')

//Seleccionar boton buscar
WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDashboardGo'))

//Cambiar a la ventana "Contrasiento Total"
WebUI.switchToWindowIndex(3)

//Verificar titulo Contrasiento total
WebUI.verifyElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/ContrasientoTotal/lblTituloContrasientoTotal'))

//Seteo de datos "Id Transaccion"
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkNuevaSeleccion'))
CustomKeywords.'pkgModules.kywSetDato.SeteoDato'('Id Transaccion', variableGHC)

//Maximizar la Ventana
WebUI.maximizeWindow()

//ScreenShot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Capturar tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Comprobar primera columna del registro
WebUI.verifyElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/ContrasientoTotal/lblIdTransaccion'))

//Capturar tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime

println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//Seleccionar boton Ver Detalle Txn
WebUI.click(findTestObject('Object Repository/38-Ajustes Monetarios/ContrasientoTotal/btnVerDetalleTxn'))

//Verificar y Validar primera columna del registro
WebUI.verifyElementVisible(findTestObject('Object Repository/38-Ajustes Monetarios/Account Charge Request/lblRequestType'))
def element = WebUI.getText(findTestObject('Object Repository/38-Ajustes Monetarios/Account Charge Request/lblRequestType'))
assert element.contains('Request Type')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}