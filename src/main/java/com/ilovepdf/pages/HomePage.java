package com.ilovepdf.pages;

import com.ilovepdf.configuration.ConfigReader;
import com.ilovepdf.helpers.BrowserHelper;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final By mergePdfTile = By.xpath("//a[contains(@href, '/merge_pdf')]");
    private final By splitPdfTile = By.xpath("//a[contains(@href, '/split_pdf')]");
    private final By compressPdfTile = By.xpath("//a[contains(@href, '/compress_pdf')]");
    private final By pdfToWordTile = By.xpath("//a[contains(@href, '/pdf_to_word')]");
    private final By wordToPdfTile = By.xpath("//a[contains(@href, '/word_to_pdf')]");
    private final By pdfToJpgTile = By.xpath("//a[contains(@href, '/pdf_to_jpg')]");
    private final By jpgToPdfTile = By.xpath("//a[contains(@href, '/jpg_to_pdf')]");
    private final By rotatePdfTile = By.xpath("//a[contains(@href, '/rotate_pdf')]");
    private final By watermarkPdfTile = By.xpath("//a[contains(@href, '/watermark_pdf')]");
    private final By unlockPdfTile = By.xpath("//a[contains(@href, '/unlock_pdf')]");
    private final By protectPdfTile = By.xpath("//a[contains(@href, '/protect_pdf')]");
    private final By organizePdfTile = By.xpath("//a[contains(@href, '/organize_pdf')]");
    private final By loginButton = By.cssSelector("a[href*='/signin']");
    private final By signupButton = By.cssSelector("a[href*='/signup']");
    private final By pptToPdfTile = By.xpath("//a[contains(@href, '/powerpoint_to_pdf')]");
    public HomePage open() {
        BrowserHelper.navigateTo(ConfigReader.get("base.url"));
        log.info("Opened iLovePDF home page");
        return this;
    }

    public MergePDFPage goToMergePDF() {
        click(mergePdfTile);
        return new MergePDFPage();
    }

    public SplitPDFPage goToSplitPDF() {
        click(splitPdfTile);
        return new SplitPDFPage();
    }

    public CompressPDFPage goToCompressPDF() {
        click(compressPdfTile);
        return new CompressPDFPage();
    }

    public PDFToWordPage goToPDFToWord() {
        click(pdfToWordTile);
        return new PDFToWordPage();
    }

    
    public PowerPointToPDFPage goToPowerPointToPDF() {
        BrowserHelper.navigateTo(ConfigReader.get("base.url") + "/powerpoint_to_pdf");
        return new PowerPointToPDFPage();
    }
    
    
    public boolean isMergeTileDisplayed() { return isDisplayed(mergePdfTile); }
    public boolean isSplitTileDisplayed() { return isDisplayed(splitPdfTile); }
    public boolean isCompressTileDisplayed() { return isDisplayed(compressPdfTile); }
    public boolean isLoginButtonDisplayed() { return isDisplayed(loginButton); }
}
