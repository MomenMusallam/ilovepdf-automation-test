package com.ilovepdf.pages;

import org.openqa.selenium.By;

public final class PowerPointToPDFPage extends BasePage {

    private static final String PAGE_URL = "https://www.ilovepdf.com/powerpoint_to_pdf";

    private final By fileInput     = By.cssSelector("input[type='file']");
    private final By convertButton = By.id("processTask");
    private final By downloadButton = By.cssSelector("a.downloader__btn.active, a[href*='download']");
    private final By uploadErrorMsg = By.xpath("//span[contains(text(),'not allowed')] | //div[contains(@class,'toast')]");

    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    public PowerPointToPDFPage navigateTo() {
        driver.get(PAGE_URL);
        return this;
    }

    public PowerPointToPDFPage uploadFile(String fileName) {
        super.uploadFile(fileInput, fileName); 
        return this;
    }

    public PowerPointToPDFPage clickConvert() {
        click(convertButton);
        return this;
    }

    public boolean isPageDisplayed() {
        return isDisplayed(fileInput); 
    }

    public boolean isDownloadButtonVisible() {
        return isDisplayed(downloadButton);
    }

    public boolean isUploadErrorDisplayed() {
        return isDisplayed(uploadErrorMsg);
    }

    public boolean isConvertButtonVisible() {
        return isDisplayed(convertButton);
    }
}