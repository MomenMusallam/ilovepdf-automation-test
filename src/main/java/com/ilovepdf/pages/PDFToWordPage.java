package com.ilovepdf.pages;

import com.ilovepdf.helpers.ValidationHelper;
import org.openqa.selenium.By;

/**
 * Page Object for ilovepdf.com/pdf_to_word
 *
 * Converts PDF files to editable .docx format.
 */
public class PDFToWordPage extends BasePage {

    // ── Upload ────────────────────────────────────────────────────────────────
    private final By selectFilesButton = By.id("pickfiles");
    private final By fileInput         = By.cssSelector("input[type='file']");

    // ── Action ────────────────────────────────────────────────────────────────
    private final By convertButton     = By.id("processTask");

    // ── Result state ──────────────────────────────────────────────────────────
    private final By successTitle      = By.xpath("//h1[text()='Your PDF has been converted to an editable WORD document']");
    private final By downloadButton    = By.xpath("//a[@id='pickfiles']");

    // ── Page identity ─────────────────────────────────────────────────────────
    private final By pageTitle1          = By.xpath("//h1[contains(normalize-space(),'PDF to WORD') and @class='tool__header__title']");
    private final By pageTitle2          = By.xpath("//div[contains(normalize-space(),'PDF to Word') and @class='option__panel__title']");

    // ─────────────────────────────────────────────────────────────────────────
    // Actions
    // ─────────────────────────────────────────────────────────────────────────

    public PDFToWordPage uploadFile(String fileName) {
        super.uploadFile(fileInput, fileName);
        log.info("Uploaded file to PDF to Word page: {}", fileName);
        return this;
    }

    public PDFToWordPage clickConvert() {
        click(convertButton);
        log.info("Clicked Convert to Word button");
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

    public String getPageHeadingText()            { return getText(pageTitle1); }
    public String getCurrentUrl()                 { return currentUrl(); }

    // ─────────────────────────────────────────────────────────────────────────
    // Validations
    // ─────────────────────────────────────────────────────────────────────────

    public PDFToWordPage verifyUrlContainsPdfToWord() {
        ValidationHelper.verifyUrlContains("pdf_to_word");
        return this;
    }
}
