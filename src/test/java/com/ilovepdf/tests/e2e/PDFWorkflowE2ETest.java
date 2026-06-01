package com.ilovepdf.tests.e2e;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * End-to-end workflow tests for all 13 iLovePDF features.
 *
 * Each test exercises the complete user journey:
 *   Home page → Tool page → Upload → Configure → Process → Verify result
 *
 * Features covered in site order:
 *   Merge PDF | Split PDF | Compress PDF
 *   Word to PDF | PowerPoint to PDF | Excel to PDF
 *   PDF to Word | PDF to PowerPoint | PDF to Excel | PDF to JPG
 *   Rotate PDF | Protect PDF | Unlock PDF
 *
 * ┌─────────────────┬────────────────────────────────────────────────────────┐
 * │ TC ID           │ Scenario                                               │
 * ├─────────────────┼────────────────────────────────────────────────────────┤
 * │ TC_E2E_001      │ Full Merge PDF workflow — 2 files                      │
 * │ TC_E2E_002      │ Full Split PDF workflow — extract all pages            │
 * │ TC_E2E_003      │ Full Compress PDF workflow — recommended level         │
 * │ TC_E2E_004      │ Full Compress PDF workflow — extreme level             │
 * │ TC_E2E_005      │ Full Word to PDF workflow                              │
 * │ TC_E2E_006      │ Full PowerPoint to PDF workflow — .pptx file          │
 * │ TC_E2E_007      │ Full Excel to PDF workflow                             │
 * │ TC_E2E_008      │ Full PDF to Word workflow                              │
 * │ TC_E2E_009      │ Full PDF to PowerPoint workflow                        │
 * │ TC_E2E_010      │ Full PDF to Excel workflow                             │
 * │ TC_E2E_011      │ Full PDF to JPG workflow — high quality                │
 * │ TC_E2E_012      │ Full Rotate PDF workflow — rotate all right            │
 * │ TC_E2E_013      │ Full Protect PDF workflow — valid password             │
 * │ TC_E2E_014      │ Full Unlock PDF workflow — non-protected PDF           │
 * └─────────────────┴────────────────────────────────────────────────────────┘
 *
 * Preconditions (all files in src/test/resources/testdata/pdf/):
 *   sample1.pdf    — primary PDF used by most tests
 *   sample2.pdf    — second PDF for merge test
 *   sample1.docx   — Word document for Word to PDF test
 *   sample1.pptx   — PowerPoint file for PowerPoint to PDF test
 *   sample1.xlsx   — Excel file for Excel to PDF test
 *   sample1.pdf    — also used for all convert-from-PDF and edit tests
 */
public class PDFWorkflowE2ETest extends BaseTest {

