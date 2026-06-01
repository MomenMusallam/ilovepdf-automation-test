package com.ilovepdf.pages;

import com.ilovepdf.configuration.ConfigReader;
import com.ilovepdf.helpers.BrowserHelper;
import org.openqa.selenium.By;

/**
 * Page Object for ilovepdf.com home page.
 *
 * Provides navigation to all tool pages used in this test suite.
 * Tool tile locators use href-based XPath to stay resilient to
 * text/label changes on the UI.
 */
public class HomePage extends BasePage {

    // ── PDF tool tiles ────────────────────────────────────────────────────────
	private final By mergePdfTile        = By.xpath("//div[@class='tools__item']//a[contains(@href, '/merge_pdf')]");
	private final By splitPdfTile        = By.xpath("//div[@class='tools__item']//a[contains(@href, '/split_pdf')]");
	private final By compressPdfTile     = By.xpath("//div[@class='tools__item']//a[contains(@href, '/compress_pdf')]");
	private final By pdfToWordTile       = By.xpath("//div[@class='tools__item']//a[contains(@href, '/pdf_to_word')]");
	private final By pdfToPowerPointTile = By.xpath("//div[@class='tools__item']//a[contains(@href, '/pdf_to_powerpoint')]");
	private final By pdfToExcelTile      = By.xpath("//div[@class='tools__item']//a[contains(@href, '/pdf_to_excel')]");
	private final By wordToPdfTile       = By.xpath("//div[@class='tools__item']//a[contains(@href, '/word_to_pdf')]");
	private final By powerPointToPdfTile = By.xpath("//div[@class='tools__item']//a[contains(@href, '/powerpoint_to_pdf')]");
	private final By excelToPdfTile      = By.xpath("//div[@class='tools__item']//a[contains(@href, '/excel_to_pdf')]");
	private final By editPdfTile         = By.xpath("//div[@class='tools__item']//a[contains(@href, '/edit_pdf')]");
	private final By pdfToJpgTile        = By.xpath("//div[@class='tools__item']//a[contains(@href, '/pdf_to_jpg')]");
	private final By jpgToPdfTile        = By.xpath("//div[@class='tools__item']//a[contains(@href, '/jpg_to_pdf')]");
	private final By rotatePdfTile       = By.xpath("//div[@class='tools__item']//a[contains(@href, '/rotate_pdf')]");
	private final By unlockPdfTile       = By.xpath("//div[@class='tools__item']//a[contains(@href, '/unlock_pdf')]");
	private final By protectPdfTile      = By.xpath("//div[@class='tools__item']//a[contains(@href, '/protect-pdf')]");
    private final By watermarkPdfTile    = By.xpath("//div[@class='tools__item']//a[contains(@href, '/pdf_add_watermark')]");


    // ── Header nav ────────────────────────────────────────────────────────────
    private final By loginButton         = By.cssSelector("a[href*='/signin']");
    private final By signupButton        = By.cssSelector("a[href*='/signup']");

    // ─────────────────────────────────────────────────────────────────────────
    // Navigation
    // ─────────────────────────────────────────────────────────────────────────

    public HomePage open() {
        BrowserHelper.navigateTo(ConfigReader.get("base.url"));
        log.info("Opened iLovePDF home page");
        return this;
    }

    // ── Tool page navigations ─────────────────────────────────────────────────

    // ── Tool page navigations ─────────────────────────────────────────────────

    public MergePDFPage goToMergePDF() {
        click(mergePdfTile);
        log.info("Navigated to Merge PDF page");
        return new MergePDFPage();
    }

    public SplitPDFPage goToSplitPDF() {
        click(splitPdfTile);
        log.info("Navigated to Split PDF page");
        return new SplitPDFPage();
    }

    public CompressPDFPage goToCompressPDF() {
        click(compressPdfTile);
        log.info("Navigated to Compress PDF page");
        return new CompressPDFPage();
    }

    public PDFToWordPage goToPDFToWord() {
        click(pdfToWordTile);
        log.info("Navigated to PDF to Word page");
        return new PDFToWordPage();
    }

