package com.ilovepdf.utilities;

import com.ilovepdf.constants.FrameworkConstants;
import com.ilovepdf.exceptions.FrameworkException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ExcelUtil {

    private ExcelUtil() {}

    public static List<Map<String, String>> readSheet(String sheetName) {
        List<Map<String, String>> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(FrameworkConstants.getExcelPath());
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) throw new FrameworkException("Sheet not found: " + sheetName);

            Row headerRow = sheet.getRow(0);
            int colCount = headerRow.getLastCellNum();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j < colCount; j++) {
                    String key = getCellValue(headerRow.getCell(j));
                    String value = getCellValue(row.getCell(j));
                    rowData.put(key, value);
                }
                data.add(rowData);
            }
        } catch (IOException e) {
            throw new FrameworkException("Failed to read Excel", e);
        }
        return data;
    }

    public static Object[][] getDataAsArray(String sheetName) {
        List<Map<String, String>> rows = readSheet(sheetName);
        if (rows.isEmpty()) return new Object[0][0];

        Object[][] arr = new Object[rows.size()][1];
        for (int i = 0; i < rows.size(); i++) {
            arr[i][0] = rows.get(i);
        }
        return arr;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> DateUtil.isCellDateFormatted(cell)
                    ? cell.getDateCellValue().toString()
                    : String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }
}
