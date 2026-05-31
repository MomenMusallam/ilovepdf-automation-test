package com.ilovepdf.tests.regression;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.helpers.BrowserHelper;
import com.ilovepdf.pages.HomePage;
import com.ilovepdf.pages.RotatePDFPage;
import com.ilovepdf.utilities.SoftAssertManager;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Regression test suite for:
 *   Rotate PDF
 *
 * ════════════════════════════════════════════════════════════════════════════
 * ROTATE PDF — TC_ROTATE_001 to TC_ROTATE_012
 * ════════════════════════════════════════════════════════════════════════════
 *
 * ┌──────────────────┬──────────────────────────────────────────────────────┐
 * │ TC ID            │ Scenario                                             │
 * ├──────────────────┼──────────────────────────────────────────────────────┤
 * │ TC_ROTATE_001    │ Page loads with correct URL                          │
 * │ TC_ROTATE_002    │ Select Files button displayed on page load           │
 * │ TC_ROTATE_003    │ Rotate button NOT visible before upload              │
 * │ TC_ROTATE_004    │ Rotate button appears after uploading a file         │
 * │ TC_ROTATE_005    │ Upload a PDF — page remains stable                   │
 * │ TC_ROTATE_006    │ Rotate All Right control visible after upload        │
 * │ TC_ROTATE_007    │ Rotate All Left control visible after upload         │
 * │ TC_ROTATE_008    │ Click Rotate All Right → page stable                 │
 * │ TC_ROTATE_009    │ Click Rotate All Left → page stable                  │
 * │ TC_ROTATE_010    │ Click Rotate PDF button → success or download        │
 * │ TC_ROTATE_011    │ All page-load elements present (soft assert)         │
 * │ TC_ROTATE_012    │ Browser title contains expected keyword              │
 * └──────────────────┴──────────────────────────────────────────────────────┘
 *
 
 * Preconditions:
 *   sample1.pdf        — standard PDF for Rotate, Unlock (non-protected), Protect tests
 *   locked_sample.pdf  — password-protected PDF for TC_UNLOCK_007 / TC_UNLOCK_008 / TC_UNLOCK_009
 */
public class RotatePDFTests extends BaseTest {

    // ═════════════════════════════════════════════════════════════════════════
    // ROTATE PDF
    // ═════════════════════════════════════════════════════════════════════════

    @Test(
        description = "TC_ROTATE_001 - Rotate PDF page loads with correct URL",
        groups = {"regression", "smoke"}
    )
    public void verifyRotatePageLoads() {
        RotatePDFPage rotate = new HomePage().open().goToRotatePDF();

        Assert.assertTrue(rotate.isPageDisplayed("title1"),
                "TC_ROTATE_001: Rotate PDF page heading not displayed");
        Assert.assertTrue(rotate.getCurrentUrl().contains("rotate_pdf"),
                "TC_ROTATE_001: URL does not contain 'rotate_pdf'");
        log.info("TC_ROTATE_001 PASSED");
    }

    @Test(
        description = "TC_ROTATE_002 - Select Files button displayed on Rotate PDF page load",
        groups = {"regression"}
    )
    public void verifyRotateSelectFilesButtonDisplayed() {
        RotatePDFPage rotate = new HomePage().open().goToRotatePDF();

        Assert.assertTrue(rotate.isSelectFilesButtonDisplayed(),
                "TC_ROTATE_002: Select Files button not displayed on page load");
        log.info("TC_ROTATE_002 PASSED");
    }

    // ── TC_ROTATE_003 — Rotate button NOT visible before upload ───────────────
    @Test(
        description = "TC_ROTATE_003 - Rotate PDF button is NOT visible before any file is uploaded",
        groups = {"regression"}
    )
    public void verifyRotateButtonNotVisibleBeforeUpload() {
        RotatePDFPage rotate = new HomePage().open().goToRotatePDF();

        Assert.assertFalse(rotate.isRotateButtonDisplayed(),
                "TC_ROTATE_003: Rotate button should NOT be visible before any file is uploaded");
        log.info("TC_ROTATE_003 PASSED");
    }

    // ── TC_ROTATE_004 — Rotate button appears after upload ────────────────────
    @Test(
        description = "TC_ROTATE_004 - Rotate PDF button becomes visible after uploading a file",
        groups = {"regression"}
    )
    public void verifyRotateButtonAppearsAfterUpload() {
        RotatePDFPage rotate = new HomePage().open().goToRotatePDF();

        // Pre-condition: button hidden before upload
        Assert.assertFalse(rotate.isRotateButtonDisplayed(),
                "TC_ROTATE_004: Pre-condition failed — Rotate button visible before upload");

        rotate.uploadFile("sample1.pdf");

        Assert.assertTrue(rotate.isRotateButtonDisplayed(),
                "TC_ROTATE_004: Rotate button did not appear after uploading a file");
        log.info("TC_ROTATE_004 PASSED");
    }

