package com.ilovepdf.tests.regression;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.helpers.BrowserHelper;
import com.ilovepdf.pages.HomePage;
import com.ilovepdf.pages.SplitPDFPage;
import com.ilovepdf.utilities.SoftAssertManager;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Regression test suite for the Split PDF feature.
 *
 * ┌─────────────────┬───────────────────────────────────────────────────────────┐
 * │ TC ID           │ Scenario                                                  │
 * ├─────────────────┼───────────────────────────────────────────────────────────┤
 * │ TC_SPLIT_001    │ Page loads with correct URL                               │
 * │ TC_SPLIT_002    │ Select Files button is displayed on page load             │
 * │ TC_SPLIT_003    │ Split button is NOT visible before any file is uploaded   │ ← FIXED
 * │ TC_SPLIT_004    │ Upload a single PDF — page remains stable                 │
 * │ TC_SPLIT_005    │ Select 'Split by ranges' mode after upload                │
 * │ TC_SPLIT_006    │ Select 'Split by pages' mode after upload                 │
 * │ TC_SPLIT_007    │ Split button APPEARS after uploading a file               │ ← NEW
 * │ TC_SPLIT_008    │ Click Split after uploading → success / download          │
 * │ TC_SPLIT_009    │ All key page-load elements present (soft assert)          │
 * │ TC_SPLIT_010    │ Browser tab title contains expected keyword               │
 * │ TC_SPLIT_011    │ Navigate back to home from Split page                     │
 * └─────────────────┴───────────────────────────────────────────────────────────┘
 */