    public PDFToPowerPointPage goToPDFToPowerPoint() {
        click(pdfToPowerPointTile);
        log.info("Navigated to PDF to PowerPoint page");
        return new PDFToPowerPointPage();
    }
//
//    public PDFToExcelPage goToPDFToExcel() {
//        click(pdfToExcelTile);
//        log.info("Navigated to PDF to Excel page");
//        return new PDFToExcelPage();
//    }
//
    public WordToPDFPage goToWordToPDF() {
        click(wordToPdfTile);
        log.info("Navigated to Word to PDF page");
        return new WordToPDFPage();
    }
//
//    public PowerPointToPDFPage goToPowerPointToPDF() {
//        click(powerPointToPdfTile);
//        log.info("Navigated to PowerPoint to PDF page");
//        return new PowerPointToPDFPage();
//    }

    public ExcelToPDFPage goToExcelToPDF() {
        click(excelToPdfTile);
        log.info("Navigated to Excel to PDF page");
        return new ExcelToPDFPage();
    }

//    public EditPDFPage goToEditPDF() {
//        click(editPdfTile);
//        log.info("Navigated to Edit PDF page");
//        return new EditPDFPage();
//    }
//
    public PDFToJPGPage goToPDFToJPG() {
        click(pdfToJpgTile);
        log.info("Navigated to PDF to JPG page");
        return new PDFToJPGPage();
    }
//
//    public JPGToPDFPage goToJPGToPDF() {
//        click(jpgToPdfTile);
//        log.info("Navigated to JPG to PDF page");
//        return new JPGToPDFPage();
//    }

    public RotatePDFPage goToRotatePDF() {
        click(rotatePdfTile);
        log.info("Navigated to Rotate PDF page");
        return new RotatePDFPage();
    }

    public UnlockPDFPage goToUnlockPDF() {
        click(unlockPdfTile);
        log.info("Navigated to Unlock PDF page");
        return new UnlockPDFPage();
    }

    public ProtectPDFPage goToProtectPDF() {
        click(protectPdfTile);
        log.info("Navigated to Protect PDF page");
        return new ProtectPDFPage();
    }
    
//    public WatermarkPDFPage goToWatermarkPDF() {
//        click(watermarkPdfTile);
//        log.info("Navigated to Watermark PDF page");
//        return new WatermarkPDFPage();
//    }

    // ─────────────────────────────────────────────────────────────────────────
    // State queries
    // ─────────────────────────────────────────────────────────────────────────

    public boolean isMergeTileDisplayed()        { return isDisplayed(mergePdfTile); }
    public boolean isSplitTileDisplayed()        { return isDisplayed(splitPdfTile); }
    public boolean isCompressTileDisplayed()     { return isDisplayed(compressPdfTile); }
    public boolean isPdfToWordTileDisplayed()    { return isDisplayed(pdfToWordTile); }
    public boolean isPdfToPptTileDisplayed()     { return isDisplayed(pdfToPowerPointTile); }
    public boolean isPdfToExcelTileDisplayed()   { return isDisplayed(pdfToExcelTile); }
    public boolean isWordToPdfTileDisplayed()    { return isDisplayed(wordToPdfTile); }
    public boolean isPptToPdfTileDisplayed()     { return isDisplayed(powerPointToPdfTile); }
    public boolean isExcelToPdfTileDisplayed()   { return isDisplayed(excelToPdfTile); }
    public boolean isEditPdfTileDisplayed()      { return isDisplayed(editPdfTile); }
    public boolean isPdfToJpgTileDisplayed()     { return isDisplayed(pdfToJpgTile); }
    public boolean isJpgToPdfTileDisplayed()     { return isDisplayed(jpgToPdfTile); }
    public boolean isRotatePdfTileDisplayed()    { return isDisplayed(rotatePdfTile); }
    public boolean isUnlockPdfTileDisplayed()    { return isDisplayed(unlockPdfTile); }
    public boolean isProtectPdfTileDisplayed()   { return isDisplayed(protectPdfTile); }
    public boolean isLoginButtonDisplayed()      { return isDisplayed(loginButton); }
    public boolean isSignupButtonDisplayed()     { return isDisplayed(signupButton); }
    public boolean isWatermarkPdfTileDisplayed()  { return isDisplayed(watermarkPdfTile); }

}
