package pkgModules

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable

public class kywUserForCommandLine {

	@Keyword
	def procesarUsuario(String cadena) {
		// Verificar si la cadena es nula o tiene menos de 2 caracteres
		if (cadena == null || cadena.length() < 2) {
			cadena = "Cadena no válida";
		} else {
			char segundoCaracter = cadena.charAt(1);
			// Verificar si el segundo caracter es una letra
			if (Character.isLetter(segundoCaracter)) {
				cadena = cadena;
			} else if (Character.isDigit(segundoCaracter)) {
				// Verificar si la cadena tiene al menos 4 caracteres
				if (cadena.length() >= 4) {
					cadena = cadena.substring(cadena.length() - 4);
				} else {
					cadena = "Cadena no válida";
				}
			} else {
				cadena = "Cadena no válida";
			}
		}

		return 'B.'+cadena;
	}
}