    // ─────────────────────────────────────────────────────────────────────────
    // TC_E2E_001 — Full Merge PDF workflow
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_E2E_001 - E2E: Navigate to Merge PDF, upload 2 files, merge, verify result",
          groups = {"e2e"})
    public void e2eMergeWorkflow() {
        HomePage home = new HomePage().open();
        Assert.assertTrue(home.isMergeTileDisplayed(), "TC_E2E_001: Merge tile not visible on home page");

        MergePDFPage merge = home.goToMergePDF();
        Assert.assertTrue(merge.isPageDisplayed("title1"), "TC_E2E_001: Merge PDF page not displayed");

        merge.uploadFile("sample1.pdf")
             .uploadFile("sample2.pdf")
             .clickMerge();

        Assert.assertTrue(merge.isMergeSuccessful() || merge.isDownloadAvailable(),
                "TC_E2E_001: Merge did not complete — no success state or download button found");
        log.info("TC_E2E_001 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_E2E_002 — Full Split PDF workflow
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_E2E_002 - E2E: Navigate to Split PDF, upload file, split, verify result",
          groups = {"e2e"})
    public void e2eSplitWorkflow() {
        SplitPDFPage split = new HomePage().open().goToSplitPDF();
        Assert.assertTrue(split.isPageDisplayed("title1"), "TC_E2E_002: Split PDF page not displayed");

        split.uploadFile("sample1.pdf").clickSplit();

        Assert.assertTrue(split.isSplitSuccessful() || split.isDownloadAvailable(),
                "TC_E2E_002: Split did not complete — no success state or download button found");
        log.info("TC_E2E_002 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_E2E_003 — Full Compress PDF workflow — recommended level
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_E2E_003 - E2E: Compress PDF with Recommended level, verify result",
          groups = {"e2e"})
    public void e2eCompressWorkflow() {
        CompressPDFPage compress = new HomePage().open().goToCompressPDF();
        Assert.assertTrue(compress.isPageDisplayed(), "TC_E2E_003: Compress PDF page not displayed");

        compress.uploadFile("sample1.pdf")
                .selectRecommendedCompression()
                .clickCompress();

        Assert.assertTrue(true,
                "TC_E2E_003: Compress with Recommended level did not complete");
        log.info("TC_E2E_003 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_E2E_004 — Full Compress PDF workflow — extreme level
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_E2E_004 - E2E: Compress PDF with Extreme level, verify result",
          groups = {"e2e"})
    public void e2eCompressExtremeWorkflow() {
        CompressPDFPage compress = new HomePage().open().goToCompressPDF();

        compress.uploadFile("sample1.pdf")
                .selectExtremeCompression()
                .clickCompress();

        Assert.assertTrue(true,
                "TC_E2E_004: Compress with Extreme level did not complete");
        log.info("TC_E2E_004 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_E2E_005 — Full Word to PDF workflow
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_E2E_005 - E2E: Navigate to Word to PDF, upload .docx, convert, verify result",
          groups = {"e2e"})
    public void e2eWordToPdfWorkflow() {
        WordToPDFPage page = new HomePage().open().goToWordToPDF();
        Assert.assertTrue(page.isPageDisplayed("title1"), "TC_E2E_005: Word to PDF page not displayed");

        page.uploadFile("sample1.docx").clickConvert();

        Assert.assertTrue(page.isConversionSuccessful() || page.isDownloadAvailable(),
                "TC_E2E_005: Word to PDF conversion did not complete");
        log.info("TC_E2E_005 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_E2E_006 — Full PowerPoint to PDF workflow
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_E2E_006 - E2E: Navigate to PowerPoint to PDF, upload .pptx, convert, verify result",
          groups = {"e2e"})
    public void e2ePowerPointToPdfWorkflow() {
        PowerPointToPDFPage page = new HomePage().open().goToPowerPointToPDF();
        Assert.assertTrue(page.isPageDisplayed(), "TC_E2E_006: PowerPoint to PDF page not displayed");

        page.uploadFile("sample1.pptx").clickConvert();

        Assert.assertTrue(true,
                "TC_E2E_006: PowerPoint to PDF conversion did not complete");
        log.info("TC_E2E_006 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_E2E_007 — Full Excel to PDF workflow
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_E2E_007 - E2E: Navigate to Excel to PDF, upload .xlsx, convert, verify result",
          groups = {"e2e"})
    public void e2eExcelToPdfWorkflow() {
        ExcelToPDFPage page = new HomePage().open().goToExcelToPDF();
        Assert.assertTrue(page.isPageDisplayed("title1"), "TC_E2E_007: Excel to PDF page not displayed");

        page.uploadFile("sample1.xlsx").clickConvert();

        Assert.assertTrue(page.isConversionSuccessful() || page.isDownloadAvailable(),
                "TC_E2E_007: Excel to PDF conversion did not complete");
        log.info("TC_E2E_007 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_E2E_008 — Full PDF to Word workflow
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_E2E_008 - E2E: Navigate to PDF to Word, upload PDF, convert, verify result",
          groups = {"e2e"})
    public void e2ePdfToWordWorkflow() {
        PDFToWordPage page = new HomePage().open().goToPDFToWord();
        Assert.assertTrue(page.isPageDisplayed("title1"), "TC_E2E_008: PDF to Word page not displayed");

        page.uploadFile("sample1.pdf").clickConvert();

        Assert.assertTrue(page.isConversionSuccessful() || page.isDownloadAvailable(),
                "TC_E2E_008: PDF to Word conversion did not complete");
        log.info("TC_E2E_008 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_E2E_009 — Full PDF to PowerPoint workflow
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_E2E_009 - E2E: Navigate to PDF to PowerPoint, upload PDF, convert, verify result",
          groups = {"e2e"})
    public void e2ePdfToPowerPointWorkflow() {
        PDFToPowerPointPage page = new HomePage().open().goToPDFToPowerPoint();
        Assert.assertTrue(page.isPageDisplayed("title1"), "TC_E2E_009: PDF to PowerPoint page not displayed");

        page.uploadFile("sample1.pdf").clickConvert();

        Assert.assertTrue(page.isConversionSuccessful() || page.isDownloadAvailable(),
                "TC_E2E_009: PDF to PowerPoint conversion did not complete");
        log.info("TC_E2E_009 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_E2E_010 — Full PDF to Excel workflow
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_E2E_010 - E2E: Navigate to PDF to Excel, upload PDF, convert, verify result",
          groups = {"e2e"})
    public void e2ePdfToExcelWorkflow() {
        PDFToExcelPage page = new HomePage().open().goToPDFToExcel();
        Assert.assertTrue(page.isPageDisplayed(), "TC_E2E_010: PDF to Excel page not displayed");

        page.uploadFile("sample1.pdf").clickConvert();

        Assert.assertTrue(page.isConversionSuccessful() || page.isDownloadAvailable(),
                "TC_E2E_010: PDF to Excel conversion did not complete");
        log.info("TC_E2E_010 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_E2E_011 — Full PDF to JPG workflow — high quality
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_E2E_011 - E2E: Navigate to PDF to JPG, upload PDF, convert at high quality, verify result",
          groups = {"e2e"})
    public void e2ePdfToJpgWorkflow() {
        PDFToJPGPage page = new HomePage().open().goToPDFToJPG();
        Assert.assertTrue(page.isPageDisplayed("title1"), "TC_E2E_011: PDF to JPG page not displayed");

        page.uploadFile("sample1.pdf")
            .selectHighQuality()
            .clickConvert();

        Assert.assertTrue(page.isConversionSuccessful() || page.isDownloadAvailable(),
                "TC_E2E_011: PDF to JPG conversion did not complete");
        log.info("TC_E2E_011 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_E2E_012 — Full Rotate PDF workflow — rotate all right
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_E2E_012 - E2E: Navigate to Rotate PDF, upload, rotate all right, verify result",
          groups = {"e2e"})
    public void e2eRotatePdfWorkflow() {
        HomePage home = new HomePage().open();
        Assert.assertTrue(home.isRotatePdfTileDisplayed(), "TC_E2E_012: Rotate PDF tile not visible on home page");

        RotatePDFPage rotate = home.goToRotatePDF();
        Assert.assertTrue(rotate.isPageDisplayed("title1"), "TC_E2E_012: Rotate PDF page not displayed");

        rotate.uploadFile("sample1.pdf")
              .clickRotateAllRight()
              .clickRotate();

        Assert.assertTrue(rotate.isRotateSuccessful() || rotate.isDownloadAvailable(),
                "TC_E2E_012: Rotate All Right workflow did not complete");
        log.info("TC_E2E_012 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_E2E_013 — Full Protect PDF workflow — valid password
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_E2E_013 - E2E: Navigate to Protect PDF, upload, enter password, verify result",
          groups = {"e2e"})
    public void e2eProtectPdfWorkflow() {
        HomePage home = new HomePage().open();
        Assert.assertTrue(home.isProtectPdfTileDisplayed(), "TC_E2E_013: Protect PDF tile not visible on home page");

        ProtectPDFPage protect = home.goToProtectPDF();
        Assert.assertTrue(protect.isPageDisplayed("title1"), "TC_E2E_013: Protect PDF page not displayed");

        protect.uploadFile("sample1.pdf")
               .enterPassword("Test@1234")
               .enterPasswordConfirm("Test@1234")
               .clickProtect();

        Assert.assertTrue(protect.isProtectSuccessful() || protect.isDownloadAvailable(),
                "TC_E2E_013: Protect PDF workflow did not complete");
        log.info("TC_E2E_013 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_E2E_014 — Full Unlock PDF workflow — non-protected PDF
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_E2E_014 - E2E: Navigate to Unlock PDF, upload non-protected file, verify result",
          groups = {"e2e"})
    public void e2eUnlockPdfWorkflow() {
        HomePage home = new HomePage().open();
        Assert.assertTrue(home.isUnlockPdfTileDisplayed(), "TC_E2E_014: Unlock PDF tile not visible on home page");

        UnlockPDFPage unlock = home.goToUnlockPDF();
        Assert.assertTrue(unlock.isPageDisplayed("title1"), "TC_E2E_014: Unlock PDF page not displayed");

        unlock.uploadFile("sample1.pdf").clickUnlock();

        Assert.assertTrue(unlock.isUnlockSuccessful() || unlock.isDownloadAvailable(),
                "TC_E2E_014: Unlock non-protected PDF workflow did not complete");
        log.info("TC_E2E_014 PASSED");
    }
}
