package com.ilovepdf.pages;

import org.openqa.selenium.By;

public class CompressPDFPage extends BasePage {

    private final By selectFilesButton = By.id("pickfiles");
    private final By fileInput = By.cssSelector("input[type='file']");
    private final By compressButton = By.id("processTask");
    private final By extremeCompression = By.cssSelector("[for='compression_level-extreme']");
    private final By recommendedCompression = By.cssSelector("[for='compression_level-recommended']");
    private final By lessCompression = By.cssSelector("[for='compression_level-low']");
    private final By pageTitle = By.cssSelector("h1.tool-title");

    public CompressPDFPage uploadFile(String fileName) {
        super.uploadFile(fileInput, fileName);
        return this;
    }

    public CompressPDFPage selectExtremeCompression() {
        click(extremeCompression);
        return this;
    }

    public CompressPDFPage selectRecommendedCompression() {
        click(recommendedCompression);
        return this;
    }

    public CompressPDFPage selectLessCompression() {
        click(lessCompression);
        return this;
    }

    public CompressPDFPage clickCompress() {
        click(compressButton);
        return this;
    }

    public boolean isPageDisplayed() { return isDisplayed(pageTitle); }
}
