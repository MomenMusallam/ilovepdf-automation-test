package com.ilovepdf.pages;

import com.ilovepdf.helpers.ElementHelper;
import com.ilovepdf.helpers.ValidationHelper;
import com.ilovepdf.utilities.WaitUtil;
import com.ilovepdf.enums.WaitStrategy;
import org.openqa.selenium.By;

import java.util.List;

/**
 * Page Object for ilovepdf.com/merge_pdf
 *
 * Responsibilities:
 *  - File upload (single and multiple)
 *  - Merge action trigger
 *  - Post-merge success / download state validation
 *  - Uploaded thumbnail list inspection
 */
public class MergePDFPage extends BasePage {

    // ── Upload area ──────────────────────────────────────────────────────────
    private final By selectFilesButton  = By.id("pickfiles");
    private final By fileInput          = By.cssSelector("input[type='file']");

    // ── Uploaded file thumbnails ──────────────────────────────────────────────
    private final By uploadedFileThumbs = By.cssSelector(".thumbnail-container, .file-item, [class*='file']");
    private final By uploadedFileNames  = By.cssSelector(".file-name, .thumbnail-name, [class*='filename']");
    private final By removeFileButton   = By.cssSelector(".btn-remove-file, [class*='remove'], [title='Remove']");

    // ── Action ────────────────────────────────────────────────────────────────
    private final By mergeButton        = By.id("processTask");

    // ── Result state ──────────────────────────────────────────────────────────
    private final By successTitle       = By.xpath("//h1[text()='PDFs have been merged!']");
    private final By downloadButton     = By.xpath("//a[@id='pickfiles']");
    private final By progressBar        = By.cssSelector(".progress, [class*='progress']");

    // ── Page identity ─────────────────────────────────────────────────────────
    private final By pageTitle1          = By.xpath("//h1[contains(normalize-space(),'Merge PDF') and @class='tool__header__title']");
    private final By pageTitle2          = By.xpath("//div[contains(normalize-space(),'Merge PDF') and @class='option__panel__title']");
    private final By pageHeading        = By.tagName("h1");

    // ─────────────────────────────────────────────────────────────────────────
    // Navigation
    // ─────────────────────────────────────────────────────────────────────────

    /** Upload a file by filename (resolved from testdata/pdf/ via FileHelper). */
    public MergePDFPage uploadFile(String fileName) {
        super.uploadFile(fileInput, fileName);
        log.info("Uploaded file to Merge PDF page: {}", fileName);
        return this;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Actions
    // ─────────────────────────────────────────────────────────────────────────

    public MergePDFPage clickMerge() {
        click(mergeButton);
        log.info("Clicked Merge PDF button");
        return this;
    }

    public MergePDFPage removeFirstFile() {
        click(removeFileButton);
        log.info("Clicked remove first file button");
        return this;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // State queries
    // ─────────────────────────────────────────────────────────────────────────

    public boolean isPageDisplayed(String title)              { return"title1".equals(title)? isDisplayed(pageTitle1): isDisplayed(pageTitle2); }
    public boolean isSelectFilesButtonDisplayed() { return isDisplayed(selectFilesButton); }
    public boolean isMergeButtonDisplayed()       { return isDisplayed(mergeButton); }
    public boolean isMergeSuccessful()            { return isDisplayed(successTitle); }
    public boolean isDownloadAvailable()          { return isDisplayed(downloadButton); }
    public boolean isProgressBarDisplayed()       { return isDisplayed(progressBar); }

    public String getPageHeadingText() { return getText(pageHeading); }
    public String getCurrentUrl()      { return currentUrl(); }

    /** Returns the number of file thumbnails currently shown in the drop zone. */
    public int getUploadedFileCount() {
        List<org.openqa.selenium.WebElement> thumbs =
                ElementHelper.getElements(uploadedFileThumbs);
        return thumbs.size();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Validations (delegated to ValidationHelper for cleaner test code)
    // ─────────────────────────────────────────────────────────────────────────

    public MergePDFPage verifyUrlContainsMerge() {
        ValidationHelper.verifyUrlContains("merge_pdf");
        return this;
    }

    public MergePDFPage verifyMergeButtonEnabled() {
        ValidationHelper.verifyElementEnabled(mergeButton);
        return this;
    }
}
