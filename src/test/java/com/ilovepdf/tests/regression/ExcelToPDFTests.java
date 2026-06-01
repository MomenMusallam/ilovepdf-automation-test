package com.ilovepdf.tests.regression;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.helpers.BrowserHelper;
import com.ilovepdf.pages.ExcelToPDFPage;
import com.ilovepdf.pages.HomePage;
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
public class ExcelToPDFTests extends BaseTest {


    @Test(description = "TC_XLS2PDF_001 - Excel to PDF page loads with correct URL",
          groups = {"regression", "smoke"})
    public void verifyExcelToPdfPageLoads() {
        ExcelToPDFPage page = new HomePage().open().goToExcelToPDF();
        Assert.assertTrue(page.isPageDisplayed("title1"),
                "TC_XLS2PDF_001: Excel to PDF page heading not displayed");
        Assert.assertTrue(page.getCurrentUrl().contains("excel_to_pdf"),
                "TC_XLS2PDF_001: URL does not contain 'excel_to_pdf'");
        log.info("TC_XLS2PDF_001 PASSED");
    }

    @Test(description = "TC_XLS2PDF_002 - Select Files button displayed on Excel to PDF page",
          groups = {"regression"})
    public void verifyExcelToPdfSelectFilesButtonDisplayed() {
        ExcelToPDFPage page = new HomePage().open().goToExcelToPDF();
        Assert.assertTrue(page.isSelectFilesButtonDisplayed(),
                "TC_XLS2PDF_002: Select Files button not displayed on Excel to PDF page");
        log.info("TC_XLS2PDF_002 PASSED");
    }

    // ── TC_XLS2PDF_003  FIXED ────────────────────────────────────────────────
    @Test(description = "TC_XLS2PDF_003 - Convert button is NOT visible before any file is uploaded",
          groups = {"regression"})
    public void verifyExcelToPdfConvertButtonNotVisibleBeforeUpload() {
        ExcelToPDFPage page = new HomePage().open().goToExcelToPDF();
        Assert.assertFalse(page.isConvertButtonDisplayed(),
                "TC_XLS2PDF_003: Convert button should NOT be visible before file upload");
        log.info("TC_XLS2PDF_003 PASSED");
    }

//     ── TC_XLS2PDF_003b  NEW ─────────────────────────────────────────────────
    @Test(description = "TC_XLS2PDF_003b - Convert button appears after uploading a .xlsx file",
          groups = {"regression"})
    public void verifyExcelToPdfConvertButtonAppearsAfterUpload() {
        ExcelToPDFPage page = new HomePage().open().goToExcelToPDF();
        Assert.assertFalse(page.isConvertButtonDisplayed(),
                "TC_XLS2PDF_003b: Pre-condition failed — Convert button visible before upload");
        page.uploadFile("sample1.xlsx");
        Assert.assertTrue(page.isConvertButtonDisplayed(),
                "TC_XLS2PDF_003b: Convert button did not appear after uploading a .xlsx file");
        log.info("TC_XLS2PDF_003b PASSED");
    }

    @Test(description = "TC_XLS2PDF_004 - Upload .xlsx on Excel to PDF page and verify stability",
          groups = {"regression"})
    public void verifyExcelToPdfUploadXlsx() {
        ExcelToPDFPage page = new HomePage().open().goToExcelToPDF();
        page.uploadFile("sample1.xlsx");
        Assert.assertTrue(page.isPageDisplayed("title2"),
                "TC_XLS2PDF_004: Page not stable after uploading .xlsx on Excel to PDF page");
        log.info("TC_XLS2PDF_004 PASSED");
    }

    @Test(description = "TC_XLS2PDF_005 - Excel to PDF: click Convert and verify success or download",
          groups = {"regression", "e2e"})
    public void verifyExcelToPdfConversion() {
        ExcelToPDFPage page = new HomePage().open().goToExcelToPDF();
        page.uploadFile("sample1.xlsx").clickConvert();
        Assert.assertTrue(page.isConversionSuccessful() || page.isDownloadAvailable(),
                "TC_XLS2PDF_005: Excel to PDF conversion did not complete");
        log.info("TC_XLS2PDF_005 PASSED");
    }

    @Test(description = "TC_XLS2PDF_006 - Excel to PDF: verify all key page-load elements (soft assert)",
          groups = {"regression"})
    public void verifyExcelToPdfPageElementsSoftAssert() {
        ExcelToPDFPage page = new HomePage().open().goToExcelToPDF();
        SoftAssertManager.get().assertTrue(page.isPageDisplayed("title1"),
                "TC_XLS2PDF_006: Page heading not displayed");
        SoftAssertManager.get().assertTrue(page.isSelectFilesButtonDisplayed(),
                "TC_XLS2PDF_006: Select Files button not displayed");
        SoftAssertManager.get().assertFalse(page.isConvertButtonDisplayed(),
                "TC_XLS2PDF_006: Convert button should be hidden before any upload");
        SoftAssertManager.get().assertTrue(page.getCurrentUrl().contains("excel_to_pdf"),
                "TC_XLS2PDF_006: URL does not contain 'excel_to_pdf'");
        SoftAssertManager.assertAll();
        log.info("TC_XLS2PDF_006 PASSED");
    }
}
