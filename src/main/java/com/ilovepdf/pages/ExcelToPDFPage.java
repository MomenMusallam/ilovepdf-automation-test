package com.ilovepdf.pages;

import com.ilovepdf.helpers.ValidationHelper;
import org.openqa.selenium.By;

/**
 * Page Object for ilovepdf.com/excel_to_pdf
 *
 * Converts .xls / .xlsx files to PDF format.
 */
public class ExcelToPDFPage extends BasePage {

    // ── Upload ────────────────────────────────────────────────────────────────
    private final By selectFilesButton = By.id("pickfiles");
    private final By fileInput         = By.cssSelector("input[type='file']");

    // ── Action ────────────────────────────────────────────────────────────────
    private final By convertButton     = By.id("processTask");

    // ── Result state ──────────────────────────────────────────────────────────
    private final By successTitle       = By.xpath("//h1[text()='EXCEL file has been converted to PDF']");
    private final By downloadButton     = By.xpath("//a[@id='pickfiles']");

    // ── Error feedback ────────────────────────────────────────────────────────
    private final By errorMessage      = By.cssSelector(".alert-danger, [class*='error'], [class*='alert']");

    // ── Page identity ─────────────────────────────────────────────────────────
    private final By pageTitle1         = By.xpath("//h1[contains(normalize-space(),'Convert EXCEL to PDF') and @class='tool__header__title']");
    private final By pageTitle2          = By.xpath("//div[contains(normalize-space(),'Excel to PDF') and @class='option__panel__title']");

    // ─────────────────────────────────────────────────────────────────────────
    // Actions
    // ─────────────────────────────────────────────────────────────────────────

    public ExcelToPDFPage uploadFile(String fileName) {
        super.uploadFile(fileInput, fileName);
        log.info("Uploaded file to Excel to PDF page: {}", fileName);
        return this;
    }

    public ExcelToPDFPage clickConvert() {
        click(convertButton);
        log.info("Clicked Convert Excel to PDF button");
        return this;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // State queries
    // ─────────────────────────────────────────────────────────────────────────

    public boolean isPageDisplayed(String title)              { return"title1".equals(title)? isDisplayed(pageTitle1): isDisplayed(pageTitle2); }
    public boolean isSelectFilesButtonDisplayed() { return isDisplayed(selectFilesButton); }
    public boolean isConvertButtonDisplayed()     { return isDisplayed(convertButton); }
    public boolean isConversionSuccessful()       { return isDisplayed(successTitle); }
    public boolean isDownloadAvailable()          { return isDisplayed(downloadButton); }
    public boolean isErrorMessageDisplayed()      { return isDisplayed(errorMessage); }

    public String getPageHeadingText()            { return getText(pageTitle1); }
    public String getCurrentUrl()                 { return currentUrl(); }

    // ─────────────────────────────────────────────────────────────────────────
    // Validations
    // ─────────────────────────────────────────────────────────────────────────

    public ExcelToPDFPage verifyUrlContainsExcelToPdf() {
        ValidationHelper.verifyUrlContains("excel_to_pdf");
        return this;
    }
}
