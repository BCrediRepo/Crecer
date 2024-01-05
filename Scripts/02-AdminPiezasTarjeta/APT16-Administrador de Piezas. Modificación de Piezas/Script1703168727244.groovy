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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1,4), findTestData('MainData/Users').getValue(2,4))
WebUI.maximizeWindow()
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Administracion de Piezas con Tarjetas"
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkAdministracionPiezasTarjetas'))

//Seleccionar "Consultas al Maestro de Card-Carrier"
WebUI.click(findTestObject('Object Repository/02-Dashboard/01-AdminPiezasConTarjetas/lnkConsultasalMaestrodeCard-Carrier (1)'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Seleccion por Nombre / Documento / Sucursal"
WebUI.click(findTestObject('Object Repository/02-Dashboard/01-AdminPiezasConTarjetas/04-ConsultaMaestroCardCarrier/lnkSeleccionporNombreDocumentoSucursal'))

//Cambiar ventana "BCCL.E.AP.ENQ.NOMBRE.DOC"
WebUI.switchToWindowTitle('BCCL.E.AP.ENQ.NOMBRE.DOC')

//Filtro limpieza
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()
WebUI.switchToWindowIndex(0)

//Seleccionar "Seleccion por Nombre / Documento / Sucursal"
WebUI.click(findTestObject('Object Repository/02-Dashboard/01-AdminPiezasConTarjetas/04-ConsultaMaestroCardCarrier/lnkSeleccionporNombreDocumentoSucursal'))

//Cambiar ventana "BCCL.E.AP.ENQ.NOMBRE.DOC"
WebUI.switchToWindowTitle('BCCL.E.AP.ENQ.NOMBRE.DOC')

//Maximizar pantalla
WebUI.maximizeWindow()

//Setear "Sucursal"
WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/08-BCCL.E.AP.ENQ.NOMBRE.DOC/txtSucursal'), '043')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Ejecutar"
WebUI.click(findTestObject('Object Repository/00-Utils/02-Filtros/lnkEjecutar'))

//Definir Primera pieza
TestObject primeraPieza = findTestObject('Object Repository/03-AdminPiezasTarjetas/08-BCCL.E.AP.ENQ.NOMBRE.DOC/lblPrimeraPieza')

//Almacenar ValorPrimeraPieza
String valorPrimeraPieza = WebUI.getText(primeraPieza)

//Definir Segunda pieza
TestObject segundaPieza = findTestObject('Object Repository/03-AdminPiezasTarjetas/08-BCCL.E.AP.ENQ.NOMBRE.DOC/lblSegundaPieza')

//Capturar elemento y almacenar en una variable
String valorSegundaPieza = WebUI.getText(segundaPieza)

//Definir Tercera pieza
TestObject terceraPieza = findTestObject('Object Repository/03-AdminPiezasTarjetas/08-BCCL.E.AP.ENQ.NOMBRE.DOC/lblTerceraPieza')

//Capturar elemento y almacenar en una variable
String valorTerceraPieza = WebUI.getText(terceraPieza)

//Definir Primer estado
TestObject primerEstado = findTestObject('Object Repository/03-AdminPiezasTarjetas/08-BCCL.E.AP.ENQ.NOMBRE.DOC/lblPrimerEstado')

//Almacenar ValorPrimerEstado
String valorPrimerEstado = WebUI.getText(primerEstado)

//Definir Segundo estado
TestObject segundoEstado = findTestObject('Object Repository/03-AdminPiezasTarjetas/08-BCCL.E.AP.ENQ.NOMBRE.DOC/lblSegundoEstado')

//Capturar elemento y almacenar en una variable
String valorSegundoEstado = WebUI.getText(segundoEstado)

//Definir Tercer estado
TestObject tercerEstado = findTestObject('Object Repository/03-AdminPiezasTarjetas/08-BCCL.E.AP.ENQ.NOMBRE.DOC/lblTercerEstado')

//Capturar elemento y almacenar en una variable
String valorTercerEstado = WebUI.getText(tercerEstado)

//Definir Primera Marca
TestObject primeraMarca = findTestObject('Object Repository/03-AdminPiezasTarjetas/08-BCCL.E.AP.ENQ.NOMBRE.DOC/lblPrimeraMarca')

//Almacenar valor Primera Marca
String valorPrimeraMarca = WebUI.getText(primeraMarca)

//Definir Segunda Marca
TestObject segundaMarca = findTestObject('Object Repository/03-AdminPiezasTarjetas/08-BCCL.E.AP.ENQ.NOMBRE.DOC/lblSegundaMarca')

//Almacenar valor Segunda Marca
String valorSegundaMarca = WebUI.getText(segundaMarca)

//Definir Tercera Marca
TestObject terceraMarca = findTestObject('Object Repository/03-AdminPiezasTarjetas/08-BCCL.E.AP.ENQ.NOMBRE.DOC/lblTerceraMarca')

//Almacenar valor Tercera Marca
String valorTerceraMarca = WebUI.getText(terceraMarca)

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Cambiar ventana "T24 - Fil.043 Villa Mitre"
WebUI.switchToWindowTitle('T24 - Fil.043 Villa Mitre')

//Seleccionar "Modificaciones sobre Card-Carrier"
WebUI.click(findTestObject('Object Repository/02-Dashboard/01-AdminPiezasConTarjetas/lnkModificacionesSobreCard-Carrier'))

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "Modificacion datos de Card-Carrier"
WebUI.click(findTestObject('Object Repository/02-Dashboard/01-AdminPiezasConTarjetas/05-Modificaciones sobre Card-Carrier/lnkModificacionDatosdeCard-Carrier'))

//Cambiar ventana "BCCL.AP.PIEZAS"
WebUI.switchToWindowTitle('BCCL.AP.PIEZAS')

//Maximizar pantalla
WebUI.maximizeWindow()

if (valorPrimerEstado.equals('010') && valorPrimeraMarca.equals('CABAL')) {
	WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/txtNro.de Pieza'), valorPrimeraPieza)
	
	//Screenshot
	CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
	
	//Seleccionar "Boton Modificar Registro"
	WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/btnModificarRegistro'))
	
	//Setear Marca VISA
	WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/txtMARCA'), 'VISA')
	
	//Seleccionar "Aceptar el registro"
	WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/btnAceptarRegistro'))
	
	//Verificar "Txn Completa"
	WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
	
	//Validar "Txn Completa"
	def element = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
	assert element.contains('Txn Completa')
	
	
} else {
	if (valorSegundoEstado.equals('010') && valorSegundaMarca.equals('CABAL')) {
		WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/txtNro.de Pieza'), valorSegundaPieza)
		
		//Screenshot
		CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
		
		//Seleccionar "Boton Modificar Registro"
		WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/btnModificarRegistro'))
		
		//Setear Marca VISA
		WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/txtMARCA'), 'VISA')
		
		//Seleccionar "Aceptar el registro"
		WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/btnAceptarRegistro'))
		
		//Verificar "Txn Completa"
		WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
		
		//Validar "Txn Completa"
		def element = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
		assert element.contains('Txn Completa')
		
		
	} else {
		if (valorTercerEstado.equals('010') && valorTerceraMarca.equals('CABAL')) {
			WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/txtNro.de Pieza'), valorTerceraPieza)
			
			//Screenshot
			CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
			
			//Seleccionar "Boton Modificar Registro"
			WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/btnModificarRegistro'))
			
			//Setear Marca VISA
			WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/txtMARCA'), 'VISA')
			
			//Seleccionar "Aceptar el registro"
			WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/btnAceptarRegistro'))
			
			//Verificar "Txn Completa"
			WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
			
			//Validar "Txn Completa"
			def element = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
			assert element.contains('Txn Completa')
			
			
		} else {
			WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/txtNro.de Pieza'), valorPrimeraPieza)
			
			//Screenshot
			CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
			
			//Seleccionar "Boton Modificar Registro"
			WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/btnModificarRegistro'))
			
			//Setear Marca CABAL
			WebUI.setText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/txtMARCA'), 'CABAL')
			
			//Seleccionar "Aceptar el registro"
			WebUI.click(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/btnAceptarRegistro'))
			
			//Verificar "Txn Completa"
			WebUI.verifyElementVisible(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
			
			//Validar "Txn Completa"
			def element = WebUI.getText(findTestObject('Object Repository/03-AdminPiezasTarjetas/07-BCCL.AP.PIEZAS/lblTxnCompleta'))
			assert element.contains('Txn Completa')
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