package com.ilovepdf.tests.regression;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.pages.CompressPDFPage;
import com.ilovepdf.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CompressPDFTests extends BaseTest {

    private final String testFileName = "sample1.pdf"; 
    private final String invalidFileName = "image.jpg"; 

    @Test(priority = 1, description = "Verify Compress page loads", groups = {"regression"})
    public void verifyCompressPageLoads() {
        Assert.assertTrue(new HomePage().open().goToCompressPDF().isPageDisplayed());
    }

    @Test(priority = 2, description = "Verify recommended compression", groups = {"regression"})
    public void verifyRecommendedCompression() {
        Assert.assertTrue(new HomePage().open().goToCompressPDF()
                .uploadFile(testFileName)
                .selectRecommendedCompression()
                .clickCompress()
                .isDownloadButtonVisible());
    }

    @Test(priority = 3, description = "Verify extreme compression", groups = {"regression"})
    public void verifyExtremeCompression() {
        Assert.assertTrue(new HomePage().open().goToCompressPDF()
                .uploadFile(testFileName)
                .selectExtremeCompression()
                .clickCompress()
                .isDownloadButtonVisible());
    }

    @Test(priority = 4, description = "Verify invalid file handling", groups = {"regression"})
    public void verifyInvalidFileTypeUpload() {
        CompressPDFPage compress = new HomePage().open().goToCompressPDF();
        compress.uploadFile(invalidFileName);
        Assert.assertTrue(compress.isPageDisplayed());
    }

    @Test(priority = 5, description = "Verify download trigger", groups = {"regression"})
    public void verifyCompressedFileDownloadedSuccessfully() {
        CompressPDFPage compress = new HomePage().open().goToCompressPDF();
        compress.uploadFile(testFileName).selectRecommendedCompression().clickCompress();
        
        Assert.assertTrue(compress.isDownloadButtonVisible());
        compress.clickDownload();
        Assert.assertTrue(compress.isDownloadTriggered());
    }
}
