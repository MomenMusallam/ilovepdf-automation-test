package com.ilovepdf.pages;

import com.ilovepdf.configuration.ConfigReader;
import com.ilovepdf.helpers.BrowserHelper;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By errorMessage = By.cssSelector(".alert-danger");
    private final By forgotPasswordLink = By.cssSelector("a[href*='reset_password']");

    public LoginPage open() {
        BrowserHelper.navigateTo(ConfigReader.get("base.url") + "/signin");
        return this;
    }

    public LoginPage enterEmail(String email) {
        clearAndType(emailField, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        clearAndType(passwordField, password);
        return this;
    }

    public HomePage clickLogin() {
        click(loginButton);
        return new HomePage();
    }

    public LoginPage clickLoginExpectingError() {
        click(loginButton);
        return this;
    }

    public String getErrorMessage() { return getText(errorMessage); }
    public boolean isErrorDisplayed() { return isDisplayed(errorMessage); }
    public boolean isDisplayedSafely() { return isDisplayed(emailField); }
}
