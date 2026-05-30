package com.ilovepdf.tests.regression;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.pages.CompressPDFPage;
import com.ilovepdf.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CompressPDFTests extends BaseTest {

    @Test(description = "Verify Compress page loads correctly", groups = {"regression"})
    public void verifyCompressPageLoads() {
        CompressPDFPage compress = new HomePage().open().goToCompressPDF();
        Assert.assertTrue(compress.isPageDisplayed(), "Compress page not displayed");
    }

    @Test(description = "Verify recommended compression option", groups = {"regression"})
    public void verifyRecommendedCompression() {
        CompressPDFPage compress = new HomePage().open().goToCompressPDF();
        compress.uploadFile("sample1.pdf").selectRecommendedCompression();
        Assert.assertTrue(compress.isPageDisplayed(), "Page not displayed");
    }

    @Test(description = "Verify extreme compression option", groups = {"regression"})
    public void verifyExtremeCompression() {
        CompressPDFPage compress = new HomePage().open().goToCompressPDF();
        compress.uploadFile("sample1.pdf").selectExtremeCompression();
        Assert.assertTrue(compress.isPageDisplayed(), "Page not displayed");
    }
}
