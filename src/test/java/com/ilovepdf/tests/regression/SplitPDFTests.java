package com.ilovepdf.tests.regression;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.pages.HomePage;
import com.ilovepdf.pages.SplitPDFPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SplitPDFTests extends BaseTest {

    @Test(description = "Verify Split PDF page loads correctly", groups = {"regression"})
    public void verifySplitPageLoads() {
        SplitPDFPage split = new HomePage().open().goToSplitPDF();
        Assert.assertTrue(split.isPageDisplayed(), "Split page not displayed");
    }

    @Test(description = "Verify split by ranges mode", groups = {"regression"})
    public void verifySplitByRanges() {
        SplitPDFPage split = new HomePage().open().goToSplitPDF();
        split.uploadFile("sample1.pdf").selectRangeMode();
        Assert.assertTrue(split.isPageDisplayed(), "Split page not displayed");
    }
}
