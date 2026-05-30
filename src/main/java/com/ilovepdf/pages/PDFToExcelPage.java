package com.ilovepdf.pages;

import com.ilovepdf.helpers.ValidationHelper;
import org.openqa.selenium.By;

/**
 * Page Object for ilovepdf.com/pdf_to_excel
 *
 * Converts PDF files to editable .xlsx format.
 */
public class PDFToExcelPage extends BasePage {

    // ── Upload ────────────────────────────────────────────────────────────────
    private final By selectFilesButton = By.id("pickfiles");
    private final By fileInput         = By.cssSelector("input[type='file']");

    // ── Action ────────────────────────────────────────────────────────────────
    private final By convertButton     = By.xpath("//button[@id='processTask']");

    // ── Result state ──────────────────────────────────────────────────────────
    private final By successTitle      = By.xpath("//h1[text()='Your PDF has been converted to an editable EXCEL spreadsheet']");
    private final By downloadButton    = By.xpath("//a[@id='pickfiles']");

    // ── Page identity ─────────────────────────────────────────────────────────
    private final By pageTitle         = By.cssSelector("h1.tool-title, h1");

    // ─────────────────────────────────────────────────────────────────────────
    // Actions
    // ─────────────────────────────────────────────────────────────────────────

    public PDFToExcelPage uploadFile(String fileName) {
        super.uploadFile(fileInput, fileName);
        log.info("Uploaded file to PDF to Excel page: {}", fileName);
        return this;
    }

    public PDFToExcelPage clickConvert() {
//    	Thread.sleep(100);
        click(convertButton);
        log.info("Clicked Convert to Excel button");
        return this;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // State queries
    // ─────────────────────────────────────────────────────────────────────────

    public boolean isPageDisplayed()              { return isDisplayed(pageTitle); }
    public boolean isSelectFilesButtonDisplayed() { return isDisplayed(selectFilesButton); }
    public boolean isConvertButtonDisplayed()     { return isDisplayed(convertButton); }
    
    public boolean isConversionSuccessful()       { return isDisplayed(successTitle); }
    public boolean isDownloadAvailable()          { return isDisplayed(downloadButton); }

    public String getPageHeadingText()            { return getText(pageTitle); }
    public String getCurrentUrl()                 { return currentUrl(); }

    // ─────────────────────────────────────────────────────────────────────────
    // Validations
    // ─────────────────────────────────────────────────────────────────────────

    public PDFToExcelPage verifyUrlContainsPdfToExcel() {
        ValidationHelper.verifyUrlContains("pdf_to_excel");
        return this;
    }
}
