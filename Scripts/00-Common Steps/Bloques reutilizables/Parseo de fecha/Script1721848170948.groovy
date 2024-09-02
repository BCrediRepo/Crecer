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

//librerias necesarias
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.DayOfWeek

//FECHA FUTURA (fecha > fecha cob)
//la suma de dia se realiza en plus days
//el while nos asegura de que la fecha seleccionada es un dia habil
//en formato assert definimos el formato dia mes año 
//en fecha assert le decimos que a esa fecha (numerica) el mes pase a estar escrito (toUpperCase), es decir, la fecha 20230902 se transforma en 02 SEP 2023
fecha = GlobalVariable.vFechaCOB
LocalDate fechaParse = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyyMMdd"))
LocalDate fechaModificada = fechaParse.plusDays(1)
while (fechaModificada.getDayOfWeek() == DayOfWeek.SATURDAY || fechaModificada.getDayOfWeek() == DayOfWeek.SUNDAY) {	
		fechaModificada = fechaModificada.plusDays(1)
}
DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd")
String fechaFutura = fechaModificada.format(formato)
DateTimeFormatter formatoAssert = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH) //se le agrega el Locale.ENGLISH para que el formato que como queremos
String fechaAssert = fechaModificada.format(formatoAssert).toUpperCase()

//-------------------------------------------------------------------

//FECHA ANTERIOR
//Unica diferencia es que en vez de plusDays(cantidad de dias) usaremos minusDays(cantidad de dias)

fecha = GlobalVariable.vFechaCOB
LocalDate fechaParse = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyyMMdd"))
LocalDate fechaModificada = fechaParse.minusDays(1)
while (fechaModificada.getDayOfWeek() == DayOfWeek.SATURDAY || fechaModificada.getDayOfWeek() == DayOfWeek.SUNDAY) {
		fechaModificada = fechaModificada.minusDays(1)
}
DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd")
String fechaFutura = fechaModificada.format(formato)
DateTimeFormatter formatoAssert = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH) //se le agrega el Locale.ENGLISH para que el formato que como queremos
String fechaAssert = fechaModificada.format(formatoAssert).toUpperCase()


