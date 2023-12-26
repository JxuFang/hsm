package com.hillstonenet.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

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

    @Test
    public void test() throws IOException {

        try (FileInputStream inputStream = new FileInputStream("C:\\Users\\jxfang\\Desktop\\镜像\\WAF\\SG6000-ZSW-FR32890-5.5R8-3.4B3-v6.img");) {
            byte[] header = new byte[140];
            inputStream.read(header);
            ByteBuffer wrap = ByteBuffer.wrap(header);
            byte[] magic = new byte[4];
            for (int i = 0; i < magic.length; i++) {
                magic[i] = wrap.get();
            }
            System.out.println(magic.toString());
            System.out.println(wrap.getInt());
            System.out.println(wrap.getInt());
            System.out.println(wrap.getInt());
            System.out.println(wrap.getInt());
            System.out.println(wrap.getInt());
            System.out.println(wrap.getInt());
            byte[] name = new byte[32];
            wrap.get(name);
            System.out.println(new String(name, StandardCharsets.UTF_8).trim());
            wrap.get(name);
            System.out.println(new String(name, StandardCharsets.UTF_8).trim());
            wrap.get(name);
            System.out.println(new String(name, StandardCharsets.UTF_8).trim());
            wrap.get(name);
            System.out.println(new String(name, StandardCharsets.UTF_8).trim());
        }
    }

    @Test
    public void test123() {
        Pattern pattern = Pattern.compile("^[\u4E00-\u9FA5?A-Za-z\\d./_@#$%&*!():~^+-]*$");
        System.out.println(pattern.matcher("-\\").matches());
    }
}