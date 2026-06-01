package com.ilovepdf.tests.regression;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.pages.PowerPointToPDFPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PowerPointToPDFTests extends BaseTest {
    private static final String PPTX_FILE    = "sample.pptx";
    private static final String PPT_FILE     = "sample.ppt";
    private static final String LARGE_PPTX   = "large.pptx";
    private static final String PDF_FILE     = "sample.pdf";
    private static final String PNG_FILE     = "sample.png";

    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Verify that the user can validate the URL")
    public void testPageUrl() {
        PowerPointToPDFPage page = new PowerPointToPDFPage();
        page.navigateTo();
        Assert.assertTrue(page.getCurrentUrl().contains("powerpoint_to_pdf"), "URL mismatch");
    }

    @Test(priority = 2, groups = {"regression"}, description = "Verify that the user can convert a .ppt file")
    public void testConvertPptToPdf() {
        PowerPointToPDFPage page = new PowerPointToPDFPage();
        page.navigateTo().uploadFile(PPT_FILE).clickConvert();
        Assert.assertTrue(page.isDownloadButtonVisible(), "Download button not visible after .ppt conversion");
    }

    @Test(priority = 3, groups = {"regression"}, description = "Verify that the user can convert a .pptx file")
    public void testConvertPptxToPdf() {
        PowerPointToPDFPage page = new PowerPointToPDFPage();
        page.navigateTo().uploadFile(PPTX_FILE).clickConvert();
        Assert.assertTrue(page.isDownloadButtonVisible(), "Download button not visible after .pptx conversion");
    }

    @Test(priority = 4, groups = {"regression"}, description = "Verify error on invalid image upload")
    public void testDragAndDropImage() {
        PowerPointToPDFPage page = new PowerPointToPDFPage();
        page.navigateTo().uploadFile(PNG_FILE);
        Assert.assertTrue(page.isUploadErrorDisplayed(), "Error message not displayed for image");
    }

    @Test(priority = 5, groups = {"regression"}, description = "Verify error on invalid PDF upload")
    public void testDragAndDropPdf() {
        PowerPointToPDFPage page = new PowerPointToPDFPage();
        page.navigateTo().uploadFile(PDF_FILE);
        Assert.assertTrue(page.isUploadErrorDisplayed(), "Error message not displayed for PDF");
    }

    @Test(priority = 6, groups = {"regression"}, description = "Verify upload same file twice")
    public void testUploadSameFileTwice() {
        PowerPointToPDFPage page = new PowerPointToPDFPage();
        page.navigateTo().uploadFile(PPTX_FILE).uploadFile(PPTX_FILE);
        Assert.assertTrue(page.isConvertButtonVisible(), "Convert button not visible after duplicate upload");
    }

    @Test(priority = 7, groups = {"regression"}, description = "Verify convert button visibility after upload")
    public void testConvertButtonVisibleAfterUpload() {
        PowerPointToPDFPage page = new PowerPointToPDFPage();
        page.navigateTo().uploadFile(PPTX_FILE);
        Assert.assertTrue(page.isConvertButtonVisible(), "Convert button not visible");
    }

    @Test(priority = 8, groups = {"regression"}, description = "Verify no conversion without upload")
    public void testConvertWithoutUpload() {
        PowerPointToPDFPage page = new PowerPointToPDFPage();
        page.navigateTo();
        Assert.assertFalse(page.isConvertButtonVisible(), "Convert button visible without file");
    }

    @Test(priority = 9, groups = {"regression"}, description = "Verify large file conversion")
    public void testConvertLargePptx() {
        PowerPointToPDFPage page = new PowerPointToPDFPage();
        page.navigateTo().uploadFile(LARGE_PPTX).clickConvert();
        Assert.assertTrue(page.isDownloadButtonVisible(), "Large file conversion failed");
    }

    @Test(priority = 10, groups = {"regression"}, description = "Verify redirect after conversion")
    public void testRedirectAfterConvert() {
        PowerPointToPDFPage page = new PowerPointToPDFPage();
        page.navigateTo().uploadFile(PPTX_FILE).clickConvert();
        Assert.assertTrue(page.isDownloadButtonVisible(), "Not on download page");
    }
    
    @Test(priority = 11, groups = {"regression"}, description = "Verify simple PPT conversion flow")
    public void testPptConversionFlow() {
        PowerPointToPDFPage page = new PowerPointToPDFPage();  
        page.navigateTo().uploadFile(PPT_FILE).clickConvert();
        Assert.assertTrue(page.isDownloadButtonVisible(), "Conversion flow failed");
    }
    
    @Test(priority = 12, groups = {"regression"}, description = "Verify that uploading an empty file is handled")
    public void testEmptyFileUpload() throws InterruptedException {
        PowerPointToPDFPage page = new PowerPointToPDFPage();
        page.navigateTo().uploadFile("empty.ppt");
                Thread.sleep(3000);        
        boolean isErrorVisible = page.isUploadErrorDisplayed();
        
        Assert.assertTrue(isErrorVisible, 
          "empty file was uploaded here");
    }
    
}
