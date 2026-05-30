# iLovePDF Automation Framework

Enterprise-grade Selenium TestNG automation framework for [ilovepdf.com](https://www.ilovepdf.com).

## Tech Stack

- Java 21
- Selenium WebDriver 4.20.0
- TestNG 7.10.2
- Maven
- WebDriverManager 5.8.0
- Extent Reports 5.1.1
- Apache POI 5.2.5
- Log4j2 2.23.1
- Jackson 2.17.0
- JavaFaker 1.0.2

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
│   │   │   ├── configuration/    # Config reader
│   │   │   ├── constants/        # Framework constants
│   │   │   ├── drivers/          # DriverFactory & DriverManager
│   │   │   ├── enums/            # BrowserType, Environment, WaitStrategy
│   │   │   ├── exceptions/       # Custom exceptions
│   │   │   ├── helpers/          # Click, Input, File, Window, JS helpers...
│   │   │   ├── listeners/        # TestListener, RetryAnalyzer
│   │   │   ├── pages/            # Page Object Model
│   │   │   ├── reports/          # ExtentReportManager
│   │   │   └── utilities/        # Wait, Excel, JSON, Logger
│   │   └── resources/
│   │       └── log4j2.xml
│   │
│   └── test/
│       ├── java/com/ilovepdf/
│       │   ├── base/             # BaseTest
│       │   ├── dataproviders/    # Excel/JSON data providers
│       │   └── tests/
│       │       ├── smoke/
│       │       ├── sanity/
│       │       ├── regression/
│       │       └── e2e/
│       └── resources/
│           ├── config/config.properties
│           ├── testdata/
│           │   ├── excel/
│           │   ├── json/
│           │   └── pdf/          # Sample PDFs for upload tests
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

## Package Responsibilities

| Package | Purpose |
|---------|---------|
| `configuration` | Reads `config.properties` into static accessors |
| `constants` | Centralized framework constants (paths, timeouts) |
| `drivers` | DriverFactory creates drivers; DriverManager stores ThreadLocal driver |
| `enums` | Type-safe enums (BrowserType, WaitStrategy, Environment) |
| `exceptions` | Custom `FrameworkException` for clear error context |
| `helpers` | Reusable actions: Click, Input, File, Window, Frame, JS, Scroll, Screenshot |
| `listeners` | TestListener (logging, reports, screenshots), RetryAnalyzer, AnnotationTransformer |
| `pages` | Page Object Model — one class per page |
| `reports` | ExtentReportManager (HTML reports with screenshots) |
| `utilities` | WaitUtil, ExcelUtil, JsonUtil, RandomDataGenerator, SoftAssertManager, LoggerUtil |
| `base` | BaseTest — setUp/tearDown for all tests |
| `dataproviders` | Excel & JSON DataProviders for data-driven testing |

## How to Run

### Run regression suite (default)
```bash
mvn clean test
```

### Run a specific suite
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/suites/smoke.xml
mvn clean test -DsuiteXmlFile=src/test/resources/suites/sanity.xml
mvn clean test -DsuiteXmlFile=src/test/resources/suites/e2e.xml
```

### Run with a specific browser
Edit `src/test/resources/config/config.properties`:
```properties
browser=chrome    # or firefox / edge
headless=true
```

## Helper Methods Reference

### Click
- `ClickHelper.click(locator)`
- `ClickHelper.clickUsingJS(locator)`
- `ClickHelper.clickUsingActions(locator)`
- `ClickHelper.doubleClick(locator)`
- `ClickHelper.rightClick(locator)`
- `ClickHelper.hoverAndClick(hover, target)`

### Input
- `InputHelper.type(locator, text)`
- `InputHelper.clearAndType(locator, text)`
- `InputHelper.sendKeysSlowly(locator, text, delay)`
- `InputHelper.clearField(locator)`

### Dropdown
- `DropdownHelper.selectByVisibleText(locator, text)`
- `DropdownHelper.selectByValue(locator, value)`
- `DropdownHelper.selectByIndex(locator, index)`
- `DropdownHelper.getSelectedOption(locator)`
- `DropdownHelper.getAllOptions(locator)`

### Wait
- `WaitUtil.waitForElement(locator, strategy)`
- `WaitUtil.waitForInvisibility(locator)`
- `WaitUtil.waitForUrlContains(partial)`
- `WaitUtil.waitForTitleContains(title)`
- `WaitUtil.fluentWait(condition, timeout, polling)`

### Validation
- `ValidationHelper.verifyText(locator, expected)`
- `ValidationHelper.verifyTextContains(locator, partial)`
- `ValidationHelper.verifyElementDisplayed(locator)`
- `ValidationHelper.verifyElementEnabled(locator)`
- `ValidationHelper.verifyUrlContains(partial)`
- `ValidationHelper.verifyTitle(expected)`
- `ValidationHelper.verifyAttribute(locator, attr, expected)`

### File Upload / Download
- `FileHelper.uploadFile(locator, fileName)`
- `FileHelper.uploadFileAbsolute(locator, absolutePath)`
- `FileHelper.isFileDownloaded(fileName, timeout)`
- `FileHelper.deleteFile(absolutePath)`
- `FileHelper.getFileSize(absolutePath)`

### Window
- `WindowHelper.switchToNewTab()`
- `WindowHelper.switchToWindowByTitle(title)`
- `WindowHelper.switchToMainWindow()`
- `WindowHelper.closeCurrentTab()`
- `WindowHelper.getWindowCount()`

### Frame
- `FrameHelper.switchToFrame(index | nameOrId | locator)`
- `FrameHelper.switchToDefaultContent()`
- `FrameHelper.switchToParentFrame()`

### Alert
- `AlertHelper.acceptAlert()`
- `AlertHelper.dismissAlert()`
- `AlertHelper.getAlertText()`
- `AlertHelper.sendKeysToAlert(text)`

### Scroll & JavaScript
- `ScrollHelper.scrollToElement(locator)`
- `ScrollHelper.scrollToTop()`
- `ScrollHelper.scrollToBottom()`
- `JavaScriptHelper.highlight(locator)`
- `JavaScriptHelper.setValue(locator, value)`

### Screenshot
- `ScreenshotHelper.captureScreenshot(testName)`
- `ScreenshotHelper.captureScreenshotBase64()`

## iLovePDF Functional Modules Covered

| Module | Purpose | Positive Tests | Negative Tests |
|--------|---------|----------------|----------------|
| Merge PDF | Combine multiple PDFs | Merge 2+ files | Upload non-PDF |
| Split PDF | Split into ranges/pages | Split by range | Invalid range |
| Compress PDF | Reduce file size | All 3 levels | Corrupted file |
| PDF to Word | Convert PDF → DOCX | Convert valid PDF | Empty file |
| Word to PDF | Convert DOCX → PDF | Convert .docx | Wrong format |
| PDF to JPG | Convert PDF → images | Multi-page PDF | Empty PDF |
| JPG to PDF | Convert images → PDF | Multiple JPGs | Wrong type |
| Rotate PDF | Rotate pages | 90/180/270 | No file |
| Watermark | Add watermark | Text & image | Empty text |
| Unlock PDF | Remove password | Correct password | Wrong password |
| Protect PDF | Add password | Valid password | Empty password |
| Organize PDF | Reorder pages | Drag/drop reorder | Invalid order |

## Git Workflow

### Branching Strategy

- `main` — production-ready code
- `develop` — integration branch
- `qa` — QA environment branch
- `feature/<name>` — new features (e.g. `feature/merge-pdf-tests`)
- `release/<version>` — release prep
- `hotfix/<name>` — production fixes

### Pull Request Rules

1. Create feature branch from `develop`
2. Open PR back to `develop`
3. At least 1 code reviewer required
4. All tests must pass in CI
5. Squash and merge

### Branch Protection

- `main` and `develop` require PR approval
- No direct pushes
- Status checks (CI) must pass before merge

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
| Test method | `verify<Behavior>` | `verifyMergeTwoPDFs()` |

### Locator Strategy (priority order)

1. `id` — fastest, most reliable
2. `name`
3. `cssSelector`
4. `xpath` (only when needed; prefer `contains()` for dynamic IDs)
5. Avoid absolute XPaths

### Code Style

- Always close resources with try-with-resources
- ThreadLocal for parallel safety
- Page Objects: no assertions, only actions
- Tests: assertions only, never raw Selenium calls
- Helpers: stateless, static methods
- Constants centralized in `FrameworkConstants`

## Sample Test Code

```java
public class SampleMergeTest extends BaseTest {

    @Test(description = "Merge two PDFs and verify success", groups = {"e2e"})
    public void mergeTwoPDFs() {
        new HomePage()
            .open()
            .goToMergePDF()
            .uploadFile("sample1.pdf")
            .uploadFile("sample2.pdf")
            .clickMerge();

        Assert.assertTrue(new MergePDFPage().isMergeSuccessful(),
            "Merge operation failed");
    }
}
```

## Reports

After test execution, find reports in:
- HTML: `reports/ExtentReport_<timestamp>.html`
- Screenshots: `screenshots/`
- Logs: `logs/automation.log`

## Scalability Recommendations

- Add **CI/CD pipeline** (GitHub Actions / Jenkins) — run `regression.xml` on every PR
- Add **Docker support** — Selenium Grid for parallel cross-browser testing
- Add **Allure Reports** as alternative to Extent
- Add **API layer** for setup/teardown (faster than UI)
- Add **DB validation utilities** if data persistence checks needed
- Add **Performance hooks** — measure page load times via JS Performance API
- Enable **parallel execution** at class or method level via TestNG

## License

For educational use only.
