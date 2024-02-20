package com.hillstonenet.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: Fang Jinxu
 * @Description:
 * @Date: 2023-11-21 16:49
 */
public class ExcelUtil {

    private ExcelUtil() {

    }
    public static HSSFWorkbook generateExcel(List<String> header, List<Map<String, Object>> dataMapList, List<String> fieldNameList) {
        // 创建sheet
        HSSFWorkbook hwk = new HSSFWorkbook();
        HSSFSheet sheet = hwk.createSheet("台账");
        // 设置sheet表头
        HSSFRow headerRow = sheet.createRow(0);
        for (int colIndex = 0; colIndex < header.size(); colIndex++) {
            headerRow.createCell(colIndex).setCellValue(header.get(colIndex));
        }
        // 填充数据
        for (int i = 0, rowIndex = 1; i < dataMapList.size(); i++, rowIndex++) {
            HSSFRow dataRow = sheet.createRow(rowIndex);
            for (int colIndex = 0; colIndex < fieldNameList.size(); colIndex++) {
                HSSFCell cell = dataRow.createCell(colIndex);
                cell.setCellValue(dataMapList.get(i).get(fieldNameList.get(colIndex)).toString());
            }
        }
        return hwk;
    }

    public static <T> List<Map<String, Object>> getPaddingData(List<T> dataList, Class<T> clazz) {
        List<Map<String, Object>> dataMapList = new ArrayList<>();
        Map<String, Field> nameFiledMap = Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toMap(Field::getName, Function.identity()));
        List<String> fieldNameList = Arrays.stream(clazz.getDeclaredFields()).map(Field::getName).collect(Collectors.toList());
        for (T data : dataList) {
            Map<String, Object> dataMap = new HashMap<>();
            for (String fieldName : fieldNameList) {
                ReflectionUtils.makeAccessible(nameFiledMap.get(fieldName));
                dataMap.put(fieldName, ReflectionUtils.getField(nameFiledMap.get(fieldName), data));
            }
            dataMapList.add(dataMap);
        }
        return dataMapList;
    }

    /**
     * 合并单元格并设置样式
     * @param sheet 工作表
     * @param startRow 起始行
     * @param endRow 结束行
     * @param startCol 起始列
     * @param endCol 结束列
     * @param style 单元格样式
     */
    public static void mergeRegion(HSSFSheet sheet, int startRow, int endRow, int startCol, int endCol, HSSFCellStyle style) {
        sheet.addMergedRegion(new CellRangeAddress(startRow, endRow, startCol, endCol));
        sheet.getRow(startRow).getCell(startCol).setCellStyle(style);
    }

    /**
     * 合并单元格
     * @param sheet
     * @param startRow
     * @param endRow
     * @param startCol
     * @param endCol
     */
    public static void mergeAndCenter(HSSFSheet sheet, int startRow, int endRow, int startCol, int endCol, HSSFCellStyle style) {
        // 居中
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  // 水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  // 垂直居中
        mergeRegion(sheet, startRow, endRow, startCol, endCol, style);
    }

    /**
     * 合并单元格并居中（同列）
     * @param sheet
     * @param startRow
     * @param endRow
     * @param col
     */
    public static void mergeAndCenter(HSSFSheet sheet, int startRow, int endRow, int col, HSSFCellStyle style) {
        if (endRow - startRow > 0) {
            mergeAndCenter(sheet, startRow, endRow, col, col, style);
        }
    }

}