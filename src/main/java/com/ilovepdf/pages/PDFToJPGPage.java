package com.ilovepdf.pages;

import com.ilovepdf.helpers.ValidationHelper;
import org.openqa.selenium.By;

/**
 * Page Object for ilovepdf.com/pdf_to_jpg
 *
 * Converts each page of a PDF into a JPG image.
 * Conversion modes:
 *  - Convert entire pages to JPG  (default)
 *  - Extract embedded images
 * Quality levels: Low / Medium / High
 */
public class PDFToJPGPage extends BasePage {

    // ── Upload ────────────────────────────────────────────────────────────────
    private final By selectFilesButton   = By.id("pickfiles");
    private final By fileInput           = By.cssSelector("input[type='file']");

    // ── Conversion mode ───────────────────────────────────────────────────────
    private final By convertPagesMode    = By.cssSelector("[for='convert_option-pages'], [data-mode='pages']");
    private final By extractImagesMode   = By.cssSelector("[for='convert_option-images'], [data-mode='images']");

    // ── Quality options ───────────────────────────────────────────────────────
    private final By normalQualityOption    = By.xpath("//li[@data-value='150']");
//    private final By mediumQualityOption = By.cssSelector("[for='qulity-medium'], #quality-medium");
    private final By highQualityOption   = By.xpath("//li[@data-value='300']");

    // ── Action ────────────────────────────────────────────────────────────────
    private final By convertButton       = By.id("processTask");

    // ── Result state ──────────────────────────────────────────────────────────
    private final By successTitle       = By.xpath("//h1[text()='PDF has been converted to JPG images']");
    private final By downloadButton     = By.xpath("//a[@id='pickfiles']");

    // ── Page identity ─────────────────────────────────────────────────────────
    private final By pageTitle1          = By.xpath("//h1[contains(normalize-space(),'PDF to JPG') and @class='tool__header__title']");
    private final By pageTitle2          = By.xpath("//div[contains(normalize-space(),'PDF to JPG options') and @class='option__panel__title']");
    // ─────────────────────────────────────────────────────────────────────────
    // Actions
    // ─────────────────────────────────────────────────────────────────────────

    public PDFToJPGPage uploadFile(String fileName) {
        super.uploadFile(fileInput, fileName);
        log.info("Uploaded file to PDF to JPG page: {}", fileName);
        return this;
    }

    public PDFToJPGPage selectConvertPagesMode() {
        click(convertPagesMode);
        log.info("Selected 'Convert entire pages' mode");
        return this;
    }

    public PDFToJPGPage selectExtractImagesMode() {
        click(extractImagesMode);
        log.info("Selected 'Extract embedded images' mode");
        return this;
    }

//    public PDFToJPGPage selectLowQuality() {
//        click(lowQualityOption);
//        log.info("Selected Low quality");
//        return this;
//    }

    public PDFToJPGPage selectMediumQuality() {
        click(normalQualityOption);
        log.info("Selected Medium quality");
        return this;
    }

    public PDFToJPGPage selectHighQuality() {
        click(highQualityOption);
        log.info("Selected High quality");
        return this;
    }

    public PDFToJPGPage clickConvert() {
        click(convertButton);
        log.info("Clicked Convert to JPG button");
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

    public PDFToJPGPage verifyUrlContainsPdfToJpg() {
        ValidationHelper.verifyUrlContains("pdf_to_jpg");
        return this;
    }
}
