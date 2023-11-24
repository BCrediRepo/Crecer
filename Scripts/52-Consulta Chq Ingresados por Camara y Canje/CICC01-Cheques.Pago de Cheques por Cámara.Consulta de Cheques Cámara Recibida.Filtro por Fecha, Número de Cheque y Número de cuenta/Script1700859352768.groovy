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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,40), findTestData('MainData/Users').getValue(2,40))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Consulta Chq Ingresados por Camara y Canje"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkConsultaChqIngresadosporCamarayCanje'))

//Seleccionar "Cheques Recibidos"
WebUI.click(findTestObject('Object Repository/02-Dashboard/51-Consulta Chq Ingresados por Camara y Canje/lnkChequesRecibidos'))

//Cambiar ventana "BCCL.E.CHQ.CAM.REC"
WebUI.switchToWindowTitle('BCCL.E.CHQ.CAM.REC')

//filtro limpieza
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Seleccionar "Cheques Recibidos"
WebUI.click(findTestObject('Object Repository/02-Dashboard/51-Consulta Chq Ingresados por Camara y Canje/lnkChequesRecibidos'))

//Cambiar ventana "BCCL.E.CHQ.CAM.REC"
WebUI.switchToWindowTitle('BCCL.E.CHQ.CAM.REC')

//Cambiar a "entre" del combobox de "Fecha"
WebUI.selectOptionByIndex(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.E.CHQ.CAM.REC/cbFecha'), 1)

//Setear "Fecha"
WebUI.setText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.E.CHQ.CAM.REC/txtFecha'), '20120901') 

//Setear "Sucursal"
WebUI.setText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.E.CHQ.CAM.REC/txtSucursal'), '173') 

//Setear "ID de la Cuenta"
WebUI.setText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.E.CHQ.CAM.REC/txtIDdelaCuenta'), '01730054895') 

//Capturar el tiempo de inicio
long startTime = System.currentTimeMillis()

//Seleccionar boton de Ejecutar
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Verificar "Fecha de Pago"
WebUI.verifyElementVisible(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.E.CHQ.CAM.REC/lblFechadePago'))

//Capturar tiempo de finalización
long endTime = System.currentTimeMillis()

//Calcular diferencia para obtener el tiempo transcurrido
long elapsedTime = endTime - startTime
println("Tiempo transcurrido: " + elapsedTime + " milisegundos")

//Conteo registros
WebUI.verifyElementVisible(findTestObject('00-Utils/02-Filtros/lblResultados'))
TotalRegistros = WebUI.getText(findTestObject('00-Utils/02-Filtros/lblResultados'))
println TotalRegistros

//Validar "Fecha de Pago"
fechaPago = WebUI.getText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.E.CHQ.CAM.REC/lblFechadePago'))
assert fechaPago == "Fecha de Pago"

//Validar "Numero de Cheque"
numeroCheque = WebUI.getText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.E.CHQ.CAM.REC/lblNumerodeCheque'))
assert numeroCheque == "Numero de Cheque"

//Validar "ID de la Cuenta"
idCuenta = WebUI.getText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.E.CHQ.CAM.REC/lblIDdelaCuenta'))
assert idCuenta == "ID de la Cuenta"

//Validar estado de pagado
pagado = WebUI.getText(findTestObject('Object Repository/53-Consulta Chq Ingresados por Camara y Canje/BCCL.E.CHQ.CAM.REC/lblPagado'))
assert pagado == "Pagado"

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}