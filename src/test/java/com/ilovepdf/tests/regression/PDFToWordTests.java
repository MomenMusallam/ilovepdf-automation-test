package com.ilovepdf.tests.regression;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.pages.HomePage;
import com.ilovepdf.pages.PDFToWordPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PDFToWordTests extends BaseTest {

    @Test(description = "Verify PDF to Word page loads", groups = {"regression"})
    public void verifyPdfToWordPageLoads() {
        PDFToWordPage page = new HomePage().open().goToPDFToWord();
        Assert.assertTrue(page.isPageDisplayed(), "PDF to Word page not displayed");
    }

    @Test(description = "Verify PDF upload on PDF to Word conversion", groups = {"regression"})
    public void verifyUploadPdfForConversion() {
        PDFToWordPage page = new HomePage().open().goToPDFToWord();
        page.uploadFile("sample1.pdf");
        Assert.assertTrue(page.isPageDisplayed(), "Page not displayed after upload");
    }
}
