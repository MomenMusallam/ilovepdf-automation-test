package com.ilovepdf.tests.smoke;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.pages.HomePage;
import com.ilovepdf.utilities.SoftAssertManager;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Smoke test suite — validates that every tool tile on the home page is
 * reachable and its destination page loads correctly.
 *
 * Features covered in site order:
 *   Merge PDF | Split PDF | Compress PDF
 *   Word to PDF | PowerPoint to PDF | Excel to PDF
 *   PDF to Word | PDF to PowerPoint | PDF to Excel | PDF to JPG
 *   Rotate PDF | Protect PDF | Unlock PDF
 *
 * ┌──────────────┬──────────────────────────────────────────────────────────┐
 * │ TC ID        │ Scenario                                                 │
 * ├──────────────┼──────────────────────────────────────────────────────────┤
 * │ TC_NAV_001   │ Home page loads with all 13 tool tiles visible           │
 * │ TC_NAV_002   │ Navigate to Merge PDF page                               │
 * │ TC_NAV_003   │ Navigate to Split PDF page                               │
 * │ TC_NAV_004   │ Navigate to Compress PDF page                            │
 * │ TC_NAV_005   │ Navigate to Word to PDF page                             │
 * │ TC_NAV_006   │ Navigate to PowerPoint to PDF page                       │
 * │ TC_NAV_007   │ Navigate to Excel to PDF page                            │
 * │ TC_NAV_008   │ Navigate to PDF to Word page                             │
 * │ TC_NAV_009   │ Navigate to PDF to PowerPoint page                       │
 * │ TC_NAV_010   │ Navigate to PDF to Excel page                            │
 * │ TC_NAV_011   │ Navigate to PDF to JPG page                              │
 * │ TC_NAV_012   │ Navigate to Rotate PDF page                              │
 * │ TC_NAV_013   │ Navigate to Protect PDF page                             │
 * │ TC_NAV_014   │ Navigate to Unlock PDF page                              │
 * │ TC_NAV_015   │ All 13 tool tiles visible from home (soft assert)        │
 * └──────────────┴──────────────────────────────────────────────────────────┘
 */
public class NavigationSmokeTest extends BaseTest {

