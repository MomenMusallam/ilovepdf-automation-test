package com.ilovepdf.pages;

import org.openqa.selenium.By;

public class SplitPDFPage extends BasePage {

    private final By selectFilesButton = By.id("pickfiles");
    private final By fileInput = By.cssSelector("input[type='file']");
    private final By splitButton = By.id("processTask");
    private final By rangeOption = By.cssSelector("[data-mode='ranges']");
    private final By pagesOption = By.cssSelector("[data-mode='pages']");
    private final By pageTitle = By.cssSelector("h1.tool-title");

    public SplitPDFPage uploadFile(String fileName) {
        super.uploadFile(fileInput, fileName);
        return this;
    }

    public SplitPDFPage selectRangeMode() {
        click(rangeOption);
        return this;
    }

    public SplitPDFPage selectPagesMode() {
        click(pagesOption);
        return this;
    }

    public SplitPDFPage clickSplit() {
        click(splitButton);
        return this;
    }

    public boolean isPageDisplayed() { return isDisplayed(pageTitle); }
}
