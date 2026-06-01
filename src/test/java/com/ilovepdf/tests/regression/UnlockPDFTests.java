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
 * Regression test suite for: Unlock PDF
 *
 * ════════════════════════════════════════════════════════════════════════════
 * UNLOCK PDF — TC_UNLOCK_001 to TC_UNLOCK_009
 * ════════════════════════════════════════════════════════════════════════════
 *
 * +------------------+------------------------------------------------------+
 * | TC ID            | Scenario                                             |
 * +------------------+------------------------------------------------------+
 * | TC_UNLOCK_001    | Page loads with correct URL                          |
 * | TC_UNLOCK_002    | Select Files button displayed on page load           |
 * | TC_UNLOCK_003    | Unlock button NOT visible before upload              |
 * | TC_UNLOCK_004    | Unlock button appears after uploading a file         |
 * | TC_UNLOCK_005    | Upload a non-protected PDF — page stable             |
 * | TC_UNLOCK_006    | Upload a non-protected PDF → success or download     |
 * | TC_UNLOCK_007    | All page-load elements present (soft assert)         |
 * | TC_UNLOCK_008    | Browser title contains expected keyword              |
 * | TC_UNLOCK_009    | Upload protected PDF and click Unlock                |
 * +------------------+------------------------------------------------------+
 *
 * Preconditions:
 * - File: locked_sample.pdf (password-protected PDF)
 * - Used in: TC_UNLOCK_007 / TC_UNLOCK_008 / TC_UNLOCK_009
 * - Password: test1234
 */
public class UnlockPDFTests extends BaseTest {

	// ═════════════════════════════════════════════════════════════════════════
	// UNLOCK PDF
	// ═════════════════════════════════════════════════════════════════════════

	@Test(description = "TC_UNLOCK_001 - Unlock PDF page loads with correct URL", groups = { "regression", "smoke" })
	public void verifyUnlockPageLoads() {
		UnlockPDFPage unlock = new HomePage().open().goToUnlockPDF();

		Assert.assertTrue(unlock.isPageDisplayed("title1"), "TC_UNLOCK_001: Unlock PDF page heading not displayed");
		Assert.assertTrue(unlock.getCurrentUrl().contains("unlock_pdf"),
				"TC_UNLOCK_001: URL does not contain 'unlock_pdf'");
		log.info("TC_UNLOCK_001 PASSED");
	}

	@Test(description = "TC_UNLOCK_002 - Select Files button displayed on Unlock PDF page load", groups = {
			"regression" })
	public void verifyUnlockSelectFilesButtonDisplayed() {
		UnlockPDFPage unlock = new HomePage().open().goToUnlockPDF();

		Assert.assertTrue(unlock.isSelectFilesButtonDisplayed(),
				"TC_UNLOCK_002: Select Files button not displayed on page load");
		log.info("TC_UNLOCK_002 PASSED");
	}

	// ── TC_UNLOCK_003 — Unlock button NOT visible before upload ───────────────
	@Test(description = "TC_UNLOCK_003 - Unlock PDF button is NOT visible before any file is uploaded", groups = {
			"regression" })
	public void verifyUnlockButtonNotVisibleBeforeUpload() {
		UnlockPDFPage unlock = new HomePage().open().goToUnlockPDF();

		Assert.assertFalse(unlock.isUnlockButtonDisplayed(),
				"TC_UNLOCK_003: Unlock button should NOT be visible before any file is uploaded");
		log.info("TC_UNLOCK_003 PASSED");
	}

	// ── TC_UNLOCK_004 — Unlock button appears after upload ────────────────────
	@Test(description = "TC_UNLOCK_004 - Unlock PDF button becomes visible after uploading a file", groups = {
			"regression" })
	public void verifyUnlockButtonAppearsAfterUpload() {
		UnlockPDFPage unlock = new HomePage().open().goToUnlockPDF();

		Assert.assertFalse(unlock.isUnlockButtonDisplayed(),
				"TC_UNLOCK_004: Pre-condition failed — Unlock button visible before upload");

		unlock.uploadFile("sample1.pdf");

		Assert.assertTrue(unlock.isUnlockButtonDisplayed(),
				"TC_UNLOCK_004: Unlock button did not appear after uploading a file");
		log.info("TC_UNLOCK_004 PASSED");
	}

	@Test(description = "TC_UNLOCK_005 - Upload a non-protected PDF — page remains stable", groups = { "regression" })
	public void verifyUploadNonProtectedPdf() {
		UnlockPDFPage unlock = new HomePage().open().goToUnlockPDF();
		unlock.uploadFile("sample1.pdf");

		Assert.assertTrue(unlock.isPageDisplayed("title2"),
				"TC_UNLOCK_005: Page not stable after uploading non-protected PDF");
		log.info("TC_UNLOCK_005 PASSED");
	}

	@Test(description = "TC_UNLOCK_006 - Upload non-protected PDF and click Unlock → success or download", groups = {
			"regression", "e2e" })
	public void verifyUnlockNonProtectedPdf() {
		UnlockPDFPage unlock = new HomePage().open().goToUnlockPDF();
		unlock.uploadFile("sample1.pdf").clickUnlock();

		Assert.assertTrue(unlock.isUnlockSuccessful() || unlock.isDownloadAvailable(),
				"TC_UNLOCK_006: Unlock did not complete for non-protected PDF");
		log.info("TC_UNLOCK_006 PASSED");
	}

	@Test(description = "TC_UNLOCK_007 - Verify all key page-load elements with soft assertions", groups = {
			"regression" })
	public void verifyUnlockPageElementsSoftAssert() {
		UnlockPDFPage unlock = new HomePage().open().goToUnlockPDF();

		SoftAssertManager.get().assertTrue(unlock.isPageDisplayed("title1"),
				"TC_UNLOCK_011: Page heading not displayed");
		SoftAssertManager.get().assertTrue(unlock.isSelectFilesButtonDisplayed(),
				"TC_UNLOCK_011: Select Files button not displayed");
		SoftAssertManager.get().assertFalse(unlock.isUnlockButtonDisplayed(),
				"TC_UNLOCK_011: Unlock button should be hidden before any upload");
		SoftAssertManager.get().assertTrue(unlock.getCurrentUrl().contains("unlock_pdf"),
				"TC_UNLOCK_011: URL does not contain 'unlock_pdf'");

		SoftAssertManager.assertAll();
		log.info("TC_UNLOCK_011 PASSED");
	}

	@Test(description = "TC_UNLOCK_008 - Browser title contains expected keyword", groups = { "regression" })
	public void verifyUnlockPageTitle() {
		new HomePage().open().goToUnlockPDF();

		String title = BrowserHelper.getTitle().toLowerCase();
		Assert.assertTrue(title.contains("unlock") || title.contains("ilovepdf"),
				"TC_UNLOCK_012: Page title '" + title + "' does not contain expected keyword");
		log.info("TC_UNLOCK_012 PASSED");
	}

	@Test(description = "TC_UNLOCK_009 - Upload a protected PDF", groups = { "regression" })
	public void verifyUploadProtectedPdf() {
		UnlockPDFPage unlock = new HomePage().open().goToUnlockPDF();
		unlock.uploadFile("locked_sample.pdf").clickUnlock();

		Assert.assertTrue(unlock.isUnlockSuccessful() || unlock.isDownloadAvailable(),
				"TC_UNLOCK_006: Unlock did not complete for non-protected PDF");
		log.info("TC_UNLOCK_009 PASSED");
	}

}
