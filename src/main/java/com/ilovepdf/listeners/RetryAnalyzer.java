package com.ilovepdf.listeners;

import com.ilovepdf.constants.FrameworkConstants;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int counter = 0;
    private static final int MAX_RETRY = FrameworkConstants.getRetryCount();

    @Override
    public boolean retry(ITestResult result) {
        if (counter < MAX_RETRY) {
            counter++;
            return true;
        }
        return false;
    }
}
