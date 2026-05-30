package com.ilovepdf.helpers;

import org.openqa.selenium.By;

public class ScrollHelper {

    public static void scrollToElement(By locator) {
        JavaScriptHelper.scrollToElement(locator);
    }

    public static void scrollToTop() {
        JavaScriptHelper.scrollToTop();
    }

    public static void scrollToBottom() {
        JavaScriptHelper.scrollToBottom();
    }

    public static void scrollByPixels(int x, int y) {
        JavaScriptHelper.scrollByPixels(x, y);
    }
}
