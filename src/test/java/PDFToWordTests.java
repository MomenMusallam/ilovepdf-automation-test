package com.ilovepdf.tests.regression;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.helpers.BrowserHelper;
import com.ilovepdf.pages.HomePage;
import com.ilovepdf.pages.PDFToWordPage;
import com.ilovepdf.utilities.SoftAssertManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PDFToWordTests extends BaseTest {

    @Test(description = "TC_PDF2W_001 - PDF to Word page loads with correct URL",
            groups = {"regression", "smoke"})
      public void verifyPdfToWordPageLoads() {
          PDFToWordPage page = new HomePage().open().goToPDFToWord();
          Assert.assertTrue(page.isPageDisplayed("title1"),
                  "TC_PDF2W_001: PDF to Word page heading not displayed");
          Assert.assertTrue(page.getCurrentUrl().contains("pdf_to_word"),
                  "TC_PDF2W_001: URL does not contain 'pdf_to_word'");
          log.info("TC_PDF2W_001 PASSED");
      }

      @Test(description = "TC_PDF2W_002 - Select Files button displayed on PDF to Word page",
            groups = {"regression"})
      public void verifyPdfToWordSelectFilesButtonDisplayed() {
          PDFToWordPage page = new HomePage().open().goToPDFToWord();
          Assert.assertTrue(page.isSelectFilesButtonDisplayed(),
                  "TC_PDF2W_002: Select Files button not displayed on PDF to Word page");
          log.info("TC_PDF2W_002 PASSED");
      }

      // ── TC_PDF2W_003  FIXED ──────────────────────────────────────────────────
      @Test(description = "TC_PDF2W_003 - Convert button is NOT visible before any file is uploaded",
            groups = {"regression"})
      public void verifyPdfToWordConvertButtonNotVisibleBeforeUpload() {
          PDFToWordPage page = new HomePage().open().goToPDFToWord();
          Assert.assertFalse(page.isConvertButtonDisplayed(),
                  "TC_PDF2W_003: Convert button should NOT be visible before file upload");
          log.info("TC_PDF2W_003 PASSED");
      }

      // ── TC_PDF2W_003b  NEW ───────────────────────────────────────────────────
      @Test(description = "TC_PDF2W_003b - Convert button appears after uploading a PDF",
            groups = {"regression"})
      public void verifyPdfToWordConvertButtonAppearsAfterUpload() {
          PDFToWordPage page = new HomePage().open().goToPDFToWord();
          Assert.assertFalse(page.isConvertButtonDisplayed(),
                  "TC_PDF2W_003b: Pre-condition failed — Convert button visible before upload");
          page.uploadFile("sample1.pdf");
          Assert.assertTrue(page.isConvertButtonDisplayed(),
                  "TC_PDF2W_003b: Convert button did not appear after uploading a file");
          log.info("TC_PDF2W_003b PASSED");
      }

      @Test(description = "TC_PDF2W_004 - Upload PDF on PDF to Word page and verify page stability",
            groups = {"regression"})
      public void verifyPdfToWordUploadPdf() {
          PDFToWordPage page = new HomePage().open().goToPDFToWord();
          page.uploadFile("sample1.pdf");
          Assert.assertTrue(page.isPageDisplayed("title2"),
                  "TC_PDF2W_004: Page not stable after file upload on PDF to Word page");
          log.info("TC_PDF2W_004 PASSED");
      }

      @Test(description = "TC_PDF2W_005 - PDF to Word: click Convert and verify success or download",
            groups = {"regression", "e2e"})
      public void verifyPdfToWordConversion() {
          PDFToWordPage page = new HomePage().open().goToPDFToWord();
          page.uploadFile("sample1.pdf").clickConvert();
          Assert.assertTrue(page.isConversionSuccessful() || page.isDownloadAvailable(),
                  "TC_PDF2W_005: PDF to Word conversion did not complete");
          log.info("TC_PDF2W_005 PASSED");
      }

      @Test(description = "TC_PDF2W_006 - PDF to Word: verify all key page-load elements (soft assert)",
            groups = {"regression"})
      public void verifyPdfToWordPageElementsSoftAssert() {
          PDFToWordPage page = new HomePage().open().goToPDFToWord();
          SoftAssertManager.get().assertTrue(page.isPageDisplayed("title1"),
                  "TC_PDF2W_006: Page heading not displayed");
          SoftAssertManager.get().assertTrue(page.isSelectFilesButtonDisplayed(),
                  "TC_PDF2W_006: Select Files button not displayed");
          SoftAssertManager.get().assertFalse(page.isConvertButtonDisplayed(),
                  "TC_PDF2W_006: Convert button should be hidden before any upload");
          SoftAssertManager.get().assertTrue(page.getCurrentUrl().contains("pdf_to_word"),
                  "TC_PDF2W_006: URL does not contain 'pdf_to_word'");
          SoftAssertManager.assertAll();
          log.info("TC_PDF2W_006 PASSED");
      }

      @Test(description = "TC_PDF2W_007 - PDF to Word: browser title contains keyword",
            groups = {"regression"})
      public void verifyPdfToWordPageTitle() {
          new HomePage().open().goToPDFToWord();
          String title = BrowserHelper.getTitle().toLowerCase();
          Assert.assertTrue(title.contains("word") || title.contains("ilovepdf"),
                  "TC_PDF2W_007: Page title '" + title + "' does not contain expected keyword");
          log.info("TC_PDF2W_007 PASSED");
      }
}
