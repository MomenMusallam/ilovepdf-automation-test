package com.ilovepdf.tests.regression;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.helpers.BrowserHelper;
import com.ilovepdf.pages.HomePage;
import com.ilovepdf.pages.ProtectPDFPage;
import com.ilovepdf.pages.RotatePDFPage;
import com.ilovepdf.pages.UnlockPDFPage;
import com.ilovepdf.utilities.SoftAssertManager;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Regression test suite for:Protect PDF
 *
 * ════════════════════════════════════════════════════════════════════════════
 * PROTECT PDF — TC_PROTECT_001 to TC_PROTECT_013
 * ════════════════════════════════════════════════════════════════════════════
 *
 * ┌──────────────────┬──────────────────────────────────────────────────────┐
 * │ TC ID            │ Scenario                                             │
 * ├──────────────────┼──────────────────────────────────────────────────────┤
 * │ TC_PROTECT_001   │ Page loads with correct URL                          │
 * │ TC_PROTECT_002   │ Select Files button displayed on page load           │
 * │ TC_PROTECT_003   │ Protect button NOT visible before upload             │
 * │ TC_PROTECT_004   │ Protect button appears after uploading a file        │
 * │ TC_PROTECT_005   │ Upload a PDF — page remains stable                   │
 * │ TC_PROTECT_006   │ Password input field appears after upload            │
 * │ TC_PROTECT_007   │ Enter a valid password → protect with success        │
 * │ TC_PROTECT_008   │ Submit without entering password → validation error  │
 * │ TC_PROTECT_009   │ Enter strong password (special chars) → success      │
 * │ TC_PROTECT_010   │ Enter single character password → accepted/rejected  │
 * │ TC_PROTECT_011   │ All page-load elements present (soft assert)         │
 * │ TC_PROTECT_012   │ Browser title contains expected keyword              │
 * └──────────────────┴──────────────────────────────────────────────────────┘
 *
 * Preconditions:
 *   sample1.pdf        — standard PDF for Rotate, Unlock (non-protected), Protect tests
 */
public class ProtectPDFTests extends BaseTest {


    @Test(
        description = "TC_PROTECT_001 - Protect PDF page loads with correct URL",
        groups = {"regression", "smoke"}
    )
    public void verifyProtectPageLoads() {
        ProtectPDFPage protect = new HomePage().open().goToProtectPDF();

        Assert.assertTrue(protect.isPageDisplayed("title1"),
                "TC_PROTECT_001: Protect PDF page heading not displayed");
        Assert.assertTrue(protect.getCurrentUrl().contains("protect-pdf"),
                "TC_PROTECT_001: URL does not contain 'protect-pdf'");
        log.info("TC_PROTECT_001 PASSED");
    }

    @Test(
        description = "TC_PROTECT_002 - Select Files button displayed on Protect PDF page load",
        groups = {"regression"}
    )
    public void verifyProtectSelectFilesButtonDisplayed() {
        ProtectPDFPage protect = new HomePage().open().goToProtectPDF();

        Assert.assertTrue(protect.isSelectFilesButtonDisplayed(),
                "TC_PROTECT_002: Select Files button not displayed on page load");
        log.info("TC_PROTECT_002 PASSED");
    }

    // ── TC_PROTECT_003 — Protect button NOT visible before upload ─────────────
    @Test(
        description = "TC_PROTECT_003 - Protect PDF button is NOT visible before any file is uploaded",
        groups = {"regression"}
    )
    public void verifyProtectButtonNotVisibleBeforeUpload() {
        ProtectPDFPage protect = new HomePage().open().goToProtectPDF();

        Assert.assertFalse(protect.isProtectButtonDisplayed(),
                "TC_PROTECT_003: Protect button should NOT be visible before any file is uploaded");
        log.info("TC_PROTECT_003 PASSED");
    }

//     ── TC_PROTECT_004 — Protect button appears after upload ──────────────────
    @Test(
        description = "TC_PROTECT_004 - Protect PDF button becomes visible after uploading a file",
        groups = {"regression"}
    )
    public void verifyProtectButtonAppearsAfterUpload() {
        ProtectPDFPage protect = new HomePage().open().goToProtectPDF();

        Assert.assertFalse(protect.isProtectButtonDisplayed(),
                "TC_PROTECT_004: Pre-condition failed — Protect button visible before upload");

        protect.uploadFile("sample1.pdf");
        
        Assert.assertTrue(protect.isProtectButtonDisplayed(),
                "TC_PROTECT_004: Protect button did not appear after uploading a file");
        log.info("TC_PROTECT_004 PASSED");
    }

    @Test(
        description = "TC_PROTECT_005 - Upload a PDF on Protect PDF page and verify page stability",
        groups = {"regression"}
    )
    public void verifyUploadPdfStabilityOnProtect() {
        ProtectPDFPage protect = new HomePage().open().goToProtectPDF();
        protect.uploadFile("sample1.pdf");

        Assert.assertTrue(protect.isPageDisplayed("title2"),
                "TC_PROTECT_005: Page not stable after uploading PDF to Protect PDF page");
        log.info("TC_PROTECT_005 PASSED");
    }