public class SplitPDFTests extends BaseTest {

//    @Test(
//        description = "TC_SPLIT_001 - Split PDF page loads with correct URL",
//        groups = {"regression", "smoke"}
//    )
//    public void verifySplitPageLoads() {
//        SplitPDFPage split = new HomePage().open().goToSplitPDF();
//
//        Assert.assertTrue(split.isPageDisplayed("title1"),
//                "TC_SPLIT_001: Split PDF page heading not displayed");
//        Assert.assertTrue(split.getCurrentUrl().contains("split_pdf"),
//                "TC_SPLIT_001: URL does not contain 'split_pdf'");
//        log.info("TC_SPLIT_001 PASSED");
//    }
//
//    @Test(
//        description = "TC_SPLIT_002 - Select Files button is displayed on Split PDF page load",
//        groups = {"regression"}
//    )
//    public void verifySelectFilesButtonDisplayed() {
//        SplitPDFPage split = new HomePage().open().goToSplitPDF();
//
//        Assert.assertTrue(split.isSelectFilesButtonDisplayed(),
//                "TC_SPLIT_002: Select Files button not displayed on page load");
//        log.info("TC_SPLIT_002 PASSED");
//    }
//
//    // ─────────────────────────────────────────────────────────────────────────
//    // TC_SPLIT_003 – Split button is NOT visible before any file is uploaded
//    //
//    // BUSINESS LOGIC: iLovePDF hides #processTask until a file is uploaded.
//    // ─────────────────────────────────────────────────────────────────────────
//    @Test(
//        description = "TC_SPLIT_003 - Split button is NOT visible on page load before any upload",
//        groups = {"regression"}
//    )
//    public void verifySplitButtonNotVisibleBeforeUpload() {
//        SplitPDFPage split = new HomePage().open().goToSplitPDF();
//
//        Assert.assertFalse(split.isSplitButtonDisplayed(),
//                "TC_SPLIT_003: Split button should NOT be visible before any file is uploaded");
//        log.info("TC_SPLIT_003 PASSED");
//    }
//
//    @Test(
//        description = "TC_SPLIT_004 - Upload a single PDF and verify page stability",
//        groups = {"regression"}
//    )
//    public void verifyUploadSinglePDF() {
//        SplitPDFPage split = new HomePage().open().goToSplitPDF();
//        split.uploadFile("sample1.pdf");
//
//        Assert.assertTrue(split.isPageDisplayed("title2"),
//                "TC_SPLIT_004: Page not stable after single file upload");
//        log.info("TC_SPLIT_004 PASSED");
//    }
//
//    @Test(
//        description = "TC_SPLIT_005 - Select 'Split by ranges' mode after uploading a PDF",
//        groups = {"regression"}
//    )
//    public void verifySplitByRangesMode() {
//        SplitPDFPage split = new HomePage().open().goToSplitPDF();
//        split.uploadFile("sample1.pdf").selectRangeMode();
//
//        Assert.assertTrue(split.isPageDisplayed("title2"),
//                "TC_SPLIT_005: Page not stable after selecting ranges mode");
//        log.info("TC_SPLIT_005 PASSED");
//    }
//
//    @Test(
//        description = "TC_SPLIT_006 - Select 'Split by pages' mode after uploading a PDF",
//        groups = {"regression"}
//    )
//    public void verifySplitByPagesMode() {
//        SplitPDFPage split = new HomePage().open().goToSplitPDF();
//        split.uploadFile("sample1.pdf").selectPagesMode();
//
//        Assert.assertTrue(split.isPageDisplayed("title2"),
//                "TC_SPLIT_006: Page not stable after selecting pages mode");
//        log.info("TC_SPLIT_006 PASSED");
//    }
//
//    // ─────────────────────────────────────────────────────────────────────────
//    // TC_SPLIT_007 – Split button APPEARS after uploading a file  ← NEW
//    // ─────────────────────────────────────────────────────────────────────────
//    @Test(
//        description = "TC_SPLIT_007 - Split button becomes visible after uploading a file",
//        groups = {"regression"}
//    )
//    public void verifySplitButtonAppearsAfterUpload() {
//        SplitPDFPage split = new HomePage().open().goToSplitPDF();
//
//        Assert.assertFalse(split.isSplitButtonDisplayed(),
//                "TC_SPLIT_007: Pre-condition failed — Split button visible before upload");
//
//        split.uploadFile("sample1.pdf");
//
//        Assert.assertTrue(split.isSplitButtonDisplayed(),
//                "TC_SPLIT_007: Split button did not appear after uploading a file");
//        log.info("TC_SPLIT_007 PASSED");
//    }
//
//    @Test(
//        description = "TC_SPLIT_008 - Split a PDF and verify success or download is available",
//        groups = {"regression", "e2e"}
//    )
//    public void verifySplitPDF() {
//        SplitPDFPage split = new HomePage().open().goToSplitPDF();
//        split.uploadFile("sample1.pdf").clickSplit();
//
//        Assert.assertTrue(split.isSplitSuccessful() || split.isDownloadAvailable(),
//                "TC_SPLIT_008: Split did not complete — no success state or download button found");
//        log.info("TC_SPLIT_008 PASSED");
//    }
//
//    @Test(
//        description = "TC_SPLIT_009 - Verify all key page-load elements with soft assertions",
//        groups = {"regression"}
//    )
//    public void verifyAllPageElementsWithSoftAssert() {
//        SplitPDFPage split = new HomePage().open().goToSplitPDF();
//
//        SoftAssertManager.get().assertTrue(split.isPageDisplayed("title1"),
//                "TC_SPLIT_009: Page heading not displayed");
//        SoftAssertManager.get().assertTrue(split.isSelectFilesButtonDisplayed(),
//                "TC_SPLIT_009: Select Files button not displayed");
//        SoftAssertManager.get().assertFalse(split.isSplitButtonDisplayed(),
//                "TC_SPLIT_009: Split button should be hidden before any upload");
//        SoftAssertManager.get().assertTrue(split.getCurrentUrl().contains("split_pdf"),
//                "TC_SPLIT_009: URL does not contain 'split_pdf'");
//
//        SoftAssertManager.assertAll();
//        log.info("TC_SPLIT_009 PASSED");
//    }
//
//    @Test(
//        description = "TC_SPLIT_010 - Browser tab title contains expected keyword",
//        groups = {"regression"}
//    )
//    public void verifyPageTitleContainsKeyword() {
//        new HomePage().open().goToSplitPDF();
//
//        String title = BrowserHelper.getTitle().toLowerCase();
//        Assert.assertTrue(title.contains("split") || title.contains("ilovepdf"),
//                "TC_SPLIT_010: Page title '" + title + "' does not contain expected keyword");
//        log.info("TC_SPLIT_010 PASSED");
//    }
//
    @Test(
        description = "TC_SPLIT_011 - Navigate back to home page from Split PDF page",
        groups = {"regression"}
    )
    public void verifyBackNavigationToHome() {
        new HomePage().open().goToSplitPDF();
        BrowserHelper.back();

        Assert.assertFalse(BrowserHelper.getCurrentUrl().contains("split_pdf"),
                "TC_SPLIT_011: Back navigation did not leave Split PDF page");
        log.info("TC_SPLIT_011 PASSED");
    }
}
