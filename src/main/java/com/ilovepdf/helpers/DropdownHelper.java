package com.ilovepdf.helpers;

import com.ilovepdf.enums.WaitStrategy;
import com.ilovepdf.utilities.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class DropdownHelper {

    public static void selectByVisibleText(By locator, String text) {
        WebElement element = WaitUtil.waitForElement(locator, WaitStrategy.VISIBLE);
        new Select(element).selectByVisibleText(text);
    }

    public static void selectByValue(By locator, String value) {
        WebElement element = WaitUtil.waitForElement(locator, WaitStrategy.VISIBLE);
        new Select(element).selectByValue(value);
    }

    public static void selectByIndex(By locator, int index) {
        WebElement element = WaitUtil.waitForElement(locator, WaitStrategy.VISIBLE);
        new Select(element).selectByIndex(index);
    }

    public static String getSelectedOption(By locator) {
        WebElement element = WaitUtil.waitForElement(locator, WaitStrategy.VISIBLE);
        return new Select(element).getFirstSelectedOption().getText();
    }

    public static List<String> getAllOptions(By locator) {
        WebElement element = WaitUtil.waitForElement(locator, WaitStrategy.VISIBLE);
        return new Select(element).getOptions()
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
