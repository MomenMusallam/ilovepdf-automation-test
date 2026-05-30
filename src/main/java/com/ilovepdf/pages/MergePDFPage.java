package com.ilovepdf.pages;

import org.openqa.selenium.By;

public class MergePDFPage extends BasePage {

    private final By selectFilesButton = By.id("pickfiles");
    private final By fileInput = By.cssSelector("input[type='file']");
    private final By mergeButton = By.id("processTask");
    private final By downloadButton = By.id("pickfiles");
    private final By successMessage = By.cssSelector(".tools__success__title");
    private final By pageTitle = By.cssSelector("h1.tool-title");

    public MergePDFPage uploadFile(String fileName) {
        super.uploadFile(fileInput, fileName);
        log.info("Uploaded file: {}", fileName);
        return this;
    }

    public MergePDFPage clickMerge() {
        click(mergeButton);
        return this;
    }

    public boolean isPageDisplayed() { return isDisplayed(pageTitle); }
    public boolean isSelectFilesButtonDisplayed() { return isDisplayed(selectFilesButton); }
    public boolean isMergeSuccessful() { return isDisplayed(successMessage); }
    public boolean isDownloadAvailable() { return isDisplayed(downloadButton); }
}
