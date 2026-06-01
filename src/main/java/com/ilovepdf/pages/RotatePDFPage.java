package com.ilovepdf.pages;

import com.ilovepdf.helpers.ValidationHelper;
import org.openqa.selenium.By;

/**
 * Page Object for ilovepdf.com/rotate_pdf
 *
 * Rotate PDF allows users to rotate individual pages or all pages of a PDF.
 * Rotation options: 90° Right, 90° Left, 180°
 *
 * UI behaviour:
 *  - Page loads with only the upload area visible.
 *  - After upload, page thumbnails render and the Rotate PDF (#processTask)
 *    button becomes visible.
 *  - Each page thumbnail has individual L / R rotate controls.
 *  - "Rotate all" shortcuts (rotate all left / right) may also be available.
 */
public class RotatePDFPage extends BasePage {

    // ── Upload ────────────────────────────────────────────────────────────────
    private final By selectFilesButton    = By.id("pickfiles");
    private final By fileInput            = By.cssSelector("input[type='file']");

    // ── Rotate controls (visible after upload) ────────────────────────────────
    // "Rotate all" buttons in the toolbar
    private final By rotateAllRightButton = By.xpath("//button[@data-action='rotateRight']");
    private final By rotateAllLeftButton  = By.xpath("//button[@data-action='rotateLeft']");

    // Per-page rotate right/left on the first thumbnail
    private final By firstPageRotateRight = By.cssSelector(
            ".thumbnail-container:first-child [data-direction='right'], " +
            ".file-item:first-child [data-direction='right']");
    private final By firstPageRotateLeft  = By.cssSelector(
            ".thumbnail-container:first-child [data-direction='left'], " +
            ".file-item:first-child [data-direction='left']");

    // ── Action ────────────────────────────────────────────────────────────────
    private final By rotateButton         = By.id("processTask");

    // ── Result state ──────────────────────────────────────────────────────────
    private final By successTitle         = By.xpath("//h1[text()='PDF files have been rotated!']");
    private final By downloadButton     = By.xpath("//a[@id='pickfiles']");

    // ── Page identity ─────────────────────────────────────────────────────────
    private final By pageTitle1          = By.xpath("//h1[contains(normalize-space(),'Rotate PDF') and @class='tool__header__title']");
    private final By pageTitle2          = By.xpath("//div[contains(normalize-space(),'Rotate PDF') and @class='option__panel__title']");
    // ─────────────────────────────────────────────────────────────────────────
    // Actions
    // ─────────────────────────────────────────────────────────────────────────

    public RotatePDFPage uploadFile(String fileName) {
        super.uploadFile(fileInput, fileName);
        log.info("Uploaded file to Rotate PDF page: {}", fileName);
        return this;
    }

    public RotatePDFPage clickRotateAllRight() {
        click(rotateAllRightButton);
        log.info("Clicked Rotate All Right");
        return this;
    }

    public RotatePDFPage clickRotateAllLeft() {
        click(rotateAllLeftButton);
        log.info("Clicked Rotate All Left");
        return this;
    }

    public RotatePDFPage clickFirstPageRotateRight() {
        click(firstPageRotateRight);
        log.info("Clicked Rotate Right on first page thumbnail");
        return this;
    }

    public RotatePDFPage clickFirstPageRotateLeft() {
        click(firstPageRotateLeft);
        log.info("Clicked Rotate Left on first page thumbnail");
        return this;
    }

    public RotatePDFPage clickRotate() {
        click(rotateButton);
        log.info("Clicked Rotate PDF button");
        return this;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // State queries
    // ─────────────────────────────────────────────────────────────────────────

    public boolean isPageDisplayed(String title)              { return "title1".equals(title)? isDisplayed(pageTitle1): isDisplayed(pageTitle2); }
    public boolean isSelectFilesButtonDisplayed()  { return isDisplayed(selectFilesButton); }

    /**
     * Returns true only if the Rotate PDF (#processTask) button is currently
     * visible in the DOM. On page load (before any upload) this returns false.
     */
    public boolean isRotateButtonDisplayed()       { return isDisplayed(rotateButton); }
    public boolean isRotateSuccessful()            { return isDisplayed(successTitle); }
    public boolean isDownloadAvailable()           { return isDisplayed(downloadButton); }
    public boolean isRotateAllRightButtonDisplayed(){ return isDisplayed(rotateAllRightButton); }
    public boolean isRotateAllLeftButtonDisplayed() { return isDisplayed(rotateAllLeftButton); }

    public String getPageHeadingText()             { return getText(pageTitle1); }
    public String getCurrentUrl()                  { return currentUrl(); }

    // ─────────────────────────────────────────────────────────────────────────
    // Validations
    // ─────────────────────────────────────────────────────────────────────────

    public RotatePDFPage verifyUrlContainsRotatePdf() {
        ValidationHelper.verifyUrlContains("rotate_pdf");
        return this;
    }
}
