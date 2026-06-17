# iLovePDF Automation Framework

Enterprise-grade Selenium WebDriver + TestNG automation framework for [ilovepdf.com](https://www.ilovepdf.com).

---

## Team

| Name | Role |
|------|------|
| Momen Musallam | Team Leader & QA Engineer |
| Rozan Abu Kwiak | QA Engineer |
| Qusay Terawi | QA Engineer |
| Alaa Abu-Duqa | QA Engineer |
| Safa Abu-Hadeed | QA Engineer |


---

## Tech Stack

| Library | Version |
|---------|---------|
| Java | 21 |
| Selenium WebDriver | 4.20.0 |
| TestNG | 7.10.2 |
| Maven | 3.x |
| WebDriverManager | 5.8.0 |
| ExtentReports | 5.1.1 |
| Apache POI | 5.2.5 |
| Log4j2 | 2.23.1 |
| Jackson | 2.17.0 |
| JavaFaker | 1.0.2 |
| Commons IO | 2.16.1 |

---

## Project Structure

```
ilovepdf-automation-framework/
├── pom.xml
├── README.md
├── .gitignore
│
├── src/
│   ├── main/
│   │   ├── java/com/ilovepdf/
│   │   │   ├── configuration/        # ConfigReader
│   │   │   ├── constants/            # FrameworkConstants
│   │   │   ├── drivers/              # DriverFactory, DriverManager (ThreadLocal)
│   │   │   ├── enums/                # BrowserType, Environment, WaitStrategy
│   │   │   ├── exceptions/           # FrameworkException
│   │   │   ├── helpers/              # Click, Input, File, Window, JS, Alert, Frame, Scroll, Screenshot, Dropdown, Validation, Browser, Element helpers
│   │   │   ├── listeners/            # TestListener, RetryAnalyzer, AnnotationTransformer
│   │   │   ├── pages/                # Page Object Model — one class per page
│   │   │   ├── reports/              # ExtentReportManager
│   │   │   └── utilities/            # WaitUtil, ExcelUtil, JsonUtil, LoggerUtil, SoftAssertManager, RandomDataGenerator
│   │   └── resources/
│   │       └── log4j2.xml
│   │
│   └── test/
│       ├── java/com/ilovepdf/
│       │   ├── base/                 # BaseTest (setUp / tearDown)
│       │   ├── dataproviders/        # ExcelDataProvider, JsonDataProvider
│       │   └── tests/
│       │       ├── smoke/            # NavigationSmokeTest
│       │       ├── regression/       # One class per feature (14 classes)
│       │       └── e2e/              # PDFWorkflowE2ETest
│       └── resources/
│           ├── config/
│           │   └── config.properties
│           ├── testdata/
│           │   ├── excel/TestData.xlsx
│           │   ├── json/testdata.json
│           │   └── pdf/              # Sample files for upload tests
│           │       ├── sample1.pdf
│           │       ├── sample2.pdf
│           │       ├── sample3.pdf
│           │       ├── sample1.docx
│           │       ├── sample1.pptx
│           │       ├── sample1.xlsx
│           │       ├── sample1.jpg
│           │       ├── sample2.jpg
│           │       ├── locked_sample.pdf   # Password: test1234
│           │       ├── empty.pdf
│           │       ├── empty.ppt
│           │       ├── large.pptx
│           │       └── image.jpg
│           └── suites/
│               ├── smoke.xml
│               ├── sanity.xml
│               ├── regression.xml
│               └── e2e.xml
│
├── logs/
├── reports/
├── screenshots/
└── downloads/
```

---

## Package Responsibilities

| Package | Class(es) | Purpose |
|---------|-----------|---------|
| `configuration` | `ConfigReader` | Reads `config.properties` into static accessors |
| `constants` | `FrameworkConstants` | Centralized paths, timeouts, retry counts |
| `drivers` | `DriverFactory`, `DriverManager` | DriverFactory creates drivers; DriverManager stores ThreadLocal driver |
| `enums` | `BrowserType`, `WaitStrategy`, `Environment` | Type-safe enums for browser, wait strategies, environments |
| `exceptions` | `FrameworkException` | Custom exception for clear framework-level error context |
| `helpers` | 13 helper classes | Reusable actions — Click, Input, File, Window, Frame, JS, Scroll, Screenshot, Dropdown, Alert, Validation, Browser, Element |
| `listeners` | `TestListener`, `RetryAnalyzer`, `AnnotationTransformer` | Logging, ExtentReport hooks, screenshot on failure, automatic retry |
| `pages` | 17 page classes | Page Object Model — one class per iLovePDF tool page |
| `reports` | `ExtentReportManager` | HTML reports with timestamped filenames and screenshot attachment |
| `utilities` | `WaitUtil`, `ExcelUtil`, `JsonUtil`, `LoggerUtil`, `SoftAssertManager`, `RandomDataGenerator` | Waits, data reading, logging, soft assertions |
| `base` | `BaseTest` | `@BeforeMethod` / `@AfterMethod` — driver init and teardown for all tests |
| `dataproviders` | `ExcelDataProvider`, `JsonDataProvider` | Data-driven TestNG DataProviders |

---

## Page Objects

| Class | URL Segment | Feature |
|-------|-------------|---------|
| `HomePage` | `/` | Home page — navigation to all tools |
| `LoginPage` | `/login` | Authentication |
| `MergePDFPage` | `/merge_pdf` | Merge PDF |
| `SplitPDFPage` | `/split_pdf` | Split PDF |
| `CompressPDFPage` | `/compress_pdf` | Compress PDF |
| `WordToPDFPage` | `/word_to_pdf` | Word to PDF |
| `PowerPointToPDFPage` | `/powerpoint_to_pdf` | PowerPoint to PDF |
| `ExcelToPDFPage` | `/excel_to_pdf` | Excel to PDF |
| `PDFToWordPage` | `/pdf_to_word` | PDF to Word |
| `PDFToPowerPointPage` | `/pdf_to_powerpoint` | PDF to PowerPoint |
| `PDFToExcelPage` | `/pdf_to_excel` | PDF to Excel |
| `PDFToJPGPage` | `/pdf_to_jpg` | PDF to JPG |
| `RotatePDFPage` | `/rotate_pdf` | Rotate PDF |
| `ProtectPDFPage` | `/protect-pdf` | Protect PDF |
| `UnlockPDFPage` | `/unlock_pdf` | Unlock PDF |
| `WatermarkPDFPage` | `/watermark_pdf` | Watermark PDF |
| `JPGToPDFPage` | `/jpg_to_pdf` | JPG to PDF |

> **Key design rule:** Every tool's action button (`#processTask`) is **hidden on page load** and only appears after a file is uploaded. All tests assert `assertFalse(isButtonDisplayed())` before upload and `assertTrue(isButtonDisplayed())` after upload.

---

## Test Classes

### Smoke — `com.ilovepdf.tests.smoke`

| Class | Tests | Description |
|-------|-------|-------------|
| `NavigationSmokeTest` | 15 | Verifies home page loads and all 13 tool pages are navigable |

### Regression — `com.ilovepdf.tests.regression`

| Class | TC Range | Count |
|-------|----------|-------|
| `AuthenticationTests` | — | 3 |
| `MergePDFTests` | TC_MERGE_001–010 | 10 |
| `SplitPDFTests` | TC_SPLIT_001–011 | 11 |
| `CompressPDFTests` | TC_COMPRESS_001–013 | 13 |
| `WordToPDFTests` | TC_W2PDF_001–007 + 003b | 8 |
| `PowerPointToPDFTests` | TC-07 – TC-18 | 12 |
| `ExcelToPDFTests` | TC_XLS2PDF_001–006 + 003b | 7 |
| `PDFToWordTests` | TC_PDF2W_001–007 + 003b | 8 |
| `PDFToPowerPointTests` | TC_PDF2P_001–006 + 003b | 7 |
| `PDFToExcelTests` | TC_PDF2E_001–006 | 6 |
| `PDFToJPGTests` | TC_PDF2J_001–010 + 003b | 11 |
| `RotatePDFTests` | TC_ROTATE_001–012 | 12 |
| `ProtectPDFTests` | TC_PROTECT_001–012 | 12 |
| `UnlockPDFTests` | TC_UNLOCK_001–009 | 9 |

### E2E — `com.ilovepdf.tests.e2e`

| Class | TC Range | Count |
|-------|----------|-------|
| `PDFWorkflowE2ETest` | TC_E2E_001–014 | 14 |

---

## Test Suites

### `smoke.xml` — Fast smoke gate
Runs all `groups = {"smoke"}` tagged tests across all 14 feature classes + `NavigationSmokeTest`.
One page-load test per tool — confirms every tool URL is reachable.

### `regression.xml` — Full regression
Runs all `groups = {"regression"}` tests across all 14 regression classes + `AuthenticationTests`.
`thread-count="3"` for parallel execution.

### `sanity.xml` — Sanity check (14 page-load tests)
Runs one method per feature class — fastest possible gate before a full regression run.

```xml
<!-- Example: one method per class -->
<class name="com.ilovepdf.tests.regression.MergePDFTests">
    <methods><include name="verifyMergePageLoads"/></methods>
</class>
```

### `e2e.xml` — End-to-end workflows
Runs all `groups = {"e2e"}` tests. Includes `PDFWorkflowE2ETest` (14 full user journeys) plus e2e-tagged methods in regression classes (e.g. `verifyMergeTwoPDFs`, `verifyProtectWithValidPassword`).

---


## Configuration

`src/test/resources/config/config.properties`

```properties
browser=chrome
base.url=https://www.ilovepdf.com
implicit.wait=10
explicit.wait=20
page.load.timeout=30
headless=false
maximize.window=true
environment=qa
screenshot.path=screenshots/
report.path=reports/
download.path=downloads/
testdata.excel.path=src/test/resources/testdata/excel/TestData.xlsx
testdata.json.path=src/test/resources/testdata/json/testdata.json
retry.count=2
parallel.execution=false
thread.count=3
```

---

## Test Data Files

All files live in `src/test/resources/testdata/pdf/`.

| File | Used By | Notes |
|------|---------|-------|
| `sample1.pdf` | Merge, Split, Compress, PDF→*, Rotate, Protect, Unlock | Primary PDF — 3 pages |
| `sample2.pdf` | Merge (2-file), boundary tests | Secondary PDF — 3 pages |
| `sample3.pdf` | Merge (3-file boundary test) | Third PDF — 3 pages |
| `sample1.docx` | Word to PDF | Multi-section Word doc with table |
| `sample1.pptx` | PowerPoint to PDF | 5-slide presentation |
| `sample1.xlsx` | Excel to PDF | 3-sheet Excel workbook |
| `sample1.jpg` | JPG to PDF, PDF to JPG tests | 1200×900 JPEG |
| `sample2.jpg` | Multi-image JPG boundary test | 1200×900 JPEG |
| `locked_sample.pdf` | Unlock PDF tests | Password-protected — **password: `test1234`** |
| `empty.pdf` | Negative/validation tests | Zero-content PDF |
| `empty.ppt` | PowerPoint validation tests | Empty presentation |
| `large.pptx` | Large file boundary test | Large PowerPoint |
| `image.jpg` | Invalid file type tests | Used to trigger format errors |

> Generate all test data files by running the Python test data generator included in the project.

---


## Helper Methods Reference

### Click
```java
ClickHelper.click(locator)
ClickHelper.clickUsingJS(locator)
ClickHelper.clickUsingActions(locator)
ClickHelper.doubleClick(locator)
ClickHelper.rightClick(locator)
ClickHelper.hoverAndClick(hover, target)
```

### Input
```java
InputHelper.type(locator, text)
InputHelper.clearAndType(locator, text)
InputHelper.sendKeysSlowly(locator, text, delay)
InputHelper.clearField(locator)
```

### Dropdown
```java
DropdownHelper.selectByVisibleText(locator, text)
DropdownHelper.selectByValue(locator, value)
DropdownHelper.selectByIndex(locator, index)
DropdownHelper.getSelectedOption(locator)
DropdownHelper.getAllOptions(locator)
```

### Wait
```java
WaitUtil.waitForElement(locator, WaitStrategy.VISIBLE)
WaitUtil.waitForElement(locator, WaitStrategy.CLICKABLE)
WaitUtil.waitForElement(locator, WaitStrategy.PRESENCE)
WaitUtil.waitForInvisibility(locator)
WaitUtil.waitForUrlContains(partial)
WaitUtil.waitForTitleContains(title)
WaitUtil.fluentWait(condition, timeout, polling)
```

### Validation
```java
ValidationHelper.verifyText(locator, expected)
ValidationHelper.verifyTextContains(locator, partial)
ValidationHelper.verifyElementDisplayed(locator)
ValidationHelper.verifyElementEnabled(locator)
ValidationHelper.verifyUrlContains(partial)
ValidationHelper.verifyTitle(expected)
ValidationHelper.verifyAttribute(locator, attr, expected)
```

### File Upload / Download
```java
FileHelper.uploadFile(locator, fileName)           // resolves from testdata/pdf/
FileHelper.uploadFileAbsolute(locator, path)
FileHelper.isFileDownloaded(fileName, timeout)
FileHelper.deleteFile(absolutePath)
FileHelper.getFileSize(absolutePath)
```

### Window & Frame
```java
WindowHelper.switchToNewTab()
WindowHelper.switchToWindowByTitle(title)
WindowHelper.switchToMainWindow()
WindowHelper.closeCurrentTab()
WindowHelper.getWindowCount()

FrameHelper.switchToFrame(index | nameOrId | locator)
FrameHelper.switchToDefaultContent()
FrameHelper.switchToParentFrame()
```

### Alert
```java
AlertHelper.acceptAlert()
AlertHelper.dismissAlert()
AlertHelper.getAlertText()
AlertHelper.sendKeysToAlert(text)
```

### Scroll & JavaScript
```java
ScrollHelper.scrollToElement(locator)
ScrollHelper.scrollToTop()
ScrollHelper.scrollToBottom()
JavaScriptHelper.highlight(locator)
JavaScriptHelper.setValue(locator, value)
```

### Screenshot
```java
ScreenshotHelper.captureScreenshot(testName)
ScreenshotHelper.captureScreenshotBase64()
```

---

## iLovePDF Functional Modules Covered

Features are listed in the order they appear on the iLovePDF website.

| # | Feature | Category | Test Class | TCs | Key Test Scenarios |
|---|---------|----------|------------|-----|--------------------|
| 1 | Merge PDF | Organize PDF | `MergePDFTests` | 10 | Merge 2 & 3 files, button hidden before upload, button appears after upload |
| 2 | Split PDF | Organize PDF | `SplitPDFTests` | 11 | Split by ranges, split by pages, back navigation |
| 3 | Compress PDF | Optimize PDF | `CompressPDFTests` | 13 | Recommended / Extreme / Less levels, invalid file type, empty file |
| 4 | Word to PDF | Convert to PDF | `WordToPDFTests` | 8 | Upload .docx, convert button hidden before upload, convert button appears after |
| 5 | PowerPoint to PDF | Convert to PDF | `PowerPointToPDFTests` | 12 | .ppt + .pptx upload, drag & drop, large file, empty file |
| 6 | Excel to PDF | Convert to PDF | `ExcelToPDFTests` | 7 | Upload .xlsx, convert, soft assert |
| 7 | PDF to Word | Convert from PDF | `PDFToWordTests` | 8 | Upload PDF, convert button state, conversion success |
| 8 | PDF to PowerPoint | Convert from PDF | `PDFToPowerPointTests` | 7 | Upload PDF, convert, soft assert |
| 9 | PDF to Excel | Convert from PDF | `PDFToExcelTests` | 6 | Upload PDF, convert, soft assert |
| 10 | PDF to JPG | Convert from PDF | `PDFToJPGTests` | 11 | High / Medium / Low quality, convert button state |
| 11 | Rotate PDF | Edit PDF | `RotatePDFTests` | 12 | Rotate all right, rotate all left, button hidden before upload |
| 12 | Protect PDF | PDF Security | `ProtectPDFTests` | 12 | Valid password, empty password, strong password, single-char boundary |
| 13 | Unlock PDF | PDF Security | `UnlockPDFTests` | 9 | Non-protected PDF, protected PDF (password: test1234) |

---

## Sample Test Code

```java
// Smoke test — navigation
@Test(description = "TC_NAV_002 - Verify navigation to Merge PDF page", groups = {"smoke"})
public void verifyNavigationToMergePage() {
    Assert.assertTrue(
        new HomePage().open().goToMergePDF().isPageDisplayed("title1"),
        "Merge PDF page not displayed");
    log.info("TC_NAV_002 PASSED");
}

// Regression — button hidden before upload (critical iLovePDF pattern)
@Test(description = "TC_MERGE_003 - Merge button NOT visible before upload", groups = {"regression"})
public void verifyMergeButtonNotVisibleBeforeUpload() {
    MergePDFPage merge = new HomePage().open().goToMergePDF();
    Assert.assertFalse(merge.isMergeButtonDisplayed(),
        "Merge button should NOT be visible before any file is uploaded");
    log.info("TC_MERGE_003 PASSED");
}

// E2E — full workflow
@Test(description = "TC_E2E_013 - E2E: Protect PDF with valid password", groups = {"e2e"})
public void e2eProtectPdfWorkflow() {
    ProtectPDFPage protect = new HomePage().open().goToProtectPDF();
    protect.uploadFile("sample1.pdf")
           .enterPassword("Test@1234")
           .enterPasswordConfirm("Test@1234")
           .clickProtect();
    Assert.assertTrue(protect.isProtectSuccessful() || protect.isDownloadAvailable());
    log.info("TC_E2E_013 PASSED");
}
```

---

## Reports

After test execution, find output in:

| Location | Content |
|----------|---------|
| `reports/ExtentReport_<timestamp>.html` | Full HTML report with test steps, status, screenshots |
| `screenshots/` | Auto-captured screenshots for failed tests |
| `logs/automation.log` | Structured Log4j2 log output |
| `downloads/` | Files downloaded during test execution |

---

## Known Issues & Notes

| Issue | Status | Notes |
|-------|--------|-------|
| `SessionNotCreatedException` on some machines | Fixed | WebDriverManager auto-downloads matching ChromeDriver. Works on all machines once dependencies are resolved. |
| `#processTask` button hidden on page load | By design | All tools hide the action button until a file is uploaded. Tests correctly assert `assertFalse` before upload. |
| TC-06 (Compress empty file) | FAIL | iLovePDF currently accepts empty PDF without error — raised as a defect. |
| TC-18 (PPT to PDF empty file) | FAIL | iLovePDF accepts empty PPT without error — raised as a defect. |

---

## Git Workflow

### Branching Strategy

| Branch | Purpose |
|--------|---------|
| `main` | Production-ready code |
| `develop` | Integration branch |
| `qa` | QA environment |
| `feature/<name>` | New features (e.g. `feature/rotate-pdf-tests`) |
| `release/<version>` | Release preparation |
| `hotfix/<name>` | Production fixes |

### Pull Request Rules

1. Create feature branch from `develop`
2. Open PR back to `develop`
3. At least 1 reviewer required
4. All tests must pass in CI
5. Squash and merge

---

## Coding Standards

### Naming Conventions

| Type | Convention | Example |
|------|------------|---------|
| Class | PascalCase | `MergePDFPage` |
| Method | camelCase | `uploadFile()` |
| Variable | camelCase | `fileName` |
| Constant | UPPER_SNAKE_CASE | `IMPLICIT_WAIT` |
| Package | lowercase | `com.ilovepdf.pages` |
| Test class | `<Feature>Tests` | `MergePDFTests` |
| Test method | `verify<Behavior>` | `verifyMergeButtonAppearsAfterUpload()` |

### Locator Strategy (priority order)

1. `id` — fastest, most reliable
2. `name`
3. `cssSelector`
4. `xpath` with `contains()` for dynamic IDs
5. Avoid absolute XPaths

### Code Style

- ThreadLocal for parallel execution safety (`DriverManager`)
- Page Objects: only actions, no assertions
- Tests: assertions only, never raw Selenium calls
- Helpers: stateless, static methods
- Constants centralized in `FrameworkConstants`
- `SoftAssertManager.get()` for multi-assertion tests; always call `assertAll()` at the end

---

## Scalability Recommendations

- Add **CI/CD pipeline** (GitHub Actions / Jenkins) — run `smoke.xml` as PR gate, `regression.xml` on merge
- Add **Docker + Selenium Grid** for parallel cross-browser execution
- Add **Allure Reports** as a richer alternative to ExtentReports
- Add **API layer** for test setup/teardown (faster than UI)
- Enable **parallel execution** at class or method level via TestNG XML
- Add **DB validation** if server-side persistence checks are needed

---

## License

For educational and QA training use only.
