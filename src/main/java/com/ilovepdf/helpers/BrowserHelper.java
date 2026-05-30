package com.ilovepdf.helpers;

import com.ilovepdf.drivers.DriverManager;

public class BrowserHelper {

    public static void navigateTo(String url) {
        DriverManager.getDriver().get(url);
    }

    public static void refresh() {
        DriverManager.getDriver().navigate().refresh();
    }

    public static void back() {
        DriverManager.getDriver().navigate().back();
    }

    public static void forward() {
        DriverManager.getDriver().navigate().forward();
    }

    public static String getCurrentUrl() {
        return DriverManager.getDriver().getCurrentUrl();
    }

    public static String getTitle() {
        return DriverManager.getDriver().getTitle();
    }

    public static void maximize() {
        DriverManager.getDriver().manage().window().maximize();
    }

    public static void deleteCookies() {
        DriverManager.getDriver().manage().deleteAllCookies();
    }
}
