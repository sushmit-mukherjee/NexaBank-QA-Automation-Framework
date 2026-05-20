package com.nexabank.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class ExcelUtils {
    private ExcelUtils() {
    }

    public static List<List<String>> readSheet(String filePath, String sheetName) {
        List<List<String>> data = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheet(sheetName);
            for (Row row : sheet) {
                List<String> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    rowData.add(cell.toString());
                }
                data.add(rowData);
            }
            return data;
        } catch (IOException exception) {
            throw new RuntimeException("Unable to read Excel file: " + filePath, exception);
        }
    }
}
