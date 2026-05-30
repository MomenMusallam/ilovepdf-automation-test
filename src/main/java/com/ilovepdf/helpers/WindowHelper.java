package com.ilovepdf.helpers;

import com.ilovepdf.drivers.DriverManager;
import com.ilovepdf.exceptions.FrameworkException;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Set;

public class WindowHelper {

    public static void switchToNewTab() {
        WebDriver driver = DriverManager.getDriver();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    public static void switchToWindowByTitle(String title) {
        WebDriver driver = DriverManager.getDriver();
        Set<String> handles = driver.getWindowHandles();
        for (String h : handles) {
            driver.switchTo().window(h);
            if (driver.getTitle().equals(title)) return;
        }
        throw new FrameworkException("Window with title not found: " + title);
    }

    public static void switchToMainWindow() {
        WebDriver driver = DriverManager.getDriver();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    public static void closeCurrentTab() {
        DriverManager.getDriver().close();
        switchToMainWindow();
    }

    public static int getWindowCount() {
        return DriverManager.getDriver().getWindowHandles().size();
    }
}
