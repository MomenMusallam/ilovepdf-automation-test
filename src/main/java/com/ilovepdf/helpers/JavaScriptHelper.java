package com.ilovepdf.helpers;

import com.ilovepdf.drivers.DriverManager;
import com.ilovepdf.enums.WaitStrategy;
import com.ilovepdf.utilities.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavaScriptHelper {

    private static JavascriptExecutor js() {
        return (JavascriptExecutor) DriverManager.getDriver();
    }

    public static void scrollToElement(By locator) {
        WebElement element = WaitUtil.waitForElement(locator, WaitStrategy.PRESENCE);
        js().executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", element);
    }

    public static void scrollToTop() {
        js().executeScript("window.scrollTo(0, 0);");
    }

    public static void scrollToBottom() {
        js().executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public static void scrollByPixels(int x, int y) {
        js().executeScript("window.scrollBy(" + x + "," + y + ");");
    }

    public static void highlight(By locator) {
        WebElement element = WaitUtil.waitForElement(locator, WaitStrategy.VISIBLE);
        js().executeScript("arguments[0].style.border='3px solid red';", element);
    }

    public static void setValue(By locator, String value) {
        WebElement element = WaitUtil.waitForElement(locator, WaitStrategy.PRESENCE);
        js().executeScript("arguments[0].value=arguments[1];", element, value);
    }

    public static String getPageTitle() {
        return (String) js().executeScript("return document.title;");
    }

    public static void refreshPage() {
        js().executeScript("history.go(0);");
    }
}
