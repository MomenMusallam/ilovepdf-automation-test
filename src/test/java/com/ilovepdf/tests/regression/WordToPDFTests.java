package com.ilovepdf.tests.regression;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.helpers.BrowserHelper;
import com.ilovepdf.pages.HomePage;
import com.ilovepdf.pages.WordToPDFPage;
import com.ilovepdf.utilities.SoftAssertManager;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Regression tests: Word → PDF | PowerPoint → PDF | Excel → PDF
 *
 * KEY FIX applied across all three tools:
 *   TC_*_003 now asserts Convert button is NOT visible before upload.
 *   TC_*_003b (new) asserts Convert button APPEARS after upload.
 */
public class WordToPDFTests extends BaseTest {

    // ═════════════════════════════════════════════════════════════════════════
    // WORD → PDF
    // ═════════════════════════════════════════════════════════════════════════

    @Test(description = "TC_W2PDF_001 - Word to PDF page loads with correct URL",
          groups = {"regression", "smoke"})
    public void verifyWordToPdfPageLoads() {
        WordToPDFPage page = new HomePage().open().goToWordToPDF();
        Assert.assertTrue(page.isPageDisplayed("title1"),
                "TC_W2PDF_001: Word to PDF page heading not displayed");
        Assert.assertTrue(page.getCurrentUrl().contains("word_to_pdf"),
                "TC_W2PDF_001: URL does not contain 'word_to_pdf'");
        log.info("TC_W2PDF_001 PASSED");
    }

    @Test(description = "TC_W2PDF_002 - Select Files button displayed on Word to PDF page",
          groups = {"regression"})
    public void verifyWordToPdfSelectFilesButtonDisplayed() {
        WordToPDFPage page = new HomePage().open().goToWordToPDF();
        Assert.assertTrue(page.isSelectFilesButtonDisplayed(),
                "TC_W2PDF_002: Select Files button not displayed on Word to PDF page");
        log.info("TC_W2PDF_002 PASSED");
    }

    // ── TC_W2PDF_003  FIXED ──────────────────────────────────────────────────
    @Test(description = "TC_W2PDF_003 - Convert button is NOT visible before any file is uploaded",
          groups = {"regression"})
    public void verifyWordToPdfConvertButtonNotVisibleBeforeUpload() {
        WordToPDFPage page = new HomePage().open().goToWordToPDF();
        Assert.assertFalse(page.isConvertButtonDisplayed(),
                "TC_W2PDF_003: Convert button should NOT be visible before file upload");
        log.info("TC_W2PDF_003 PASSED");
    }

    // ── TC_W2PDF_003b  NEW ───────────────────────────────────────────────────
    @Test(description = "TC_W2PDF_003b - Convert button appears after uploading a .docx file",
          groups = {"regression"})
    public void verifyWordToPdfConvertButtonAppearsAfterUpload() {
        WordToPDFPage page = new HomePage().open().goToWordToPDF();
        Assert.assertFalse(page.isConvertButtonDisplayed(),
                "TC_W2PDF_003b: Pre-condition failed — Convert button visible before upload");
        page.uploadFile("sample1.docx");
        Assert.assertTrue(page.isConvertButtonDisplayed(),
                "TC_W2PDF_003b: Convert button did not appear after uploading a .docx file");
        log.info("TC_W2PDF_003b PASSED");
    }

    @Test(description = "TC_W2PDF_004 - Upload .docx file on Word to PDF page and verify stability",
          groups = {"regression"})
    public void verifyWordToPdfUploadDocx() {
        WordToPDFPage page = new HomePage().open().goToWordToPDF();
        page.uploadFile("sample1.docx");
        Assert.assertTrue(page.isPageDisplayed("title2"),
                "TC_W2PDF_004: Page not stable after uploading .docx on Word to PDF page");
        log.info("TC_W2PDF_004 PASSED");
    }

    @Test(description = "TC_W2PDF_005 - Word to PDF: click Convert and verify success or download",
          groups = {"regression", "e2e"})
    public void verifyWordToPdfConversion() {
        WordToPDFPage page = new HomePage().open().goToWordToPDF();
        page.uploadFile("sample1.docx").clickConvert();
        Assert.assertTrue(page.isConversionSuccessful() || page.isDownloadAvailable(),
                "TC_W2PDF_005: Word to PDF conversion did not complete");
        log.info("TC_W2PDF_005 PASSED");
    }

    @Test(description = "TC_W2PDF_006 - Word to PDF: verify all key page-load elements (soft assert)",
          groups = {"regression"})
    public void verifyWordToPdfPageElementsSoftAssert() {
        WordToPDFPage page = new HomePage().open().goToWordToPDF();
        SoftAssertManager.get().assertTrue(page.isPageDisplayed("title1"),
                "TC_W2PDF_006: Page heading not displayed");
        SoftAssertManager.get().assertTrue(page.isSelectFilesButtonDisplayed(),
                "TC_W2PDF_006: Select Files button not displayed");
        SoftAssertManager.get().assertFalse(page.isConvertButtonDisplayed(),
                "TC_W2PDF_006: Convert button should be hidden before any upload");
        SoftAssertManager.get().assertTrue(page.getCurrentUrl().contains("word_to_pdf"),
                "TC_W2PDF_006: URL does not contain 'word_to_pdf'");
        SoftAssertManager.assertAll();
        log.info("TC_W2PDF_006 PASSED");
    }

    @Test(description = "TC_W2PDF_007 - Word to PDF: browser title contains expected keyword",
          groups = {"regression"})
    public void verifyWordToPdfPageTitle() {
        new HomePage().open().goToWordToPDF();
        String title = BrowserHelper.getTitle().toLowerCase();
        Assert.assertTrue(title.contains("word") || title.contains("ilovepdf"),
                "TC_W2PDF_007: Page title '" + title + "' does not contain expected keyword");
        log.info("TC_W2PDF_007 PASSED");
    }

}
