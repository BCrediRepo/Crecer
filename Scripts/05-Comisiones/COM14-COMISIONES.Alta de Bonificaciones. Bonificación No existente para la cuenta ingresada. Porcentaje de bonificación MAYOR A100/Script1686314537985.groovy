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

////Desbloqueo de usuario CRECEREM
//CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)
//
////Login
//CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 12), findTestData('MainData/Users').getValue(
//        2, 12))
//
//WebUI.maximizeWindow()
//
////Toma Screen
//CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()
//
////Ingresa el ENQ en el Buscador
//WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'RECORD.LOCK')
//
////Clickea en el btn "Ejecutar"
//WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))
//
////Cambia a ventana nueva
//WebUI.switchToWindowTitle('Active Record Locks')
//
////Click en Lista de Registro
//WebUI.click(findTestObject('06-Comisiones/DESBLOQUEO CUENTA/Page_Active Record Locks/btnListadeRegistros'))
//
////Click Cuenta Bloqueada
//WebUI.click(findTestObject('06-Comisiones/DESBLOQUEO CUENTA/Page_Active Record Locks/lnkCuentaBloqueada'))
//
////Auto/Bor/Rev Registro
//WebUI.click(findTestObject('06-Comisiones/DESBLOQUEO CUENTA/Page_Active Record Locks/btnAutoBorraRevRegistro'))
//
////Desbloquear
//WebUI.click(findTestObject('06-Comisiones/DESBLOQUEO CUENTA/Page_Active Record Locks/btnDesbloquear') //Control de fin de script
//    )
CustomKeywords.'pkgModules.kywGeneric.ConfigEnvironment'(GlobalVariable.vServerIPRun, GlobalVariable.vServerNameRun)

//Login
CustomKeywords.'pkgModules.kywGeneric.Login'(findTestData('MainData/Users').getValue(1, 1), findTestData('MainData/Users').getValue(
        2, 1))

WebUI.maximizeWindow()

//Toma Screen
CustomKeywords.'pkgModules.kywScreenshot.takeScreenshotInScript'()

//Ingresa el ENQ en el Buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CTA.CONS.BONIF')

//Clickea en el btn "Ejecutar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambia a la ventana "Bonificacion - Consulta"
WebUI.switchToWindowTitle('Bonificacion - Consulta')

//Filtro para limpiar selección
CustomKeywords.'pkgModules.kywGeneric.LimpiarFiltroenScript'()

WebUI.switchToWindowIndex(0)

//Vuelve a ingresar en ENQ en el Buscador
WebUI.setText(findTestObject('02-Dashboard/txtDashboardBuscador'), 'ENQ BCCL.E.CTA.CONS.BONIF')

//Clickea en el btn "Ejecutar"
WebUI.click(findTestObject('02-Dashboard/btnDashboardGo'))

//Cambia a la ventana "Bonificacion - Consulta"
WebUI.switchToWindowTitle('Bonificacion - Consulta')

//Ingresa el Nro de Cuenta
WebUI.setText(findTestObject('06-Comisiones/BCCL.E.CTA.CONS.BONIF/txtNroCuenta'), '00010015665')

//Ingresa la Comision
WebUI.setText(findTestObject('06-Comisiones/BCCL.E.CTA.CONS.BONIF/txtTipoComision'), 'DEPDISP')

//Click en Ejecutar
WebUI.click(findTestObject('00-Utils/02-Filtros/lnkEjecutar'))

//Click en Alta/Modificacion
WebUI.click(findTestObject('06-Comisiones/BCCL.E.CTA.CONS.BONIF/lnkAltaModificacion'))

WebUI.setText(findTestObject('06-Comisiones/BCCL.E.CTA.CONS.BONIF/txtPorcentajeBonif'), '500')

//Ingresa Bonificacion.
//Ingresa fecha hasta.
WebUI.setText(findTestObject('06-Comisiones/BCCL.E.CTA.CONS.BONIF/txtFechaVigenciaHasta'), GlobalVariable.vFechaCOBAmbTES10)

//Acepta el registro
WebUI.click(findTestObject('06-Comisiones/BCCL.E.CTA.CONS.BONIF/btnAceptarRegistro'))

//Assert
WebUI.waitForElementVisible(findTestObject('Object Repository/06-Comisiones/BCCL.E.CTA.CONS.BONIF/lblFaltaIngresarInformacion'), 
    6)

WebUI.verifyElementVisible(findTestObject('Object Repository/06-Comisiones/BCCL.E.CTA.CONS.BONIF/lblFaltaIngresarInformacion'))

def element = WebUI.getText(findTestObject('Object Repository/06-Comisiones/BCCL.E.CTA.CONS.BONIF/lblFaltaIngresarInformacion'))

assert element.contains('VALOR DEBE SER MAYOR A 0 Y MENOR O IGUAL A 100') 


//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgModules.kywGeneric.fFailStatus'()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgModules.kywGeneric.fPassStatus'()
}

