package com.ilovepdf.helpers;

import com.ilovepdf.enums.WaitStrategy;
import com.ilovepdf.utilities.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class InputHelper {

    public static void type(By locator, String text) {
        WaitUtil.waitForElement(locator, WaitStrategy.VISIBLE).sendKeys(text);
    }

    public static void clearAndType(By locator, String text) {
        WebElement element = WaitUtil.waitForElement(locator, WaitStrategy.VISIBLE);
        element.clear();
        element.sendKeys(text);
    }

    public static void sendKeysSlowly(By locator, String text, int delayMillis) {
        WebElement element = WaitUtil.waitForElement(locator, WaitStrategy.VISIBLE);
        for (char c : text.toCharArray()) {
            element.sendKeys(String.valueOf(c));
            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void clearField(By locator) {
        WaitUtil.waitForElement(locator, WaitStrategy.VISIBLE).clear();
    }
}
