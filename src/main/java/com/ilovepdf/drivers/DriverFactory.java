package com.ilovepdf.drivers;

import com.ilovepdf.configuration.ConfigReader;
import com.ilovepdf.constants.FrameworkConstants;
import com.ilovepdf.enums.BrowserType;
import com.ilovepdf.exceptions.FrameworkException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public final class DriverFactory {

    private DriverFactory() {}

    public static WebDriver initDriver(String browser) {
        WebDriver driver;
        BrowserType type = BrowserType.valueOf(browser.toUpperCase());

        switch (type) {
            case CHROME -> driver = createChromeDriver();
            case FIREFOX -> driver = createFirefoxDriver();
            case EDGE -> driver = createEdgeDriver();
            default -> throw new FrameworkException("Browser not supported: " + browser);
        }

        configureDriver(driver);
        DriverManager.setDriver(driver);
        return driver;
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        
        String downloadPath = new java.io.File(FrameworkConstants.getDownloadFolder()).getAbsolutePath();
        prefs.put("download.default_directory", downloadPath);
        
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        
        prefs.put("plugins.always_open_pdf_externally", true);
        
        options.setExperimentalOption("prefs", prefs);

        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--no-sandbox");
        
        return new ChromeDriver(options);
    }
    
    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();

        if (ConfigReader.getBoolean("headless")) {
            options.addArguments("-headless");
        }

        options.addPreference("browser.download.dir", FrameworkConstants.getDownloadFolder());
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");

        return new FirefoxDriver(options);
    }

    private static WebDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();

        if (ConfigReader.getBoolean("headless")) {
            options.addArguments("--headless=new");
        }

        return new EdgeDriver(options);
    }

    private static void configureDriver(WebDriver driver) {
        if (ConfigReader.getBoolean("maximize.window")) {
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(FrameworkConstants.getImplicitWait()));
        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(FrameworkConstants.getPageLoadTimeout()));
        driver.manage().deleteAllCookies();
    }
}
