package com.ilovepdf.helpers;

import com.ilovepdf.drivers.DriverManager;
import com.ilovepdf.enums.WaitStrategy;
import com.ilovepdf.utilities.LoggerUtil;
import com.ilovepdf.utilities.WaitUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementHelper {

    private static final Logger log = LoggerUtil.getLogger(ElementHelper.class);

    public static void click(By locator) {
        WaitUtil.waitForElement(locator, WaitStrategy.CLICKABLE).click();
        log.info("Clicked element: {}", locator);
    }

    public static void type(By locator, String text) {
        WebElement element = WaitUtil.waitForElement(locator, WaitStrategy.VISIBLE);
        element.sendKeys(text);
        log.info("Typed '{}' into: {}", text, locator);
    }

    public static void clearAndType(By locator, String text) {
        WebElement element = WaitUtil.waitForElement(locator, WaitStrategy.VISIBLE);
        element.clear();
        element.sendKeys(text);
        log.info("Cleared and typed '{}' into: {}", text, locator);
    }

    public static String getText(By locator) {
        return WaitUtil.waitForElement(locator, WaitStrategy.VISIBLE).getText();
    }

    public static String getAttribute(By locator, String attr) {
        return WaitUtil.waitForElement(locator, WaitStrategy.PRESENCE).getAttribute(attr);
    }

    public static boolean isDisplayed(By locator) {
        try {
            return WaitUtil.waitForElement(locator, WaitStrategy.VISIBLE).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isEnabled(By locator) {
        return WaitUtil.waitForElement(locator, WaitStrategy.PRESENCE).isEnabled();
    }

    public static boolean isSelected(By locator) {
        return WaitUtil.waitForElement(locator, WaitStrategy.PRESENCE).isSelected();
    }

    public static List<WebElement> getElements(By locator) {
        return DriverManager.getDriver().findElements(locator);
    }

    public static int getElementsCount(By locator) {
        return getElements(locator).size();
    }
}
