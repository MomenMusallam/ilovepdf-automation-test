package com.ilovepdf.tests.regression;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.helpers.BrowserHelper;
import com.ilovepdf.pages.HomePage;
import com.ilovepdf.pages.PDFToExcelPage;
import com.ilovepdf.pages.PDFToPowerPointPage;
import com.ilovepdf.pages.PDFToWordPage;
import com.ilovepdf.utilities.SoftAssertManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PDFToPowerPointTests extends BaseTest {

    // ═════════════════════════════════════════════════════════════════════════
    // PDF → POWERPOINT
    // ═════════════════════════════════════════════════════════════════════════

    @Test(
        description = "TC_PDF2P_001 - PDF to PowerPoint page loads with correct URL",
        groups = {"regression", "smoke"}
    )
    public void verifyPdfToPptPageLoads() {
        PDFToPowerPointPage page = new HomePage().open().goToPDFToPowerPoint();

        Assert.assertTrue(page.isPageDisplayed("title1"),
                "TC_PDF2P_001: PDF to PowerPoint page heading not displayed");
        Assert.assertTrue(page.getCurrentUrl().contains("pdf_to_powerpoint"),
                "TC_PDF2P_001: URL does not contain 'pdf_to_powerpoint'");
        log.info("TC_PDF2P_001 PASSED");
    }

    @Test(
        description = "TC_PDF2P_002 - Select Files button displayed on PDF to PowerPoint page",
        groups = {"regression"}
    )
    public void verifyPdfToPptSelectFilesButtonDisplayed() {
        PDFToPowerPointPage page = new HomePage().open().goToPDFToPowerPoint();

        Assert.assertTrue(page.isSelectFilesButtonDisplayed(),
                "TC_PDF2P_002: Select Files button not displayed on PDF to PowerPoint page");
        log.info("TC_PDF2P_002 PASSED");
    }

    @Test(
        description = "TC_PDF2P_003 - Convert button present on PDF to PowerPoint page",
        groups = {"regression"}
    )
    public void verifyPdfToPptConvertButtonPresent() {
        PDFToPowerPointPage page = new HomePage().open().goToPDFToPowerPoint();
        page.uploadFile("sample1.pdf");
        
        Assert.assertTrue(page.isConvertButtonDisplayed(),
                "TC_PDF2P_003: Convert button not displayed on PDF to PowerPoint page");
        log.info("TC_PDF2P_003 PASSED");
    }

	@Test(
        description = "TC_PDF2P_004 - Upload PDF on PDF to PowerPoint page and verify stability",
        groups = {"regression"}
    )
    public void verifyPdfToPptUploadPdf() {
        PDFToPowerPointPage page = new HomePage().open().goToPDFToPowerPoint();
        page.uploadFile("sample1.pdf");

        Assert.assertTrue(page.isPageDisplayed("title2"),
                "TC_PDF2P_004: Page not stable after file upload on PDF to PowerPoint page");
        log.info("TC_PDF2P_004 PASSED");
    }

    @Test(
        description = "TC_PDF2P_005 - PDF to PowerPoint: click Convert and verify success or download",
        groups = {"regression", "e2e"}
    )
    public void verifyPdfToPptConversion() {
        PDFToPowerPointPage page = new HomePage().open().goToPDFToPowerPoint();
        page.uploadFile("sample1.pdf").clickConvert();

        Assert.assertTrue(page.isConversionSuccessful() || page.isDownloadAvailable(),
                "TC_PDF2P_005: PDF to PowerPoint conversion did not complete");
        log.info("TC_PDF2P_005 PASSED");
    }

    @Test(
        description = "TC_PDF2P_006 - PDF to PowerPoint: verify all key elements with soft assertions",
        groups = {"regression"}
    )
    public void verifyPdfToPptPageElementsSoftAssert() {
        PDFToPowerPointPage page = new HomePage().open().goToPDFToPowerPoint();

        SoftAssertManager.get().assertTrue(page.isPageDisplayed("title1"),
                "TC_PDF2P_006: Page heading not displayed");
        SoftAssertManager.get().assertTrue(page.isSelectFilesButtonDisplayed(),
                "TC_PDF2P_006: Select Files button not displayed");
        SoftAssertManager.get().assertTrue(!page.isConvertButtonDisplayed(),
                "TC_PDF2P_006: Convert button not displayed");
        SoftAssertManager.get().assertTrue(page.getCurrentUrl().contains("pdf_to_powerpoint"),
                "TC_PDF2P_006: URL does not contain 'pdf_to_powerpoint'");

        SoftAssertManager.assertAll();
        log.info("TC_PDF2P_006 PASSED");
    }
}
