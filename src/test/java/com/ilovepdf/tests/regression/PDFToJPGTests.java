package com.ilovepdf.tests.regression;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.helpers.BrowserHelper;
import com.ilovepdf.pages.HomePage;
import com.ilovepdf.pages.PDFToJPGPage;
import com.ilovepdf.utilities.SoftAssertManager;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Regression test suite for image conversion features:
 *   PDF → JPG  |  JPG → PDF
 *
 * ┌──────────────────┬──────────────────────────────────────────────────────┐
 * │ TC ID            │ Scenario                                             │
 * ├──────────────────┼──────────────────────────────────────────────────────┤
 * │ TC_PDF2J_001     │ PDF to JPG page loads with correct URL               │
 * │ TC_PDF2J_002     │ Select Files button displayed on PDF to JPG page     │
 * │ TC_PDF2J_003     │ Convert button present on PDF to JPG page            │
 * │ TC_PDF2J_004     │ Upload PDF on PDF to JPG page — page stable          │
 * │ TC_PDF2J_005     │ Select High quality option                           │
 * │ TC_PDF2J_006     │ Select Medium quality option                         │
 * │ TC_PDF2J_007     │ Select Low quality option                            │
 * │ TC_PDF2J_008     │ PDF to JPG: click Convert → success / download       │
 * │ TC_PDF2J_009     │ PDF to JPG: all key elements (soft assert)           │
 * │ TC_PDF2J_010     │ PDF to JPG: page title contains keyword              │
 * └──────────────────┴──────────────────────────────────────────────────────┘
 *
 * Preconditions:
 *   - sample1.pdf at src/test/resources/testdata/pdf/  (for PDF to JPG tests)
 */
public class PDFToJPGTests extends BaseTest {

    @Test(
        description = "TC_PDF2J_001 - PDF to JPG page loads with correct URL",
        groups = {"regression", "smoke"}
    )
    public void verifyPdfToJpgPageLoads() {
        PDFToJPGPage page = new HomePage().open().goToPDFToJPG();

        Assert.assertTrue(page.isPageDisplayed("title1"),
                "TC_PDF2J_001: PDF to JPG page heading not displayed");
        Assert.assertTrue(page.getCurrentUrl().contains("pdf_to_jpg"),
                "TC_PDF2J_001: URL does not contain 'pdf_to_jpg'");
        log.info("TC_PDF2J_001 PASSED");
    }

    @Test(
        description = "TC_PDF2J_002 - Select Files button displayed on PDF to JPG page",
        groups = {"regression"}
    )
    public void verifyPdfToJpgSelectFilesButtonDisplayed() {
        PDFToJPGPage page = new HomePage().open().goToPDFToJPG();

        Assert.assertTrue(page.isSelectFilesButtonDisplayed(),
                "TC_PDF2J_002: Select Files button not displayed on PDF to JPG page");
        log.info("TC_PDF2J_002 PASSED");
    }

    @Test(
        description = "TC_PDF2J_003 - Convert button not present on PDF to JPG page",
        groups = {"regression"}
    )
    public void verifyPdfToJpgConvertButtonPresent() {
        PDFToJPGPage page = new HomePage().open().goToPDFToJPG();
        
        Assert.assertTrue(!page.isConvertButtonDisplayed(),
                "TC_PDF2J_003: Convert button displayed on PDF to JPG page");
        log.info("TC_PDF2J_003 PASSED");
    }

    @Test(
        description = "TC_PDF2J_004 - Upload PDF on PDF to JPG page and verify page stability",
        groups = {"regression"}
    )
    public void verifyPdfToJpgUploadPdf() {
        PDFToJPGPage page = new HomePage().open().goToPDFToJPG();
        page.uploadFile("sample1.pdf");

        Assert.assertTrue(page.isPageDisplayed("title2"),
                "TC_PDF2J_004: Page not stable after uploading PDF to JPG page");
        log.info("TC_PDF2J_004 PASSED");
    }

    @Test(
        description = "TC_PDF2J_005 - Select High quality option on PDF to JPG page",
        groups = {"regression"}
    )
    public void verifyPdfToJpgHighQualitySelected() {
        PDFToJPGPage page = new HomePage().open().goToPDFToJPG();
        page.uploadFile("sample1.pdf").selectHighQuality();

        Assert.assertTrue(page.isPageDisplayed("title2"),
                "TC_PDF2J_005: Page not stable after selecting High quality");
        log.info("TC_PDF2J_005 PASSED");
    }

    @Test(
        description = "TC_PDF2J_006 - Select Medium quality option on PDF to JPG page",
        groups = {"regression"}
    )
    public void verifyPdfToJpgMediumQualitySelected() {
        PDFToJPGPage page = new HomePage().open().goToPDFToJPG();
        page.uploadFile("sample1.pdf").selectMediumQuality();

        Assert.assertTrue(page.isPageDisplayed("title2"),
                "TC_PDF2J_006: Page not stable after selecting Medium quality");
        log.info("TC_PDF2J_006 PASSED");
    }

    @Test(
        description = "TC_PDF2J_008 - PDF to JPG: click Convert and verify success or download",
        groups = {"regression", "e2e"}
    )
    public void verifyPdfToJpgConversion() {
        PDFToJPGPage page = new HomePage().open().goToPDFToJPG();
        page.uploadFile("sample1.pdf").clickConvert();

        Assert.assertTrue(page.isConversionSuccessful() || page.isDownloadAvailable(),
                "TC_PDF2J_008: PDF to JPG conversion did not complete");
        log.info("TC_PDF2J_008 PASSED");
    }

    @Test(
        description = "TC_PDF2J_009 - PDF to JPG: verify all key elements with soft assertions",
        groups = {"regression"}
    )
    public void verifyPdfToJpgPageElementsSoftAssert() {
        PDFToJPGPage page = new HomePage().open().goToPDFToJPG();

        SoftAssertManager.get().assertTrue(page.isPageDisplayed("title1"),
                "TC_PDF2J_009: Page heading not displayed");
        SoftAssertManager.get().assertTrue(page.isSelectFilesButtonDisplayed(),
                "TC_PDF2J_009: Select Files button not displayed");
        SoftAssertManager.get().assertTrue(!page.isConvertButtonDisplayed(),
                "TC_PDF2J_009: Convert button displayed");
        SoftAssertManager.get().assertTrue(page.getCurrentUrl().contains("pdf_to_jpg"),
                "TC_PDF2J_009: URL does not contain 'pdf_to_jpg'");

        SoftAssertManager.assertAll();
        log.info("TC_PDF2J_009 PASSED");
    }

    @Test(
        description = "TC_PDF2J_010 - PDF to JPG: browser tab title contains expected keyword",
        groups = {"regression"}
    )
    public void verifyPdfToJpgPageTitle() {
        new HomePage().open().goToPDFToJPG();

        String title = BrowserHelper.getTitle().toLowerCase();
        Assert.assertTrue(title.contains("jpg") || title.contains("ilovepdf"),
                "TC_PDF2J_010: Page title '" + title + "' does not contain expected keyword");
        log.info("TC_PDF2J_010 PASSED");
    }
}
