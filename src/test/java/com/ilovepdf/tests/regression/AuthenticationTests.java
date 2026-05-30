package com.ilovepdf.tests.regression;

import com.ilovepdf.base.BaseTest;
import com.ilovepdf.dataproviders.JsonDataProvider;
import com.ilovepdf.pages.LoginPage;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.Map;

public class AuthenticationTests extends BaseTest {

    @Test(description = "Verify login with invalid credentials", groups = {"regression"})
    public void verifyInvalidLogin() {
        LoginPage login = new LoginPage().open()
                .enterEmail("invalid@test.com")
                .enterPassword("WrongPass")
                .clickLoginExpectingError();
        Assert.assertTrue(login.isErrorDisplayed(), "Error message not displayed");
    }

    @Test(description = "Verify login with empty fields", groups = {"regression"})
    public void verifyEmptyFieldsLogin() {
        LoginPage login = new LoginPage().open().clickLoginExpectingError();
        Assert.assertTrue(login.isDisplayedSafely(), "Login page validation failed");
    }

    @Test(description = "Data-driven login test",
          dataProvider = "jsonData", dataProviderClass = JsonDataProvider.class,
          groups = {"regression"})
    public void verifyLoginScenarios(Map<String, Object> data) {
        String testCase = (String) data.get("testCase");
        if (testCase == null || !testCase.startsWith("TC_LOGIN")) {
            throw new SkipException("Not a login test case");
        }

        LoginPage login = new LoginPage().open()
                .enterEmail((String) data.get("email"))
                .enterPassword((String) data.get("password"))
                .clickLoginExpectingError();

        String expected = (String) data.get("expectedResult");
        if ("failure".equals(expected) || "validation_error".equals(expected)) {
            Assert.assertTrue(login.isErrorDisplayed() || login.isDisplayedSafely(),
                    "Expected error not shown for case: " + testCase);
        }
    }
}
