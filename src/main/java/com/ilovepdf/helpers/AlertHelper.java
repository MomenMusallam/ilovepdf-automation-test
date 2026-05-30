package com.ilovepdf.helpers;

import com.ilovepdf.constants.FrameworkConstants;
import com.ilovepdf.drivers.DriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertHelper {

    private static Alert waitForAlert() {
        return new WebDriverWait(DriverManager.getDriver(),
                Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
                .until(ExpectedConditions.alertIsPresent());
    }

    public static void acceptAlert() {
        waitForAlert().accept();
    }

    public static void dismissAlert() {
        waitForAlert().dismiss();
    }

    public static String getAlertText() {
        return waitForAlert().getText();
    }

    public static void sendKeysToAlert(String text) {
        waitForAlert().sendKeys(text);
    }
}
