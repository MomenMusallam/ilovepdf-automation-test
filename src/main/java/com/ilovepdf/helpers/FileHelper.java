package com.ilovepdf.helpers;

import com.ilovepdf.constants.FrameworkConstants;
import com.ilovepdf.enums.WaitStrategy;
import com.ilovepdf.exceptions.FrameworkException;
import com.ilovepdf.utilities.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class FileHelper {

    public static void uploadFile(By locator, String fileName) {
        String absolutePath = FrameworkConstants.getPdfTestDataPath() + fileName;
        WebElement element = WaitUtil.waitForElement(locator, WaitStrategy.PRESENCE);
        element.sendKeys(absolutePath);
    }

    public static void uploadFileAbsolute(By locator, String absolutePath) {
        WebElement element = WaitUtil.waitForElement(locator, WaitStrategy.PRESENCE);
        element.sendKeys(absolutePath);
    }

    public static boolean isFileDownloaded(String fileName, int timeoutSeconds) {
        File file = new File(FrameworkConstants.getDownloadFolder() + fileName);
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < TimeUnit.SECONDS.toMillis(timeoutSeconds)) {
            if (file.exists() && file.length() > 0) return true;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return false;
    }

    public static void deleteFile(String absolutePath) {
        File file = new File(absolutePath);
        if (file.exists() && !file.delete()) {
            throw new FrameworkException("Failed to delete: " + absolutePath);
        }
    }

    public static long getFileSize(String absolutePath) {
        File file = new File(absolutePath);
        if (!file.exists()) throw new FrameworkException("File not found: " + absolutePath);
        return file.length();
    }
}
