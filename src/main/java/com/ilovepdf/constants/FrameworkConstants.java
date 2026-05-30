package com.ilovepdf.constants;

public final class FrameworkConstants {

    private FrameworkConstants() {}

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/test/resources/";
    private static final String CONFIG_PATH = RESOURCES_PATH + "config/config.properties";
    private static final String EXTENT_REPORT_FOLDER = System.getProperty("user.dir") + "/reports/";
    private static final String SCREENSHOT_FOLDER = System.getProperty("user.dir") + "/screenshots/";
    private static final String DOWNLOAD_FOLDER = System.getProperty("user.dir") + "/downloads/";
    private static final String EXCEL_PATH = RESOURCES_PATH + "testdata/excel/TestData.xlsx";
    private static final String JSON_PATH = RESOURCES_PATH + "testdata/json/testdata.json";
    private static final String PDF_TESTDATA_PATH = RESOURCES_PATH + "testdata/pdf/";

    private static final int IMPLICIT_WAIT = 10;
    private static final int EXPLICIT_WAIT = 10;
    private static final int PAGE_LOAD_TIMEOUT = 30;
    private static final int RETRY_COUNT = 2;

    public static String getConfigPath() { return CONFIG_PATH; }
    public static String getExtentReportFolder() { return EXTENT_REPORT_FOLDER; }
    public static String getScreenshotFolder() { return SCREENSHOT_FOLDER; }
    public static String getDownloadFolder() { return DOWNLOAD_FOLDER; }
    public static String getExcelPath() { return EXCEL_PATH; }
    public static String getJsonPath() { return JSON_PATH; }
    public static String getPdfTestDataPath() { return PDF_TESTDATA_PATH; }
    public static int getImplicitWait() { return IMPLICIT_WAIT; }
    public static int getExplicitWait() { return EXPLICIT_WAIT; }
    public static int getPageLoadTimeout() { return PAGE_LOAD_TIMEOUT; }
    public static int getRetryCount() { return RETRY_COUNT; }
}
