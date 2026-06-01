package com.ilovepdf.pages;

import com.ilovepdf.helpers.ValidationHelper;
import org.openqa.selenium.By;

/**
 * Page Object for ilovepdf.com/protect_pdf
 *
 * Protect PDF adds a password to a PDF file so it cannot be opened
 * without the password.
 *
 * UI behaviour:
 *  - Page loads with only the upload area visible.
 *  - After uploading a PDF, a password input field appears and the
 *    Protect PDF (#processTask) button becomes visible.
 *  - Submitting without a password should show a validation error.
 *  - Submitting with a valid password → success / download state.
 */
public class ProtectPDFPage extends BasePage {

    // ── Upload ────────────────────────────────────────────────────────────────
    private final By selectFilesButton = By.id("pickfiles");
    private final By fileInput         = By.cssSelector("input[type='file']");

    // ── Password field (appears after upload) ──────────────────────────────────
    private final By passwordInput     = By.xpath("//input[@id='pwd']");
    private final By confirmPasswordInput     = By.xpath("//input[@id='pwd2']");

    // ── Action ────────────────────────────────────────────────────────────────
    private final By protectButton     = By.id("processTask");

    // ── Result / error states ─────────────────────────────────────────────────
    private final By successTitle      = By.cssSelector(
            ".tools__success__title, [class*='success']");
    private final By downloadButton    = By.xpath("//h1[text()='PDF files have been protected!']");
    private final By errorMessage      = By.xpath("//a[@id='processTaskWrapper']");
    private final By passwordValidationMsg = By.cssSelector(
            "input[type='password']:invalid, .password-error, [class*='password-hint']");

    // ── Page identity ─────────────────────────────────────────────────────────
    private final By pageTitle1          = By.xpath("//h1[contains(normalize-space(),'Protect PDF') and @class='tool__header__title']");
    private final By pageTitle2          = By.xpath("//div[contains(normalize-space(),'Protect PDF') and @class='option__panel__title']");

    // ─────────────────────────────────────────────────────────────────────────
    // Actions
    // ─────────────────────────────────────────────────────────────────────────

    public ProtectPDFPage uploadFile(String fileName) {
        super.uploadFile(fileInput, fileName);
        log.info("Uploaded file to Protect PDF page: {}", fileName);
        return this;
    }

    public ProtectPDFPage enterPassword(String password) {
        clearAndType(passwordInput, password);
        log.info("Entered password on Protect PDF page");
        return this;
    }
    
    public ProtectPDFPage enterPasswordConfirm(String password) {
        clearAndType(confirmPasswordInput, password);
        log.info("Entered confirm password on Protect PDF page");
        return this;
    }

    public ProtectPDFPage clickProtect() {
        click(protectButton);
        log.info("Clicked Protect PDF button");
        return this;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // State queries
    // ─────────────────────────────────────────────────────────────────────────

    public boolean isPageDisplayed(String title)              { return"title1".equals(title)? isDisplayed(pageTitle1): isDisplayed(pageTitle2); }
    public boolean isSelectFilesButtonDisplayed() { return isDisplayed(selectFilesButton); }

    /**
     * Returns true only if the Protect PDF (#processTask) button is currently
     * visible. On page load (before upload) this returns false.
     */
    public boolean isProtectButtonDisplayed()     { return isDisplayed(protectButton); }
    public boolean isPasswordInputDisplayed()     { return isDisplayed(passwordInput); }
    public boolean isProtectSuccessful()          { return isDisplayed(successTitle); }
    public boolean isDownloadAvailable()          { return isDisplayed(downloadButton); }
    public boolean isProtectButtonDisabled()      { return isDisable(protectButton); }
    public boolean isErrorMessageDisplayed()      { return isDisplayed(errorMessage); }

    public String getErrorMessageText()           { return getText(errorMessage); }
    public String getPageHeadingText()            { return getText(pageTitle1); }
    public String getCurrentUrl()                 { return currentUrl(); }

    // ─────────────────────────────────────────────────────────────────────────
    // Validations
    // ─────────────────────────────────────────────────────────────────────────

    public ProtectPDFPage verifyUrlContainsProtectPdf() {
        ValidationHelper.verifyUrlContains("protect_pdf");
        return this;
    }
}