    @Test(
        description = "TC_PROTECT_006 - Password input field appears after uploading a PDF",
        groups = {"regression"}
    )
    public void verifyPasswordFieldAppearsAfterUpload() {
        ProtectPDFPage protect = new HomePage().open().goToProtectPDF();
        protect.uploadFile("sample1.pdf");

        Assert.assertTrue(protect.isPasswordInputDisplayed(),
                "TC_PROTECT_006: Password input field did not appear after uploading PDF");
        log.info("TC_PROTECT_006 PASSED");
    }

    @Test(
        description = "TC_PROTECT_007 - Enter a valid password and click Protect → success or download",
        groups = {"regression", "e2e"}
    )
    public void verifyProtectWithValidPassword() {
        ProtectPDFPage protect = new HomePage().open().goToProtectPDF();
        protect.uploadFile("sample1.pdf")
               .enterPassword("Test@1234")
               .enterPasswordConfirm("Test@1234")
               .clickProtect();

        Assert.assertTrue(protect.isProtectSuccessful() || protect.isDownloadAvailable(),
                "TC_PROTECT_007: Protect PDF with valid password did not complete");
        log.info("TC_PROTECT_007 PASSED");
    }

    @Test(
        description = "TC_PROTECT_008 - Submit without entering a password → validation error shown",
        groups = {"regression"}
    )
    public void verifyProtectWithEmptyPassword() {
        ProtectPDFPage protect = new HomePage().open().goToProtectPDF();
        protect.uploadFile("sample1.pdf");

        Assert.assertTrue(protect.isProtectButtonDisabled(),
                "TC_PROTECT_008: No validation shown when Protect submitted with empty password");
        log.info("TC_PROTECT_008 PASSED");
    }

    @Test(
        description = "TC_PROTECT_009 - Enter strong password with special characters → success or download",
        groups = {"regression"}
    )
    public void verifyProtectWithStrongPassword() {
        ProtectPDFPage protect = new HomePage().open().goToProtectPDF();
        protect.uploadFile("sample1.pdf")
               .enterPassword("P@$$w0rd!#2024")
               .enterPasswordConfirm("P@$$w0rd!#2024")
               .clickProtect();

        Assert.assertTrue(protect.isProtectSuccessful() || protect.isDownloadAvailable(),
                "TC_PROTECT_009: Protect PDF with strong password did not complete");
        log.info("TC_PROTECT_009 PASSED");
    }

    @Test(
        description = "TC_PROTECT_010 - Enter single character password — boundary value",
        groups = {"regression"}
    )
    public void verifyProtectWithSingleCharPassword() {
        ProtectPDFPage protect = new HomePage().open().goToProtectPDF();
        protect.uploadFile("sample1.pdf")
               .enterPassword("a")
               .enterPasswordConfirm("a")
               .clickProtect();

        // Single-char password may be accepted or rejected depending on
        // iLovePDF's minimum length policy. Either outcome is valid;
        // the test confirms the UI responds with one or the other — no crash.
        Assert.assertTrue(
                protect.isProtectSuccessful()
                || protect.isDownloadAvailable()
                || protect.isErrorMessageDisplayed(),
                "TC_PROTECT_010: No UI response to single-character password submission");
        log.info("TC_PROTECT_010 PASSED");
    }

    @Test(
        description = "TC_PROTECT_011 - Verify all key page-load elements with soft assertions",
        groups = {"regression"}
    )
    public void verifyProtectPageElementsSoftAssert() {
        ProtectPDFPage protect = new HomePage().open().goToProtectPDF();

        SoftAssertManager.get().assertTrue(protect.isPageDisplayed("title1"),
                "TC_PROTECT_011: Page heading not displayed");
        SoftAssertManager.get().assertTrue(protect.isSelectFilesButtonDisplayed(),
                "TC_PROTECT_011: Select Files button not displayed");
        SoftAssertManager.get().assertFalse(protect.isProtectButtonDisplayed(),
                "TC_PROTECT_011: Protect button should be hidden before any upload");
        SoftAssertManager.get().assertTrue(protect.getCurrentUrl().contains("protect-pdf"),
                "TC_PROTECT_011: URL does not contain 'protect-pdf'");

        SoftAssertManager.assertAll();
        log.info("TC_PROTECT_011 PASSED");
    }

    @Test(
        description = "TC_PROTECT_012 - Browser title contains expected keyword",
        groups = {"regression"}
    )
    public void verifyProtectPageTitle() {
        new HomePage().open().goToProtectPDF();

        String title = BrowserHelper.getTitle().toLowerCase();
        Assert.assertTrue(title.contains("protect") || title.contains("ilovepdf"),
                "TC_PROTECT_012: Page title '" + title + "' does not contain expected keyword");
        log.info("TC_PROTECT_012 PASSED");
    }
}
