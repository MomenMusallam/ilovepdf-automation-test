package com.ilovepdf.tests.regression;


import com.ilovepdf.base.BaseTest;
import com.ilovepdf.pages.HomePage;
import com.ilovepdf.pages.PDFToExcelPage;
import com.ilovepdf.utilities.SoftAssertManager;
import org.testng.Assert;
import org.testng.annotations.Test;


public class PDFToExcelTests extends BaseTest {
	
    @Test(
            description = "TC_PDF2E_001 - PDF to Excel page loads with correct URL",
            groups = {"regression", "smoke"}
        )
        public void verifyPdfToExcelPageLoads() {
            PDFToExcelPage page = new HomePage().open().goToPDFToExcel();

            Assert.assertTrue(page.isPageDisplayed(),
                    "TC_PDF2E_001: PDF to Excel page heading not displayed");
            Assert.assertTrue(page.getCurrentUrl().contains("pdf_to_excel"),
                    "TC_PDF2E_001: URL does not contain 'pdf_to_excel'");
            log.info("TC_PDF2E_001 PASSED");
        }

        @Test(
            description = "TC_PDF2E_002 - Select Files button displayed on PDF to Excel page",
            groups = {"regression"}
        )
        public void verifyPdfToExcelSelectFilesButtonDisplayed() {
            PDFToExcelPage page = new HomePage().open().goToPDFToExcel();

            Assert.assertTrue(page.isSelectFilesButtonDisplayed(),
                    "TC_PDF2E_002: Select Files button not displayed on PDF to Excel page");
            log.info("TC_PDF2E_002 PASSED");
        }

        @Test(
            description = "TC_PDF2E_003 - Convert button present on PDF to Excel page",
            groups = {"regression"}
        )
        public void verifyPdfToExcelConvertButtonPresent() {
            PDFToExcelPage page = new HomePage().open().goToPDFToExcel();
            page.uploadFile("sample1.pdf");

            Assert.assertTrue(page.isConvertButtonDisplayed(),
                    "TC_PDF2E_003: Convert button not displayed on PDF to Excel page");
            log.info("TC_PDF2E_003 PASSED");
        }

        @Test(
            description = "TC_PDF2E_004 - Upload PDF on PDF to Excel page and verify stability",
            groups = {"regression"}
        )
        public void verifyPdfToExcelUploadPdf() {
            PDFToExcelPage page = new HomePage().open().goToPDFToExcel();
            page.uploadFile("sample1.pdf");

            Assert.assertTrue(page.isPageDisplayed(),
                    "TC_PDF2E_004: Page not stable after file upload on PDF to Excel page");
            log.info("TC_PDF2E_004 PASSED");
        }

        @Test(
            description = "TC_PDF2E_005 - PDF to Excel: click Convert and verify success or download",
            groups = {"regression", "e2e"}
        )
        public void verifyPdfToExcelConversion() {
            PDFToExcelPage page = new HomePage().open().goToPDFToExcel();
            page.uploadFile("sample1.pdf").clickConvert();

            Assert.assertTrue(page.isConversionSuccessful() || page.isDownloadAvailable(),
                    "TC_PDF2E_005: PDF to Excel conversion did not complete");
            log.info("TC_PDF2E_005 PASSED");
        }

        @Test(
            description = "TC_PDF2E_006 - PDF to Excel: verify all key elements with soft assertions",
            groups = {"regression"}
        )
        public void verifyPdfToExcelPageElementsSoftAssert() {
            PDFToExcelPage page = new HomePage().open().goToPDFToExcel();

            SoftAssertManager.get().assertTrue(page.isPageDisplayed(),
                    "TC_PDF2E_006: Page heading not displayed");
            SoftAssertManager.get().assertTrue(page.isSelectFilesButtonDisplayed(),
                    "TC_PDF2E_006: Select Files button not displayed");
           
            SoftAssertManager.get().assertTrue(page.getCurrentUrl().contains("pdf_to_excel"),
                    "TC_PDF2E_006: URL does not contain 'pdf_to_excel'");

            SoftAssertManager.assertAll();
            log.info("TC_PDF2E_006 PASSED");
        }

}
