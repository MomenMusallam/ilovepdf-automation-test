package com.ilovepdf.helpers;

import com.ilovepdf.drivers.DriverManager;
import com.ilovepdf.enums.WaitStrategy;
import com.ilovepdf.utilities.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FrameHelper {

    public static void switchToFrame(int index) {
        DriverManager.getDriver().switchTo().frame(index);
    }

    public static void switchToFrame(String nameOrId) {
        DriverManager.getDriver().switchTo().frame(nameOrId);
    }

    public static void switchToFrame(By locator) {
        WebElement frame = WaitUtil.waitForElement(locator, WaitStrategy.PRESENCE);
        DriverManager.getDriver().switchTo().frame(frame);
    }

    public static void switchToDefaultContent() {
        DriverManager.getDriver().switchTo().defaultContent();
    }

    public static void switchToParentFrame() {
        DriverManager.getDriver().switchTo().parentFrame();
    }
}
