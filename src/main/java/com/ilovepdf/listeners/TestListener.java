package com.ilovepdf.listeners;

import com.aventstack.extentreports.Status;
import com.ilovepdf.helpers.ScreenshotHelper;
import com.ilovepdf.reports.ExtentReportManager;
import com.ilovepdf.utilities.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger log = LoggerUtil.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        log.info("=== Test Suite Started: {} ===", context.getName());
        ExtentReportManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info(">>> Test Started: {}", result.getMethod().getMethodName());
        ExtentReportManager.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("PASS: {}", result.getMethod().getMethodName());
        ExtentReportManager.getTest().log(Status.PASS, "Test Passed Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("FAIL: {}", result.getMethod().getMethodName(), result.getThrowable());
        String screenshotPath = ScreenshotHelper.captureScreenshot(result.getMethod().getMethodName());
        ExtentReportManager.getTest().log(Status.FAIL, result.getThrowable());
        try {
            ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            log.warn("Failed to attach screenshot: {}", e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("SKIP: {}", result.getMethod().getMethodName());
        ExtentReportManager.getTest().log(Status.SKIP, result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("=== Test Suite Finished: {} ===", context.getName());
        ExtentReportManager.flushReport();
    }
}
