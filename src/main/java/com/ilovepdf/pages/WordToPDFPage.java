package com.ilovepdf.pages;

import com.ilovepdf.helpers.ValidationHelper;
import org.openqa.selenium.By;

/**
 * Page Object for ilovepdf.com/word_to_pdf
 *
 * Converts .doc / .docx files to PDF format.
 * Accepted file types: .doc, .docx
 */
public class WordToPDFPage extends BasePage {

    // ── Upload ────────────────────────────────────────────────────────────────
    private final By selectFilesButton = By.id("pickfiles");
    private final By fileInput         = By.cssSelector("input[type='file']");

    // ── Action ────────────────────────────────────────────────────────────────
    private final By convertButton     = By.id("processTask");

    // ── Result state ──────────────────────────────────────────────────────────
    private final By successTitle       = By.xpath("//h1[text()='WORD file has been converted to PDF']");
    private final By downloadButton     = By.xpath("//a[@id='pickfiles']");

    // ── Error / validation feedback ───────────────────────────────────────────
    private final By errorMessage      = By.cssSelector(".alert-danger, [class*='error'], [class*='alert']");

    // ── Page identity ─────────────────────────────────────────────────────────
    private final By pageTitle1          = By.xpath("//h1[contains(normalize-space(),'WORD to PDF') and @class='tool__header__title']");
    private final By pageTitle2          = By.xpath("//div[contains(normalize-space(),'Word to PDF') and @class='option__panel__title']");
    // ─────────────────────────────────────────────────────────────────────────
    // Actions
    // ─────────────────────────────────────────────────────────────────────────

    public WordToPDFPage uploadFile(String fileName) {
        super.uploadFile(fileInput, fileName);
        log.info("Uploaded file to Word to PDF page: {}", fileName);
        return this;
    }

    public WordToPDFPage clickConvert() {
        click(convertButton);
        log.info("Clicked Convert to PDF button");
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

    public String getErrorMessageText()           { return getText(errorMessage); }
    public String getPageHeadingText()            { return getText(pageTitle1); }
    public String getCurrentUrl()                 { return currentUrl(); }

    // ─────────────────────────────────────────────────────────────────────────
    // Validations
    // ─────────────────────────────────────────────────────────────────────────

    public WordToPDFPage verifyUrlContainsWordToPdf() {
        ValidationHelper.verifyUrlContains("word_to_pdf");
        return this;
    }
}