    // ─────────────────────────────────────────────────────────────────────────
    // TC_NAV_001 — Home page loads with all 13 tool tiles visible
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_NAV_001 - Verify home page loads with all main tool tiles", groups = {"smoke"})
    public void verifyHomePageLoadsWithTools() {
        HomePage home = new HomePage().open();

        Assert.assertTrue(home.isMergeTileDisplayed(),    "TC_NAV_001: Merge tile not displayed");
        Assert.assertTrue(home.isSplitTileDisplayed(),    "TC_NAV_001: Split tile not displayed");
        Assert.assertTrue(home.isCompressTileDisplayed(), "TC_NAV_001: Compress tile not displayed");
        log.info("TC_NAV_001 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_NAV_002 — Navigate to Merge PDF page
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_NAV_002 - Verify navigation to Merge PDF page", groups = {"smoke"})
    public void verifyNavigationToMergePage() {
        Assert.assertTrue(
                new HomePage().open().goToMergePDF().isPageDisplayed("title1"),
                "TC_NAV_002: Merge PDF page not displayed");
        log.info("TC_NAV_002 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_NAV_003 — Navigate to Split PDF page
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_NAV_003 - Verify navigation to Split PDF page", groups = {"smoke"})
    public void verifyNavigationToSplitPage() {
        Assert.assertTrue(
                new HomePage().open().goToSplitPDF().isPageDisplayed("title1"),
                "TC_NAV_003: Split PDF page not displayed");
        log.info("TC_NAV_003 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_NAV_004 — Navigate to Compress PDF page
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_NAV_004 - Verify navigation to Compress PDF page", groups = {"smoke"})
    public void verifyNavigationToCompressPage() {
        Assert.assertTrue(
                new HomePage().open().goToCompressPDF().isPageDisplayed(),
                "TC_NAV_004: Compress PDF page not displayed");
        log.info("TC_NAV_004 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_NAV_005 — Navigate to Word to PDF page
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_NAV_005 - Verify navigation to Word to PDF page", groups = {"smoke"})
    public void verifyNavigationToWordToPdfPage() {
        Assert.assertTrue(
                new HomePage().open().goToWordToPDF().isPageDisplayed("title1"),
                "TC_NAV_005: Word to PDF page not displayed");
        log.info("TC_NAV_005 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_NAV_006 — Navigate to PowerPoint to PDF page
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_NAV_006 - Verify navigation to PowerPoint to PDF page", groups = {"smoke"})
    public void verifyNavigationToPowerPointToPdfPage() {
        Assert.assertTrue(
                new HomePage().open().goToPowerPointToPDF().isPageDisplayed(),
                "TC_NAV_006: PowerPoint to PDF page not displayed");
        log.info("TC_NAV_006 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_NAV_007 — Navigate to Excel to PDF page
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_NAV_007 - Verify navigation to Excel to PDF page", groups = {"smoke"})
    public void verifyNavigationToExcelToPdfPage() {
        Assert.assertTrue(
                new HomePage().open().goToExcelToPDF().isPageDisplayed("title1"),
                "TC_NAV_007: Excel to PDF page not displayed");
        log.info("TC_NAV_007 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_NAV_008 — Navigate to PDF to Word page
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_NAV_008 - Verify navigation to PDF to Word page", groups = {"smoke"})
    public void verifyNavigationToPdfToWordPage() {
        Assert.assertTrue(
                new HomePage().open().goToPDFToWord().isPageDisplayed("title1"),
                "TC_NAV_008: PDF to Word page not displayed");
        log.info("TC_NAV_008 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_NAV_009 — Navigate to PDF to PowerPoint page
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_NAV_009 - Verify navigation to PDF to PowerPoint page", groups = {"smoke"})
    public void verifyNavigationToPdfToPowerPointPage() {
        Assert.assertTrue(
                new HomePage().open().goToPDFToPowerPoint().isPageDisplayed("title1"),
                "TC_NAV_009: PDF to PowerPoint page not displayed");
        log.info("TC_NAV_009 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_NAV_010 — Navigate to PDF to Excel page
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_NAV_010 - Verify navigation to PDF to Excel page", groups = {"smoke"})
    public void verifyNavigationToPdfToExcelPage() {
        Assert.assertTrue(
                new HomePage().open().goToPDFToExcel().isPageDisplayed(),
                "TC_NAV_010: PDF to Excel page not displayed");
        log.info("TC_NAV_010 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_NAV_011 — Navigate to PDF to JPG page
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_NAV_011 - Verify navigation to PDF to JPG page", groups = {"smoke"})
    public void verifyNavigationToPdfToJpgPage() {
        Assert.assertTrue(
                new HomePage().open().goToPDFToJPG().isPageDisplayed("title1"),
                "TC_NAV_011: PDF to JPG page not displayed");
        log.info("TC_NAV_011 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_NAV_012 — Navigate to Rotate PDF page
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_NAV_012 - Verify navigation to Rotate PDF page", groups = {"smoke"})
    public void verifyNavigationToRotatePdfPage() {
        Assert.assertTrue(
                new HomePage().open().goToRotatePDF().isPageDisplayed("title1"),
                "TC_NAV_012: Rotate PDF page not displayed");
        log.info("TC_NAV_012 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_NAV_013 — Navigate to Protect PDF page
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_NAV_013 - Verify navigation to Protect PDF page", groups = {"smoke"})
    public void verifyNavigationToProtectPdfPage() {
        Assert.assertTrue(
                new HomePage().open().goToProtectPDF().isPageDisplayed("title1"),
                "TC_NAV_013: Protect PDF page not displayed");
        log.info("TC_NAV_013 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_NAV_014 — Navigate to Unlock PDF page
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_NAV_014 - Verify navigation to Unlock PDF page", groups = {"smoke"})
    public void verifyNavigationToUnlockPdfPage() {
        Assert.assertTrue(
                new HomePage().open().goToUnlockPDF().isPageDisplayed("title1"),
                "TC_NAV_014: Unlock PDF page not displayed");
        log.info("TC_NAV_014 PASSED");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TC_NAV_015 — All 13 tool tiles visible from home (soft assert)
    // ─────────────────────────────────────────────────────────────────────────
    @Test(description = "TC_NAV_015 - All 13 tool tiles visible on home page (soft assert)", groups = {"smoke"})
    public void verifyAllToolTilesDisplayed() {
        HomePage home = new HomePage().open();

        SoftAssertManager.get().assertTrue(home.isMergeTileDisplayed(),           "TC_NAV_015: Merge PDF tile missing");
        SoftAssertManager.get().assertTrue(home.isSplitTileDisplayed(),           "TC_NAV_015: Split PDF tile missing");
        SoftAssertManager.get().assertTrue(home.isCompressTileDisplayed(),        "TC_NAV_015: Compress PDF tile missing");
        SoftAssertManager.get().assertTrue(home.isWordToPdfTileDisplayed(),       "TC_NAV_015: Word to PDF tile missing");
        SoftAssertManager.get().assertTrue(home.isPptToPdfTileDisplayed(), "TC_NAV_015: PowerPoint to PDF tile missing");
        SoftAssertManager.get().assertTrue(home.isExcelToPdfTileDisplayed(),      "TC_NAV_015: Excel to PDF tile missing");
        SoftAssertManager.get().assertTrue(home.isPdfToWordTileDisplayed(),       "TC_NAV_015: PDF to Word tile missing");
        SoftAssertManager.get().assertTrue(home.isPdfToPptTileDisplayed(), "TC_NAV_015: PDF to PowerPoint tile missing");
        SoftAssertManager.get().assertTrue(home.isPdfToExcelTileDisplayed(),      "TC_NAV_015: PDF to Excel tile missing");
        SoftAssertManager.get().assertTrue(home.isPdfToJpgTileDisplayed(),        "TC_NAV_015: PDF to JPG tile missing");
        SoftAssertManager.get().assertTrue(home.isRotatePdfTileDisplayed(),       "TC_NAV_015: Rotate PDF tile missing");
        SoftAssertManager.get().assertTrue(home.isProtectPdfTileDisplayed(),      "TC_NAV_015: Protect PDF tile missing");
        SoftAssertManager.get().assertTrue(home.isUnlockPdfTileDisplayed(),       "TC_NAV_015: Unlock PDF tile missing");

        SoftAssertManager.assertAll();
        log.info("TC_NAV_015 PASSED");
    }
}
