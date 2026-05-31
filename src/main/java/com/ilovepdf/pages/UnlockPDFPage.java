package com.ilovepdf.pages;

import com.ilovepdf.helpers.ValidationHelper;
import org.openqa.selenium.By;

/**
 * Page Object for ilovepdf.com/unlock_pdf
 *
 * Unlock PDF removes the password protection from a PDF file.
 *
 * UI behaviour:
 *  - Page loads with only the upload area visible.
 *  - After uploading a password-protected PDF, a password input field
 *    appears and the Unlock PDF (#processTask) button becomes visible.
 *  - For non-protected PDFs the tool may process without a password prompt.
 *  - Error message shown if wrong/missing password is submitted.
 *
 * Note: True unlock tests (with a real password-protected PDF) require a
 *   locked sample file (e.g. locked_sample.pdf). These tests verify the
 *   UI flow and error handling; actual decryption is validated via the
 *   success/download state.
 */
public class UnlockPDFPage extends BasePage {

    // ── Upload ────────────────────────────────────────────────────────────────
    private final By selectFilesButton = By.id("pickfiles");
    private final By fileInput         = By.cssSelector("input[type='file']");

    // ── Password field (appears after uploading a protected PDF) ──────────────
    private final By passwordInput     = By.cssSelector(
            "input[type='password'], input[name='password'], #password");

    // ── Action ────────────────────────────────────────────────────────────────
    private final By unlockButton      = By.id("processTask");

    // ── Result / error states ─────────────────────────────────────────────────
    private final By successTitle       = By.xpath("//h1[text()='PDF files have been unlocked!']");

    private final By downloadButton     = By.xpath("//a[@id='pickfiles']");

    private final By errorMessage      = By.cssSelector(
            ".alert-danger, [class*='error'], [class*='alert'], [class*='invalid']");

    // ── Page identity ─────────────────────────────────────────────────────────
    private final By pageTitle1          = By.xpath("//h1[contains(normalize-space(),'Unlock PDF') and @class='tool__header__title']");
    private final By pageTitle2          = By.xpath("//div[contains(normalize-space(),'Unlock PDF') and @class='option__panel__title']");

    // ─────────────────────────────────────────────────────────────────────────
    // Actions
    // ─────────────────────────────────────────────────────────────────────────

    public UnlockPDFPage uploadFile(String fileName) {
        super.uploadFile(fileInput, fileName);
        log.info("Uploaded file to Unlock PDF page: {}", fileName);
        return this;
    }

    public UnlockPDFPage enterPassword(String password) {
        clearAndType(passwordInput, password);
        log.info("Entered password on Unlock PDF page");
        return this;
    }

    public UnlockPDFPage clickUnlock() {
        click(unlockButton);
        log.info("Clicked Unlock PDF button");
        return this;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // State queries
    // ─────────────────────────────────────────────────────────────────────────

    public boolean isPageDisplayed(String title)              { return"title1".equals(title)? isDisplayed(pageTitle1): isDisplayed(pageTitle2); }
    public boolean isSelectFilesButtonDisplayed() { return isDisplayed(selectFilesButton); }

    /**
     * Returns true only if the Unlock PDF (#processTask) button is currently
     * visible. On page load (before upload) this returns false.
     */
    public boolean isUnlockButtonDisplayed()      { return isDisplayed(unlockButton); }
    public boolean isPasswordInputDisplayed()     { return isDisplayed(passwordInput); }
    public boolean isUnlockSuccessful()           { return isDisplayed(successTitle); }
    public boolean isDownloadAvailable()          { return isDisplayed(downloadButton); }
    public boolean isErrorMessageDisplayed()      { return isDisplayed(errorMessage); }

    public String getErrorMessageText()           { return getText(errorMessage); }
    public String getPageHeadingText()            { return getText(pageTitle1); }
    public String getCurrentUrl()                 { return currentUrl(); }

    // ─────────────────────────────────────────────────────────────────────────
    // Validations
    // ─────────────────────────────────────────────────────────────────────────

    public UnlockPDFPage verifyUrlContainsUnlockPdf() {
        ValidationHelper.verifyUrlContains("unlock_pdf");
        return this;
    }
}
