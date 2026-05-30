package com.ilovepdf.utilities;

import com.ilovepdf.constants.FrameworkConstants;
import com.ilovepdf.drivers.DriverManager;
import com.ilovepdf.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtil {

    private WaitUtil() {}

    public static WebElement waitForElement(By locator, WaitStrategy strategy) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),
                Duration.ofSeconds(FrameworkConstants.getExplicitWait()));

        return switch (strategy) {
            case VISIBLE -> wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            case CLICKABLE -> wait.until(ExpectedConditions.elementToBeClickable(locator));
            case PRESENCE -> wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            default -> DriverManager.getDriver().findElement(locator);
        };
    }

    public static boolean waitForInvisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),
                Duration.ofSeconds(FrameworkConstants.getExplicitWait()));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean waitForUrlContains(String partialUrl) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),
                Duration.ofSeconds(FrameworkConstants.getExplicitWait()));
        return wait.until(ExpectedConditions.urlContains(partialUrl));
    }

    public static boolean waitForTitleContains(String title) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),
                Duration.ofSeconds(FrameworkConstants.getExplicitWait()));
        return wait.until(ExpectedConditions.titleContains(title));
    }

    public static <V> V fluentWait(ExpectedCondition<V> condition, int timeout, int polling) {
        FluentWait<WebDriver> wait = new FluentWait<>(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(polling))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        return wait.until(condition);
    }
}
