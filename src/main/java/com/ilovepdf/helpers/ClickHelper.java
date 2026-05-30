package com.ilovepdf.helpers;

import com.ilovepdf.drivers.DriverManager;
import com.ilovepdf.enums.WaitStrategy;
import com.ilovepdf.utilities.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ClickHelper {

    public static void click(By locator) {
        WaitUtil.waitForElement(locator, WaitStrategy.CLICKABLE).click();
    }

    public static void clickUsingJS(By locator) {
        WebElement element = WaitUtil.waitForElement(locator, WaitStrategy.PRESENCE);
        ((JavascriptExecutor) DriverManager.getDriver())
                .executeScript("arguments[0].click();", element);
    }

    public static void clickUsingActions(By locator) {
        WebElement element = WaitUtil.waitForElement(locator, WaitStrategy.CLICKABLE);
        new Actions(DriverManager.getDriver()).moveToElement(element).click().perform();
    }

    public static void doubleClick(By locator) {
        WebElement element = WaitUtil.waitForElement(locator, WaitStrategy.CLICKABLE);
        new Actions(DriverManager.getDriver()).doubleClick(element).perform();
    }

    public static void rightClick(By locator) {
        WebElement element = WaitUtil.waitForElement(locator, WaitStrategy.CLICKABLE);
        new Actions(DriverManager.getDriver()).contextClick(element).perform();
    }

    public static void hoverAndClick(By hover, By target) {
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(WaitUtil.waitForElement(hover, WaitStrategy.VISIBLE))
                .moveToElement(WaitUtil.waitForElement(target, WaitStrategy.CLICKABLE))
                .click().perform();
    }
}
