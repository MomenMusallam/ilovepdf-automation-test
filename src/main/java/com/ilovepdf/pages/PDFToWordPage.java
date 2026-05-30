package com.ilovepdf.pages;

import org.openqa.selenium.By;

public class PDFToWordPage extends BasePage {

    private final By fileInput = By.cssSelector("input[type='file']");
    private final By convertButton = By.id("processTask");
    private final By pageTitle = By.cssSelector("h1.tool-title");

    public PDFToWordPage uploadFile(String fileName) {
        super.uploadFile(fileInput, fileName);
        return this;
    }

    public PDFToWordPage clickConvert() {
        click(convertButton);
        return this;
    }

    public boolean isPageDisplayed() { return isDisplayed(pageTitle); }
}
