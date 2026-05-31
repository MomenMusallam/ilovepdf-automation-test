package com.ilovepdf.pages;

import com.ilovepdf.constants.FrameworkConstants;
import com.ilovepdf.drivers.DriverManager;
import com.ilovepdf.enums.WaitStrategy;
import com.ilovepdf.exceptions.FrameworkException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public final class CompressPDFPage {

    private final By fileInput = By.cssSelector("input[type='file']");
    private final By compressButton = By.id("processTask");
    private final By extremeCompression = By.cssSelector("li[data-value='extreme']");
    private final By recommendedCompression = By.cssSelector("li[data-value='recommended']");
    private final By downloadButton = By.id("pickfiles");

    private WebElement getElement(By by, WaitStrategy waitStrategy) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitWait()));
        try {
            if (waitStrategy == WaitStrategy.PRESENCE) return wait.until(ExpectedConditions.presenceOfElementLocated(by));
            if (waitStrategy == WaitStrategy.VISIBLE) return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            if (waitStrategy == WaitStrategy.CLICKABLE) return wait.until(ExpectedConditions.elementToBeClickable(by));
            if (waitStrategy == WaitStrategy.NONE) return DriverManager.getDriver().findElement(by);
        } catch (Exception e) {
            throw new FrameworkException("   element not found : " + waitStrategy, e);
        }
        return DriverManager.getDriver().findElement(by);
    }

    public CompressPDFPage uploadFile(String fileName) {
        String filePath = FrameworkConstants.getPdfTestDataPath() + fileName;
        WebElement inputElement = getElement(fileInput, WaitStrategy.PRESENCE);
        inputElement.sendKeys(filePath);
        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", inputElement);
            Thread.sleep(2000); 
        } catch (Exception e) { }
        return this;
    }

    public CompressPDFPage selectRecommendedCompression() {
        getElement(recommendedCompression, WaitStrategy.CLICKABLE).click();
        return this;
    }

    public CompressPDFPage selectExtremeCompression() {
        getElement(extremeCompression, WaitStrategy.CLICKABLE).click();
        return this;
    }

    public CompressPDFPage clickCompress() {
        getElement(compressButton, WaitStrategy.CLICKABLE).click();
        return this;
    }

    public boolean isPageDisplayed() { 
        return DriverManager.getDriver().getTitle().contains("Compress PDF"); 
    }

    public boolean isDownloadButtonVisible() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(60));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(downloadButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickDownload() {
        getElement(downloadButton, WaitStrategy.CLICKABLE).click();
    }

    public boolean isDownloadTriggered() {
        return isDownloadButtonVisible();
    }
}
