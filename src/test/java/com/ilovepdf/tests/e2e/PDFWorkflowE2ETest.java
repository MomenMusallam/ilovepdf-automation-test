package com.ilovepdf.tests.e2e;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.pages.CompressPDFPage;
import com.ilovepdf.pages.HomePage;
import com.ilovepdf.pages.MergePDFPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PDFWorkflowE2ETest extends BaseTest {

    @Test(description = "E2E: Open home, navigate to Merge, upload two files, merge",
          groups = {"e2e"})
    public void e2eMergeWorkflow() {
        HomePage home = new HomePage().open();
        Assert.assertTrue(home.isMergeTileDisplayed());

        MergePDFPage merge = home.goToMergePDF();
        Assert.assertTrue(merge.isPageDisplayed());

        merge.uploadFile("sample1.pdf").uploadFile("sample2.pdf").clickMerge();
        Assert.assertTrue(merge.isMergeSuccessful() || merge.isDownloadAvailable());
    }

    @Test(description = "E2E: Compress a PDF with recommended settings",
          groups = {"e2e"})
    public void e2eCompressWorkflow() {
        CompressPDFPage compress = new HomePage().open().goToCompressPDF();
        Assert.assertTrue(compress.isPageDisplayed());

        compress.uploadFile("sample1.pdf")
                .selectRecommendedCompression()
                .clickCompress();
    }
}
