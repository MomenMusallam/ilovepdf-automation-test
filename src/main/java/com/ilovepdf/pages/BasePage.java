package com.ilovepdf.pages;

import com.ilovepdf.drivers.DriverManager;
import com.ilovepdf.helpers.*;
import com.ilovepdf.utilities.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final Logger log;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        this.log = LoggerUtil.getLogger(this.getClass());
    }

    protected void click(By locator) { ClickHelper.click(locator); }
    protected void clickJS(By locator) { ClickHelper.clickUsingJS(locator); }
    protected void type(By locator, String text) { InputHelper.type(locator, text); }
    protected void clearAndType(By locator, String text) { InputHelper.clearAndType(locator, text); }
    protected String getText(By locator) { return ElementHelper.getText(locator); }
    protected boolean isDisplayed(By locator) { return ElementHelper.isDisplayed(locator); }
    protected void scrollTo(By locator) { ScrollHelper.scrollToElement(locator); }
    protected void uploadFile(By locator, String fileName) { FileHelper.uploadFile(locator, fileName); }
    protected String currentUrl() { return BrowserHelper.getCurrentUrl(); }
    protected String pageTitle() { return BrowserHelper.getTitle(); }
}
