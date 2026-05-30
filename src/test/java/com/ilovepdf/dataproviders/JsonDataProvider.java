package com.ilovepdf.dataproviders;

import com.ilovepdf.utilities.JsonUtil;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.Map;

public class JsonDataProvider {

    @DataProvider(name = "jsonData")
    public Object[][] jsonData() {
        List<Map<String, Object>> data = JsonUtil.readJsonAsList();
        Object[][] arr = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) arr[i][0] = data.get(i);
        return arr;
    }
}
