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
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 5), findTestData('MainData/Users').getValue(2, 5))
WebUI.maximizeWindow()

//Ingresar "BCCL.EB.CONCEPTOS,ALTA" en el buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'BCCL.EB.CONCEPTOS,ALTA')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton de buscar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambiar ventana "BCCL EB CONCEPTOS"
WebUI.switchToWindowTitle('BCCL EB CONCEPTOS')

//Setear "Alta de Conceptos" 099CMI
WebUI.setText(findTestObject('Object Repository/17-Remesas/04-TELLER ID/txtReasignarCaja'), '099CMI')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Modificar Registro"
WebUI.click(findTestObject('Object Repository/15-MONEX/14-GRUPO.COTIZACION/btnModificarRegistro'))

//Setear "Descripcion"
WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/txtDescripcion'), 'PRUEBAS CRECER 1')

//Setear "Descripcion Corta"
WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/txtDescripcionCorta'), 'PRUEBA1')

//Combobox de Tipo concepto
WebUI.selectOptionByIndex(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/cbTipoConcepto'), 2)

//Seleccionar "NO" en Formulario 18505
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/rbFormulario'))

//Seleccionar "NO" en Requiere documento socio
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/rbRequiereDocumentoSocio'))

//Setear Divisa que aplica al concepto
WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/txtDivisaQueAplicaAlConcepto'), 'ARS')

//Seleccionar "boton Dropdown Aplica Filial Desde"
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/btnDropdownAplicaFilialDesde'))

//Seleccionar "Primer Filial Desde"
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/lblPrimerFilialDesde'))

//Esperar Campo de Aplica Filial Desde
//WebUI.waitForElementVisible(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/txtPrimerAplicaFilial'), 3)

//Setear filial 001 en el primer campo de "Aplica de la Filial"
//WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/txtPrimerAplicaFilial'), '001')

//Seleccionar "boton Dropdown Aplica Filial Hasta"
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/btnDropdownAplicaFilialHasta'))

//Seleccionar "Primer Filial Hasta"
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/lblPrimerFilialHasta'))

//Esperar Campo de Aplica Filial Hasta
//WebUI.waitForElementVisible(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/txtSegundoAplicaFilial'), 3)

//Setear filial 149 en el segundo campo de "Aplica de la Filial"
//WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/txtSegundoAplicaFilial'), '149')
	
//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnAceptarRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Validar "Txn Completa"
def element = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))
assert element.contains('Txn Completa')

//Setear "Alta de Conceptos"
WebUI.setText(findTestObject('Object Repository/17-Remesas/04-TELLER ID/txtReasignarCaja'), '099CMI')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Modificar Registro"
WebUI.click(findTestObject('Object Repository/15-MONEX/14-GRUPO.COTIZACION/btnModificarRegistro'))

//Setear "Descripcion"
WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/txtDescripcion'), 'PRUEBAS CRECER 2')

//Setear "Descripcion Corta"
WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/txtDescripcionCorta'), 'PRUEBA2')

//Combobox de Tipo concepto
WebUI.selectOptionByIndex(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/cbTipoConcepto'), 2)

//Seleccionar "NO" en Formulario 18505
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/rbFormulario'))

//Seleccionar "NO" en Requiere documento socio
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/rbRequiereDocumentoSocio'))

//Setear Divisa que aplica al concepto
WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/txtDivisaQueAplicaAlConcepto'), 'ARS')

//Seleccionar "boton Dropdown Aplica Filial Desde"
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/btnDropdownAplicaFilialDesde'))

//Seleccionar "Primer Filial Desde"
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/lblPrimerFilialDesde'))

//Esperar Campo de Aplica Filial Desde
//WebUI.waitForElementVisible(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/txtPrimerAplicaFilial'), 3)

//Setear filial 001 en el primer campo de "Aplica de la Filial"
//WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/txtPrimerAplicaFilial'), '001')

//Seleccionar "boton Dropdown Aplica Filial Hasta"
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/btnDropdownAplicaFilialHasta'))

//Seleccionar "Primer Filial Hasta"
WebUI.click(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/lblPrimerFilialHasta'))

//Esperar Campo de Aplica Filial Hasta
//WebUI.waitForElementVisible(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/txtSegundoAplicaFilial'), 3)

//Setear filial 149 en el segundo campo de "Aplica de la Filial"
//WebUI.setText(findTestObject('Object Repository/57-Pases Entre Cajas/04-BCCL EB CONCEPTOS/txtSegundoAplicaFilial'), '149')

//Screenshot
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Seleccionar "boton Aceptar Registro
WebUI.click(findTestObject('Object Repository/17-Remesas/03-TELLER/btnAceptarRegistro'))

//Verificar "Txn Completa"
WebUI.verifyElementVisible(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))

//Validar "Txn Completa"
def element2 = WebUI.getText(findTestObject('Object Repository/17-Remesas/03-TELLER/lblTxnCompleta'))
assert element2.contains('Txn Completa')

//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}