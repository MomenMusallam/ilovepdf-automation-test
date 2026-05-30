package com.ilovepdf.dataproviders;

import com.ilovepdf.utilities.ExcelUtil;
import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return ExcelUtil.getDataAsArray("Login");
    }

    @DataProvider(name = "mergeData")
    public Object[][] mergeData() {
        return ExcelUtil.getDataAsArray("Merge");
    }

    @DataProvider(name = "compressData")
    public Object[][] compressData() {
        return ExcelUtil.getDataAsArray("Compress");
    }
}
