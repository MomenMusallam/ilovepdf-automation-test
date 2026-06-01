package com.ilovepdf.pages;

import com.ilovepdf.helpers.ValidationHelper;
import org.openqa.selenium.By;

/**
 * Page Object for ilovepdf.com/split_pdf
 *
 * Split modes supported by the tool:
 *  - Extract all pages  (default / "all-pages" mode)
 *  - Split by ranges    (data-mode="ranges") – user enters page range e.g. "1-3,5"
 *  - Split by pages     (data-mode="pages")  – fixed interval, one page per file, etc.
 */
public class SplitPDFPage extends BasePage {

    // ── Upload ────────────────────────────────────────────────────────────────
    private final By selectFilesButton = By.id("pickfiles");
    private final By fileInput         = By.cssSelector("input[type='file']");

    // ── Split mode options ────────────────────────────────────────────────────
    private final By extractAllOption  = By.cssSelector("[data-mode='all-pages'], [for='all-pages']");
    private final By rangeOption       = By.xpath("//li[@id='tab-range']");
    private final By pagesOption       = By.xpath("//li[@id='tab-extract']");

    // ── Range input field (visible only when rangeOption selected) ────────────
    private final By rangeInput        = By.cssSelector("input[name='ranges'], #ranges");

    // ── Action ────────────────────────────────────────────────────────────────
    private final By splitButton       = By.id("processTask");

    // ── Result state ──────────────────────────────────────────────────────────
    private final By successTitle       = By.xpath("//h1[text()='PDF has been split!']");
    private final By downloadButton     = By.xpath("//a[@id='pickfiles']");

    // ── Page identity ─────────────────────────────────────────────────────────
    private final By pageTitle1          = By.xpath("//h1[contains(normalize-space(),'Split PDF file') and @class='tool__header__title']");
    private final By pageTitle2          = By.xpath("//div[contains(normalize-space(),'Split') and @class='option__panel__title']");
    // ─────────────────────────────────────────────────────────────────────────
    // Actions
    // ─────────────────────────────────────────────────────────────────────────

    public SplitPDFPage uploadFile(String fileName) {
        super.uploadFile(fileInput, fileName);
        log.info("Uploaded file to Split PDF page: {}", fileName);
        return this;
    }

    public SplitPDFPage selectExtractAllMode() {
        click(extractAllOption);
        log.info("Selected 'Extract all pages' split mode");
        return this;
    }

    public SplitPDFPage selectRangeMode() {
        click(rangeOption);
        log.info("Selected 'Split by ranges' mode");
        return this;
    }

    public SplitPDFPage enterRanges(String ranges) {
        clearAndType(rangeInput, ranges);
        log.info("Entered split ranges: {}", ranges);
        return this;
    }

    public SplitPDFPage selectPagesMode() {
        click(pagesOption);
        log.info("Selected 'Split by pages' mode");
        return this;
    }

    public SplitPDFPage clickSplit() {
        click(splitButton);
        log.info("Clicked Split PDF button");
        return this;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // State queries
    // ─────────────────────────────────────────────────────────────────────────

    public boolean isPageDisplayed(String title)              { return"title1".equals(title)? isDisplayed(pageTitle1): isDisplayed(pageTitle2); }
    public boolean isSelectFilesButtonDisplayed() { return isDisplayed(selectFilesButton); }
    public boolean isSplitButtonDisplayed()       { return isDisplayed(splitButton); }
    public boolean isSplitSuccessful()            { return isDisplayed(successTitle); }
    public boolean isDownloadAvailable()          { return isDisplayed(downloadButton); }
    public boolean isRangeInputDisplayed()        { return isDisplayed(rangeInput); }

    public String getPageHeadingText()            { return getText(pageTitle1); }
    public String getCurrentUrl()                 { return currentUrl(); }

    // ─────────────────────────────────────────────────────────────────────────
    // Validations
    // ─────────────────────────────────────────────────────────────────────────

    public SplitPDFPage verifyUrlContainsSplit() {
        ValidationHelper.verifyUrlContains("split_pdf");
        return this;
    }
}
