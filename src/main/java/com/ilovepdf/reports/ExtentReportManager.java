package com.ilovepdf.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ilovepdf.configuration.ConfigReader;
import com.ilovepdf.constants.FrameworkConstants;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class ExtentReportManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> testThreadLocal = new ThreadLocal<>();

    private ExtentReportManager() {}

    public static ExtentReports getInstance() {
        if (extent == null) {
            initReport();
        }
        return extent;
    }

    private static void initReport() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = FrameworkConstants.getExtentReportFolder() + "ExtentReport_" + timestamp + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("iLovePDF Automation Report");
        spark.config().setReportName("Test Execution Report");
        spark.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", ConfigReader.get("environment"));
        extent.setSystemInfo("Browser", ConfigReader.get("browser"));
        extent.setSystemInfo("Base URL", ConfigReader.get("base.url"));
    }

    public static ExtentTest createTest(String testName, String description) {
        ExtentTest test = getInstance().createTest(testName, description);
        testThreadLocal.set(test);
        return test;
    }

    public static ExtentTest getTest() {
        return testThreadLocal.get();
    }

    public static void removeTest() {
        testThreadLocal.remove();
    }

    public static void flushReport() {
        if (extent != null) extent.flush();
    }
}
