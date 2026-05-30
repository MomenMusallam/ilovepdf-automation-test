package com.ilovepdf.helpers;

import com.ilovepdf.constants.FrameworkConstants;
import com.ilovepdf.drivers.DriverManager;
import com.ilovepdf.exceptions.FrameworkException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotHelper {

    public static String captureScreenshot(String testName) {
        TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = FrameworkConstants.getScreenshotFolder() + testName + "_" + timestamp + ".png";
        File dest = new File(filePath);
        try {
            FileUtils.copyFile(source, dest);
        } catch (IOException e) {
            throw new FrameworkException("Failed to save screenshot", e);
        }
        return filePath;
    }

    public static String captureScreenshotBase64() {
        TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
        return ts.getScreenshotAs(OutputType.BASE64);
    }
}
