package com.ilovepdf.tests.regression;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.pages.HomePage;
import com.ilovepdf.pages.MergePDFPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MergePDFTests extends BaseTest {

    @Test(description = "Verify Merge PDF page loads correctly", groups = {"regression"})
    public void verifyMergePageLoads() {
        MergePDFPage merge = new HomePage().open().goToMergePDF();
        Assert.assertTrue(merge.isPageDisplayed(), "Merge page not displayed");
        Assert.assertTrue(merge.isSelectFilesButtonDisplayed(), "Select files button missing");
    }

    @Test(description = "Verify uploading a single PDF works", groups = {"regression"})
    public void verifyUploadSinglePDF() {
        MergePDFPage merge = new HomePage().open().goToMergePDF();
        merge.uploadFile("sample1.pdf");
        Assert.assertTrue(merge.isPageDisplayed(), "Page not displayed after upload");
    }

    @Test(description = "Verify merging two PDFs", groups = {"regression", "e2e"})
    public void verifyMergeTwoPDFs() {
        MergePDFPage merge = new HomePage().open().goToMergePDF();
        merge.uploadFile("sample1.pdf")
             .uploadFile("sample2.pdf")
             .clickMerge();
        Assert.assertTrue(merge.isMergeSuccessful() || merge.isDownloadAvailable(),
                "Merge did not complete");
    }
}
