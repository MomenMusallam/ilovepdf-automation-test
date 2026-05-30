package com.ilovepdf.tests.regression;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.helpers.BrowserHelper;
import com.ilovepdf.pages.HomePage;
import com.ilovepdf.pages.MergePDFPage;
import com.ilovepdf.utilities.SoftAssertManager;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Regression test suite for the Merge PDF feature.
 *
 * ┌─────────────────┬───────────────────────────────────────────────────────────┐
 * │ TC ID           │ Scenario                                                  │
 * ├─────────────────┼───────────────────────────────────────────────────────────┤
 * │ TC_MERGE_001    │ Page loads with correct URL and heading                   │
 * │ TC_MERGE_002    │ Select Files button is displayed on page load             │
 * │ TC_MERGE_003    │ Merge button is NOT visible before any file is uploaded   │ ← FIXED
 * │ TC_MERGE_004    │ Upload a single PDF — page remains stable                 │
 * │ TC_MERGE_005    │ Upload two PDFs — page remains stable                     │
 * │ TC_MERGE_006    │ Upload three PDFs — page remains stable (boundary)        │
 * │ TC_MERGE_007    │ Merge button APPEARS after uploading files                │ ← FIXED
 * │ TC_MERGE_008    │ Merge two PDFs → success or download available            │
 * │ TC_MERGE_009    │ All page elements present (soft assert)                   │
 * │ TC_MERGE_010    │ Browser tab title contains expected keyword               │
 * └─────────────────┴───────────────────────────────────────────────────────────┘
 *
 * Precondition for upload tests:
 *   sample1.pdf, sample2.pdf, sample3.pdf must exist at
 *   src/test/resources/testdata/pdf/
 */
public class MergePDFTests extends BaseTest {

