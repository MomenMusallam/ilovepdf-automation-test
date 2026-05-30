package com.ilovepdf.tests.smoke;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.helpers.BrowserHelper;
import com.ilovepdf.pages.HomePage;
import com.ilovepdf.pages.MergePDFPage;
import com.ilovepdf.utilities.SoftAssertManager;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * SAMPLE TEST — demonstrates how to use this framework
 *
 * Shows:
 *   - Page Object navigation (fluent chain)
 *   - Hard assertions (Assert.assertX)
 *   - Soft assertions (SoftAssertManager)
 *   - Browser helpers
 *   - Logging via BaseTest
 */
public class SampleUsageTest extends BaseTest {

    @Test(description = "Sample: Verify home page basics", groups = {"smoke"})
    public void sampleHomePageTest() {
        log.info("Starting sample home page test");

        HomePage home = new HomePage().open();

        Assert.assertTrue(home.isMergeTileDisplayed(), "Merge tile missing");
        Assert.assertTrue(BrowserHelper.getTitle().toLowerCase().contains("ilovepdf"),
                "Wrong page title");

        log.info("Sample home page test PASSED");
    }

    @Test(description = "Sample: Soft assertion demo on Merge page", groups = {"smoke"})
    public void sampleSoftAssertTest() {
        log.info("Starting soft assertion sample");

        MergePDFPage merge = new HomePage().open().goToMergePDF();

        SoftAssertManager.get().assertTrue(merge.isPageDisplayed(),
                "Merge page not displayed");
        SoftAssertManager.get().assertTrue(merge.isSelectFilesButtonDisplayed(),
                "Select files button not displayed");
        SoftAssertManager.get().assertTrue(BrowserHelper.getCurrentUrl().contains("merge"),
                "URL does not contain 'merge'");

        SoftAssertManager.assertAll();
        log.info("Sample soft assertion test PASSED");
    }
}
