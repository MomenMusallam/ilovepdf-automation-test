package com.ilovepdf.base;

import com.ilovepdf.configuration.ConfigReader;
import com.ilovepdf.drivers.DriverFactory;
import com.ilovepdf.drivers.DriverManager;
import com.ilovepdf.utilities.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {

    protected static final Logger log = LoggerUtil.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional String browser) {
        String browserToUse = (browser == null) ? ConfigReader.get("browser") : browser;
        log.info("Initializing driver: {}", browserToUse);
        DriverFactory.initDriver(browserToUse);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Closing browser");
        DriverManager.unloadDriver();
    }
}