    // ─────────────────────────────────────────────────────────────────────────
    // TC_MERGE_001 – Page loads with correct URL and heading
    // ─────────────────────────────────────────────────────────────────────────
    @Test(
        description = "TC_MERGE_001 - Merge PDF page loads with correct URL",
        groups = {"regression", "smoke"}
    )
    public void verifyMergePageLoads() {
        MergePDFPage merge = new HomePage().open().goToMergePDF();

        Assert.assertTrue(merge.isPageDisplayed("title1"),
                "TC_MERGE_001: Merge PDF page heading not displayed");
        Assert.assertTrue(merge.getCurrentUrl().contains("merge_pdf"),
                "TC_MERGE_001: URL does not contain 'merge_pdf'");
        log.info("TC_MERGE_001 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_MERGE_002 – Select Files button is displayed on page load
    // ─────────────────────────────────────────────────────────────────────────
    @Test(
        description = "TC_MERGE_002 - Select Files button is displayed on Merge PDF page load",
        groups = {"regression"}
    )
    public void verifySelectFilesButtonDisplayed() {
        MergePDFPage merge = new HomePage().open().goToMergePDF();

        Assert.assertTrue(merge.isSelectFilesButtonDisplayed(),
                "TC_MERGE_002: Select Files button not displayed on page load");
        log.info("TC_MERGE_002 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_MERGE_003 – Merge button is NOT visible before any file is uploaded
    //
    // BUSINESS LOGIC: iLovePDF hides the #processTask (Merge) button until
    // at least one file has been uploaded. Verifying this ensures the UI
    // correctly prevents the user from attempting an empty merge.
    // ─────────────────────────────────────────────────────────────────────────
    @Test(
        description = "TC_MERGE_003 - Merge button is NOT visible on page load before any upload",
        groups = {"regression"}
    )
    public void verifyMergeButtonNotVisibleBeforeUpload() {
        MergePDFPage merge = new HomePage().open().goToMergePDF();

        Assert.assertFalse(merge.isMergeButtonDisplayed(),
                "TC_MERGE_003: Merge button should NOT be visible before any file is uploaded");
        log.info("TC_MERGE_003 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_MERGE_004 – Upload a single PDF — page remains stable
    // ─────────────────────────────────────────────────────────────────────────
    @Test(
        description = "TC_MERGE_004 - Upload a single PDF file and verify page stability",
        groups = {"regression"}
    )
    public void verifyUploadSinglePDF() {
        MergePDFPage merge = new HomePage().open().goToMergePDF();
        merge.uploadFile("sample1.pdf");

        Assert.assertTrue(merge.isPageDisplayed("title2"),
                "TC_MERGE_004: Page not stable after single file upload");
        log.info("TC_MERGE_004 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_MERGE_005 – Upload two PDFs — page remains stable
    // ─────────────────────────────────────────────────────────────────────────
    @Test(
        description = "TC_MERGE_005 - Upload two PDFs and verify page stability",
        groups = {"regression"}
    )
    public void verifyUploadTwoPDFs() {
        MergePDFPage merge = new HomePage().open().goToMergePDF();
        merge.uploadFile("sample1.pdf")
             .uploadFile("sample2.pdf");

        Assert.assertTrue(merge.isPageDisplayed("title2"),
                "TC_MERGE_005: Page not stable after two file uploads");
        log.info("TC_MERGE_005 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_MERGE_006 – Upload three PDFs — page remains stable (boundary)
    // ─────────────────────────────────────────────────────────────────────────
    @Test(
        description = "TC_MERGE_006 - Upload three PDFs and verify page stability (boundary test)",
        groups = {"regression"}
    )
    public void verifyUploadThreePDFs() {
        MergePDFPage merge = new HomePage().open().goToMergePDF();
        merge.uploadFile("sample1.pdf")
             .uploadFile("sample2.pdf")
             .uploadFile("sample3.pdf");

        Assert.assertTrue(merge.isPageDisplayed("title2"),
                "TC_MERGE_006: Page not stable after three file uploads");
        log.info("TC_MERGE_006 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_MERGE_007 – Merge button APPEARS after uploading files
    //
    // BUSINESS LOGIC: Validates the correct state transition —
    // Merge button must become visible once at least one file is uploaded.
    // This is the positive counterpart to TC_MERGE_003.
    // ─────────────────────────────────────────────────────────────────────────
    @Test(
        description = "TC_MERGE_007 - Merge button becomes visible after uploading files",
        groups = {"regression"}
    )
    public void verifyMergeButtonAppearsAfterUpload() {
        MergePDFPage merge = new HomePage().open().goToMergePDF();

        // Confirm button is hidden before upload (pre-condition)
        Assert.assertFalse(merge.isMergeButtonDisplayed(),
                "TC_MERGE_007: Pre-condition failed — Merge button visible before upload");

        // Upload a file and confirm button appears
        merge.uploadFile("sample1.pdf");

        Assert.assertTrue(merge.isMergeButtonDisplayed(),
                "TC_MERGE_007: Merge button did not appear after uploading a file");
        log.info("TC_MERGE_007 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_MERGE_008 – Merge two PDFs → success or download available
    // ─────────────────────────────────────────────────────────────────────────
    @Test(
        description = "TC_MERGE_008 - Merge two PDFs and verify success or download is available",
        groups = {"regression", "e2e"}
    )
    public void verifyMergeTwoPDFs() {
        MergePDFPage merge = new HomePage().open().goToMergePDF();
        merge.uploadFile("sample1.pdf")
             .uploadFile("sample2.pdf")
             .clickMerge();

        Assert.assertTrue(merge.isMergeSuccessful() || merge.isDownloadAvailable(),
                "TC_MERGE_008: Merge did not complete — no success state or download button found");
        log.info("TC_MERGE_008 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_MERGE_009 – All page elements present (soft assert coverage)
    //
    // Note: Merge button is intentionally NOT asserted here because it is
    // hidden on page load. Only elements always visible on load are checked.
    // ─────────────────────────────────────────────────────────────────────────
    @Test(
        description = "TC_MERGE_009 - Verify all key page-load elements with soft assertions",
        groups = {"regression"}
    )
    public void verifyAllPageElementsWithSoftAssert() {
        MergePDFPage merge = new HomePage().open().goToMergePDF();

        SoftAssertManager.get().assertTrue(merge.isPageDisplayed("title1"),
                "TC_MERGE_009: Page heading not displayed");
        SoftAssertManager.get().assertTrue(merge.isSelectFilesButtonDisplayed(),
                "TC_MERGE_009: Select Files button not displayed");
        SoftAssertManager.get().assertFalse(merge.isMergeButtonDisplayed(),
                "TC_MERGE_009: Merge button should be hidden before any upload");
        SoftAssertManager.get().assertTrue(merge.getCurrentUrl().contains("merge_pdf"),
                "TC_MERGE_009: URL does not contain 'merge_pdf'");

        SoftAssertManager.assertAll();
        log.info("TC_MERGE_009 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_MERGE_010 – Browser tab title contains expected keyword
    // ─────────────────────────────────────────────────────────────────────────
    @Test(
        description = "TC_MERGE_010 - Browser tab title contains iLovePDF keyword",
        groups = {"regression"}
    )
    public void verifyPageTitleContainsKeyword() {
        new HomePage().open().goToMergePDF();

        String title = BrowserHelper.getTitle().toLowerCase();
        Assert.assertTrue(title.contains("merge") || title.contains("ilovepdf"),
                "TC_MERGE_010: Page title '" + title + "' does not contain expected keyword");
        log.info("TC_MERGE_010 PASSED");
    }
}
