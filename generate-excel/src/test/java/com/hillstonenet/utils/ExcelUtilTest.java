package com.hillstonenet.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ExcelUtilTest {

    @Test
    public void test1() {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("ledger");
        HSSFRow firstRow = sheet.createRow(0);
        HSSFCell firstCell = firstRow.createCell(0);
        firstCell.setCellValue("test");
        try (OutputStream output = new FileOutputStream("./testExcel.xls")) {
            wb.write(output);
            output.flush();
        } catch (IOException e) {

        }
    }
}