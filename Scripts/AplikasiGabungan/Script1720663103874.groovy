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
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.nio.file.attribute.BasicFileAttributes
import java.util.Comparator
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

String baseDir = System.getProperty('user.dir')

WebUI.setText(findTestObject('Object Repository/xpath', ['xpath' : "//input[@id='mobileid']"]), MID)
WebUI.takeScreenshot((((baseDir + GlobalVariable.screenshotPath)) + '/' + Data  + '/' + '1 Input Mobile ID') + '.png', FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/xpath', ['xpath' : "//button[@type='submit']"]))
WebUI.delay(2)

// Hasil Search
WebUI.waitForElementPresent(findTestObject('Object Repository/xpath', ['xpath' : "//*[text()='Result Document']"]), 0)
WebUI.takeScreenshot((((baseDir + GlobalVariable.screenshotPath)) + '/' + Data  + '/' + '2 Hasil Search Mobile ID') + '.png', FailureHandling.STOP_ON_FAILURE)

String downloadDirectoryPath = System.getProperty('user.home') + "\\Downloads"
Path downloadDirectory = Paths.get(downloadDirectoryPath)


void clickAndTakeScreenshot(String xpath, String screenshotName) {
	String baseDir = System.getProperty('user.dir')
    WebUI.click(findTestObject('Object Repository/xpath', ['xpath': xpath]))
    WebUI.waitForElementPresent(findTestObject('Object Repository/xpath', ['xpath': "//*[@id='loadingBar' and @class='hidden']"]), 0)
    WebUI.delay(1)
    WebUI.takeScreenshot("${baseDir}${GlobalVariable.screenshotPath}/${Data}/${screenshotName}.png", FailureHandling.STOP_ON_FAILURE)
}

void downloadAndMoveFile(String targetFileName) {
	String downloadDirectoryPath = System.getProperty('user.home') + "\\Downloads"
	Path downloadDirectory = Paths.get(downloadDirectoryPath)
    WebUI.click(findTestObject('Object Repository/xpath', ['xpath': "//button[@id='downloadButton']"]))
    Path mostRecentFile = Files.list(downloadDirectory)
        .filter(Files.&isRegularFile)
        .max(Comparator.comparingLong { Path p -> p.toFile().lastModified() })
        .orElse(null)
    if (mostRecentFile == null) {
        println("No recent file found in download directory.")
        return
    }
    Path targetPath = Paths.get(downloadDirectoryPath, targetFileName)
    try {
        Files.move(mostRecentFile, targetPath, StandardCopyOption.REPLACE_EXISTING)
    } catch (IOException e) {
        println("Error moving file: ${e.message}")
        e.printStackTrace()
    }
    WebUI.click(findTestObject('Object Repository/xpath', ['xpath': "//button[@class='btn-close']"]))
}

// KTP Konsumen
clickAndTakeScreenshot("//a[text()='KTP Konsumen']", "3 KTP Konsumen")
downloadAndMoveFile("KTPKonsumen${Data}.png")

// Kartu Nama Konsumen
clickAndTakeScreenshot("//a[text()='Kartu Nama Konsumen']", "4 Kartu Nama Konsumen")
downloadAndMoveFile("KartuNama${Data}.png")

// Kartu Keluarga
clickAndTakeScreenshot("//a[text()='Kartu Keluarga']", "5 Kartu Keluarga")
downloadAndMoveFile("KartuKeluarga${Data}.png")

// NPWP Konsumen
clickAndTakeScreenshot("//a[text()='NPWP Konsumen']", "6 NPWP Konsumen")
downloadAndMoveFile("NPWP${Data}.png")

/*
// Cover Buku Tabungan
clickAndTakeScreenshot("//a[text()='Cover Buku Tabungan']", "7 Cover Buku Tabungan")
downloadAndMoveFile("CoverBukuTabungan${Data}.png")
*/

// FAP
clickAndTakeScreenshot("//a[text()='FAP']", "8 FAP")
downloadAndMoveFile("FAP${Data}.png")

// Slip Gaji
clickAndTakeScreenshot("//a[text()='Slip Gaji']", "8 Slip Gaji")
downloadAndMoveFile("SlipGaji${Data}.png")

// SIUP
clickAndTakeScreenshot("//a[text()='SIUP']", "9 SIUP")
downloadAndMoveFile("SIUP${Data}.png")