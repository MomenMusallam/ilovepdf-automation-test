package com.ilovepdf.helpers;

import com.ilovepdf.drivers.DriverManager;
import com.ilovepdf.enums.WaitStrategy;
import com.ilovepdf.utilities.WaitUtil;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ValidationHelper {

    public static void verifyText(By locator, String expected) {
        String actual = WaitUtil.waitForElement(locator, WaitStrategy.VISIBLE).getText();
        Assert.assertEquals(actual, expected, "Text mismatch");
    }

    public static void verifyTextContains(By locator, String partial) {
        String actual = WaitUtil.waitForElement(locator, WaitStrategy.VISIBLE).getText();
        Assert.assertTrue(actual.contains(partial), "Text does not contain: " + partial);
    }

    public static void verifyElementDisplayed(By locator) {
        Assert.assertTrue(ElementHelper.isDisplayed(locator), "Element not displayed: " + locator);
    }

    public static void verifyElementEnabled(By locator) {
        Assert.assertTrue(ElementHelper.isEnabled(locator), "Element not enabled: " + locator);
    }

    public static void verifyUrlContains(String partial) {
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(partial), "URL does not contain: " + partial);
    }

    public static void verifyTitle(String expected) {
        String actual = DriverManager.getDriver().getTitle();
        Assert.assertEquals(actual, expected, "Title mismatch");
    }

    public static void verifyAttribute(By locator, String attr, String expected) {
        String actual = ElementHelper.getAttribute(locator, attr);
        Assert.assertEquals(actual, expected, "Attribute mismatch");
    }
}
