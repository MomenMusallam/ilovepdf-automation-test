package com.ilovepdf.tests.smoke;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationSmokeTest extends BaseTest {

    @Test(description = "Verify home page loads with all main tool tiles", groups = {"smoke"})
    public void verifyHomePageLoadsWithTools() {
        HomePage home = new HomePage().open();
        Assert.assertTrue(home.isMergeTileDisplayed(), "Merge tile not displayed");
        Assert.assertTrue(home.isSplitTileDisplayed(), "Split tile not displayed");
        Assert.assertTrue(home.isCompressTileDisplayed(), "Compress tile not displayed");
    }

    @Test(description = "Verify navigation to Merge PDF page", groups = {"smoke"})
    public void verifyNavigationToMergePage() {
        Assert.assertTrue(new HomePage().open().goToMergePDF().isPageDisplayed(),
                "Merge page not displayed");
    }

    @Test(description = "Verify navigation to Split PDF page", groups = {"smoke"})
    public void verifyNavigationToSplitPage() {
        Assert.assertTrue(new HomePage().open().goToSplitPDF().isPageDisplayed(),
                "Split page not displayed");
    }

    @Test(description = "Verify navigation to Compress PDF page", groups = {"smoke"})
    public void verifyNavigationToCompressPage() {
        Assert.assertTrue(new HomePage().open().goToCompressPDF().isPageDisplayed(),
                "Compress page not displayed");
    }
}