    @Test(
        description = "TC_ROTATE_005 - Upload a single PDF on Rotate PDF page and verify stability",
        groups = {"regression"}
    )
    public void verifyUploadPdfStability() {
        RotatePDFPage rotate = new HomePage().open().goToRotatePDF();
        rotate.uploadFile("sample1.pdf");

        Assert.assertTrue(rotate.isPageDisplayed("title2"),
                "TC_ROTATE_005: Page not stable after uploading PDF to Rotate PDF page");
        log.info("TC_ROTATE_005 PASSED");
    }

    @Test(
        description = "TC_ROTATE_006 - Rotate All Right control is visible after upload",
        groups = {"regression"}
    )
    public void verifyRotateAllRightVisible() {
        RotatePDFPage rotate = new HomePage().open().goToRotatePDF();
        rotate.uploadFile("sample1.pdf");

        Assert.assertTrue(rotate.isRotateAllRightButtonDisplayed(),
                "TC_ROTATE_006: Rotate All Right button not visible after upload");
        log.info("TC_ROTATE_006 PASSED");
    }

    @Test(
        description = "TC_ROTATE_007 - Rotate All Left control is visible after upload",
        groups = {"regression"}
    )
    public void verifyRotateAllLeftVisible() {
        RotatePDFPage rotate = new HomePage().open().goToRotatePDF();
        rotate.uploadFile("sample1.pdf");

        Assert.assertTrue(rotate.isRotateAllLeftButtonDisplayed(),
                "TC_ROTATE_007: Rotate All Left button not visible after upload");
        log.info("TC_ROTATE_007 PASSED");
    }

    @Test(
        description = "TC_ROTATE_008 - Click Rotate All Right and verify page remains stable",
        groups = {"regression"}
    )
    public void verifyClickRotateAllRight() {
        RotatePDFPage rotate = new HomePage().open().goToRotatePDF();
        rotate.uploadFile("sample1.pdf").clickRotateAllRight();

        Assert.assertTrue(rotate.isPageDisplayed("title2"),
                "TC_ROTATE_008: Page not stable after clicking Rotate All Right");
        log.info("TC_ROTATE_008 PASSED");
    }

    @Test(
        description = "TC_ROTATE_009 - Click Rotate All Left and verify page remains stable",
        groups = {"regression"}
    )
    public void verifyClickRotateAllLeft() {
        RotatePDFPage rotate = new HomePage().open().goToRotatePDF();
        rotate.uploadFile("sample1.pdf").clickRotateAllLeft();

        Assert.assertTrue(rotate.isPageDisplayed("title2"),
                "TC_ROTATE_009: Page not stable after clicking Rotate All Left");
        log.info("TC_ROTATE_009 PASSED");
    }

    @Test(
        description = "TC_ROTATE_010 - Click Rotate PDF and verify success or download available",
        groups = {"regression", "e2e"}
    )
    public void verifyRotatePdfAndDownload() {
        RotatePDFPage rotate = new HomePage().open().goToRotatePDF();
        rotate.uploadFile("sample1.pdf")
              .clickRotateAllRight()
              .clickRotate();

        Assert.assertTrue(rotate.isRotateSuccessful() || rotate.isDownloadAvailable(),
                "TC_ROTATE_010: Rotate PDF did not complete — no success or download found");
        log.info("TC_ROTATE_010 PASSED");
    }

    @Test(
        description = "TC_ROTATE_011 - Verify all key page-load elements with soft assertions",
        groups = {"regression"}
    )
    public void verifyRotatePageElementsSoftAssert() {
        RotatePDFPage rotate = new HomePage().open().goToRotatePDF();

        SoftAssertManager.get().assertTrue(rotate.isPageDisplayed("title1"),
                "TC_ROTATE_011: Page heading not displayed");
        SoftAssertManager.get().assertTrue(rotate.isSelectFilesButtonDisplayed(),
                "TC_ROTATE_011: Select Files button not displayed");
        SoftAssertManager.get().assertFalse(rotate.isRotateButtonDisplayed(),
                "TC_ROTATE_011: Rotate button should be hidden before any upload");
        SoftAssertManager.get().assertTrue(rotate.getCurrentUrl().contains("rotate_pdf"),
                "TC_ROTATE_011: URL does not contain 'rotate_pdf'");

        SoftAssertManager.assertAll();
        log.info("TC_ROTATE_011 PASSED");
    }

    @Test(
        description = "TC_ROTATE_012 - Browser title contains expected keyword",
        groups = {"regression"}
    )
    public void verifyRotatePageTitle() {
        new HomePage().open().goToRotatePDF();

        String title = BrowserHelper.getTitle().toLowerCase();
        Assert.assertTrue(title.contains("rotate") || title.contains("ilovepdf"),
                "TC_ROTATE_012: Page title '" + title + "' does not contain expected keyword");
        log.info("TC_ROTATE_012 PASSED");
    }
}
